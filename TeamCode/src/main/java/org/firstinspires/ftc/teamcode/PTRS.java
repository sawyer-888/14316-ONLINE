package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "PTRS", group = "TeleOp")
public class PTRS extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    @Override
    public void init() {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // apperantly i gotta set motor directoins?
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double leftStickY = -gamepad1.left_stick_y; // inverted for forward/backaswward motion
        double leftStickX = gamepad1.left_stick_x; // for sidewasys motion
        double rightStickX = gamepad1.right_stick_x; // for rotation

        double frontLeftPower = leftStickY - leftStickX - rightStickX;
        double frontRightPower = leftStickY + leftStickX + rightStickX;
        double backLeftPower = leftStickY + leftStickX - rightStickX;
        double backRightPower = leftStickY - leftStickX + rightStickX;

        double maxPower = Math.max(
                Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))
        );

        if (maxPower > 1.0) {
            frontLeftPower /= maxPower;
            frontRightPower /= maxPower;
            backLeftPower /= maxPower;
            backRightPower /= maxPower;
        }

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);

        if (gamepad1.a) {
            telemetry.addData("Pressed A", "Controller is working, ys Letlstoomg go");
            telemetry.update();
        }
    }


}