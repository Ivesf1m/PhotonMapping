/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author Moura
 */
public class Sphere extends Primitive{
    private Vec3 center;
    private double radius;
    
    public Sphere(Vec3 center, double radius, Material m){
        super(m);
        this.center = center;
        this.radius = radius;
    }
    
    public boolean shadowHit(Ray r){
        return true;
    }
    
    public double hit(Ray r){
        
        double xo = r.getOrigin().getX();
        double yo = r.getOrigin().getY();
        double zo = r.getOrigin().getZ();
        
        double xd = r.getDirection().getX();
        double yd = r.getDirection().getY();
        double zd = r.getDirection().getZ();
        
        double xc = center.getX();
        double yc = center.getY();
        double zc = center.getZ();
        
        double a = xd*xd + yd*yd + zd*zd;
        double b = 2.0*( xd*(xo-xc) + yd*(yo-yc) + zd*(zo-zc) );
        double c = (xo-xc)*(xo-xc) + (yo-yc)*(yo-yc) + (zo-zc)*(zo-zc) - radius*radius;
        
        double delta = b*b - 4.0*a*c;
        
        double result;
        
        //if(delta >= 0 ) System.out.println("Delta =  " + delta);
        if(delta < 0) return infinity;
        else{
            
            double centerDist = Math.sqrt(Math.pow(r.getOrigin().getX() - center.getX(), 2)
                        + Math.pow(r.getOrigin().getY() - center.getY(), 2)
                        + Math.pow(r.getOrigin().getZ() - center.getZ(), 2));
             
            double tdot = (-b + Math.sqrt(delta)) / 2.0*a;
            double tdotdot = (-b - Math.sqrt(delta))/2.0*a;
            
            double result2;
            
            if(tdot < tdotdot){
                result = tdot;
                result2 = tdotdot;
            }else{
                result = tdotdot;
                result2 = tdot;
            }
            
            if(centerDist > radius){
            
                if(tdot < tdotdot) result = tdot;
                else result = tdotdot;
                
            }else{
                
                if(tdot > tdotdot) result = tdot;
                else result = tdotdot;
                
            }
            
        }
        
        return result;

    }
    
    public Vec3 getNormal(Vec3 point) {
        Vec3 n =  point.subtract(center);
        n.normalize();
        return n;
    }
    
    public boolean isInside(Ray r){
        double centerDist = Math.sqrt(Math.pow(r.getOrigin().getX() - center.getX(), 2)
                + Math.pow(r.getOrigin().getY() - center.getY(), 2)
                + Math.pow(r.getOrigin().getZ() - center.getZ(), 2));
        
       //System.out.println("CenterDist = " + centerDist + " Radius = " + radius);
        
        return centerDist < radius;
        
    }
    
}
