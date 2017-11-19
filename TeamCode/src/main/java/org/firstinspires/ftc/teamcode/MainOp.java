package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.*;

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
    Servo rl;
    Servo ll;
    Servo rr;
    Servo lr;
    public Telemetry telemetry = new TelemetryImpl(this);
    public void runOpMode(){
        try {
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")}; //this is our rear wheel motors.
            drive=new MovementDriver();
            drive.telemetry = telemetry;
            drive.init(tmp, false);
            lift=hardwareMap.dcMotor.get("lift");
            //DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr")};
            //drive=new MovementDriver();
            //drive.telemetry = telemetry;
            //drive.init(tmp, false);
            //gun=hardwareMap.dcMotor.get("g");
            //lift=hardwareMap.dcMotor.get("lift");
            //clct=hardwareMap.dcMotor.get("clct");
            //ll=hardwareMap.servo.get("ll");
            //rl=hardwareMap.servo.get("rl");
            //lr=hardwareMap.servo.get("lr");
            //rr=hardwareMap.servo.get("rr");
            //rl.setPosition(0);
            //ll.setPosition(1);
            //rr.setPosition(0);
            //lr.setPosition(1);
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //rl.setPosition(0);
        //ll.setPosition(1);
        while (opModeIsActive()) {
            telemetry.addData("Main op", "%s stick x", gamepad1.right_stick_y);
            telemetry.update();
            drive.setRotspeed(gamepad1.left_stick_x);

            drive.setSpeed(-gamepad1.right_stick_y);
            //if(gamepad2.right_bumper)//open
                //flapright.setPosition(0.7);
            //if(gamepad1.left_trigger>0.2) {//close
            //    rr.setPosition(1);
            //    lr.setPosition(0);
            //}else{
            //    rr.setPosition(0);
            //    lr.setPosition(1);
            //}
            //if(gamepad1.right_trigger>0.2) {//close
            //    rl.setPosition(1);
            //    ll.setPosition(0);
            //}else{
            //    rl.setPosition(0.5);
            //    ll.setPosition(0.5);
            //}
            if(gamepad2.right_trigger>0.2) {//close
                lift.setPower(-.5);
                //clct.setPower(1);
            }else{
                lift.setPower(0);
                //clct.setPower(0);
            }
            if(gamepad2.left_trigger>0.2) {//close
                lift.setPower(.5);
                //clct.setPower(1);
            }else{
                lift.setPower(0);
                //clct.setPower(0);
            }
            /*
            gun.setPower(Math.abs(gamepad2.left_stick_y)-Math.abs(gamepad2.right_stick_y));
            //  gun.Fire();
            /*if(gamepad2.left_bumper)//open
                flapleft.setPosition(0.25);
            if(gamepad2.left_trigger>0.2)//close
                flapleft.setPosition(0.82);
            if (gamepad2.b)
                armup.setPower(0.5);
            else if (gamepad2.y)
                armup.setPower(-0.5);
            else
                armup.setPower(0);
            if(gamepad2.x)
                dropper.setPosition(0.99f);
            if(gamepad1.dpad_down)
                drive.sluff();
            if(gamepad1.dpad_up)
                drive.nosluff();*/

        }
    }
    void moveArm0(float value){
        float power=0.1f;

    }
    private void manwait(long i, long end, long end2) {
        long start=System.currentTimeMillis();
        while(opModeIsActive()&&start+i>System.currentTimeMillis()&&end>System.currentTimeMillis()&&end2>System.currentTimeMillis());
    }
}

