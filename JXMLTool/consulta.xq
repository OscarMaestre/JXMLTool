for $parte in doc("datos.xml")//parte
return
<resultado>
	{
		$parte/nombreparte
	}
	{
		sum (
			doc("datos.xml")//suministra[numparte=$parte/@numparte]/cantidad
			)
	}
</resultado>