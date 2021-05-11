package io.github.oscarmaestre.jxmltool.resolvedorconsultas;


import io.github.oscarmaestre.jxmltool.Constantes;
import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
            ArrayList<Informe> informes = recorrerFicheros(directorioPasado.listFiles());
            generarInforme(informes);
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
    private static String getResultadoXPath(String xpath, String xml){
        NodeList nodos;
        String consultaAlumno = "";
        try {
            nodos = ProcesadorXML.evaluarXPath(
                    xpath, xml);
             consultaAlumno = ProcesadorConsultasCLI.getConsultaAlumno(nodos);
             return consultaAlumno.trim();
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "La consulta dio un error:"+xpath;
        
    }
    private static String getConsultaAlumno(NodeList nodos){
        if (nodos.item(0)==null){
            return "No hay nada escrito";
        }
        String text=nodos.item(0).getTextContent();
        //System.out.println("El alumno ha escrito:"+text);
        return text;
    }
    private static RespuestaXQuery[] getRespuestasOficiales(){
        RespuestaXQuery[] respuestas=new RespuestaXQuery[Constantes.consultasXQuery.length];
        for (int i = 0; i < Constantes.consultasXQuery.length; i++) {
            RespuestaXQuery respuesta = Constantes.consultasXQuery[i];
            respuestas[i]=respuesta;
            try {
                
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
    
    
    private static Informe getRespuestasAlumno(String rutaFicheroRespuestas){        
        String xmlRespuestasAlumno;
        xmlRespuestasAlumno = ProcesadorXML.leerFichero(rutaFicheroRespuestas);
        
        
        String consultaApellidos="//apellidos";
        String consultaNombre   ="//nombre";
        String nombreAlumno =ProcesadorConsultasCLI.getResultadoXPath(
                consultaNombre, xmlRespuestasAlumno);
        String apAlumno     =ProcesadorConsultasCLI.getResultadoXPath(
                consultaApellidos, xmlRespuestasAlumno);
        nombreAlumno=nombreAlumno.trim();
        apAlumno=apAlumno.trim();
        System.out.println("Nombre:"+nombreAlumno+" "+apAlumno);
        RespuestaXQuery[] respuestasOficiales;
        respuestasOficiales = ProcesadorConsultasCLI.getRespuestasOficiales();
        
        RespuestaXQueryAlumno[] respuestas;
        respuestas = new RespuestaXQueryAlumno[respuestasOficiales.length];
        
        int totalConsultas=Constantes.consultasXQuery.length;
        /*Recuperamos el XQuery que ha escrito en el XML de respuestas*/
        for (int i=0; i<totalConsultas;i++){
            int numConsultaEnExamen=i+1;
            String consultaXPath="//pregunta[@num='"+numConsultaEnExamen+"']/xquery";
            

            String consultaAlumno=ProcesadorConsultasCLI.getResultadoXPath(consultaXPath, xmlRespuestasAlumno);
            System.out.println("El alumno ha escrito:"+consultaAlumno);
            RespuestaXQueryAlumno r;
            r=new RespuestaXQueryAlumno(
                    respuestasOficiales[i], 
                    ProcesadorConsultasCLI.xmlBaseDedatos, 
                    consultaAlumno);
            respuestas[i]=r;

            
        } /*Fin del for*/
        Informe informe=new Informe(respuestas, nombreAlumno, apAlumno);
        return informe;
    }
    public static Informe procesarFicheroRespuestas (String ruta) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        Informe informe  = ProcesadorConsultasCLI.getRespuestasAlumno(ruta);
        return informe;                
        
    } /*Fin del método*/
    
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static ArrayList<Informe> recorrerFicheros(File[] files) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, IOException, XPathExpressionException, XPathExpressionException {
        ArrayList<Informe> informes=new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                recorrerFicheros(file.listFiles()); 
            } else {
                //System.out.println("File: " + file.getAbsolutePath());
                String absolutePath = file.getAbsolutePath();
                try  {
                    Optional<String> extension = getExtensionByStringHandling(absolutePath);
                    if (extension.get().equals("xml")){
                        Informe i=procesarFicheroRespuestas(absolutePath);
                        informes.add(i);
                    } else {
                        //System.out.println("Extension:"+extension.get());
                    }
                }
                catch (NoSuchElementException e){
                    continue;
                }
            }
        } //Fin del for
        return informes;
    } //Fin del método

    private static void generarInforme(ArrayList<Informe> recorrerFicheros) {
        FileWriter fw=null;
        try {
            fw = new FileWriter("informe.html");
            PrintWriter pw=new PrintWriter(fw);
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < recorrerFicheros.size(); i++) {
                Informe get = recorrerFicheros.get(i);
                sb.append(get.generarInforme());
                
            }
            pw.print(sb.toString());
            pw.flush();
            pw.close();
            System.out.println("Informe generado");
        } catch (IOException ex) {
            Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcesadorConsultasCLI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
