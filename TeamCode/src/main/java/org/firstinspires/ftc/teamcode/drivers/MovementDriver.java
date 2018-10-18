package org.firstinspires.ftc.teamcode.drivers;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// MovementDriver 1.0.1
// NOT backwards compatible

// TODO: Fix targetRotation()
public class MovementDriver extends Driver {
    public DcMotor fl=null;
    public DcMotor fr=null;
    public DcMotor bl=null;
    public DcMotor br=null;
    private float speed=0;
    private float rotspeed=0;
    public boolean knowsLocation=false;
    public boolean started = false;
    long lstime;
    float fpms=0;
    float dpms=0;
    public boolean reachedTarget=false;
    boolean um=false;
    private boolean fourwheeldrive=false;
    OrientationPoint location;
    OrientationPoint target;
    public void init(DcMotor[] l, boolean useMath) {
        fl = l[0];
        fr = l[1];
        if (l.length > 2) {
            bl = l[2];
            br = l[3];
            fourwheeldrive=true;
        }
        um=useMath;
    }
    public void update() {
        reachedTarget = false;
        //calculatePosition();
        if (!knowsLocation) {
            if (!started) {
                setSpeed(0);
                location.x -= 5;
                location.y = location.x;
                knowsLocation = true;
                started=true;
            }
        }
        if (round(Math.atan2(location.y - target.y, location.x - target.x) * 57.2958f, 1) >= Math.round(360 % (180 + location.rotation))) {
            setRotspeed(-0.4f);
        } else if (round(Math.atan2(location.y - target.y, location.x - target.x) * 57.2958f, 1) < Math.round(360 % (180 + location.rotation))) {
            setRotspeed(0.4f);
        } else
            setRotspeed(0);
        if (round(location.distance(target), 1) > 0){
            setSpeed(1);
        }else if(round(location.distance(target), 1) < 0) {
            setSpeed(-1);
        }
        setSpeed(0);
        reachedTarget=true;
    }
    private void calculatePosition(){
        if(!um)
            return;
        long time=System.currentTimeMillis()-lstime;
        lstime=System.currentTimeMillis();
        float distanceMoved=time*speed*fpms;
        location.x+=(Math.sin(torad(location.rotation))*distanceMoved);
        location.y+=distanceMoved*Math.cos(location.rotation);
        location.rotation+=rotspeed*dpms;
    }
    public void setRotspeed(float s){
        //calculatePosition();
        float r=(s-rotspeed)/2;
        float l=(rotspeed+s)/2;
        fl.setPower(-l);
        fr.setPower(-r);
        if(fourwheeldrive) {
            bl.setPower(-l);
            br.setPower(-r);
        }
        speed=s;
    }
    public void setSpeed(float s){
        //calculatePosition();
        float r=(speed-s)/2;
        float l=(speed+s)/2;
        fl.setPower(-l);
        fr.setPower(-r);
        if(fourwheeldrive) {
            bl.setPower(-l);
            br.setPower(-r);
        }
        rotspeed=s;
    }
    private float torad(float x){
        return x/57.2958f;
    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    public double rotationLoc(){
        return (fl.getCurrentPosition()+bl.getCurrentPosition())/2;
    }
    public void setTargetRotation(double t){
        double tx=t/100;
        //fl.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        fl.setPower(-tx);
        //fr.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        fr.setPower(tx);
        if(fourwheeldrive) {
            //bl.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            bl.setPower(-tx);
            //br.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            br.setPower(tx);
        }
        rotspeed= (float) tx;
        speed=0;
    }
}
