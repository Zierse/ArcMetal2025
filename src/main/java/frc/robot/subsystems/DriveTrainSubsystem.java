
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.TracaoDriveContants;

public class DriveTrainSubsystem extends SubsystemBase {


    public static final String movimentaRobo = null;
    private final SparkMax motorSuperiorDireitoLider = new SparkMax(TracaoDriveContants.MOTOR_SUPERIOR_DIREITO, MotorType.kBrushless);
    private final SparkMax motorInferiorDireitoSeguidor = new SparkMax(TracaoDriveContants.MOTOR_INFERIOR_DIREITO, MotorType.kBrushless);

    private final SparkMax motorSuperiorEsquerdoLider = new SparkMax(TracaoDriveContants.MOTOR_SUPERIOR_ESQUERDO, MotorType.kBrushless);
    private final SparkMax motorInferiorEsquerdoSeg = new SparkMax(TracaoDriveContants.MOTOR_INFERIOR_ESQUERDO, MotorType.kBrushless);

    private final Pigeon2 giroscopio = new Pigeon2(TracaoDriveContants.gyroPigeon);

    private final RelativeEncoder encoderDireito = motorSuperiorDireitoLider.getEncoder();
    private final RelativeEncoder encoderEsquerdo = motorSuperiorEsquerdoLider.getEncoder();

    public DriveTrainSubsystem() {

        giroscopio.reset();
        motorSuperiorDireitoLider.setCANTimeout(250);
        motorInferiorDireitoSeguidor.setCANTimeout(250);

        motorSuperiorEsquerdoLider.setCANTimeout(250);
        motorInferiorEsquerdoSeg.setCANTimeout(250);

        SparkMaxConfig configMotorDireitoLider = new SparkMaxConfig();
        SparkMaxConfig configMotorDireitoSeg = new SparkMaxConfig();
        SparkMaxConfig configMotorEsquerdoSeg = new SparkMaxConfig();
        SparkMaxConfig configMotorEsquerdoLider = new SparkMaxConfig();

        configMotorDireitoLider
                .inverted(true)
                .idleMode(IdleMode.kCoast).openLoopRampRate(0.30);

        configMotorEsquerdoLider
                .inverted(false)
                .idleMode(IdleMode.kCoast).openLoopRampRate(0.30);

        configMotorEsquerdoSeg
                .inverted(false)
                .idleMode(IdleMode.kBrake).openLoopRampRate(0.30);

        configMotorDireitoSeg
                .inverted(true)
                .idleMode(IdleMode.kBrake).openLoopRampRate(0.30);

        motorSuperiorEsquerdoLider.configure(configMotorEsquerdoLider, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorInferiorEsquerdoSeg.configure(configMotorEsquerdoSeg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorSuperiorDireitoLider.configure(configMotorDireitoLider, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorInferiorDireitoSeguidor.configure(configMotorDireitoSeg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void movimentaRobo(double FrenteTras, double EsqueraDireita){

        motorSuperiorEsquerdoLider.set(FrenteTras + EsqueraDireita);
        motorInferiorEsquerdoSeg.set(FrenteTras + EsqueraDireita);
        motorSuperiorDireitoLider.set(FrenteTras - EsqueraDireita);
        motorInferiorDireitoSeguidor.set(FrenteTras - EsqueraDireita);
    }

    public void resetarPigeon(){
        giroscopio.reset();
    }

    public double readPigeon(){
        return giroscopio.getAngle();
    }
    public void stop(){
        motorSuperiorEsquerdoLider.set(0);
        motorSuperiorDireitoLider.set(0);
    }

    public void resetarEncoder(){
        motorSuperiorDireitoLider.getEncoder().setPosition(0);
        motorInferiorDireitoSeguidor.getEncoder().setPosition(0);

        motorSuperiorEsquerdoLider.getEncoder().setPosition(0);
        motorInferiorEsquerdoSeg.getEncoder().setPosition(0);
    }

    public double rotToMeters(double rot){
        return rot / TracaoDriveContants.GEAR_RATIO * Math.abs(TracaoDriveContants.PERIMETRO); 
    }

    public double readEncoder(){
        return (encoderDireito.getPosition() + encoderEsquerdo.getPosition()) / 2.0;
    }

    public double getAverageDistance(){
        return (rotToMeters(encoderDireito.getPosition()) + rotToMeters(encoderEsquerdo.getPosition())) / 2.0;
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Encoder Direito", encoderDireito.getPosition());
        SmartDashboard.putNumber("Encoder Esquerdo", encoderEsquerdo.getPosition());
        SmartDashboard.putNumber("Distancia em Metros", getAverageDistance());
    }
}
