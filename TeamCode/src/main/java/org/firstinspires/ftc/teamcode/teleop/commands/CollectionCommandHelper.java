package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CollectionCommandHelper {
    private final IntakeArmSubsystem arm;

    public CollectionCommandHelper(IntakeArmSubsystem arm) {
        this.arm = arm;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class OpenClawCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public OpenClawCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.openClaw();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

class CloseClawCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public CloseClawCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.closeClaw();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

class MoveClawUpCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public MoveClawUpCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveClawUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

class MoveClawDownCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public MoveClawDownCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveClawDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

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
        return !arm.isLinkageMoving();
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
        return !arm.isLinkageMoving();
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
        return true;
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
        return true;
    }
}