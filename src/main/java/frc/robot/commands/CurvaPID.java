package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class CurvaPID extends Command {
     final DriveTrainSubsystem driveSubsystem;

    private final double anguloMeta;
    private final double velocidadeMaxima;
    private final double zonaTolerancia = 3.5; // Tolerância em graus
    private final PIDController pidController;

    public CurvaPID(DriveTrainSubsystem driveSubsystem, double anguloMeta, double velocidadeMaxima) {
        this.driveSubsystem = driveSubsystem;
        this.anguloMeta = anguloMeta;
        this.velocidadeMaxima = Math.abs(velocidadeMaxima); // Garante que a velocidade seja positiva
        this.pidController = new PIDController(0.05, 0.0, 0.01); // Ajuste os valores de kP, kI e kD conforme necessário
        this.pidController.setTolerance(zonaTolerancia); // Define a tolerância do PID
        this.pidController.setSetpoint(anguloMeta); // Define o ângulo alvo

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.resetarPigeon();
        driveSubsystem.readPigeon();
    }

    public void execute() {
        double anguloAtual = driveSubsystem.readPigeon();
        double output = pidController.calculate(anguloAtual);

        // Limita a saída à velocidade máxima
        output = Math.max(-velocidadeMaxima, Math.min(velocidadeMaxima, output));

        driveSubsystem.movimentaRobo(0, output);
    }

    @Override
    public boolean isFinished() {
        return pidController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.movimentaRobo(0, 0);
    }


}
