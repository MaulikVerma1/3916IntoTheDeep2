package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;


@Config
public class TeleOpConfig {
    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed (for precision mode)


    public static double DEADBAND = 0.05;
    public static double EXPO_MIX = 0.5;
    public static double EXPO_POWER = 1.0;
    public static double GLOBAL_POWER_SCALAR = 1.0;




    public static double SLEW_MAX_STRAFE_PER_LOOP  = 0.08;
    public static double SLEW_MAX_FORWARD_PER_LOOP = 0.08;
    public static double SLEW_MAX_TURN_PER_LOOP    = 0.10;




    public static boolean HEADING_HOLD_ENABLED = true;
    public static double HEADING_HOLD_TURN_THRESHOLD = 0.05;
    public static double HEADING_HOLD_KP = 2.0;


    public static double DEFULT_POWER = 0.5; //Default motor power
    public static double WHEEL_RADIUS = 1.889764; //in


    // telemetry
    public static boolean SEND_DASHBOARD_TELEMETRY = true;


}