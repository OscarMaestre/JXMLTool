package io.github.oscarmaestre.jxmltool;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
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
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;
import oracle.xml.xquery.OXQConnection;
import oracle.xml.xquery.OXQDataSource;
import oracle.xml.xquery.OXQView;
import org.w3c.dom.Attr;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
    private static final String FICHERO_DATOS_PARA_XQUERY="datos.xml";
    private static final String FICHERO_CONSULTA_XQUERY="consulta.xq";
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
    
    public static void volcarCadenaEnFichero(String rutaFichero, String cadena) throws IOException{
        FileWriter ficheroTemporal=new FileWriter(rutaFichero);
        ficheroTemporal.write(cadena);
        ficheroTemporal.flush();
        ficheroTemporal.close();
    }
    public static boolean DTDValidaXML(String dtd, Document doc) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        boolean esValido=false;
        
        volcarCadenaEnFichero(ProcesadorXML.dtd, dtd);
        
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
    
    public static String transformarConXSLT(String xslt, String xml) throws TransformerConfigurationException, TransformerException{
        TransformerFactory factory  =       TransformerFactory.newInstance();
        Source sourceXSLT           = new   StreamSource(new StringReader(xslt));
        Transformer transformer     =       factory.newTransformer(sourceXSLT);
        Source text                 = new   StreamSource(new StringReader(xml));
        StringWriter writerResultado= new   StringWriter();
        
        transformer.setOutputProperty("{http://xml.apache.org/xalan}omit-meta-tag", "yes"); 
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(text, new StreamResult(writerResultado));
        
        String resultado                   =       writerResultado.toString();
        
        return resultado;
    }
    
    
    public static String tabularXML(String xmlOriginal) throws TransformerException{
        xmlOriginal=xmlOriginal.replaceAll(">(\\s)+", ">");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        Source source = new StreamSource(new StringReader(xmlOriginal));
        transformer.transform(source, result);
        String xmlString = result.getWriter().toString();
        return xmlString;
    }
    public static String nodeListToString(NodeList nodos) throws TransformerException{
        String resultado="";
        for (int i=0; i<nodos.getLength(); i++){
            Node elem = nodos.item(i);//Your Node
            if (elem.getNodeType()==Node.ELEMENT_NODE){
                StringWriter buf = new StringWriter();
                Transformer xform = TransformerFactory.newInstance().newTransformer();
                xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                xform.setOutputProperty(OutputKeys.INDENT, "yes");
                xform.transform(new DOMSource(elem), new StreamResult(buf));
                resultado+=ProcesadorXML.tabularXML(buf.toString());
            }
            if (elem.getNodeType()==Node.TEXT_NODE){
                resultado+=elem.getTextContent();
            }
            if (elem.getNodeType()==Node.ATTRIBUTE_NODE){
                Attr atributo=(Attr) elem; 
                resultado+=atributo.getValue();
           }
            resultado+="\n";
        }
        return resultado;
    }
    public static NodeList evaluarXPath(String xpath, String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        NodeList resultado=null;
        Document doc=ProcesadorXML.analizarXML(xml, false);
        XPath objetoXPath = XPathFactory.newInstance().newXPath();
        resultado = (NodeList) objetoXPath.evaluate(xpath, doc.getDocumentElement(), XPathConstants.NODESET);
        return resultado;
    }
    
    public static String ejecutarXQuery(String xquery, String xml) throws XQException, IOException{
        String resultado="";
        
        volcarCadenaEnFichero(ProcesadorXML.FICHERO_DATOS_PARA_XQUERY, xml);
        volcarCadenaEnFichero(ProcesadorXML.FICHERO_CONSULTA_XQUERY, xquery);
        
        OXQDataSource ds = new OXQDataSource();
        XQConnection con = ds.getConnection();
        OXQConnection ocon = OXQView.getConnection(con);
        ocon.setEntityResolver(new ResolutorEntidades());
        
        File query = new File(ProcesadorXML.FICHERO_CONSULTA_XQUERY);
        
        XQStaticContext ctx = con.getStaticContext();
        ctx.setBaseURI(query.toURI().toString());
 
        FileInputStream queryInput = new FileInputStream(query);
        XQPreparedExpression expr = con.prepareExpression(queryInput, ctx);
        queryInput.close();
        XQSequence result = expr.executeQuery();
        
        
        resultado = result.getSequenceAsString(null);
        
        result.close();
        expr.close();
        con.close();
        
        
        
        return resultado;
    }
    
    public static String getProveedoresPartes(){
        String xml="<datos>\n" +
            "    <proveedores>\n" +
            "        <proveedor numprov=\"v1\">\n" +
            "            <nombreprov>Smith</nombreprov>\n" +
            "            <estado>20</estado>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v2\">\n" +
            "            <nombreprov>Jones</nombreprov>\n" +
            "            <estado>10</estado>\n" +
            "            <ciudad>Paris</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v3\">\n" +
            "            <nombreprov>Blake</nombreprov>\n" +
            "            <estado>30</estado>\n" +
            "            <ciudad>Paris</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v4\">\n" +
            "            <nombreprov>Clarke</nombreprov>\n" +
            "            <estado>20</estado>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v5\">\n" +
            "            <nombreprov>Adams</nombreprov>\n" +
            "            <estado>30</estado>\n" +
            "            <ciudad>Atenas</ciudad>\n" +
            "        </proveedor>       \n" +
            "    </proveedores>\n" +
            "    <partes>\n" +
            "        <parte numparte=\"p1\">\n" +
            "            <nombreparte>Tuerca</nombreparte>\n" +
            "            <color>Rojo</color>\n" +
            "            <peso>12</peso>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p2\">\n" +
            "            <nombreparte>Perno</nombreparte>\n" +
            "            <color>Verde</color>\n" +
            "            <peso>17</peso>\n" +
            "            <ciudad>Paris</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p3\">\n" +
            "            <nombreparte>Tornillo</nombreparte>\n" +
            "            <color>Azul</color>\n" +
            "            <peso>17</peso>\n" +
            "            <ciudad>Roma</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p4\">\n" +
            "            <nombreparte>Tornillo</nombreparte>\n" +
            "            <color>Rojo</color>\n" +
            "            <peso>14</peso>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p5\">\n" +
            "            <nombreparte>Leva</nombreparte>\n" +
            "            <color>Azul</color>\n" +
            "            <peso>12</peso>\n" +
            "            <ciudad>Paris</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p6\">\n" +
            "            <nombreparte>Engranaje</nombreparte>\n" +
            "            <color>Rojo</color>\n" +
            "            <peso>19</peso>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </parte>\n" +
            "    </partes>\n" +
            "    <proyectos>\n" +
            "        <proyecto numproyecto=\"y1\">\n" +
            "            <nombreproyecto>Clasificador</nombreproyecto>\n" +
            "            <ciudad>Paris</ciudad>\n" +
            "        </proyecto>\n" +
            "        <proyecto numproyecto=\"y2\">\n" +
            "            <nombreproyecto>Monitor</nombreproyecto>\n" +
            "            <ciudad>Roma</ciudad>\n" +
            "        </proyecto>\n" +
            "        <proyecto numproyecto=\"y3\">\n" +
            "            <nombreproyecto>OCR</nombreproyecto>\n" +
            "            <ciudad>Atenas</ciudad>\n" +
            "        </proyecto>\n" +
            "        <proyecto numproyecto=\"y4\">\n" +
            "            <nombreproyecto>Consola</nombreproyecto>\n" +
            "            <ciudad>Atenas</ciudad>\n" +
            "        </proyecto>\n" +
            "        <proyecto numproyecto=\"y5\">\n" +
            "            <nombreproyecto>RAID</nombreproyecto>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </proyecto>\n" +
            "        <proyecto numproyecto=\"y6\">\n" +
            "            <nombreproyecto>EDS</nombreproyecto>\n" +
            "            <ciudad>Oslo</ciudad>\n" +
            "        </proyecto>\n" +
            "        <proyecto numproyecto=\"y7\">\n" +
            "            <nombreproyecto>Cinta</nombreproyecto>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </proyecto>\n" +
            "    </proyectos>\n" +
            "    <suministros>\n" +
            "        <suministra>\n" +
            "            <numprov>v1</numprov>\n" +
            "            <numparte>p1</numparte>\n" +
            "            <numproyecto>y1</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v1</numprov>\n" +
            "            <numparte>p1</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>700</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y1</numproyecto>\n" +
            "            <cantidad>400</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y3</numproyecto>\n" +
            "            <cantidad>300</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>500</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y5</numproyecto>\n" +
            "            <cantidad>600</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y6</numproyecto>\n" +
            "            <cantidad>400</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y7</numproyecto>\n" +
            "            <cantidad>600</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v2</numprov>\n" +
            "            <numparte>p5</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>100</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v3</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y1</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v3</numprov>\n" +
            "            <numparte>p4</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>500</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v4</numprov>\n" +
            "            <numparte>p6</numparte>\n" +
            "            <numproyecto>y3</numproyecto>\n" +
            "            <cantidad>300</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v4</numprov>\n" +
            "            <numparte>p6</numparte>\n" +
            "            <numproyecto>y7</numproyecto>\n" +
            "            <cantidad>300</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p2</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p2</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>100</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p5</numparte>\n" +
            "            <numproyecto>y5</numproyecto>\n" +
            "            <cantidad>500</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p6</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p1</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>100</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p3</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p4</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>800</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p5</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>400</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p6</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>500</cantidad>\n" +
            "        </suministra>\n" +
            "    </suministros>\n" +
            "</datos>";
        return xml;
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
    public static String getXMLEjemploInventario(){
        String ejemplo="<inventario>\n" +
            "    <producto codigo=\"P1\">\n" +
            "        <peso unidad=\"kg\">10</peso>\n" +
            "        <nombre>Ordenador</nombre>\n" +
            "        <lugar edificio=\"B\">\n" +
            "            <aula>10</aula>\n" +
            "        </lugar>\n" +
            "    </producto>\n" +
            "    <producto codigo=\"P2\">\n" +
            "        <peso unidad='g'>500</peso>\n" +
            "        <nombre>Switch</nombre>\n" +
            "        <lugar edificio=\"A\">\n" +
            "            <aula>6</aula>\n" +
            "        </lugar>\n" +
            "    </producto>\n" +
            "</inventario>";
        return ejemplo;
    }
    public static String getXSLTEjemploInventario(){
        String ejemplo="<xsl:stylesheet\n" +
            " xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
            "<xsl:template match=\"/\">\n" +
            "    <inventario>\n" +
            "    <xsl:for-each select=\"inventario/producto\">\n" +
            "        <xsl:if test=\"lugar/@edificio='B'\">\n" +
            "            <producto>\n" +
            "                <peso>\n" +
            "                    <xsl:value-of select=\"peso\"/>\n" +
            "                </peso>\n" +
            "                <nombre>\n" +
            "                    <xsl:value-of select=\"nombre\"/>\n" +
            "                </nombre>\n" +
            "                <lugar>\n" +
            "                    <xsl:attribute name=\"edificio\">\n" +
            "                        <xsl:value-of\n" +
            "                            select=\"lugar/@edificio\"/>\n" +
            "                    </xsl:attribute>\n" +
            "                    <aula>\n" +
            "                        <xsl:value-of\n" +
            "                            select=\"lugar/aula\"/>\n" +
            "                    </aula>\n" +
            "                </lugar>\n" +
            "            </producto>\n" +
            "        </xsl:if>\n" +
            "    </xsl:for-each>\n" +
            "    </inventario>\n" +
            "</xsl:template>\n" +
            "</xsl:stylesheet>";
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
    public static String getXMLEjemploBiblioteca(){
        String xml="<catalogo>\n" +
            "        <libro>\n" +
            "                <title>Don Quijote</title>\n" +
            "                <autor>Cervantes</autor>\n" +
            "        </libro>\n" +
            "        <libro>\n" +
            "                <title>Poeta en Nueva York</title>\n" +
            "                <autor>Lorca</autor>\n" +
            "        </libro>\n" +
            "</catalogo>";
        return xml;
    }
    public static String getXMLAlumnosParaXQuery(){
        String resultado="<modulo xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
"    xs:noNamespaceSchemaLocation=\"modulo.xsd\">\n" +
"    <alumnos>\n" +
"        <alumno cod=\"n12344345\">\n" +
"            <apenom>Alcalde García, Luis</apenom>\n" +
"            <direc>Las Manos, 24</direc>\n" +
"            <pobla>Lamadrid</pobla>\n" +
"            <telef>942756645</telef>\n" +
"        </alumno>\n" +
"        <alumno cod=\"n43483437\">\n" +
"            <apenom>González Pérez, Olga</apenom>\n" +
"            <direc>Miraflor 28 - 3A</direc>\n" +
"            <pobla>Torres</pobla>\n" +
"            <telef>942564355</telef>\n" +
"        </alumno>\n" +
"        <alumno cod=\"n88234942\">\n" +
"            <apenom>Fernández Díaz, María</apenom>\n" +
"            <direc>Luisa Fernanda 53</direc>\n" +
"            <pobla>Miera</pobla>\n" +
"            <telef>942346945</telef>\n" +
"        </alumno>   \n" +
"    </alumnos>\n" +
"    <asignaturas>\n" +
"        <nombre cod=\"a1\">FH</nombre>\n" +
"        <nombre cod=\"a2\">FOL</nombre>\n" +
"        <nombre cod=\"a3\">ISO</nombre>\n" +
"        <nombre cod=\"a4\">LMSGI</nombre>\n" +
"        <nombre cod=\"a5\">PAR</nombre>\n" +
"        <nombre cod=\"a6\">GBD</nombre>\n" +
"    </asignaturas>\n" +
"    <notas>\n" +
"        <calificacion alum=\"n12344345\" asig=\"a1\">4</calificacion>\n" +
"        <calificacion alum=\"n43483437\" asig=\"a1\">5</calificacion>\n" +
"        <calificacion alum=\"n88234942\" asig=\"a1\">8</calificacion>\n" +
"        <calificacion alum=\"n12344345\" asig=\"a2\">10</calificacion>\n" +
"        <calificacion alum=\"n43483437\" asig=\"a2\">7</calificacion>\n" +
"        <calificacion alum=\"n88234942\" asig=\"a2\">6</calificacion>\n" +
"        <calificacion alum=\"n12344345\" asig=\"a3\">3</calificacion>\n" +
"        <calificacion alum=\"n88234942\" asig=\"a3\">6</calificacion>\n" +
"        <calificacion alum=\"n12344345\" asig=\"a4\">8</calificacion>\n" +
"        <calificacion alum=\"n43483437\" asig=\"a4\">4</calificacion>\n" +
"        <calificacion alum=\"n12344345\" asig=\"a5\">6</calificacion>\n" +
"        <calificacion alum=\"n12344345\" asig=\"a6\">9</calificacion>\n" +
"    </notas>\n" +
"</modulo>";
        return resultado;
    }
}
