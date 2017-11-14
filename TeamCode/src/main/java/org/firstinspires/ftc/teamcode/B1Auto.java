package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
import org.firstinspires.ftc.teamcode.drivers.PictureReader;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

@TeleOp(name="B1Auto", group="2017")
public class B1Auto extends LinearOpMode {
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
            PictureReader pr = new PictureReader();
            while (opModeIsActive()){
                String pictureType = pr.readPicture(telemetry, hardwareMap);

                //TODO: Lower Left arm
                //TODO: read color
                //TODO: if red rotate Couterclockwise
                //TODO: if blue rotate clockwise
                //TODO: Raise arm
                //TODO: Recenter
                //TODO: move forward to P(left), P(center), P(right)
                //TODO: rotate Counter Clockwise
                //TODO: drive fowards
                //TODO: realease arm
                //TODO: drive backwards
                //TODO: close arm
                //TODO: drive forward
            }
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();

        }

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

