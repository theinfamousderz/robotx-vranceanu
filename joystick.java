package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by thein on 10/12/2016.
 */
@TeleOp(name = "Joystick v2", group = "Galeata cu experimente")
public class joystick extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private ModernRoboticsI2cRangeSensor rangeSensor;
    float xValue, yValue, leftPower, rightPower;
    private ModernRoboticsI2cCompassSensor compass;
    int count = 0, count2 = 1;
    double distance;

    @Override
    public void runOpMode() throws InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("MotorLeft");
        motorRight = hardwareMap.dcMotor.get("MotorRight");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "RangeSensor");
        compass = hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "CompassSensor");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        compass.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);

        telemetry.addData("Status", "asteptam...");
        telemetry.update();
        rangeSensor.enableLed(false);
        waitForStart();

        while(opModeIsActive()){

            double x, y, z;
            yValue = gamepad1.left_stick_y;
            xValue = gamepad1.left_stick_x;

            if(gamepad1.y)
                if(count2%2==0){
                    compass.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);
                    telemetry.addData("Compass", "Compass in measurement mode");
                    telemetry.update();
                    count2++;
                }
                else{
                    compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
                    telemetry.addData("Compass", "Compass in calibration mode");
                    telemetry.update();
                    count2++;
                }


            if(gamepad1.x)
                if(count%2==0){
                    rangeSensor.enableLed(true);
                    count++;
                }
                else{
                    rangeSensor.enableLed(false);
                    count++;
                }

            distance = rangeSensor.getDistance(DistanceUnit.CM);

            if(distance<10.0){
                motorLeft.setPower(-1.0);
                motorRight.setPower(-1.0);
                sleep(450);
                motorLeft.setPower(0.0);
                motorRight.setPower(0.0);
                sleep(50);
            }



            leftPower =  yValue - xValue;
            rightPower = yValue + xValue;

            motorLeft.setPower(-leftPower);
            motorRight.setPower(-rightPower);

            Acceleration accel = compass.getAcceleration();

            double accelMagnitude = Math.sqrt(accel.xAccel*accel.xAccel + accel.yAccel*accel.yAccel + accel.zAccel*accel.zAccel);

            x = accel.xAccel * 100;
            x = (int)x;
            x = x/100;

            y = accel.yAccel * 100;
            y = (int)y;
            y = y/100;

            z = accel.zAccel * 100;
            z = (int)z;
            z = z/100;

            telemetry.addData("Acceleratie", x + " " + y + " " + z );
            telemetry.addData("Accelmagnitude", accelMagnitude);
            telemetry.addData("heading", "%.1f", compass.getDirection());
            telemetry.addData("vector planar", Math.sqrt(y*y+z*z));
            telemetry.update();

            idle();
        }
    }
}