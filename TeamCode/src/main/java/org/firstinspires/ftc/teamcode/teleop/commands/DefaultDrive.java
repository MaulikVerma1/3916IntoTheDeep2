package org.firstinspires.ftc.teamcode.teleop.commands;


import com.arcrobotics.ftclib.command.CommandBase;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;


import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;


import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class DefaultDrive extends CommandBase {
    private final DriveSubsystem drive;


    private final DoubleSupplier strafeSpeed;
    private final DoubleSupplier forwardSpeed;
    private final DoubleSupplier turnSpeed;


    private final BooleanSupplier leftBumper;
    private final BooleanSupplier rightBumper;


    private double prevStrafe = 0.0, prevForward = 0.0, prevTurn = 0.0;


    private boolean holdingHeading = false;
    private double headingSetpoint = 0.0;


    public DefaultDrive(DriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, BooleanSupplier left, BooleanSupplier right) {
        drive = subsystem;
        strafeSpeed = strafe;
        forwardSpeed = forward;
        turnSpeed = turn;
        leftBumper = left;
        rightBumper = right;
        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        prevStrafe = prevForward = prevTurn = 0.0;
        headingSetpoint = drive.getHeadingRadians();
        holdingHeading = false;
    }


    @Override
    public void execute() {
        double strafe = shape(applyDeadband(strafeSpeed.getAsDouble(), TeleOpConfig.DEADBAND));
        double forward = shape(applyDeadband(forwardSpeed.getAsDouble(), TeleOpConfig.DEADBAND));
        double turn = shape(applyDeadband(turnSpeed.getAsDouble(), TeleOpConfig.DEADBAND));


        if (leftBumper.getAsBoolean() || rightBumper.getAsBoolean()) {
            strafe *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
            forward *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
            turn *= TeleOpConfig.PRECISION_TURN_MULTIPLIER;
        }


        if (Math.abs(turn) < TeleOpConfig.HEADING_HOLD_TURN_THRESHOLD) {
            if (!holdingHeading) {
                headingSetpoint = drive.getHeadingRadians();
                holdingHeading = true;
            }
            double heading = drive.getHeadingRadians();
            double error = angleWrap(headingSetpoint - heading);
            double holdCorrection = TeleOpConfig.HEADING_HOLD_KP * error;
            turn += holdCorrection;
        } else {
            holdingHeading = false;
        }


        strafe = slew(strafe, prevStrafe, TeleOpConfig.SLEW_MAX_STRAFE_PER_LOOP);
        forward = slew(forward, prevForward, TeleOpConfig.SLEW_MAX_FORWARD_PER_LOOP);
        turn = slew(turn, prevTurn, TeleOpConfig.SLEW_MAX_TURN_PER_LOOP);


        prevStrafe = strafe;
        prevForward = forward;
        prevTurn = turn;


        drive.drive(strafe, forward, turn);


        // live telemetry
        if (TeleOpConfig.SEND_DASHBOARD_TELEMETRY) {
            TelemetryPacket p = new TelemetryPacket();
            p.put("strafe", strafe);
            p.put("forward", forward);
            p.put("turn", turn);
            p.put("holdingHeading", holdingHeading);
            p.put("heading(deg)", Math.toDegrees(drive.getHeadingRadians()));
            FtcDashboard.getInstance().sendTelemetryPacket(p);
        }
    }


    private static double applyDeadband(double x, double db) {
        if (Math.abs(x) <= db) return 0.0;
        double sign = Math.signum(x);
        double mag = (Math.abs(x) - db) / (1.0 - db);
        return sign * mag;
    }


    private static double shape(double x) {
        double a = TeleOpConfig.EXPO_MIX;
        double cubic = x * x * x;
        double mixed = (1 - a) * x + a * cubic;


        double e = TeleOpConfig.EXPO_POWER;
        double out = Math.copySign(Math.pow(Math.abs(mixed), e), mixed);


        return out * TeleOpConfig.GLOBAL_POWER_SCALAR;
    }


    private static double slew(double target, double prev, double maxStep) {
        double delta = target - prev;
        if (delta >  maxStep) return prev + maxStep;
        if (delta < -maxStep) return prev - maxStep;
        return target;
    }


    private static double angleWrap(double r) {
        while (r > Math.PI)  r -= 2.0 * Math.PI;
        while (r < -Math.PI) r += 2.0 * Math.PI;
        return r;
    }


}