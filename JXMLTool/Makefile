DIRECTORIO_CLASES=lib/classes
CLASES_PROYECTO=build/classes
ARCHIVO_JAR=JXMLTool.jar
ARCHIVO_JAR_CONSULTAS=ProcesadorConsultas.jar
all:
	rm $(ARCHIVO_JAR); jar -cvfe $(ARCHIVO_JAR) io.github.oscarmaestre.jxmltool.Interfaz -C $(DIRECTORIO_CLASES) . \
							-C $(CLASES_PROYECTO) . 
							
xquery:
	rm $(ARCHIVO_JAR); jar -cvfe $(ARCHIVO_JAR_CONSULTAS) io.github.oscarmaestre.jxmltool.ProcesadorConsultasCLI -C $(DIRECTORIO_CLASES) . \
							-C $(CLASES_PROYECTO) . 
