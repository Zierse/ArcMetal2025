// package frc.robot.autos;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.DriveTrainSubsystem;
// import frc.robot.subsystems.RolloSubsystem;

// public class AutonomoRank extends Command {

//     private DriveTrainSubsystem m_drive;
//     private RolloSubsystem m_RolloSubsystem;
//     private Timer timer;
//     private double direcao_por_segundo = 3.25;

//     public AutonomoRank(DriveTrainSubsystem drive, RolloSubsystem rollo) {
//         m_drive = drive;
//         m_RolloSubsystem = rollo;
//         timer = new Timer();
//         addRequirements(m_drive, m_RolloSubsystem);
//     }

//     @Override
//     public void initialize() {
//         timer.start();
//     }

//     @Override
//     public void execute() {

//         if (timer.get() < direcao_por_segundo) {
//             m_drive.movimentaRobo(0.4, 0);
//         }else{
//             m_drive.movimentaRobo(0, 0);
//             m_RolloSubsystem.runRollo(-1);
//         }
//     }

//     @Override
//     public void end(boolean interrupted) {
//         m_drive.movimentaRobo(0, 0);
//         m_RolloSubsystem.runRollo(0);
//         timer.stop();
//     }

//     @Override
//     public boolean isFinished() {

//         return timer.get() >= 6;
//     }


// }
