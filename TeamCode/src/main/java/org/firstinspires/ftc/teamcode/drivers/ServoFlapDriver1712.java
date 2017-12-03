package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Kyle on 12/3/2017.
 */

public class ServoFlapDriver1712 {
    Servo rarm;
    Servo larm;
    public ServoFlapDriver1712(Servo left, Servo right){
        rarm=right;
        larm=left;
        flapLeftUp();
        flapRightUp();
    }
    public void flapLeftDown(){
        larm.setDirection(Servo.Direction.REVERSE);
        larm.setPosition(-.1);
    }
    public void flapRightDown(){
        rarm.setDirection(Servo.Direction.FORWARD);
        rarm.setPosition(0);
    }
    public void flapLeftUp(){
        larm.setDirection(Servo.Direction.FORWARD);
        larm.setPosition(.5);
    }
    public void flapRightUp(){
        rarm.setDirection(Servo.Direction.REVERSE);
        rarm.setPosition(0.5);
    }
}
