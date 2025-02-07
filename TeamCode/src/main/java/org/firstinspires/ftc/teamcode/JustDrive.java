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

    @Override
    public void run() {
        super.run();

        // Update telemetry with control scheme
        telemetry.addLine("=== Controls ===");
        telemetry.addData("A Button", "Prepare Collection (Extend + Down)");
        telemetry.addData("B Button", "Complete Collection (Retract + Up)");

        // Add control input telemetry
        telemetry.addLine("\n=== Control Inputs ===");
        telemetry.addData("Right Stick Y", "%.2f", -gamepad2.right_stick_y);
        telemetry.addData("Right Trigger", "%.2f", gamepad2.right_trigger);
        telemetry.addData("Left Trigger", "%.2f", gamepad2.left_trigger);

        // Add mechanism state telemetry with actual positions
        telemetry.addLine("\n=== Mechanism States ===");

        // Linkage telemetry
        String linkageStatus = intakeArm.isLinkageExtended() ? "EXTENDED" :
                intakeArm.isLinkageRetracted() ? "RETRACTED" : "MOVING";
        telemetry.addData("Linkage", "%s", linkageStatus);
        telemetry.addData("- Left Linkage", "%.3f", intakeArm.getLeftLinkagePosition());
        telemetry.addData("- Right Linkage", "%.3f", intakeArm.getRightLinkagePosition());

        // Intake telemetry
        String intakeStatus = intakeArm.isIntakeDown() ? "DOWN" :
                intakeArm.isIntakeUp() ? "UP" : "MOVING";
        telemetry.addData("Intake", "%s", intakeStatus);
        telemetry.addData("- Left Flip", "%.3f", intakeArm.getLeftIntakePosition());
        telemetry.addData("- Right Flip", "%.3f", intakeArm.getRightIntakePosition());




    }
}