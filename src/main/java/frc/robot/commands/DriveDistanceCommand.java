package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveDistanceCommand extends Command{
    
     final DriveTrainSubsystem k_drive;
     
     final double targetMeters;
     final double maxSpeed;
     double startDistance;

     final double kP = 0.34;

     public DriveDistanceCommand(DriveTrainSubsystem drive, double meters, double maxSpeed){
         this.k_drive = drive;
         this.targetMeters = meters;
         this.maxSpeed = maxSpeed;

         addRequirements(k_drive);
     }

     @Override
     public void initialize(){

        k_drive.resetarEncoder();
        startDistance = k_drive.getAverageDistance();
     }

     @Override
     public void execute(){
         double currentDistance = k_drive.getAverageDistance();
         double error = (startDistance + targetMeters) - currentDistance;

         double output = error * kP;

         output = MathUtil.clamp(output, -0.3, 0.3);

         k_drive.movimentaRobo(output, 0);
     }

    @Override
    public void end(boolean interrupted){
            k_drive.stop();
    }

    @Override
    public boolean isFinished(){
        double currentDistance = k_drive.getAverageDistance();
        return Math.abs(targetMeters- currentDistance) < 0.03; // 3cm de Tolerancia
    }
}