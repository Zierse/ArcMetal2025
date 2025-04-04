package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Rollo_Constants;

public class RolloSubsystem extends SubsystemBase {

    private final SparkMax spk6RolloSnow;

    public RolloSubsystem() {

        spk6RolloSnow = new SparkMax(Rollo_Constants.ROLLER_MOTOR_SNOW, MotorType.kBrushed);


        spk6RolloSnow.setCANTimeout(250);

        // Create and apply configuration for roller motor. Voltage compensation helps
        // the roller behave the same as the battery
        // voltage dips. The current limit helps prevent breaker trips or burning out
        // the motor in the event the roller stalls.
        SparkMaxConfig rollerConfig = new SparkMaxConfig();
        rollerConfig.voltageCompensation(Rollo_Constants.ROLLER_MOTOR_VOLTAGE_COMP);
        rollerConfig.smartCurrentLimit(Rollo_Constants.ROLLER_MOTOR_CURRENT_LIMIT);
        rollerConfig.idleMode(IdleMode.kBrake);
        spk6RolloSnow.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runRollo(double speed) {
        spk6RolloSnow.set(0);
    }
}
