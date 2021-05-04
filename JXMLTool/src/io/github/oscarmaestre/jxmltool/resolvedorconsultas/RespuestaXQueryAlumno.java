package io.github.oscarmaestre.jxmltool.resolvedorconsultas;

import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.IOException;
import javax.xml.xquery.XQException;

public class RespuestaXQueryAlumno {
    RespuestaXQuery respuestaAsociada;
    String xmlBD;
    String resultadoDeSuConsulta="Error";
    boolean daError=false;
    String textoXQuery;
    public RespuestaXQueryAlumno(RespuestaXQuery respuestaAsociada, String xmlBD, String textoXQuery) {
        this.respuestaAsociada = respuestaAsociada;
        this.xmlBD = xmlBD;
        this.textoXQuery=textoXQuery;
        
        this.ejecutar();
    }
    private void ejecutar(){
        try {
            this.resultadoDeSuConsulta= ProcesadorXML.ejecutarXQuery(this.textoXQuery, xmlBD);
        } catch (IOException ex) {
            daError=true;
        } catch (XQException ex) {
            daError=true;
        }
    }
    
    public String comparar(){
        StringBuilder sb=new StringBuilder();
        sb.append("El resultado oficial es:\n"+this.respuestaAsociada.getResultadoConsulta());
        sb.append("\n");
        sb.append("Su resultado es:\n"+this.resultadoDeSuConsulta);
        return sb.toString();
    }
    
    
    
    
}
