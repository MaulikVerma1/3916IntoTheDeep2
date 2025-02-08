package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.teleop.commands.CompleteCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.commands.PrepareCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;

@TeleOp(name = "Just Drive")
public class JustDrive extends CommandOpMode {
    private GamepadEx driver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;

    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);
        GamepadEx codriver = new GamepadEx(gamepad2);
        IntakeArmSubsystem intakeArm = new IntakeArmSubsystem(hardwareMap);
        drive = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront",
                new boolean[] {false, false, false, false});
        driveCommand = new DefaultDrive(drive,
                driver::getLeftX,
                driver::getLeftY,
                driver::getRightX,
                ()-> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
                ()-> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER)

        );
        register(drive);
        drive.setDefaultCommand(driveCommand);


        register(intakeArm);

        // A button - Prepare for collection (extend + down)
        new GamepadButton(codriver, GamepadKeys.Button.A)
                .whenPressed(new PrepareCollectionCommand(intakeArm));

        // B button - Complete collection (retract + up)
        new GamepadButton(codriver, GamepadKeys.Button.B)
                .whenPressed(new CompleteCollectionCommand(intakeArm));

        // Simple button controls for claw
        schedule(new RunCommand(() -> {
            // Left bumper - Open claw
            if (gamepad2.left_bumper) {
                intakeArm.openClaw();
            }
            // Right bumper - Close claw
            if (gamepad2.right_bumper) {
                intakeArm.closeClaw();
            }
            // Y button - Move claw up
            if (gamepad2.y) {
                intakeArm.moveClawUp();
            }
            // X button - Move claw down
            if (gamepad2.x) {
                intakeArm.moveClawDown();
            }
            if (gamepad2.dpad_up) {
                intakeArm.rotateClawFront();
            }
            if (gamepad2.dpad_down) {
                intakeArm.rotateClawBack();
            }
            double intakePower = gamepad2.right_trigger - gamepad2.left_trigger;
            intakeArm.setIntakePower(intakePower);
        }));
    }
}