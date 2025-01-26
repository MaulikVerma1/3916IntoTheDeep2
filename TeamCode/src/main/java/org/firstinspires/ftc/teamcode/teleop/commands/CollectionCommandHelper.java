package org.firstinspires.ftc.teamcode.teleop.commands;

import com.arcrobotics.ftclib.command.CommandBase;
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

    //@Override
    //public boolean isFinished() {
      //  return arm.isClawDown();
    //}
}

class GrabPixelCommand extends CommandBase {
    private final IntakeArmSubsystem arm;

    public GrabPixelCommand(IntakeArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.closeClaw();
    }

    //@Override
    //public boolean isFinished() {
    //      return arm.isClawClosed();
    //}
//}

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
}