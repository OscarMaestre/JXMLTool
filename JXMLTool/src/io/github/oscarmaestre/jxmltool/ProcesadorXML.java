package io.github.oscarmaestre.jxmltool;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ProcesadorXML {
    private static final String NOMBRE_ARCHIVO_DTD="dtd";
    private static final String SUFIJO_ARCHIVO_DTD="dtd";
    private static final String dtd=
                    NOMBRE_ARCHIVO_DTD+"."+SUFIJO_ARCHIVO_DTD;
    private static final String FICHERO_XML_CON_DTD=
            "fichero_resultado_con.dtd.xml";
    /**
     * Analiza una cadena XML para ver si es un documento
     * XML válido
     * @param xml
     * @return Devuelve un objeto Document que representa
     * el XML pasado.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public static Document analizarXML(String xml,
            boolean validar) throws ParserConfigurationException, SAXException, IOException{
        //El metodo parse necesita que la cadena
        //vaya en forma de InputSource
        InputSource inputSource;
        inputSource= new InputSource( new StringReader( xml ) );
        DocumentBuilderFactory fabrica;
        DocumentBuilder parser;
        fabrica=DocumentBuilderFactory.newInstance();
        fabrica.setValidating(validar);
        
        parser=fabrica.newDocumentBuilder();
        if (validar){
            /*Java obliga a que cuando se usa un parser con
            validacion activemos algun errorHandler. Ponemos uno vacio
            que lo unico que hace es propagar excepciones. Ver el codigo
            de ProcesadorErrores
            */
            ProcesadorErrores procesadorErrores;
            procesadorErrores=new ProcesadorErrores();
            parser.setErrorHandler(procesadorErrores);
        }
        Document documento;

        documento=parser.parse(inputSource);
        return documento;
        
    }
    
    private static String leerFichero(String ruta) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(ruta));
            String contenidoFichero=new String(encoded, "UTF-8");
            return contenidoFichero;
        } catch (UnsupportedEncodingException ex) {
            return ("Error de codificación en el fichero "+ruta);
        } catch (IOException ex) {
            return ("Error: No se pudo leer el fichero "+ruta);
        }
    }
    public static Document anadirDTD(Document doc,String rutaDTD) throws TransformerException, ParserConfigurationException, SAXException, IOException{
        DOMSource source = new DOMSource(doc);
        FileOutputStream fos=new FileOutputStream(ProcesadorXML.FICHERO_XML_CON_DTD);
        StreamResult result=new StreamResult(fos);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, 
                rutaDTD);
        transformer.transform(source, result);
        fos.close();
        String xmlConDTD=leerFichero(ProcesadorXML.FICHERO_XML_CON_DTD);
        
        return analizarXML(xmlConDTD, false);
    }
    public static boolean DTDValidaXML(String dtd, Document doc) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        boolean esValido=false;
        FileWriter ficheroTemporal=new FileWriter(ProcesadorXML.dtd);
        ficheroTemporal.write(dtd);
        ficheroTemporal.flush();
        ficheroTemporal.close();
        Document documentoConDTD=anadirDTD(doc, ProcesadorXML.dtd);
        String contenidoFichero=leerFichero(ProcesadorXML.FICHERO_XML_CON_DTD);
        Document docValido=analizarXML(contenidoFichero, true);
        return esValido;
    }
    
    public static boolean XMLSchemaValidaXML(String schema, String xml) throws SAXException, IOException {
        //Se asume que todo va bien, si hay un error de validación
        //ya saltará una excepcion
        boolean esValido=true;
        SchemaFactory fabrica   =       SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source esquemaSource    = new   StreamSource(new StringReader(schema));
        Source xmlSource        = new   StreamSource(new StringReader(xml));
        Schema objetoEsquema    =       fabrica.newSchema(esquemaSource);
        Validator validador     =       objetoEsquema.newValidator();
        
        validador.validate(xmlSource);
        return esValido;
    }
    
    public static String transformarConXSLT(String xslt, String xml){
        String resultado="";
        return resultado;
    }
    
    
    public static String tabularXML(String xmlOriginal) throws TransformerException{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        Source source = new StreamSource(new StringReader(xmlOriginal));
        transformer.transform(source, result);
        String xmlString = result.getWriter().toString();
        return xmlString;
    }
    public static String nodeListToString(NodeList nodos){
        String resultado="";
        return resultado;
    }
    public static NodeList evaluarXPath(String xpath, String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        NodeList resultado=null;
        Document doc=ProcesadorXML.analizarXML(xml, false);
        XPath objetoXPath = XPathFactory.newInstance().newXPath();
        resultado = (NodeList) objetoXPath.evaluate(xpath, doc.getDocumentElement(), XPathConstants.NODESET);
        return resultado;
    }
    
    public static String ejecutarXQuery(String xquery, String xml){
        String resultado="";
        return resultado;
    }
    
    public static String getXMLejemploDTD(){
        String ejemplo="<pedido>\n" +
        "    <tractor>\n" +
        "        <componente nombrefabricante=\"Ebro\">\n" +
        "            <fechaentrega>\n" +
        "                <mes>2018</mes> <anio>2018</anio>\n" +
        "            </fechaentrega>\n" +
        "            <fragil/>\n" +
        "            <peso unidad=\"kg\">12</peso>\n" +
        "            <numserie>00A</numserie>\n" +
        "        </componente>\n" +
        "        <componente nombrefabricante=\"Avia\">\n" +
        "            <fechaentrega>\n" +
        "                <dia>12</dia><mes>1</mes><anio>2019</anio>\n" +
        "            </fechaentrega>\n" +
        "            <nofragil/>\n" +
        "            <peso unidad=\"g\">1450</peso>\n" +
        "            <numserie>00D</numserie>\n" +
        "            <kmmaximos>25000</kmmaximos>\n" +
        "        </componente>\n" +
        "    </tractor>\n" +
        "    <tractor>\n" +
        "        <componente nombrefabricante=\"John Deere\">\n" +
        "            <fragil/>\n" +
        "            <peso unidad=\"g\">770</peso>\n" +
        "            <numserie>43Z</numserie>\n" +
        "        </componente>\n" +
        "    </tractor>\n" +
        "</pedido>";
        return ejemplo;
    }
    public static String getDTDejemplo(){
        String ejemplo="<!ELEMENT pedido     (tractor)+>\n" +
        "<!ELEMENT tractor    (componente)+>\n" +
        "<!ELEMENT componente (fechaentrega?, (fragil|nofragil),\n" +
        "                      peso, numserie, kmmaximos?)>\n" +
        "\n" +
        "<!ELEMENT fechaentrega (dia?, mes, anio)>\n" +
        "<!ELEMENT dia      (#PCDATA)>\n" +
        "<!ELEMENT mes      (#PCDATA)>\n" +
        "<!ELEMENT anio     (#PCDATA)>\n" +
        "<!ELEMENT fragil   EMPTY>\n" +
        "<!ELEMENT nofragil EMPTY >\n" +
        "<!ELEMENT peso     (#PCDATA)>\n" +
        "<!ATTLIST peso unidad CDATA #REQUIRED>\n" +
        "<!ELEMENT numserie  (#PCDATA)>\n" +
        "<!ELEMENT kmmaximos (#PCDATA)>\n" +
        "<!ATTLIST componente nombrefabricante CDATA #REQUIRED>";
        return ejemplo;
    }
    public static String getXMLEjemploSchema(){
        String ejemplo="<listaproductos>\n" +
            "    <articulo>\n" +
            "        <!--Estructura 2 letras,2 cifras-->\n" +
            "        <codigo>CD12</codigo>\n" +
            "        <!--Descripcion es optativo y su atributo autor tb-->\n" +
            "        <descripcion autor=\"Pepe\">Monitor</descripcion>\n" +
            "    </articulo>\n" +
            "    <articulo>\n" +
            "        <codigo>CA12</codigo>\n" +
            "    </articulo>\n" +
            "    <articulo>\n" +
            "        <codigo>AA99</codigo>\n" +
            "        <descripcion>Teclado</descripcion>\n" +
            "    </articulo>\n" +
            "</listaproductos>";
        return ejemplo;
    }
    public static String getSchemaEjemplo(){
        String ejemplo="<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "    <xsd:element name=\"listaproductos\" type=\"tipoListaProductos\"/>\n" +
            "    <xsd:complexType name=\"tipoListaProductos\">\n" +
            "        <xsd:complexContent>\n" +
            "            <xsd:restriction base=\"xsd:anyType\">\n" +
            "                <xsd:sequence>\n" +
            "                    <xsd:element name=\"articulo\"\n" +
            "                                 type=\"tipoArticulo\"\n" +
            "                                 maxOccurs=\"unbounded\"/>\n" +
            "                </xsd:sequence>\n" +
            "            </xsd:restriction>\n" +
            "        </xsd:complexContent>\n" +
            "    </xsd:complexType> <!--Fin de listaarticulos-->\n" +
            "    <xsd:complexType name=\"tipoArticulo\">\n" +
            "        <xsd:complexContent>\n" +
            "            <xsd:restriction base=\"xsd:anyType\">\n" +
            "                <xsd:sequence>\n" +
            "                    <xsd:element name=\"codigo\" type=\"tipoCodigo\"/>\n" +
            "                    <xsd:element name=\"descripcion\"\n" +
            "                                 type=\"tipoDescripcion\"\n" +
            "                                 minOccurs=\"0\" maxOccurs=\"1\"/>\n" +
            "                </xsd:sequence>\n" +
            "            </xsd:restriction>\n" +
            "        </xsd:complexContent>\n" +
            "    </xsd:complexType> <!--Fin de  articulo-->\n" +
            "\n" +
            "    <xsd:simpleType name=\"tipoCodigo\">\n" +
            "        <xsd:restriction base=\"xsd:string\">\n" +
            "            <xsd:pattern value=\"[A-Z]{2}[0-9]{2}\"/>\n" +
            "        </xsd:restriction>\n" +
            "    </xsd:simpleType> <!--Fin de codigo-->\n" +
            "\n" +
            "    <xsd:complexType name=\"tipoDescripcion\">\n" +
            "        <xsd:simpleContent>\n" +
            "            <xsd:extension base=\"xsd:string\">\n" +
            "                <xsd:attribute name=\"autor\" type=\"xsd:string\"/>\n" +
            "            </xsd:extension>\n" +
            "        </xsd:simpleContent>\n" +
            "    </xsd:complexType>\n" +
            "</xsd:schema>";
        return ejemplo;
    }
}
