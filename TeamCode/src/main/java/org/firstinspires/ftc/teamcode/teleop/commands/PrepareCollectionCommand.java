package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class PrepareCollectionCommand extends SequentialCommandGroup {
    public PrepareCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new ExtendLinkageCommand(arm),
                new MoveIntakeDownCommand(arm)
        );
    }
}