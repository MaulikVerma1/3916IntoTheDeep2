package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;
import com.arcrobotics.ftclib.command.WaitCommand;


public class PrepareCollectionCommand extends SequentialCommandGroup {
    public PrepareCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new ExtendLinkageCommand(arm),
                new WaitCommand(500),
                new MoveIntakeDownCommand(arm)
        );
    }
}