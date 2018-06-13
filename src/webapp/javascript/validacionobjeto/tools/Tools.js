
/***************************************** INICIO FUNCIONES DIRECCION ************************************************************************/
	function consultaDireccionGrid(frmPantalla) 
	{		
		if (frmPantalla.txtColonia.value == "" && frmPantalla.txtDelegacionMunicipio.value == "" && frmPantalla.txtCodigoPostal.value == "") 
		{
			codeDIVHTML("gridbusquedaDireccion","");
			adminDIV("gridbusquedaDireccion","hidden","none");
		} else {
			var strName = frmPantalla.name;
			ToolsAjax.consultaDireccionGrid(frmPantalla.txtColonia.value,
											frmPantalla.txtDelegacionMunicipio.value,
											frmPantalla.txtCodigoPostal.value,
											frmPantalla.selEstado[frmPantalla.selEstado.selectedIndex].text,
											strName,
											consultaDireccionGrid_CallBack);
		}
	}	
			
	function consultaDireccionGrid_CallBack(data)
	{
		adminDIV("gridbusquedaDireccion","visible","inline");
		codeDIVHTML("gridbusquedaDireccion",data);
	}	 

	function registroDireccion(txtDireccion,frmPantalla) 
	{
		var sdireccioncompleta = txtDireccion.value;
		var sdireccioncompleta_array=sdireccioncompleta.split("-");	    
		frmPantalla.txtColonia.value = sdireccioncompleta_array[0];    	
		frmPantalla.txtDelegacionMunicipio.value = sdireccioncompleta_array[1];    	
		frmPantalla.txtCodigoPostal.value = sdireccioncompleta_array[2];    	
		frmPantalla.selEstado.selectedIndex = compareSelect(frmPantalla.selEstado,sdireccioncompleta_array[3]);			
		frmPantalla.hdnCodigoPostal.value = sdireccioncompleta_array[4];
		codeDIVHTML("gridbusquedaDireccion","");
		adminDIV("gridbusquedaDireccion","hidden","none");
		frmPantalla.txtColonia.focus();    			
	}
	 
/***************************************** TERMINA FUNCIONES DIRECCION ************************************************************************/
	 	
	/**
	 * funcion que invoca la funcionalidad de calcular la edad
	 * en anos, meses y dias de CalculaEdad.js
	 */
	function obtenerEdad(frmPantalla) 
	{				
	 	txtFechaNac = frmPantalla.txtFechaNacimiento.value;
		if( txtFechaNac != null && txtFechaNac != "" ) 
		{
			txtFechaNac = txtFechaNac.toUpperCase();
		 	intEdadAnios = calcularAniosFecha(txtFechaNac, "-");
		 	if(intEdadAnios < 0)
		 	{
		 		alert("Fecha de nacimiento incorrecta");
		 		frmPantalla.txtFechaNac.value = "";
		 		frmPantalla.txtEdadDescompuesta.value = "";
		 		return false;
		 	}
		 	intEdadMeses = calcularMesesFecha(txtFechaNac, "-");
	 		intEdadDias  = calcularDiasFecha(txtFechaNac, "-"); 		 	
	 		frmPantalla.txtEdadDescompuesta.value = intEdadAnios + "-" + intEdadMeses + "-" + intEdadDias;
	 	}
	}