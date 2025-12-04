package org.firstinspires.ftc.teamcode.teleop.subsystems;


import com.arcrobotics.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;


import java.lang.reflect.Array;


public class DriveSubsystem extends SubsystemBase {


    private final MecanumDrive drive;


    //motors
    private final MotorEx backRight;
    private final MotorEx backLeft;
    private final MotorEx frontRight;
    private final MotorEx frontLeft;


    private final IMU imu;
    private final VoltageSensor battery;


    private boolean fieldCentricEnabled = false;
    private double drivePowerScalar = 1.0;






    public DriveSubsystem(HardwareMap hw, String backRight, String backLeft, String frontRight, String frontLeft, boolean[] reverse) {
        this.backRight = new MotorEx(hw, backRight);
        this.backLeft = new MotorEx(hw, backLeft);
        this.frontRight = new MotorEx(hw, frontRight);
        this.frontLeft = new MotorEx(hw, frontLeft);


        if (reverse[0]){this.backRight.motor.setDirection(DcMotorSimple.Direction.REVERSE);}
        if (reverse[1]){this.backLeft.motor.setDirection(DcMotorSimple.Direction.REVERSE);}
        if (reverse[2]){this.frontRight.motor.setDirection(DcMotorSimple.Direction.REVERSE);}
        if (reverse[3]){this.frontLeft.motor.setDirection(DcMotorSimple.Direction.REVERSE);}


        setZeroPowerBrake(true);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        drive = new MecanumDrive(this.frontLeft, this.frontRight, this.backLeft, this.backRight);


        imu = hw.get(IMU.class, "imu");
        IMU.Parameters params = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        LogoFacingDirection.UP,
                        UsbFacingDirection.FORWARD
                )
        );
        imu.initialize(params);


        battery = hw.voltageSensor.iterator().hasNext() ? hw.voltageSensor.iterator().next() : null;


    }




    public void drive(double strafe, double forward, double turn) {
        strafe  *= drivePowerScalar;
        forward *= drivePowerScalar;
        turn    *= drivePowerScalar;


        strafe  = Math.abs(strafe)  < 0.03 ? 0.0 : strafe;
        forward = Math.abs(forward) < 0.03 ? 0.0 : forward;
        turn    = Math.abs(turn)    < 0.03 ? 0.0 : turn;


        if (fieldCentricEnabled) {
            double h = getHeadingRadians();
            double cos = Math.cos(-h);
            double sin = Math.sin(-h);
            double x = strafe;
            double y = forward;


            double rx = x * cos - y * sin;
            double ry = x * sin + y * cos;


            drive.driveRobotCentric(rx, ry, turn);
        } else {
            drive.driveRobotCentric(strafe, forward, turn);
        }
    }


    public void setFieldCentric(boolean enabled) {
        fieldCentricEnabled = enabled;
    }


    public boolean isFieldCentric() {
        return fieldCentricEnabled;
    }


    public void setDrivePowerScalar(double scalar01) {
        drivePowerScalar = Math.max(0.0, Math.min(1.0, scalar01));
    }




    public double getHeadingRadians() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }


    public void resetHeading() {
        imu.resetYaw();
    }


    public double[] getMotorVelocities() {
        return new double[] {
                backRight.encoder.getCorrectedVelocity(),
                backLeft.encoder.getCorrectedVelocity(),
                frontRight.encoder.getCorrectedVelocity(),
                frontLeft.encoder.getCorrectedVelocity()
        };
    }


    public double getBatteryVoltage() {
        return (battery != null) ? battery.getVoltage() : 12.0;
    }


    public void setZeroPowerBrake(boolean brake) {
        DcMotor.ZeroPowerBehavior mode = brake
                ? DcMotor.ZeroPowerBehavior.BRAKE
                : DcMotor.ZeroPowerBehavior.FLOAT;
        backRight.motor.setZeroPowerBehavior(mode);
        backLeft.motor.setZeroPowerBehavior(mode);
        frontRight.motor.setZeroPowerBehavior(mode);
        frontLeft.motor.setZeroPowerBehavior(mode);
    }


    public void setRunMode(DcMotor.RunMode mode) {
        backRight.motor.setMode(mode);
        backLeft.motor.setMode(mode);
        frontRight.motor.setMode(mode);
        frontLeft.motor.setMode(mode);
    }


}