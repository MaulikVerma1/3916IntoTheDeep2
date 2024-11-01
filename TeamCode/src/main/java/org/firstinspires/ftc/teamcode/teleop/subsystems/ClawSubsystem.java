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
        final double adjustmentStep = 0.01;
        final double tolerance = 0.01;
        final int MAX_ITERATIONS = 500; // Prevent infinite loop

        Thread servo_thread = new Thread(() -> {
            int iterations = 0;
            while (Math.abs(servo.getPosition() - targetPosition) > tolerance &&
                    iterations < MAX_ITERATIONS) {
                double currentPosition = servo.getPosition();

                if (currentPosition < targetPosition) {
                    servo.setPosition(Math.min(currentPosition + adjustmentStep, targetPosition));
                } else {
                    servo.setPosition(Math.max(currentPosition - adjustmentStep, targetPosition));
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                iterations++;
            }

            servo.setPosition(targetPosition);
        });

        servo_thread.start();
    }
}
