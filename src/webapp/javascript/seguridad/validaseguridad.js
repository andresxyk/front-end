//Funcion que valida que un campo solo contenga letras
//forma: nombre de la forma en el VM 
//Campo: nombre del componente en el VM
//nombre: nombre del campo en el VM
function inhabilitarUsuario(aStrLiga,imodo) {
		if(imodo==0)
		{
			      if(confirm('¿Esta seguro de inhabilitar el usuario?'))
        		    {         
        		              window.document.frmUsuario.action= aStrLiga +'?imodo=' +imodo + '&eventSubmit_doAlta=action';
						 	  window.document.frmUsuario.submit();
		 	                  return false;
				
			         }
			    	else
			    	{         
			    			  return false;  
			     	}	
		}//fin de if 0
		else
		{ if(confirm('¿Esta seguro de habilitar el usuario?'))
        		    {         
        		    		  window.document.frmUsuario.action= aStrLiga +'?imodo=' +imodo + '&eventSubmit_doAlta=action';
						 	  window.document.frmUsuario.submit();
        		    		  return false;
			        }
			      else
			        {         
			        		  return false;
			        }	
			
		}//fin de else
		//-----------------------
	}
//valida la caja de contrase&ntilde;a para Usuarios_Form
function validapass(){
var username= window.document.frmUsuario.username.value;
var password= window.document.frmUsuario.password.value;
var flag=true;

if(username=="" || password=="")
{alert('Debe de llenar todos los campos');
 flag=false;
 return flag;
 
 }else if(password.length < 6){
	alert('Su password debe de tener al menos seis d&iacute;gitos');
	flag=false;
    return flag;
	
 }else if(password.length >6 || password.length==6 ){
    if(username==password){
      alert('Por su seguridad su password no debe ser igual al usuario, escriba algo diferente');
      flag=false;
      return flag;
    }
    else{
			var numeros="1234567890"
			var num= password;
			var a;
			var cont=0;
			var valido=true;
	   
			for(i=0;i<password.length;i++)
			{ a=num.charAt(i);
			   //alert(a);
			  for(j=0;j<numeros.length;j++)
				{ if(a==numeros.charAt(j))
					  { cont++;
						 //alert('Si es igual el contador es'+cont);
					  }
			  }
			}
			//alert('NUMEROS--el contador es---'+cont+''+'la longitud que tiene es '+password.length);
			if(cont==password.length)
			   { 
			     alert('¿Debe ingresar al menos una letra?');
			     flag=false;
                 return flag;
			   }
			   else{
			    var letras="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz"
			    cont=0;
			       for(i=0;i<password.length;i++)
					  {   a=num.charAt(i);
						   //alert(a);
						  for(j=0;j<letras.length;j++)
							{ if(a==letras.charAt(j))
								  { cont++;
									// alert('Si es igual el contador es'+cont);
								  }
						  }
						}
						//alert('LETRAS--el contador es---'+cont+''+'la longitud que tiene es '+password.length);
						if(cont==password.length)
						   { 
						     alert('¿Debe ingresar al menos un d&iacute;gito?');
						     flag=false;
                             return flag;
						   }
						   
			   
			   
			   }	
		}  
 }//end password.length
 else if(username==password){
    alert('Por su seguridad el password y el usuario no deben ser iguales');
	flag=false;
    return flag;
 }

return flag;

	
}
//valida la caja de contrase&ntilde;a para Usuarios_Form
function validapassCam(){
var username= window.document.frmCamContras.hdnLoginName.value;
var password= window.document.frmCamContras.txtNuevaContras.value;
var flag=true;

if(username=="" || password=="")
{alert('Debe de llenar todos los campos');
 flag=false;
 return flag;
 
 }else if(password.length < 6){
	alert('Su password debe de tener al menos seis d&iacute;gitos');
	flag=false;
	return flag;
	
 }else if(password.length >6 || password.length==6 ){
    if(username==password){
      alert('Por su seguridad su password no debe ser igual al usuario, escriba algo diferente');
      flag=false
      return flag;
    }
    else{
			var numeros="1234567890"
			var num= password;
			var a;
			var cont=0;
			var valido=true;
	   
			for(i=0;i<password.length;i++)
			{ a=num.charAt(i);
			   //alert(a);
			  for(j=0;j<numeros.length;j++)
				{ if(a==numeros.charAt(j))
					  { cont++;
						 //alert('Si es igual el contador es'+cont);
					  }
			  }
			}
			//alert('NUMEROS--el contador es---'+cont+''+'la longitud que tiene es '+password.length);
			if(cont==password.length)
			   { 
			     alert('¿Debe ingresar al menos una letra?');
			     flag=false;
			     return flag;
			   }
			   else{
			    var letras="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz"
			    cont=0;
			       for(i=0;i<password.length;i++)
					  {   a=num.charAt(i);
						   //alert(a);
						  for(j=0;j<letras.length;j++)
							{ if(a==letras.charAt(j))
								  { cont++;
									// alert('Si es igual el contador es'+cont);
								  }
						  }
						}
						//alert('LETRAS--el contador es---'+cont+''+'la longitud que tiene es '+password.length);
						if(cont==password.length)
						   { 
						     alert('¿Debe ingresar al menos un d&iacute;gito?');
						     flag=false;
						     return flag;
						   }
						   
			   
			   
			   }	
		}  
 }//end password.length
 else if(username==password){
    alert('Por su seguridad el password y el usuario no deben ser iguales');
    flag=false;
	return flag;
 }

return flag;

	
}
function validaVacios(forma, campo, nombre){
  var patron = /^[A-Za-z\s????????????_\ \'\-]*$/i;
  obj_elem = eval('window.document.' + forma + '.' + campo);
  var bandera = true;
    if (!patron.test(obj_elem.value) ){
      alert ("El campo " + nombre +" debe contener solo letras.");
      obj_elem.focus();
      bandera =  false;
    }
    if ( obj_elem.value == "" || obj_elem.value == "0"){
	alert("El campo " + nombre +" es Dato Requerido");
	obj_elem.focus();
	bandera =  false;
	}
  return bandera;
}

//Funcion que valida que en el campo select haya sido seleccionada
//alguna opcion
//forma: nombre de la forma en el VM 
//Campo: nombre del componente en el VM
//nombre: nombre del campo en el VM
function validaSelect(forma, campo, nombre){      
  	obj_elem = eval('window.document.'+forma+'.'+campo);  
    if (obj_elem==null || obj_elem.value==''){
    	alert ("Se debe seleccionar una opcion para " + nombre);      
    	return false;
    }
  	return true;
}

//Funcion que valida que un campo link solo contenga letras y 
//opcionalmente comas (,)
//forma: nombre de la forma en el VM 
//Campo: nombre del componente en el VM
//nombre: nombre del campo en el VM
function validaLink(forma, campo, nombre){
  var patron = /^[A-Za-z0-9\s????????????_,.\ \'\-]*$/i;
  obj_elem = eval('window.document.' + forma + '.' + campo);
  var bandera = true;      
    if (!patron.test(obj_elem.value) ){
      alert ("El campo " + nombre +" debe contener solo letras.");
      obj_elem.focus();
      bandera =  false;
    }
  return bandera;
}

//Funcion que valida el form al ingesar un munu
//Todos los campos son obligatorios es decir que no pueden
// ser enviados parametros vacios.
function validaFrmAdmonMenu(forma){	
	var txtLiga = eval("window.document."+forma+".txtLiga");
	var patron = /^[A-Za-z0-9\s????????????_,.\ \'\-]*$/i;
	var cboPermSel = eval('window.document.'+forma+'.cboPermisosmenu'+'.selectedIndex');  
	var cboGrupoSel = eval('window.document.'+forma+'.cboGruposmenu'+'.selectedIndex');  		
	if((cboPermSel)==0){
		alert ("Se debe seleccionar una opcion para Permisos");      
		return false;
	}else if((cboGrupoSel)==0){
		alert ("Se debe seleccionar una opcion para Grupos");      
		return false;
	}else if(txtLiga.value==""){
		alert ("Se debe de ingresar una Liga");
		return false;
	}else if(!patron.test(txtLiga.value)){
		alert ("Se debe de ingresar una Liga v?lida");
		return false;
	}else{
		return true;
	}
}

//Funcion que valida que no vengan vacios los campos
// que son enviados en la forma de Usuarios
function validaUsuarios(forma){	
	var grupo = eval("window.document."+forma+".cboGrupo");
	var usuario = eval("window.document."+forma+".txtNombreUsuario");
	var nombre = eval("window.document."+forma+".txtNombre");
	var paterno = eval("window.document."+forma+".txtApellido");
	var sel = eval("window.document."+forma+".chkSelTodos");

	if(grupo.value == "" && trim(usuario.value) == "" && trim(nombre.value) == "" && trim(paterno.value) == "" && !sel.checked){
		alert("Se debe de especificar un parametro de b?squeda");
		return false;
	}else{
		return true;
	}
}

//Funcion que valida el form al ingesar un nuevo usuario
//Todos los campos son obligatorios es decir que no pueden
// ser enviados parametros vacios.
//El Nombre y el apellido paterno no pueden contener numeros
//La direccion de correo electr?nico deber? de ser v?lida
//xx@xxx.aa 
// El id de usuario yu password si pueden contener numeros
function validaFormaUsuarios(forma){	
	var usuario = eval("window.document."+forma+".username");
	var password = eval("window.document."+forma+".password");	
	var confPassword = eval("window.document."+forma+".txtCofirmaContra");	
	var nombre = eval("window.document."+forma+".firstname");
	var paterno = eval("window.document."+forma+".lastname");
	var email = eval("window.document."+forma+".email");
	var patronEmail=/^[A-Za-z0-9][\w-.]+@[A-Za-z0-9]([\w-.]+[A-Za-z0-9]\.)+([A-Za-z]){2,4}$/i;
	var patron = /^[A-Za-z\s????????????_\ \'\-]*$/i;	
    var flag=validapass();
	//alert(flag);
	
	if(usuario.value == "" || password.value == "" || nombre.value == "" || paterno.value == "" || email.value == ""){
		alert("Todos los campos son obligatorios");
		return false;
	}else if(!patronEmail.test(email.value)){
		alert("Correo electronico inv?lido");
		return false;
	}else if(!patron.test(nombre.value)){
		alert("El campo Nombre solo debe de contener letras");
		return false;
	}else if(!patron.test(paterno.value)){
		alert("El campo Apellido solo debe de contener letras");
		return false;
	}else if(!(password.value == confPassword.value)){
		alert("No son iguales las contrase?as");
		return false;
	}else if(flag == false){
		return false
	}
	else{		
		return true;
	}
}


//Funcion que valida que no vengan vacios los campos
//que son enviados en la forma de Permisos, Grupos y Roles
function validaSeg(forma,strCampo,chkCampo){
	var campo = eval("window.document."+forma+"."+strCampo);
	var sel = eval("window.document."+forma+"."+chkCampo);
	if(campo.value == "" && !sel.checked){
		alert("Se debe de especificar un parametro de b?squeda");
		return false;
	}else{
		return true;
	}
}

// valida la entrada de dos contrase?as iguales
function ContrasIguales(){
var ContraseniaUno = window.document.frmCamContras.txtNuevaContras.value;
//alert("La contrase?a uno es :"+ContraseniaUno);
var ContraseniaDos = window.document.frmCamContras.txtConfContras.value;
var flag=validapassCam();
//alert(flag);
//alert("La contrase?a dos es :"+ContraseniaDos);
	if ( ContraseniaUno != 0){
		  
		  if(flag == false)
          { 
          	window.document.frmCamContras.txtNuevaContras.value = "";
			window.document.frmCamContras.txtConfContras.value = "";
          	return false;
          }
          else{
				if ( ContraseniaUno == ContraseniaDos ){
					//alert("Son Iguales");
					return true;
			    }
			    else{
			    	alert("No son iguales las contrase?as");
			    	window.document.frmCamContras.txtNuevaContras.value = "";
			    	window.document.frmCamContras.txtConfContras.value = "";
			    	return false;  
			    }
          }
	      
    }
    else{
    	alert("Ingresa la nueva contrase?a");
    	return false;
    }
    
     
}

// valida la entrada del password (A�n no esta terminada)

