/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class e_menu extends javax.swing.JFrame {

    Mesas am = null;
    Personal_Vistas ap = null;
    settings set = null;
    Alimentos apl = null;
    Atencion atender = null;
    File archivo=null;
    FileInputStream entrada;
    FileOutputStream salida;
    
    public e_menu() {
        initComponents();       
        this.setLocationRelativeTo(null); 
        //ESTO LO DESCOMENTE 
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconoPrincipal();
        setIconoAtender();
        setIconImage(new ImageIcon(getClass().getResource("/img/e_menu.png")).getImage());
        ajustarIconos();
        crearArchivoConfiguracion();
    }
    public void setIconoPrincipal(){
        ImageIcon fot = new javax.swing.ImageIcon(getClass().getResource("/img/principal.png"));
        int max = 200;                      //tamaño maximo en pixeles
        int alto = fot.getIconHeight();
        int ancho = fot.getIconWidth();
        
        if(ancho>alto){  //caso 2
            float porcentaje;
            porcentaje=(int)((max*100)/ancho);
            ancho=max;
            alto=(int)((porcentaje*0.01f)*alto);
        }else if(alto>ancho){   //caso 3
            max+=50;
            float porcentaje;
            porcentaje=(int)((max*100)/alto);
            alto=max;
            ancho=(int)((porcentaje*0.01f)*ancho);
        }else{
            alto=max-30;
            ancho=max-30;
        }
            

        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        jLabel2.setIcon(icono);
    }
    public void setIconoAtender(){
        ImageIcon fot = new javax.swing.ImageIcon(getClass().getResource("/img/secundaria.png"));
        int max = 200;                      //tamaño maximo en pixeles
        int alto = fot.getIconHeight();
        int ancho = fot.getIconWidth();
        
        if(ancho>alto){  //caso 2
            float porcentaje;
            porcentaje=(int)((max*100)/ancho);
            ancho=max;
            alto=(int)((porcentaje*0.01f)*alto);
        }else if(alto>ancho){   //caso 3
            max+=50;
            float porcentaje;
            porcentaje=(int)((max*100)/alto);
            alto=max;
            ancho=(int)((porcentaje*0.01f)*ancho);
        }else{
            alto=max-30;
            ancho=max-30;
        }
            

        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        btnAtender.setIcon(icono);
    }
     public void ajustarIconos(){
        int escalaIconos = 25;
        
        //Iconos Personal
        ImageIcon subimagen = new ImageIcon("src/iconos/ic_group_add_black_48dp.png");
        ImageIcon subimagen1 = new ImageIcon("src/iconos/ic_group_black_48dp.png");
        ImageIcon subimagen2 = new ImageIcon("src/iconos/ic_perm_identity_black_48dp.png" );  
        Icon subicono = new ImageIcon(subimagen.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        Icon subicono1 = new ImageIcon(subimagen1.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        Icon subicono2 = new ImageIcon(subimagen2.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        ImageIcon subimagen4 = new ImageIcon("src/iconos/ic_assignment_turned_in_black_48dp.png");
        Icon subicono4 = new ImageIcon(subimagen4.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        btnAlimentos.setIcon(subicono4);
        //Iconos Mesa
        ImageIcon subimagen7 = new ImageIcon("src/iconos/ic_queue_play_next_black_48dp.png");
        ImageIcon subimagen8 = new ImageIcon("src/iconos/ic_apps_black_48dp.png");
        ImageIcon subimagen9 = new ImageIcon("src/iconos/ic_remove_from_queue_black_48dp.png");   
        Icon subicono7 = new ImageIcon(subimagen7.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        Icon subicono8 = new ImageIcon(subimagen8.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        Icon subicono9 = new ImageIcon(subimagen9.getImage().getScaledInstance(escalaIconos,escalaIconos,Image.SCALE_DEFAULT));
        
        this.repaint();
        
    }
    public void centrarInternal(javax.swing.JInternalFrame o) {
        int x = this.panel.getWidth() / 2 - o.getWidth() / 2;
        int y = this.panel.getHeight() / 2 - o.getHeight() / 2;
        if (this.panel.isShowing()) {
            o.setLocation(x, y);
        }
    }
    
    public void bloquearFondo() {
        btnMesa.setEnabled(false);
        btnPersonal.setEnabled(false);
        jButton2.setEnabled(false);
        btnAlimentos.setEnabled(false);
        pnlMenu.setVisible(false);
    }
    
    public void desbloquearFondo() {
        btnMesa.setEnabled(true);
        btnPersonal.setEnabled(true);
        btnAlimentos.setEnabled(true);
        pnlMenu.setVisible(true);
        jButton2.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        panel = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAlimentos = new javax.swing.JButton();
        btnMesa = new javax.swing.JButton();
        btnPersonal = new javax.swing.JButton();
        btnAtender = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(255, 0, 51));

        pnlMenu.setBackground(new java.awt.Color(255, 0, 51));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/elbar.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Categoria");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAlimentos.setBackground(new java.awt.Color(0, 153, 153));
        btnAlimentos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAlimentos.setForeground(new java.awt.Color(255, 255, 255));
        btnAlimentos.setText("Alimentos");
        btnAlimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlimentosActionPerformed(evt);
            }
        });

        btnMesa.setBackground(new java.awt.Color(0, 153, 153));
        btnMesa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnMesa.setForeground(new java.awt.Color(255, 255, 255));
        btnMesa.setText("Mesa");
        btnMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesaActionPerformed(evt);
            }
        });

        btnPersonal.setBackground(new java.awt.Color(0, 153, 153));
        btnPersonal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPersonal.setForeground(new java.awt.Color(255, 255, 255));
        btnPersonal.setText("Personal");
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAlimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );

        btnAtender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/atender.png"))); // NOI18N
        btnAtender.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenderActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(456, Short.MAX_VALUE)
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        Atencion n = new Atencion(this);
        this.setVisible(false);
        n.setVisible(true);
    }//GEN-LAST:event_btnAtenderActionPerformed

    private void btnMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesaActionPerformed
        Mesas am = new Mesas();
        this.am = am;
        ejecutarPanel(am);
    }//GEN-LAST:event_btnMesaActionPerformed

    private void btnAlimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlimentosActionPerformed
        mostrarPlatos();
    }//GEN-LAST:event_btnAlimentosActionPerformed
    public void mostrarPlatos(){
        Alimentos apl = new Alimentos();
        this.apl = apl;
        ejecutarPanel(apl);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Categorias c = new Categorias();
        ejecutarPanel(c);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        Personal_Vistas ap = new Personal_Vistas();
        this.ap = ap;
        ejecutarPanel(ap);
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        settings set = new settings();
        this.set = set;
        ejecutarPanel(set);
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public void ejecutarPanel(javax.swing.JInternalFrame obj){
        panel.add(obj);
        obj.setVisible(true);
        centrarInternal(obj);
        bloquearFondo();
    }
    public String GuardarArchivo(File archivo,String documento){
        String mensaje=null;
        try{
            salida=new FileOutputStream(archivo);
            byte[] bytxt=documento.getBytes();
            salida.write(bytxt);
            mensaje="Archivo guardado";
        }catch(Exception e){
        
        }
        return mensaje;
    }
    private void crearArchivoConfiguracion() {
        archivo =new File("preferences.txt");
        try{
        if (archivo.createNewFile()) {
            cambiarColor(new java.awt.Color(255, 0, 51));
        } else {
            cambiarColor(leerArchivo(archivo));
        }
        }catch(Exception e){
        
        }
    }
    public void cambiarColor(Color nuevo) {
        GuardarArchivo(archivo,
                String.valueOf(nuevo.getRed())+"-"+
                String.valueOf(nuevo.getGreen())+"-"+
                String.valueOf(nuevo.getBlue())+
                "/"
        );
       
        panel.setBackground(nuevo);
        pnlMenu.setBackground(nuevo);
    }
    public Color leerArchivo(File arch){
        String documento="";
        try{
            entrada = new FileInputStream(arch);
            int ascci;
            while((ascci=entrada.read())!=-1){
                char caracter=(char)ascci;
                documento+=caracter;
            }
        }catch(Exception e){
            
        }
        String[] colorFondo=documento.split("/");
        String[] cadaColor=colorFondo[0].split("-");
        int r=Integer.parseInt(cadaColor[0]);
        int g=Integer.parseInt(cadaColor[1]);
        int b=Integer.parseInt(cadaColor[2]);
        //System.out.println(documento);
        return new java.awt.Color(r, g, b);
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
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            
            /*for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(e_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(e_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(e_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(e_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new e_menu().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlimentos;
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnMesa;
    private javax.swing.JButton btnPersonal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables

    
}
