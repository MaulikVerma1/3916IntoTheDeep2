package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CompleteCollectionCommand extends SequentialCommandGroup {
    public CompleteCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new MoveClawDownCommand(arm),
                new WaitCommand(200),
                new CloseClawCommand(arm),
                new WaitCommand(200),
                new MoveIntakeUpCommand(arm),
                new WaitCommand(200),
                new RetractLinkageCommand(arm)
        );
    }
}