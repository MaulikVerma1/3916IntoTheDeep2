package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeColorSubsystem;
import org.firstinspires.ftc.teamcode.teleop.commands.PrepareCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.CompleteCollectionCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.IntakeColorCommand;

@TeleOp(name="Just Drive TeleOp", group = "Apex Robotics 3916")
public class JustDrive extends CommandOpMode {
    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;
    private IntakeArmSubsystem intakeArm;
    private IntakeColorSubsystem intakeColor;
    private IntakeColorCommand intakeColorCommand;

    @Override
    public void initialize() {
        // Initialize gamepads
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);

        // Initialize drive system
        drive = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront",
                new boolean[] {false, false, false, false});
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
        intakeArm = new IntakeArmSubsystem(hardwareMap);
        intakeColor = new IntakeColorSubsystem(hardwareMap);
        register(intakeArm);
        register(intakeColor);

        // Create prepare collection command (extends linkage and flips down)
        PrepareCollectionCommand prepareCollection = new PrepareCollectionCommand(intakeArm, intakeColor);
        codriver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(prepareCollection);

        // Create complete collection command (flips up, retracts, claw sequence)
        CompleteCollectionCommand completeCollection = new CompleteCollectionCommand(intakeArm);
        codriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(completeCollection);

        // Bind intake control to right trigger
        intakeColorCommand = new IntakeColorCommand(intakeColor,
                () -> codriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));
        intakeColor.setDefaultCommand(intakeColorCommand);
    }
}