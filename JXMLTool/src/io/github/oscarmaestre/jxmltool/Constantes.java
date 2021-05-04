package io.github.oscarmaestre.jxmltool;

import io.github.oscarmaestre.jxmltool.resolvedorconsultas.RespuestaXQuery;

public class Constantes {
    public final static String ARCHIVO_DATOS="datos.xml";
    public final static String DOC = "doc(\""+ARCHIVO_DATOS+"\")";
    
    public final static RespuestaXQuery[] consultasXQuery={
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/ciudad",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/nombreprov",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/ciudad",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/nombreprov",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/ciudad",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/nombreprov",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/ciudad",
                2.5f
        ),
        new RespuestaXQuery(
                "for $x in doc(\"datos.xml\")//proveedor return $x/nombreprov",
                2.5f
        )
        
    };
}
