package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.teleop.controller.PIDClawController;

public class IntakeArmSubsystem extends SubsystemBase {
    private final ServoEx leftLinkage;
    private final ServoEx rightLinkage;
    private final ServoEx leftIntakeFlip;
    private final ServoEx rightIntakeFlip;
    private final ServoEx leftClawPivot;
    private final ServoEx rightClawPivot;
    private final ServoEx clawGrip;
    private final Telemetry telemetry;
    private final PIDClawController clawController;

    // Increased tolerance for more reliable state detection
    private static final double POSITION_TOLERANCE = 0.05;

    // Linkage positions (adjusted to release tension)
    private static final double LEFT_LINKAGE_EXTENDED = 0.05;  // Full extend
    private static final double RIGHT_LINKAGE_EXTENDED = 0.95; // Full extend
    private static final double LEFT_LINKAGE_RETRACTED = 0.95;  // Not quite full retract to reduce tension
    private static final double RIGHT_LINKAGE_RETRACTED = 0.05; // Not quite full retract to reduce tension

    // Intake positions (adjusted for better clearance)
    private static final double LEFT_INTAKE_UP = 0.05;   // Slightly less than full up
    private static final double RIGHT_INTAKE_UP = 0.95;  // Slightly less than full up
    private static final double LEFT_INTAKE_DOWN = 0.9; // More clearance in down position
    private static final double RIGHT_INTAKE_DOWN = 0.1; // More clearance in down position

    // Claw pivot positions (adjusted for better grip)
    private static final double LEFT_CLAW_UP = 0.9;
    private static final double RIGHT_CLAW_UP = 0.1;
    private static final double LEFT_CLAW_DOWN = 0.3;
    private static final double RIGHT_CLAW_DOWN = 0.7;

    // Claw grip positions
    private static final double CLAW_OPEN = 0.6;  // Wider opening
    private static final double CLAW_CLOSED = 0.2; // Gentler grip

    public IntakeArmSubsystem(HardwareMap hw, Telemetry telemetry) {
        this.telemetry = telemetry;

        telemetry.addData("Status", "Initializing servos...");
        telemetry.update();

        // Initialize servos with proper angle ranges
        leftLinkage = new SimpleServo(hw, "linkage.L", -180, 180);
        rightLinkage = new SimpleServo(hw, "linkage.R", -180, 180);
        leftIntakeFlip = new SimpleServo(hw, "flip.L", -180, 180);
        rightIntakeFlip = new SimpleServo(hw, "flip.R", -180, 180);
        leftClawPivot = new SimpleServo(hw, "pivot.L", 0, 180);
        rightClawPivot = new SimpleServo(hw, "pivot.R", 0, 180);
        clawGrip = new SimpleServo(hw, "claw_grip", 0, 180);

        // Initialize PID controller for claw
        clawController = new PIDClawController(leftClawPivot, rightClawPivot);

        // Set inversions
        leftIntakeFlip.setInverted(true);
        rightIntakeFlip.setInverted(true);

        // Set initial positions - start in retracted position
        leftLinkage.setPosition(LEFT_LINKAGE_RETRACTED);
        rightLinkage.setPosition(RIGHT_LINKAGE_RETRACTED);
        leftIntakeFlip.setPosition(LEFT_INTAKE_UP);
        rightIntakeFlip.setPosition(RIGHT_INTAKE_UP);
        clawGrip.setPosition(CLAW_OPEN);
        clawController.setTargetPosition(LEFT_CLAW_UP);
    }

    @Override
    public void periodic() {
        // Update PID controller
        clawController.update();

        // Telemetry for linkage
        telemetry.addLine("=== Mechanism Status ===");
        telemetry.addData("Linkage", "%s (L:%.2f, R:%.2f)",
                isLinkageExtended() ? "EXTENDED" : (isLinkageRetracted() ? "RETRACTED" : "MOVING"),
                leftLinkage.getPosition(),
                rightLinkage.getPosition());
        telemetry.addData("Intake", "%s (L:%.2f, R:%.2f)",
                isIntakeDown() ? "DOWN" : (isIntakeUp() ? "UP" : "MOVING"),
                leftIntakeFlip.getPosition(),
                rightIntakeFlip.getPosition());
        telemetry.addData("Claw", "%s / %s (Target: %.2f)",
                isClawUp() ? "UP" : "DOWN",
                isClawClosed() ? "CLOSED" : "OPEN",
                clawController.getTargetPosition());
    }

    public boolean isLinkageMoving() {
        return Math.abs(leftLinkage.getPosition() - LEFT_LINKAGE_EXTENDED) > POSITION_TOLERANCE ||
                Math.abs(rightLinkage.getPosition() - RIGHT_LINKAGE_EXTENDED) > POSITION_TOLERANCE;
    }

    // Linkage controls with better error handling
    public void extendLinkage() {
        leftLinkage.setPosition(LEFT_LINKAGE_EXTENDED);
        rightLinkage.setPosition(RIGHT_LINKAGE_EXTENDED);
    }

    public void retractLinkage() {
        leftLinkage.setPosition(LEFT_LINKAGE_RETRACTED);
        rightLinkage.setPosition(RIGHT_LINKAGE_RETRACTED);
    }

    public boolean isLinkageExtended() {
        return Math.abs(leftLinkage.getPosition() - LEFT_LINKAGE_EXTENDED) < POSITION_TOLERANCE &&
                Math.abs(rightLinkage.getPosition() - RIGHT_LINKAGE_EXTENDED) < POSITION_TOLERANCE;
    }

    public boolean isLinkageRetracted() {
        return Math.abs(leftLinkage.getPosition() - LEFT_LINKAGE_RETRACTED) < POSITION_TOLERANCE &&
                Math.abs(rightLinkage.getPosition() - RIGHT_LINKAGE_RETRACTED) < POSITION_TOLERANCE;
    }

    // Intake flip controls
    public void moveIntakeDown() {
        leftIntakeFlip.setPosition(LEFT_INTAKE_DOWN);
        rightIntakeFlip.setPosition(RIGHT_INTAKE_DOWN);
    }

    public void moveIntakeUp() {
        leftIntakeFlip.setPosition(LEFT_INTAKE_UP);
        rightIntakeFlip.setPosition(RIGHT_INTAKE_UP);
    }

    public boolean isIntakeDown() {
        return Math.abs(leftIntakeFlip.getPosition() - LEFT_INTAKE_DOWN) < POSITION_TOLERANCE &&
                Math.abs(rightIntakeFlip.getPosition() - RIGHT_INTAKE_DOWN) < POSITION_TOLERANCE;
    }

    public boolean isIntakeUp() {
        return Math.abs(leftIntakeFlip.getPosition() - LEFT_INTAKE_UP) < POSITION_TOLERANCE &&
                Math.abs(rightIntakeFlip.getPosition() - RIGHT_INTAKE_UP) < POSITION_TOLERANCE;
    }

    // Claw controls with position feedback
    public void openClaw() {
        clawGrip.setPosition(CLAW_OPEN);
    }

    public void closeClaw() {
        clawGrip.setPosition(CLAW_CLOSED);
    }

    public void moveClawUp() {
        clawController.setTargetPosition(LEFT_CLAW_UP);
    }

    public void moveClawDown() {
        clawController.setTargetPosition(LEFT_CLAW_DOWN);
    }

    public boolean isClawUp() {
        return Math.abs(clawController.getTargetPosition() - LEFT_CLAW_UP) < POSITION_TOLERANCE;
    }

    public boolean isClawDown() {
        return Math.abs(clawController.getTargetPosition() - LEFT_CLAW_DOWN) < POSITION_TOLERANCE;
    }

    public boolean isClawClosed() {
        return Math.abs(clawGrip.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
    }

    // Manual claw adjustment with PID
    public void adjustClawPosition(double delta) {
        clawController.adjustTargetPosition(delta * 0.05); // Scale delta for finer control
    }

    // Claw incremental control
    public void adjustClawGrip(double delta) {
        double currentPos = clawGrip.getPosition();
        double newPos = clamp(currentPos + (delta * 0.02));
        // Ensure we stay within the defined open/closed range
        newPos = Math.min(CLAW_OPEN, Math.max(CLAW_CLOSED, newPos));
        clawGrip.setPosition(newPos);
        telemetry.addData("Claw", "Position: %.2f", newPos);
        telemetry.update();
    }

    public void manualAdjustLinkage(double delta) {
        // Increased movement speed for manual adjustments
        delta = delta * 0.1; // 10% adjustment per step for more responsive control
        double newLeft = clamp(leftLinkage.getPosition() - delta); // Reversed direction
        double newRight = clamp(rightLinkage.getPosition() + delta); // Reversed direction
        leftLinkage.setPosition(newLeft);
        rightLinkage.setPosition(newRight);
    }

    public void manualAdjustIntake(double delta) {
        // Simple direct control - move both servos in same direction
        delta = delta * 0.1; // 10% adjustment per step
        double newLeft = clamp(leftIntakeFlip.getPosition() - delta);
        double newRight = clamp(rightIntakeFlip.getPosition() + delta);
        leftIntakeFlip.setPosition(newLeft);
        rightIntakeFlip.setPosition(newRight);
    }

    // Simplified intake position adjustment - removed complex delta logic
    public void adjustIntakePositions(boolean up, boolean down, boolean left, boolean right) {
        if (up) {
            moveIntakeUp();
        }
        if (down) {
            moveIntakeDown();
        }
    }

    public void setClawPosition(double position) {
        // Clamp position between open and closed values
        double clampedPosition = Math.min(CLAW_OPEN, Math.max(CLAW_CLOSED, position));
        clawGrip.setPosition(clampedPosition);
        telemetry.addData("Claw", "Position: %.2f", clampedPosition);
        telemetry.update();
    }

    private double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }

    // Add helper method for complete retraction sequence
    public void completeRetraction() {
        moveIntakeUp();
        retractLinkage();
        moveClawDown();
        closeClaw();
    }
}