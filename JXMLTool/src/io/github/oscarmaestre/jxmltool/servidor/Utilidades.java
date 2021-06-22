package io.github.oscarmaestre.jxmltool.servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades{
    
    public static BufferedReader getFlujoLectura(Socket s) throws IOException{
        InputStream flujoSimple = s.getInputStream();
        InputStreamReader flujoMedio = new InputStreamReader(flujoSimple);
        BufferedReader flujoAvanzado = new BufferedReader(flujoMedio);
        
        return flujoAvanzado;
    }
    
    public static PrintWriter getFlujoEscritura (Socket s) throws IOException {
        // Flujo simple que maneja bytes
        OutputStream flujoSimple = s.getOutputStream();
        
        // Flujo sofisticado que maneja búfferes
        PrintWriter flujoSofisticado = new PrintWriter(flujoSimple);
        
        return flujoSofisticado;
    }
    
    public static void enviarMensaje(PrintWriter pw, String mensaje) {
        pw.println(mensaje);
        pw.flush();
    }
    
    public static void enviarMensaje(PrintWriter pw, int accion,
            String nombreFichero, String numCuenta){
        pw.println(accion);
        pw.println(nombreFichero);
        
        if (numCuenta != null)
            pw.println(numCuenta);
        
        pw.flush();
    }
    
    public static void enviarMensaje(PrintWriter pw, String[] cuentas){
        pw.println(cuentas.length);
        
        for (String recorre : cuentas)
            pw.println(recorre);
        
        pw.flush();
    }
}