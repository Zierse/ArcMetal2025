package frc.robot.commands;

import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IO_Constants;
import frc.robot.Constants.TracaoDriveContants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveCommand extends Command {
    
    private final DriveTrainSubsystem m_driveSubsystem;
    private final CommandXboxController controleDirecao;

    private final SparkMaxConfig configAnalog = new SparkMaxConfig();

    public DriveCommand(DriveTrainSubsystem driveSubsystem, CommandXboxController joystick){
        this.m_driveSubsystem = driveSubsystem;
        this.controleDirecao = joystick;

        addRequirements(m_driveSubsystem);
    }

    @Override
    public void initialize() {
        double gatEsquerdo = -controleDirecao.getRawAxis(IO_Constants.AXIS_GATILHO_ESQUERDO);
        double gatDireito = -controleDirecao.getRawAxis(IO_Constants.AXIS_GATILHO_DIREITO);
        double analogEsquerdo = controleDirecao.getRawAxis(IO_Constants.AXIS_ANALOG_ESQUERDO);

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
