package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeColorSubsystem;

// All helper commands moved here
class ExtendLinkageCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public ExtendLinkageCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.extendLinkage();
    }

    @Override
    public boolean isFinished() {
        return arm.isLinkageExtended();
    }
}

class RetractLinkageCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public RetractLinkageCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.retractLinkage();
    }

    @Override
    public boolean isFinished() {
        return arm.isLinkageRetracted();
    }
}

class MoveIntakeDownCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public MoveIntakeDownCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveIntakeDown();
    }

    @Override
    public boolean isFinished() {
        return arm.isIntakeDown();
    }
}

class MoveIntakeUpCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public MoveIntakeUpCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveIntakeUp();
    }

    @Override
    public boolean isFinished() {
        return arm.isIntakeUp();
    }
}

class MoveClawPivotDownCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public MoveClawPivotDownCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveClawPivotDown();
    }

    @Override
    public boolean isFinished() {
        return arm.isClawPivotDown();
    }
}

class MoveClawPivotUpCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public MoveClawPivotUpCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveClawPivotUp();
    }

    @Override
    public boolean isFinished() {
        return !arm.isClawPivotDown();
    }
}

class CloseClawGripCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public CloseClawGripCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.closeClawGrip();
    }

    @Override
    public boolean isFinished() {
        return arm.isClawGripClosed();
    }
}

class OpenClawGripCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public OpenClawGripCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.openClawGrip();
    }

    @Override
    public boolean isFinished() {
        return !arm.isClawGripClosed();
    }
}

class WaitForPixelCommand extends CommandBase {
    private final IntakeColorSubsystem intake;
    private boolean hasPixel = false;

    public WaitForPixelCommand(IntakeColorSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        if (!intake.isYellow()) {
            hasPixel = true;
        }
    }

    @Override
    public boolean isFinished() {
        return hasPixel;
    }
}