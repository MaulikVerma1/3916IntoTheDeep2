package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class IntakeArmSubsystem extends SubsystemBase {
    private final ServoEx leftLinkage;
    private final ServoEx rightLinkage;
    private final ServoEx leftIntakeFlip;
    private final ServoEx rightIntakeFlip;
    //private final ServoEx clawPivot;
    //private final ServoEx clawGrip;

    private static final double POSITION_TOLERANCE = 0.05;

    // Linkage positions are good
    private static final double LEFT_LINKAGE_RETRACTED = 0.8;
    private static final double LEFT_LINKAGE_EXTENDED = 0.0;

    private static final double RIGHT_LINKAGE_RETRACTED = 0.2;
    private static final double RIGHT_LINKAGE_EXTENDED = 1.0;

    // Swapped UP/DOWN positions to reverse direction
    private static final double LEFT_INTAKE_UP = 0.1;    // Was 0.1
    private static final double LEFT_INTAKE_DOWN = 0.7;  // Was 0.7

    private static final double RIGHT_INTAKE_UP = 0.9;   // Was 0.9
    private static final double RIGHT_INTAKE_DOWN = 0.3; // Was 0.3

    // Define claw positions
    private static final double CLAW_PIVOT_UP = 0.0;
    private static final double CLAW_PIVOT_DOWN = 0.5;

    private static final double CLAW_OPEN = 0.4;
    private static final double CLAW_CLOSED = 0.0;

    public IntakeArmSubsystem(HardwareMap hw) {
        leftLinkage = new SimpleServo(hw, "linkage.L", -180, 180, AngleUnit.DEGREES);
        rightLinkage = new SimpleServo(hw, "linkage.R", -180, 180, AngleUnit.DEGREES);
        leftIntakeFlip = new SimpleServo(hw, "flip.L", -180, 180, AngleUnit.DEGREES);
        rightIntakeFlip = new SimpleServo(hw, "flip.R", -180, 180, AngleUnit.DEGREES);
        // leftClawPivot = new SimpleServo(hw, "pivot.L", 0, 180, AngleUnit.DEGREES);
        //rightClawPivot = new SimpleServo(hw, "pivot.R", 0, 180, AngleUnit.DEGREES);
        //clawGrip = new SimpleServo(hw, "claw_grip", 0, 180, AngleUnit.DEGREES);

        // Initialize to starting position
        retractLinkage();
        //moveIntakeUp();
    }

    // Linkage controls
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

    // Claw controls
    /*
    public void moveClawDown() {
        leftClawPivot.setPosition(LEFT_CLAW_PIVOT_DOWN);
        rightClawPivot.setPosition(RIGHT_CLAW_PIVOT_DOWN);
    }

    public void moveClawUp() {
        leftClawPivot.setPosition(LEFT_CLAW_PIVOT_UP);
        rightClawPivot.setPosition(RIGHT_CLAW_PIVOT_UP);
    }

    public void closeClaw() {
        clawGrip.setPosition(CLAW_CLOSED);
    }

    public void openClaw() {
        clawGrip.setPosition(CLAW_OPEN);
    }

    public boolean isClawDown() {
        return Math.abs(leftClawPivot.getPosition() - LEFT_CLAW_PIVOT_DOWN) < POSITION_TOLERANCE &&
                Math.abs(rightClawPivot.getPosition() - RIGHT_CLAW_PIVOT_DOWN) < POSITION_TOLERANCE;
    }

    public boolean isClawClosed() {
        return Math.abs(clawGrip.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
    } */
}