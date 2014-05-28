/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author Moura
 */

import java.util.ArrayList;

public class Model {
    private ArrayList<Face> faces;
    private ArrayList<Vec3> vertices;
    private ArrayList<Vec3> vertexNormals;
    private ArrayList<Vec3> faceNormals;
    private Material material;
    private Primitive collisionPrim;
    private Face collisionFace;
    private AABB box;
    private boolean built;
    
    public Model(){
        vertices = new ArrayList<>();
        vertexNormals = new ArrayList<>();
        faceNormals = new ArrayList<>();
        faces = new ArrayList<>();
        collisionPrim = null;
        box = null;
        built = false;
    }
    
    public Model(boolean useBox){
        vertices = new ArrayList<>();
        vertexNormals = new ArrayList<>();
        faceNormals = new ArrayList<>();
        faces = new ArrayList<>();
        collisionPrim = null;
        if(useBox) box = new AABB();
        else box = null;
        built = false;
    }
    
    public void addFace(Face f){
        faces.add(f);
    }
    
    public void addFace(Sphere s){
        faces.add(new SphericalFace(s));
    }
    
    public void addFace(Plane p){
        faces.add(new PlanarFace(p));
    }
    
    public void addFace(Triangle t){
        faces.add(new TriangularFace(t));
    }

    public ArrayList<Vec3> getVertices() {
        return vertices;
    }

    public ArrayList<Vec3> getVertexNormals() {
        return vertexNormals;
    }
    
    public ArrayList<Vec3> getFaceNormals() {
        return faceNormals;
    }
    
    public ArrayList<Face> getFaces(){
        return faces;
    }

    public Material getMaterial() {
        return material;
    }
    
    public AABB getBox(){
        return box;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Primitive getCollisionPrimitive() {
        return collisionPrim;
    }
    
    public Face getCollisionFace() {
        return collisionFace;
    }   
    
    public double hit(Ray r){
        if(box != null){
            if(!box.isInitialized()) box.initBox(vertices);
            if(!box.collision(r)) return Primitive.infinity;
        }
        if(!built) this.buildTriangles();
        collisionPrim = null;
        collisionFace = null;
        double result = Primitive.infinity;
        double aux;
        for(Face f : faces){
            aux = f.getPrimitive().hit(r);
            if(aux > 0 && aux < result){
                result = aux;
                collisionFace = f;
                collisionPrim = f.getPrimitive();
            }
        }
        return result;
    }
    
    public void translate(double x, double y, double z){
        for(Vec3 v : vertices){
            v.setX(v.getX() + x);
            v.setY(v.getY() + y);
            v.setZ(v.getZ() + z);
        }
    }
    
    public void scale(double x, double y, double z){
        for(Vec3 v : vertices){
            v.setX(v.getX() * x);
            v.setY(v.getY() * y);
            v.setZ(v.getZ() * z);
        }
    }
    
    public void buildTriangles(){
        for(Face f: faces){
            if(!(f instanceof TriangularFace)) continue;
            int[] v = ((TriangularFace)f).getVertexIndices();
            int n = ((TriangularFace)f).getNormalIndex();
            ((TriangularFace)f).initTriangle(vertices.get(v[0]), vertices.get(v[1]), vertices.get(v[2]),
                           faceNormals.get(n), material);
        }
        built = true;
    }
    
}
