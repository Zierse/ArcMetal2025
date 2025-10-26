package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class EncoderTracao extends Command{
    
    private final DriveTrainSubsystem m_DriveTrainSubsystem;
    private double DistanciaPorMetros;
    private double Velocidade;


    public EncoderTracao(DriveTrainSubsystem m_DriveTrainSubsystem, double DistanciaPorMetros, double Velocidade){
        this.m_DriveTrainSubsystem = m_DriveTrainSubsystem;
        this.DistanciaPorMetros = DistanciaPorMetros;
        this.Velocidade = Velocidade;

        addRequirements(m_DriveTrainSubsystem);
    }

    public void initialize(){

        m_DriveTrainSubsystem.resetarEncoder();
    }

    public void execute(){
        double Encoder = Math.abs(m_DriveTrainSubsystem.readEncoder()/8.45); //Quantos giros a roda irá andar com a redução da tracao 8.45/Essa variavel declara o valor que o tank anda.
        double distanciaPercorrida = Encoder*0.47877; //Variavel que retorna em metros quanto o tank anda.

        m_DriveTrainSubsystem.movimentaRobo((DistanciaPorMetros-distanciaPercorrida)*0.05, 0);
    }

    public boolean isFinished(){
        double Encoder = Math.abs(m_DriveTrainSubsystem.readEncoder()/8.45); //Quantos giros a roda irá andar com a redução da tracao 8.45/Essa variavel declara o valor que o tank anda.
        double distanciaPercorrida = Encoder*0.47877; //Variavel que retorna em metros quanto o tank anda.
        return Math.abs(DistanciaPorMetros-distanciaPercorrida) < 0.03; 
        
    }

    public void end(boolean interrupted){
        m_DriveTrainSubsystem.movimentaRobo(0, 0);
    }
}
