package org.firstinspires.ftc.teamcode.teleop.controller;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDClawController {
    private final PIDController controller;
    private final ServoEx leftServo;
    private final ServoEx rightServo;
    private final ElapsedTime timer;
    private double targetPosition;
    private double lastError;
    private double lastTime;

    // PID Constants - these can be tuned
    private static final double kP = 0.1;
    private static final double kI = 0.01;
    private static final double kD = 0.05;

    // Limits and thresholds
    private static final double MAX_POSITION = 1.0;
    private static final double MIN_POSITION = 0.0;
    private static final double POSITION_TOLERANCE = 0.02;

    public PIDClawController(ServoEx leftServo, ServoEx rightServo) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;
        this.controller = new PIDController(kP, kI, kD);
        this.timer = new ElapsedTime();
        this.targetPosition = leftServo.getPosition(); // Initialize to current position

        // Configure PID controller
        controller.setTolerance(POSITION_TOLERANCE);
        controller.setSetPoint(targetPosition);

        timer.reset();
        lastTime = timer.seconds();
        lastError = 0;
    }

    public void setTargetPosition(double position) {
        targetPosition = clamp(position, MIN_POSITION, MAX_POSITION);
        controller.setSetPoint(targetPosition);
    }

    public void adjustTargetPosition(double delta) {
        setTargetPosition(targetPosition + delta);
    }

    public void update() {
        double currentTime = timer.seconds();
        double dt = currentTime - lastTime;

        // Get current position (average of both servos)
        double currentPosition = (leftServo.getPosition() + rightServo.getPosition()) / 2.0;

        // Calculate PID output
        double output = controller.calculate(currentPosition);

        // Apply output to servos (note: right servo is inverted)
        double newLeftPos = clamp(currentPosition + output, MIN_POSITION, MAX_POSITION);
        double newRightPos = clamp(currentPosition - output, MIN_POSITION, MAX_POSITION);

        leftServo.setPosition(newLeftPos);
        rightServo.setPosition(newRightPos);

        lastTime = currentTime;
    }

    public boolean atTarget() {
        return Math.abs(leftServo.getPosition() - targetPosition) < POSITION_TOLERANCE;
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
