package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ScoringSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.commands.DefaultDrive;

@TeleOp(name="Scoring TeleOp", group = "Apex Robotics 3916")
public class ScoringTeleOp extends CommandOpMode {
    private GamepadEx driver, codriver;
    private DriveSubsystem drive;
    private DefaultDrive driveCommand;
    private ScoringSubsystem scoring;

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

        // Initialize scoring system
        scoring = new ScoringSubsystem(hardwareMap, telemetry);
        register(scoring);

        // Add manual control command
        schedule(new RunCommand(() -> {
            // Slide control with right stick Y
            double slidePower = -codriver.getRightY();  // Negate for proper direction
            scoring.setSlidePower(slidePower);

            // Claw pivot control with left stick Y
            double pivotPosition = (1 - codriver.getLeftY()) / 2.0;  // Map -1,1 to 0,1
            scoring.setClawPivotPosition(pivotPosition);
        }));

        // Claw grip controls
        codriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(() -> scoring.closeClawGrip());
        codriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> scoring.openClawGrip());
    }
}