// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IO_Constants;
import frc.robot.commands.AlgieInCommand;
import frc.robot.commands.AlgieOutCommand;
import frc.robot.commands.CoralOutCommand;
import frc.robot.commands.CoralStackCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevadorCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.RolloSubsystem;

public class RobotContainer {

  public final DriveTrainSubsystem m_driveSubsystem = new DriveTrainSubsystem();
  public final RolloSubsystem m_roller = new RolloSubsystem();
  
  private final CommandXboxController m_JoystickOperador = new CommandXboxController(IO_Constants.JOYSTICK_PORT_OPERATOR);

  private final PS5Controller controleDirecao = new PS5Controller(IO_Constants.JOYSTICK_PORT_DRIVE);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem, controleDirecao));

    m_JoystickOperador.rightBumper().whileTrue(new AlgieInCommand(m_roller));

    m_JoystickOperador.rightTrigger(.2).whileTrue(new AlgieOutCommand(m_roller));

    m_JoystickOperador.leftBumper().whileTrue(new AlgieInCommand(m_roller));

    m_JoystickOperador.leftTrigger(.2).whileTrue(new AlgieOutCommand(m_roller));


    m_JoystickOperador.x().whileTrue(new CoralOutCommand(m_roller));

    m_JoystickOperador.y().whileTrue(new CoralStackCommand(m_roller));

    m_JoystickOperador.a().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-15)));

    m_JoystickOperador.b().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-100)));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
