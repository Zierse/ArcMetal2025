package frc.robot;

public class Constants {
    
    public static final class TracaoDriveContants{

        public static final int MOTOR_SUPERIOR_DIREITO = 1;
        public static final int MOTOR_INFERIOR_DIREITO = 2;
        public static final int MOTOR_INFERIOR_ESQUERDO = 3;
        public static final int MOTOR_SUPERIOR_ESQUERDO = 4;

        public static final int gyroPigeon = 21;


        public static final double SLOW_MODE_GIRO = 0.1;
        public static final double SLOW_MODE_VELOCIDADE = 0.5;
        
    }

    public static final class IO_Constants {

        public static final int JOYSTICK_PORT_DRIVE = 1;
        public static final int JOYSTICK_PORT_OPERATOR = 0;

        public static final int AXIS_GATILHO_ESQUERDO = 2;
        public static final int AXIS_GATILHO_DIREITO = 3;
        public static final int AXIS_ANALOG_ESQUERDO = 0;
    }

    public static final class Rollo_Constants {

        public static final int ROLLER_MOTOR_RIGHT = 11;
        public static final int ROLLER_MOTOR_LEFT = 12;

        public static final int ROLLER_MOTOR_CURRENT_LIMIT = 60;
        public static final double ROLLER_MOTOR_VOLTAGE_COMP = 10;

    }

    public static final class ElevadorConstants {
        public static final int ELEVADOR_MOTOR_ID = 10;
    }
}
