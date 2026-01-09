package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Classe para operações de tempo do robô
 */
public class TimerRobot {
    private Timer m_Timer;

    /*
     * Construtor privado
     */
    public TimerRobot() {
        m_Timer = new Timer();
        
    }

    /**
     * Inicia o timer
     */
    public void startTimer() {
        m_Timer.start();
        SmartDashboard.putNumber("Timer atual", getSeconds());
    }

    /**
     * Para o timer
     */
    public void stopTimer() {
        m_Timer.stop();
        SmartDashboard.putNumber("Timer atual", getSeconds());
    }

    /**
     * Reinicia o timer
     */
    public void restartTimer() {
        m_Timer.restart();
        SmartDashboard.putNumber("Timer atual", getSeconds());
    }

    /**
     * Reseta o timer para 0, sem parar a contagem
     */
    public void resetTimer() {
        m_Timer.reset();
        SmartDashboard.putNumber("Timer atual", getSeconds());
    }

    /**
     * Retorna quantos segundos se passaram do início
     * @return
     */
    public double getSeconds() {
        return m_Timer.get();
    }

    /**
     * Retorna quantos minutos se passaram do inicio
     * @return
     */
    public double getMinutes() {
        return getSeconds() / 60;
    }
}
