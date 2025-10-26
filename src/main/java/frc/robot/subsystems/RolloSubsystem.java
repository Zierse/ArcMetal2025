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

    private final SparkMax rolloIntakeDireito;
    private final SparkMax rolloIntakeEsquerdo;

    public RolloSubsystem() {

        rolloIntakeDireito = new SparkMax(Rollo_Constants.ROLLER_MOTOR_RIGHT, MotorType.kBrushless);
        rolloIntakeEsquerdo = new SparkMax(Rollo_Constants.ROLLER_MOTOR_LEFT, MotorType.kBrushless);


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
            .inverted(false);

        configRolloEsquerdo
            .disableFollowerMode()
            .smartCurrentLimit(Rollo_Constants.ROLLER_MOTOR_LEFT)
            .idleMode(IdleMode.kBrake)
            .inverted(true);


        rolloIntakeDireito.configure(configRolloDireito, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rolloIntakeEsquerdo.configure(configRolloEsquerdo, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);


    }

    public void runRollo(double speed) {
        rolloIntakeDireito.set(speed);
        rolloIntakeEsquerdo.set(speed);

    }
}
