/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

/**
 *
 * @author Moura
 */
public class TriangularFace extends Face{
    private int vIndices[];
    private int nIndex;
    
    public TriangularFace(int v1, int v2, int v3, int n){
        vIndices = new int[3];
        vIndices[0] = v1;
        vIndices[1] = v2;
        vIndices[2] = v3;
        nIndex = n;
        p = null;
    }
    
    public TriangularFace(Triangle t){
        p = t;
    }
    
    public void initTriangle(Vec3 v1, Vec3 v2, Vec3 v3, Vec3 n, Material m){
        p = new Triangle(v1, v2, v3, m, false);
    }

    public int[] getVertexIndices() {
        return vIndices;
    }

    public int getNormalIndex() {
        return nIndex;
    }

    
}
