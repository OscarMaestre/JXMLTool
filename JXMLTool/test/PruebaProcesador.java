/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.github.oscarmaestre.jxmltool.ProcesadorXML;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xquery.XQException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ogomez
 */
public class PruebaProcesador {
    
    public PruebaProcesador() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void pruebaAnalizador() {
        String xml=ProcesadorXML.getXMLejemploDTD();
        try {
            Document doc=ProcesadorXML.analizarXML(xml, false);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PruebaProcesador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PruebaProcesador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PruebaProcesador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void pruebaDTD() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        String xml=ProcesadorXML.getXMLejemploDTD();
        String dtd=ProcesadorXML.getDTDejemplo();
        Document doc=ProcesadorXML.analizarXML(xml, false);
        ProcesadorXML.DTDValidaXML(dtd, doc);
        
    }
    
    @Test
    public void pruebaEsquemaSimple() throws SAXException, IOException{
        String xml=ProcesadorXML.getXMLEjemploSchema();
        String esquema=ProcesadorXML.getSchemaEjemplo();
        boolean esValido=ProcesadorXML.XMLSchemaValidaXML(esquema, xml);
        Assert.assertEquals(esValido, true);
    }
    @Test
    public void pruebaXPathSimple() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        String xml=ProcesadorXML.getXMLEjemploSchema();
        String expresion="//codigo";
        NodeList resultado=ProcesadorXML.evaluarXPath(expresion, xml);
        //String xmlTabulado=ProcesadorXML.tabularXML(resultado);
        System.out.println(resultado);
    }
    
    @Test
    public void pruebaXQuerySimple() throws XQException, IOException{
        String xmlEjemplo=ProcesadorXML.getXMLAlumnosParaXQuery();
        String ejemploXQuery="for $a in doc(\"datos.xml\")//alumnos/alumno\n" +
            "where $a/@cod union $a/../../notas/nota/@alum\n" +
            "return\n" +
            "<alumno>{ data($a/apenom) }</alumno>";
        String resultado=ProcesadorXML.ejecutarXQuery(ejemploXQuery, xmlEjemplo);
        System.out.println(resultado);
    }
}
