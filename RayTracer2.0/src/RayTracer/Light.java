/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author thiago
 */
public class Light {
    
    //hee
    private Vec3 lightColor;
    private Vec3 lightPosition;
    
    public Light(Vec3 lightPosition, Vec3 lightColor){
        this.lightColor = lightColor;
        this.lightPosition = lightPosition;
    }
    
    public Vec3 getColor(){
        return lightColor;
    }
    
    public Vec3 getPosition(){
        return lightPosition;
    }
    
}
