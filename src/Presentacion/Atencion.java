/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Controladores_Interfaces.IPersonalController;
import Controladores_Interfaces.ictrl_Pedido;
import Hilos.ConsultaPedidos;
import Logica.Fabrica;
import Logica.Mesa;
import Logica.Pedidos;
import Logica.enum_Estado;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class Atencion extends javax.swing.JFrame implements ActionListener{

    ictrl_Pedido controladorPedido = Fabrica.getInstancia().getPedidoController();
    IPersonalController controladorPersonal = Fabrica.getInstancia().getPersonaController();
    static int velocidad = 1;
    static int cerrar = Atencion.DO_NOTHING_ON_CLOSE;;
    boolean cambiar = false;
    boolean setearImagenArregloBotones = true;
    JButton[] arregloBotones;
                ConsultaPedidos hilo1 = new ConsultaPedidos();

    
    public Atencion() {

    }

    Atencion(e_menu menuPrincipal) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/e_menu.png")).getImage());
        this.setLocationRelativeTo(null);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);   //Para que se ejecute maximisado 
        Atencion atencion = this;
        cargarMesas();
//        ConsultaPedidos hilo1 = new ConsultaPedidos();
//        //List<Pedidos> p = hilo1.cargarPedidosPendientes();
//        hilo1.conocerBotones(arregloBotones);
//        hilo1.start();
        
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int result = JOptionPane.showConfirmDialog(
                    null, "Quiere salir del modo Atencion?");
            if( result==JOptionPane.OK_OPTION){
                // Cuando cambie se cierra DO_NOTHING_ON_CLOSE = 0 y DISPOSE_ON_CLOSE = 2
                 cerrar = Atencion.DISPOSE_ON_CLOSE;
            }
                    if(cerrar != 0){
                        hilo1.stop();
                        menuPrincipal.desbloquearFondo();
                        menuPrincipal.setVisible(true);
                        atencion.dispose();
                        atencion.setDefaultCloseOperation(cerrar);
                    }
            }
        });
    }
    
    public void cargarMesas() {
        List<Mesa> mesas = controladorPedido.listarMesas();
        int cantMesas = mesas.size();
        int filas = (int)Math.ceil(Math.sqrt(cantMesas));
        int columnas = filas;
        
        this.panel.setLayout(new GridLayout(filas,columnas,20,20));
        arregloBotones = new JButton[cantMesas];
        
        for(int j=0; j<arregloBotones.length; j++){
            arregloBotones[j] = new JButton();
            arregloBotones[j].setBackground(Color.white);
            arregloBotones[j].setName("btnMesa"+Integer.toString(j+1));
            arregloBotones[j].addActionListener(this);
            this.panel.add(arregloBotones[j]); //Lo agrego al panel si no supere las cantidad de mesas que hay                              //Creo el boton con el icono
        }
        
        //temporizador();
    }
    
    
//Acá campturo un boton cuando se preciona
    @Override
    public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        for(int i=0; i<arregloBotones.length;i++){
            if(b.getName().equals(this.arregloBotones[i].getName())){
                int nromesa = Integer.parseInt(b.getName().replace("btnMesa", ""));
                Pedidos pedido = controladorPedido.obtenerUltimoPedidoPendientePorMesa(nromesa);
                if(pedido != null){
                    
                    Pedido p = new Pedido(pedido);
                    //this.setVisible(false);
                    p.setVisible(true);
//                    String mensaje = "Información del pedido: \n";
//                    //recorro todos los alimentos del pedido 
//                    for (int j = 0; j < pedido.getAlimento().size(); j++) {
//                        Alimento alimento = (Alimento) pedido.getAlimento().get(j);
//                        Integer cantidad;
//                        int key = alimento.getId().intValue();
//                        cantidad = pedido.getAlimentos_cantidad().get(key);
//                        mensaje += cantidad + "|" + alimento.getNombre() + "\n";
//                    }
//
//                    int result = JOptionPane.showConfirmDialog(null, mensaje + "\n¿Quiere tomar el pedido?");
//                    if( result==JOptionPane.OK_OPTION){
//                        int idPersonal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su número de ID"));
//                        Personal mozo = controladorPersonal.buscarPersonalPorId(idPersonal);
//                        pedido.setPersonal(mozo);
//                        pedido.setEstado(enum_Estado.Activo);
//                        JOptionPane.showMessageDialog(new Frame(),"Usted ha tomado el pedido!","Información",JOptionPane.INFORMATION_MESSAGE);  
//                    }
                }else{
                    pedido = controladorPedido.obtenerUltimoPedidoSinPagarPorMesa(nromesa);
                    if (pedido != null){
                        int result = JOptionPane.showConfirmDialog(null,"\n¿Quiere finalizar esta pedido?");
                        if( result==JOptionPane.OK_OPTION){
                            pedido.setEstado(enum_Estado.Finalizado);
                            JOptionPane.showMessageDialog(new Frame(),"Pedido finalizado.","Información",JOptionPane.INFORMATION_MESSAGE);  
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No hay pedidos pendientes en esta mesa.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
    
    void temporizador(){
        Timer timer;
        TimerTask tarea;
        int parpadeo = velocidad*5000;
        
        tarea = new TimerTask(){
            @Override
            public void run() {
                JButton b= (JButton) panel.getComponent(1);
                 if(cambiar){
                    ImageIcon icon = new ImageIcon("img/Mesa con pedido 1.png");
                    b.setIcon(icon);
                    cambiar =false;
                }else{
                    ImageIcon icon = new ImageIcon("img/Mesa con pedido 2.png");
                    b.setIcon(icon);
                    cambiar =true;
                }
            }
        };
        timer = new Timer();
        
        timer.scheduleAtFixedRate(tarea, 0, parpadeo);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1311, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        if (setearImagenArregloBotones == true){
            for(int j=0; j<arregloBotones.length; j++){
                ImageIcon icon = new ImageIcon (new ImageIcon("img/mesa_Libre.png").getImage().getScaledInstance(arregloBotones[j].getHeight()-20, arregloBotones[j].getHeight()-20, Image.SCALE_DEFAULT));
                arregloBotones[j].setIcon(icon);                              //Creo el boton con el icono
            }
        //List<Pedidos> p = hilo1.cargarPedidosPendientes();
        hilo1.conocerBotones(arregloBotones);
        hilo1.start();
            setearImagenArregloBotones = false;
        }
    }//GEN-LAST:event_formWindowGainedFocus

    void ejecutarPanel(javax.swing.JInternalFrame obj){
        this.panel.add(obj);
        obj.setVisible(true);
        centrarInternal(obj);
        mandarAlFrente(obj);
    }
    
    void mandarAlFrente(javax.swing.JInternalFrame obj){
        Container parent = obj.getParent();
        synchronized (parent.getTreeLock()) {
        parent.setComponentZOrder(obj, 1);  //Para que se muestre por arriba de lo demas
  
    }
    }

    public void centrarInternal(javax.swing.JInternalFrame o) {
        int x = this.panel.getWidth() / 2 - o.getWidth() / 2;
        int y = this.panel.getHeight() / 2 - o.getHeight() / 2;
        if (this.panel.isShowing()) {
            o.setLocation(x, y);
        }
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
            java.util.logging.Logger.getLogger(Atencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Atencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Atencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Atencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Atencion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
