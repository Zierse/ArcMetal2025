package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedSub extends SubsystemBase {

    private final Solenoid ledR;
    private final Solenoid ledG;
    private final Solenoid ledB;

    private boolean piscarEstado = false;
    private long ultimoTempoPiscar = 0;

    public LedSub(int canalR, int canalG, int canalB, int idHub) {
        // Cria solenoides individuais
        ledR = new Solenoid(idHub, PneumaticsModuleType.REVPH, canalR);
        ledG = new Solenoid(idHub, PneumaticsModuleType.REVPH, canalG);
        ledB = new Solenoid(idHub, PneumaticsModuleType.REVPH, canalB);
    }

    // Define cor manualmente
    public void setColor(boolean r, boolean g, boolean b) {
        ledR.set(r);
        ledG.set(g);
        ledB.set(b);
    }

    // Cor laranja fixa
    public void setLaranjaFixo() {
        ledR.set(true);
        ledG.set(false);
        ledB.set(true);
    }

    // Pisca o led
    public void piscarLaranja() {
        long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - ultimoTempoPiscar > 300) {
            piscarEstado = !piscarEstado;
            ultimoTempoPiscar = tempoAtual;
        }
        ledR.set(piscarEstado);
        ledG.set(piscarEstado);
        ledB.set(false);
    }
}
