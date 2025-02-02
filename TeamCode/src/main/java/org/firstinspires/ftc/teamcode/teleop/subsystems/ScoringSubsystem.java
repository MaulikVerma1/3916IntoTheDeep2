package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ScoringSubsystem extends SubsystemBase {
    private final ServoEx leftClawPivot;
    private final ServoEx rightClawPivot;
    private final ServoEx clawGrip;
    private final DcMotor leftSlide;
    private final DcMotor rightSlide;
    private final Telemetry telemetry;

    // Claw positions
    private static final double LEFT_CLAW_PIVOT_UP = 0.8;
    private static final double LEFT_CLAW_PIVOT_DOWN = 0.2;
    private static final double RIGHT_CLAW_PIVOT_UP = 0.2;
    private static final double RIGHT_CLAW_PIVOT_DOWN = 0.8;
    private static final double CLAW_GRIP_OPEN = 0.4;
    private static final double CLAW_GRIP_CLOSED = 0.0;

    // Slide constants
    private static final double SLIDE_POWER = 1.0;
    private static final int SLIDE_MAX_POSITION = 1000; // Adjust based on your robot

    public ScoringSubsystem(HardwareMap hw, Telemetry telemetry) {
        this.telemetry = telemetry;

        // Initialize claw servos
        leftClawPivot = new SimpleServo(hw, "pivot.L", 0, 180, AngleUnit.DEGREES);
        rightClawPivot = new SimpleServo(hw, "pivot.R", 0, 180, AngleUnit.DEGREES);
        clawGrip = new SimpleServo(hw, "claw_grip", 0, 180, AngleUnit.DEGREES);

        // Initialize slide motors
        leftSlide = hw.get(DcMotor.class, "slide.L");
        rightSlide = hw.get(DcMotor.class, "slide.R");

        // Configure slide motors
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //sdfsff
    }

    // Manual control methods
    public void setClawPivotPosition(double position) {
        leftClawPivot.setPosition(position);
        rightClawPivot.setPosition(1.0 - position); // Mirror the position
    }

    public void setSlidePower(double power) {
        // Add limits
        if ((power > 0 && leftSlide.getCurrentPosition() > SLIDE_MAX_POSITION) ||
                (power < 0 && leftSlide.getCurrentPosition() < 0)) {
            power = 0;
        }
        leftSlide.setPower(power);
        rightSlide.setPower(power);
    }

    public void closeClawGrip() {
        clawGrip.setPosition(CLAW_GRIP_CLOSED);
    }

    public void openClawGrip() {
        clawGrip.setPosition(CLAW_GRIP_OPEN);
    }

    @Override
    public void periodic() {
        telemetry.addData("Left Slide Pos", leftSlide.getCurrentPosition());
        telemetry.addData("Right Slide Pos", rightSlide.getCurrentPosition());
        telemetry.addData("Claw Pivot Pos", "L: %.2f, R: %.2f",
                leftClawPivot.getPosition(), rightClawPivot.getPosition());
        telemetry.addData("Claw Grip Pos", clawGrip.getPosition());
    }
}