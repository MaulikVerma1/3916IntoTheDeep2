    package org.firstinspires.ftc.teamcode.teleop.subsystems;

    import com.arcrobotics.ftclib.command.SubsystemBase;
    import com.arcrobotics.ftclib.hardware.ServoEx;
    import com.arcrobotics.ftclib.hardware.SimpleServo;
    import com.qualcomm.robotcore.hardware.HardwareMap;
    import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

    public class IntakeArmSubsystem extends SubsystemBase {
        private final ServoEx leftLinkage;
        private final ServoEx rightLinkage;
        private final ServoEx leftIntakeFlip;
        private final ServoEx rightIntakeFlip;
        private final ServoEx clawPivot;
        private final ServoEx clawGrip;

        private static final double POSITION_TOLERANCE = 0.05;

        // Define servo positions
        private static final double LINKAGE_RETRACTED = 0.0;
        private static final double LINKAGE_EXTENDED = 1.0;

        private static final double INTAKE_UP = 0.0;
        private static final double INTAKE_DOWN = 1.0;

        private static final double CLAW_PIVOT_UP = 0.0;
        private static final double CLAW_PIVOT_DOWN = 0.8;

        private static final double CLAW_OPEN = 0.7;
        private static final double CLAW_CLOSED = 0.0;

        public IntakeArmSubsystem(HardwareMap hw) {
            leftLinkage = new SimpleServo(hw, "left_linkage", 0, 180, AngleUnit.DEGREES);
            rightLinkage = new SimpleServo(hw, "right_linkage", 0, 180, AngleUnit.DEGREES);
            leftIntakeFlip = new SimpleServo(hw, "left_intake_flip", 0, 180, AngleUnit.DEGREES);
            rightIntakeFlip = new SimpleServo(hw, "right_intake_flip", 0, 180, AngleUnit.DEGREES);
            clawPivot = new SimpleServo(hw, "claw_pivot", 0, 180, AngleUnit.DEGREES);
            clawGrip = new SimpleServo(hw, "claw_grip", 0, 180, AngleUnit.DEGREES);

            retractLinkage();
            moveIntakeUp();
            moveClawUp();
            openClaw();
        }

        // Linkage controls
        public void extendLinkage() {
            leftLinkage.turnToAngle(LINKAGE_EXTENDED);
            rightLinkage.turnToAngle(LINKAGE_EXTENDED);
        }

        public void retractLinkage() {
            leftLinkage.turnToAngle(LINKAGE_RETRACTED);
            rightLinkage.turnToAngle(LINKAGE_RETRACTED);
        }

        public boolean isLinkageExtended() {
            return Math.abs(leftLinkage.getPosition() - LINKAGE_EXTENDED) < POSITION_TOLERANCE &&
                    Math.abs(rightLinkage.getPosition() - LINKAGE_EXTENDED) < POSITION_TOLERANCE;
        }

        public boolean isLinkageRetracted() {
            return Math.abs(leftLinkage.getPosition() - LINKAGE_RETRACTED) < POSITION_TOLERANCE &&
                    Math.abs(rightLinkage.getPosition() - LINKAGE_RETRACTED) < POSITION_TOLERANCE;
        }

        // Intake flip controls
        public void moveIntakeDown() {
            leftIntakeFlip.turnToAngle(INTAKE_DOWN);
            rightIntakeFlip.turnToAngle(INTAKE_DOWN);
        }

        public void moveIntakeUp() {
            leftIntakeFlip.turnToAngle(INTAKE_UP);
            rightIntakeFlip.turnToAngle(INTAKE_UP);
        }

        public boolean isIntakeDown() {
            return Math.abs(leftIntakeFlip.getPosition() - INTAKE_DOWN) < POSITION_TOLERANCE &&
                    Math.abs(rightIntakeFlip.getPosition() - INTAKE_DOWN) < POSITION_TOLERANCE;
        }

        public boolean isIntakeUp() {
            return Math.abs(leftIntakeFlip.getPosition() - INTAKE_UP) < POSITION_TOLERANCE &&
                    Math.abs(rightIntakeFlip.getPosition() - INTAKE_UP) < POSITION_TOLERANCE;
        }

        // Claw controls
        public void moveClawDown() {
            clawPivot.turnToAngle(CLAW_PIVOT_DOWN);
        }

        public void moveClawUp() {
            clawPivot.turnToAngle(CLAW_PIVOT_UP);
        }

        public void closeClaw() {
            clawGrip.turnToAngle(CLAW_CLOSED);
        }

        public void openClaw() {
            clawGrip.turnToAngle(CLAW_OPEN);
        }

        public boolean isClawDown() {
            return Math.abs(clawPivot.getPosition() - CLAW_PIVOT_DOWN) < POSITION_TOLERANCE;
        }

        public boolean isClawClosed() {
            return Math.abs(clawGrip.getPosition() - CLAW_CLOSED) < POSITION_TOLERANCE;
        }
    }