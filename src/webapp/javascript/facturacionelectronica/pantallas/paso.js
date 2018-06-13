	function envioPasos(strPantalla) {
    	var liga = strPantalla + "?kordensucursalpaso=" + trim(document.getElementById('consecutivo').value) +"&emailpaso=" + trim(document.getElementById('email').value) + "&passwordpaso=" + trim(document.getElementById('password').value) ;
		window.location.href =liga;
    	return true;    		
	}

	function envioFinal() {
		if (IsEmail(trim(document.getElementById('email').value))) {
	    	var liga = "validaAccesoFacturacionElectronica.jsp?kordensucursalfinal=" + trim(document.getElementById('consecutivo').value) +"&emailfinal=" + trim(document.getElementById('email').value) + "&passwordfinal=" + trim(document.getElementById('password').value);
			window.location.href =liga;
	    	return true;    		
		} else {
			alert('Correo Electrónico No Valido !!!!!')
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