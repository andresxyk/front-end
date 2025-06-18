	function envioPasos(strPantalla) {
    	var liga = strPantalla + "?kordensucursalpaso=" + trim(document.getElementById('widgetu7947_input').value) +"&emailpaso=" + trim(document.getElementById('widgetu7952_input').value) + "&passwordpaso=" + trim(document.getElementById('widgetu7973_input').value) ;
		window.location.href =liga;
    	return true;    		
	}

	function envioFinal() {
		if (IsEmail(trim(document.getElementById('widgetu7952_input').value))) {
			document.getElementById('u8148_img').src =  document.getElementById('loaderAjax').src;	
            document.getElementById('u8148_img').height = '150';
            document.getElementById('u8148_img').width = '150';			
	    	var liga = "validaAccesoFacturacionElectronica.jsp?kordensucursalfinal=" + trim(document.getElementById('widgetu7947_input').value) +"&emailfinal=" + trim(document.getElementById('widgetu7952_input').value) + "&passwordfinal=" + trim(document.getElementById('widgetu7973_input').value) + "&strimagenfinal=" + trim(document.getElementById('widgetu7978_input').value);
			window.location.href =liga;
	    	return true;    		
		} else {
			alert('Correo Electr&oacute;nico No Valido !!!!!')
	    	return false;    		
		}
	}
	
	function trim (myString) {
		return myString.replace(/^\s+/g,'').replace(/\s+$/g,'')
	}
	
	function IsEmail(email) {
        var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if(!regex.test(email)) {
           return false;
        }else{
           return true;
        }
    }