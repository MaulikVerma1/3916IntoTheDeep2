package org.firstinspires.ftc.teamcode.teleop.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import java.util.function.BooleanSupplier;

import org.firstinspires.ftc.teamcode.TeleOpConfig;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;
public class ClawITD extends CommandBase {
    boolean currentState;
    double closedness;
    BooleanSupplier button;
    ClawSubsystem clawSubsystem;

    public ClawITD(ClawSubsystem clawSubsystem, BooleanSupplier button){
        currentState = false;
        closedness = 0.0;
        this.button = button;
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
        clawSubsystem.open();
    }

    @Override
    public void execute() {
        if (button.getAsBoolean()) {
            if (!currentState) {
                clawSubsystem.safeclose((TeleOpConfig.CLAW_CLOSE_DEGREES / (2 * Math.PI)));
            }
        }else {
            clawSubsystem.open();
        }
    }

}
