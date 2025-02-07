package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.teleop.commands.CompleteCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.PrepareCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

@TeleOp(name = "Just Drive")
public class JustDrive extends CommandOpMode {
    private GamepadEx driver, codriver;
    private IntakeArmSubsystem intakeArm;

    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);
        intakeArm = new IntakeArmSubsystem(hardwareMap, telemetry);

        // A button - Prepare for collection (extend + down)
        new GamepadButton(codriver, GamepadKeys.Button.A)
                .whenPressed(new PrepareCollectionCommand(intakeArm));

        // B button - Complete collection (retract + up)
        new GamepadButton(codriver, GamepadKeys.Button.B)
                .whenPressed(new CompleteCollectionCommand(intakeArm));

        // Update telemetry in a loop


    }
    @Override
    public void run() {
        super.run();

        // Manual claw controls
        double clawHeightDelta = -gamepad2.right_stick_y; // Negate because pushing up should move claw up
        if (Math.abs(clawHeightDelta) > 0.1) { // Small deadzone
            intakeArm.adjustClawHeight(clawHeightDelta);
        }

        double clawGripDelta = gamepad2.right_trigger - gamepad2.left_trigger; // Right to close, left to open
        if (Math.abs(clawGripDelta) > 0.1) { // Small deadzone
            intakeArm.adjustClawGrip(clawGripDelta);
        }
    }
}