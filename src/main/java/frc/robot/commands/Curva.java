package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Curva extends Command {
    
    private final DriveTrainSubsystem m_driveSubsystem;
    private final double Angulo;
    private final double Velocidade;
    private final double zona = 5;
    private int contador = 0;


    public Curva(DriveTrainSubsystem driveSubsystem, double Angulo, double Velocidade){
        this.m_driveSubsystem = driveSubsystem;
        this.Angulo = Angulo;
        this.Velocidade = Velocidade;

        addRequirements(m_driveSubsystem);

    }

    public void initialize() {
        m_driveSubsystem.resetarPigeon();
        System.out.print("Iniciando Curva para Angulo: " + Angulo + " com Velocidade: " + Velocidade);

    }

    @Override
    public void execute() {
        double AnguloAtual = m_driveSubsystem.readPigeon();
        double AnguloMeta = Angulo;

        double erro =  AnguloAtual - AnguloMeta;

        m_driveSubsystem.movimentaRobo(0, erro*(0.09*Velocidade));
    }

    @Override
    public boolean isFinished()  {
        double erro = m_driveSubsystem.readPigeon() + Angulo;
        
        if (erro < zona) {
            contador++;
        } else {
            contador = 0; 
        }
    System.out.print(m_driveSubsystem.readPigeon());
        return contador > 19.8;
    }

    public void end(boolean interrupted) {
        m_driveSubsystem.movimentaRobo(0, 0);
    }
}