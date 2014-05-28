/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author thiago
 */
public class Material {
    
    private double specular;
    private double diffuse;
    private double refrIndex;
    private double krefl;
    private double krefr;
    private double kloc;
    private int n;
    private Vec3 color;
    
    public Material(Vec3 color,double specular, double diffuse, double krefl, double krefr, double kloc, double refrIndex, int n){
        this.specular = specular;
        this.diffuse = diffuse;
        this.n = n;
        this.color = color;
        this.krefl = krefl;
        this.krefr = krefr;
        this.kloc = kloc;
        this.refrIndex = refrIndex;
    }

    public double getRefrIndex() {
        return refrIndex;
    }

    public void setRefrIndex(double refrIndex) {
        this.refrIndex = refrIndex;
    }
    
    public Vec3 getProperties(){
        return new Vec3(specular,diffuse,n);
    }
    
    public Vec3 getColor(){
        return color;
    }
    
    
    public double getKloc() {
        return kloc;
    }

    public void setKloc(double kloc) {
        this.kloc = kloc;
    }
    
    public void setProperties(int s, int d, int n){
       specular = s;
       diffuse = d;
       this.n = n;
    }
    
    public void setColor(Vec3 c){
        color = c;
    }
    
    public double getKrefl() {
        return krefl;
    }

    public void setKrefl(double krefl) {
        this.krefl = krefl;
    }

    public double getKrefr() {
        return krefr;
    }

    public void setKrefr(double krefr) {
        this.krefr = krefr;
    }
    
}
