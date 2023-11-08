package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "testWheels")
public class test extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    int currentMotorIndex = 0;

    @Override
    public void init() {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        Gamepad gamepad = gamepad1;

        if (gamepad.a) {
            currentMotorIndex++;

            if (currentMotorIndex > 4) {
                currentMotorIndex = 0;
            }
        }

        switch (currentMotorIndex) {
            case 0:
                frontLeftMotor.setPower(gamepad.left_stick_y);
                break;
            case 1:
                frontRightMotor.setPower(gamepad.left_stick_y);
                break;
            case 2:
                backLeftMotor.setPower(gamepad.left_stick_y);
                break;
            case 3:
                backRightMotor.setPower(gamepad.left_stick_y);
                break;
        }

        telemetry.addData("Selected Motor", getCurrentMotorName());
        telemetry.addData("Motor Power", gamepad.left_stick_y);
        telemetry.update();
    }

    private String getCurrentMotorName() {
        switch (currentMotorIndex) {
            case 0:
                return "Front Left Motor";
            case 1:
                return "Front Right Motor";
            case 2:
                return "Back Left Motor";
            case 3:
                return "Back Right Motor";
            default:
                return "Unknown Motor";
        }
    }
}