package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IO_Constants;
import frc.robot.Constants.TracaoDriveContants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveCommand extends Command {
    
    private final DriveTrainSubsystem m_driveSubsystem;
    private final CommandXboxController m_JoystickOperador;

    public DriveCommand(DriveTrainSubsystem driveSubsystem, CommandXboxController joystick){
        this.m_driveSubsystem = driveSubsystem;
        this.m_JoystickOperador = joystick;

        addRequirements(m_driveSubsystem);
    }

    @Override
    public void initialize() {
        double gatEsquerdo = -m_JoystickOperador.getRawAxis(IO_Constants.AXIS_GATILHO_ESQUERDO);
        double gatDireito = -m_JoystickOperador.getRawAxis(IO_Constants.AXIS_GATILHO_DIREITO);
        double analogEsquerdo = m_JoystickOperador.getRawAxis(IO_Constants.AXIS_ANALOG_ESQUERDO);

        double valorGat = gatDireito - gatEsquerdo;
        double ajusteAnalogEsquerdo = analogEsquerdo * TracaoDriveContants.SLOW_MODE_GIRO;
        double ajusteGatilho = valorGat * TracaoDriveContants.SLOW_MODE_VELOCIDADE;
        
        m_driveSubsystem.movimentaRobo(ajusteGatilho, ajusteAnalogEsquerdo);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}