package org.firstinspires.ftc.teamcode; // Package declaration

// Imports CommandOpMode
import com.arcrobotics.ftclib.command.CommandOpMode;
// Imports RunCommand
import com.arcrobotics.ftclib.command.RunCommand;
// Imports GamepadButton
import com.arcrobotics.ftclib.command.button.GamepadButton;
// Imports GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadEx;
// Imports GamepadKeys
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
// Imports TeleOp
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// Imports CompleteCollectionCommand
import org.firstinspires.ftc.teamcode.teleop.commands.CompleteCollectionCommand;
// Imports PrepareCollectionCommand
import org.firstinspires.ftc.teamcode.teleop.commands.PrepareCollectionCommand;
// Imports IntakeArmSubsystem
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeArmSubsystem;


// This annotation declares that this class is a TeleOp mode named "Just Drive"
@TeleOp(name = "Just Drive")
public class JustDrive extends CommandOpMode { // This class extends CommandOpMode, which allows for command-based programming

    // Declares a GamepadEx object named 'driver' for the main driver
    private GamepadEx driver;
    // This line used to declare two GamepadEx objects, 'driver' and 'codriver', but 'codriver' was removed from here

    @Override
    public void initialize() { // This method runs when the OpMode is initialized

        // Creates a new GamepadEx instance for 'driver' using 'gamepad1' input from the controller
        driver = new GamepadEx(gamepad1);

        // Creates a new GamepadEx instance for 'codriver' using 'gamepad2' input from the controller
        GamepadEx codriver = new GamepadEx(gamepad2);

        // Creates an instance of IntakeArmSubsystem, passing in the hardwareMap to access hardware components
        IntakeArmSubsystem intakeArm = new IntakeArmSubsystem(hardwareMap);

        // Registers the intakeArm subsystem so it can be managed by the command scheduler
        register(intakeArm);//remember to use semicolon
        //newlines for readability

        

        // Creates a new button binding for the A button on 'codriver'
        // When pressed, it triggers the PrepareCollectionCommand, which extends and lowers the intake arm
        new GamepadButton(codriver, GamepadKeys.Button.A)
                .whenPressed(new PrepareCollectionCommand(intakeArm));//periods on new line for readability

        // Creates a new button binding for the B button on 'codriver'
        // When pressed, it triggers the CompleteCollectionCommand, which retracts and raises the intake arm
        new GamepadButton(codriver, GamepadKeys.Button.B)
                .whenPressed(new CompleteCollectionCommand(intakeArm));// i dont remember what this does
    } // End of initialize method
} // End of JustDrive class
