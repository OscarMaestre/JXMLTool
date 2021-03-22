package io.github.oscarmaestre.jxmltool.testspropios;

import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

public class ProbadorFicherosConXMLSchemaEntregado {
    public static String getXMLSchemaEntregado(){
        final String xmlSchemaEntregado="<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
        "    <xs:element name=\"listaarticulos\" type=\"tipoListado\"/>\n" +
        "    <xs:complexType name=\"tipoListado\">\n" +
        "        <xs:sequence>\n" +
        "            <xs:element name=\"articulo\" type=\"tipoArticulo\" maxOccurs=\"unbounded\"/>\n" +
        "        </xs:sequence>\n" +
        "    </xs:complexType>\n" +
        "\n" +
        "\n" +
        "    <xs:complexType name=\"tipoArticulo\">\n" +
        "        <xs:sequence>\n" +
        "            <xs:element name=\"peso\" type=\"tipoPeso\" minOccurs=\"0\"/>\n" +
        "            <xs:element name=\"descripcion\" type=\"xs:string\"/>\n" +
        "            <xs:element name=\"fechaentrega\" type=\"tipoFecha\"/>\n" +
        "            <xs:choice>\n" +
        "                <xs:element name=\"pagado\" type=\"tipoVacio\"/>\n" +
        "                <xs:element name=\"nopagado\" type=\"tipoVacio\"/>\n" +
        "            </xs:choice>\n" +
        "        </xs:sequence>\n" +
        "         <xs:attribute name=\"codigo\" type=\"tipoCodigo\" use=\"required\"/>\n" +
        "    </xs:complexType>\n" +
        "\n" +
        "\n" +
        "    <xs:simpleType name=\"tipoCodigo\">\n" +
        "        <xs:restriction base=\"xs:string\">\n" +
        "            <xs:pattern value=\"[0-9]{3,4}[A-z]{4}\"/>\n" +
        "        </xs:restriction>\n" +
        "    </xs:simpleType>\n" +
        "    \n" +
        "    <xs:simpleType name=\"tipoPesoAuxiliar\">\n" +
        "        <xs:restriction base=\"xs:float\">\n" +
        "            <xs:minInclusive value=\"0.00001\"/>\n" +
        "        </xs:restriction>\n" +
        "    </xs:simpleType>\n" +
        "\n" +
        "    <xs:complexType name=\"tipoPeso\">\n" +
        "        <xs:simpleContent>\n" +
        "            <xs:extension base=\"tipoPesoAuxiliar\">\n" +
        "                <xs:attribute name=\"unidad\" type=\"tipoUnidad\"/>\n" +
        "            </xs:extension>\n" +
        "        </xs:simpleContent>\n" +
        "    </xs:complexType>\n" +
        "\n" +
        "    <xs:simpleType name=\"tipoUnidad\">\n" +
        "        <xs:restriction base=\"xs:string\">\n" +
        "            <xs:enumeration value=\"g\"/>\n" +
        "            <xs:enumeration value=\"kg\"/>\n" +
        "            <xs:enumeration value=\"ton\"/>\n" +
        "        </xs:restriction>\n" +
        "    </xs:simpleType>\n" +
        "\n" +
        "    <xs:simpleType name=\"tipoFecha\">\n" +
        "        <xs:restriction base=\"xs:string\">\n" +
        "            <xs:pattern value=\"[0-9]{4}-[0-9]{2}-[0-9]{2}\"/>\n" +
        "        </xs:restriction>\n" +
        "    </xs:simpleType>\n" +
        "\n" +
        "    <xs:complexType name=\"tipoVacio\">\n" +
        "        <xs:sequence></xs:sequence>\n" +
        "    </xs:complexType>\n" +
        "</xs:schema>";
        return xmlSchemaEntregado;
    }
    
    public static void probar(String rutaDirectorio){
        String xmlSchemaEntregado=getXMLSchemaEntregado();
        File directorio=new File(rutaDirectorio);
        String[] rutas = directorio.list();
        
        for (String ruta:rutas){
            String rutaCompleta=rutaDirectorio+File.separator+ruta;
            System.out.println("Ruta:"+rutaCompleta);
            String xmlEnFichero=ProcesadorXML.leerFichero(rutaCompleta);
            //System.out.println(xmlEnFichero);
            try {
                ProcesadorXML.XMLSchemaValidaXML(xmlSchemaEntregado, xmlEnFichero);
                
            } catch (SAXException ex) {
                System.out.println(ex.getLocalizedMessage());
            } catch (IOException ex) {
                System.out.println("Error de E/S");
            }
        }
    }
    public static void main(String[] args) {
        probar("/home/usuario/repos/deckard/hoy/dam1/t0401-dtds/casosprueba/");
    }
    
}
