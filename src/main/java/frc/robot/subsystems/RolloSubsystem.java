package frc.robot.subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TimerRobot;
import frc.robot.Constants.Rollo_Constants;
import frc.robot.Constants.SensorConstants;
import frc.robot.commands.ElevadorCommand;


public class RolloSubsystem extends SubsystemBase {


    TimerRobot timerRobot = new TimerRobot();

    private final SparkMax rolloIntakeDireito;
    private final SparkMax rolloIntakeEsquerdo;

    private final SparkClosedLoopController pidRolloDireito;
    private final SparkClosedLoopController pidRolloEsquerdo;

    private static final double kP = Constants.PIDConstantsRolloOuttake.kP;
    private static final double kI = Constants.PIDConstantsRolloOuttake.kI;
    private static final double kD = Constants.PIDConstantsRolloOuttake.kD;
    private static final double kFF = Constants.PIDConstantsRolloOuttake.kFF;

    private final DigitalInput sensorCoral = new DigitalInput(SensorConstants.SENSOR_IF_ID);

    private final LedSub ledSub;
   

    public RolloSubsystem(LedSub ledSub) {
        this.ledSub = ledSub;

      
        rolloIntakeDireito = new SparkMax(Rollo_Constants.ROLLER_MOTOR_RIGHT, MotorType.kBrushless);
        rolloIntakeEsquerdo = new SparkMax(Rollo_Constants.ROLLER_MOTOR_LEFT, MotorType.kBrushless);

        pidRolloDireito = rolloIntakeDireito.getClosedLoopController();
        pidRolloEsquerdo = rolloIntakeEsquerdo.getClosedLoopController();

        rolloIntakeDireito.setCANTimeout(250);

        // Create and apply configuration for roller motor. Voltage compensation helps
        // the roller behave the same as the battery
        // voltage dips. The current limit helps prevent breaker trips or burning out
        // the motor in the event the roller stalls.
        SparkMaxConfig configRolloDireito = new SparkMaxConfig();
        SparkMaxConfig configRolloEsquerdo = new SparkMaxConfig();

        configRolloDireito
            .smartCurrentLimit(Rollo_Constants.ROLLER_MOTOR_RIGHT)
            .idleMode(IdleMode.kBrake)
            .inverted(false)
            .closedLoop.pid(kP, kI, kD)
            .velocityFF(kFF);


        configRolloEsquerdo
            .disableFollowerMode()
            .smartCurrentLimit(Rollo_Constants.ROLLER_MOTOR_LEFT)
            .idleMode(IdleMode.kBrake)
            .inverted(true)
            .closedLoop.pid(kP, kI, kD)
            .velocityFF(kFF);


        rolloIntakeDireito.configure(configRolloDireito, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rolloIntakeEsquerdo.configure(configRolloEsquerdo, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);


    }

    public boolean isObjectDetectado(){
        return sensorCoral.get();
    }

    public void setTargetRPM(double rpm){
        pidRolloDireito.setReference(rpm, ControlType.kVelocity);
        pidRolloEsquerdo.setReference(rpm, ControlType.kVelocity);
    }

    public void runRollo(double speed) {
        rolloIntakeDireito.set(speed);
        rolloIntakeEsquerdo.set(speed);

        ledSub.setColor(true, false, false);
        // medeP();
    }

    public void stopRollo() {
        rolloIntakeDireito.set(0);
        rolloIntakeEsquerdo.set(0);

        ledSub.setLaranjaFixo();
    }

    public void executePID(){
        if (isObjectDetectado()) {
            setTargetRPM(-1000);
        } else {
            setTargetRPM(170);
        }
    }

   

    

    // public void medeP(){
    //     timerRobot.startTimer();
    //     while (timerRobot.getSeconds() <= 5)
    //     {
    //         PIDConstantsRolloOuttake.kP += 0.0001;
    //     }
    //     timerRobot.stopTimer();
    //     stopRollo();
    // }

    @Override
    public void periodic () {

        if(isObjectDetectado()){
        ledSub.setLaranjaFixo();
    }else{
        ledSub.piscarLaranja();
        }
    }
   
}
