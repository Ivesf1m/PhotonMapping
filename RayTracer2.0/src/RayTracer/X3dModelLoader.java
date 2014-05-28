package RayTracer;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class X3dModelLoader extends ModelLoader{
    
    public X3dModelLoader(String fileName){
        try{
            stream = new BufferedReader(new FileReader(new File(fileName)));
        }catch(FileNotFoundException e){
            System.err.println("O arquivo " + fileName + " não foi encontrado.");
            System.err.println("O programa será encerrado.");
            System.exit(1);
        }
    }
    
    public Model load(){
        Model m = new Model(true);
        String aux;
        int i = 1;
        StringTokenizer st;
        Vec3 vert;
        Vec3 norm;
        double c1, c2, c3;
        ArrayList<Integer> indices = new ArrayList<>();
        try{
            while(true){            
                aux = stream.readLine();
                if(aux == null) break;                
                st = new StringTokenizer(aux, " \t");
                System.out.println("Linha " + (i++) + ": " + aux);
                String tok = st.nextToken();
                if(tok.equals("normalPerVertex=\"true\"")){
                    aux = stream.readLine();
                    st = new StringTokenizer(aux, " \t");
                    String first = st.nextToken();
                    String firstNumber = first.substring(first.length() - 1);
                    indices.add(Integer.parseInt(firstNumber));
                    first = st.nextToken();
                    while(!first.equals("\"")){
                        indices.add(Integer.parseInt(first));
                        first = st.nextToken();
                    }
                }
                else if(tok.equals("<Coordinate")){
                    aux = stream.readLine();
                    st = new StringTokenizer(aux, " \t");
                    StringTokenizer st2 = new StringTokenizer(st.nextToken(), "\"");
                    String numero = st2.nextToken();
                    numero = st2.nextToken();
                    c1 = Double.parseDouble(numero);
                    c2 = Double.parseDouble(st.nextToken());
                    c3 = Double.parseDouble(st.nextToken());
                    vert = new Vec3(c1, c2, c3);
                    m.getVertices().add(vert);
                    while(true){
                        numero = st.nextToken();
                        if(numero.equals("\"")) break;
                        c1 = Double.parseDouble(numero);
                        c2 = Double.parseDouble(st.nextToken());
                        c3 = Double.parseDouble(st.nextToken());
                        vert = new Vec3(c1, c2, c3);
                        m.getVertices().add(vert);
                    }     
                    System.out.println(m.getVertices().size());
                }
                else if(tok.equals("<Normal")){
                    aux = stream.readLine();
                    st = new StringTokenizer(aux, " \t");
                    StringTokenizer st2 = new StringTokenizer(st.nextToken(), "\"");
                    String numero = st2.nextToken();
                    numero = st2.nextToken();
                    c1 = Double.parseDouble(numero);
                    c2 = Double.parseDouble(st.nextToken());
                    c3 = Double.parseDouble(st.nextToken());
                    norm = new Vec3(c1, c2, c3);
                    m.getVertexNormals().add(norm);
                    while(true){
                        numero = st.nextToken();
                        if(numero.equals("\"")) break;
                        c1 = Double.parseDouble(numero);
                        c2 = Double.parseDouble(st.nextToken());
                        c3 = Double.parseDouble(st.nextToken());
                        norm = new Vec3(c1, c2, c3);
                        norm.normalize();
                        m.getVertexNormals().add(norm);
                    }  
                    System.out.println(m.getVertexNormals().size());
                }
            }
            System.out.println((indices.size() - indices.size()/4)/3);
            for(int n = 0; n < indices.size(); n += 4){
                Vec3 v1 = m.getVertices().get(indices.get(n));
                Vec3 v2 = m.getVertices().get(indices.get(n + 1));
                Vec3 v3 = m.getVertices().get(indices.get(n + 2));
                Vec3 e1 = v2.subtract(v1);
                Vec3 e2 = v3.subtract(v2);
                Vec3 norma = e1.cross(e2);
                norma.normalize();
                m.getFaceNormals().add(norma);
                TriangularFace tf = new TriangularFace(indices.get(n), 
                                                       indices.get(n + 1),
                                                       indices.get(n + 2),
                                                       m.getFaceNormals().size() - 1);
                m.getFaces().add(tf);
            }
        }catch(IOException e){
            System.err.println("Falha na leitura do arquivo.");
            System.err.println("O programa será encerrado.");
            System.exit(2);
        }            
        return m;
    }   
    
}