package org.firstinspires.ftc.teamcode.legacy.PKG1718;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
import org.firstinspires.ftc.teamcode.drivers.ServoFlapDriver1712;

/**
 * Created by dylanbrophy on 12/3/2017.
 */
//@TeleOp(name="Auto Op 0x03FF", group="2017")
public class AutoOp extends LinearOpMode {
    private MovementDriver drive;
    private ServoFlapDriver1712 flaps;
    static final float FORWARD_SPEED = -0.6f;
    private ElapsedTime runtime = new ElapsedTime();
    public void runOpMode(){
        try {
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
            drive=new MovementDriver();
            drive.init(tmp, false);
            flaps=new ServoFlapDriver1712(hardwareMap.servo.get("larm"),hardwareMap.servo.get("rarm"));
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();

        }
        sleep(2000);
        drive.setSpeed(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }
        drive.setSpeed(0);

        while (opModeIsActive() && (runtime.seconds() < 8.0)) {
            System.out.println("Firing mode");
            //gun.setPower(1);
            sleep(400);
            //gun.setPower(0);
            sleep(100);
        }
        flaps.flapLeftDown();
        flaps.flapRightDown();

        //TODO: read colors
        //TODO: detect correct sensor

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
    private void manwait(long i, long end, long end2) {
        long start=System.currentTimeMillis();
        while(opModeIsActive()&&start+i>System.currentTimeMillis()&&end>System.currentTimeMillis()&&end2>System.currentTimeMillis());
    }
}
