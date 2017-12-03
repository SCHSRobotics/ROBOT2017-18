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

    }
}
