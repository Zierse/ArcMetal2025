// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.SensorConstants;
import frc.robot.commands.ElevadorCommand;


public class Robot extends TimedRobot {
  private Command autonomousCommand;

  private RobotContainer robotContainer;

  private ElevadorCommand m_elevadorCommand = new ElevadorCommand();


  public Robot() {
    robotContainer = new RobotContainer();

  ElevadorCommand.motorElevador.getEncoder().setPosition(0);

  System.out.println("PH ID" + SensorConstants.PNEUMATIC_CHANNEL);
  System.out.println("Led R channel" + Constants.LEDConstants.LED_R);

  }

  @Override
  public void robotPeriodic() {

  CommandScheduler.getInstance().run();

  SmartDashboard.putNumber("Encoder Elevador:", ElevadorCommand.motorElevador.getEncoder().getPosition());

  // SmartDashboard.putBoolean("Sensor IF",.get());

  

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    if (robotContainer != null) {
      autonomousCommand = robotContainer.getAutonomousCommand();
      if (autonomousCommand != null) {
          System.out.println("Executando modo autonomo: " + autonomousCommand.getName());
          autonomousCommand.schedule();
      } else {
          System.out.println("Nenhum comando autonomo foi selecionado.");
      }
  } else {
      System.out.println("robotContainer é nulo!");
  }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
