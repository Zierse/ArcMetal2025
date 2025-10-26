// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IO_Constants;
import frc.robot.commands.Curva;
// import frc.robot.autos.AutonomoRank;
import frc.robot.commands.DriveCommand;
// import frc.robot.commands.ElevadorCommand;
import frc.robot.commands.EncoderTracao;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.RolloSubsystem;

public class RobotContainer {

  final SendableChooser<Command> m_escolherAutonomo = new SendableChooser<>();

  public final DriveTrainSubsystem m_driveSubsystem = new DriveTrainSubsystem();
  public final RolloSubsystem m_roller = new RolloSubsystem();
  // public final AutonomoRank m_autonomoRank = new AutonomoRank(m_driveSubsystem, m_roller);
  
  private final CommandXboxController m_JoystickOperador = new CommandXboxController(IO_Constants.JOYSTICK_PORT_OPERATOR);

  // private final CommandXboxController controleDirecao = new CommandXboxController(IO_Constants.JOYSTICK_PORT_OPERATOR);

  public RobotContainer() {
    configureBindings();

    // m_escolherAutonomo.setDefaultOption("Autonomo Rank", m_autonomoRank);
    // SmartDashboard.putData(m_autonomoRank);
  }

  private void configureBindings() {

    m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem, m_JoystickOperador));
    
//CONTROLE ELEVADOR LOGITECH

    // m_JoystickOperador.start().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(0))); //PONTO ZERO ELEVADOR

    // m_JoystickOperador.back().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(24))); //PONTO ZERO CORAL STATION

    // m_JoystickOperador.a().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(32))); //L2 RECIFE

    // m_JoystickOperador.b().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(80))); //L3 RECIFE

    // m_JoystickOperador.x().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(90))); //L3 RECIFE

    // m_JoystickOperador.y().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(162))); //L4 RECIFE

    m_JoystickOperador.povDown().whileTrue(Commands.runEnd(() -> m_roller.runRollo(0.09), () -> m_roller.runRollo(0),  m_roller));

    m_JoystickOperador.rightBumper().whileTrue(Commands.runEnd(() -> m_roller.runRollo(-0.4), () -> m_roller.runRollo(0),  m_roller));

    m_JoystickOperador.povUp().whileTrue(Commands.runEnd(() -> m_roller.runRollo(-0.09), () -> m_roller.runRollo(0),  m_roller));

    //RB CHUTA CORAL DO FUNIL

    //LB VOLTAR CORAL

    //ANALOG DIREITO DERRUBA ALGAES

  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new EncoderTracao(m_driveSubsystem, -1, 1),
      new Curva(m_driveSubsystem, 90, 1)
      );
    
  }
}
