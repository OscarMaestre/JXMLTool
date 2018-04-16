for $alumno in doc("clase.xml")//alumnos/alumno
order by $alumno/apenom descending
return <alumno>{ data($alumno/apenom),"- teléfono",data($alumno/telef) }</alumno>