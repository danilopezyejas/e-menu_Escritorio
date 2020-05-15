/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Controladores_Interfaces.IAlimentoController;
import Logica.Bebida;
import Logica.Categoria;
import Logica.Fabrica;
import Logica.Plato;
import Logica.enum_Bebida;
import Persistencia.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Danilo
 */
public class Alimentos extends javax.swing.JInternalFrame {
    IAlimentoController alimentoContoller;
    boolean isPlato = true;
    boolean modificar = false;
    IAlimentoController platoController;
    DefaultTableModel md; 
    String data[][]={};
    List<Bebida> bebidas = null;
    List<Plato> platos = null;
    private TextAutoCompleter ac;
    
    
    public Alimentos() {
        initComponents();
        platoController = Fabrica.getInstancia().getAlimentoController();
        alimentoContoller = Fabrica.getInstancia().getAlimentoController();
        this.platos = platoController.listarPlatos();
        this.bebidas = platoController.listarBebidas();
        cargarCategorias();
        cargarBusqueda();
        if(isPlato){
            btnPlatos.setSelected(true);
            cargarPlatos();
        }else{
            btnBebidas.setSelected(true);
            cargarBebidas();
        }
    }
    
    private void cargarCategorias(){
        List<Categoria> categorias =  alimentoContoller.listarCategoria();
        this.categoria.addItem("Elija una categoria");
        for(Categoria aux : categorias){
            this.categoria.addItem(aux.getNombre());
        }
    }
    
    private void cargarPlatos(){
        this.platos = platoController.listarPlatos();
        this.setTitle("Platos");
        tipoTexto.setVisible(false);        //ocultar tipo bebida
        ComboBoxTipo.setVisible(false);     //ocultar tipo bebida
        jLabel6.setText("Calorias:");          //cambio calorias por cantidad
        unidad.setText("kcal.");
        isPlato = true;
  //Le doy formato a la tabla
        String columnas[]={"Id","Nombre","Precio $","Demora min.","Activo","Calorias kcal."};
        md = new DefaultTableModel(data,columnas);
        tabla.setModel(md);
        int anchoTabla = (int)tabla.getPreferredSize().getWidth();
        TableColumnModel tcm = tabla.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(10);
        tcm.getColumn(1).setPreferredWidth(anchoTabla-180);
        tcm.getColumn(2).setPreferredWidth(30);
        tcm.getColumn(3).setPreferredWidth(60);
        tcm.getColumn(4).setPreferredWidth(20);
        tcm.getColumn(5).setPreferredWidth(60);
 //Fin del formato
        tabla.setColumnModel(tcm);
        //lleno la tabla
        for(Plato aux : this.platos){ 
            String datos[]={String.valueOf(aux.getId()),
                aux.getNombre(),
                String.valueOf(aux.getPrecio()),
                String.valueOf(aux.getTiempoPreparacion()),
                String.valueOf(aux.isActivo()),
                String.valueOf(aux.getCalorias())
            };
            md.addRow(datos);
        }
    }
    
    private void cargarBebidas(){ 
        this.bebidas = platoController.listarBebidas();
        this.setTitle("Bebidas");
        tipoTexto.setVisible(true);    //muestro tipo bebida
        ComboBoxTipo.setVisible(true); //muestro tipo bebida
        jLabel6.setText("Cantidad:");          //cambio calorias por cantidad
        unidad.setText("mml.");
        isPlato = false;
        ComboBoxTipo.addItem("Alcoholica");
        ComboBoxTipo.addItem("Analcoholica");
        ComboBoxTipo.addItem("Elaboracion propia");   
//Le doy formato a la tabla
        String columnas[]={"Id","Nombre","Precio $","Demora min.","Contenido","Activo","Tipo"};
        md = new DefaultTableModel(data,columnas);
        tabla.setModel(md);
        int anchoTabla = (int)tabla.getPreferredSize().getWidth();
        TableColumnModel tcm = tabla.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(10);
        tcm.getColumn(1).setPreferredWidth(anchoTabla-300);
        tcm.getColumn(2).setPreferredWidth(40);
        tcm.getColumn(3).setPreferredWidth(60);
        tcm.getColumn(4).setPreferredWidth(60);
        tcm.getColumn(5).setPreferredWidth(20);
        tcm.getColumn(6).setPreferredWidth(60);
 //Fin del formato
        tabla.setColumnModel(tcm);
        //lleno la tabla
        for(Bebida aux : this.bebidas){ 
            String datos[]={String.valueOf(aux.getId()),
                aux.getNombre(),
                String.valueOf(aux.getPrecio()),
                String.valueOf(aux.getTiempoPreparacion()),
                String.valueOf(aux.getCantidad()),
                String.valueOf(aux.isActivo()),
                String.valueOf(aux.getTipo())
            };
            md.addRow(datos);
        }
    }
    
    public Plato buscarPlato(int id){
        for(Plato aux : this.platos){
            if(aux.getId() == id){
                return aux;
            }
        }
        return null;
    }
    public Bebida buscarBebida(int id){
        for(Bebida aux : this.bebidas){
            if(aux.getId() == id){
                return aux;
            }
        }
        return null;
    }
    
    boolean comprobarCampos(){
        Border border = BorderFactory.createLineBorder(Color.decode("#FF0000"));
        boolean retorno = true;
        if(this.nombre.getText().isEmpty()){
            this.nombre.setBorder(border);
            retorno = false;
        }
        if(this.precio.getText().isEmpty()){
            this.precio.setBorder(border);
            retorno = false;
        }
        if(this.calorias.getText().isEmpty()){
            this.calorias.setBorder(border);
            retorno = false;
        }
        if(this.ingredientes.getText().isEmpty()){
            this.ingredientes.setBorder(border);
            retorno = false;
        }
        if(this.tiempoPreparacion.getText().isEmpty()){
            this.tiempoPreparacion.setBorder(border);
            retorno = false;
        }
        if(this.categoria.getSelectedIndex() == 0){
            this.categoria.setBorder(border);
            retorno = false;
        }
        if(retorno)
            return true;
        else
            return false;
    }
    
    void cargarBusqueda(){
        ac = new TextAutoCompleter(this.busqueda);

        for(Bebida aux : this.bebidas){
            ac.addItem(aux.getNombre());
        }
        for(Plato aux : this.platos){
            ac.addItem(aux.getNombre());
        }
    }
    
    void cargarSeleccionado(){
        String nombre = "";
        String precio = "";
        String calorias = "";
        String demora = "";
        String cantidad = "";
        for(Plato aux : this.platos){
            if(aux.getNombre().equals(this.busqueda.getText())){
                nombre = aux.getNombre();
                precio = Float.toString(aux.getPrecio());
                demora = Integer.toString(aux.getTiempoPreparacion());
                calorias = Integer.toString(aux.getCalorias());
                break;
            }
        }
        this.calorias.setText(calorias);
         for(Bebida aux : this.bebidas){
            if(aux.getNombre().equals(this.busqueda.getText())){
                nombre = aux.getNombre();
                precio = Float.toString(aux.getPrecio());
                demora = Integer.toString(aux.getTiempoPreparacion());
                cantidad = Integer.toString(aux.getCantidad());
                break;
            }
        }
        this.nombre.setText(nombre);
        this.precio.setText(precio);
        this.tiempoPreparacion.setText(demora);
        this.ingredientes.setText("tengo que arreglar");
        this.calorias.setText(cantidad);
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

        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nombre = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        ingredientes = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        precio = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tiempoPreparacion = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        calorias = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        foto = new javax.swing.JButton();
        ComboBoxTipo = new javax.swing.JComboBox<>();
        tipoTexto = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        unidad = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnPlatos = new javax.swing.JToggleButton();
        btnBebidas = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        aceptar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        busqueda = new javax.swing.JTextField();

        setResizable(true);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Buscar:");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Precio", "Demora", "Calorias", "Ingredientes", "Title 7", "Title 8"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Nombre: ");

        nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreMouseClicked(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(nombre);

        ingredientes.setColumns(20);
        ingredientes.setRows(5);
        ingredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingredientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ingredientes);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Ingredientes:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Precio:");

        precio.setContentType(""); // NOI18N
        precio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                precioMouseClicked(evt);
            }
        });
        precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(precio);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Demora:");

        tiempoPreparacion.setContentType(""); // NOI18N
        tiempoPreparacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tiempoPreparacionMouseClicked(evt);
            }
        });
        tiempoPreparacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tiempoPreparacionKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(tiempoPreparacion);

        calorias.setContentType(""); // NOI18N
        calorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                caloriasMouseClicked(evt);
            }
        });
        calorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                caloriasKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(calorias);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Calorías:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Fotos");

        foto.setText("Abrir Fotos");

        tipoTexto.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tipoTexto.setText("Tipo");

        categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoriaMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Categoria:");

        unidad.setText("kcal");

        jLabel11.setText("min.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoTexto)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(unidad))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foto))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(unidad)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foto)
                            .addComponent(jLabel8))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(ComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoTexto))
                .addGap(20, 20, 20))
        );

        btnPlatos.setText("Platos");
        btnPlatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlatosActionPerformed(evt);
            }
        });

        btnBebidas.setText("Bebidas");
        btnBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBebidasActionPerformed(evt);
            }
        });

        aceptar.setText("Agregar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(btnModificar)
                .addGap(118, 118, 118)
                .addComponent(eliminar)
                .addGap(117, 117, 117)
                .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(eliminar)
                    .addComponent(btnModificar)
                    .addComponent(salir))
                .addGap(0, 0, 0))
        );

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busquedaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPlatos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBebidas)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnPlatos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        //agregar plato
        String nom = nombre.getText();
        String preString = precio.getText();
        String caloriasSting = calorias.getText();
        String tiempoPrepString = tiempoPreparacion.getText();
        String ingred = ingredientes.getText();
        String categoria = (String)this.categoria.getSelectedItem();
        enum_Bebida a= enum_Bebida.Alcoholica;
        int cal,tiempoPrep,cant;
        float pre;
        if(!comprobarCampos()){
            JOptionPane.showMessageDialog(null,"Datos Incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
        }else{
            if(isPlato){
                pre=Float.parseFloat(preString);
                cal=Integer.parseInt(caloriasSting);
                tiempoPrep= Integer.parseInt(tiempoPrepString);
                alimentoContoller.altaPlato(nom, pre, ingred,tiempoPrep,cal);       
                JOptionPane.showMessageDialog(null,"Plato agregado correctamente");
            }else{
                //preguntar por tipo
                pre=Integer.parseInt(preString);
                cant=Integer.parseInt(caloriasSting);
                tiempoPrep= Integer.parseInt(tiempoPrepString);
               
                int tipoInt=ComboBoxTipo.getSelectedIndex();
                switch(tipoInt){
                 case 0:
                   a= enum_Bebida.Alcoholica;
                    break;
                 case 1:
                   a= enum_Bebida.Analcoholica;
                    break;
                case 2:
                   a= enum_Bebida.elaboracion_propia;
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null,"Seleccione tipo de bebida","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
            }
                
                alimentoContoller.altaBebida(nom, pre, ingred,cant,a,tiempoPrep);       
                JOptionPane.showMessageDialog(null,"Bebida agregada correctamente");
            }
           
        }
        if(isPlato){
            cargarPlatos();
        }else{
            cargarBebidas();
        }
    }//GEN-LAST:event_aceptarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        salir();
    }//GEN-LAST:event_salirActionPerformed

    private void btnPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlatosActionPerformed
        boolean estadoOtro = btnPlatos.isSelected();
        btnBebidas.setSelected(!estadoOtro);
        if(btnPlatos.isSelected()){
            cargarPlatos();
        }else{
            cargarBebidas();
        }
    }//GEN-LAST:event_btnPlatosActionPerformed

    private void btnBebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebidasActionPerformed
       boolean estadoOtro = btnBebidas.isSelected();
       btnPlatos.setSelected(!estadoOtro);
       
       if(btnBebidas.isSelected()){
           cargarBebidas();
        }else{
           cargarPlatos();
        }
    }//GEN-LAST:event_btnBebidasActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        float porcentaje = 0;
        float suba = 0;
        int[] seleccionados = this.tabla.getSelectedRows();
        int cantSelec = seleccionados.length;
        
        if(cantSelec < 1){
            JOptionPane.showMessageDialog(null,"Debe seleccionar al menos un alimento.","ERROR",JOptionPane.ERROR_MESSAGE);
        }else{
            if(cantSelec == 1){  //Si selecciona solo un alimento puede modificar cualquier dato
                int id = Integer.parseInt(this.tabla.getValueAt(seleccionados[0], 0).toString());
                if(isPlato){
                    Plato aModificar = buscarPlato(id);
                    String nuevoNombre = this.nombre.getText();
                    float nuevoPrecio = Float.parseFloat(this.precio.getText());
                    int nuevoTiempo = Integer.parseInt(this.tiempoPreparacion.getText());
                    String nuevoIngredientes = this.ingredientes.getText();
                    aModificar.setNombre(nuevoNombre);
                    aModificar.setPrecio(nuevoPrecio);
                    aModificar.setTiempoPreparacion(nuevoTiempo);
                    aModificar.setIngredientes(nuevoIngredientes);

    //                aModificar.setCategoria(categoria);


                    Conexion.getInstance().modificar(aModificar);
                    cargarPlatos();
                }else{
                    Bebida aModificar = buscarBebida(id);
                    String nuevoNombre = this.nombre.getText();
                    float nuevoPrecio = Float.parseFloat(this.precio.getText());
                    int nuevoTiempo = Integer.parseInt(this.tiempoPreparacion.getText());
                    String nuevoIngredientes = this.ingredientes.getText();
                    aModificar.setNombre(nuevoNombre);
                    aModificar.setPrecio(nuevoPrecio);
                    aModificar.setTiempoPreparacion(nuevoTiempo);
                    aModificar.setIngredientes(nuevoIngredientes);

    //                aModificar.setCategoria(categoria);


                    Conexion.getInstance().modificar(aModificar);
                    cargarBebidas();
                }
            }else{  //si selecciona mas de un alimento solo se va a aumentar el precio
                porcentaje = Integer.parseInt(JOptionPane.showInputDialog("Introduzca un porcentaje (sin %): "));
                if(isPlato){
                    for(int i = 0; i < seleccionados.length; i++ ){
                        int id = Integer.parseInt(this.tabla.getValueAt(seleccionados[i], 0).toString());
                        Plato aModificar = buscarPlato(id);
                        suba = aModificar.getPrecio() + (aModificar.getPrecio() * porcentaje)/100;
                        aModificar.setPrecio(suba);
                        Conexion.getInstance().modificar(aModificar);
                        cargarPlatos();
                    }
                }else{
                    for(int i = 0; i < cantSelec; i++ ){
                        int id = Integer.parseInt(this.tabla.getValueAt(seleccionados[i], 0).toString());
                        Bebida aModificar = buscarBebida(id);
                        suba = aModificar.getPrecio() + (aModificar.getPrecio() * porcentaje)/100;
                        aModificar.setPrecio(suba);
                        Conexion.getInstance().modificar(aModificar);
                        cargarBebidas();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(isPlato){
            int seleccionados = this.tabla.getSelectedRow();
           
            String nombre = (String) this.tabla.getValueAt(seleccionados, 1);
            String precio = (String) this.tabla.getValueAt(seleccionados, 2);
            String calorias = (String) this.tabla.getValueAt(seleccionados, 3);
            String ingredientes = (String) this.tabla.getValueAt(seleccionados, 4);
            String demora = (String) this.tabla.getValueAt(seleccionados, 5);
            this.nombre.setText(nombre);
            this.precio.setText(precio);
            this.tiempoPreparacion.setText(demora);
            this.ingredientes.setText("tengo que arreglar");
            this.calorias.setText(calorias);
        }else{
            int seleccionados = this.tabla.getSelectedRow();
           
            String nombre = (String) this.tabla.getValueAt(seleccionados, 1);
            String precio = (String) this.tabla.getValueAt(seleccionados, 2);
            String demora = (String) this.tabla.getValueAt(seleccionados, 3);
            String cantidad = (String) this.tabla.getValueAt(seleccionados, 4);
            this.nombre.setText(nombre);
            this.precio.setText(precio);
            this.tiempoPreparacion.setText(demora);
            this.ingredientes.setText("tengo que arreglar");
            this.calorias.setText(cantidad);
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int seleccionado = tabla.getSelectedRow();
        int id = Integer.parseInt(this.tabla.getValueAt(seleccionado, 0).toString());
        if(isPlato){
            Conexion.getInstance().baja(buscarPlato(id));
            cargarPlatos();
        }else{
            Conexion.getInstance().baja(buscarBebida(id));
            cargarBebidas();
        }
        JOptionPane.showMessageDialog(null,"Se elimino correctamente.");
    }//GEN-LAST:event_eliminarActionPerformed

    private void nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreMouseClicked
        this.nombre.setBorder(null);
    }//GEN-LAST:event_nombreMouseClicked

    private void precioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_precioMouseClicked
        this.precio.setBorder(null);
    }//GEN-LAST:event_precioMouseClicked

    private void tiempoPreparacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiempoPreparacionMouseClicked
        this.tiempoPreparacion.setBorder(null);
    }//GEN-LAST:event_tiempoPreparacionMouseClicked

    private void caloriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caloriasMouseClicked
        this.calorias.setBorder(null);
    }//GEN-LAST:event_caloriasMouseClicked

    private void ingredientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredientesMouseClicked
        this.ingredientes.setBorder(null);
    }//GEN-LAST:event_ingredientesMouseClicked

    private void categoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriaMouseClicked
        this.categoria.setBorder(null);
    }//GEN-LAST:event_categoriaMouseClicked

    private void tablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseEntered
        tabla.setToolTipText("Para modificar los datos seleccione uno. Si quiere modificar el precio de un conjunto seleccione mas de uno.");
    }//GEN-LAST:event_tablaMouseEntered

    private void busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyTyped
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada == KeyEvent.VK_ENTER)
            cargarSeleccionado();
    }//GEN-LAST:event_busquedaKeyTyped

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada == KeyEvent.VK_TAB)
            this.ingredientes.transferFocus();
    }//GEN-LAST:event_nombreKeyTyped

    private void precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyTyped
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada == KeyEvent.VK_TAB)
            this.precio.transferFocus();
    }//GEN-LAST:event_precioKeyTyped

    private void tiempoPreparacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tiempoPreparacionKeyTyped
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada == KeyEvent.VK_TAB)
            this.tiempoPreparacion.transferFocus();
    }//GEN-LAST:event_tiempoPreparacionKeyTyped

    private void caloriasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caloriasKeyTyped
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada == KeyEvent.VK_TAB)
            this.nombre.transferFocus();
    }//GEN-LAST:event_caloriasKeyTyped


    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxTipo;
    private javax.swing.JButton aceptar;
    private javax.swing.JToggleButton btnBebidas;
    private javax.swing.JButton btnModificar;
    private javax.swing.JToggleButton btnPlatos;
    private javax.swing.JTextField busqueda;
    private javax.swing.JTextPane calorias;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton foto;
    private javax.swing.JTextArea ingredientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextPane nombre;
    private javax.swing.JTextPane precio;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla;
    private javax.swing.JTextPane tiempoPreparacion;
    private javax.swing.JLabel tipoTexto;
    private javax.swing.JLabel unidad;
    // End of variables declaration//GEN-END:variables
}
