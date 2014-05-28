package RayTracer;


public class OrthoBasis {
    private Vec3 u;
    private Vec3 v;
    private Vec3 w;    
    
    public OrthoBasis(){
        
    }
    
    public OrthoBasis(Vec3 a, Vec3 b, Vec3 c){
        u = new Vec3(a);
        v = new Vec3(b);
        w = new Vec3(c);
    }
    
    boolean equals(OrthoBasis ob){
        return (u.equals(ob.getU()) && v.equals(ob.getV()) && w.equals(ob.getW()));
    }
    
    public Vec3 getU(){
        return u;
    }
    
    public Vec3 getV(){
        return v;
    }
    
    public Vec3 getW(){
        return w;
    }
    
    void initFromU(Vec3 u){
        Vec3 n = new Vec3(1.0, 0.0, 0.0);
        Vec3 m = new Vec3(0.0, 1.0, 0.0);
        u.normalize();
        this.u.assign(u);
        this.v = this.u.cross(n);
        if(v.length() < Vec3.epsilon) this.v = this.u.cross(m);
        this.w = this.u.cross(v);
    }
    
    void initFromV(Vec3 v){
        Vec3 n = new Vec3(1.0, 0.0, 0.0);
        Vec3 m = new Vec3(0.0, 1.0, 0.0);
        v.normalize();
        this.v = v;
        this.u = this.v.cross(n);
        if(u.length() < Vec3.epsilon) u = this.v.cross(m);
        this.w = u.cross(this.v);
     }
    
    void initFromW(Vec3 w){
        Vec3 n = new Vec3(1.0, 0.0, 0.0);
        Vec3 m = new Vec3(0.0, 1.0, 0.0);
        w.normalize();
        this.w = w;
        u = this.w.cross(n);
        if(u.length() < Vec3.epsilon) u = this.w.cross(m);
        v = this.w.cross(u);
    }
    
    void initFromUV(Vec3 u, Vec3 v){
        u.normalize();
        this.u = u;
        w = u.cross(v);
        w.normalize();
        this.v = w.cross(u);
    }
    
    void initFromVU(Vec3 v, Vec3 u){
        v.normalize();
        this.v = v;
        w = u.cross(v);
        w.normalize();
        this.u = this.v.cross(w);
    }
    
    void initFromUW(Vec3 u, Vec3 w){
        u.normalize();
        this.u = u;
        v = w.cross(u);
        v.normalize();
        this.w = this.u.cross(v);
    }
    
    void initFromWU(Vec3 w, Vec3 u){
        w.normalize();
        this.w = w;
        v = w.cross(u);
        v.normalize();
        this.w = v.cross(this.u);
    }
    
    void initFromVW(Vec3 v, Vec3 w){
        v.normalize();
        this.v = v;
        u = v.cross(w);
        u.normalize();
        this.w = u.cross(this.v);
    }
    
    void initFromWV(Vec3 w, Vec3 v){
        this.w = w;
        this.w.normalize();
        u = v.cross(w);
        u.normalize();
        this.v = this.w.cross(u);
        this.v.normalize();
    }
    
    public String toString(){
        return (u.toString() + v.toString() + w.toString());
    }
}
