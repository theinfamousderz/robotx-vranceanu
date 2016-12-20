// First we identify the package the OpMode belongs to.

package org.firstinspires.ftc.teamcode;

// Now we import the classes we need from the FTC SDK and the Java SDK.

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.text.SimpleDateFormat;
import java.util.Date;

// Here we extend the base OpMode class to be the new NullOp class.

@Autonomous(name="experiment_autonomie")
public class experiment extends OpMode
{
    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();

    // Here is the init() method. We don't have anything to do here so we could
    // have left it out.

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private ModernRoboticsI2cRangeSensor rangeSensor;
    double distance;

    @Override
    public void init()
    {
        motorLeft = hardwareMap.dcMotor.get("MotorLeft");
        motorRight = hardwareMap.dcMotor.get("MotorRight");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "RangeSensor");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    // Here we are intializing the variables we are using each time we run the
    // OpMode.

    @Override
    public void init_loop()
    {
        startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        runtime.reset();
        telemetry.addData("Null Op Init Loop", runtime.toString());
    }

    // Here are the start() and stop() methods. We don't have anything to do at those
    // times so we could have left them out.

    @Override
    public void start()
    {
    }
    @Override
    public void stop()
    {
    }

    // The loop() method is called over and over until the Stop button is pressed.
    // The method displays the elapsed run time on the driver stattion using the
    // telemtry field of the base OpMode class.

    @Override
    public void loop()
    {
        distance = rangeSensor.getDistance(DistanceUnit.CM);
        if(distance <= 25.0){

            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);

            sleep(100);

            motorRight.setPower(-1.0);
            motorLeft.setPower(-1.0);

            sleep(1250);

            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);

            //stanga
            motorRight.setPower(1.0);
            motorLeft.setPower(-1.0);

            sleep(800);

            motorLeft.setPower(0.0);
            motorRight.setPower(0.0);

            sleep(1000);
        }
        else{
            motorLeft.setPower(1.0);
            motorRight.setPower(1.0);
        }
    }

    public void sleep(long time){
        long past_time;
        long current_time;
        past_time = System.currentTimeMillis();
        current_time = past_time;
        while(current_time < past_time + time)
            current_time = System.currentTimeMillis();
    }

}