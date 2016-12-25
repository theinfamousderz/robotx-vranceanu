package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by thein on 25/12/2016.
 */

public class vuforia_exp extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {

        // construiesc un obiect cu diversi parametrii pentru operarea camerei de pe telefon
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        // o sa apara camera pe ecranul telefonului de pe robot

        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        // o sa folosesc camera din spate

        params.vuforiaLicenseKey = "AastLTz/////AAAAGTfpOMQKkUk/nDxWWRRly9RCfqkxFkFZFDa5eKVuXW7diOzQaETKlx+c4pMsxHO6YhzvELvaFY4Fd/T55CeSyYXnqMTRI8Acb2jR829nvE4UNXRlAiL8U+bsjr6Q0jalI9n+VdfnWRXMA8GyywU10HzMTW+VJoyHs4tCwsAzFCP2x10jgHU27TXo1+RNcDtxxRv3C69Tc+7rv4gUBtC6XXOSW2iZ0IAvFaHD5fs/q4Xmsv3o6JwyTNQuObNTnLnciw1+7xTbODDN4r3ABMvPaTrdM4qTtyoDRdVO7njIANncaRh5HqKfuRciVXPaZvToskAhNc1zHCExSaJ63wj8nDT+wqYC3FL7r+LUaQdXSov2";

        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        // pe obiectul recuonscut o sa apara sistemul de axe cu coordonate

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        // construiesc obiectul vuforia (camera) cu parametrii anteriori

        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);
        //numarul maxim de tinte recunoscute simultan

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        //incarc tintele de sub beacon-uri

        /* @vezi
        https://firstinspiresst01.blob.core.windows.net/ftc/gears.pdf
        https://firstinspiresst01.blob.core.windows.net/ftc/legos.pdf
        https://firstinspiresst01.blob.core.windows.net/ftc/tools.pdf
        https://firstinspiresst01.blob.core.windows.net/ftc/wheels.pdf

        btw, astea ar trebui printate color
         */

        beacons.get(0).setName("Wheels");
        // @vezi FtcRobotController/assets/FTC_2016-17.xml

        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Legos");
        beacons.get(3).setName("Gears");

        waitForStart();

        beacons.activate();
        // incep sa caut tintele

        while(opModeIsActive()){

            for(VuforiaTrackable beac: beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
                // asta ar trebui sa returneze pozitia tintei

                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + " translation", translation);

                    double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(0), translation.get(2)));
                    //daca telefonul este vertical, primul argument ar trebui sa fie translation.get(1)

                    telemetry.addData(beac.getName() + " degrees", degreesToTurn);


                }

            }

            telemetry.update();

        }




    }
}
