package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.*;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

@TeleOp(name="Main Op", group="2017")
public class MainOp extends LinearOpMode {
    private MovementDriver drive;

    private DcMotor gun;
    DcMotor lift;
    DcMotor clct;
    Servo rarm;
    Servo larm;
    Servo rightgrabber;
    Servo leftgrabber;
    ServoFlapDriver1712 flaps;
    public Telemetry telemetry = new TelemetryImpl(this);
    public void runOpMode(){
        try {
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")}; //this is our rear wheel motors.
            drive=new MovementDriver();
            drive.init(tmp, false);
            lift=hardwareMap.dcMotor.get("grabber");
            leftgrabber=hardwareMap.servo.get("leftgrab");
            rightgrabber=hardwareMap.servo.get("rightgrab");
            leftgrabber.setDirection(Servo.Direction.REVERSE);
            rightgrabber.setDirection(Servo.Direction.FORWARD);

            leftgrabber.setPosition(.5);
            rightgrabber.setPosition(.5);
            flaps=new ServoFlapDriver1712(hardwareMap.servo.get("larm"),hardwareMap.servo.get("rarm"),(ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("rc"),(ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("lc"));
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
            telemetry.addData("Main op", "%s error", e.toString());
            telemetry.update();

        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //rl.setPosition(0);
        //ll.setPosition(1);
        while (opModeIsActive()) {
            //telemetry.addData("Main op", "%s stick x", gamepad1.right_stick_y);
            //telemetry.update();
            drive.setRotspeed(gamepad1.left_stick_x);
            drive.setSpeed(gamepad1.right_stick_y);
            telemetry.addData("Main op", "%s forward power", gamepad1.right_stick_y);
            telemetry.addData("Main op", "%s rotation power", gamepad1.left_stick_y);
            telemetry.update();
            if(gamepad2.a){

                leftgrabber.setDirection(Servo.Direction.FORWARD);
                rightgrabber.setDirection(Servo.Direction.REVERSE);

                leftgrabber.setPosition(0);
                rightgrabber.setPosition(0);
                telemetry.addData("Main op", "%s arm pos", leftgrabber.getPosition());
                telemetry.addData("Main op", "%s arm pos", leftgrabber.getDirection());
                telemetry.addData("Main op", "%s arm pos", rightgrabber.getPosition());
                telemetry.addData("Main op", "%s arm pos", rightgrabber.getDirection());

                telemetry.update();

            }
            if (gamepad2.b) {

                leftgrabber.setDirection(Servo.Direction.REVERSE);
                rightgrabber.setDirection(Servo.Direction.FORWARD);

                leftgrabber.setPosition(.7);
                rightgrabber.setPosition(.7);
                telemetry.addData("Main op", "%s arm pos", leftgrabber.getPosition());
                telemetry.addData("Main op", "%s arm pos", leftgrabber.getDirection());
                telemetry.addData("Main op", "%s arm pos", rightgrabber.getPosition());
                telemetry.addData("Main op", "%s arm pos", rightgrabber.getDirection());

                telemetry.update();

            }

            if(gamepad1.a) {
                flaps.flapLeftDown();
            }
            if(gamepad1.b) {
                flaps.flapLeftUp();
            }
            if(gamepad1.x) {
                flaps.flapRightDown();
            }
            if(gamepad1.y) {
                flaps.flapRightUp();
            }
            if(gamepad1.right_bumper){
                float[] color = flaps.readRight(telemetry);
                telemetry.addData("R0", "%s Color R 0", color[0]);
                telemetry.addData("R1", "%s Color R 1", color[1]);
                telemetry.update();
            }
            if(gamepad2.right_trigger>0.2) {//close
                lift.setPower(-.8);
                //clct.setPower(1);
            }else{
                lift.setPower(0);
                //clct.setPower(0);
            }
            if(gamepad2.left_trigger>0.2) {//close
                lift.setPower(.8);
                //clct.setPower(1);
            }else{
                lift.setPower(0);
                //clct.setPower(0);
            }

        }
    }
}

