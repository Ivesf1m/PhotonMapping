/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author thiago
 */
public class Plane extends Primitive{
    
    private Vec3 normal;
    private Vec3 point;
    
    public Plane(Vec3 normal, Vec3 point, Material m){
        super(m);
        this.normal = normal;
        this.point = point;
    }
    
    public boolean shadowHit(Ray r){
        return true;
    }
    
    public double hit(Ray r){
        
        double nDotd = normal.dot(r.getDirection());
        if(Math.abs(nDotd) < Vec3.epsilon) return infinity;
        double result = -( (normal.dot(r.getOrigin().subtract(point)))/nDotd);
        if(result < 0) return infinity;
        else return result;
        
    }

    public Vec3 getNormal(Vec3 point) {
        Vec3 n = normal;
        n.normalize();
        return n;
    }
    
    public boolean isInside(Ray r){
        return false;
    }
    
}
