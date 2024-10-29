package org.firstinspires.ftc.teamcode.teleop.subsystems;

import static java.lang.Thread.sleep;

import java.lang.Thread;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.TeleOpConfig;

public class ClawSubsystem extends SubsystemBase {
    private SimpleServo servo;
    //private boolean isOpen = false;

    public ClawSubsystem(HardwareMap hw, String servoName, double minDegree, double maxDegree) {
        servo = new SimpleServo(hw, servoName, minDegree, maxDegree);
    }

    public void close() {

        servo.setPosition(TeleOpConfig.CLAW_CLOSE_DEGREES);
    }

    public void open() {

        servo.setPosition(TeleOpConfig.CLAW_OPEN_DEGREES);
    }

    public double getPos() {
        return servo.getPosition();
    }

    public void safeclose(double target) {
        final double targetPosition = target;
        final double adjustmentStep = 0.01; // Amount to adjust the position
        final double tolerance = 0.01; // Acceptable range from target position

        new Thread(() -> {
            // Loop until the claw is close enough to the desired position
            while (Math.abs(servo.getPosition() - targetPosition) > tolerance) {
                // Check the current position of the claw
                double currentPosition = servo.getPosition();

                // Calculate the direction and apply less force
                if (currentPosition < targetPosition) {
                    // Move towards the target position slowly
                    servo.setPosition(Math.min(currentPosition + adjustmentStep, targetPosition));
                } else {
                    // Move towards the target position slowly
                    servo.setPosition(Math.max(currentPosition - adjustmentStep, targetPosition));
                }

                // Sleep for a short period to avoid overwhelming the servo
                try {
                    sleep(50); // Adjust the sleep time as necessary
                } catch (InterruptedException e) {
                    // Handle the interruption
                    Thread.currentThread().interrupt();
                }
            }

            // Final adjustment to ensure it reaches the target position
            servo.setPosition(targetPosition);
        }).start();
    }
}
