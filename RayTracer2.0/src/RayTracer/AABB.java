package RayTracer;

import java.util.ArrayList;


public class AABB {
    private Vec3 center;
    private Vec3 radius;
    private boolean initialized;

    public AABB() {
        center = new Vec3();
        radius = new Vec3();
        initialized = false;
    }

    public Vec3 getCenter() {
        return center;
    }

    public Vec3 getRadius() {
        return radius;
    }
    
    public boolean isInitialized(){
        return initialized;
    }
    
    public void initBox(ArrayList<Vec3> vertices){
        double minX, minY, minZ, maxX, maxY, maxZ;
        minX = minY = minZ = Double.MAX_VALUE;
        maxX = maxY = maxZ = Double.MIN_VALUE;
        for(Vec3 v : vertices){
            if(v.getX() < minX) minX = v.getX();
            if(v.getY() < minY) minY = v.getY();
            if(v.getZ() < minZ) minZ = v.getZ();
            if(v.getX() > maxX) maxX = v.getX();
            if(v.getY() > maxY) maxY = v.getY();
            if(v.getZ() > maxZ) maxZ = v.getZ();
        }
        
        center.setX((minX + maxX) / 2.0);
        center.setY((minY + maxY) / 2.0);
        center.setZ((minZ + maxZ) / 2.0);
        
        radius.setX((maxX - minX) / 2.0);
        radius.setY((maxY - minY) / 2.0);
        radius.setZ((maxZ - minZ) / 2.0);
        
        initialized = true;
        System.out.println(this.toString());
    }
    
    public boolean collision(Ray r){
        double tmin = 0.0;
        double tmax = Double.MAX_VALUE;
        double[] pmin = this.getMin().toDoubleArray();
        double[] pmax = this.getMax().toDoubleArray();
        double[] orig = r.getOrigin().toDoubleArray();
        double[] dir = r.getDirection().toDoubleArray();
        for(int i = 0; i < 3; ++i){
            if(Math.abs(r.getDirection().toDoubleArray()[i]) < Vec3.epsilon){
                if(orig[i] < pmin[i] || orig[i] > pmax[i]){
                    return false;
                } else;
            }else{
                double ood = 1.0 / dir[i];
                double t1 = (pmin[i] - orig[i]) * ood;
                double t2 = (pmax[i] - orig[i]) * ood;
                if(t1 > t2) this.swap(t1, t2);
                if(t1 > tmin) tmin = t1;
                if(t2 > tmax) tmax = t2;
                if(tmin > tmax) return false;
            }
        }
        return true;
    }
    
    public Vec3 getMin(){
        Vec3 pmin = new Vec3();
        pmin.setX(center.getX() - radius.getX());
        pmin.setY(center.getY() - radius.getY());
        pmin.setZ(center.getZ() - radius.getZ());
        return pmin;
    }
    
    public Vec3 getMax(){
        Vec3 pmax = new Vec3();
        pmax.setX(center.getX() + radius.getX());
        pmax.setY(center.getY() + radius.getY());
        pmax.setZ(center.getZ() + radius.getZ());
        return pmax;
    }
    
    private void swap(double d1, double d2){
        double aux = d1;
        d1 = d2;
        d2 = aux;
    }
    
    public String toString(){
        String centro = "Centro: " + center.getX() + "    " + center.getY() + "     " + center.getZ() + "\n";
        String raio = "Raio: " + radius.getX() + "     " + radius.getY() + "     " + radius.getZ() + "\n";
        return ("Descrição da AABB:\n" + centro + raio);
    }
    
}
