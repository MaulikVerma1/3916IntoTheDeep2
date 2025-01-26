package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CompleteCollectionCommand extends SequentialCommandGroup {
    public CompleteCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new MoveIntakeUpCommand(arm),
                new RetractLinkageCommand(arm),
                new MoveClawDownCommand(arm),
                new GrabPixelCommand(arm)
        );
    }
}