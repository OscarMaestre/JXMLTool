package io.github.oscarmaestre.jxmltool;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ProcesadorXMLCLI {
    
    public static void despacharArgumentos(String[] args){
        if (args[0].equals("dtd")){
            ProcesadorXMLCLI.validarConDtd(args);
        }
        
        if (args[0].equals("xml")){
            ProcesadorXMLCLI.validarConXMLSchema(args);
        }
        for (String s:args){
            System.out.println("Argumento: "+s);
        }
    }
    private static void validarConDtd(String[] args) {
        try {
            System.out.println("Validando DTD");
            String nombreFicheroXML=args[1];
            String xmlEnFichero=ProcesadorXML.leerFichero(nombreFicheroXML);
            
            String nombreFicheroDTD=args[2];
            String dtdEnFichero=ProcesadorXML.leerFichero(nombreFicheroDTD);
            
            String resultadoEsperado=args[3];
            
            float notaAsignada=Float.parseFloat(args[4]);
            
            Document doc=ProcesadorXML.analizarXML(xmlEnFichero, false);
            boolean esValido = ProcesadorXML.DTDValidaXML(dtdEnFichero, doc);
            if (esValido && resultadoEsperado.equals("OK")){
                System.out.println("El fichero DTD "+nombreFicheroDTD+
                        " valida correctamente el fichero "+nombreFicheroXML+
                        " tal y como se esperaba. Nota en este ejercicio:"+notaAsignada);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProcesadorXMLCLI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesadorXMLCLI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ProcesadorXMLCLI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ProcesadorXMLCLI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void validarConXMLSchema(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
