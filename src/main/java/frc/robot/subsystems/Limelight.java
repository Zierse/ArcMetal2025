// package frc.robot.subsystems;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class Limelight extends SubsystemBase {
    
//     private final NetworkTable limelighTable;
    
//     public Limelight() {
//         limelighTable = NetworkTableInstance.getDefault().getTable("limelight");

//     }

//     public double getTx() {
//         return limelighTable.getEntry("tx").getDouble(0.0);
//     }

//     public double getTy() {
//         return limelighTable.getEntry("ty").getDouble(0.0);
//     }

//     public double getTa() {
//         return limelighTable.getEntry("ta").getDouble(0.0);
//     }

//     public boolean hasTarget() {
//         return limelighTable.getEntry("tv").getDouble(0.0) == 1.0;
//     }


    
// }
