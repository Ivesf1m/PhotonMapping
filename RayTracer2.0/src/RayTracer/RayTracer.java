/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

import java.util.Random;

/**
 *
 * @author thiago
 */
public class RayTracer extends Thread{
    
    private Image im;
    private Model[] models;
    private Light[] lights;
    private Camera camera;
    private int width;
    private int height;
    private int objAmount;
    private int lightAmount;
    private double ambientIntensity;
    private double[][][] mat;
            
    public RayTracer(RayTracer newRT){
        this.ambientIntensity = newRT.ambientIntensity;
        this.camera = newRT.camera;
        this.height = newRT.height;
        this.im = newRT.im;
        this.lightAmount = newRT.lightAmount;
        this.lights = newRT.lights;
        this.mat = newRT.mat;
        this.models = newRT.models;
        this.objAmount = newRT.objAmount;
        this.width = newRT.width;
    }
    
    public RayTracer(int width, int height){
        im = new Image(width, height, "Teste");
        this.width = width;
        this.height = height;
        objAmount = 0;
        lightAmount = 0;
        lights = new Light[10];
        models = new Model[100];
        ambientIntensity = 0;
        mat = new double[width][height][3];
        
    }
    
    /*public Vec3 createRandomVector(Vec3 normal){
    
        Random r = new Random();
        
        double a1,a2;
        
        do{
            a1 = Math.toRadians(r.nextInt(180));
            a2 = Math.toRadians(r.nextInt(360));
        }while()
        
    }*/
    
    //public Vec3 fakeIndirectIllumination(){
    //    
    //}
    
    public void setPerspectiveCamera(Vec3 pos, Vec3 dir, Vec3 up){
        
        int resolution[] = new int[2];
        resolution[0] = width;
        resolution[1] = height;
        camera = new Camera(pos,dir,up,Math.PI/3.0,resolution);
        
        System.out.println(camera.toString());
        
    }
      
    public Vec3 fireRay(Ray r, Light l, int stop){
        
        Model minHitObj = null;
        Primitive minHitPrimitive = null;
        double minHitLength = Primitive.infinity;
        Vec3 objectColor = new Vec3();
        Vec3 reflectedColor = new Vec3();
        Vec3 refractedColor = new Vec3();
        
        for(int k = 0; k < objAmount;k++){

            double result = models[k].hit(r);      
            if(result < minHitLength){
                minHitObj = models[k];
                minHitLength = result;
            }

        }
        
        if(minHitObj == null){
            return new Vec3(0.0,0.0,0.0);
        }else{
            double[] color = calculatePixelColor(r, l, minHitObj, minHitLength);
            objectColor = new Vec3(color[0],color[1],color[2]);
        }
        
        if(stop == 6) return objectColor;
        
        //Cálculo do Cor Refletida
        
        if (minHitObj.getMaterial().getKrefl() > 0 || minHitObj.getMaterial().getKrefr() > 0) {
            
            minHitPrimitive = minHitObj.getCollisionPrimitive();
            
            Vec3 normal = minHitObj.getCollisionPrimitive().getNormal(r.getPointAtParameter(minHitLength));
            
            Vec3 intersect = r.getPointAtParameter(minHitLength);
            //Vec3 incRay = intersect.subtract(r.getOrigin()).negative();
            Vec3 incRay = r.getOrigin().subtract(intersect).negative();
            incRay.normalize();
            
            //Vec3 reflRay = incRay.add(normal.multiply(2*c1));
            Vec3 reflRay = ((normal.multiply(2.0 * normal.dot(incRay))).subtract(incRay));
            
            reflRay.normalize();
            
            //if(minHitLength < 0) reflectedColor = new Vec3(255,0,0);
            
            reflRay.setY(-reflRay.getY());
            reflRay.setX(-reflRay.getX());
            reflRay.setZ(-reflRay.getZ());  
            
            //System.out.println("normal = " + normal.toString());
            
            intersect = intersect.add(normal);

            //System.out.println("normal 4 = " + normal.toString());
            
            Ray rr = new Ray(intersect,reflRay);
            
            if(minHitObj.getCollisionPrimitive().isInside(rr)){
                System.out.println("Esta dentro!");
            }
            
            reflectedColor = fireRay(rr, l, stop + 1);
            
        }
        
        //Calculo do Cor Refratada
        
        if (minHitObj.getMaterial().getKrefr() > 0) {
        
            //System.out.println("Entrei. Nivel = " + stop);
            //System.out.println("Vetor que chegou = \nOri = " + r.getOrigin().toString() + "Dest = "+ r.getDirection().toString());
            
            Primitive collPrim;
            
            if(minHitObj.getCollisionPrimitive() == null){
                collPrim = minHitPrimitive;
            }else{
                collPrim = minHitObj.getCollisionPrimitive();
            }
                    
            Vec3 normal = collPrim.getNormal(r.getPointAtParameter(minHitLength));
            Vec3 intersect = r.getPointAtParameter(minHitLength);
            Vec3 incRayR = r.getOrigin().subtract(intersect);
            incRayR.normalize();
            
            double lambdaDiv = 1.0;
            
            if (collPrim.isInside(r)) {
                lambdaDiv = minHitObj.getMaterial().getRefrIndex() / 1.0;
                normal = normal.negative();
            } else {
                lambdaDiv = 1.0 / minHitObj.getMaterial().getRefrIndex();
            }
            
            double ndoti = incRayR.dot(normal);
            
            double raiz = 1.0 - (lambdaDiv*lambdaDiv)*(1.0 - (ndoti*ndoti));

            double c2 = Math.sqrt(Math.max(raiz,0.0));
            Vec3 refractVec = null;
            
            if(c2 >= 0){
                refractVec = (incRayR.multiply(lambdaDiv)).add(normal.multiply(lambdaDiv * ndoti - c2));
                refractVec.normalize();
                intersect = intersect.add(normal.negative());
            }else{
                Vec3 incRay = incRayR.negative();
                refractVec = ((normal.multiply(2.0 * normal.dot(incRay))).subtract(incRay));
                intersect = intersect.add(normal);
            }         

            refractVec.setX(-refractVec.getX());
            refractVec.setY(-refractVec.getY());
            refractVec.setZ(-refractVec.getZ());
            
            Ray rRay = new Ray(intersect, refractVec);
            
            refractedColor = fireRay(rRay, l, stop + 1);
          
        }
        //Calculo da Cor Total
 
        double finalColor[] = new double[3];

        if (minHitObj.getMaterial().getKrefr() > 0  && !minHitPrimitive.isInside(r)) {
            
            Vec3 intersect = r.getPointAtParameter(minHitLength);
            
            Vec3 incRay = intersect.subtract(r.getOrigin());
            incRay.normalize();
            
            Vec3 normal = minHitPrimitive.getNormal(intersect);
            
            double ndoti = normal.dot(incRay);
            
            double pot = 1.0;
           
            double roh = Math.max(0, Math.min(1, Math.pow((1 + ndoti), pot)));
            
            finalColor[0] = (roh)*reflectedColor.getX() + (1 - roh)*refractedColor.getX();
            finalColor[1] = (roh)*reflectedColor.getY() + (1 - roh)*refractedColor.getY();
            finalColor[2] = (roh)*reflectedColor.getZ() + (1 - roh)*refractedColor.getZ();
            
            /*finalColor[0] = refractedColor.getX();
            finalColor[1] = refractedColor.getY();
            finalColor[2] = refractedColor.getZ();*/
            
            
        } else {
            finalColor[0] = objectColor.getX() * minHitObj.getMaterial().getKloc()
                    + reflectedColor.getX() * minHitObj.getMaterial().getKrefl()
                    + refractedColor.getX() * minHitObj.getMaterial().getKrefr();

            finalColor[1] = objectColor.getY() * minHitObj.getMaterial().getKloc()
                    + reflectedColor.getY() * minHitObj.getMaterial().getKrefl()
                    + refractedColor.getY() * minHitObj.getMaterial().getKrefr();

            finalColor[2] = objectColor.getZ() * minHitObj.getMaterial().getKloc()
                    + reflectedColor.getZ() * minHitObj.getMaterial().getKrefl()
                    + refractedColor.getZ() * minHitObj.getMaterial().getKrefr();

            finalColor[0] /= minHitObj.getMaterial().getKloc() + minHitObj.getMaterial().getKrefl() + minHitObj.getMaterial().getKrefr();
            finalColor[1] /= minHitObj.getMaterial().getKloc() + minHitObj.getMaterial().getKrefl() + minHitObj.getMaterial().getKrefr();
            finalColor[2] /= minHitObj.getMaterial().getKloc() + minHitObj.getMaterial().getKrefl() + minHitObj.getMaterial().getKrefr();
        }
        
        return new Vec3(finalColor[0],finalColor[1],finalColor[2]);
        
    }    

    public void Cast(){
        
        int i = 0;
        int j = 0;
        
        for(;i < width;i++){
            for(j=0;j<height;j++){
                
                Ray[] rays = new Ray[5];
                Vec3[] colorsRays = new Vec3[5];
                Vec3[] colorsLight = new Vec3[lightAmount];
                
                rays[0] = camera.createRay(i-0.3,j+0.3);
                rays[1] = camera.createRay(i+0.3,j-0.3);
                rays[2] = camera.createRay(i-0.3,j-0.3);
                rays[3] = camera.createRay(i+0.3,j+0.3);
                rays[4] = camera.createRay(i, j);
           
                for (int p = 0; p < 5; p++) {
                    for (int w = 0; w < lightAmount; ++w) {
                        colorsLight[w] = fireRay(rays[p], lights[w], 0);
                    }
                    
                    double rAcc = 0;
                    double gAcc = 0;
                    double bAcc = 0;

                    for (int c = 0; c < lightAmount; c++) {
                        rAcc += colorsLight[c].getX();
                        gAcc += colorsLight[c].getY();
                        bAcc += colorsLight[c].getZ();
                    }
                    
                    colorsRays[p] = new Vec3(rAcc / (double)lightAmount, gAcc / (double)lightAmount, bAcc / (double)lightAmount);
                }
                
                double rAcc = 0;
                double gAcc = 0;
                double bAcc = 0;
                
                for(int c = 0; c < 5; c++){
                    rAcc += colorsRays[c].getX();
                    gAcc += colorsRays[c].getY();
                    bAcc += colorsRays[c].getZ();
                }
                
                double[] c_Total = {rAcc/5.0,gAcc/5.0,bAcc/5.0};
                //for(int o = 0; o < 3; ++o) System.out.print(c_Total[o] + "   ");
                //System.out.print("\n");
                mat[i][j] = c_Total;        
                //im.setPixelColor(i, j, c_Total);
            }

        }
        
    }
    
    public Image getImage(){
        return im;
    }
    
    public void addSphere(Vec3 center, double radius, Material m){
       
        models[objAmount] = new Model();
        models[objAmount].addFace(new Sphere(center,radius,m));
        models[objAmount].setMaterial(m);
        ++objAmount;        
    }
    
    public void addTriangle(Vec3 v0, Vec3 v1, Vec3 v2, Material m){
        models[objAmount] = new Model();
        models[objAmount].addFace(new Triangle(v0, v1, v2, m, false));
        models[objAmount].setMaterial(m);
        ++objAmount;
    }
    
    public void addPlane(Vec3 n, Vec3 p, Material m){
        models[objAmount] = new Model();
        models[objAmount].addFace(new Plane(n,p,m));
        models[objAmount].setMaterial(m);
        ++objAmount;
    }
    
    public void addModel(Model m){
        models[objAmount++] = m;
    }
    
    public void addLight(Vec3 p, Vec3 c){
        lights[lightAmount++] = new Light(p,c);
    }
    
    public void addAmbient(double ambient){
        if(ambient > 1){
            ambientIntensity = 1;
        }else if(ambient < 0){
            ambientIntensity = 0;
        }else{
            ambientIntensity = ambient;
        }
    }
    
    public boolean shadowTest(Ray r, Light l, double dist, Model minHitObj){        
        Vec3 origin = r.getPointAtParameter(dist);        
        Vec3 direction = l.getPosition().subtract(origin);
        direction.normalize();        
        Ray shadowRay = new Ray(origin,direction);
        
        double distLuz = Math.sqrt(Math.pow(origin.getX() - l.getPosition().getX(), 2) +
                                   Math.pow(origin.getY() - l.getPosition().getY(), 2) +
                                   Math.pow(origin.getZ() - l.getPosition().getZ(), 2));
        
        for(int i = 0; i < objAmount; i++){
            if(minHitObj == models[i]) continue;
            double t = models[i].hit(shadowRay);
            if(t < distLuz && t < Primitive.infinity){
                return true;
            }
        }        
        return false;
        
    }
    
    public double phong(Ray r, double dist, Vec3 normal, Light l, double diffuse, double specular, double expoent){
        
        Vec3 intersect = r.getPointAtParameter(dist);

        Vec3 viewVec = r.getOrigin().subtract(intersect);
        Vec3 lightVec = l.getPosition().subtract(intersect);
        Vec3 reflection = ((normal.multiply(2.0 * normal.dot(lightVec))).subtract(lightVec));
        
        viewVec.normalize();
        lightVec.normalize();
        reflection.normalize();
       
        double rdotv = Math.max(reflection.dot(viewVec), 0.0);
        
        double s = specular * Math.pow(rdotv,expoent);
        double d = diffuse * Math.max(0.0, normal.dot(lightVec));
        
        return s + d;
        
    }
    
    public double limitTest(double value){
        
        double test = value;
        
        if(value > 255){
            test = 255;
        }else if(value < 0){
            test = 0;
        }
        
        return test;

    }
    
    public double[] calculatePixelColor(Ray r, Light l, Model minHitObj, double minHitLength){
        
        double pixelColor[] = new double[3];
        
        Vec3 properties = minHitObj.getMaterial().getProperties();
        Vec3 normal;
        
        if(minHitObj.getBox() == null) normal = minHitObj.getCollisionPrimitive().getNormal(r.getPointAtParameter(minHitLength));
        else normal = interpolateNormal(r.getPointAtParameter(minHitLength), minHitObj);
        
        double phong = phong(r,minHitLength, normal ,l,properties.getY(),properties.getX(),properties.getZ());
                        
        Vec3 color = minHitObj.getMaterial().getColor();

        if(shadowTest(r,l,minHitLength,minHitObj)){
            //System.out.println("Shadow");
            pixelColor[0] = limitTest(((color.getX() * phong)/2) + (color.getX()*ambientIntensity));
            pixelColor[1] = limitTest((color.getY() * phong)/2 + (color.getY()*ambientIntensity));
            pixelColor[2] = limitTest((color.getZ() * phong)/2 + (color.getZ()*ambientIntensity));
            //pixelColor[0] = 255;
            //pixelColor[1] = 0;
            //pixelColor[2] = 0;
        }else{
            pixelColor[0] = limitTest(color.getX() * phong + (color.getX()*ambientIntensity));
            pixelColor[1] = limitTest(color.getY() * phong + (color.getY()*ambientIntensity));
            pixelColor[2] = limitTest(color.getZ() * phong + (color.getZ()*ambientIntensity));    
        }
        
        return pixelColor;
        
    }
    
    public Vec3 interpolateNormal(Vec3 point, Model m){
        Triangle prim = (Triangle)m.getCollisionPrimitive();
        TriangularFace tf = (TriangularFace)m.getCollisionFace();
        Vec3 p0 = prim.getVertices()[0];
        Vec3 p1 = prim.getVertices()[1];
        Vec3 p2 = prim.getVertices()[2]; 
        
        Vec3 n0 = m.getVertexNormals().get(tf.getVertexIndices()[0]);
        Vec3 n1 = m.getVertexNormals().get(tf.getVertexIndices()[1]);
        Vec3 n2 = m.getVertexNormals().get(tf.getVertexIndices()[2]);
        
        Vec3 u = new Vec3(p0.getX() - p1.getX(), p0.getY() - p1.getY(), p0.getZ() - p1.getZ());
        Vec3 v = new Vec3(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ());
        Vec3 n = new Vec3(point.getX() - p1.getX(), point.getY() - p1.getY(), point.getZ() - p1.getZ());
        
        double du = u.length();
        double dv = v.length();
        double dn = n.length();
        
        n.normalize();
        u.normalize();
        
        double cost = n.dot(u);
        if(cost < 0.0) cost = 0.0;
        if(cost > 1.0) cost = 1.0;
        double t = Math.acos(cost);
        
        double distY = 0.0, distX = 0.0;
        distX = dn * Math.cos(t);
        distY = dn * Math.sin(t);
        double uu = distX / du;
        double vv = distY / dv;
        
        n0.normalize();
        n1.normalize();
        n2.normalize();
        
        double nx = ((1.0 - (uu + vv)) * n1.getX() + n0.getX() * uu + n2.getX() * vv);
        double ny = ((1.0 - (uu + vv)) * n1.getY() + n0.getY() * uu + n2.getY() * vv);
        double nz = ((1.0 - (uu + vv)) * n1.getZ() + n0.getZ() * uu + n2.getZ() * vv);

        Vec3 nInter = new Vec3(nx, ny, nz);
        //nInter.normalize();
        return nInter;
    }
    
    public void assembleIm(){
        for(int i = 0, i2 = width - 1; i < width; i++, i2--){
            for(int j = 0, j2 = height - 1; j < height; j++,j2--){
                im.setPixelColor(i, j, mat[i2][j2]);
            }
        }
    }
    
}
