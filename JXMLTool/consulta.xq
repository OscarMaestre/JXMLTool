for $a in doc('datos.xml')//alumnos/alumno
where $a/@cod union $a/../../notas/nota/@alum
return
<alumno>{ data($a/apenom) }</alumno>