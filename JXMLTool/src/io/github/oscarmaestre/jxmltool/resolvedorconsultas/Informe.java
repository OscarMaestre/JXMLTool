package io.github.oscarmaestre.jxmltool.resolvedorconsultas;

public class Informe {
    private RespuestaXQueryAlumno[] respuestas;
    private String nombre;
    private String apellidos;

    public Informe(RespuestaXQueryAlumno[] respuestas, String nombre, String apellidos) {
        this.respuestas = respuestas;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public RespuestaXQueryAlumno[] getRespuestas() {
        return respuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    private String generarCelda(String texto){
        
        texto=texto.replace("<", "&lt;");
        texto=texto.replace(">", "&gt;");
        texto=texto.replace("\n", "<br>");
        return "<td>"+texto+"</td>\n";
    }
    private String generarCeldaConPre(String texto){
        
        texto=texto.replace("<", "&lt;");
        texto=texto.replace(">", "&gt;");
        return "<td><pre>"+texto+"</pre></td>\n";
    }
    
    private String generarCelda(int texto){
        return generarCelda(""+texto);
    }
    private String generarFila(String t1, String t2){
        return "<tr>"+this.generarCelda(t1)+this.generarCelda(t2)+"</tr>\n";
    }
    private String generarFilaConPre(String t1, String t2){
        return "<tr>"+this.generarCelda(t1)+this.generarCeldaConPre(t2)+"</tr>\n";
    }
    private String generarFila(int t1, String t2){
        return this.generarFila(""+t1, t2);
    }
    private String generarFila(String t1, int t2){
        return this.generarFila(t1, ""+t2);
    }
    
    public String generarInforme(){
        StringBuilder sb=new StringBuilder();
        sb.append("<h1>");
        String nombreAlumno=this.apellidos+", "+this.nombre;
        sb.append(nombreAlumno);
        sb.append("</h1>");
        
        for (int i = 0; i < respuestas.length; i++) {
            RespuestaXQueryAlumno respuesta = respuestas[i];
            sb.append("<table border='1'>");
            sb.append(this.generarFila("Pregunta", i+1));
            
            sb.append(this.generarFilaConPre("Resultados del alumno", respuesta.resultadoDeSuConsulta));
            sb.append(this.generarFilaConPre("Resultados oficiales", respuesta.respuestaAsociada.resultadoConsulta));
            
            sb.append(this.generarFilaConPre("XQuery del alumno",respuesta.textoXQuery));
            sb.append(this.generarFilaConPre("XQuery oficial",respuesta.respuestaAsociada.textoXQuery));
             
            
            sb.append("<tr>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("</tr>");
            sb.append("</table>");
        }
        /*Fin de generación del informe de preguntas*/
        sb.append("<br><br>");
        return sb.toString();
        
    }

    
    
}
