package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CompleteCollectionCommand extends SequentialCommandGroup {
    public CompleteCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new MoveIntakeUpCommand(arm),          // First move intake up
                new WaitCommand(500),                  // Wait for stability
                new RetractLinkageCommand(arm),        // Then retract linkage
                new WaitCommand(500),                  // Wait for stability
                new MoveClawPivotDownCommand(arm),     // Move claw pivot down
                new WaitCommand(250),                  // Short wait
                new CloseClawGripCommand(arm)          // Finally grip the pixel
        );
    }
}