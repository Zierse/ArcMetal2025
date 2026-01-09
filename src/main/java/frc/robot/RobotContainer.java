// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ElevadorConstants;
import frc.robot.Constants.IO_Constants;
import frc.robot.commands.CurvaPID;
// import frc.robot.commands.Curva;
// import frc.robot.autos.AutonomoRank;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.ElevadorCommand;
// import frc.robot.commands.EncoderTracao;
// import frc.robot.commands.Outtake;
// import frc.robot.commands.SegueAprilTag;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.LedSub;
// import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.RolloSubsystem;

public class RobotContainer {

  final SendableChooser<Command> m_escolherAutonomo = new SendableChooser<>();

  public final LedSub LedSub = new LedSub(0, 1, 2, 15);
  public final DriveTrainSubsystem m_driveSubsystem = new DriveTrainSubsystem();
  public final RolloSubsystem m_roller = new RolloSubsystem(LedSub);

  public final CurvaPID m_curvaPID = new CurvaPID(m_driveSubsystem, 0, 0);

  // public final Limelight Limelight = new Limelight();
  // public final AutonomoRank m_autonomoRank = new AutonomoRank(m_driveSubsystem, m_roller);
  // public final DriveDistanceCommand m_driveDistanceCommand = new DriveDistanceCommand(m_driveSubsystem, -1.0);
  
  private final CommandXboxController m_JoystickOperador = new CommandXboxController(IO_Constants.JOYSTICK_PORT_OPERATOR);

  // private final CommandXboxController controleDirecao = new CommandXboxController(IO_Constants.JOYSTICK_PORT_OPERATOR);

  public RobotContainer() {
    configureBindings();

    // m_escolherAutonomo.setDefaultOption("Autonomo Rank", m_autonomoRank);
    // SmartDashboard.putData(m_autonomoRank);

    configureNamedCommands();

  }

  private void configureNamedCommands() {
    // NamedCommands.registerCommand("Girar90", new Curva(m_driveSubsystem, 90, 1));
  }
  private void configureBindings() {

    m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem, m_JoystickOperador));

    
//CONTROLE ELEVADOR LOGITECH


    m_JoystickOperador.a().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(ElevadorConstants.setPositionZero))); // PONTO ZERO CORAL STATION)); //PONTO ZERO CORAL STATION

    m_JoystickOperador.x().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(ElevadorConstants.setPositionL2))); //L2 RECIFE

    m_JoystickOperador.b().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(ElevadorConstants.setPositionL3))); //L3 RECIFE

    m_JoystickOperador.y().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(ElevadorConstants.setPositionL4))); //L4 RECIFE

    m_JoystickOperador.povDown().whileTrue(Commands.runEnd(() -> m_roller.runRollo(0.09), () -> m_roller.runRollo(0),  m_roller));

    // m_JoystickOperador.leftBumper().whileTrue(Commands.runEnd(() -> m_roller.executePID(), () -> m_roller.stopRollo(),  m_roller));

    m_JoystickOperador.leftBumper().toggleOnTrue(Commands.runEnd(() -> m_roller.executePID(), () -> m_roller.stopRollo(),  m_roller));

    m_JoystickOperador.rightBumper().whileTrue(Commands.runEnd(() -> m_roller.runRollo(-0.4), () -> m_roller.runRollo(0),  m_roller));

    m_JoystickOperador.povUp().whileTrue(Commands.runEnd(() -> m_roller.runRollo(-0.09), () -> m_roller.runRollo(0),  m_roller));

    //RB CHUTA CORAL DO FUNIL

    //LB VOLTAR CORAL

    //ANALOG DIREITO DERRUBA ALGAES

  }

  public Command getAutonomousCommand() {

    double distancia1 = -1.0;
    double velocidade1 = 0.3;

    double anguloCurva1 = -90.0;
    double velocidadeCurva1 = 0.2;

    double distancia2 = -1.0;
    double velocidade2 = 0.3;

    double anguloCurva2 = -178.0;
    double velocidadeCurva2 = 0.2;

    double distancia3 = -1.0;
    double velocidade3 = 0.3;

    double anguloCurva3 = 90.0;
    double velocidadeCurva3 = 0.2;


    // return new SequentialCommandGroup(
    //   // new Curva(m_driveSubsystem, 90, 1)
    //   new SegueAprilTag(m_driveSubsystem, Limelight, 50)
    //   );

    // return new PathPlannerAuto("Meu Primeiro Auto", true);

    Command posicaoInicial = new InstantCommand(() -> m_driveSubsystem.readPigeon());
    Command resetInicio = new InstantCommand(() -> m_driveSubsystem.resetarPigeon());
    Command driveCommand1 = new DriveDistanceCommand(m_driveSubsystem, distancia1, velocidade1);
    Command curva1 = new CurvaPID(m_driveSubsystem, m_driveSubsystem.readPigeon() + anguloCurva1, velocidadeCurva1);
    Command driveForward2 = new DriveDistanceCommand(m_driveSubsystem, distancia2, velocidade2);
    Command curva2 = new CurvaPID(m_driveSubsystem, m_driveSubsystem.readPigeon() + anguloCurva2, velocidadeCurva2);
    // Command posicaoCurva2 = new InstantCommand(() -> System.out.printf("Posição curva 2: %f", m_driveSubsystem.readPigeon()));
    Command driveForward3 = new DriveDistanceCommand(m_driveSubsystem, distancia3, velocidade3);
    Command curva3Command = new CurvaPID(m_driveSubsystem, m_driveSubsystem.readPigeon() + anguloCurva3, velocidadeCurva3);
    

    
    return new  SequentialCommandGroup(
      new InstantCommand(() -> m_driveSubsystem.resetarEncoder()),
      posicaoInicial,
      resetInicio,
      driveCommand1,
      curva1,
      driveForward2,
      curva2,
      driveForward3,
      curva3Command
        );
  }
}
