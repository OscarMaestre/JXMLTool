package io.github.oscarmaestre.jxmltool;

import io.github.dheid.fontchooser.FontDialog;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xquery.XQException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Interfaz implements ActionListener, MouseListener{
    
    JFrame framePrincipal;
            
    public JTextArea txtXMLAntiguo, txtRestoAntiguo, txtInformes;
    public RSyntaxTextArea txtXML, txtResto;
    public javax.swing.JMenuBar barraMenus;
    
    public javax.swing.JMenu menuArchivo;
    public javax.swing.JMenuItem menuCambiarFuente;
    public javax.swing.JMenuItem menuCambiarFuenteGrande;
    public javax.swing.JMenuItem menuCambiarFuenteMuyGrande;
    public javax.swing.JMenuItem menuCargarDer;
    public javax.swing.JMenuItem menuCargarIzq;
    public javax.swing.JMenuItem menuCopiar;
    public javax.swing.JMenuItem menuCortar;
    public javax.swing.JMenu menuEdicion;
    public javax.swing.JMenuItem menuPegar;
    public javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuEjemploAlumnos;
    private javax.swing.JMenuItem menuEjemploBiblioteca;
    private javax.swing.JMenuItem menuEjemploInventario;
    private javax.swing.JMenuItem menuEjemploPedido;
    private javax.swing.JMenuItem menuEjemploProveedoresPartes;
    private javax.swing.JMenuItem menuEjemploImpresoras;
    private javax.swing.JMenuItem menuEjemploImpresorasConN;
    private javax.swing.JMenuItem menuEjemploTorneo;
    private javax.swing.JMenu menuEjemplos;
    
    private static final int X_CUADRO_XML           = 0;
    private static final int Y_CUADRO_XML           = 0;
    private static final int ANCHO_CUADRO_XML       = 5;
    private static final int ALTO_CUADRO_XML        = 8;
    private static final double PESO_CUADRO_XML     = 0.3;
    
    private static final int X_CUADRO_RESTO      = 6;
    private static final int Y_CUADRO_RESTO      = 0;
    private static final int ANCHO_CUADRO_RESTO  = 5;
    private static final int ALTO_CUADRO_RESTO   = 8;
    private static final double PESO_CUADRO_RESTO= 0.3;
    
    private static final int X_CUADRO_BOTONERA      = 11;
    private static final int Y_CUADRO_BOTONERA      = 0;
    private static final int ANCHO_CUADRO_BOTONERA  = 2;
    private static final int ALTO_CUADRO_BOTONERA   = 8;
    private static final double PESO_CUADRO_BOTONERA= 0.2;
    
    
    private static final int X_CUADRO_INFORMES      = 0;
    private static final int Y_CUADRO_INFORMES      = 9;
    private static final int ANCHO_CUADRO_INFORMES  = 13;
    private static final int ALTO_CUADRO_INFORMES   = 4;
    private static final double PESO_CUADRO_INFORMES= 0.3;
    
    private static final String ACCION_CARGAR_IZQUIERDA = "Cargar izquierda";
    private static final String ACCION_CARGAR_DERECHA   = "Cargar derecha";
    
    private static final String ACCION_VALIDAR_DTD      = "Validar DTD";
    private static final String ACCION_VALIDAR_ESQUEMA  = "Validar esquema";
    private static final String ACCION_EVALUAR_XPATH    = "Evaluar XPATH";
    private static final String ACCION_TRANSFORMAR_XSLT = "Transformar XSLT";
    private static final String ACCION_EJECUTAR_XQUERY  = "Ejecutar XQuery";
    
    private static final String ACCION_PEGAR_VALIDAR_DTD      = "Pegar + Validar DTD";
    private static final String ACCION_PEGAR_VALIDAR_ESQUEMA  = "Pegar + Validar esquema";
    private static final String ACCION_PEGAR_EVALUAR_XPATH    = "Pegar + Evaluar XPATH";
    private static final String ACCION_PEGAR_TRANSFORMAR_XSLT = "Pegar + Transformar XSLT";
    private static final String ACCION_PEGAR_EJECUTAR_XQUERY  = "Pegar + Ejecutar XQuery";
    
    private static final String ACCION_CAMBIAR_FUENTE               = "Cambiar fuente";
    private static final String ACCION_CAMBIAR_FUENTE_GRANDE        = "Cambiar fuente grande";
    private static final String ACCION_CAMBIAR_FUENTE_MUY_GRANDE    = "Cambiar fuente muy grande";
    private static final String ACCION_EJEMPLO_PROVEEDORES_PARTES = 
            "BD Proveedores y partes";
    private static final String ACCION_EJEMPLO_INVENTARIO = 
            "Inventario";
    private static final String ACCION_EJEMPLO_IMPRESORAS = 
            "Impresoras";
    private static final String ACCION_EJEMPLO_IMPRESORAS_CON_N = 
            "ImpresorasConN";
    private static final String ACCION_EJEMPLO_TORNEO = 
            "Torneo";
    private static final String ACCION_EJEMPLO_ALUMNOS = "Alumnos";
    private static final String ACCION_EJEMPLO_PEDIDO = "Pedido";
    private static final String ACCION_EJEMPLO_BIBLIOTECA = "Biblioteca";
    
    
    
    
    private static final int DESTACAR_LINEA_ACTUAL    = 0;
    private static final int DESTACAR_LINEA_ANTERIOR  = 1;
    
    
    public static final int OK                                  = 0;
    public static final int ERROR_XMLSCHEMA_NO_VALIDA_XML       =-1;
    public static final int ERROR_FALLO_TRANSFORMACION_XSLT     =-2;
    
    
    public Interfaz(){
        
    }
    private void    crearMenus(JFrame ventana){
        
         

        barraMenus              = new javax.swing.JMenuBar();
        menuArchivo             = new javax.swing.JMenu();
        menuCargarIzq           = new javax.swing.JMenuItem();
        menuCargarDer           = new javax.swing.JMenuItem();
        menuSalir               = new javax.swing.JMenuItem();
        menuEdicion             = new javax.swing.JMenu();
        menuCortar              = new javax.swing.JMenuItem();
        menuCopiar              = new javax.swing.JMenuItem();
        menuPegar               = new javax.swing.JMenuItem();
        menuCambiarFuente       = new javax.swing.JMenuItem();
        menuCambiarFuenteGrande = new javax.swing.JMenuItem();
        menuCambiarFuenteMuyGrande= new javax.swing.JMenuItem();

        menuEjemplos = new javax.swing.JMenu();
        menuEjemploInventario = new javax.swing.JMenuItem();
        menuEjemploProveedoresPartes = new javax.swing.JMenuItem();
        menuEjemploBiblioteca = new javax.swing.JMenuItem();
        menuEjemploAlumnos = new javax.swing.JMenuItem();
        menuEjemploPedido = new javax.swing.JMenuItem();
        menuEjemploImpresoras = new javax.swing.JMenuItem();
        menuEjemploImpresorasConN = new javax.swing.JMenuItem();
        menuEjemploTorneo = new javax.swing.JMenuItem();

        barraMenus.setName(""); // NOI18N

        menuArchivo.setText("Archivo");
        menuArchivo.setName("menuArchivo"); // NOI18N

        menuCargarIzq.setText("Cargar archivo (izq)");
        menuCargarIzq.setName("menuCargarArchivoIzq"); // NOI18N
        menuArchivo.add(menuCargarIzq);

        menuCargarDer.setText("Cargar archivo (der)");
        menuCargarDer.setName("menuCargarArchivoDer"); // NOI18N
        menuArchivo.add(menuCargarDer);

        menuSalir.setText("Salir");
        menuArchivo.add(menuSalir);

        barraMenus.add(menuArchivo);

        menuEdicion.setText("Edicion");

        menuCortar.setText("Cortar");
        menuEdicion.add(menuCortar);

        menuCopiar.setText("Copiar");
        menuEdicion.add(menuCopiar);

        menuPegar.setText("Pegar");
        menuEdicion.add(menuPegar);

        menuCambiarFuente.setText("Cambiar fuente");
        menuCambiarFuenteGrande.setText("Cambiar fuente grande");
        menuCambiarFuenteMuyGrande.setText("Cambiar fuente muy grande");
        menuEdicion.add(menuCambiarFuente);
        menuEdicion.add(menuCambiarFuenteGrande);
        menuEdicion.add(menuCambiarFuenteMuyGrande);

        barraMenus.add(menuEdicion);

        
        menuEjemplos.setText("Ejemplos");
        menuEjemplos.setName("menuEjemplos"); // NOI18N

        menuEjemploInventario.setText("Inventario");
        menuEjemploInventario.setName("menuEjemploInventario"); // NOI18N
        menuEjemplos.add(menuEjemploInventario);

        menuEjemploProveedoresPartes.setText("Proveedores/partes");
        menuEjemploProveedoresPartes.setName("menuEjemploProveedoresPartes"); // NOI18N
        menuEjemplos.add(menuEjemploProveedoresPartes);

        menuEjemploBiblioteca.setText("Biblioteca");
        menuEjemploBiblioteca.setName("menuEjemploBiblioteca"); // NOI18N
        menuEjemplos.add(menuEjemploBiblioteca);

        menuEjemploAlumnos.setText("Alumnos");
        menuEjemplos.add(menuEjemploAlumnos);

        menuEjemploPedido.setText("Pedido");
        menuEjemplos.add(menuEjemploPedido);

        menuEjemploBiblioteca.setText("Biblioteca");
        menuEjemploBiblioteca.setName("menuEjemploBiblioteca"); // NOI18N
        menuEjemplos.add(menuEjemploBiblioteca);
        
        menuEjemploImpresoras.setText("Impresoras");
        menuEjemploImpresoras.setName("menuEjemploImpresoras"); // NOI18N
        menuEjemplos.add(menuEjemploImpresoras);
        
        menuEjemploImpresorasConN.setText("ImpresorasConN");
        menuEjemploImpresorasConN.setName("menuEjemploImpresorasConN"); // NOI18N
        menuEjemplos.add(menuEjemploImpresorasConN);
        
        menuEjemploTorneo.setText("Torneo");
        menuEjemploTorneo.setName("menuEjemploTorneo"); // NOI18N
        menuEjemplos.add(menuEjemploTorneo);
        

        barraMenus.add(menuEjemplos);
        
        ventana.setJMenuBar(barraMenus);
        
    }
    private void crearInterfaz(Container panel){
        GridBagLayout gridbag=new GridBagLayout();
        panel.setLayout(gridbag);
        
        //Añadimos el cuadro para el XML
        txtXML=new RSyntaxTextArea();
        txtXML.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        txtXML.addMouseListener(this);
        txtXML.setWrapStyleWord(true);
        
        
        JScrollPane scrollXML=new JScrollPane(txtXML);
        GridBagConstraints constraintsCuadroXML=new GridBagConstraints();
        constraintsCuadroXML.gridx=Interfaz.X_CUADRO_XML;
        constraintsCuadroXML.gridy=Interfaz.Y_CUADRO_XML;
        constraintsCuadroXML.gridheight=Interfaz.ALTO_CUADRO_XML;
        constraintsCuadroXML.gridwidth=Interfaz.ANCHO_CUADRO_XML;
        constraintsCuadroXML.fill=GridBagConstraints.BOTH;
        constraintsCuadroXML.weightx=Interfaz.PESO_CUADRO_XML;
        constraintsCuadroXML.weighty=0.8;
        constraintsCuadroXML.insets=new Insets(10, 10, 10, 10);
        txtXML.setText("");
        panel.add(scrollXML, constraintsCuadroXML);
        
        
        txtResto=new RSyntaxTextArea();
        txtResto.addMouseListener(this);
        txtResto.setWrapStyleWord(true);
        JScrollPane scrollResto=new JScrollPane(txtResto);
        GridBagConstraints constraintsCuadroResto=new GridBagConstraints();
        constraintsCuadroResto.gridx=Interfaz.X_CUADRO_RESTO;
        constraintsCuadroResto.gridy=Interfaz.Y_CUADRO_RESTO;
        constraintsCuadroResto.gridheight=Interfaz.ALTO_CUADRO_RESTO;
        constraintsCuadroResto.gridwidth=Interfaz.ANCHO_CUADRO_RESTO;
        constraintsCuadroResto.fill=GridBagConstraints.BOTH;
        constraintsCuadroResto.weightx=Interfaz.PESO_CUADRO_RESTO;
        constraintsCuadroResto.weighty=0.8;
        constraintsCuadroResto.insets=new Insets(10, 10, 10, 10);
        txtResto.setText("");
        panel.add(scrollResto, constraintsCuadroResto);
        
        //Añadimos la botonera de control XML, que es bastante compleja
        //de ahi que lo metamos en un metodo separado
        JPanel botoneraXML=getBotoneraOperacionesXML();
        GridBagConstraints constraintsBotoneraXML=new GridBagConstraints();
        constraintsBotoneraXML.gridx=Interfaz.X_CUADRO_BOTONERA;
        constraintsBotoneraXML.gridy=Interfaz.Y_CUADRO_BOTONERA;
        constraintsBotoneraXML.gridheight=Interfaz.ALTO_CUADRO_BOTONERA;
        constraintsBotoneraXML.gridwidth=Interfaz.ANCHO_CUADRO_BOTONERA;
        constraintsBotoneraXML.fill=GridBagConstraints.BOTH;
        constraintsBotoneraXML.weightx=Interfaz.PESO_CUADRO_BOTONERA;
        constraintsBotoneraXML.weighty=0.8;
        constraintsBotoneraXML.insets=new Insets(10, 10, 10, 10);
        panel.add(botoneraXML, constraintsBotoneraXML);
        
        
        txtInformes=new JTextArea();
        JScrollPane scrollInformes=new JScrollPane(txtInformes);
        GridBagConstraints constraintsCuadroInformes=new GridBagConstraints();
        
        constraintsCuadroInformes.gridx=Interfaz.X_CUADRO_INFORMES;
        constraintsCuadroInformes.gridy=Interfaz.Y_CUADRO_INFORMES;
        constraintsCuadroInformes.gridheight=Interfaz.ALTO_CUADRO_INFORMES;
        constraintsCuadroInformes.gridwidth=Interfaz.ANCHO_CUADRO_INFORMES;
        constraintsCuadroInformes.fill=GridBagConstraints.BOTH;
        constraintsCuadroInformes.weightx=1;
        constraintsCuadroInformes.weighty=0.2;
        constraintsCuadroInformes.insets=new Insets(10, 10, 10, 10);
        txtInformes.setText("Resultados");
        panel.add(scrollInformes, constraintsCuadroInformes);
        
    }
    private void anadirBotonGestionXML(JPanel panel, String texto, String ACCION, int fila, int columna){
        JButton boton=new JButton(texto);
        boton.setActionCommand(ACCION);
        boton.addActionListener(this);
        //btnValidarDTD.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints constraintsBotonDTD=new GridBagConstraints();
        constraintsBotonDTD.fill=GridBagConstraints.BOTH;
        constraintsBotonDTD.weightx=1;
        constraintsBotonDTD.weighty=1;
        constraintsBotonDTD.gridx=columna;
        constraintsBotonDTD.gridy=fila;
        constraintsBotonDTD.insets=new Insets(10, 10, 10, 10);
        panel.add(boton, constraintsBotonDTD);
        
    }
    private JPanel getBotoneraOperacionesXML(){
        JPanel panelBotones=new JPanel();
        GridBagLayout layout=new GridBagLayout();
        panelBotones.setLayout(layout);
        
        this.anadirBotonGestionXML(panelBotones, "Validar con DTD",             ACCION_VALIDAR_DTD, 0, 0);
        this.anadirBotonGestionXML(panelBotones, "Validar con esquema XML",     ACCION_VALIDAR_ESQUEMA, 1, 0);
        this.anadirBotonGestionXML(panelBotones, "Evaluar XPath",               ACCION_EVALUAR_XPATH, 2, 0);
        this.anadirBotonGestionXML(panelBotones, "Transformar con XSLT",        ACCION_TRANSFORMAR_XSLT, 3, 0);
        this.anadirBotonGestionXML(panelBotones, "Ejecutar XQuery",             ACCION_EJECUTAR_XQUERY, 4, 0);
        
        this.anadirBotonGestionXML(panelBotones, "Pegar + Validar con DTD",         ACCION_PEGAR_VALIDAR_DTD,     0, 1);
        this.anadirBotonGestionXML(panelBotones, "Pegar + Validar con esquema XML", ACCION_PEGAR_VALIDAR_ESQUEMA, 1, 1);
        this.anadirBotonGestionXML(panelBotones, "Pegar + Evaluar XPath",           ACCION_PEGAR_EVALUAR_XPATH,   2, 1);
        this.anadirBotonGestionXML(panelBotones, "Pegar + Transformar con XSLT",    ACCION_PEGAR_TRANSFORMAR_XSLT, 3, 1);
        this.anadirBotonGestionXML(panelBotones, "Pegar + Ejecutar XQuery",         ACCION_PEGAR_EJECUTAR_XQUERY, 4, 1);
        
        
        
        return panelBotones;
        
    }
    
    
    public void vincularEventosMenus(){
        /*Si se añade algún menú, poner aquí la acción asociada a ese menú*/
        Map<JMenuItem, String> asociacionesAcciones;
        asociacionesAcciones = new HashMap<JMenuItem, String>();
        
        asociacionesAcciones.put(this.menuCambiarFuente,            Interfaz.ACCION_CAMBIAR_FUENTE);
        asociacionesAcciones.put(this.menuCambiarFuenteGrande,      Interfaz.ACCION_CAMBIAR_FUENTE_GRANDE);
        asociacionesAcciones.put(this.menuCambiarFuenteMuyGrande,   Interfaz.ACCION_CAMBIAR_FUENTE_MUY_GRANDE);
        asociacionesAcciones.put(this.menuEjemploProveedoresPartes, Interfaz.ACCION_EJEMPLO_PROVEEDORES_PARTES);
        asociacionesAcciones.put(this.menuEjemploInventario,        Interfaz.ACCION_EJEMPLO_INVENTARIO);
        asociacionesAcciones.put(this.menuEjemploAlumnos,           Interfaz.ACCION_EJEMPLO_ALUMNOS);
        asociacionesAcciones.put(this.menuEjemploPedido,            Interfaz.ACCION_EJEMPLO_PEDIDO);
        asociacionesAcciones.put(this.menuEjemploBiblioteca,        Interfaz.ACCION_EJEMPLO_BIBLIOTECA);
        asociacionesAcciones.put(this.menuEjemploImpresoras,        Interfaz.ACCION_EJEMPLO_IMPRESORAS);
        asociacionesAcciones.put(this.menuEjemploImpresorasConN,    Interfaz.ACCION_EJEMPLO_IMPRESORAS_CON_N);
        asociacionesAcciones.put(this.menuEjemploTorneo,            Interfaz.ACCION_EJEMPLO_TORNEO);
        asociacionesAcciones.put(this.menuCargarDer,                Interfaz.ACCION_CARGAR_DERECHA);
        asociacionesAcciones.put(this.menuCargarIzq,                Interfaz.ACCION_CARGAR_IZQUIERDA);

        Iterator it=asociacionesAcciones.keySet().iterator();
        /*Recorremos el mapa asociando a cada menu su accion y haciendo que cuando
        sean pulsados se procese el evento en esta clase */
        while (it.hasNext()){
            JMenuItem menu=(JMenuItem) it.next();
            menu.setActionCommand(asociacionesAcciones.get(menu));
            menu.addActionListener(this);
        }
        
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        framePrincipal = new JFrame("JXMLTool");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        crearInterfaz(framePrincipal.getContentPane());
        crearMenus(framePrincipal);
        vincularEventosMenus();
        //Display the window.
        framePrincipal.pack();
        framePrincipal.setVisible(true);
        framePrincipal.setMinimumSize(new Dimension(700, 450));
        
    }

    
    
    private void destacarError (int numLinea, JTextArea control){
        String texto=control.getText();
        int posInicioMarcaError=0;
        int posFinMarcaError=0;
        for (int i=1; i<numLinea; i++){
            posInicioMarcaError=texto.indexOf("\n", posFinMarcaError);
            posFinMarcaError=texto.indexOf("\n", posInicioMarcaError+1);
        }
        control.requestFocusInWindow();
        control.select(posInicioMarcaError, posFinMarcaError);
    }
    
    /* Cuando estamos comprobando si un fichero esta bien formado, la SAXException
    nos dice la linea donde esta el problema. Cuando estamos buscando errores de 
    validacion nos iremos a la linea anterior (pasando una diferencia de 1)
    */
    private void procesarExcepcionParseadoSAX(SAXException ex, int diferencia, JTextArea control){
        if (ex instanceof SAXParseException){
                SAXParseException e=(SAXParseException) ex;
                int lineaError=e.getLineNumber() - diferencia;
                int columnaError=e.getColumnNumber();
                
                String motivo=e.getMessage();
                String error="Error en linea " +
                        lineaError + ", columna "+ columnaError + 
                        ": "+ motivo;
                txtInformes.setText(error);       
                destacarError(lineaError, control);
        }
    }
    private boolean esXMLBienFormado(JTextArea controlTexto){
        
        String xml=controlTexto.getText();
        try {
            /* Primero comprobamos si es XML bien formado*/
            ProcesadorXML.analizarXML(xml, false);
            return true;
        } catch (ParserConfigurationException ex) {
            this.txtInformes.setText("Error desconocido");
            this.txtInformes.append(ex.toString());
        } catch (SAXException ex) {
            this.procesarExcepcionParseadoSAX(ex, DESTACAR_LINEA_ACTUAL, controlTexto);
        } catch (IOException ex) {
            this.txtInformes.setText("Error desconocido");
            this.txtInformes.append(ex.toString());
        }
        return false;
    }
    private void validarConDTD(){
        
        if (!esXMLBienFormado(txtXML)){
            return ;
        } else {
            try {
                
                Document doc=ProcesadorXML.analizarXML(this.txtXML.getText(), false);
                ProcesadorXML.DTDValidaXML(this.txtResto.getText(), doc);
                this.txtInformes.setText("La DTD valida correctamente el fichero");
            } catch (ParserConfigurationException ex) {
                this.txtInformes.setText("Error desconocido");
                this.txtInformes.append(ex.toString());
            } catch (SAXException ex) {
                this.procesarExcepcionParseadoSAX(ex, DESTACAR_LINEA_ANTERIOR, txtXML);
            } catch (IOException ex) {
                this.txtInformes.setText("Error desconocido");
                this.txtInformes.append(ex.toString());
            } catch (TransformerException ex) {
                this.txtInformes.setText("Ha habido un fallo al intentar inyectar la DTD");
            } 
        }
    }
    
    private void validarConEsquema(){
        String xml=txtXML.getText();
        if (!esXMLBienFormado(txtXML)){
            return ;
        }
        
        String xmlSchema=txtResto.getText();
        if (!esXMLBienFormado(txtResto)){
            return ;
        }
        
        try {
            ProcesadorXML.XMLSchemaValidaXML(xmlSchema, xml);
        } catch (SAXException ex) {
            this.procesarExcepcionParseadoSAX(ex, Interfaz.DESTACAR_LINEA_ANTERIOR, txtXML);
            return ;
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Si llegamos aqui todo ha ido bien*/
        txtInformes.setText("El esquema valida correctamente el XML");
    }
    
    private void cargarFichero(JTextArea txt) throws IOException{
        JFileChooser dlgAbrirFichero=new JFileChooser();
        int codigoExito;
        codigoExito = dlgAbrirFichero.showOpenDialog(this.framePrincipal);
        if (codigoExito == JFileChooser.APPROVE_OPTION){
            String path=dlgAbrirFichero.getSelectedFile().getAbsolutePath();
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            String contenidoFichero=new String(encoded, StandardCharsets.UTF_8);
            txt.setText(contenidoFichero);
        }
    }
    
    public String indentarXML(String xml){
        String xmlIndentado="";
        try {
            xmlIndentado=ProcesadorXML.tabularXML(xml);
        } catch (TransformerException ex) {
            return xml;
        }
        return xmlIndentado;
    }
    
    public void pegarTextoPortapapeles(){
        
    try {
        String texto=(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        this.txtResto.setText(texto);
    } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
         this.txtResto.setText("El portapapeles no contiene ningún texto");
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {
        
        this.txtInformes.setText("");
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_VALIDAR_DTD == null : e.getActionCommand().equals(Interfaz.ACCION_VALIDAR_DTD)){
            this.validarConDTD();
        }
        if (e.getActionCommand() == null ? Interfaz.ACCION_PEGAR_VALIDAR_DTD == null : e.getActionCommand().equals(Interfaz.ACCION_PEGAR_VALIDAR_DTD)){
            this.pegarTextoPortapapeles();
            this.validarConDTD();
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_VALIDAR_ESQUEMA == null : e.getActionCommand().equals(Interfaz.ACCION_VALIDAR_ESQUEMA)){
            this.validarConEsquema();
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_PEGAR_VALIDAR_ESQUEMA == null : e.getActionCommand().equals(Interfaz.ACCION_PEGAR_VALIDAR_ESQUEMA)){
            this.pegarTextoPortapapeles();
            this.validarConEsquema();
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EVALUAR_XPATH == null : e.getActionCommand().equals(Interfaz.ACCION_EVALUAR_XPATH)){
            String xpath=txtResto.getText();
            String xml=txtXML.getText();
            
            try {
                txtInformes.setText("");
                
                NodeList resultados=ProcesadorXML.evaluarXPath(xpath, xml);
                String xmlResultado=ProcesadorXML.nodeListToString(resultados);
                txtInformes.setText(xmlResultado);
            } catch (Exception ex) {
                txtInformes.setText(ex.toString());
            }
            
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_PEGAR_EVALUAR_XPATH == null : e.getActionCommand().equals(Interfaz.ACCION_PEGAR_EVALUAR_XPATH)){
            this.pegarTextoPortapapeles();
            System.out.println("Pegado el XPath...");
            String xml=txtXML.getText();
            String xpath=txtResto.getText();
            try {
                txtInformes.setText("");
                
                NodeList resultados=ProcesadorXML.evaluarXPath(xpath, xml);
                String xmlResultado=ProcesadorXML.nodeListToString(resultados);
                txtInformes.setText(xmlResultado);
            } catch (Exception ex) {
                txtInformes.setText(ex.toString());
            }
            
        }
        
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJECUTAR_XQUERY == null : e.getActionCommand().equals(Interfaz.ACCION_EJECUTAR_XQUERY)){
            String xquery=txtResto.getText();
            String xml=txtXML.getText();
            try {
                String resultado=ProcesadorXML.ejecutarXQuery(xquery, xml);
                String resultadoIndentado=indentarXML(resultado);
                txtInformes.setText(resultado);
            } catch (XQException ex) {
                String textoExcepcion=ex.toString();
                String ayudaExtra="Parece que has usado un nombre de archivo que no está en el directorio actual.\n"
                        + "Recuerda que el archivo por defecto se llama 'datos.xml' así que quizá quieras usar algo como doc(\"datos.xml\")//proveedor";
                if (textoExcepcion.contains("java.io.FileNotFoundException")){
                    textoExcepcion=textoExcepcion +"\n\n"+ayudaExtra;
                }
                txtInformes.setText(textoExcepcion);
            } catch (IOException ex) {
                txtInformes.setText(ex.toString());
            }
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_PEGAR_EJECUTAR_XQUERY == null : e.getActionCommand().equals(Interfaz.ACCION_PEGAR_EJECUTAR_XQUERY)){
            this.pegarTextoPortapapeles();
            String xquery=txtResto.getText();
            String xml=txtXML.getText();
            try {
                String resultado=ProcesadorXML.ejecutarXQuery(xquery, xml);
                String resultadoIndentado=indentarXML(resultado);
                txtInformes.setText(resultado);
            } catch (XQException ex) {
                String textoExcepcion=ex.toString();
                String ayudaExtra="Parece que has usado un nombre de archivo que no está en el directorio actual.\n"
                        + "Recuerda que el archivo por defecto se llama 'datos.xml' así que quizá quieras usar algo como doc(\"datos.xml\")//proveedor";
                if (textoExcepcion.contains("java.io.FileNotFoundException")){
                    textoExcepcion=textoExcepcion +"\n\n"+ayudaExtra;
                }
                txtInformes.setText(textoExcepcion);
            } catch (IOException ex) {
                txtInformes.setText(ex.toString());
            }
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_TRANSFORMAR_XSLT == null : e.getActionCommand().equals(Interfaz.ACCION_TRANSFORMAR_XSLT)){
            try {
                String xml          =   txtXML.getText();
                String xslt         =   txtResto.getText();
                String resultado    =   ProcesadorXML.transformarConXSLT(xslt, xml);
                String resultadoEmbellecido;
                resultadoEmbellecido=ProcesadorXML.tabularXML(resultado);
                
                this.almacenarResultadoTransformacion(resultadoEmbellecido);
                txtInformes.append(resultadoEmbellecido);
            } catch (TransformerException ex) {
                txtInformes.setText(ex.toString());
            }
        }

        if (e.getActionCommand() == null ? Interfaz.ACCION_PEGAR_TRANSFORMAR_XSLT == null : e.getActionCommand().equals(Interfaz.ACCION_PEGAR_TRANSFORMAR_XSLT)){
            try {
                this.pegarTextoPortapapeles();
                String xml          =   txtXML.getText();
                String xslt         =   txtResto.getText();
                String resultado    =   ProcesadorXML.transformarConXSLT(xslt, xml);
                String resultadoEmbellecido;
                resultadoEmbellecido=ProcesadorXML.tabularXML(resultado);
                
                this.almacenarResultadoTransformacion(resultadoEmbellecido);
                txtInformes.append(resultadoEmbellecido);
            } catch (TransformerException ex) {
                txtInformes.setText(ex.toString());
            }
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_CAMBIAR_FUENTE == null : e.getActionCommand().equals(Interfaz.ACCION_CAMBIAR_FUENTE)){
            this.cambiarFuenteCuadrosTexto();
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_CAMBIAR_FUENTE_GRANDE == null : e.getActionCommand().equals(Interfaz.ACCION_CAMBIAR_FUENTE_GRANDE)){
            //System.out.println("Cambiando a fuente grande");
            Font fuente=this.txtXML.getFont();
            Font nuevaFuente=fuente.deriveFont(24.0f).deriveFont(Font.BOLD);
            this.txtXML.setFont(nuevaFuente);
            this.txtResto.setFont(nuevaFuente);
            this.txtInformes.setFont(nuevaFuente);
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_CAMBIAR_FUENTE_MUY_GRANDE == null : e.getActionCommand().equals(Interfaz.ACCION_CAMBIAR_FUENTE_MUY_GRANDE)){
            //System.out.println("Cambiando a fuente grande");
            Font fuente=this.txtXML.getFont();
            Font nuevaFuente=fuente.deriveFont(32.0f).deriveFont(Font.BOLD);
            this.txtXML.setFont(nuevaFuente);
            this.txtResto.setFont(nuevaFuente);
            this.txtInformes.setFont(nuevaFuente);
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_PROVEEDORES_PARTES == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_PROVEEDORES_PARTES)){
            String xmlProveedores=ProcesadorXML.getProveedoresPartes();
            this.txtXML.setText(xmlProveedores);
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_INVENTARIO == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_INVENTARIO)){
            String xmlInventario=ProcesadorXML.getXMLEjemploInventario();
            this.txtXML.setText(xmlInventario);
        }
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_ALUMNOS == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_ALUMNOS)){
            String xmlAlumnos=ProcesadorXML.getXMLAlumnosParaXQuery();
            this.txtXML.setText(xmlAlumnos);
        }
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_PEDIDO == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_PEDIDO)){
            String xmlPedido=ProcesadorXML.getXMLejemploDTD();
            this.txtXML.setText(xmlPedido);
        }
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_BIBLIOTECA == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_BIBLIOTECA)){
            String xmlBiblioteca=ProcesadorXML.getXMLEjemploBiblioteca();
            this.txtXML.setText(xmlBiblioteca);
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_IMPRESORAS == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_IMPRESORAS)){
            String xmlImpresoras=ProcesadorXML.getXMLEjemploImpresoras();
            this.txtXML.setText(xmlImpresoras);
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_IMPRESORAS_CON_N == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_IMPRESORAS_CON_N)){
            String xmlImpresoras=ProcesadorXML.getXMLEjemploImpresorasConN();
            this.txtXML.setText(xmlImpresoras);
        }
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJEMPLO_TORNEO == null : e.getActionCommand().equals(Interfaz.ACCION_EJEMPLO_TORNEO)){
            String xmlImpresoras=ProcesadorXML.getXMLEjemploTorneo();
            this.txtXML.setText(xmlImpresoras);
        }
        
        
        if (e.getActionCommand() == null ? Interfaz.ACCION_CARGAR_IZQUIERDA == null : e.getActionCommand().equals(Interfaz.ACCION_CARGAR_IZQUIERDA)){
            try {
                this.cargarFichero(txtXML);
            } catch (IOException ex) {
                txtInformes.setText("No se ha podido leer el fichero");
            }
        }
        if (e.getActionCommand() == null ? Interfaz.ACCION_CARGAR_DERECHA == null : e.getActionCommand().equals(Interfaz.ACCION_CARGAR_DERECHA)){
            try {
                this.cargarFichero(txtResto);
            } catch (IOException ex) {
                txtInformes.setText("No se ha podido leer el fichero");
            }
        }        
    } /*Fin del método*/
    
    public static String getDatosXML(String rutaFichero){
        if (rutaFichero.equals("proveedores")){
            return ProcesadorXML.getProveedoresPartes();
        }
        if (rutaFichero.equals("alumnos")){
            return ProcesadorXML.getXMLAlumnosParaXQuery();
        }
        return ProcesadorXML.leerFichero(rutaFichero);
        
    }
    public static void imprimirOpciones(){
        System.out.println("Uso: JXMLTool.jar (dtd|xsd|xsl|xq) <datos.xml> <fichero>");
    }
    
    public static void XMLSchemaValidaXML(String xmlSchema, String xmlDatos){
        try {
            boolean esValido = ProcesadorXML.XMLSchemaValidaXML(xmlSchema, xmlDatos);
            if (esValido){
                System.exit(OK);
            } else {
                System.exit(Interfaz.ERROR_XMLSCHEMA_NO_VALIDA_XML);
            }
        } catch (SAXException ex) {
            System.exit(Interfaz.ERROR_XMLSCHEMA_NO_VALIDA_XML);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(Interfaz.ERROR_XMLSCHEMA_NO_VALIDA_XML);
        }
        System.exit(OK);
    }
    
    
    public static void main(String[] args) {
        if (args.length>0){
            ProcesadorXMLCLI.despacharArgumentos(args);
            return;
        }
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interfaz i=new Interfaz();
                i.createAndShowGUI();                
            }
        });
    }
    public void cargarEjemploProveedoresPartes(){
        String xmlProveedores           =   ProcesadorXML.getProveedoresPartes();
        txtXML.setText(xmlProveedores);
        txtResto.setText("");
    }
    public void cargarEjemploXSLT(){
        String xmlEjemploInventario     =   ProcesadorXML.getXMLEjemploInventario();
        String xsltEjemploInventario    =   ProcesadorXML.getXSLTEjemploInventario();
        txtXML.setText(xmlEjemploInventario);
        txtResto.setText(xsltEjemploInventario);
        //txtResto.setText("/inventario/producto/peso/text()");
    }
    
    public void cargarEjemploXQuery(){
        String XMLProveedor     =           ProcesadorXML.getProveedoresPartes();
        String xquery           =           ProcesadorXML.getXQueryProveedores();
        txtXML.setText(XMLProveedor);
        txtResto.setText(xquery);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
         if (SwingUtilities.isRightMouseButton(e) && e.getSource() == txtResto){
            try {
                String data = (String) Toolkit.getDefaultToolkit() 
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
                txtResto.setText(data);
            } catch (UnsupportedFlavorException ex) {
                txtInformes.setText("Lo que hay en el portapapeles no es texto, no se puede hacer el pegado");
            } catch (IOException ex) {
                txtInformes.setText("No hay nada en el portapapeles, no se hizo nada");
            }
        }
        if (SwingUtilities.isRightMouseButton(e) && e.getSource() == txtXML){
            try {
                String data = (String) Toolkit.getDefaultToolkit() 
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
                txtXML.setText(data);
            } catch (UnsupportedFlavorException ex) {
                txtInformes.setText("Lo que hay en el portapapeles no es texto, no se puede hacer el pegado");
            } catch (IOException ex) {
                txtInformes.setText("No hay nada en el portapapeles, no se hizo nada");
            }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    private void cambiarFuenteCuadrosTexto(){
        FontDialog dialog = new FontDialog((Frame) null, 
                "Elige una fuente para los cuadros de texto", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (!dialog.isCancelSelected()) {
          Font fuenteElegida=dialog.getSelectedFont();
          txtXML.setFont(fuenteElegida);
          txtResto.setFont(fuenteElegida);
          txtInformes.setFont(fuenteElegida);
        }       
    }

    private void almacenarResultadoTransformacion(String resultado) {
        String nombreFicheroResultado;
        if (resultado.contains("<html>")){
            nombreFicheroResultado="transformacion.html";
        } else {
            nombreFicheroResultado="transformacion.xml";
        }
        try {
            File fichero=new File(nombreFicheroResultado);
            
            FileWriter fw=new FileWriter(fichero);
            fw.write(resultado);
            fw.flush();
            fw.close();
            
            txtInformes.append("\n\n Se ha almacenado el resultado de esta transformacion en:"+
                    fichero.getAbsolutePath()+"\n\n");
        } catch (IOException ex) {
            txtInformes.append("\n\n No se pudo almacenar el resultado de esta transformacion en:"+
                    nombreFicheroResultado);
        }
    }

}
