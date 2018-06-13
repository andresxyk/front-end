$(document).ready(function(){
	
	 /***********RFC**********************/
	 $("#widgetu7841_input").keypress(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7841_input").val(($("#widgetu7841_input").val()).toUpperCase());
	  });

	  $("#widgetu7841_input").keyup(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7841_input").val(($("#widgetu7841_input").val()).toUpperCase());
	   });
	  /***********RAZON SOCIAL**********************/
	 $("#widgetu7846_input").keypress(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7846_input").val(($("#widgetu7846_input").val()).toUpperCase());
	  });

	  $("#widgetu7846_input").keyup(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7846_input").val(($("#widgetu7846_input").val()).toUpperCase());
	   });
	  /***********DIRECCION**********************/
	 $("#widgetu7987_input").keypress(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7987_input").val(($("#widgetu7987_input").val()).toUpperCase());
	  });

	  $("#widgetu7987_input").keyup(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7987_input").val(($("#widgetu7987_input").val()).toUpperCase());
	   });
	  /***********COLONIA**********************/
	 $("#widgetu7992_input").keypress(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7992_input").val(($("#widgetu7992_input").val()).toUpperCase());
	  });

	  $("#widgetu7992_input").keyup(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7992_input").val(($("#widgetu7992_input").val()).toUpperCase());
	   });
	  /***********DELEGACION O MUNICIPIO**********************/
	 $("#widgetu7997_input").keypress(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7997_input").val(($("#widgetu7997_input").val()).toUpperCase());
	  });

	  $("#widgetu7997_input").keyup(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu7997_input").val(($("#widgetu7997_input").val()).toUpperCase());
	   });
	  /***********ESTADO**********************/
	 $("#widgetu8003_input").keypress(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu8003_input").val(($("#widgetu8003_input").val()).toUpperCase());
	  });

	  $("#widgetu8003_input").keyup(function(e){
			if (e.which >= 97 && e.which <= 122) {
				var newKey = e.which - 32;
				e.keyCode = newKey;
				e.charCode = newKey;
			}
			$("#widgetu8003_input").val(($("#widgetu8003_input").val()).toUpperCase());
	   });
	  /***********CP**********************/
	 $("#widgetu8008_input").keypress(function(e){		 
		 return ( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57)) ? false : true ;
	  });

	  $("#widgetu8008_input").keyup(function(e){
		 return ( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57)) ? false : true ;
	   });	   
 })
 