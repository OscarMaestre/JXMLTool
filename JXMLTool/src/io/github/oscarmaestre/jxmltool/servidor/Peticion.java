package io.github.oscarmaestre.jxmltool.servidor;

import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQException;

public class Peticion implements Runnable{
    protected Socket            conexionEntrante;
    protected PrintWriter       salida;
    protected BufferedReader    entrada;
    protected Examen            examen;
    public Peticion(Socket conexionEntrante) throws IOException {
        this.conexionEntrante   = conexionEntrante;
        salida                  = Utilidades.getFlujoEscritura(conexionEntrante);
        entrada                 = Utilidades.getFlujoLectura(conexionEntrante);
        
        /** Cuidado, cambiar por el examen correspondiente*/
        
        examen                  = Examen.getExamen2();
        
    }

    @Override
    public void run() {
        this.procesarOperacion();
    }
    
    private String getLinea(String error){
        try {
            String operacion=entrada.readLine();
            System.out.println("Recibida linea:"+operacion);
            return operacion;
        } catch (IOException ex) {
            System.out.println(error);
        }
        return null;
    }
    private void procesarOperacion() {
        String comando = this.getLinea("No se pudo leer la operacion");
        if (comando==null){
            return ;
        }
        switch (comando){
            case "XQUERY":{
                this.procesarXQuery();
            }
        }
        this.salida.flush();
        this.salida.close();
    }

    private String getNumPregunta(){
        String numPregunta=this.getLinea("No se pudo leer el numero de pregunta XQuery");
        return numPregunta;
    }
    
    private String codificarSalida(String salida){
        return salida.replace("\n", "#_-_#");
    }
    private void procesarXQuery() {
        String xqueryEntrada=this.getLinea("No se pudo leer el XQuery");
        if (xqueryEntrada==null){
            return ;
        }
        String textoEjemplo=this.getEjemplo();
        if (textoEjemplo==null){
            return ;
        }
        
        String numPregunta=this.getNumPregunta();
        if (numPregunta==null){
            return ;
        }
        
        try {
            String resultadoXQuery;
            resultadoXQuery = ProcesadorXML.ejecutarXQuery(xqueryEntrada, textoEjemplo);
            if (resultadoXQuery==null){
                resultadoXQuery="El XQuery dio un error:";
            }
            
            System.out.println("XQuery ejecutado");
            String respuestaOficial=this.examen.getRespuesta(numPregunta);
            String resultadoXQueryOficial;
            resultadoXQueryOficial = ProcesadorXML.ejecutarXQuery(respuestaOficial, textoEjemplo);
            if (resultadoXQueryOficial==null){
                System.out.println("El XQuery oficial dio null");
                resultadoXQueryOficial=null;
            }
            System.out.println("Todo terminado");
            System.out.println("Resultado:"+resultadoXQuery);
            System.out.println("Resp oficial:"+respuestaOficial);
            System.out.println("Result oficial:"+resultadoXQueryOficial);
            salida.println(this.codificarSalida(resultadoXQuery));
            salida.println(this.codificarSalida(respuestaOficial));
            salida.println(this.codificarSalida(resultadoXQueryOficial));
            salida.println("##FIN##");
            salida.flush();
            
        } catch (XQException ex) {
            
            System.out.println("Hubo algun error XQuery");
            String error="Error XQuery\n"+ex.getLocalizedMessage();
            Utilidades.enviarMensaje(salida, error);
            Utilidades.enviarMensaje(salida, error);
            Utilidades.enviarMensaje(salida, error);
        } catch (IOException ex) {
            System.out.println("Hubo algun error de entrada salida con el XQuery");
            String error="Error IO\n"+ex.getLocalizedMessage();
            Utilidades.enviarMensaje(salida, error);
            Utilidades.enviarMensaje(salida, error);
            Utilidades.enviarMensaje(salida, error);
        }
        return ;
    }

    private String getEjemplo() {
        String nombreEjemplo=this.getLinea("No se pudo leer el nombre del ejemplo");
        if (nombreEjemplo==null){
            return null;
        }
        switch (nombreEjemplo){
            case "PROVEEDORES":{
                return ProcesadorXML.getProveedoresPartes();
            }
        }
        /* Si se llega aquí no se ha dado un ejemplo válido*/
        return null;
    }

}
