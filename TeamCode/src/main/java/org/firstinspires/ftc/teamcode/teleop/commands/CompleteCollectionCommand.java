package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CompleteCollectionCommand extends SequentialCommandGroup {
    public CompleteCollectionCommand(IntakeArmSubsystem arm) {
        addCommands(
                // First retract linkage and move intake up
                new RetractLinkageCommand(arm),
                //new WaitCommand(200),
                new MoveIntakeUpCommand(arm),
                // Then move claw down and grab
                new MoveClawUpCommand(arm)
                //new WaitCommand(200),
                //new CloseClawCommand(arm)
                //new WaitCommand(200)
                // Finally move claw back up with grip closed
                //new MoveClawUpCommand(arm)
        );
    }
}