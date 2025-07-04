// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.Rollo_Constants;
import frc.robot.subsystems.RolloSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** A CoralStackCommand that uses a roller subsystem.
 *  Used when coral is already in the L1 to get it past the first once.
 */
public class CoralStackCommand extends Command {
  private final RolloSubsystem m_roller;

  /**
   * Use this command when there is already coral in L1.
   * 
   * May be less reliable if there is no coral already in L1.
   *
   * @param roller The subsystem used by this command.
   */
  public CoralStackCommand(RolloSubsystem roller) {
    m_roller = roller;
    addRequirements(roller);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_roller.runRollo(Rollo_Constants.ROLLER_CORAL_STACK);
  }

  // Called once the command ends or is interrupted. Ensures the roller
  // is not running after we let go of the button.
  @Override
  public void end(boolean interrupted) {
    m_roller.runRollo(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}