package org.firstinspires.ftc.teamcode.teleop.commands;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.teleop.subsystems.MotorSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class MotorButtonCommand extends CommandBase{
    private double power;
    private double speed;
    private MotorSubsystem subsystem;
    private BooleanSupplier fw;
    private BooleanSupplier bw;

    public MotorButtonCommand(MotorSubsystem subsystem, BooleanSupplier fw, BooleanSupplier bw, double speed) {
        this.subsystem = subsystem;
        this.fw = fw;
        this.bw = bw;
        this.speed = speed ;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.brake();
    }

    @Override
    public void execute() {
        if (fw.getAsBoolean()) {
            subsystem.setPower(speed);
        } else if (bw.getAsBoolean()) {
            subsystem.setPower(-speed);
        } else {
            subsystem.brake();
        }

    }

    @Override
    public void end(boolean interrupted) {
        subsystem.brake();
    }
}
