 	function persistentesExamenes() {		
        var frmPantalla = window.document.frmConsultaOrden;
        var intRol = frmPantalla.idRol.value;
        var boolGerente = false;
        if (intRol == 330) {
        	boolGerente = true;
        } else {
        	boolGerente = false;
        }
		DatosExamen.persistentesExamenes(frmPantalla.txtAdmision.value,
		                       										 0,
		                       							   boolGerente,
		                                          RefreshGrid_CallBack);
	}	

 	/*****Versi&oacute;n 28/03/2013
 	 * BY*/
 	function persistentesExamenesFac() {		
        var frmPantalla = window.document.frmConsultaOrden;
        var cSucursal = document.getElementById("IdSucursalActual");
        
		DatosExamen.persistentesExamenesFac(frmPantalla.txtAdmision.value,
											frmPantalla.IdUnidadActual.value,
		                       										0,
		                                          RefreshGrid_CallBack);
	}		
 	
 	
    function RefreshGrid_CallBack(data)
    {
        var frmPantalla = window.document.frmConsultaOrden;
		codeDIVHTML("Grilla",data[0]);
	 	codeDIVHTML("buscarExamenesACotizar","");
	    adminDIV("buscarExamenesACotizar","hidden","none");			
	}

	function AgregarProducto(selProducto) {		
        var frmPantalla = window.document.frmConsultaOrden;
		 clavenew = TypeObjeto(frmPantalla.selConvenios); 
		 productonew = TypeObjeto(selProducto); 
		if (confirm("Estas seguro de agregar el examen o paquete " + productonew + " a la orden, con el convenio " + clavenew + "?")) {
			 MantenimientoOrdenFacturacion.agregarExamen_Perfil(TypeObjeto(selProducto),frmPantalla.txtAdmision.value,frmPantalla.idUsuario.value,clavenew,AgregarProducto_CallBack);
		}    		
	}	
    
	function AgregarProducto_CallBack(data) {
         var frmPantalla = window.document.frmConsultaOrden;
		 alert(data);
		 ToolFacSucursalesAjax.changeConvenioOrdenesFac(frmPantalla.txtAdmision.value,clavenew,CambiarConvenio_CallBack); 
		 DatosOrden.getOrdenFac(kAdmision,actualizarOrdenExamen_CallBack);
	}

	function AgregarProducto_CallBack(data) {
        var frmPantalla = window.document.frmConsultaOrden;
		alert(data);
		ToolFacSucursalesAjax.changeConvenioOrdenesFac(frmPantalla.txtAdmision.value,clavenew,AgregarProductoCambiarConvenio_CallBack); 
		DatosOrden.getOrdenFac(frmPantalla.txtAdmision.value,actualizarOrdenExamen_CallBack);
	}

    function AgregarProductoCambiarConvenio_CallBack(data) {

	}
    
    function addOptionSelect(objId) {
        adminDIV("gridprogressbar","visible","inline");
    	DatosCliente.buscarExamenesConvenio(TypeObjeto(objId),addOptionSelect_CallBack);
    }
     
    function addOptionSelect_CallBack(data) {
    	DWRUtil.removeAllOptions("selExamenes");
    	DWRUtil.removeAllOptions("selPerfiles");
    	loadCombo(data);
    }        
    
    function loadCombo(data){    	
        var comboBox = document.getElementById("selExamenes");
        var oOption = document.createElement("OPTION"); 
        var strCodigos = data[1];
        var strNombres = data[0];
        var ArrayCodigos = strCodigos.split("&");
        var ArrayNombres = strNombres.split("&");
        oOption.value = "0";
        oOption.text = "----- Select -----";
        oOption.selected = true;
        addOption(comboBox,oOption);
        for (var i = 0; i < ArrayCodigos.length; i++)
        {
            var oOption = document.createElement("OPTION");
            oOption.value = ArrayCodigos[i];
            oOption.text =  ArrayNombres[i] + " " + ArrayCodigos[i];
            addOption(comboBox,oOption);    
        }
        /************************Perfiles******************************/
        var comboBoxPerfiles = document.getElementById("selPerfiles");
        var oOptionPerfiles = document.createElement("OPTION"); 
        var strCodigosPerfiles = data[3];
        var strNombresPerfiles = data[2];
        var ArrayCodigosPerfiles = strCodigosPerfiles.split("&");
        var ArrayNombresPerfiles = strNombresPerfiles.split("&");
        oOptionPerfiles.value = "";
        oOptionPerfiles.text = "----- Select -----";
        oOptionPerfiles.selected = true;
        addOption(comboBoxPerfiles,oOptionPerfiles);
        for (var i = 0; i < ArrayCodigosPerfiles.length; i++)
        {
            var oOptionPerfiles = document.createElement("OPTION");
            oOptionPerfiles.value = ArrayCodigosPerfiles[i];
            oOptionPerfiles.text =  ArrayNombresPerfiles[i] + " " + ArrayCodigosPerfiles[i];
            addOption(comboBoxPerfiles,oOptionPerfiles);    
        }        
        adminDIV("gridprogressbar","hidden","none");        
    }
       
    function addOption(selectElement,newOption)
    {
        if (selectElement !== null)
        {
           try 
           {
               selectElement.add(newOption,null);
           }    
           catch (e)
           {
               selectElement.add(newOption);
           }
       }
    }