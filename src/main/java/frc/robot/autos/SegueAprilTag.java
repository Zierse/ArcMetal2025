// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.DriveTrainSubsystem;
// import frc.robot.subsystems.Limelight;

// public class SegueAprilTag extends Command {
    
//     private final DriveTrainSubsystem m_driveSubsystem;
//     private final Limelight m_limelight;
//     private final double tamanhoDeParada = 0;

//     public SegueAprilTag(DriveTrainSubsystem driveSubsystem, Limelight limelight, double tamanhoDeParada){
//         this.m_driveSubsystem = driveSubsystem;
//         this.m_limelight = limelight;

//         addRequirements(m_driveSubsystem);

//     }

//     @Override
//     public void initialize() {

//         System.out.println("Iniciando Segue AprilTag");
//     }

//     @Override
//     public void execute(){

//         if (!m_limelight.hasTarget()) {
//             m_driveSubsystem.movimentaRobo(0.3, 0);
//         }else{
//             double tx = m_limelight.getTx();
//             double ajusteGiro = tx * 0.03;
//             double area = m_limelight.getTa();
//             double ajusteFrente = (tamanhoDeParada - area) * 0.02;

//             ajusteGiro = Math.max(-0.5, Math.min(0.5, ajusteGiro));
//             ajusteFrente = Math.max(-0.5, Math.min(0.5, ajusteFrente));
//             m_driveSubsystem.movimentaRobo(ajusteFrente, ajusteGiro);
//         }

//         System.out.println(m_limelight.hasTarget());
//     }

//     public void end(boolean interrupted) {
//         m_driveSubsystem.movimentaRobo(0, 0);
//         System.out.println("Finalizando Segue AprilTag");
//     }
//     public boolean isFinished()  {
//         return m_limelight.getTa() >= tamanhoDeParada;
//     }
// }
