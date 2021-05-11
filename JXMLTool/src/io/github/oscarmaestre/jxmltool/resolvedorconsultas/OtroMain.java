package io.github.oscarmaestre.jxmltool.resolvedorconsultas;

import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQException;

public class OtroMain {
    public static void main(String[] args) {
        //String xquery="<raiz>{for $p in doc(\"datos.xml\")//proveedor return $p}</raiz>";
        String xquery="for $p in doc(\"datos.xml\")//proveedor return $p";
        try {
            String ejecutarXQuery = ProcesadorXML.ejecutarXQuery(xquery, ProcesadorXML.getProveedoresPartes());
            System.out.println("Resultado:"+ejecutarXQuery);
        } catch (XQException ex) {
            Logger.getLogger(OtroMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OtroMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
