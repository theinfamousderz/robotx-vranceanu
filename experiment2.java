package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by thein on 12/12/2016.
 */
@TeleOp(name = "Experiment auto", group = "Galeata cu experimente")
public class experiment2 extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;

    @Override
    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("MotorLeft");
        motorRight = hardwareMap.dcMotor.get("MotorRight");

        motorRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "asteptam...");
        telemetry.update();

        waitForStart();
        telemetry.addData("Status", "sii am inceput");
        telemetry.update();

        sleep(500);

        for(int i = 0; i < 4; i++){

            telemetry.addData("Status", "merge...");

             //90 la dreapta

            motorLeft.setPower(-0.25);
            motorRight.setPower(0.25);

            sleep(1700);

            //stop un moment

            motorLeft.setPower(0.0);
            motorRight.setPower(0.0);

            sleep(500);

            //direct inainte

            motorLeft.setPower(-0.25);
            motorRight.setPower(-0.25);

            sleep(1000);

        }

        motorLeft.setPower(0);
        motorRight.setPower(0);

    }

}
