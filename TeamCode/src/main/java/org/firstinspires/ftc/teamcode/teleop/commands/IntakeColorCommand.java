package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeColorSubsystem;
import java.util.function.DoubleSupplier;

public class IntakeColorCommand extends CommandBase {
    private final IntakeColorSubsystem intakeColor;
    private final DoubleSupplier trigger;

    public IntakeColorCommand(IntakeColorSubsystem subsystem, DoubleSupplier triggerValue) {
        intakeColor = subsystem;
        trigger = triggerValue;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        if (trigger.getAsDouble() > 0.1) {  // Trigger threshold
            if (intakeColor.isYellow()) {
                intakeColor.reverse();
            } else {
                intakeColor.intake();
            }
        } else {
            intakeColor.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeColor.stop();
    }
}