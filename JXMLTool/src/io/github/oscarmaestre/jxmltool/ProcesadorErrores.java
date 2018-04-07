package io.github.oscarmaestre.jxmltool;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/*Java obliga a que cuando se usa un parser con
validacion activemos algun errorHandler. Ponemos uno vacio
que lo unico que hace es propagar excepciones.      */
public class ProcesadorErrores implements ErrorHandler{

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        throw (exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw (exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw (exception);
    }

}
