package RayTracer;


import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class ObjModelLoader extends ModelLoader{
    
    public ObjModelLoader(String fileName){
        try{
            stream = new BufferedReader(new FileReader(new File(fileName)));
        }catch(FileNotFoundException e){
            System.err.println("O arquivo " + fileName + " não foi encontrado.");
            System.err.println("O programa será encerrado.");
            System.exit(1);
        }
        
    }
    
    @Override
    public Model load(){
        Model m = new Model(true);
        String aux;
        double v1, v2, v3;
        ArrayList< ArrayList<Integer> > indices = null;
        boolean init = false;
        while(true){
            try{
                aux = stream.readLine();
                System.out.println(aux);
                if(aux == null) break;
                StringTokenizer lt = new StringTokenizer(aux, " ");
                String pref = lt.nextToken();
                //System.out.println(pref);
                if(pref.equals("v")){
                    v1 = Double.parseDouble(lt.nextToken());
                    v2 = Double.parseDouble(lt.nextToken());
                    v3 = Double.parseDouble(lt.nextToken());
                    Vec3 v = new Vec3(v1, v2, v3);
                    m.getVertices().add(v);
                }
                if(pref.equals("vn")){
                    if(!init){
                        init = true;
                        indices = new ArrayList<>();
                        for(int i = 0; i < m.getVertices().size(); ++i){
                            ArrayList<Integer> al = new ArrayList<>();
                            indices.add(al);
                        }
                    }
                    v1 = Double.parseDouble(lt.nextToken());
                    v2 = Double.parseDouble(lt.nextToken());
                    v3 = Double.parseDouble(lt.nextToken());
                    Vec3 n = new Vec3(v1, v2, v3);
                    m.getFaceNormals().add(n);
                }
                if(pref.equals("f")){
                    int n, i[];
                    i = new int[3];
                    n = 0;
                    String token;
                    for(int j = 0; j < 3; ++j){
                        token = lt.nextToken();
                        StringTokenizer st = new StringTokenizer(token, " /");
                        i[j] = Integer.parseInt(st.nextToken()) - 1;
                        n = Integer.parseInt(st.nextToken()) - 1;
                    }
                    TriangularFace f = new TriangularFace(i[0], i[1], i[2], n);
                    m.addFace(f);
                    indices.get(i[0]).add(n);
                    indices.get(i[1]).add(n);
                    indices.get(i[2]).add(n);
                }
            }catch(EOFException e){
                System.out.println("Cheguei no break");
                break;
            }catch(IOException e){
                System.err.println("Falha na leitura do arquivo.");
                System.err.println("O programa será encerrado.");
                System.exit(2);
            }
        }
        System.out.println(m.getVertices().size());
        System.out.println(m.getFaceNormals().size());
        System.out.println(m.getFaces().size());
        initVerticesNormals(m, indices);
        return m;
    }
    
    public void initVerticesNormals(Model m, ArrayList<ArrayList<Integer> > al){
        for(int i = 0; i < al.size(); ++i){
            Vec3 vn = new Vec3();
            for(Integer fn : al.get(i)){
                vn.setX(vn.getX() + m.getFaceNormals().get(fn).getX());
                vn.setY(vn.getY() + m.getFaceNormals().get(fn).getY());
                vn.setZ(vn.getZ() + m.getFaceNormals().get(fn).getZ());
            }
            vn.divide(al.get(i).size());
            vn.normalize();
            m.getVertexNormals().add(vn);
        }
    }
}