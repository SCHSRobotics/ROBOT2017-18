package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// Firing Mechanism 1.0.0

public class FiringMechanism extends Driver{
    DcMotor a;
    Servo q;
    double time;
    int state=0;
    boolean use_opt=true;
    public FiringMechanism(DcMotor aa,Servo qq,boolean uo){
        a=aa;
        q=qq;
        use_opt=uo;
        if(!uo){
            a.setPower(1);
        }
    }
    public FiringMechanism(DcMotor aa,Servo qq){
        a=aa;
        q=qq;
        use_opt=true;
    }
    public void Fire(){
        if(use_opt) {
            a.setPower(1);
        }
        time=System.currentTimeMillis();
        state=1;
    }

    @Override
    public void update() {
        if(state==1&&(time+1500<System.currentTimeMillis()||!use_opt)){
            state=2;
            time=System.currentTimeMillis();
            q.setDirection(Servo.Direction.FORWARD);
        }else if(state==2&&time+500<System.currentTimeMillis()){
            q.setDirection(Servo.Direction.REVERSE);
            state=0;
            a.setPower(0);
        }
    }
}
