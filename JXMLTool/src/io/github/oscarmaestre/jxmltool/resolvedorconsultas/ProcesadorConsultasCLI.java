package io.github.oscarmaestre.jxmltool.resolvedorconsultas;


import io.github.oscarmaestre.jxmltool.Constantes;
import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xquery.XQException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcesadorConsultasCLI {
    static final int TOTAL_PREGUNTAS_XQUERY=8;
    static String xmlBaseDedatos=ProcesadorXML.getProveedoresPartes();
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
    
    private static String getConsultaAlumno(NodeList nodos){
        if (nodos.item(0)==null){
            return "No hay nada escrito";
        }
        String text=nodos.item(0).getTextContent();
        System.out.println("El alumno ha escrito:"+text);
        return text;
    }
    private static RespuestaXQuery[] getRespuestasOficiales(){
        RespuestaXQuery[] respuestas=new RespuestaXQuery[Constantes.consultasXQuery.length];
        for (int i = 0; i < Constantes.consultasXQuery.length; i++) {
            RespuestaXQuery respuesta = Constantes.consultasXQuery[i];
            respuestas[i]=respuesta;
            try {
                System.out.println("Ejecutando:<"+respuesta.getTextoXQuery()+">" + " consulta "+i);
                String ejecutarXQuery = ProcesadorXML.ejecutarXQuery(respuesta.getTextoXQuery(), xmlBaseDedatos);
                respuesta.setResultadoConsulta(ejecutarXQuery);
            } catch (XQException ex) {
                System.out.println("Error con:"+respuesta.getTextoXQuery());
            } catch (IOException ex) {
                System.out.println("Error de archivo");
            }
        }
        return respuestas;
    }
    
    
    private static RespuestaXQueryAlumno[] getRespuestasAlumno(String rutaFicheroRespuestas){
        String xmlRespuestasAlumno;
        xmlRespuestasAlumno = ProcesadorXML.leerFichero(rutaFicheroRespuestas);
        
        RespuestaXQuery[] respuestasOficiales;
        respuestasOficiales = ProcesadorConsultasCLI.getRespuestasOficiales();
        
        RespuestaXQueryAlumno[] respuestas;
        respuestas = new RespuestaXQueryAlumno[respuestasOficiales.length];
        
        int totalConsultas=Constantes.consultasXQuery.length;
        /*Recuperamos el XQuery que ha escrito en el XML de respuestas*/
        for (int i=0; i<totalConsultas;i++){
            int numConsultaEnExamen=i+1;
            String consultaXPath="//pregunta[@num='"+numConsultaEnExamen+"']/xquery";
            NodeList nodos;
            try {
                nodos = ProcesadorXML.evaluarXPath(
                        consultaXPath, xmlRespuestasAlumno);
                String consultaAlumno = ProcesadorConsultasCLI.getConsultaAlumno(nodos);
                RespuestaXQueryAlumno r;
                r=new RespuestaXQueryAlumno(
                        respuestasOficiales[i], 
                        ProcesadorConsultasCLI.xmlBaseDedatos, 
                        consultaAlumno);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } /*Fin del for*/
        return respuestas;
    }
    public static void procesarFicheroRespuestas (String ruta) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        
        RespuestaXQueryAlumno[] respuestasAlumno;
        respuestasAlumno = ProcesadorConsultasCLI.getRespuestasAlumno(ruta);
        for (int i = 0; i < respuestasAlumno.length; i++) {
            RespuestaXQueryAlumno respuestaXQueryAlumno = respuestasAlumno[i];
            
            String comparacion = respuestaXQueryAlumno.comparar();
            System.out.println("Comparacion:"+comparacion);
            
        }
        
        
    } /*Fin del método*/
    
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
