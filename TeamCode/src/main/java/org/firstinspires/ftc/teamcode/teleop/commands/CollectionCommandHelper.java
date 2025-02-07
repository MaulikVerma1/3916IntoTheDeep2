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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
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
    public boolean isFinished() {
        return true;
    }
}