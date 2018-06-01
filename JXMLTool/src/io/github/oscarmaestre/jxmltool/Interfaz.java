package io.github.oscarmaestre.jxmltool;

import io.github.dheid.fontchooser.FontDialog;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
    private javax.swing.JMenu menuEjemplos;
    
    private static final int X_CUADRO_XML      = 0;
    private static final int Y_CUADRO_XML      = 0;
    private static final int ANCHO_CUADRO_XML  = 5;
    private static final int ALTO_CUADRO_XML   = 8;
    private static final double PESO_CUADRO_XML    = 0.3;
    
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
    private static final String ACCION_TRANSFORMAR_XSLT ="Transformar XSLT";
    private static final String ACCION_EJECUTAR_XQUERY  = "Ejecutar XQuery";
    
    
    private static final String ACCION_CAMBIAR_FUENTE = "Cambiar fuente";
    private static final String ACCION_EJEMPLO_PROVEEDORES_PARTES = 
            "BD Proveedores y partes";
    private static final String ACCION_EJEMPLO_INVENTARIO = 
            "Inventario";
    private static final String ACCION_EJEMPLO_ALUMNOS = "Alumnos";
    private static final String ACCION_EJEMPLO_PEDIDO = "Pedido";
    private static final String ACCION_EJEMPLO_BIBLIOTECA = "Biblioteca";
    
    
    
    
    private static int DESTACAR_LINEA_ACTUAL    = 0;
    private static int DESTACAR_LINEA_ANTERIOR  = 1;
    
    public Interfaz(){
        
    }
    private void    crearMenus(JFrame ventana){
        
         

        barraMenus = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuCargarIzq = new javax.swing.JMenuItem();
        menuCargarDer = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();
        menuEdicion = new javax.swing.JMenu();
        menuCortar = new javax.swing.JMenuItem();
        menuCopiar = new javax.swing.JMenuItem();
        menuPegar = new javax.swing.JMenuItem();
        menuCambiarFuente = new javax.swing.JMenuItem();

        menuEjemplos = new javax.swing.JMenu();
        menuEjemploInventario = new javax.swing.JMenuItem();
        menuEjemploProveedoresPartes = new javax.swing.JMenuItem();
        menuEjemploBiblioteca = new javax.swing.JMenuItem();
        menuEjemploAlumnos = new javax.swing.JMenuItem();
        menuEjemploPedido = new javax.swing.JMenuItem();
        

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
        menuEdicion.add(menuCambiarFuente);

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
    
    private JPanel getBotoneraOperacionesXML(){
        JPanel panelBotones=new JPanel();
        GridBagLayout layout=new GridBagLayout();
        panelBotones.setLayout(layout);
        
        JButton btnValidarDTD=new JButton("Validar con DTD");
        btnValidarDTD.setActionCommand(ACCION_VALIDAR_DTD);
        btnValidarDTD.addActionListener(this);
        //btnValidarDTD.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints constraintsBotonDTD=new GridBagConstraints();
        constraintsBotonDTD.fill=GridBagConstraints.BOTH;
        constraintsBotonDTD.weightx=1;
        constraintsBotonDTD.weighty=1;
        constraintsBotonDTD.gridx=0;
        constraintsBotonDTD.gridy=0;
        constraintsBotonDTD.insets=new Insets(10, 10, 10, 10);
        panelBotones.add(btnValidarDTD, constraintsBotonDTD);
        
        
        JButton btnValidarEsquema=new JButton("Validar con esquema XML");
        btnValidarEsquema.setActionCommand(ACCION_VALIDAR_ESQUEMA);
        btnValidarEsquema.addActionListener(this);
        //btnValidarESQUEMA.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints constraintsBotonEsquema=new GridBagConstraints();
        constraintsBotonEsquema.fill=GridBagConstraints.BOTH;
        constraintsBotonEsquema.gridx=0;
        constraintsBotonEsquema.gridy=1;
        constraintsBotonEsquema.weightx=1;
        constraintsBotonEsquema.weighty=1;
        constraintsBotonEsquema.insets=new Insets(10, 10, 10, 10);
        panelBotones.add(btnValidarEsquema, constraintsBotonEsquema);
        
        
        JButton btnEvaluarXPath=new JButton("Evaluar XPath");
        btnEvaluarXPath.setActionCommand(ACCION_EVALUAR_XPATH);
        btnEvaluarXPath.addActionListener(this);
        //btnValidarXPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints constraintsBotonXPath=new GridBagConstraints();
        constraintsBotonXPath.fill=GridBagConstraints.BOTH;
        constraintsBotonXPath.gridx=0;
        constraintsBotonXPath.gridy=2;
        constraintsBotonXPath.weightx=1;
        constraintsBotonXPath.weighty=1;
        constraintsBotonXPath.insets=new Insets(10, 10, 10, 10);
        panelBotones.add(btnEvaluarXPath, constraintsBotonXPath);
        
        
        JButton btnTransformarXSLT=new JButton("Transformar con XSLT");
        btnTransformarXSLT.setActionCommand(ACCION_TRANSFORMAR_XSLT);
        btnTransformarXSLT.addActionListener(this);
        //btnValidarXSLT.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints constraintsBotonXSLT=new GridBagConstraints();
        constraintsBotonXSLT.fill=GridBagConstraints.BOTH;
        constraintsBotonXSLT.gridx=0;
        constraintsBotonXSLT.gridy=3;
        constraintsBotonXSLT.weightx=1;
        constraintsBotonXSLT.weighty=1;
        constraintsBotonXSLT.insets=new Insets(10, 10, 10, 10);
        panelBotones.add(btnTransformarXSLT, constraintsBotonXSLT);
        
        
        JButton btnEjecutarXQuery=new JButton("Ejecutar XQuery");
        btnEjecutarXQuery.addActionListener(this);
        btnEjecutarXQuery.setActionCommand(ACCION_EJECUTAR_XQUERY);
        //btnValidarXQuery.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints constraintsBotonXQuery=new GridBagConstraints();
        constraintsBotonXQuery.fill=GridBagConstraints.BOTH;
        constraintsBotonXQuery.gridx=0;
        constraintsBotonXQuery.gridy=4;
        constraintsBotonXQuery.weightx=1;
        constraintsBotonXQuery.weighty=1;
        constraintsBotonXQuery.insets=new Insets(10, 10, 10, 10);
        panelBotones.add(btnEjecutarXQuery, constraintsBotonXQuery);
        
        return panelBotones;
        
    }
    
    
    public void vincularEventosMenus(){
        this.menuCambiarFuente.setActionCommand(Interfaz.ACCION_CAMBIAR_FUENTE);
        this.menuCambiarFuente.addActionListener(this);
        
        this.menuEjemploProveedoresPartes.setActionCommand(Interfaz.ACCION_EJEMPLO_PROVEEDORES_PARTES);
        this.menuEjemploProveedoresPartes.addActionListener(this);
        
        this.menuEjemploInventario.setActionCommand(Interfaz.ACCION_EJEMPLO_INVENTARIO);
        this.menuEjemploInventario.addActionListener(this);
        
        this.menuEjemploAlumnos.setActionCommand(Interfaz.ACCION_EJEMPLO_ALUMNOS);
        this.menuEjemploAlumnos.addActionListener(this);
        
        this.menuEjemploPedido.setActionCommand(Interfaz.ACCION_EJEMPLO_PEDIDO);
        this.menuEjemploPedido.addActionListener(this);
        
        this.menuEjemploBiblioteca.setActionCommand(Interfaz.ACCION_EJEMPLO_BIBLIOTECA);
        this.menuEjemploBiblioteca.addActionListener(this);
        
        this.menuCargarDer.setActionCommand(Interfaz.ACCION_CARGAR_DERECHA);
        this.menuCargarDer.addActionListener(this);
        
        this.menuCargarIzq.setActionCommand(Interfaz.ACCION_CARGAR_IZQUIERDA);
        this.menuCargarIzq.addActionListener(this);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        this.txtInformes.setText("");
        if (e.getActionCommand() == null ? Interfaz.ACCION_VALIDAR_DTD == null : e.getActionCommand().equals(Interfaz.ACCION_VALIDAR_DTD)){
            this.validarConDTD();
        }
        if (e.getActionCommand() == null ? Interfaz.ACCION_VALIDAR_ESQUEMA == null : e.getActionCommand().equals(Interfaz.ACCION_VALIDAR_ESQUEMA)){
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
        if (e.getActionCommand() == null ? Interfaz.ACCION_EJECUTAR_XQUERY == null : e.getActionCommand().equals(Interfaz.ACCION_EJECUTAR_XQUERY)){
            String xquery=txtResto.getText();
            String xml=txtXML.getText();
            try {
                String resultado=ProcesadorXML.ejecutarXQuery(xquery, xml);
                String resultadoIndentado=indentarXML(resultado);
                txtInformes.setText(resultado);
            } catch (XQException ex) {
                txtInformes.setText(ex.toString());
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
                txtInformes.setText(resultadoEmbellecido);
            } catch (TransformerException ex) {
                txtInformes.setText(ex.toString());
            }
        }        
        if (e.getActionCommand() == null ? Interfaz.ACCION_CAMBIAR_FUENTE == null : e.getActionCommand().equals(Interfaz.ACCION_CAMBIAR_FUENTE)){
            this.cambiarFuenteCuadrosTexto();
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
        
        
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
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
        txtResto.setText("/inventario/producto/peso/text()");
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

}
