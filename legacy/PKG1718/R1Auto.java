package org.firstinspires.ftc.teamcode.legacy.PKG1718;

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

//@TeleOp(name="R1Auto", group="2017")
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

            flaps = new ServoFlapDriver1712(hardwareMap.servo.get("larm"), hardwareMap.servo.get("rarm"), (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("rc"), (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("lc"));
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

        //while (opModeIsActive()) {

//
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
            //TODO: Lower Right arm
        flaps.flapRightDown();
        sleep(2000);

        //TODO: read color
        float[] color = flaps.readRight(telemetry);
        int value = Float.compare(color[0],color[1]);
        if(value < 0){ //blue
            telemetry.addData("BLUE ", "BLUE");
            telemetry.update();
            drive.setSpeed(.2f);
            sleep(400);
            drive.setSpeed(0);
            flaps.flapRightUp();
            sleep(750);
            drive.setSpeed(-.2f);
            sleep(400);
            drive.setSpeed(-.7f);
            sleep(1370);
        }
        if(value >0){ //red
            telemetry.addData("RED", "RED");
            drive.setSpeed(-.15f);
            sleep(500);
            drive.setSpeed(0);
            flaps.flapRightUp();
            drive.setSpeed(-.7f);
            sleep(1300);
        }
        drive.setSpeed(0);

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

        while(this.opModeIsActive());
        }
        //pull out to its own class







    //}
    void moveArm0(float value){
        float power=0.1f;

    }
    private void manwait(long i, long end, long end2) {
        long start=System.currentTimeMillis();
        while(opModeIsActive()&&start+i>System.currentTimeMillis()&&end>System.currentTimeMillis()&&end2>System.currentTimeMillis());
    }
}

