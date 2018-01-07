package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
import org.firstinspires.ftc.teamcode.drivers.PictureReader;
import org.firstinspires.ftc.teamcode.drivers.ServoFlapDriver1712;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

@TeleOp(name="R1Auto", group="2017")
public class R1Auto extends LinearOpMode {
    private MovementDriver drive;
    private DcMotor gun;

    Servo rightgrabber;
    Servo leftgrabber;
    static final float FORWARD_SPEED = -0.6f;
    private ElapsedTime     runtime = new ElapsedTime();
    public Telemetry telemetry = new TelemetryImpl(this);
    ServoFlapDriver1712 flaps;

    public void runOpMode(){

        try {
            leftgrabber=hardwareMap.servo.get("leftgrab");
            rightgrabber=hardwareMap.servo.get("rightgrab");
            leftgrabber.setDirection(Servo.Direction.REVERSE);
            rightgrabber.setDirection(Servo.Direction.FORWARD);

            leftgrabber.setPosition(.5);
            rightgrabber.setPosition(.5);
            flaps = new ServoFlapDriver1712(hardwareMap.servo.get("larm"), hardwareMap.servo.get("rarm"), (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("rightcolor"), (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("leftcolor"));
            flaps.flapRightUp();
            flaps.flapLeftUp();
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")}; //this is our rear wheel motors.
            drive=new MovementDriver();
            drive.init(tmp, false);


        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();
        }

        boolean imageFound = false;

        while (opModeIsActive()) {

//
            flaps = new ServoFlapDriver1712(hardwareMap.servo.get("larm"), hardwareMap.servo.get("rarm"), (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("rc"), (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("lc"));
//            PictureReader pr = new PictureReader();
//
//            for (int i = 0; i> 20; i++) {
//                String pictureType = pr.readPicture(telemetry, hardwareMap);
//                telemetry.addData("Q", "%s picture", pictureType);
//
//                telemetry.update();
//                if(pictureType != RelicRecoveryVuMark.UNKNOWN.toString()){
//                    break;
//                }
//            }
            sleep(2000);
            //TODO: Lower Right arm

            flaps.flapRightDown();

            //TODO: read color
            float[] color = flaps.readRight(telemetry);


            telemetry.addData("R0", "%s Color R 0", color[0]);


            telemetry.addData("R1", "%s Color R 1", color[1]);
            telemetry.update();


            if(color[0] > 0){
                drive.setSpeed(.2f);
                sleep(200);
                drive.setSpeed(0);
                drive.setSpeed(-.2f);
                sleep(200);
                drive.setSpeed(0);

            }
            if(color[1] > 0){
                drive.setSpeed(-.2f);
                sleep(200);
                drive.setSpeed(0);
                drive.setSpeed(.2f);
                sleep(200);
                drive.setSpeed(0);

            }
            /*color = flaps.readLeft(telemetry);


            telemetry.addData("R1", "%s Color L0", color[0]);
            telemetry.update();
            sleep(2000);
            telemetry.addData("R1", "%s Color L1", color[1]);
            telemetry.update();
            sleep(2000);
            telemetry.addData("R1", "%s Color L2", color[2]);
            telemetry.update();
            sleep(2000);
            telemetry.addData("R1", "%s Color L3", color[4]);
            telemetry.update();
            sleep(2000);

            //telemetry.addData("L1", "%s Color", color.toString());
            //telemetry.addData("Q", "%s V", flaps.getValue());
*/
            /*telemetry.update();
            sleep(2000);

            int test = flaps.getValue();
            telemetry.addData("R1", "%s Color value", test);
            telemetry.update();
*/
            //flaps.flapRightUp();
            //TODO: if red rotate Couterclockwise
            //TODO: if blue rotate clockwise
            //TODO: Raise arm
            //TODO: Recenter
            //TODO: move forward to P(left), P(center), P(right)
            //TODO: rotate clockwise
            //TODO: drive fowards
            //TODO: realease arm
            //TODO: drive backwards
            //TODO: close arm
            //TODO: drive forward
            //          }

        }
        flaps.flapRightUp();
        //pull out to its own class







    }
    void moveArm0(float value){
        float power=0.1f;

    }
    private void manwait(long i, long end, long end2) {
        long start=System.currentTimeMillis();
        while(opModeIsActive()&&start+i>System.currentTimeMillis()&&end>System.currentTimeMillis()&&end2>System.currentTimeMillis());
    }
}

