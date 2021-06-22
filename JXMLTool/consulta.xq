<resultadoconsultaxquery>{for $parte in doc("datos.xml")//parte[peso>=15]
for $proyecto in doc("datos.xml")//proyecto[ciudad=$parte/ciudad]
return  <resultado>
		{$parte/nombreparte}
		{$proyecto/ciudad}
        </resultado>}</resultadoconsultaxquery>