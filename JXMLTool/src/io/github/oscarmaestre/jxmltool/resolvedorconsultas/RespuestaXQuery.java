package io.github.oscarmaestre.jxmltool.resolvedorconsultas;

public class RespuestaXQuery {
    protected String textoXQuery;
    protected float puntuacion;
    protected String resultadoConsulta;

    public RespuestaXQuery(String textoXQuery, float puntuacion) {
        this.textoXQuery = textoXQuery;
        this.puntuacion = puntuacion;
    }

    public String getTextoXQuery() {
        return textoXQuery;
    }


    public float getPuntuacion() {
        return puntuacion;
    }

    
    public String getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(String resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    @Override
    public String toString() {
        return "RespuestaXQuery{" + "textoXQuery=" + textoXQuery + ", puntuacion=" + puntuacion + ", resultadoConsulta=" + resultadoConsulta + '}';
    }
    
}
