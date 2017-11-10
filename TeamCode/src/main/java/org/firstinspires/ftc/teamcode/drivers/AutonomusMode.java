package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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
public class AutonomusMode extends LinearOpMode {
    private MovementDriver drive;
    private DcMotor gun;
    DcMotor lift;
    DcMotor clct;
    Servo rl;
    Servo ll;
    Servo rr;
    Servo lr;
    static final float FORWARD_SPEED = -0.6f;
    private ElapsedTime     runtime = new ElapsedTime();
    public Telemetry telemetry = new TelemetryImpl(this);
    public void runOpMode(){
        boolean imageFound = false;
        try {

             //   case RelicRecoveryVuMark.LEFT:
             //       telemetry.addData("Picturereader", "%s visible", "Center");
             //       break;
             //   case RelicRecoveryVuMark.RIGHT:
            //        telemetry.addData("Picturereader", "%s visible", "Center");
             //       break;
            //}
            /**DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr")};
            drive=new MovementDriver();
            drive.init(tmp, false);
            gun=hardwareMap.dcMotor.get("g");
            lift=hardwareMap.dcMotor.get("lift");
            clct=hardwareMap.dcMotor.get("clct");
            ll=hardwareMap.servo.get("ll");
            rl=hardwareMap.servo.get("rl");
            lr=hardwareMap.servo.get("lr");
            rr=hardwareMap.servo.get("rr");
            rl.setPosition(0);
            ll.setPosition(1);
            rr.setPosition(0);
            lr.setPosition(1);
             */
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();

        }
        ConceptVuMarkIdentification pictureReader = new ConceptVuMarkIdentification();
        pictureReader.initializeCamera(hardwareMap);
        sleep(5000);
        while (opModeIsActive()&& !imageFound) {

            RelicRecoveryVuMark relicMark = pictureReader.readImage();
            if(relicMark == RelicRecoveryVuMark.CENTER){

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                //imageFound = true;
                //sleep(10000);
            }
            else if(relicMark == RelicRecoveryVuMark.RIGHT){

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                //imageFound = true;
                //sleep(10000);
            }
            else if(relicMark == RelicRecoveryVuMark.LEFT){

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                //imageFound = true;
                //sleep(10000);
            }
            else if(relicMark == RelicRecoveryVuMark.UNKNOWN){

                telemetry.addData("Picturereader", "%s visible", relicMark);
                telemetry.update();
                //imageFound = true;
                //sleep(10000);
            }
            else{
                telemetry.addData("Picturereader", "NOTHING VISABLE", relicMark);
                telemetry.update();
            }
        }
        //rl.setPosition(0.5);
        //ll.setPosition(1);
       //sleep(2000);
        //drive.setSpeed(FORWARD_SPEED);
        //runtime.reset();
        //while (opModeIsActive() && (runtime.seconds() < 2)) {
         //   telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
        //    telemetry.update();

        //}
       // drive.setSpeed(0);

        //while (opModeIsActive() && (runtime.seconds() < 8.0)) {
        //    System.out.println("Firing mode");
        //    gun.setPower(1);
        //    sleep(400);
        //    gun.setPower(0);
       //     sleep(100);
        //}
        //gun.setPower(0);
        //sleep(100);






    }
    void moveArm0(float value){
        float power=0.1f;

    }
    private void manwait(long i, long end, long end2) {
        long start=System.currentTimeMillis();
        while(opModeIsActive()&&start+i>System.currentTimeMillis()&&end>System.currentTimeMillis()&&end2>System.currentTimeMillis());
    }
}

