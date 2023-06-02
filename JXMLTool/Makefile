VERSION=1.2.1
DIRECTORIO_CLASES=lib/classes
CLASES_PROYECTO=build/classes
ARCHIVO_JAR=JXMLTool-$(VERSION).jar
ARCHIVO_JAR_CONSULTAS=ProcesadorConsultas.jar
all:
	make programa; make xquery
programa:
	rm $(ARCHIVO_JAR); jar -cvfe $(ARCHIVO_JAR) io.github.oscarmaestre.jxmltool.Interfaz -C $(DIRECTORIO_CLASES) . \
							-C $(CLASES_PROYECTO) . 
							
xquery:
	rm $(ARCHIVO_JAR_CONSULTAS); jar -cvfe $(ARCHIVO_JAR_CONSULTAS) io.github.oscarmaestre.jxmltool.resolvedorconsultas.ProcesadorConsultasCLI -C $(DIRECTORIO_CLASES) . \
							-C $(CLASES_PROYECTO) . 
