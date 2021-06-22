package io.github.oscarmaestre.jxmltool.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Tester {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket();
        InetSocketAddress dir=new InetSocketAddress("127.0.0.1", 9876);
        s.connect(dir);
        PrintWriter flujoEscritura = Utilidades.getFlujoEscritura(s);
        BufferedReader flujoLectura = Utilidades.getFlujoLectura(s);
        flujoEscritura.println("XQUERY");
        flujoEscritura.println("doc(\"datos.xml\")//proveedor/nombreprov");
        flujoEscritura.println("PROVEEDORES");
        flujoEscritura.println("1");
        flujoEscritura.flush();
        String l1 = flujoLectura.readLine();
        while (!l1.equals("##FIN##")){
            System.out.println("Linea 1:"+l1);
            l1 = flujoLectura.readLine();
        }
        
    }
}
