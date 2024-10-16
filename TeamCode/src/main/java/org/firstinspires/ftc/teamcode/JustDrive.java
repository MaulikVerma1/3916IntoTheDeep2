package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@TeleOp(name="Just Drive TeleOp", group = "Apex Robotics 3916")
public class JustDrive extends CommandOpMode {
    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;
    @Override
    public void initialize() {
        //default driving
        driver = new GamepadEx(gamepad1);
        codriver = new GamepadEx(gamepad2);
        drive = new DriveSubsystem(hardwareMap, "leftBack", "rightBack", "leftFront", "rightFront", new boolean[] {false, false, false, false});
        driveCommand = new DefaultDrive(drive,
                driver::getLeftX,
                driver::getLeftY,
                driver::getRightX,
                () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
                () -> driver.getButton(GamepadKeys.Button.RIGHT_BUMPER)
        );
        register(drive);
        drive.setDefaultCommand(driveCommand);

    }
}