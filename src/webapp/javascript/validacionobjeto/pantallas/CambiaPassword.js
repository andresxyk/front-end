
	function init() {
		DWRUtil.useLoadingMessage();
		Seguridad.getUser(window.document.frmCambiaPassword.txtLoginUser.value,initUsuario_CallBack);					
	}

    function initUsuario_CallBack(data) {
    		
    	var frmPantalla = window.document.frmCambiaPassword;		

		document.getElementById('txtidUser').className = 'textflat';
		frmPantalla.txtidUser.disabled = true;
		frmPantalla.txtidUser.value = data.Id;
    	
		document.getElementById('txtLoginUser').className = 'textflat';
		frmPantalla.txtLoginUser.disabled = true;
		frmPantalla.txtLoginUser.value = data.UserName;
    	
    	document.getElementById('txtNombreUser').className = 'textflat';
		frmPantalla.txtNombreUser.disabled = true;
		frmPantalla.txtNombreUser.value = (data.FirstName + " " + data.LastName);
			
    }
	