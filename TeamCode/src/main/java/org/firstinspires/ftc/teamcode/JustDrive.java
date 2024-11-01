package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.LauncherCommand;
import org.firstinspires.ftc.teamcode.teleop.commands.SimpleSlide;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.SlideSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.teleop.commands.ClawITD;

@TeleOp(name="Just Drive TeleOp", group = "Apex Robotics 3916")
public class JustDrive extends CommandOpMode {

    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;
    private ClawITD clawCommand;
    private ClawSubsystem claw;
    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);
        //normal config

        drive = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront", new boolean[] {true, false, false, true});

        driveCommand = new DefaultDrive(drive, driver::getLeftX, driver::getLeftY, driver::getRightX,
               () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),  () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

        register(drive);
        drive.setDefaultCommand(driveCommand);

        claw = new ClawSubsystem(hardwareMap, "claw", 0, 90);
        clawCommand = new ClawITD(claw, () -> driver.getButton(GamepadKeys.Button.A));
        register(claw);
        claw.setDefaultCommand(clawCommand);

        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(clawCommand);
        driver.getGamepadButton(GamepadKeys.Button.A).whenReleased(clawCommand);




    }
}