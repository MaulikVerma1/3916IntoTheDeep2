package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeArmSubsystem extends SubsystemBase {
    private final ServoEx leftLinkage;
    private final ServoEx rightLinkage;
    private final ServoEx leftIntakeFlip;
    private final ServoEx rightIntakeFlip;
    private final ServoEx clawPivot;
    private final ServoEx clawGrip;

    private static final double POSITION_TOLERANCE = 0.05;

    // Define servo positions
    private static final double LINKAGE_RETRACTED = 0.0;
    private static final double LINKAGE_EXTENDED = 1.0;

    private static final double INTAKE_UP = 0.0;
    private static final double INTAKE_DOWN = 1.0;

    private static final double CLAW_PIVOT_UP = 0.0;
    private static final double CLAW_PIVOT_DOWN = 0.8;

    private static final double CLAW_OPEN = 0.7;
    private static final double CLAW_CLOSED = 0.0;

    public IntakeArmSubsystem(HardwareMap hw) {
        leftLinkage = new SimpleServo(hw, "left_linkage", 0, 180);
        rightLinkage = new SimpleServo(hw, "right_linkage", 0, 180);
        leftIntakeFlip = new SimpleServo(hw, "left_intake_flip", 0, 180);
        rightIntakeFlip = new SimpleServo(hw, "right_intake_flip", 0, 180);
        clawPivot = new SimpleServo(hw, "claw_pivot", 0, 180);
        clawGrip = new SimpleServo(hw, "claw_grip", 0, 180);

        retractLinkage();
        moveIntakeUp();
        moveClawUp();
        openClaw();
    }

    // Linkage controls
    public void extendLinkage() {
        leftLinkage.setPosition(LINKAGE_EXTENDED);
        rightLinkage.setPosition(LINKAGE_EXTENDED);
    }

    public void retractLinkage() {
        leftLinkage.setPosition(LINKAGE_RETRACTED);
        rightLinkage.setPosition(LINKAGE_RETRACTED);
    }

    public boolean isLinkageExtended() {
        return Math.abs(leftLinkage.getPosition() - LINKAGE_EXTENDED) < POSITION_TOLERANCE &&
                Math.abs(rightLinkage.getPosition() - LINKAGE_EXTENDED) < POSITION_TOLERANCE;
    }

    public boolean isLinkageRetracted() {
        return Math.abs(leftLinkage.getPosition() - LINKAGE_RETRACTED) < POSITION_TOLERANCE &&
                Math.abs(rightLinkage.getPosition() - LINKAGE_RETRACTED) < POSITION_TOLERANCE;
    }

    // Intake flip controls
    public void moveIntakeDown() {
        leftIntakeFlip.setPosition(INTAKE_DOWN);
        rightIntakeFlip.setPosition(INTAKE_DOWN);
    }

    public void moveIntakeUp() {
        leftIntakeFlip.setPosition(INTAKE_UP);
        rightIntakeFlip.setPosition(INTAKE_UP);
    }

    public boolean isIntakeDown() {
        return Math.abs(leftIntakeFlip.getPosition() - INTAKE_DOWN) < POSITION_TOLERANCE &&
                Math.abs(rightIntakeFlip.getPosition() - INTAKE_DOWN) < POSITION_TOLERANCE;
    }

    public boolean isIntakeUp() {
        return Math.abs(leftIntakeFlip.getPosition() - INTAKE_UP) < POSITION_TOLERANCE &&
                Math.abs(rightIntakeFlip.getPosition() - INTAKE_UP) < POSITION_TOLERANCE;
    }

    // Claw controls
    public void moveClawDown() {
        clawPivot.setPosition(CLAW_PIVOT_DOWN);
    }

    public void moveClawUp() {
        clawPivot.setPosition(CLAW_PIVOT_UP);
    }

    public void closeClaw() {
        clawGrip.setPosition(CLAW_CLOSED);
    }

    public void openClaw() {
        clawGrip.setPosition(CLAW_OPEN);
    }

    public boolean isClawDown() {
        return Math.abs(clawPivot.getPosition() - CLAW_PIVOT_DOWN) < POSITION_TOLERANCE;
    }

    public boolean isClawClosed() {
        return Math.abs(clawGrip.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
    }
}