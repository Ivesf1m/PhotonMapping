package RayTracer;


public class Vec3 {
    private double v[];
    public static final double epsilon = 0.00001; 
    
    public Vec3(){
        v = new double[3];
        v[0] = v[1] = v[2] = 0.0;
    }
    
    public Vec3(double x, double y, double z){
        v = new double[3];
        v[0] = x;
        v[1] = y;
        v[2] = z;
    }
    
    public Vec3(Vec3 newVec){
        v = new double[3];
        this.assign(newVec);
    }
    
    public Vec3 add(Vec3 v2){
        return new Vec3(v[0] + v2.getX(), v[1] + v2.getY(), v[2] + v2.getZ());
    }
    
    public void assign(Vec3 v2){
        v[0] = v2.getX();
        v[1] = v2.getY();
        v[2] = v2.getZ();
    }
    
    public Vec3 cross(Vec3 v2){
        double x = v[1] * v2.getZ() - v[2] * v2.getY();
        double y = v[2] * v2.getX() - v[0] * v2.getZ();
        double z = v[0] * v2.getY() - v[1] * v2.getX();
        Vec3 result = new Vec3(x, y, z);
        return result;
    }
    
    public boolean differs(Vec3 v2){
        return !(this.equals(v2));
    }
    
    public double distance(Vec3 v2){
        double dist;
        double dx = this.v[0] - v2.v[0];
        double dy = this.v[1] - v2.v[1];
        double dz = this.v[2] - v2.v[2];
        dist = Math.sqrt(dx*dx + dy*dy + dz*dz);
        return dist;
    }
    
    public void divide(double scalar){
        v[0] /= scalar;
        v[1] /= scalar;
        v[2] /= scalar;
    }
    
    public double dot(Vec3 v2){
        return (v[0] * v2.getX() + v[1] * v2.getY() + v[2] * v2.getZ());
    }
    
    public boolean equals(Vec3 v2){
        if(v[0] != v2.getX()) return false;
        if(v[1] != v2.getY()) return false;
        if(v[2] != v2.getZ()) return false;
        return true;
    }
    
    public double getX(){
        return v[0];
    }
    
    public double getY(){
        return v[1];
    }
    
    public double getZ(){
        return v[2];
    }
    
    public double length(){
        return (Math.sqrt(v[0]*v[0] + v[1]*v[1] + v[2]*v[2]));
    }
    
    public double maxElement(){
        double aux = v[0];
        if(v[1] > aux) aux = v[1];
        if(v[2] > aux) aux = v[2];
        return aux;
    }
    
    public Vec3 maxVector(Vec3 v2){
        Vec3 aux = new Vec3(v2);
        if(v2.getX() > v[0]) aux.setX(v2.getX());
        if(v2.getY() > v[1]) aux.setY(v2.getY());
        if(v2.getZ() > v[2]) aux.setZ(v2.getZ());
        return aux;
    }
    
    public double minElement(){
        double aux = v[0];
        if(v[1] < aux) aux = v[1];
        if(v[2] < aux) aux = v[2];
        return aux;
    }
    
    public Vec3 minVector(Vec3 v2){
        Vec3 aux = new Vec3(v2);
        if(v2.getX() < v[0]) aux.setX(v2.getX());
        if(v2.getY() < v[1]) aux.setY(v2.getY());
        if(v2.getZ() < v[2]) aux.setZ(v2.getZ());
        return aux;
    }
    
    public Vec3 multiply(double scalar){
        Vec3 aux = new Vec3();
        aux.v[0] = this.v[0] * scalar;
        aux.v[1] = this.v[1] * scalar;
        aux.v[2] = this.v[2] * scalar;
        return aux;
    }
    
    public Vec3 negative(){
        return new Vec3(-v[0], -v[1], -v[2]);
    }
    
    public void normalize(){
        Vec3 aux = new Vec3(this);
        aux.divide(this.length());
        this.assign(aux);
    }
    
    public double squaredLength(){
        return (v[0]*v[0] + v[1]*v[1] + v[2]*v[2]);
    }
    
    public void setX(double x){
        v[0] = x;
    }
    
    public void setY(double y){
        v[1] = y;
    }
    
    public void setZ(double z){
        v[2] = z;
    }
    
    public Vec3 subtract(Vec3 v2){
        return new Vec3(v[0] - v2.getX(), v[1] - v2.getY(), v[2] - v2.getZ());
    }
    
    public String toString(){
        return ("" + v[0] + "    " + v[1] + "     " + v[2] + "\n");
    }
    
    public double tripleProduct(Vec3 v2, Vec3 v3){
        return this.cross(v2).dot(v3);
    }
    
    public double[] toDoubleArray(){
        
        double[] a = new double[3];
        
        a[0] = v[0];
        a[1] = v[1];
        a[2] = v[2];
        
        return a;
    }
    
}
