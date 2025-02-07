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
    }

    @Override
    public void runOpMode() {
        initialize();

        try {
            waitForStart();

            while (opModeIsActive()) {
                run();
            }
        } finally {
            // This will run when the OpMode stops
            telemetry.addLine("OpMode stopping - Performing hard reset...");
            telemetry.update();

            // Completely recreate the IntakeArmSubsystem
            intakeArm = new IntakeArmSubsystem(hardwareMap, telemetry);

            telemetry.addLine("Hard reset complete - IntakeArmSubsystem recreated");
            telemetry.update();
        }
    }





}