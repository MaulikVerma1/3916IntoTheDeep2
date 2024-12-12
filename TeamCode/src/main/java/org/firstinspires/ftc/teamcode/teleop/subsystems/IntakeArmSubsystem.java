package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeArmSubsystem extends SubsystemBase {
    private final ServoEx intakeLinkage;
    private final ServoEx claw;
    private final ServoEx arm;

    private static final double POSITION_TOLERANCE = 0.05;

    // Define servo positions
    private static final double LINKAGE_RETRACTED = 0.0;
    private static final double LINKAGE_EXTENDED = 1.0;

    private static final double CLAW_OPEN = 0.7;
    private static final double CLAW_CLOSED = 0.0;

    private static final double ARM_PICKUP = 0.0;
    private static final double ARM_DROP = 0.8;

    public IntakeArmSubsystem(HardwareMap hw) {
        intakeLinkage = new SimpleServo(hw, "intake_linkage", 0, 180);
        claw = new SimpleServo(hw, "claw", 0, 180);
        arm = new SimpleServo(hw, "arm", 0, 270);

        retractIntake();
        openClaw();
        setArmToPickup();
    }

    // Intake linkage controls
    public void extendIntake() {
        intakeLinkage.setPosition(LINKAGE_EXTENDED);
    }

    public void retractIntake() {
        intakeLinkage.setPosition(LINKAGE_RETRACTED);
    }

    public boolean isIntakeExtended() {
        return Math.abs(intakeLinkage.getPosition() - LINKAGE_EXTENDED) < POSITION_TOLERANCE;
    }

    public boolean isIntakeRetracted() {
        return Math.abs(intakeLinkage.getPosition() - LINKAGE_RETRACTED) < POSITION_TOLERANCE;
    }

    // Claw controls
    public void closeClaw() {
        claw.setPosition(CLAW_CLOSED);
    }

    public void openClaw() {
        claw.setPosition(CLAW_OPEN);
    }

    public boolean isClawClosed() {
        return Math.abs(claw.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
    }

    // Arm controls
    public void setArmToPickup() {
        arm.setPosition(ARM_PICKUP);
    }

    public void setArmToDrop() {
        arm.setPosition(ARM_DROP);
    }

    public boolean isArmAtDrop() {
        return Math.abs(arm.getPosition() - ARM_DROP) < POSITION_TOLERANCE;
    }
}