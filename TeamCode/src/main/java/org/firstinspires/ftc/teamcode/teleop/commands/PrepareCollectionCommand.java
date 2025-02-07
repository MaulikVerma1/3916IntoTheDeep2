package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class PrepareCollectionCommand extends SequentialCommandGroup {
    public PrepareCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                new OpenClawCommand(arm),
                new WaitCommand(200),
                //new MoveClawUpCommand(arm),
                new WaitCommand(200),
                new MoveIntakeDownCommand(arm),
                new WaitCommand(200),
                new ExtendLinkageCommand(arm)
        );
    }
}