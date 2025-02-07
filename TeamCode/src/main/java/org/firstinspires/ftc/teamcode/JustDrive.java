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
    private GamepadEx driver;
    //used to be private GamepadEx driver, codriver;

    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        GamepadEx codriver = new GamepadEx(gamepad2);
        IntakeArmSubsystem intakeArm = new IntakeArmSubsystem(hardwareMap);

        // A button - Prepare for collection (extend + down)
        new GamepadButton(codriver, GamepadKeys.Button.A)
                .whenPressed(new PrepareCollectionCommand(intakeArm));

        // B button - Complete collection (retract + up)
        new GamepadButton(codriver, GamepadKeys.Button.B)
                .whenPressed(new CompleteCollectionCommand(intakeArm));
    }








}