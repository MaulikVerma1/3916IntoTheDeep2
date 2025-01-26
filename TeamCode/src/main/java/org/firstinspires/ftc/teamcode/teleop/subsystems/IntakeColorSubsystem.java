package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeColorSubsystem extends SubsystemBase {
    //private final DcMotor intakeMotor;
    //private final ColorSensor colorSensor;

    private static final double INTAKE_POWER = 0.7;
    private static final double YELLOW_THRESHOLD = 200; // Adjust this value based on testing

    public IntakeColorSubsystem(HardwareMap hw) {
        //intakeMotor = hw.get(DcMotor.class, "intake_motor");
        //colorSensor = hw.get(ColorSensor.class, "color_sensor");

        // Initialize motor
        //intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //public void intake() {
    //  intakeMotor.setPower(INTAKE_POWER);
    //}

    //public void reverse() {
    //  intakeMotor.setPower(-INTAKE_POWER);
    //}

    //public void stop() {
    //  intakeMotor.setPower(0);
    //}

    public boolean isYellow() {
        // Check if red and green values are high but blue is lower (indicating yellow)
        //  return colorSensor.red() > YELLOW_THRESHOLD &&
        //        colorSensor.green() > YELLOW_THRESHOLD &&
        //      colorSensor.blue() < YELLOW_THRESHOLD/2;
        //}
        return true;
    }
}
