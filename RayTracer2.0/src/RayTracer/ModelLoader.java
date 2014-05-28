package RayTracer;

import java.io.BufferedReader;

public abstract class ModelLoader {
    protected BufferedReader stream;
    
    public abstract Model load();
}
