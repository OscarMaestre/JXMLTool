package io.github.oscarmaestre.jxmltool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.xquery.XQException;

public class GeneradorEjercicios {
    private static class Pregunta{
        public String pregunta;
        public String xquery;
        public final int TIPO_RESPUESTA_XML             =   1;
        public final int TIPO_RESPUESTA_VALOR_UNICO     =   2;
        public int tipoRespuesta = TIPO_RESPUESTA_XML;
    }
    public static String getProveedoresPartesOriginal(){
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
    public static String getProveedoresPartes_2(){
        String xml="<datos>\n" +
            "    <proveedores>\n" +
            "        <proveedor numprov=\"v1\">\n" +
            "            <nombreprov>Smith</nombreprov>\n" +
            "            <estado>10</estado>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v2\">\n" +
            "            <nombreprov>Jones</nombreprov>\n" +
            "            <estado>20</estado>\n" +
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
            "            <color>Azul</color>\n" +
            "            <peso>12</peso>\n" +
            "            <ciudad>Londres</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p2\">\n" +
            "            <nombreparte>Perno</nombreparte>\n" +
            "            <color>Verde</color>\n" +
            "            <peso>12</peso>\n" +
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
            "            <ciudad>Roma</ciudad>\n" +
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
            "            <cantidad>100</cantidad>\n" +
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
            "            <cantidad>100</cantidad>\n" +
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
            "            <cantidad>200</cantidad>\n" +
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
            "            <cantidad>300</cantidad>\n" +
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
    public static String getProveedoresPartes_3(){
        String xml="<datos>\n" +
            "    <proveedores>\n" +
            "        <proveedor numprov=\"v1\">\n" +
            "            <nombreprov>Smith</nombreprov>\n" +
            "            <estado>20</estado>\n" +
            "            <ciudad>Roma</ciudad>\n" +
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
            "            <ciudad>Londres</ciudad>\n" +
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
            "            <ciudad>Paris</ciudad>\n" +
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
            "            <ciudad>Roma</ciudad>\n" +
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
            "            <cantidad>900</cantidad>\n" +
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
            "            <cantidad>100</cantidad>\n" +
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
            "            <cantidad>600</cantidad>\n" +
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
            "            <cantidad>400</cantidad>\n" +
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
            "            <cantidad>100</cantidad>\n" +
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
    public static String getProveedoresPartes_4(){
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
            "            <ciudad>Londres</ciudad>\n" +
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
            "            <color>Rojo</color>\n" +
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
            "            <ciudad>Roma</ciudad>\n" +
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
            "            <cantidad>100</cantidad>\n" +
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
            "            <cantidad>700</cantidad>\n" +
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
            "            <cantidad>100</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p2</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>100</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p2</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>600</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p5</numparte>\n" +
            "            <numproyecto>y5</numproyecto>\n" +
            "            <cantidad>100</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p6</numparte>\n" +
            "            <numproyecto>y2</numproyecto>\n" +
            "            <cantidad>800</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p1</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>600</cantidad>\n" +
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
    public static String getProveedoresPartes_5(){
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
    public static String getProveedoresPartes_6(){
        String xml="<datos>\n" +
            "    <proveedores>\n" +
            "        <proveedor numprov=\"v1\">\n" +
            "            <nombreprov>Smith</nombreprov>\n" +
            "            <estado>20</estado>\n" +
            "            <ciudad>Roma</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v2\">\n" +
            "            <nombreprov>Jones</nombreprov>\n" +
            "            <estado>10</estado>\n" +
            "            <ciudad>Paris</ciudad>\n" +
            "        </proveedor>\n" +
            "        <proveedor numprov=\"v3\">\n" +
            "            <nombreprov>Blake</nombreprov>\n" +
            "            <estado>30</estado>\n" +
            "            <ciudad>Toronto</ciudad>\n" +
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
            "            <ciudad>Atenas</ciudad>\n" +
            "        </parte>\n" +
            "        <parte numparte=\"p3\">\n" +
            "            <nombreparte>Tornillo</nombreparte>\n" +
            "            <color>Azul</color>\n" +
            "            <peso>17</peso>\n" +
            "            <ciudad>Paris</ciudad>\n" +
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
            "            <ciudad>Atenas</ciudad>\n" +
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
            "            <ciudad>Londres</ciudad>\n" +
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
            "            <cantidad>600</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p4</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>200</cantidad>\n" +
            "        </suministra>\n" +
            "        <suministra>\n" +
            "            <numprov>v5</numprov>\n" +
            "            <numparte>p5</numparte>\n" +
            "            <numproyecto>y4</numproyecto>\n" +
            "            <cantidad>100</cantidad>\n" +
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
    public static String getCadenaAzar(String[] cadenas){
        Random generador=new Random();
        int posVector=generador.nextInt(cadenas.length);
        return cadenas[posVector];
    }
    public static String getColorAzar(){
        String[] colores={"Rojo", "Azul", "Verde"};
        return getCadenaAzar(colores);
        
    }
    public static String getProveedorAzar(){
        String[] proveedores={"Adams", "Blake", "Clarke", "Smith", "Jones"};
        return getCadenaAzar(proveedores);
    }
    public static String getProyectoAzar(){
        String[] proyectos={"Clasificador", "Monitor", "OCR", "Consola", "RAID", "EDS", "Cinta"};
        return getCadenaAzar(proyectos);
    }
    public static String getCiudadAzar(){
        String[] ciudades={"Paris", "Atenas", "Roma", "Londres"};
        return getCadenaAzar(ciudades);
    }
    public static String getPesoAzar(){
        String[] pesos={"12", "14", "17", "15"};
        return getCadenaAzar(pesos);
    }
    public static int getCantidadSuministroAzar(){
        int MAX_CENTENA=10;
        Random generadorAzar=new Random();
        int numAzar=generadorAzar.nextInt(MAX_CENTENA);
        return numAzar*100; 
    }
    
    private static ArrayList<Pregunta> getPaquetePreguntas(){
        ArrayList<Pregunta> preguntas=new ArrayList<Pregunta>();
        String textoPregunta;
        textoPregunta="Extraer toda la información de los proveedores cuya ciudad sea "+getCiudadAzar();
        String xqueryPregunta="for $a in doc('datos.xml')/datos/proveedores/proveedor return $a";
        Pregunta p1;
        p1 = new Pregunta();
        p1.pregunta=textoPregunta;
        p1.xquery=xqueryPregunta;
        preguntas.add(p1);
        
        textoPregunta="Extraer solo el nombre de los proveedores cuya ciudad sea "+getCiudadAzar();
        xqueryPregunta="for $a in doc('datos.xml')/datos/proveedores/proveedor return data($a/nombreprov/text())";
        Pregunta p11;
        p11 = new Pregunta();
        p11.pregunta=textoPregunta;
        p11.xquery=xqueryPregunta;
        preguntas.add(p11);
        
        String colorAzar=getColorAzar();
        textoPregunta="Extraer el numero de parte de partes cuyo color sea " + colorAzar;
        xqueryPregunta="for $a in doc('datos.xml')/datos/partes/parte[color='"+colorAzar+"'] return data($a/@numparte) ";
        Pregunta p2;
        p2 = new Pregunta();
        p2.pregunta=textoPregunta;
        p2.xquery=xqueryPregunta;
        preguntas.add(p2);
        
        
        String pesoAzar=getPesoAzar();
        textoPregunta="Sin usar WHERE mostrar las partes que tienen un peso mayor de  " + pesoAzar;
        xqueryPregunta="for $a in doc('datos.xml')/datos/partes/parte[peso>'"+pesoAzar+"'] return $a ";
        Pregunta p3;
        p3 = new Pregunta();
        p3.pregunta=textoPregunta;
        p3.xquery=xqueryPregunta;
        preguntas.add(p3);
        
        
        textoPregunta="Repetir la consulta anterior usando where";
        xqueryPregunta="for $a in doc('datos.xml')/datos/partes/parte where $a/peso > "+pesoAzar+" return $a ";
        Pregunta p31;
        p31 = new Pregunta();
        p31.pregunta=textoPregunta;
        p31.xquery=xqueryPregunta;
        
        preguntas.add(p31);
        
        colorAzar=getColorAzar();
        textoPregunta="Extraer el nombre de parte y el peso de las partes cuyo color sea " + colorAzar;
        xqueryPregunta="for $a in doc('datos.xml')/datos/partes/parte where $a/color='"+colorAzar+"' return ($a/nombreparte, $a/peso) ";
        Pregunta p4;
        p4 = new Pregunta();
        p4.pregunta=textoPregunta;
        p4.xquery=xqueryPregunta;
        preguntas.add(p4);
        
        int cantidadSuministroAzar=getCantidadSuministroAzar();
        textoPregunta="Contar cuantas partes se han suministrado en una cantidad mayor de " + cantidadSuministroAzar;
        xqueryPregunta="let $a:=doc('datos.xml')/datos/suministros/suministra[cantidad > " + 
                cantidadSuministroAzar + "] return count($a)";
        Pregunta p5;
        p5 = new Pregunta();
        p5.tipoRespuesta= p5.TIPO_RESPUESTA_VALOR_UNICO;
        p5.pregunta=textoPregunta;
        p5.xquery=xqueryPregunta;
        preguntas.add(p5);
        
        
        cantidadSuministroAzar=getCantidadSuministroAzar();
        textoPregunta="De la tabla suministros extraer el nombre de "+
                "los proyectos con una cantidad suministrada menor que " + 
                cantidadSuministroAzar;
        xqueryPregunta="for $a in doc('datos.xml')/datos/proyectos/proyecto, "+ 
                "$b in doc('datos.xml')/datos/suministros/suministra " + 
                "where $a/@numproyecto = $b/numproyecto and" + 
                " $b/cantidad < " + cantidadSuministroAzar + 
                
                " return \n" +
                "<resultado>{data($a/nombreproyecto)}, {data($b/cantidad)}</resultado>";
                
        Pregunta p6;
        p6 = new Pregunta();
        p6.tipoRespuesta= p5.TIPO_RESPUESTA_VALOR_UNICO;
        p6.pregunta=textoPregunta;
        p6.xquery=xqueryPregunta;
        preguntas.add(p6);

        return preguntas;
    }
    
    public static String getProveedoresPartesXMLAzar(){
        String xml="";
        
        ArrayList<Supplier<String>> vectorFunciones;
        vectorFunciones=new ArrayList<Supplier<String>>();
        /* Añadimos todas las funciones al vector*/
        vectorFunciones.add(
                () -> GeneradorEjercicios.getProveedoresPartesOriginal()
        );
        vectorFunciones.add(
                () -> GeneradorEjercicios.getProveedoresPartes_2()
        );
        vectorFunciones.add(
                () -> GeneradorEjercicios.getProveedoresPartes_3()
        );
        vectorFunciones.add(
                () -> GeneradorEjercicios.getProveedoresPartes_4()
        );
        vectorFunciones.add(
                () -> GeneradorEjercicios.getProveedoresPartes_5()
        );
        vectorFunciones.add(
                () -> GeneradorEjercicios.getProveedoresPartes_6()
        );
        
        /* Y elegimos un elemento al azar*/
        Random generador=new Random();
        int pos=generador.nextInt(vectorFunciones.size());
        /* Se llama al get del elemento del vector, que en realidad
        llama a las funciones que hemos empaquetado   */
        xml = vectorFunciones.get(pos).get();
        return xml;
    }
    public static void main(String[] args) throws XQException, IOException{
        ArrayList<Pregunta> preguntas;
        preguntas = getPaquetePreguntas();
        String xml=GeneradorEjercicios.getProveedoresPartesXMLAzar();
        System.out.println("Para la base de datos siguiente:");
        System.out.println();
        System.out.println(xml);
        System.out.println("");
        System.out.println("");
        System.out.println("Resolver las siguientes preguntas. Obsérvese que");
        System.out.println("a modo de pista se proporciona cual debe ser el resultado ");
        System.out.println("de la consulta.");
        int numConsulta=0;
        for (Pregunta p : preguntas){
            String textoPregunta=p.pregunta;
            String xqueryPregunta=p.xquery;
            System.out.println("Consulta "+numConsulta);
            numConsulta++;
            System.out.println(textoPregunta);
            //System.out.println(xqueryPregunta);
            System.out.println("");
            String resultadoDeseable;
            resultadoDeseable=ProcesadorXML.ejecutarXQuery(xqueryPregunta, xml);
            System.out.println("Resultado que se debería obtener:");
            System.out.println("----------------------------------------------");
            System.out.println(resultadoDeseable);
            System.out.println("----------------------------------------------");
            System.out.println("");
            
        }
    }
}
