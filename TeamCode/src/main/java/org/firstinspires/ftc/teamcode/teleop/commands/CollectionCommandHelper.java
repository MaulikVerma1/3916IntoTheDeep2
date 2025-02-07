package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

public class CollectionCommandHelper {
    private final IntakeArmSubsystem arm;

    public CollectionCommandHelper(IntakeArmSubsystem arm) {
        this.arm = arm;
    }
}

class OpenClawCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public OpenClawCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.openClaw();
    }

    @Override
    public void execute() {
        // Wait for the claw to fully open before marking as finished
        if (!arm.isClawClosed()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class CloseClawCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public CloseClawCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.closeClaw();
    }

    @Override
    public void execute() {
        // Wait for the claw to fully close before marking as finished
        if (arm.isClawClosed()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class MoveClawUpCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public MoveClawUpCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveClawUp();
    }

    @Override
    public void execute() {
        // Wait for the claw to reach the up position
        if (arm.isClawUp()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class MoveClawDownCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public MoveClawDownCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveClawDown();
    }

    @Override
    public void execute() {
        // Wait for the claw to reach the down position
        if (arm.isClawDown()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class ExtendLinkageCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public ExtendLinkageCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.extendLinkage();
    }

    @Override
    public void execute() {
        // Wait for linkage to fully extend
        if (arm.isLinkageExtended()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class RetractLinkageCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public RetractLinkageCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.retractLinkage();
    }

    @Override
    public void execute() {
        // Wait for linkage to fully retract
        if (arm.isLinkageRetracted()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class MoveIntakeDownCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public MoveIntakeDownCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveIntakeDown();
    }

    @Override
    public void execute() {
        // Wait for intake to reach down position
        if (arm.isIntakeDown()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

class MoveIntakeUpCommand extends CommandBase {
    private final IntakeArmSubsystem arm;
    private boolean isFinished = false;

    public MoveIntakeUpCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.moveIntakeUp();
    }

    @Override
    public void execute() {
        // Wait for intake to reach up position
        if (arm.isIntakeUp()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}