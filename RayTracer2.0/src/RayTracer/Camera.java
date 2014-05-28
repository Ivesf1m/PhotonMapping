/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author Moura
 */
public class Camera {
    private OrthoBasis camBasis;
    private Vec3 viewPoint;
    private Vec3 viewDirection;
    private Vec3 upVector;
    private double fovy;
    private int[] resolution;
    private Vec3 xInc, yInc;
    
    public Camera(Vec3 position, Vec3 direction, Vec3 upVector, double fovy, int[] resolution){
        viewPoint = position;
        viewDirection = direction;
        this.upVector = upVector;
        this.fovy = fovy;
        this.resolution = resolution;
        camBasis = new OrthoBasis();        
        Vec3 w = viewDirection;//.subtract(viewPoint);
        camBasis.initFromWV(w/*.negative()*/, upVector);
        double aux = 2.0 * Math.tan(fovy / 2.0) / ((double)resolution[0]);
        System.out.println(aux);
        xInc = camBasis.getU().multiply(aux);
        yInc = camBasis.getV().multiply(aux);
    }
    
    public Ray createRay(double i, double j){
        double aux1 = j + 0.5 - resolution[1] / 2.0;
        double aux2 = i + 0.5 - resolution[0] / 2.0;
        Vec3 mxinc = xInc.multiply(aux2);
        Vec3 myinc = yInc.multiply(aux1);
        Vec3 dir = camBasis.getW().add(myinc.add(mxinc));
        dir.normalize();
        Ray r = new Ray(viewPoint, dir);
        return r;
    }
    
    public Ray createOrthoRay(double i, double j)
    {
        Vec3 origin = new Vec3(i, j, 0);
        return new Ray(origin, viewDirection);
    }
    
    public String toString(){
        String s1 = "Origin: " + viewPoint.toString();
        String s2 = "Vetor u: " + camBasis.getU().toString();
        String s3 = "Vetor v: " + camBasis.getV().toString();
        String s4 = "Vetor w: " + camBasis.getW().toString();
        String s5 = "Vetor up: " + upVector.toString();
        String s6 = "Fovy: " + fovy + "\n";
        String s7 = "xInc: " + xInc.toString();
        String s8 = "yInc: " + yInc.toString();
        return s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;        
    }
}
