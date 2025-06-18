	var strLigaCotizacion = "";

		function cancelarFactura() {
	        var frmPantalla = window.document.frmDatosOrdenFundacion;
			var motivo = "";            
			if (confirm("Estas seguro de cancelar la factura " + frmPantalla.txtOrden.value + "?")) {
			motivo = prompt("Cual es el motivo de la cancelacion?", "");
				if ((motivo != "") && (motivo != null)) {
					DatosExamen.cancelarFactura(frmPantalla.txtAdmision.value,
				                                                       motivo,
				                                  frmPantalla.idUsuario.value,
				                               actualizarOrdenExamen_CallBack);
				} else {
					alert("Se requiere un motivo de la cancelacion, no puedo proceder con la cancelacion!!!!");
				}
			} 
		} 

		 function actualizarOrdenExamen() {		
	            var frmPantalla = window.document.frmDatosOrdenFundacion;
				hdnExamenesCotizados = frmPantalla.hdnExamenesCotizados;	
				hdnVolumenesExamenesCotizados = frmPantalla.hdnVolumenesExamenesCotizados;	
			    intSucursal = frmPantalla.IdSucursalActual.value;
				hdnkPaciente = frmPantalla.hdnkPaciente;		
				txtEntregaResultadoA = frmPantalla.txtEntregaResultadoA;		
			    if(trim(frmPantalla.txtFechaEntrega.value) == "") {
		    		alert("Es necesario que asigne una fecha de entrega.");
		    		return false;
				} else if (trim(hdnExamenesCotizados.value) == "0" || trim(hdnExamenesCotizados.value) == "") {
		    		alert("Necesita ingresar por lo menos un examen.");
		    		return false;		    		
				} else if (trim(frmPantalla.txtFechaNacimiento.value) == "01-ENE-1900" || trim(frmPantalla.txtFechaNacimiento.value) == "") {
		    		alert("Es necesario que asigne una fecha de nacimiento correcto.");
		    		return false;		    		
				} else if (trim(frmPantalla.txtEdadDescompuesta.value) == "113-8-5" || trim(frmPantalla.txtEdadDescompuesta.value) == "") {
		    		alert("Es necesario que asigne una fecha de nacimiento correcto.");
		    		return false;		    		
				} else if (trim(frmPantalla.txtCalle.value) == "SIN DIRECCION" || trim(frmPantalla.txtCalle.value) == "") {
		    		alert("Es necesario que asigne una direccion correcta.");
		    		return false;		    		
				} else if (trim(txtEntregaResultadoA.value) == "") {
		    		alert("Es necesario registrar el nombre de la persona a quien se le entrega el resultado..");
		    		return false;		    		
				} else if (frmPantalla.hdnkMedico.value == 0) {
		    		alert("Es necesario poner un medico registrado.");
		    		return false;		    							
				} else if (frmPantalla.hdnkPaciente.value == 0) {
		    		alert("No puedes crear una orden a un expediente menor a un millon.");
		    		return false;		    											
				} else if (((TypeObjeto(frmPantalla.selConvenios) == 309) || (TypeObjeto(frmPantalla.selConvenios) == 310) || (TypeObjeto(frmPantalla.selConvenios) == 311) || (TypeObjeto(frmPantalla.selConvenios) == 312)) && (frmPantalla.txtExpedienteMetro.value == '')) {
		    		alert("Es necesario poner el expediente del Metro.");
		    		frmPantalla.txtExpedienteMetro.value = "";
					document.getElementById('txtExpedienteMetro').className = 'text';
					frmPantalla.txtExpedienteMetro.disabled = false;			
		    		return false;		    							
				} else if (((TypeObjeto(frmPantalla.selConvenios) == 309) || (TypeObjeto(frmPantalla.selConvenios) == 310) || (TypeObjeto(frmPantalla.selConvenios) == 311) || (TypeObjeto(frmPantalla.selConvenios) == 312)) && (frmPantalla.txtPaseMetro.value == '')) {
		    		alert("Es necesario poner el n&uacute;mero de pase del Metro.");
		    		frmPantalla.txtPaseMetro.value = "";
					document.getElementById('txtPaseMetro').className = 'text';
					frmPantalla.txtPaseMetro.disabled = false;			
		    		return false;		    							
				} else if (((TypeObjeto(frmPantalla.selConvenios) == 341) || (TypeObjeto(frmPantalla.selConvenios) == 446) || (TypeObjeto(frmPantalla.selConvenios) == 475) || (TypeObjeto(frmPantalla.selConvenios) == 350) || (TypeObjeto(frmPantalla.selConvenios) == 518) || (TypeObjeto(frmPantalla.selConvenios) == 502) || (TypeObjeto(frmPantalla.selConvenios) == 503) || (TypeObjeto(frmPantalla.selConvenios) == 501) || (TypeObjeto(frmPantalla.selConvenios) == 499) || (TypeObjeto(frmPantalla.selConvenios) == 500)) && (frmPantalla.txtElegibilidadVitaMedica.value == '')) {
		    		alert("Es necesario poner el elegibilidad del VitaMedica.");
		    		frmPantalla.txtElegibilidadVitaMedica.value = "";
					document.getElementById('txtElegibilidadVitaMedica').className = 'text';
					frmPantalla.txtElegibilidadVitaMedica.disabled = false;			
		    		return false;		    							
				} else { 
					if (frmPantalla.hdnkMedico.value != 0 && frmPantalla.hdnkMedico.value != "") {
						var strNombreMedico = (frmPantalla.txtApellidoPaternoMedico.value + " " + frmPantalla.txtApellidoMaternoMedico.value + " " + frmPantalla.txtNombreMedico.value);
//						alert(hdnVolumenesExamenesCotizados.value);
						var Cordensolicitada = 0;
						if (TypeObjeto(frmPantalla.radAQuienCorresponda[0]) == true) {
							Cordensolicitada = 0;
						} else if (TypeObjeto(frmPantalla.radAQuienCorresponda[1]) == true) {
							Cordensolicitada = 1;
						} else if (TypeObjeto(frmPantalla.radAQuienCorresponda[2]) == true) {
							Cordensolicitada = 2;
						} else if (TypeObjeto(frmPantalla.radAQuienCorresponda[3]) == true) {
							Cordensolicitada = 3;
						}		
						var Bautorizacionverresultadosmedico = 0;
						if (TypeObjeto(frmPantalla.radCorreoElectronicoMedico[0]) == true) {
							Bautorizacionverresultadosmedico = 0;
						} else if (TypeObjeto(frmPantalla.radCorreoElectronicoMedico[1]) == true) {
							Bautorizacionverresultadosmedico = 1;
						}						
//						document.getElementById('idGuardarNuevaOrden').disabled=true;
//						document.getElementById('idGuardarNuevaCotizacion').disabled=true;
		        		DatosExamen.altaOrdenExamenes(hdnExamenesCotizados.value,
		        									  hdnVolumenesExamenesCotizados.value,
		        									  intSucursal,
		        									  hdnkPaciente.value,
		        									  frmPantalla.idUsuario.value,
		        									  frmPantalla.hdnkMedico.value,
		        									  strNombreMedico,
		        									  TypeObjeto(frmPantalla.selConvenios),
		        									  frmPantalla.txtFechaEntrega.value,
		        									  frmPantalla.txtObservaciones.value,
		        									  Cordensolicitada,
		        									  Bautorizacionverresultadosmedico,
		        									  frmPantalla.hdlCotizacionUtilizado.value,
		        									  trim(txtEntregaResultadoA.value),
		        									  TypeObjeto(frmPantalla.selSucursales),
		        									  actualizarOrdenExamenTicket_CallBack);
					} else {
						alert("Es necesario agregar un m&eacute;dico a la orden");
					}
		    	}	 	
		 }	

		 function changeVolumen(intExamen,selVolumen) {
	         var frmPantalla = window.document.frmDatosOrdenFundacion;
	         var strOld = frmPantalla.hdnVolumenesExamenesCotizados.value;
	         frmPantalla.hdnVolumenesExamenesCotizados.value = (intExamen +":" + TypeObjeto(selVolumen) + "," + strOld);
     		 DatosExamen.newExamen("0",frmPantalla.hdnVolumenesExamenesCotizados.value,frmPantalla.hdnExamenesCotizados.value,intSucursal,TypeObjeto(frmPantalla.selConvenios),RefreshGrid_CallBack);
		 }
		 
		 function guardarCotizacion(strligaReporte) {		
	            var frmPantalla = window.document.frmDatosOrdenFundacion;
				hdnExamenesCotizados = frmPantalla.hdnExamenesCotizados;	
			    intSucursal = frmPantalla.IdSucursalActual.value;
				hdnkPaciente = frmPantalla.hdnkPaciente;		
			    if(trim(frmPantalla.txtFechaEntrega.value) == "") {
		    		alert("Es necesario que asigne una fecha de entrega.");
		    		return false;
				} else if (trim(hdnExamenesCotizados.value) == "0" || trim(hdnExamenesCotizados.value) == "") {
		    		alert("Necesita ingresar por lo menos un examen.");
		    		return false;		    		
				} else if (frmPantalla.hdnkMedico.value == 0) {
		    		alert("Es necesario poner un medico registrado.");
		    		return false;		    							
//				} else if (frmPantalla.txtExpediente.value == '') {
//		    		alert("Es necesario poner el expediente del Metro.");
//		    		return false;		    							
				} else { 
					if (frmPantalla.hdnkMedico.value != 0 && frmPantalla.hdnkMedico.value != "") {
						strLigaCotizacion = strligaReporte;
						var strNombreMedico = (frmPantalla.txtApellidoPaternoMedico.value + " " + frmPantalla.txtApellidoMaternoMedico.value + " " + frmPantalla.txtNombreMedico.value);
						CotizacionOrdenes.guardarCotizacion(hdnExamenesCotizados.value,frmPantalla.hdnVolumenesExamenesCotizados.value,intSucursal,hdnkPaciente.value,frmPantalla.idUsuario.value,frmPantalla.hdnkMedico.value,strNombreMedico,TypeObjeto(frmPantalla.selConvenios),frmPantalla.txtObservaciones.value,guardarCotizacion_CallBack);
//		        		window.document.frmDatosOrdenFundacion.idGuardarNuevaOrden.disabled=true;		
					} else {
						alert("Es necesario agregar un m&eacute;dico a la orden");
					}
		    	}	 	
		 }	

		 function guardarCotizacion_CallBack(data) {
     		repCotizacion(strLigaCotizacion,'Factura',data.kadmision);
		 }
		 
		 
		 function aplicarCotizar(kCotizacion,strExamenes,cConvenio) {
            var frmPantalla = window.document.frmDatosOrdenFundacion;
			frmPantalla.hdnExamenesCotizados.value = strExamenes;	
			frmPantalla.txtBuscarConvenio.value = cConvenio;	
			frmPantalla.hdlCotizacionUtilizado.value = kCotizacion;
			frmPantalla.txtExamenesACotizar.value = 0;
			buscarConvenioRapido(frmPantalla.selConvenios,frmPantalla.txtBuscarConvenio.value);
			consultaExamenesGrid();
			var strProductos = frmPantalla.txtExamenesACotizar.value;	
			frmPantalla.txtExamenesACotizar.value = "";		
		    intSucursal = frmPantalla.IdSucursalActual.value;
//			if (frmPantalla.idGuardarNuevaOrden.disabled==false) {							
//        		DatosExamen.newExamen(strProductos,frmPantalla.hdnVolumenesExamenesCotizados.value,frmPantalla.hdnExamenesCotizados.value,intSucursal,TypeObjeto(frmPantalla.selConvenios),RefreshGrid_CallBack);
//        	} 
		 }
		 

		 function TypeBusqueda() {
	        var frmPantalla = window.document.frmDatosOrdenFundacion;
			if (TypeObjeto(frmPantalla.radTipo[0])) { 
				buscarOrden();
			} else {
				buscarCotizacion();
			}	 
		 }
		 
		 function buscarCotizacion() {
	            var frmPantalla = window.document.frmDatosOrdenFundacion;
	            kCotizacion = prompt("Cual es el n&uacute;mero de Cotizaci&oacute;n?", "");
				if ((kCotizacion != "") && (kCotizacion != null)) {
					CotizacionOrdenes.expedientePacienteCotizacion(kCotizacion,buscarCotizacion_CallBack);
		    	} else {
		    		alert("Se requiere un numero de Cotizaci&oacute;n!!!!");
		    	}
		 }
		 
		 function buscarCotizacion_CallBack(data) {
			 if (data > 0) {
		 		window.document.frmDatosOrdenFundacion.txtCodigoPaciente.value = data;
		 		buscarPaciente();
			 }
		 }
		 
		 function buscarOrden(){
		 	limpiaPantalla();
            var frmPantalla = window.document.frmDatosOrdenFundacion;
			var motivo = "";            
			motivo = prompt("Cual es la orden a buscar?", "");
			if ((motivo != "") && (motivo != null)) {
	    		DatosOrden.getkPaciente(frmPantalla.IdSucursalActual.value,motivo,getkPaciente_CallBack);
	    	} else {
	    		alert("Se requiere un numero de orden, no puedo proceder con la busqueda!!!!");
	    	}
		 }				  
			 
		 function getkPaciente_CallBack(data) {
		 	DatosPaciente.buscarPaciente(data,buscarPaciente_CallBack);		
		 }
		 
		 function visualizaDiagnostico(kOrdenFundacion){
		 	viewDiag('$strLigaDiagnostico','DiagnosticoFactura',kOrdenFundacion);
		 }		

		 
		 
		 function guardarDatosAdicionales_CallBack(data) {
			 
		 }
		 
		 function actualizarOrdenExamen_CallBack(data)
	     {     
//			if (data.sobservacion == "NO CANCELADO") {
//				alert("NO SE PUEDE CANCELAR LA ORDEN YA ESTA FACTURADA");
//			} 
	     	if (data.cestado == 20) {
				alert("Esta orden ya fue cerrada, no se pueden ingresar mas examenes");	     	
	     	} else {
	            var frmPantalla = window.document.frmDatosOrdenFundacion;
	            if (data.cestado == 17) {
					document.getElementById('txtAdmision').style.color="red";			            
					document.getElementById('txtOrden').style.color="red";			            
					document.getElementById('txtFechaEntrega').style.color="red";			            
					document.getElementById('txtTotalPagar').style.color="red";			            
					document.getElementById('txtAdeudo').style.color="red";			            
					document.getElementById('txtPago').style.color="red";			            
					document.getElementById('txtAcuenta').style.color="red";			            
//			        adminDIV("cotizaExamenesSeccion","hidden","none");
	 		        adminDIV("cotizaExamenesSeccion","visible","inline");
					frmPantalla.chkEntregaResultados.disabled = true;						        
					frmPantalla.chkEntregaResultados.checked = 0;
	            } else {
	 		        adminDIV("cotizaExamenesSeccion","visible","inline");
					document.getElementById('txtAdmision').style.color="black";			            
					document.getElementById('txtOrden').style.color="black";			            
					document.getElementById('txtFechaEntrega').style.color="black";			            
					document.getElementById('txtTotalPagar').style.color="black";			            
					document.getElementById('txtAdeudo').style.color="black";			            
					document.getElementById('txtPago').style.color="black";			            
					document.getElementById('txtAcuenta').style.color="black";			            
					if (data.madeuda > 0) {
						frmPantalla.chkEntregaResultados.disabled = true;	
						frmPantalla.chkEntregaResultados.checked = 0;	                
	            	} else {
						frmPantalla.chkEntregaResultados.disabled = false;						
						frmPantalla.chkEntregaResultados.checked = 0;
					}
	            }
				frmPantalla.txtAdmision.value 		= data.kadmision;
				frmPantalla.txtOrden.value 			= data.sordencompleta;		
				frmPantalla.txtFechaEntrega.value 	= data.sdentregaresultado;
				frmPantalla.txtSubTotal.value 		= data.msubtotal;				
				frmPantalla.txtDescuentoPaciente.value = data.mdescuentopaciente;
				frmPantalla.txtDescuentoEmpresa.value = data.mdescuentoempresa;
				frmPantalla.txtFacturarEmpresa.value = data.mfacturaempresa; 
				frmPantalla.txtTotalPagar.value 	= data.mpagapaciente;
				frmPantalla.txtAdeudo.value 		= data.madeuda;
				frmPantalla.hdnAdeudo.value 		= data.madeuda;
				frmPantalla.txtAcuenta.value 		= data.macuenta;			
		   		frmPantalla.hdnkMedico.value 		= data.kmedico;
		   		frmPantalla.txtCodigoMedico.value 	= data.cmedico;		
		   		frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;		
		   		frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
		   		frmPantalla.txtNombreMedico.value 		  = data.snombre;		
		   		frmPantalla.txtObservaciones.value  	  = data.sobservacion;
		   		
		   		if (data.cordensolicitada == 0) {
					frmPantalla.radAQuienCorresponda[0].checked=true;		   			
				    adminDIV("RegistroMedico","visible","inline");					
		   		} else if (data.cordensolicitada == 1) {
					frmPantalla.radAQuienCorresponda[1].checked=true;
				    adminDIV("RegistroMedico","visible","inline");					
		   		} else if (data.cordensolicitada == 2) {
					frmPantalla.radAQuienCorresponda[2].checked=true;
				    adminDIV("RegistroMedico","hidden","none");					
		   		} else if (data.cordensolicitada == 3) {
					frmPantalla.radAQuienCorresponda[3].checked=true;
				    adminDIV("RegistroMedico","hidden","none");					
		   		}
		   		frmPantalla.radAQuienCorresponda[0].disabled = true;
		   		frmPantalla.radAQuienCorresponda[1].disabled = true;
		   		frmPantalla.radAQuienCorresponda[2].disabled = true;
		   		frmPantalla.radAQuienCorresponda[3].disabled = true;
		   		
		   		if (data.bautorizacionverresultadosmedico == 0) {
					frmPantalla.radCorreoElectronicoMedico[0].checked=true;
		   		} else if (data.bautorizacionverresultadosmedico == 1) {
					frmPantalla.radCorreoElectronicoMedico[1].checked=true;
		   		}

		   		valorCombo(frmPantalla.selConvenios,data.cconvenio);
				frmPantalla.txtPago.value           = 0; 
				frmPantalla.idGuardarNuevoMedico.disabled = true;	
				frmPantalla.idLimpiarMedico.value = "Cambiar Medico";	
				frmPantalla.idLimpiarMedico.disabled = true;	
				activaDemograficosMedico(false);
			    adminDIV("pagoOrden","visible","inline");
			    adminDIV("RegistraUsuario","visible","inline");	
			    adminDIV("RegistraPago","hidden","none");		    	    
//				frmPantalla.idGuardarNuevaOrden.disabled=true;	
				if (data.madeuda <= 0) {
				    adminDIV("RegistraUsuario","hidden","none");	
				    adminDIV("RegistraPago","hidden","none");		    	    			
				}
				persistentesExamenes();
			}
		 }	 	     

		 function actualizarOrdenExamen2_CallBack(data)
	     {     
	     	if (data.cestado == 20) {
				alert("Esta orden ya fue cerrada, no se pueden ingresar mas examenes");	     	
	     	} else {
	            var frmPantalla = window.document.frmDatosOrdenFundacion;
	            if (data.cestado == 17) {
					document.getElementById('txtAdmision').style.color="red";			            
					document.getElementById('txtOrden').style.color="red";			            
					document.getElementById('txtFechaEntrega').style.color="red";			            
					document.getElementById('txtTotalPagar').style.color="red";			            
					document.getElementById('txtAdeudo').style.color="red";			            
					document.getElementById('txtPago').style.color="red";			            
					document.getElementById('txtAcuenta').style.color="red";			            
			        adminDIV("cotizaExamenesSeccion","hidden","none");
					frmPantalla.chkEntregaResultados.disabled = true;						        
					frmPantalla.chkEntregaResultados.checked = 0;
	            } else {
	 		        adminDIV("cotizaExamenesSeccion","visible","inline");
					document.getElementById('txtAdmision').style.color="black";			            
					document.getElementById('txtOrden').style.color="black";			            
					document.getElementById('txtFechaEntrega').style.color="black";			            
					document.getElementById('txtTotalPagar').style.color="black";			            
					document.getElementById('txtAdeudo').style.color="black";			            
					document.getElementById('txtPago').style.color="black";			            
					document.getElementById('txtAcuenta').style.color="black";			            
					if (data.madeuda > 0) {
						frmPantalla.chkEntregaResultados.disabled = true;	
						frmPantalla.chkEntregaResultados.checked = 0;	                
	            	} else {
						frmPantalla.chkEntregaResultados.disabled = false;						
						frmPantalla.chkEntregaResultados.checked = 0;
					}
	            }
				frmPantalla.txtAdmision.value 		= data.kadmision;
				frmPantalla.txtOrden.value 			= data.sordencompleta;		
				frmPantalla.txtFechaEntrega.value 	= data.sdentregaresultado;
				frmPantalla.txtSubTotal.value 		= data.msubtotal;				
				frmPantalla.txtDescuentoPaciente.value = data.mdescuentopaciente;
				frmPantalla.txtDescuentoEmpresa.value = data.mdescuentoempresa;
				frmPantalla.txtFacturarEmpresa.value = data.mfacturaempresa; 
				frmPantalla.txtTotalPagar.value 	= data.mpagapaciente;
				frmPantalla.txtAdeudo.value 		= data.madeuda;
				frmPantalla.hdnAdeudo.value 		= data.madeuda;
				frmPantalla.txtAcuenta.value 		= data.macuenta;			
				frmPantalla.txtPago.value           = 0; 
				frmPantalla.idGuardarNuevoMedico.disabled = true;	
				frmPantalla.idLimpiarMedico.value = "Cambiar Medico";	
				activaDemograficosMedico(false);
			    adminDIV("pagoOrden","visible","inline");
			    adminDIV("RegistraUsuario","visible","inline");	
			    adminDIV("RegistraPago","hidden","none");		    	    
//				frmPantalla.idGuardarNuevaOrden.disabled=true;	
				if (data.madeuda <= 0) {
				    adminDIV("RegistraUsuario","hidden","none");	
				    adminDIV("RegistraPago","hidden","none");		    	    			
				}
				imprimirEtiquetas();
				persistentesExamenes();
//        		DatosExamen.guardarDatosAdicionales(frmPantalla.txtAdmision.value,frmPantalla.txtExpediente.value,guardarDatosAdicionales_CallBack);
			}
		 }	 	     
		 
		 function actualizarOrdenExamenTicket_CallBack(data)
	     {     
	     	if (data.cestado == 20) {
				alert("Esta orden ya fue cerrada, no se pueden ingresar mas examenes");	     	
	     	} else {
	            var frmPantalla = window.document.frmDatosOrdenFundacion;
	            if (data.cestado == 17) {
					document.getElementById('txtAdmision').style.color="red";			            
					document.getElementById('txtOrden').style.color="red";			            
					document.getElementById('txtFechaEntrega').style.color="red";			            
					document.getElementById('txtTotalPagar').style.color="red";			            
					document.getElementById('txtAdeudo').style.color="red";			            
					document.getElementById('txtPago').style.color="red";			            
					document.getElementById('txtAcuenta').style.color="red";			            
			        adminDIV("cotizaExamenesSeccion","hidden","none");
					frmPantalla.chkEntregaResultados.disabled = true;						        
					frmPantalla.chkEntregaResultados.checked = 0;
	            } else {
	 		        adminDIV("cotizaExamenesSeccion","visible","inline");
					document.getElementById('txtAdmision').style.color="black";			            
					document.getElementById('txtOrden').style.color="black";			            
					document.getElementById('txtFechaEntrega').style.color="black";			            
					document.getElementById('txtTotalPagar').style.color="black";			            
					document.getElementById('txtAdeudo').style.color="black";			            
					document.getElementById('txtPago').style.color="black";			            
					document.getElementById('txtAcuenta').style.color="black";			            
					if (data.madeuda > 0) {
						frmPantalla.chkEntregaResultados.disabled = true;	
						frmPantalla.chkEntregaResultados.checked = 0;	                
	            	} else {
						frmPantalla.chkEntregaResultados.disabled = false;						
						frmPantalla.chkEntregaResultados.checked = 0;
					}
	            }
				frmPantalla.txtAdmision.value 		= data.kadmision;
				frmPantalla.txtOrden.value 			= data.sordencompleta;		
				frmPantalla.txtFechaEntrega.value 	= data.sdentregaresultado;
				frmPantalla.txtSubTotal.value 		= data.msubtotal;				
				frmPantalla.txtDescuentoPaciente.value = data.mdescuentopaciente;
				frmPantalla.txtDescuentoEmpresa.value = data.mdescuentoempresa;
				frmPantalla.txtFacturarEmpresa.value = data.mfacturaempresa; 
				frmPantalla.txtTotalPagar.value 	= data.mpagapaciente;
				frmPantalla.txtAdeudo.value 		= data.madeuda;
				frmPantalla.hdnAdeudo.value 		= data.madeuda;
				frmPantalla.txtAcuenta.value 		= data.macuenta;			
				frmPantalla.txtPago.value           = 0; 
				frmPantalla.idGuardarNuevoMedico.disabled = true;	
				frmPantalla.idLimpiarMedico.value = "Cambiar Medico";	
				activaDemograficosMedico(false);
			    adminDIV("pagoOrden","visible","inline");
			    adminDIV("RegistraUsuario","visible","inline");	
			    adminDIV("RegistraPago","hidden","none");		    	    
//				frmPantalla.idGuardarNuevaOrden.disabled=true;	
				if (data.madeuda <= 0) {
				    adminDIV("RegistraUsuario","hidden","none");	
				    adminDIV("RegistraPago","hidden","none");		    	    			
				}
				imprimirEtiquetas();
				persistentesExamenes();
				//Versi&oacute;n 29 Marzo 2013 Warning BY
//				if(data.csucursal == 10) {
				//Versi&oacute;n 22 Mayo 2013 Warning OR
				if((data.csucursal == 10) || (data.csucursal == 12)) {
					atendidoPaciente();	
				}		        						
//        		DatosExamen.guardarDatosAdicionales(frmPantalla.txtAdmision.value,frmPantalla.txtExpediente.value,guardarDatosAdicionales_CallBack);
			}
		 }	 	     
		 
		function mostrarFactura(kAdmision) {
		 	codeDIVHTML("gridbusquedaDireccion","");
		    adminDIV("gridbusquedaDireccion","hidden","none");
			document.getElementById('txtPago').className = 'text';
			window.document.frmDatosOrdenFundacion.txtPago.disabled = false;	    
//			window.document.frmDatosOrdenFundacion.idGuardarNuevaOrden.disabled=false;		
    		DatosOrden.getOrden(kAdmision,actualizarOrdenExamen_CallBack);
		}
		
		function consultaOrdenesGrid_CallBack(data) {		 
			    adminDIV("gridbusquedaPacientes","visible","inline");
			 	codeDIVHTML("gridbusquedaPacientes",data);		 
		}
		
		
	     