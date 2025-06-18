



/********************* Negocio *******************************/
   
 function mostrarDatoAdicional(kAdmision) {
    DatosAdicionales.getDatoAdicional(kAdmision,actualizarDatoAdicional_CallBack);
 }
 
 function actualizarDatoAdicional_CallBack(data){
	 codeDIVHTML("datosAdicionales",data);
 } 
 
 function limpiarDatos(){
	   var frm=document.getElementById("frmAltaDatoAdicional");
	   
	   for (i=0;i<frm.elements.length;i++)
	   {
		   if(frm.elements[i].type=="text"){
			   if(!(frm.elements[i].name=="00")|| (frm.elements[i].name=="01")){
				   frm.elements[i].value="";   
			   }
		   	}
	   }
 }
 
 function guardaDatos(){
	   var frm=document.getElementById("frmAltaDatoAdicional");
	   var datos="";
	   var bguarda=true;
	   for (i=0;i<frm.elements.length;i++)
	   {   
			if(frm.elements[i].type=="text"){
				if(frm.elements[i].value=="") {
					bguarda=false;
				}else{
					datos+=frm.elements[i].name+","+frm.elements[i].value+";";
					//alert(datos);
					
				}
			}
	   }
	   if(bguarda){
		  DatosAdicionales.persistirDatoAdicional(datos,false,setdatos_CallBack);
	   }else{
		   alert('Deben ingresar todos los datos adicionales para el convenio');
	   }
	
 }
 
 
 function setdatos_CallBack(data) {
	   if(data=="Existente"){
		   if(confirm('¿Desea Actualizar los Datos Existentes?')){
			   var frm=document.getElementById("frmAltaDatoAdicional");
			   var datos="";
			   var bactualiza=true;
			   for (i=0;i<frm.elements.length;i++)
			   {   
					if(frm.elements[i].type=="text"){
						if(frm.elements[i].value=="") {
							bactualiza=false;
						}else{
							datos+=frm.elements[i].name+","+frm.elements[i].value+";";
							//alert(datos);
							
						}
					}
			   }
			   if(bactualiza)
			   {
				   DatosAdicionales.persistirDatoAdicional(datos,true,setactualizacion_CallBack);
			   }
		   }
	   }else {
		   alert('Se ingresaron los datos correctamente');
	   }
 }
 
 function setactualizacion_CallBack(data) {
	   if(data==""){
		   alert('Se actualizaron los datos correctamente');
	   }
}