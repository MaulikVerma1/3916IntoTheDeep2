package org.firstinspires.ftc.teamcode.teleop.subsystems;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
    private final MotorEx motor;

    public MotorSubsystem(HardwareMap hw, String name) {
        motor = new MotorEx(hw, name);
    }
    public void setPower(double power) {
        motor.set(power);
    }
    public void setVelocity(double velocity) {
        motor.setVelocity(velocity);
    }
    public double getPower() {
        return motor.get();
    }
    public double getVelocity() {
        return motor.encoder.getCorrectedVelocity();
    }
    public void brake() {
        motor.setVelocity(0);
    }
    public void resetEncoder() {
        motor.encoder.reset();
    }



}
