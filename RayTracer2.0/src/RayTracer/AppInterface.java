/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

import CustomizedWorld.CameraAdd;
import CustomizedWorld.InputSize;
import CustomizedWorld.LuzAdd;
import CustomizedWorld.ModeloImport;
import CustomizedWorld.Primitiva;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author thiago
 */
public class AppInterface extends javax.swing.JFrame {

    /**
     * Creates new form Interface
     */
    private AppInterface() {
        initComponents();
        System.out.println("Fui chamado");
        customizedWorld = null;
        im = new Image(5,5,"Teste");
    }
    
    public static AppInterface getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new AppInterface();
        }
        return uniqueInstance;
    }
    
    public void setImage(Image i){
        im = i;
    }
    
    public void showImage(){
        if(im == null) {
            return;
        }
        
        ImageIcon icon = new ImageIcon(im.getImage());
        JLabel imageLabel = new JLabel(icon);
        jInternalFrame1.setTitle(im.getName());
        Container contentPane = jInternalFrame1.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);        
    }
    
    public void salvar(){
        if(im == null){
            JOptionPane.showMessageDialog(null,"A cópia que você está tentando salvar não existe", "ERRO",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String fileName = JOptionPane.showInputDialog("Digite o nome que você deseja dar a imagem: ");
        String ext = "bmp";
        File f = new File(fileName + "." + ext);
        try{
            ImageIO.write(im.getImage(), ext, f);
        }catch(IOException e){
            System.err.println("Águia");
        }
        JOptionPane.showMessageDialog(null, "Imagem salva com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jProgressBar1 = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("Arquivo");

        jMenuItem9.setText("Salvar");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cenas Predefinidas");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Cena 1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Cena 2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Cena 3");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Cena Personalizada");

        jMenuItem5.setText("Renderizar cena");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem4.setText("Criar nova cena");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem6.setText("Adicionar Primitiva");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Adicionar Luz");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Configurar Câmera");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem12.setText("Importar Modelo");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.out.println("Entrei");
        RayTracer r = new RayTracer(600,600);
        
        //r.addSphere(new Vec3(250,250,-1000), 75, new Vec3(100.0,0,150.0),0.8,0.2);
        //r.addSphere(new Vec3(350,300,-600), 75, new Vec3(50.0,200.0,50.0),0.7,0.8);
        //r.addSphere(new Vec3(310,310,-1300), 75, new Vec3(255.0,0.0,0.0));
        double color[] = {255, 255, 0, 1};
        //r.addTriangle(new Vec3(256,100,-1000), new Vec3(100,256,-1000), new Vec3(356,256,-1000), color);
        //r.addPlane(new Vec3(1,0,1), new Vec3(500,500,-1800), new Vec3(255,0,0),1.0,0.0);
        
        Vec3 camPos = new Vec3(300, 300, 300);
        Vec3 camDir = new Vec3(0.0, 0.0, -1.0);
        Vec3 camUp = new Vec3(0.0, 1.0, 0.0);
        r.setPerspectiveCamera(camPos, camDir, camUp);
        
        Material m1_red = new Material(new Vec3(255,50,50),0.2,0.8,0,0,1,1,80);
        Material m1_green = new Material(new Vec3(50,255,50),0.2,0.8,0,0,1,1,80);
        Material m1_white = new Material(new Vec3(255,255,255),0.2,0.8,0,0,1,1,80);
        Material m1_white_reflect = new Material(new Vec3(255,255,255),0.2,0.8,0.5,0,0.5,1,80);
        Material m1_gray = new Material(new Vec3(50,50,50),0.2,0.8,0,0,1,1,80);
        Material m1_redwhite = new Material(new Vec3(255,255,0),0.2,0.8,0,0,1,1,80);
        
        Material m2 = new Material(new Vec3(100,100,255),0.8,0.8,0.2,0,0.8,1.49,80);
        Material m3 = new Material(new Vec3(255,50,50),0.1,0.9,0,1.0,0,1.1,10);
        Material m4 = new Material(new Vec3(50,50,255),0.8,0.8,0.8,0,0.8,1.49,80);
        
        r.addAmbient(0.09);
        r.addLight(new Vec3(300,300, 0), new Vec3(255,255,255));
        //r.addLight(new Vec3(300,476, -300), new Vec3(255,255,255));
        //r.addLight(new Vec3(600,-600,500), new Vec3(255,255,255));
        
        r.addPlane(new Vec3( 0,  1, 0), new Vec3(0,100,0),  m1_redwhite);
        r.addPlane(new Vec3( 0, -1, 0), new Vec3(0,400,0),  m1_white_reflect);
        r.addPlane(new Vec3( 0,  0, 1), new Vec3(0,0,-300), m1_white);
        r.addPlane(new Vec3( 0,  0, -1), new Vec3(0,0,300), m1_white);
        r.addPlane(new Vec3(-1,  0, 0), new Vec3(480,0,0),  m1_green);
        r.addPlane(new Vec3( 1,  0, 0), new Vec3(80,0,0),   m1_red);
        
        r.addSphere(new Vec3(200,200,-100), 50, m3);
        r.addSphere(new Vec3(300,200,-200), 50, m2);
        //r.addSphere(new Vec3(300,100,-100), 10, m3);
        //r.addTriangle(new Vec3(300,300,-400), new Vec3(100,200,-400), new Vec3(100,300,-400), color, 0.6, 0.4);       
        
        r.Cast();
        r.assembleIm();
        im = r.getImage();
        showImage();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.out.println("Entrei");
        RayTracer r = new RayTracer(600,600);
        
        //r.addSphere(new Vec3(250,250,-1000), 75, new Vec3(100.0,0,150.0),0.8,0.2);
        //r.addSphere(new Vec3(350,300,-600), 75, new Vec3(50.0,200.0,50.0),0.7,0.8);
        //r.addSphere(new Vec3(310,310,-1300), 75, new Vec3(255.0,0.0,0.0));
        double color[] = {255, 255, 0, 1};
        //r.addTriangle(new Vec3(256,100,-1000), new Vec3(100,256,-1000), new Vec3(356,256,-1000), color);
        //r.addPlane(new Vec3(1,0,1), new Vec3(500,500,-1800), new Vec3(255,0,0),1.0,0.0);
        
        Vec3 camPos = new Vec3(300, 300, 300);
        Vec3 camDir = new Vec3(0.0, 0.0, -1.0);
        Vec3 camUp = new Vec3(0.0, 1.0, 0.0);
        r.setPerspectiveCamera(camPos, camDir, camUp);
        
        Material m1_red = new Material(new Vec3(255,50,50),0.2,0.8,0,0,1,1,80);
        Material m1_green = new Material(new Vec3(50,255,50),0.2,0.8,0,0,1,1,80);
        Material m1_white = new Material(new Vec3(255,255,255),0.2,0.8,0,0,1,1,80);
        Material m1_white_reflect = new Material(new Vec3(255,255,255),0.2,0.8,0.5,0,0.5,1,80);
        Material m1_gray = new Material(new Vec3(50,50,50),0.2,0.8,0,0,1,1,80);
        Material m1_redwhite = new Material(new Vec3(255,255,0),0.2,0.8,0,0,1,1,80);
        
        Material m2 = new Material(new Vec3(100,100,255),0.8,0.8,0.2,0,0.8,1.49,80);
        Material m3 = new Material(new Vec3(255,50,50),0.1,0.9,0,1.0,0,1.1,10);
        Material m4 = new Material(new Vec3(50,50,255),0.8,0.8,0.8,0,0.8,1.49,80);
        
        r.addAmbient(0.09);
        r.addLight(new Vec3(300,300, 0), new Vec3(255,255,255));
        //r.addLight(new Vec3(300,476, -300), new Vec3(255,255,255));
        //r.addLight(new Vec3(600,-600,500), new Vec3(255,255,255));
        
        r.addPlane(new Vec3( 0,  1, 0), new Vec3(0,100,0),  m1_redwhite);
        r.addPlane(new Vec3( 0, -1, 0), new Vec3(0,400,0),  m1_white_reflect);
        r.addPlane(new Vec3( 0,  0, 1), new Vec3(0,0,-300), m1_white);
        r.addPlane(new Vec3( 0,  0, -1), new Vec3(0,0,300), m1_white);
        r.addPlane(new Vec3(-1,  0, 0), new Vec3(480,0,0),  m1_green);
        r.addPlane(new Vec3( 1,  0, 0), new Vec3(80,0,0),   m1_red);
        
        //r.addSphere(new Vec3(200,200,-100), 50, m3);
        //r.addSphere(new Vec3(300,200,-200), 50, m2);
        //r.addSphere(new Vec3(300,100,-100), 10, m3);
        //r.addTriangle(new Vec3(300,300,-400), new Vec3(100,200,-400), new Vec3(100,300,-400), color, 0.6, 0.4);
        
        ObjModelLoader oml = new ObjModelLoader("cube.obj");
        Model m = oml.load();
        m.scale(100, 100, 100);
        m.setMaterial(m2);
        m.translate(100, 100, -100);
        r.addModel(m);
        
        
        r.Cast();
        r.assembleIm();
        im = r.getImage();
        showImage();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        RayTracer r = new RayTracer(600,600);

        double color[] = {255, 255, 0, 1};
        
        Vec3 camPos = new Vec3(0, 0, 100);
        Vec3 camDir = new Vec3(0.0, 0.0, -1.0);
        Vec3 camUp = new Vec3(0.0, 1.0, 0.0);
        r.setPerspectiveCamera(camPos, camDir, camUp);
        
        Material m1_red = new Material(new Vec3(255,50,50),0.2,0.8,0,0,1,1,80);
        Material m1_white_reflect = new Material(new Vec3(255,255,255),0.2,0.8,0.5,0,0.5,1,80);
        Material m1_white = new Material(new Vec3(255,255,255),0.2,0.8,0,0,1,1,80);
        Material m1_refract = new Material(new Vec3(255,50,50),0.1,0.9,0,1.0,0,1.13,10);
        
        r.addSphere(new Vec3(-50,0,-200),40,m1_white_reflect);
        r.addSphere(new Vec3(50,0,-200),40,m1_refract);
        r.addPlane(new Vec3(0,0,1), new Vec3(0,0,-400), m1_red);
        r.addPlane(new Vec3(0,1,0), new Vec3(0,-100,0), m1_red);
        r.addLight(new Vec3(0,150,-100), new Vec3(255,255,255));
        r.Cast();
        r.assembleIm();
        im = r.getImage();
        showImage();        
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //Cena Personalizada 
        
        InputSize is = new InputSize();
        is.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        Primitiva p = new Primitiva();
        p.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        
        LuzAdd lad = new LuzAdd();
        lad.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        
        CameraAdd ca = new CameraAdd();
        ca.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        
        customizedWorld.Cast();
        customizedWorld.assembleIm();
        im = customizedWorld.getImage();
        showImage();
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        salvar();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ModeloImport mi = new ModeloImport();
        mi.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    public RayTracer getWorld(){
        return customizedWorld;
    }
    
    public void setWorld(RayTracer rt){
        
        customizedWorld = rt;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        AppInterface.getInstance().setVisible(true);

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppInterface.getInstance().setVisible(true);
            }
        });*/
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    private static AppInterface uniqueInstance = null;
    private Image im;
    private RayTracer customizedWorld;
}
