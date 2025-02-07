package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeArmSubsystem extends SubsystemBase {
    private final ServoEx leftLinkage;
    private final ServoEx rightLinkage;
    private final ServoEx leftIntakeFlip;
    private final ServoEx rightIntakeFlip;
    private final ServoEx leftClawPivot;
    private final ServoEx rightClawPivot;
    private final ServoEx clawGrip;
    private final Telemetry telemetry;

    // Increased tolerance for more reliable state detection
    private static final double POSITION_TOLERANCE = 0.05;

    // Linkage positions (adjusted to release tension)
    private static final double LEFT_LINKAGE_EXTENDED = 0.05;  // Full extend
    private static final double RIGHT_LINKAGE_EXTENDED = 0.95; // Full extend
    private static final double LEFT_LINKAGE_RETRACTED = 0.95;  // Not quite full retract to reduce tension
    private static final double RIGHT_LINKAGE_RETRACTED = 0.05; // Not quite full retract to reduce tension

    // Intake positions (adjusted for better clearance)
    private static final double LEFT_INTAKE_UP = .8;   // Slightly less than full up
    private static final double RIGHT_INTAKE_UP = 0.2;  // Slightly less than full up
    private static final double LEFT_INTAKE_DOWN = 0.0; // More clearance in down position
    private static final double RIGHT_INTAKE_DOWN = 1.0; // More clearance in down position

    // Claw pivot positions (adjusted for better grip)
    private static final double LEFT_CLAW_UP = 0.8;
    private static final double RIGHT_CLAW_UP = 0.2;
    private static final double LEFT_CLAW_DOWN = 0.3;
    private static final double RIGHT_CLAW_DOWN = 0.7;

    // Claw grip positions
    private static final double CLAW_OPEN = 0.6;  // Wider opening
    private static final double CLAW_CLOSED = 0.2; // Gentler grip

    public IntakeArmSubsystem(HardwareMap hw, Telemetry telemetry) {
        this.telemetry = telemetry;

        // Initialize servos with proper angle ranges
        leftLinkage = new SimpleServo(hw, "linkage.L", -180, 180);
        rightLinkage = new SimpleServo(hw, "linkage.R", -180, 180);
        leftIntakeFlip = new SimpleServo(hw, "flip.L", -180, 180);
        rightIntakeFlip = new SimpleServo(hw, "flip.R", -180, 180);
        leftClawPivot = new SimpleServo(hw, "pivot.L", 0, 180);
        rightClawPivot = new SimpleServo(hw, "pivot.R", 0, 180);
        clawGrip = new SimpleServo(hw, "claw_grip", 0, 180);

        // Set inversions
        leftIntakeFlip.setInverted(true);
        rightIntakeFlip.setInverted(true);

        // Set initial positions
        retractLinkage();
        moveIntakeUp();
        //clawGrip.setPosition(CLAW_OPEN);
        //leftClawPivot.setPosition(LEFT_CLAW_UP);
        //rightClawPivot.setPosition(RIGHT_CLAW_UP);
    }

    @Override
    public void periodic() {
        // Detailed telemetry for mechanism status
        telemetry.addLine("=== Mechanism Status ===");

        // Linkage status
        String linkageStatus = isLinkageExtended() ? "EXTENDED" :
                (isLinkageRetracted() ? "RETRACTED" : "MOVING");
        telemetry.addData("Linkage", "%s", linkageStatus);
        telemetry.addData("- Left", "%.2f", leftLinkage.getPosition());
        telemetry.addData("- Right", "%.2f", rightLinkage.getPosition());

        // Intake status
        String intakeStatus = isIntakeDown() ? "DOWN" :
                (isIntakeUp() ? "UP" : "MOVING");
        telemetry.addData("Intake", "%s", intakeStatus);
        telemetry.addData("- Left", "%.2f", leftIntakeFlip.getPosition());
        telemetry.addData("- Right", "%.2f", rightIntakeFlip.getPosition());

        // Claw status
        String clawHeightStatus = isClawUp() ? "UP" :
                (isClawDown() ? "DOWN" : "MOVING");
        String clawGripStatus = isClawClosed() ? "CLOSED" : "OPEN";
        telemetry.addData("Claw Height", "%s", clawHeightStatus);
        telemetry.addData("- Left", "%.2f", leftClawPivot.getPosition());
        telemetry.addData("- Right", "%.2f", rightClawPivot.getPosition());
        telemetry.addData("Claw Grip", "%s (%.2f)", clawGripStatus, clawGrip.getPosition());
    }

    public void extendLinkage() {
        leftLinkage.setPosition(LEFT_LINKAGE_EXTENDED);
        rightLinkage.setPosition(RIGHT_LINKAGE_EXTENDED);
    }

    public void retractLinkage() {
        leftLinkage.setPosition(LEFT_LINKAGE_RETRACTED);
        rightLinkage.setPosition(RIGHT_LINKAGE_RETRACTED);
    }

    public boolean isLinkageExtended() {
        //return Math.abs(leftLinkage.getPosition() - LEFT_LINKAGE_EXTENDED) < POSITION_TOLERANCE &&
                //Math.abs(rightLinkage.getPosition() - RIGHT_LINKAGE_EXTENDED) < POSITION_TOLERANCE;
        return true;
    }

    public boolean isLinkageRetracted() {
        //return Math.abs(leftLinkage.getPosition() - LEFT_LINKAGE_RETRACTED) < POSITION_TOLERANCE &&
          //      Math.abs(rightLinkage.getPosition() - RIGHT_LINKAGE_RETRACTED) < POSITION_TOLERANCE;
        return true;
    }

    public void moveIntakeDown() {
        leftIntakeFlip.setPosition(LEFT_INTAKE_DOWN);
        rightIntakeFlip.setPosition(RIGHT_INTAKE_DOWN);
    }

    public void moveIntakeUp() {
        leftIntakeFlip.setPosition(LEFT_INTAKE_UP);
        rightIntakeFlip.setPosition(RIGHT_INTAKE_UP);
    }

    public boolean isIntakeDown() {
        //return Math.abs(leftIntakeFlip.getPosition() - LEFT_INTAKE_DOWN) < POSITION_TOLERANCE &&
          //      Math.abs(rightIntakeFlip.getPosition() - RIGHT_INTAKE_DOWN) < POSITION_TOLERANCE;
        return true;
    }

    public boolean isIntakeUp() {
        //return Math.abs(leftIntakeFlip.getPosition() - LEFT_INTAKE_UP) < POSITION_TOLERANCE &&
           //     Math.abs(rightIntakeFlip.getPosition() - RIGHT_INTAKE_UP) < POSITION_TOLERANCE;
        return true;
    }

    public void moveClawUp() {
        leftClawPivot.setPosition(LEFT_CLAW_UP);
        rightClawPivot.setPosition(RIGHT_CLAW_UP);
    }

    public void moveClawDown() {
        leftClawPivot.setPosition(LEFT_CLAW_DOWN);
        rightClawPivot.setPosition(RIGHT_CLAW_DOWN);
    }

    public void openClaw() {
        clawGrip.setPosition(CLAW_OPEN);
    }

    public void closeClaw() {
        clawGrip.setPosition(CLAW_CLOSED);
    }

    public boolean isClawUp() {
        return Math.abs(leftClawPivot.getPosition() - LEFT_CLAW_UP) < POSITION_TOLERANCE &&
                Math.abs(rightClawPivot.getPosition() - RIGHT_CLAW_UP) < POSITION_TOLERANCE;
    }

    public boolean isClawDown() {
        return Math.abs(leftClawPivot.getPosition() - LEFT_CLAW_DOWN) < POSITION_TOLERANCE &&
                Math.abs(rightClawPivot.getPosition() - RIGHT_CLAW_DOWN) < POSITION_TOLERANCE;
    }

    public boolean isClawClosed() {
        return Math.abs(clawGrip.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
    }
    public void adjustClawGrip(double delta) {
        double currentPos = clawGrip.getPosition();
        double newPos = clamp(currentPos + (delta * 0.02)); // Small increments for fine control
        // Ensure we stay within the defined open/closed range
        newPos = Math.min(CLAW_OPEN, Math.max(CLAW_CLOSED, newPos));
        clawGrip.setPosition(newPos);
    }

    public void adjustClawHeight(double delta) {
        double leftCurrentPos = leftClawPivot.getPosition();
        double rightCurrentPos = rightClawPivot.getPosition();

        // Calculate new positions with small increments for fine control
        double leftNewPos = clamp(leftCurrentPos + (delta * 0.02));
        double rightNewPos = clamp(rightCurrentPos - (delta * 0.02)); // Inverted for right servo

        // Ensure we stay within the defined up/down range
        leftNewPos = Math.min(LEFT_CLAW_UP, Math.max(LEFT_CLAW_DOWN, leftNewPos));
        rightNewPos = Math.min(RIGHT_CLAW_UP, Math.max(RIGHT_CLAW_DOWN, rightNewPos));

        leftClawPivot.setPosition(leftNewPos);
        rightClawPivot.setPosition(rightNewPos);
    }


    private double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }

    // Getter methods for servo positions

}