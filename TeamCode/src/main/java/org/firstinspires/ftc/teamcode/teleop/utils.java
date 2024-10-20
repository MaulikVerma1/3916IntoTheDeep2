package org.firstinspires.ftc.teamcode.teleop;

import static org.firstinspires.ftc.teamcode.TeleOpConfig.DEFULT_POWER;


import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.teleop.commands.MotorButtonCommand;
import org.firstinspires.ftc.teamcode.teleop.subsystems.MotorSubsystem;
import com.arcrobotics.ftclib.command.CommandOpMode;

public class utils{
    private MotorSubsystem motor;
    private MotorButtonCommand command;

    public void bind(HardwareMap hw, CommandOpMode opMode, GamepadEx driver,GamepadKeys.Button forwardButton, GamepadKeys.Button backButton, String type, String name){
        if (type.equals("motor")) {
            motor = new MotorSubsystem(hw, name);
            command = new MotorButtonCommand(motor, () -> driver.getButton(forwardButton), () -> driver.getButton(backButton), DEFULT_POWER);
            motor.setDefaultCommand(command);
            opMode.register(motor);
        } else if (type.equals("servo")) {
            //do all the motor stuff but for servos here: button bakc puts it to defult pos and button forward moves if foward slowly

        }

    }
}
