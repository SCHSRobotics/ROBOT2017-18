package org.firstinspires.ftc.teamcode.drivers;

/**
 * Created by buric_000 on 11/6/2015.
 */
public class OrientationPoint {
    public float x,y,rotation=0;
    public float distance(OrientationPoint p){
        return (float)Math.sqrt(Math.pow(p.x-x,2)+Math.pow(p.y-y,2));
    }
    public OrientationPoint(float x0, float y0){
        x=x0;
        y=y0;
    }
    public OrientationPoint(float x0, float y0, float rot){
        x=x0;
        y=y0;
        rotation=rot;
    }
}
