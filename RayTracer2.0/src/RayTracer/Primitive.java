/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author thiago
 */
public abstract class Primitive {
    
    protected Material m;
    
    protected static final double infinity = Double.MAX_VALUE;
    
    public Primitive(Material m){
        this.m = m;
    }
    
    public Material getMaterial(){
        return m;
    }
    
    public void setMaterial(Material m){
        this.m = m;
    }
    
    public abstract double hit(Ray r);
    public abstract boolean shadowHit(Ray r);
    public abstract Vec3 getNormal(Vec3 point);
    public abstract boolean isInside(Ray r);
    
}
