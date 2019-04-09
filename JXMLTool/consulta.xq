for $alumno in doc("notas.xml")/notas/alumnos/alumno
order by $alumno/apenom descending
return <datos>
<apellidosynombre>{$alumno/apenom}</apellidosynombre>
<nunerotelefono>{$alumno/telef}</nunerotelefono>
</datos>