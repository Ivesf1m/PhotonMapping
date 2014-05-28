package RayTracer;

public class Ray {
    private Vec3 origin;
    private Vec3 direction;
    private double currentLambda;
    
    public Ray(){
        origin = new Vec3();
        direction = new Vec3();
        direction.normalize();
    }
    
    public Ray(Vec3 o, Vec3 d){
        origin = o;
        direction = d;
        d.normalize();
    }
    
    public Ray(Ray r){
        this.origin = r.getOrigin();
        this.direction = r.getDirection();
        currentLambda = 1.0;
    }
    
    public double getCurrentLambda() {
        return currentLambda;
    }

    public void setCurrentLambda(double currentLambda) {
        this.currentLambda = currentLambda;
    }
    
    public Vec3 getOrigin(){
        return origin;
    }
    
    public Vec3 getDirection(){
        return direction;
    }
    
    public Vec3 getPointAtParameter(double t){
        Vec3 aux = direction.multiply(t);
        return origin.add(aux);
    }
    
    public String toString(){
        return ("Origem: " + origin.toString() + "\nDireção: " + direction.toString());
    }
}
