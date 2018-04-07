package io.github.oscarmaestre.jxmltool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Interfaz implements ActionListener, MouseListener{
    public JTextArea txtXML, txtResto, txtInformes;
    
    private static final int X_CUADRO_XML      = 0;
    private static final int Y_CUADRO_XML      = 0;
    private static final int ANCHO_CUADRO_XML  = 5;
    private static final int ALTO_CUADRO_XML   = 8;
    private static final double PESO_CUADRO_XML    = 0.4;
    
    private static final int X_CUADRO_RESTO      = 6;
    private static final int Y_CUADRO_RESTO      = 0;
    private static final int ANCHO_CUADRO_RESTO  = 5;
    private static final int ALTO_CUADRO_RESTO   = 8;
    private static final double PESO_CUADRO_RESTO= 0.4;
    
    private static final int X_CUADRO_BOTONERA      = 11;
    private static final int Y_CUADRO_BOTONERA      = 0;
    private static final int ANCHO_CUADRO_BOTONERA  = 2;
    private static final int ALTO_CUADRO_BOTONERA   = 8;
    private static final double PESO_CUADRO_BOTONERA= 0.2;
    
    
    private static final int X_CUADRO_INFORMES      = 0;
    private static final int Y_CUADRO_INFORMES      = 9;
    private static final int ANCHO_CUADRO_INFORMES  = 13;
    private static final int ALTO_CUADRO_INFORMES   = 4;
    private static final double PESO_CUADRO_INFORMES= 0.2;
    
    private static final String VALIDAR_DTD = "Validar DTD";
    private static final String VALIDAR_ESQUEMA = "Validar esquema";
    private static final String EVALUAR_XPATH = "Evaluar XPATH";
    private static final String TRANSFORMAR_XSLT="Transformar XSLT";
    private static final String EJECUTAR_XQUERY = "Ejecutar XQuery";
    
    private static int DESTACAR_LINEA_ACTUAL    = 0;
    private static int DESTACAR_LINEA_ANTERIOR  = 1;
    
    public Interfaz(){
        
    }
    
    private void crearInterfaz(Container panel){
        GridBagLayout gridbag=new GridBagLayout();
        panel.setLayout(gridbag);
        
        //Añadimos el cuadro para el XML
        txtXML=new JTextArea();
        txtXML.addMouseListener(this);
        txtXML.setWrapStyleWord(true);
        GridBagConstraints constraintsCuadroXML=new GridBagConstraints();
        constraintsCuadroXML.gridx=Interfaz.X_CUADRO_XML;
        constraintsCuadroXML.gridy=Interfaz.Y_CUADRO_XML;
        constraintsCuadroXML.gridheight=Interfaz.ALTO_CUADRO_XML;
        constraintsCuadroXML.gridwidth=Interfaz.ANCHO_CUADRO_XML;
        constraintsCuadroXML.fill=GridBagConstraints.BOTH;
        constraintsCuadroXML.weightx=Interfaz.PESO_CUADRO_XML;
        constraintsCuadroXML.weighty=0.8;
        constraintsCuadroXML.insets=new Insets(10, 10, 10, 10);
        txtXML.setText("Hola mundo");
        panel.add(txtXML, constraintsCuadroXML);
        
        
        txtResto=new JTextArea();
        txtResto.addMouseListener(this);
        txtResto.setWrapStyleWord(true);
        GridBagConstraints constraintsCuadroResto=new GridBagConstraints();
        constraintsCuadroResto.gridx=Interfaz.X_CUADRO_RESTO;
        constraintsCuadroResto.gridy=Interfaz.Y_CUADRO_RESTO;
        constraintsCuadroResto.gridheight=Interfaz.ALTO_CUADRO_RESTO;
        constraintsCuadroResto.gridwidth=Interfaz.ANCHO_CUADRO_RESTO;
        constraintsCuadroResto.fill=GridBagConstraints.BOTH;
        constraintsCuadroResto.weightx=Interfaz.PESO_CUADRO_RESTO;
        constraintsCuadroResto.weighty=0.8;
        constraintsCuadroResto.insets=new Insets(10, 10, 10, 10);
        txtResto.setText("Soy la DTD");
        panel.add(txtResto, constraintsCuadroResto);
        
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
        panel.add(txtInformes, constraintsCuadroInformes);
        
    }
    
    private JPanel getBotoneraOperacionesXML(){
        JPanel panelBotones=new JPanel();
        GridBagLayout layout=new GridBagLayout();
        panelBotones.setLayout(layout);
        
        JButton btnValidarDTD=new JButton("Validar con DTD");
        btnValidarDTD.setActionCommand(VALIDAR_DTD);
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
        btnValidarEsquema.setActionCommand(VALIDAR_ESQUEMA);
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
        btnEvaluarXPath.setActionCommand(EVALUAR_XPATH);
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
        btnTransformarXSLT.setActionCommand(TRANSFORMAR_XSLT);
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
        btnEjecutarXQuery.setActionCommand(EJECUTAR_XQUERY);
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
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("JXMLTool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        crearInterfaz(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(700, 450));
    }

    
    
    private void destacarError (int numLinea, JTextArea control){
        String texto=control.getText();
        int posInicioMarcaError=0;
        int posFinMarcaError=0;
        for (int i=1; i<numLinea; i++){
            posInicioMarcaError=texto.indexOf("\n", posFinMarcaError);
            posFinMarcaError=texto.indexOf("\n", posInicioMarcaError+1);
        }
        
        System.out.println("Marcando en:"+posInicioMarcaError+" hasta " + posFinMarcaError);
        control.requestFocusInWindow();
        control.select(posInicioMarcaError, posFinMarcaError);
    }
    
    /* Cuando estamos comprobando si un fichero esta bien formado, la SAXException
    nos dice la linea donde esta el problema. Cuando estamos buscando errores de 
    validacion nos iremos a la linea anterior (pasando una diferencia de 1)
    */
    private void procesarExcepcionParseadoSAX(SAXException ex, int diferencia){
        if (ex instanceof SAXParseException){
                SAXParseException e=(SAXParseException) ex;
                int lineaError=e.getLineNumber() - diferencia;
                int columnaError=e.getColumnNumber();
                
                String motivo=e.getMessage();
                String error="Error en linea " +
                        lineaError + ", columna "+ columnaError + 
                        ": "+ motivo;
                txtInformes.setText(error);
                
                destacarError(lineaError, txtXML);
        }
    }
    private boolean esXMLBienFormado(){
        String xml=txtXML.getText();
        String dtd=txtResto.getText();
        try {
            /* Primero comprobamos si es XML bien formado*/
            ProcesadorXML.analizarXML(xml, false);
            return true;
        } catch (ParserConfigurationException ex) {
            this.txtInformes.setText("Error desconocido");
            this.txtInformes.append(ex.toString());
        } catch (SAXException ex) {
            this.procesarExcepcionParseadoSAX(ex, DESTACAR_LINEA_ACTUAL);
        } catch (IOException ex) {
            this.txtInformes.setText("Error desconocido");
            this.txtInformes.append(ex.toString());
        }
        return false;
    }
    private void validarConDTD(){
        
        if (!esXMLBienFormado()){
            return ;
        } else {
            try {
                
                Document doc=ProcesadorXML.analizarXML(this.txtXML.getText(), false);
                System.out.println("Obtenido el doc");
                ProcesadorXML.DTDValidaXML(this.txtResto.getText(), doc);
                this.txtInformes.setText("La DTD valida correctamente el fichero");
            } catch (ParserConfigurationException ex) {
                this.txtInformes.setText("Error desconocido");
                this.txtInformes.append(ex.toString());
            } catch (SAXException ex) {
                System.out.println("Excepcion!");
                this.procesarExcepcionParseadoSAX(ex, DESTACAR_LINEA_ANTERIOR);
            } catch (IOException ex) {
                this.txtInformes.setText("Error desconocido");
                this.txtInformes.append(ex.toString());
            } catch (TransformerException ex) {
                this.txtInformes.setText("Ha habido un fallo al intentar inyectar la DTD");
            } 
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.txtInformes.setText("");
        if (e.getActionCommand()==Interfaz.VALIDAR_DTD){
            this.validarConDTD();
        }
        if (e.getActionCommand()==Interfaz.VALIDAR_ESQUEMA){
            System.out.println("Esquema");
        }
        if (e.getActionCommand()==Interfaz.EVALUAR_XPATH){
            System.out.println("XPath");
        }
        if (e.getActionCommand()==Interfaz.EJECUTAR_XQUERY){
            System.out.println("XQuery");
        }
        if (e.getActionCommand()==Interfaz.TRANSFORMAR_XSLT){
            System.out.println("XSLT");
        }
        
        
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interfaz i=new Interfaz();
                i.createAndShowGUI();
                i.txtXML.setText(ProcesadorXML.getXMLejemploDTD());
                i.txtResto.setText(ProcesadorXML.getDTDejemplo());
            }
        });
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

}
