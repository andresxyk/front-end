/*
* Esta funcion esta encargada de intercambiar
* opciones de 2 combo events
*/
	
	function editaRoles(forma,cboOrigen,cboDestino){
		var cboOrigen = eval("window.document."+forma+"."+cboOrigen);
	    var cboDestino = eval("window.document."+forma+"."+cboDestino);
	    var j = cboOrigen.length;	    
	    for(var i=0; i < j; i++){
	    	if (cboOrigen.options[i].selected==true){
	    		cboDestino.add(new Option(cboOrigen.options[i].text,cboOrigen.options[i].value, true, true));	    			    		
	    		cboOrigen.options[i]=null;
	    		i--;
	    		j--;
	    	}	    	
	    }	    
	}
	
/*
* Esta funcion esta encargada de seleccionar todas
* las funciones de un combo para que se vayan
* en el submit 
*/

	function selectAll(forma,cboRolesAsig,cboRolesSinAsig){		
		var cboAsignados = eval("window.document."+forma+"."+cboRolesAsig);
		var cboSinAsig = eval("window.document."+forma+"."+cboRolesSinAsig);
		for(var i=0; i < cboAsignados.length; i++){
			cboAsignados.options[i].selected = true;
		}	
		for(var i=0; i < cboSinAsig.length; i++){
			cboSinAsig.options[i].selected = false;
		}	
	}