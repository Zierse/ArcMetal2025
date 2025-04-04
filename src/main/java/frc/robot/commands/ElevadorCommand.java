package frc.robot.commands;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevadorConstants;

public class ElevadorCommand extends Command {

    public final static SparkMax motorElevador= new SparkMax(ElevadorConstants.ELEVADOR_MOTOR_ID, MotorType.kBrushless);
    private final static SparkMaxConfig configElevadorPrincipal = new SparkMaxConfig();

    private static  SparkClosedLoopController closedLoopController = motorElevador.getClosedLoopController();

    private final double setP = 0;
            
    private final double OutRange = 0;
            
    double Position = 0;

    public double getOutputRange(){
        return this.OutRange;
    }

    public double setValueP(){
        return this.setP;
    }

    public void stopElevator() {
        motorElevador.set(0);
    }

    static void configElevador(double P, double Out){
            configElevadorPrincipal.inverted(false).idleMode(IdleMode.kBrake);
            configElevadorPrincipal.openLoopRampRate(2);
            configElevadorPrincipal.encoder.positionConversionFactor(1).velocityConversionFactor(1);
            configElevadorPrincipal.closedLoop
            .feedbackSensor(ClosedLoopConfig.FeedbackSensor.kPrimaryEncoder)
            .pid(P, 0.0, 0.0)
            .outputRange(-Out, Out);
    
            motorElevador.configure(configElevadorPrincipal, null, null);
            closedLoopController = motorElevador.getClosedLoopController();
    }
    public static void setPosition(double posiiton){
        configElevador(0.5,0.5);
        closedLoopController.setReference(posiiton, ControlType.kPosition);
    }
}
