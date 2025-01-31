package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CompleteCollectionCommand extends SequentialCommandGroup {
    public CompleteCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new MoveIntakeUpCommand(arm),
                new WaitCommand(500),
                new RetractLinkageCommand(arm)
                //new MoveClawDownCommand(arm),
                //new GrabPixelCommand(arm)
        );
    }
}