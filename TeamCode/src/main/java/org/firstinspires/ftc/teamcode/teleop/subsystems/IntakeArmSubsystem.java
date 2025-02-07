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
    //private final Telemetry telemetry;

    // Increased tolerance for more reliable state detection
    private static final double POSITION_TOLERANCE = 0.05;

    // Linkage positions (adjusted to release tension)
    private static final double LEFT_LINKAGE_EXTENDED = 0.0;  // Full extend
    private static final double RIGHT_LINKAGE_EXTENDED = 1.0; // Full extend
    private static final double LEFT_LINKAGE_RETRACTED = 0.95;  // Not quite full retract to reduce tension
    private static final double RIGHT_LINKAGE_RETRACTED = 0.05; // Not quite full retract to reduce tension

    // Intake positions (adjusted for better clearance)
    private static final double LEFT_INTAKE_UP = 0.8;   // Slightly less than full up
    private static final double RIGHT_INTAKE_UP = 0.2;  // Slightly less than full up
    private static final double LEFT_INTAKE_DOWN = 0.3; // More clearance in down position
    private static final double RIGHT_INTAKE_DOWN = 0.7; // More clearance in down position

    // Claw pivot positions (adjusted for better grip)
    private static final double LEFT_CLAW_UP = 0.2;
    private static final double RIGHT_CLAW_UP = 0.8;
    private static final double LEFT_CLAW_DOWN = 0.6;
    private static final double RIGHT_CLAW_DOWN = 0.4;

    // Claw grip positions
    private static final double CLAW_OPEN = 0.6;  // Wider opening
    private static final double CLAW_CLOSED = 0.2; // Gentler grip

    public IntakeArmSubsystem(HardwareMap hw) {


        // Initialize servos with proper angle ranges
        leftLinkage = new SimpleServo(hw, "linkage.L", -180, 180);
        rightLinkage = new SimpleServo(hw, "linkage.R", -180, 180);
        leftIntakeFlip = new SimpleServo(hw, "flip.L", -180, 180);
        rightIntakeFlip = new SimpleServo(hw, "flip.R", -180, 180);
        leftClawPivot = new SimpleServo(hw, "pivot.L", -180, 180);
        rightClawPivot = new SimpleServo(hw, "pivot.R", -180, 180);
        clawGrip = new SimpleServo(hw, "claw_grip", -180, 180);

        // Set inversions
        leftIntakeFlip.setInverted(true);
        rightIntakeFlip.setInverted(true);
        //leftClawPivot.setInverted(true);
        //rightClawPivot.setInverted(true);

        // Set initial positions
        //retractLinkage();
        //moveIntakeUp();
        //clawGrip.setPosition(CLAW_OPEN);
        leftClawPivot.turnToAngle(90);
        rightClawPivot.turnToAngle(90);
        //moveClawUp();
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
        //return Math.abs(leftClawPivot.getPosition() - LEFT_CLAW_UP) < POSITION_TOLERANCE &&
          //      Math.abs(rightClawPivot.getPosition() - RIGHT_CLAW_UP) < POSITION_TOLERANCE;
        return true;
    }

    public boolean isClawDown() {
        //return Math.abs(leftClawPivot.getPosition() - LEFT_CLAW_DOWN) < POSITION_TOLERANCE &&
             //   Math.abs(rightClawPivot.getPosition() - RIGHT_CLAW_DOWN) < POSITION_TOLERANCE;
        return true;
    }

    public boolean isClawClosed() {
        //return Math.abs(clawGrip.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
        return true;
    }




    // Getter methods for servo positions

}