/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

/********************* Negocio *******************************/
   
   
   function limpiaPantalla() {
	   var frmPantalla = window.document.frmRecalculos;
	   
	   valorCombo(frmPantalla.selConvenios,356);
	   for (i=0; ele = document.getElementById('selBloques').options[i]; i++)
	   ele.selected = false;
   }
   
   function generacionRecalculo() {
	   var frmPantalla = window.document.frmRecalculos;
       var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
       var strbloques = loopSelected();
       
	   	if ((cconvenio>0) && (strbloques !=',')&& (strbloques !='')) {
	   		Facturacion.setRecalculo(cconvenio,strbloques,setrecalculo_CallBack);
	   	} else {
	   		alert('Debe seleccionar al menos un bloque');
	   	} 
	       
   }
   
   function setrecalculo_CallBack(data){
	   alert(data);
	   
   }
   
   function loopSelected()
	{
	  var strElecciones;
	  var strcadena=',';
	  var selObj = document.getElementById('selBloques');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	    if (selObj.options[i].selected) {
	    	if(selObj.options[i].value != 0) {
		    	strcadena += selObj.options[i].value+',';
		    	count++;
	    	} else {
	    		strcadena='';
	    	}
	    }
	  }
	  return strcadena;
	}

   