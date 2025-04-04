package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GARRAConstants;

public class GarraSub extends SubsystemBase {

    public final static SparkMax motorGarraSnow = new SparkMax(GARRAConstants.GARRASNOW_ID, MotorType.kBrushed);

    public GarraSub() {

    }

    public static void startSnow(double speed) {
        motorGarraSnow.set(speed);

    }
}
