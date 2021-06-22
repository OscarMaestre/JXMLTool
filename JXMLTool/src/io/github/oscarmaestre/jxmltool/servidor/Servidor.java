package io.github.oscarmaestre.jxmltool.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public void servir() throws IOException{
        ServerSocket socketServidor;
        socketServidor=new ServerSocket(9876);
        while (true){
            Socket entrante = socketServidor.accept();
            Peticion p=new Peticion(entrante);
            Thread h  =new Thread(p);
            h.start();
        }
    }
    public static void main(String[] args) throws IOException {
        Servidor s=new Servidor();
        s.servir();
    }
}
