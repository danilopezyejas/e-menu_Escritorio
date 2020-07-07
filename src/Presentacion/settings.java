/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author luisg
 */
public class settings extends javax.swing.JInternalFrame {
    int tamanioMaximo = 3145728; //3Megas
    File nuevaImagen=null;
    Color nuevoColor=null;
    /**
     * Creates new form settings
     */
    public settings() {
        initComponents();
    }
    void salir(){
        e_menu m = (e_menu) this.getTopLevelAncestor();
        m.desbloquearFondo();
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnSubir = new javax.swing.JButton();
        verRuta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        save = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        exit.setText("Salir");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Configuración");

        btnSubir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSubir.setText("Subir Imagen");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        verRuta.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Logo Principal");

        save.setText("Guardar");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Color de fondo");

        jButton1.setText("Cambiar Color");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(save))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1)
                        .addGap(0, 250, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSubir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubir)
                    .addComponent(verRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit)
                    .addComponent(save))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        salir();
    }//GEN-LAST:event_exitActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        try{
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("FORMATO IMAGEN", "jpg", "png", "gif", "bmp");
            fc.setFileFilter(filter);
            fc.setDialogTitle("Buscar imagen");
            if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                File imagenElejida = new File(fc.getSelectedFile().toString());
                if(formatoCorrecto(imagenElejida)){
                    this.verRuta.setText(fc.getSelectedFile().getPath());
                    this.nuevaImagen=imagenElejida;
                }else{
                    JOptionPane.showMessageDialog(null,"Seleccione una imagen de hasta 3MB y con extención .png","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (Exception e) {
        }
    }//GEN-LAST:event_btnSubirActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        Boolean ok=false;
        if(this.nuevaImagen!=null){
            BufferedImage image;      
            try {
                image = ImageIO.read(this.nuevaImagen);
                String name = this.nuevaImagen.getName().toLowerCase();
                if(name.endsWith(".png")){
                   ImageIO.write(image, "png",new File("src/img/principal.png"));
                }                         
                ok=true;
            } catch (IOException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(this.nuevoColor!=null){
            e_menu m = (e_menu) this.getTopLevelAncestor();
            m.cambiarColor(this.nuevoColor);
            ok=true;
        }
        if(ok==true){
            JOptionPane.showMessageDialog(this,"Configurado con exito,algunos cambios se veran al reiniciar el programa");
        }else{
            JOptionPane.showMessageDialog(this,"No hay cambios"); 
        }
    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Color newColor = JColorChooser.showDialog(
                     this,
                     "Elige el nuevo color de fondo",
                     this.getBackground());
                if(newColor != null){
                    this.nuevoColor=newColor;
                }
    }//GEN-LAST:event_jButton1ActionPerformed
    public boolean formatoCorrecto(File f) {
        String name = f.getName().toLowerCase();
        return (name.endsWith(".png")&&f.length() < tamanioMaximo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JToggleButton save;
    private javax.swing.JTextField verRuta;
    // End of variables declaration//GEN-END:variables
}
