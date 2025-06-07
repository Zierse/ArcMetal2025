// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IO_Constants;
import frc.robot.autos.AutonomoRank;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevadorCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.RolloSubsystem;

public class RobotContainer {

  final SendableChooser<Command> m_escolherAutonomo = new SendableChooser<>();

  public final DriveTrainSubsystem m_driveSubsystem = new DriveTrainSubsystem();
  public final RolloSubsystem m_roller = new RolloSubsystem();
  public final ArmSubsystem m_arm = new ArmSubsystem();
  public final AutonomoRank m_autonomoRank = new AutonomoRank(m_driveSubsystem, m_roller);
  
  private final CommandXboxController m_JoystickOperador = new CommandXboxController(IO_Constants.JOYSTICK_PORT_OPERATOR);

  private final CommandXboxController controleDirecao = new CommandXboxController(IO_Constants.JOYSTICK_PORT_DRIVE);

  public RobotContainer() {
    configureBindings();

    m_escolherAutonomo.setDefaultOption("Autonomo Rank", m_autonomoRank);
    SmartDashboard.putData(m_autonomoRank);
  }

  private void configureBindings() {

    m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem, controleDirecao));
    
    

    //CONTROLE DO EVERYBOT COM PS5
    // controleDirecao.button(2).onTrue(new AlgieInCommand(m_roller));

    // controleDirecao.button(4).onTrue(new AlgieOutCommand(m_roller));

    // controleDirecao.L1().onTrue(new CoralStackCommand(m_roller));

    // controleDirecao.R1().onTrue(new CoralOutCommand(m_roller));

    controleDirecao.povUp().whileTrue(Commands.runEnd(() -> m_arm.runArm(-0.1), () -> m_arm.runArm(0), m_arm));

    controleDirecao.povDown().whileTrue(Commands.runEnd(() -> m_arm.runArm(0.1), () -> m_arm.runArm(0), m_arm));

    controleDirecao.leftBumper().whileTrue(Commands.runEnd(() -> m_roller.runRollo(1), () -> m_roller.runRollo(0),  m_roller));

    controleDirecao.rightBumper().whileTrue(Commands.runEnd(() -> m_roller.runRollo(-1), () -> m_roller.runRollo(0),  m_roller));


//CONTROLE ELEVADOR LOGITECH


    m_JoystickOperador.start().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-0))); //PONTO ZERO ELEVADOR

    m_JoystickOperador.a().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-50))); //COLETA CORAL STATION

    m_JoystickOperador.b().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-45))); //L2 RECIFE

    m_JoystickOperador.x().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-125))); //L3 RECIFE

    m_JoystickOperador.y().onTrue(new InstantCommand(()-> ElevadorCommand.setPosition(-235))); // L4 RECIFE    

    //RB CHUTA CORAL DO FUNIL

    //LB VOLTAR CORAL

    //ANALOG DIREITO DERRUBA ALGAES

  }

  public Command getAutonomousCommand() {
    return m_autonomoRank;
  }
}
