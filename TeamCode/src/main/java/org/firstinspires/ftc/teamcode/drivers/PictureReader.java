package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

@TeleOp(name="Autotomomus Picture Reader", group="2017")
public class PictureReader {
    private MovementDriver drive;
    private DcMotor gun;
    DcMotor lift;
    DcMotor clct;
    Servo rl;
    Servo ll;
    Servo rr;
    Servo lr;
    static final float FORWARD_SPEED = -0.6f;
    private ElapsedTime runtime = new ElapsedTime();

    public String readPicture(Telemetry telemetry, HardwareMap hardwareMap) {
        boolean imageFound = false;
        ConceptVuMarkIdentification pictureReader = new ConceptVuMarkIdentification();
        pictureReader.initializeCamera(hardwareMap);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        while (!imageFound) {

            RelicRecoveryVuMark relicMark = pictureReader.readImage();
            if (relicMark == RelicRecoveryVuMark.CENTER) {

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                return "CENTER";
            } else if (relicMark == RelicRecoveryVuMark.RIGHT) {

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                return "RIGHT";

            } else if (relicMark == RelicRecoveryVuMark.LEFT) {

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                return "LEFT";
            } else if (relicMark == RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
            } else {
                telemetry.addData("Picturereader", "NOTHING VISABLE", relicMark);
                telemetry.update();
            }
        }
        return "UNKNOWN";
    }
}


