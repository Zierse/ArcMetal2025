package frc.robot;

public class Constants {
    
    public static final class TracaoDriveContants{

        public static final int MOTOR_SUPERIOR_DIREITO = 1;
        public static final int MOTOR_INFERIOR_DIREITO = 2;
        public static final int MOTOR_INFERIOR_ESQUERDO = 3;
        public static final int MOTOR_SUPERIOR_ESQUERDO = 4;

        public static final double SLOW_MODE_GIRO = 0.6;
        public static final double SLOW_MODE_VELOCIDADE = 0.5;
    }

    public static final class IO_Constants {

        public static final int JOYSTICK_PORT_DRIVE = 0;
        public static final int JOYSTICK_PORT_OPERATOR = 1;

        public static final int AXIS_GATILHO_ESQUERDO = 3;
        public static final int AXIS_GATILHO_DIREITO = 4;
        public static final int AXIS_ANALOG_ESQUERDO = 0;
    }

    public static final class Rollo_Constants {

        public static final int ROLLER_MOTOR_SNOW = 6;

        public static final int ROLLER_MOTOR_CURRENT_LIMIT = 60;
        public static final double ROLLER_MOTOR_VOLTAGE_COMP = 10;

        public static final double ROLLER_CORAL_OUT = -.4;
        public static final double ROLLER_ALGAE_IN = -0.8;

        public static final double ROLLER_ALGAE_OUT = 0.4;
        public static final double ROLLER_CORAL_STACK = -1;


    }
    public static final class Alga_Constants {

        public static final double ROLLER_ALGAE_IN = -0.1;
        public static final double ROLLER_ALGAE_OUT = 0.4;
    }

    public static final class ArmConstants {
        public static final int ARM_MOTOR_ID = 8;
        public static final int ARM_MOTOR_CURRENT_LIMIT = 60;
        public static final double ARM_MOTOR_VOLTAGE_COMP = 10;
        public static final double ARM_SPEED_DOWN = 0.4;
        public static final double ARM_SPEED_UP = -0.4;
        public static final double ARM_HOLD_DOWN = 0.1;
        public static final double ARM_HOLD_UP = -0.15;
    }

    public static final class ElevadorConstants {
        public static final int ELEVADOR_MOTOR_ID = 5;

    }

    
}
