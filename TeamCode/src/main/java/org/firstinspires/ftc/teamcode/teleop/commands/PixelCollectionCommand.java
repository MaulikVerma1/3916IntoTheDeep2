package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class PixelCollectionCommand extends SequentialCommandGroup {
    public PixelCollectionCommand(IntakeArmSubsystem intakeArm) {
        addCommands(
                new ExtendIntakeCommand(intakeArm),
                new RetractIntakeCommand(intakeArm),
                new GrabWithClawCommand(intakeArm),
                new MoveArmToDropCommand(intakeArm)
        );
    }
}

class ExtendIntakeCommand extends CommandBase {
    private final IntakeArmSubsystem intakeArm;

    public ExtendIntakeCommand(IntakeArmSubsystem intakeArm) {
        this.intakeArm = intakeArm;
        addRequirements(intakeArm);
    }

    @Override
    public void initialize() {
        intakeArm.extendIntake();
    }

    @Override
    public boolean isFinished() {
        return intakeArm.isIntakeExtended();
    }
}

class RetractIntakeCommand extends CommandBase {
    private final IntakeArmSubsystem intakeArm;

    public RetractIntakeCommand(IntakeArmSubsystem intakeArm) {
        this.intakeArm = intakeArm;
        addRequirements(intakeArm);
    }

    @Override
    public void initialize() {
        intakeArm.retractIntake();
    }

    @Override
    public boolean isFinished() {
        return intakeArm.isIntakeRetracted();
    }
}

class GrabWithClawCommand extends CommandBase {
    private final IntakeArmSubsystem intakeArm;

    public GrabWithClawCommand(IntakeArmSubsystem intakeArm) {
        this.intakeArm = intakeArm;
        addRequirements(intakeArm);
    }

    @Override
    public void initialize() {
        intakeArm.closeClaw();
    }

    @Override
    public boolean isFinished() {
        return intakeArm.isClawClosed();
    }
}

class MoveArmToDropCommand extends CommandBase {
    private final IntakeArmSubsystem intakeArm;

    public MoveArmToDropCommand(IntakeArmSubsystem intakeArm) {
        this.intakeArm = intakeArm;
        addRequirements(intakeArm);
    }

    @Override
    public void initialize() {
        intakeArm.setArmToDrop();
    }

    @Override
    public boolean isFinished() {
        return intakeArm.isArmAtDrop();
    }
}