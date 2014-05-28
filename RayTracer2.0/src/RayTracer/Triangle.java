/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author Moura
 */
public class Triangle extends Primitive{
    private Vec3 v0;
    private Vec3 v1;
    private Vec3 v2;
    private boolean cull;
    private Vec3 normal;
    
    public Triangle(Vec3 v0, Vec3 v1, Vec3 v2, Material m, boolean cull){
        super(m);
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.cull = cull;
        
        normal = v1.subtract(v0).cross(v2.subtract(v1));
        normal.normalize();
        
    }
    
    public Triangle(Vec3 v0, Vec3 v1, Vec3 v2, Vec3 n, Material m, boolean cull){
        super(m);
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.cull = cull;
        
        normal = n;
        normal.normalize();
        
    }
    
    public Vec3[] getVertices(){
        Vec3[] verts = new Vec3[3];
        verts[0] = v0; verts[1] = v1; verts[2] = v2;
        return verts;
    }
    
    @Override
    public boolean shadowHit(Ray r){
        return true;
    }
    
    @Override
    public double hit(Ray r){
        Vec3 e1 = v1.subtract(v0);
        Vec3 e2 = v2.subtract(v0);        
        Vec3 aux = r.getDirection().cross(e2);
        double det = e1.dot(aux);
        double inv_det;
        double u, v, t;
        
        if(cull){
            if(det < Vec3.epsilon) return infinity;
            Vec3 aux2 = r.getOrigin().subtract(v0);
            u = aux.dot(aux2);
            if(u < 0.0 || u > det) return infinity;
            Vec3 aux3 = aux2.cross(e1);
            v = r.getDirection().dot(aux3);
            if( v < 0.0 || v > det) return infinity;
            t = e2.dot(aux3);
            inv_det = 1.0 / det;
            t *= inv_det;
            //System.out.println(t);
            u *= inv_det;
            v *= inv_det;
        }else{
            if(det > -Vec3.epsilon && det < Vec3.epsilon) return infinity;
            inv_det = 1.0 / det;
            Vec3 aux2 = r.getOrigin().subtract(v0);
            u = aux.dot(aux2) * inv_det;
            if(u < 0.0 || u > 1.0) return infinity;
            Vec3 aux3 = aux2.cross(e1);
            v = r.getDirection().dot(aux3) * inv_det;
            if( v < 0.0 || u + v > 1.0) return infinity;
            t = e2.dot(aux3) * inv_det;   
            //System.out.println(t);
        }
        
        if(-Vec3.epsilon <= t && t <= Vec3.epsilon){
            return infinity;
        }else{
            return t;
        }
    }

    @Override
    public Vec3 getNormal(Vec3 point) {
        return normal;
    }
    
    public boolean isInside(Ray r){
        return false;
    }
}
