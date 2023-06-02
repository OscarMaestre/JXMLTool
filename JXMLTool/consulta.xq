for $proveedor in doc("datos.xml")//proveedor
	for $suministra in doc("datos.xml")//suministra
		where $suministra/numprov=$proveedor/@numprov
			group by $nombreprov:=$proveedor/nombreprov
		let $max:=max($suministra/cantidad)
return 	<resultado>
		{$nombreprov}
		{$max}
	</resultado>	