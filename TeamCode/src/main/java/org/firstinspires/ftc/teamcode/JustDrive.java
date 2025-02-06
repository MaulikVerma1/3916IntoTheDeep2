package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.commands.PrepareCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.CompleteCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeColorSubsystem;

@TeleOp(name="Just Drive TeleOp", group = "Apex Robotics 3916")
public class JustDrive extends CommandOpMode {
    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;
    private IntakeArmSubsystem intakeArm;
    private IntakeColorSubsystem intakeColor;

    @Override
    public void initialize() {
        // Initialize gamepads
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);

        // Initialize drive system
        drive = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront",
                new boolean[]{false, false, false, false});
        driveCommand = new DefaultDrive(drive,
                driver::getLeftX,
                driver::getLeftY,
                driver::getRightX,
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
                () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER)
        );
        register(drive);
        drive.setDefaultCommand(driveCommand);

        // Initialize intake arm and color subsystems
        intakeArm = new IntakeArmSubsystem(hardwareMap, telemetry);
        intakeColor = new IntakeColorSubsystem(hardwareMap);
        register(intakeArm);
        register(intakeColor);

        // Manual control for intake and linkage using bumpers and triggers
        schedule(new RunCommand(() -> {
            // A button - Extend linkage and lower intake
            if (codriver.wasJustPressed(GamepadKeys.Button.A)) {
                new PrepareCollectionCommand(intakeArm).schedule();
            }

            // B button - Complete collection (retract everything)
            if (codriver.wasJustPressed(GamepadKeys.Button.B)) {
                new CompleteCollectionCommand(intakeArm).schedule();
            }

            // DPad Up/Down - Manual claw height control with PID
            if (codriver.getButton(GamepadKeys.Button.DPAD_UP)) {
                intakeArm.adjustClawPosition(0.5); // Move up
            } else if (codriver.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                intakeArm.adjustClawPosition(-0.5); // Move down
            }

            // Y button - Quick move claw up
            if (codriver.wasJustPressed(GamepadKeys.Button.Y)) {
                intakeArm.moveClawUp();
            }

            // X button - Quick move claw down
            if (codriver.wasJustPressed(GamepadKeys.Button.X)) {
                intakeArm.moveClawDown();
            }

            // Right Bumper/Trigger - Claw grip control
            if (codriver.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                intakeArm.adjustClawGrip(-1.0); // Open
            } else if (codriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
                intakeArm.adjustClawGrip(1.0);  // Close
            }

            // Left trigger - Manual intake control
            double leftTrigger = codriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
            if (leftTrigger > 0.1) {
                intakeArm.manualAdjustIntake(-leftTrigger * 0.05);
            }

            // Update telemetry
            telemetry.addData("Manual Control", "Active");
            telemetry.addData("Controls", "DPad Up/Down = Claw Height (PID)");
            telemetry.addData("Buttons", "A=Extend+Down, B=Retract+Up");
            telemetry.addData("Claw", "X=Down, Y=Up");
            telemetry.addData("Grip", "R.Bumper=Open, R.Trigger=Close");
            telemetry.update();
        }));
    }
}