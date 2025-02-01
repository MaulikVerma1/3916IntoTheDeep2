package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeColorSubsystem;
import java.util.function.DoubleSupplier;

public class IntakeColorCommand extends CommandBase {
    private final IntakeColorSubsystem intakeColor;
    private final DoubleSupplier trigger;
    private boolean isSpittingOut = false;
    private long spitStartTime = 0;
    private static final long SPIT_DURATION_MS = 500; // Time to spit out unwanted pixel

    public IntakeColorCommand(IntakeColorSubsystem subsystem, DoubleSupplier triggerValue) {
        intakeColor = subsystem;
        trigger = triggerValue;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double triggerValue = trigger.getAsDouble();

//        if (isSpittingOut) {
//            // If we're in spitting mode, check if we should stop
//            if (System.currentTimeMillis() - spitStartTime > SPIT_DURATION_MS) {
//                isSpittingOut = false;
//                intakeColor.stop();
//            }
//            return; // Don't process trigger input while spitting
//        }

        if (triggerValue > 0.1) {  // Trigger threshold
            intakeColor.intake();  // Run intake motor

            // Check color sensor if motor is running
//            if (!intakeColor.isYellow()) {
//                isSpittingOut = true;
//                spitStartTime = System.currentTimeMillis();
//                intakeColor.reverse();  // Spit out non-yellow pixel
//            }
//        } else {
//            intakeColor.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeColor.stop();
    }
}
