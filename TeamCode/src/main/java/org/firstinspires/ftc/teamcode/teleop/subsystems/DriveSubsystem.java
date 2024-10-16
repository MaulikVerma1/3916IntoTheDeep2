package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.reflect.Array;

public class DriveSubsystem extends SubsystemBase {

    private final MecanumDrive drive;
    private final MotorEx backRight;
    private final MotorEx backLeft;
    private final MotorEx frontRight;
    private final MotorEx frontLeft;





    public DriveSubsystem(HardwareMap hw, String backRight, String backLeft, String frontRight, String frontLeft, boolean[] reverse) {
        this.backRight = new MotorEx(hw, backRight);
        this.backLeft = new MotorEx(hw, backLeft);
        this.frontRight = new MotorEx(hw, frontRight);
        this.frontLeft = new MotorEx(hw, frontLeft);

        if (reverse[0]){this.backRight.motor.setDirection(DcMotorSimple.Direction.REVERSE);}
        if (reverse[1]){this.backLeft.motor.setDirection(DcMotorSimple.Direction.REVERSE);}
        if (reverse[2]){this.frontRight.motor.setDirection(DcMotorSimple.Direction.REVERSE);}
        if (reverse[3]){this.frontLeft.motor.setDirection(DcMotorSimple.Direction.REVERSE);}

        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);

    }


    public void drive(double strafe, double forward, double turn) {
        drive.driveRobotCentric(strafe, forward, turn);
    }

    public void drive(double strafe, double forward, double turn, double heading) {
        drive.driveFieldCentric(strafe, forward, turn, heading);
    }

    public double[] getMotorVelocities(){
        return new double[]{backRight.encoder.getCorrectedVelocity(), backLeft.encoder.getCorrectedVelocity(), frontRight.encoder.getCorrectedVelocity(), frontLeft.encoder.getCorrectedVelocity()};
    }

}
