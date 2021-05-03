package io.github.oscarmaestre.jxmltool;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcesadorConsultasCLI {
    static final int TOTAL_PREGUNTAS_XQUERY=8;
    public static void main(String[] args) throws FileNotFoundException {
        File directorioPasado=new File(args[0]);
        try {
            recorrerFicheros(directorioPasado.listFiles());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void procesarFicheroRespuestas (String ruta) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        String xmlProveedoresPartes=ProcesadorXML.leerFichero(ruta);
        int totalConsultas=Constantes.consultasXQuery.length;
        for (int i=0; i<totalConsultas;i++){
            int numConsultaEnExamen=i+1;
            String consultaXPath="//pregunta[@num='"+numConsultaEnExamen+"']/xquery";
            NodeList nodos = ProcesadorXML.evaluarXPath(
                    consultaXPath, xmlProveedoresPartes);
            for (int j = 0; j < nodos.getLength(); j++) {
                System.out.println(nodos.item(j));
                
            }
            System.out.println(nodos.toString());
            if (nodos.item(0)==null){
                System.out.println("Fallo:"+consultaXPath +" en archivo:"+ruta);
                continue;
            }
            String textoRespuestaAlumno = nodos.item(0).getTextContent();
            System.out.println(textoRespuestaAlumno);
        }
        
    }
    
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static void recorrerFicheros(File[] files) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, IOException, XPathExpressionException, XPathExpressionException {
        for (File file : files) {
            if (file.isDirectory()) {
                recorrerFicheros(file.listFiles()); 
            } else {
                //System.out.println("File: " + file.getAbsolutePath());
                String absolutePath = file.getAbsolutePath();
                try  {
                    Optional<String> extension = getExtensionByStringHandling(absolutePath);
                    if (extension.get().equals("xml")){
                        procesarFicheroRespuestas(absolutePath);
                    } else {
                        //System.out.println("Extension:"+extension.get());
                    }
                }
                catch (NoSuchElementException e){
                    continue;
                }
            }
        } //Fin del for
    } //Fin del método
}
