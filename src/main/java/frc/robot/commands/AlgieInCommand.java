package frc.robot.commands;

import frc.robot.Constants.Alga_Constants;
import frc.robot.subsystems.RolloSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** A command to take Algae into the robot. */
public class AlgieInCommand extends Command {
  private final RolloSubsystem m_roller;

  /**
   * Rolls Algae into the intake.
   *
   * @param roller The subsystem used by this command.
   */
  public AlgieInCommand(RolloSubsystem roller) {
    m_roller = roller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(roller);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      m_roller.runRollo(Alga_Constants.ROLLER_ALGAE_IN);
  }

  // Called once the command ends or is interrupted. This ensures the roller is not running when not intented.
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
