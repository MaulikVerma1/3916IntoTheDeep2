package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeArmSubsystem extends SubsystemBase {
    private final ServoEx leftLinkage;
    private final ServoEx rightLinkage;
    private final ServoEx leftIntakeFlip;
    private final ServoEx rightIntakeFlip;
    private final ServoEx clawRotate;
    private final ServoEx leftClawPivot;
    private final ServoEx rightClawPivot;
    private final ServoEx clawGrip;
    private final DcMotor leftSlide;
    private final DcMotor rightSlide;
    private final DcMotor intakeMotor;
    //private final Telemetry telemetry;

    // Increased tolerance for more reliable state detection
    private static final double POSITION_TOLERANCE = 0.05;

    // Linkage positions (adjusted to release tension)
    private static final double LEFT_LINKAGE_EXTENDED = 0.0;  // Full extend
    private static final double RIGHT_LINKAGE_EXTENDED = 1.0; // Full extend
    private static final double LEFT_LINKAGE_RETRACTED = 0.95;  // Not quite full retract to reduce tension
    private static final double RIGHT_LINKAGE_RETRACTED = 0.05; // Not quite full retract to reduce tension

    // Intake positions (adjusted for better clearance)
    private static final double LEFT_INTAKE_UP = 0.9;   // Slightly less than full up
    private static final double RIGHT_INTAKE_UP = 0.1;  // Slightly less than full up
    private static final double LEFT_INTAKE_DOWN = 0.3; // More clearance in down position
    private static final double RIGHT_INTAKE_DOWN = 0.7; // More clearance in down position

    // Claw pivot positions (adjusted for better grip)
    private static final double LEFT_CLAW_UP = 0.8;
    private static final double RIGHT_CLAW_UP = 0.2;
    private static final double LEFT_CLAW_DOWN = 0.4;
    private static final double RIGHT_CLAW_DOWN = 0.6;

    // Claw grip positions
    private static final double CLAW_OPEN = 0.6;  // Wider opening
    private static final double CLAW_CLOSED = 0.4; // Gentler grip

    private static final double CLAW_FACE_FRONT = 0.3;
    private static final double CLAW_FACE_BACK = .5;
    // Slide constants
    private static final double SLIDE_POWER = 1.0;
    private static final int SLIDE_MAX_POSITION = 1000;

    public IntakeArmSubsystem(HardwareMap hw) {


        // Initialize servos with proper angle ranges
        leftLinkage = new SimpleServo(hw, "linkage.L", -180, 180);
        rightLinkage = new SimpleServo(hw, "linkage.R", -180, 180);
        leftIntakeFlip = new SimpleServo(hw, "flip.L", -180, 180);
        rightIntakeFlip = new SimpleServo(hw, "flip.R", -180, 180);
        leftClawPivot = new SimpleServo(hw, "pivot.L", -180, 180);
        rightClawPivot = new SimpleServo(hw, "pivot.R", -180, 180);
        clawGrip = new SimpleServo(hw, "claw_grip", -180, 180);

        leftSlide = hw.get(DcMotor.class, "slide.L");
        rightSlide = hw.get(DcMotor.class, "slide.R");
        intakeMotor = hw.get(DcMotor.class, "intake_motor");
        clawRotate = new SimpleServo(hw, "claw_rotate", -180, 180);

        // Configure slide motors
        //leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set inversions
        leftIntakeFlip.setInverted(true);
        rightIntakeFlip.setInverted(true);

       leftClawPivot.setInverted(true);
        rightClawPivot.setInverted(true);


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
        return true;
    }

    public boolean isLinkageRetracted() {

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
        return true;
    }

    public boolean isIntakeUp() {

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
    public void controlClawWithJoystick(double joystickY) {
        // Map joystick values (-1 to 1) to servo positions
        // Only allow downward movement (positive joystick values)
        joystickY=-joystickY;
        if (joystickY > 0) {
            double leftPosition = LEFT_CLAW_UP + (joystickY * (LEFT_CLAW_DOWN - LEFT_CLAW_UP));
            double rightPosition = RIGHT_CLAW_UP + (joystickY * (RIGHT_CLAW_DOWN - RIGHT_CLAW_UP));

            leftClawPivot.setPosition(leftPosition);
            rightClawPivot.setPosition(rightPosition);
        } else {
            // Return to default position when joystick is released or pushed up
            leftClawPivot.setPosition(LEFT_CLAW_UP);
            rightClawPivot.setPosition(RIGHT_CLAW_UP);
        }
    }
    public void controlSlidesWithJoystick(double power) {
        // Limit movement based on position
        if ((power > 0 && leftSlide.getCurrentPosition() > SLIDE_MAX_POSITION) ||
                (power < 0 && leftSlide.getCurrentPosition() < 0)) {
            power = 0;
        }
        leftSlide.setPower(power);
        rightSlide.setPower(power);
    }
    public void setIntakePower(double power) {
        intakeMotor.setPower(power);
    }
    public void rotateClawFront() {
        clawRotate.setPosition(CLAW_FACE_FRONT);
    }

    public void rotateClawBack() {
        clawRotate.setPosition(CLAW_FACE_BACK);
    }




    // Getter methods for servo positions

}