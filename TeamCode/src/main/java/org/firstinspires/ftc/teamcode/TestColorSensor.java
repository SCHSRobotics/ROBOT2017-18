package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
import org.firstinspires.ftc.teamcode.drivers.ServoFlapDriver1712;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

@TeleOp(name="testColorSensor", group="2017")
public class TestColorSensor extends LinearOpMode {
    private MovementDriver drive;
    private DcMotor gun;

    Servo rightgrabber;
    Servo leftgrabber;
    static final float FORWARD_SPEED = -0.6f;
    private ElapsedTime     runtime = new ElapsedTime();
    public Telemetry telemetry = new TelemetryImpl(this);
    ServoFlapDriver1712 flaps;
    org.firstinspires.ftc.teamcode.ModernRoboticsI2cColorSensor2 colorx;
    public void runOpMode(){

        try {
            //leftgrabber=hardwareMap.servo.get("leftgrab");
            //rightgrabber=hardwareMap.servo.get("rightgrab");
            //leftgrabber.setDirection(Servo.Direction.REVERSE);
            //rightgrabber.setDirection(Servo.Direction.FORWARD);

            //leftgrabber.setPosition(.5);
            //rightgrabber.setPosition(.5);


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
        flaps.flapLeftDown();
        flaps.flapRightDown();
        while (opModeIsActive()) {

            sleep(2000);
            //TODO: read color
            float[] color = flaps.readRight(telemetry);

            telemetry.addData("R0", "%s Color R 0 ", color[0]);
            telemetry.addData("R1", "%s Color R 1", color[1]);
            telemetry.update();
            sleep(2000);
            float[] color2 = flaps.readRightAlt();

            telemetry.addData("R0 alt", "%s Color2 ", color2[0]);
            telemetry.addData("R1 alt", "%s Color2 ", color2[1]);
            telemetry.update();
            sleep(2000);
            if (color[0] > color[1]) { //red
                telemetry.addData("RED", "RED");
                telemetry.update();
            }
            if (color[1] > color[0]) { //blue
                telemetry.addData("BlUE", "BLUE");
                telemetry.update();
            }

            //test 3
            //I2cDevice colori2c = hardwareMap.i2cDevice.get("lc");
            //colorx = new org.firstinspires.ftc.teamcode.ModernRoboticsI2cColorSensor2(colori2c.getI2cController(),colori2c.getPort());
            //int cnumber = colorx.colorNumber();
            //telemetry.addData("Colornumber: ",cnumber);
            //telemetry.update();
            //sleep(1000);
        }
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

