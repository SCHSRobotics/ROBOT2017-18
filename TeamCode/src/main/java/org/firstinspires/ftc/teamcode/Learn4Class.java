package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.MovementDriver;

@TeleOp(name="Learn!!", group="2018")
public class Learn4Class {
    public void runOpMode(){
        try {
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")}; //this is our rear wheel motors.
            drive=new MovementDriver();

}
