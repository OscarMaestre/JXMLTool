package io.github.oscarmaestre.jxmltool.servidor;

import java.util.ArrayList;

public class Examen {
    protected ArrayList<String> respuestasOficiales;

    public Examen() {
        this.respuestasOficiales=new ArrayList<>();
        this.respuestasOficiales.ensureCapacity(15);
    }
    public void anadirRespuesta(int pos, String respuesta){
        this.respuestasOficiales.add(respuesta);
    }
    
    public String getRespuesta(String num){
        int pos = Integer.parseInt(num)-1;
        return this.respuestasOficiales.get(pos);
    }
    public static Examen getExamen1(){
        Examen e=new Examen();
        String respuesta;
        
        respuesta="avg(doc(\"datos.xml\")//suministra[numparte='p1' or numparte='p2' or numparte='p4']/cantidad)";
        e.anadirRespuesta(1, respuesta);
        
        respuesta="for $proveedor in doc(\"datos.xml\")//proveedor[nombreprov='Jones' or nombreprov='Smith']\n" +
"let $media:=avg(doc(\"datos.xml\")//suministra[numprov=$proveedor/@numprov]/cantidad)\n" +
"return  <resultado>\n" +
"        {$proveedor/nombreprov}\n" +
"        <media>\n" +
"        {$media}\n" +
"        </media>\n" +
"        </resultado>";
        e.anadirRespuesta(2, respuesta);
        
        respuesta="for $proveedor in doc(\"datos.xml\")//proveedor\n" +
"    for $parte in doc(\"datos.xml\")//parte\n" +
"        for $proyecto in doc(\"datos.xml\")//proyecto\n" +
"        where $proveedor/ciudad!=$parte/ciudad or\n" +
"            $parte/ciudad!=$proyecto/ciudad or\n" +
"            $proyecto/ciudad!=$proveedor/ciudad\n" +
"return  <resultado>\n" +
"        {$proveedor/nombreprov} \n" +
"        {$proveedor/ciudad} \n" +
"        {$parte/nombreparte}\n" +
"        {$parte/ciudad}\n" +
"        {$proyecto/nombreproyecto}\n" +
"        {$proyecto/ciudad}\n" +
"        </resultado>\n";
        e.anadirRespuesta(3, respuesta);
        
        respuesta="count(doc(\"datos.xml\")//proveedor[ciudad='Paris' or estado>=20])";
        e.anadirRespuesta(4, respuesta);
        
        respuesta="for $proyecto in doc(\"datos.xml\")//proyecto[ciudad=\"Paris\"]\n" +
"    let $media:=avg(doc(\"datos.xml\")//suministra[numproyecto=$proyecto/@numproyecto]/cantidad\n" +
"    )\n" +
"    return  <resultado>\n" +
"            {$proyecto/nombreproyecto}\n" +
"            {$proyecto/ciudad}\n" +
"            <media>{$media}</media>\n" +
"            </resultado>";
        e.anadirRespuesta(5, respuesta);
        
        respuesta="for $parte in doc(\"datos.xml\")//parte\n" +
"    let $media:=avg(doc(\"datos.xml\")//parte/peso)\n" +
"    where $parte/peso>$media\n" +
"    return  <resultado>\n" +
"            {$parte/nombreparte}\n" +
"            {$parte/peso}\n" +
"            <media>{$media}</media>\n" +
"            </resultado>";
        e.anadirRespuesta(6, respuesta);
        
        respuesta="for $proyecto1 in doc(\"datos.xml\")//proyecto\n" +
"    for $proyecto2 in doc(\"datos.xml\")//proyecto\n" +
"    where $proyecto1/ciudad=$proyecto2/ciudad\n" +
"    return  <resultado>\n" +
"            {\n" +
"                data($proyecto1/@numproyecto)\n" +
"            }-{\n" +
"                data($proyecto2/@numproyecto)\n" +
"            }\n" +
"            </resultado>\n";
        e.anadirRespuesta(7, respuesta);
        
        respuesta="";
        e.anadirRespuesta(8, respuesta);
        
        respuesta="";
        e.anadirRespuesta(9, respuesta);
        
        
        respuesta="";
        e.anadirRespuesta(10, respuesta);
        
        
        
        return e;
        
        
    }
    
    public static Examen getExamen2(){
        Examen e=new Examen();
        String[] respuestas={
            "sum(for $proveedor in doc(\"datos.xml\")//proveedor[estado>=10 and estado<=20]\n" +
"	for $suministra in doc(\"datos.xml\")//suministra[numprov=$proveedor/@numprov]\n" +
"	return $suministra/cantidad)",
            
            "for $parte in doc(\"datos.xml\")//parte[color='Verde' or color='Azul']\n" +
"let $media:=avg(doc(\"datos.xml\")//suministra[numparte=$parte/@numparte]/cantidad) return  <resultado>\n" +
"		{$parte/nombreparte}\n" +
"		{$parte/color}\n" +
"		<media>{$media}</media>\n" +
"		</resultado>",
            
            "for $proyecto in doc(\"datos.xml\")//proyecto[ciudad='Londres']\n" +
"return	<resultado>\n" +
"		{$proyecto/@numproyecto}\n" +
"		{$proyecto/nombreproyecto}\n" +
"        </resultado>",
            
            
            
            "for $proveedor in doc(\"datos.xml\")//proveedor\n" +
"	where $proveedor/estado=\"10\"\n" +
"return	<resultado>\n" +
"		{\n" +
"			$proveedor/nombreprov\n" +
"		}\n" +
"		{\n" +
"			$proveedor/ciudad\n" +
"		}\n" +
"		</resultado>",
            
            "for $suministra in doc(\"datos.xml\")//suministra[cantidad>=650]\n" +
"return $suministra",
            
            "for $suministra in doc(\"datos.xml\")//suministra[(numprov='v1' or numprov='v2') and cantidad<=200]\n" +
"return $suministra",
            
            
            
            "for $parte in doc(\"datos.xml\")//parte[peso>=15]\n" +
"for $proyecto in doc(\"datos.xml\")//proyecto[ciudad=$parte/ciudad]\n" +
"return  <resultado>\n" +
"		{$parte/nombreparte}\n" +
"		{$proyecto/ciudad}\n" +
"        </resultado>"
            
        };
        for (int i = 0; i < respuestas.length; i++) {
            String respuesta = respuestas[i];
            e.anadirRespuesta(i+1, respuesta);
        }
        return e;
    }
}
