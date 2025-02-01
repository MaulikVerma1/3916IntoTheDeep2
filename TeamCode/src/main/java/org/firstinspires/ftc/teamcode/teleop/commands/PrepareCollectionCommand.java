package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeColorSubsystem;
import com.arcrobotics.ftclib.command.WaitCommand;

public class PrepareCollectionCommand extends SequentialCommandGroup {
    public PrepareCollectionCommand(IntakeArmSubsystem arm, IntakeColorSubsystem intakeColor) {
        addCommands(
                new ExtendLinkageCommand(arm),
                new WaitCommand(500),
                new MoveIntakeDownCommand(arm)
                //new CollectPixelCommand(intakeColor)
        );
    }
}