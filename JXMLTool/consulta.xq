for $suministra in
  doc("datos.xml")/datos/suministros/suministra
for $proyecto in
  doc("datos.xml")/datos/proyectos/proyecto
where $suministra/numproyecto = $proyecto/@numproyecto

return $proyecto/nombreproyecto