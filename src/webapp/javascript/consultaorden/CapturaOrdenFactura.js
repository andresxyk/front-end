var facturaelectronicaBean = new FacturaElectronicaBean();

		function mostrarFactura(kAdmision) {
            var frmPantalla = window.document.frmConsultaOrden;
		 	codeDIVHTML("gridbusquedaDireccion","");
		    adminDIV("gridbusquedaDireccion","hidden","none");
			if (frmPantalla.IdUnidadActual.value != 1003) {
				DatosOrden.getOrden(kAdmision,actualizarOrdenExamen_CallBack);
			} else {
				DatosOrden.getOrdenFac(kAdmision,actualizarOrdenExamen_CallBack);
			}
		}
		
		function consultaOrdenesGrid_CallBack(data) {
			    adminDIV("gridbusquedaPacientes","visible","inline");
			 	codeDIVHTML("gridbusquedaPacientes",data[0]);		 
		}
		
		function ocultarOrdenes() {
			var objDIV = document.getElementById("gridGridOrdenes");
			if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
			    adminDIV("gridGridOrdenes","visible","inline");			
			} else {
			    adminDIV("gridGridOrdenes","hidden","none");			
			}		
		}
		 
		 function actualizarOrdenExamen_CallBack(data)
	     {     
//			alert('Se encontro la orden ' + data.sordencompleta); 
	     	if (data.cestado == 20) {
				alert("Esta orden ya fue cerrada, no se pueden ingresar mas examenes");	     	
	     	} else {
	            var frmPantalla = window.document.frmConsultaOrden;
	            if (data.cestado == 17) {
					document.getElementById('txtAdmision').style.color="red";			            
					document.getElementById('txtOrden').style.color="red";			            
					document.getElementById('txtFechaEntrega').style.color="red";			            
					document.getElementById('txtTotalPagar').style.color="red";			            
					document.getElementById('txtAdeudo').style.color="red";			            
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
		   		frmPantalla.txtConvenio.value 		= data.cconvenio + ' ' + data.sconvenio;				    	
		    	frmPantalla.hdnkMedico.value 		= data.kmedico;		   		
		   		frmPantalla.txtCodigoMedico.value 	= data.cmedico;				   		
		   		frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;		
		   		frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
		   		frmPantalla.txtNombreMedico.value 		  = data.snombre;				   		
				document.getElementById('txtObservaciones').className = 'textflat';
				frmPantalla.txtObservaciones.disabled = true;
		   		frmPantalla.txtObservaciones.value  	  = data.sobservacion;
		   		frmPantalla.txtObservaciones.value  	  = data.sobservacion;
		   		frmPantalla.txtEntregaResultadoA.value    = data.sentregaresultadosa;
			    adminDIV("pagoOrden","visible","inline");
			    adminDIV("RegistraUsuario","visible","inline");	
			    adminDIV("RegistraPago","hidden","none");		    	    
			    adminDIV("DireccionFiscal","hidden","none");		    	    
				if (data.madeuda <= 0) {
				    adminDIV("RegistraUsuario","hidden","none");	
				    adminDIV("RegistraPago","hidden","none");		    	    			
				}
				
				if (frmPantalla.IdUnidadActual.value == 103) {
					frmPantalla.idGenerarFactura.disabled=true;
				    adminDIV("consultaExpedienteEmpresas","visible","inline");	
				    initExpedinteEmpresa();
				} else if (frmPantalla.IdUnidadActual.value != 1003) {
					if (data.csucursal == frmPantalla.IdUnidadActual.value) {
						if ((data.mpagapaciente > 0) && (data.madeuda == 0)) {
							frmPantalla.idGenerarFactura.disabled=false;
							if (data.strFactura != "") {
								reimprimirFactura(data.strFactura);
							} else {
								if (confirm("Esta Orden no est� facturada, �Quieres Facturar la Orden?")) {
									 frmPantalla.idGenerarFactura.disabled=true;		
								     adminDIV("BusquedaFiscal","visible","inline");	
									 adminDIV("DireccionFiscal","hidden","none");
								     codeDIVHTML("gridbusquedaDatosFiscales","");
									 adminDIV("gridbusquedaDatosFiscales","hidden","none");	 
								}
							}
						} else {
							frmPantalla.idGenerarFactura.disabled=true;
						}
		     		} else {
		     			alert("Esta orden no la puedes facturar es de otra sucursal!!!!!!");
						frmPantalla.idGenerarFactura.disabled=true;
		     		}
				} else {
					frmPantalla.idGenerarFactura.disabled=false;
				    adminDIV("gridFacturarEmpresas","visible","inline");								
					if (data.strFactura != "") {
						reimprimirFactura(data.strFactura);
					} 
					frmPantalla.idGenerarFactura.disabled=true;		
				    adminDIV("DireccionFiscal","visible","inline");	
					ToolFacSucursalesAjax.getEstadoFacturarOrden(frmPantalla.txtAdmision.value,getEstadoFacturarOrden_CallBack); 
				    adminDIV("BusquedaFiscal","visible","inline");	
					adminDIV("DireccionFiscal","hidden","none");
				    codeDIVHTML("gridbusquedaDatosFiscales","");
					adminDIV("gridbusquedaDatosFiscales","hidden","none");	 				    
				}
				if (frmPantalla.IdUnidadActual.value != 1003) {
					persistentesExamenes();
				} else {
					persistentesExamenesFac();
				    adminDIV("BusquedaFiscal","visible","inline");	
					adminDIV("DireccionFiscal","hidden","none");
				    codeDIVHTML("gridbusquedaDatosFiscales","");
					adminDIV("gridbusquedaDatosFiscales","hidden","none");	 				    
				}				
			}
		 }	 	     
		 
		 function cancelarExamen(ExamenEliminar) {
	         var frmPantalla = window.document.frmConsultaOrden;
			 ToolFacSucursalesAjax.changeExamenOrdenesFac(frmPantalla.txtAdmision.value,ExamenEliminar.value,-1,CambiarConvenio_CallBack); 
		 }

		 function cancelarPerfil(PerfilEliminar) {
	         var frmPantalla = window.document.frmConsultaOrden;
			 ToolFacSucursalesAjax.changeExamenOrdenesFac(frmPantalla.txtAdmision.value,-1,PerfilEliminar.value,CambiarConvenio_CallBack); 
		 }
		 
		 function getEstadoFacturarOrden_CallBack(data) {
	         var frmPantalla = window.document.frmConsultaOrden;
			 frmPantalla.txtEstadoFacturacion.value = data;
		 }

		 function reimprimirFactura(sfactura) {
			if (confirm("Esta orden ya esta facturada con el numero " + sfactura + ", ¿Quieres RE-IMPRIMIR la factura?")) {
				snombre = "FacturaOrden";
						strRuta = "http://192.237.150.66:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + sfactura + ".pdf";
//				strRuta = "http://localhost/FacturasElectronicas/FacturacionElectronica_" + sfactura + ".pdf";
			    abrirVentanaOrden(strRuta,snombre);	   			     			    
			    return true;
			} else {
				return false;
			}
		 }
		 
//		 OMRR
		 function guardarFactura() {
            var frmPantalla = window.document.frmConsultaOrden;
            if ((frmPantalla.hdnkDatoFiscal.value >0) && (frmPantalla.hdnkDatoFiscal.value != "")) {
    			frmPantalla.idGuardarFactura.disabled=true;	
    			frmPantalla.idLimpiarDatosFiscales.disabled=true;
	            LoadFacturaElectronica();
				if (frmPantalla.IdUnidadActual.value == 1003) {
					if (frmPantalla.txtEstadoFacturacion.value != "LISTAFACTURAR") {
						alert("La orden debe estar Lista para Facturar");
						frmPantalla.idGuardarFactura.disabled=false;		
						return false;
					}
				}				
				if (frmPantalla.IdUnidadActual.value == 1003) {
					OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false," ",generarFactura_CallBack);
				} else {
					if (trim(frmPantalla.txtCorreoElectronico.value) != "") {
						if (confirm("&iquest;Quieres enviar la factura al correo electr&oacute;nico del paciente, el cual es " + trim(frmPantalla.txtCorreoElectronico.value) + " ?")) {
							if (frmPantalla.txtCorreoElectronico.value.length > 3) {
								OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,frmPantalla.txtCorreoElectronico.value,generarFactura_CallBack);
							} else {
								alert('Es necesario ingresar el e-mail del paciente');
								frmPantalla.idGuardarFactura.disabled=false;		
							}
						} else {
							if (confirm("&iquest;Quieres enviar la factura a otro correo electr&oacute;nico?")) {
								alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener un @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
								emaila = prompt("&iquest;Cual es la direccion del correo electr&oacute;nico?", "borrasoloesto@borrasoloesto.com.mx");
								OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,emaila,generarFactura_CallBack);
							} else {
								OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false," ",generarFactura_CallBack);
							}
						}
					} else {
						if (confirm("No existe correo electronico del Paciente, �Quieres enviar la factura a otro correo electr&oacute;nico?")) {
							alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener una sola @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
							emailb = prompt("&iquest;Cual es la direccion del correo electr&oacute;nico?", "borrasoloesto@borrasoloesto.com.mx");
							OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,emailb,generarFactura_CallBack);
						} else {
							OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false," ",generarFactura_CallBack);
						}					
					}
				}
            } else {
            	alert('Existe un error en la seleccion de los Datos Demograficos, busquelos de nuevo por favor.');
            	cleanDatosFiscales();
            }
		 }
		 
		 function ListaFacturar() {
             var frmPantalla = window.document.frmConsultaOrden;
			 ToolFacSucursalesAjax.listaFacturarOrden(frmPantalla.txtAdmision.value,TypeObjeto(frmPantalla.selGrupo),getEstadoFacturarOrden_CallBack); 
		 }
		    
		 function RetenerFacturar() {
             var frmPantalla = window.document.frmConsultaOrden;
			 ToolFacSucursalesAjax.retenerFacturarOrden(frmPantalla.txtAdmision.value,getEstadoFacturarOrden_CallBack); 
		 }

		 function noFacturarOrden() {
             var frmPantalla = window.document.frmConsultaOrden;
			 ToolFacSucursalesAjax.noFacturarOrden(frmPantalla.txtAdmision.value,getEstadoFacturarOrden_CallBack); 
		 }
		 
		 
		 function CambiarConvenio() {
            var frmPantalla = window.document.frmConsultaOrden;
			clavenew = TypeObjeto(frmPantalla.selConvenios); 
			if (confirm("Estas seguro de actualizar la orden con la clave del nuevo convenio " + clavenew + "?")) {
				 ToolFacSucursalesAjax.changeConvenioOrdenesFac(frmPantalla.txtAdmision.value,clavenew,CambiarConvenio_CallBack); 
				 DatosOrden.getOrdenFac(kAdmision,actualizarOrdenExamen_CallBack);
			}    		
		 }
		 
		 function CambiarConvenio_CallBack(data) {
			var frmPantalla = window.document.frmConsultaOrden;
			alert(data);
			DatosOrden.getOrdenFac(frmPantalla.txtAdmision.value,actualizarOrdenExamen_CallBack);
		 }
		 		 
		 function beginFactura() {
			 window.document.frmConsultaOrden.idGenerarFactura.disabled=true;		
		     adminDIV("BusquedaFiscal","visible","inline");	
		 }
		 
		 
		 function generarFactura_CallBack(data) {
             var frmPantalla = window.document.frmConsultaOrden;
			 frmPantalla.idGuardarFactura.disabled=false;		
			 if (data.length > 10) {
			 	 codeDIVHTML("gridbusquedaDatosFiscales","");
			     adminDIV("gridbusquedaDatosFiscales","hidden","none");
				 snombre = "FacturaOrden";
				 abrirVentanaOrden(data,snombre);	   			     
			 } else {
				alert("Verificamos y la orden ya tiene una factura, la cual es " + data); 
				if (!reimprimirFactura(data)) {
					if (frmPantalla.IdUnidadActual.value != 1003) {
						if (confirm("&iquest;Quieres RE-FACTURAR la orden?")) {
							frmPantalla.idGuardarFactura.disabled=true;								
							if (trim(frmPantalla.txtCorreoElectronico.value) != "") {
								if (confirm("&iquest;Quieres enviar la factura al correo electr&oacute;nico del paciente, el cual es " + trim(frmPantalla.txtCorreoElectronico.value) + " ?")) {
									if (frmPantalla.txtCorreoElectronico.value.length > 3) {
										OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true,frmPantalla.txtCorreoElectronico.value,generarFactura_CallBack);
									} else {
										alert('Es necesario ingresar el e-mail del paciente');
										frmPantalla.idGuardarFactura.disabled=false;		
									}
								} else {
									if (confirm("&iquest;Quieres enviar la factura a otro correo electr&oacute;nico?")) {
										alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener un @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
										emaila = prompt("&iquest;Cual es la direccion del correo electronico?", "borrasoloesto@borrasoloesto.com.mx");
										OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true,emaila,generarFactura_CallBack);
									} else {
										OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true," ",generarFactura_CallBack);
									}
								}
							} else {
								if (confirm("No existe correo electronico del Paciente, &iquest;Quieres enviar la factura a otro correo electr&oacute;nico?")) {
									alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener una sola @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
									emailb = prompt("&iquest;Cual es la direccion del correo electronico?", "borrasoloesto@borrasoloesto.com.mx");
									OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true,emailb,generarFactura_CallBack);
								} else {
									OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true," ",generarFactura_CallBack);
								}					
							}
						}
					}
				}
			 }
		 }
		 
		 function LoadFacturaElectronica() {
				facturaelectronicaBean.sserie="A";
				facturaelectronicaBean.sfolio="1";
				facturaelectronicaBean.fecha="2010-12-31 10:55:31";
				facturaelectronicaBean.cconvenio=309;
				facturaelectronicaBean.nnumeroaprobacion="422843";
				facturaelectronicaBean.sanoaprobacion="2010";
				facturaelectronicaBean.sformapago="pago en una sola exhibicion"; 
				facturaelectronicaBean.mtotal= frmPantalla.txtTotalPagar.value;
				facturaelectronicaBean.miva=( frmPantalla.txtTotalPagar.value - (frmPantalla.txtTotalPagar.value / 1.16));
				facturaelectronicaBean.mdescuento=(((frmPantalla.txtDescuentoPaciente.value + frmPantalla.txtDescuentoEmpresa.value))/1.16);
				facturaelectronicaBean.stipocomprobante="ingreso";
				facturaelectronicaBean.sncertificado="00001000000202233501";
//				facturaelectronicaBean.sncertificado="00001000000102101701";
				//variables del emisorOlab	
				if (document.getElementById('idMarca').value == 1) {
					facturaelectronicaBean.srazonsocialemisor="ESTUDIOS CLINICOS DR TJ ORIARD SA DE CV";
					facturaelectronicaBean.srfcemisor="ECD741021QA5";
					facturaelectronicaBean.scalleemisor="AV. REVOLUCION No.56";
					facturaelectronicaBean.snexterioremisor="";
					facturaelectronicaBean.sninterioremisor="";
					facturaelectronicaBean.scoloniaemisor="ESCANDON";
					facturaelectronicaBean.sciudademisor="MEXICO D.F.";
					facturaelectronicaBean.smunicipioemisor="MIGUEL HIDALGO";
					facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
					facturaelectronicaBean.scodigopostalemisor="11800";
					facturaelectronicaBean.spaisemisor="MEXICO";
					facturaelectronicaBean.cmarca = 1;
				} else {
					facturaelectronicaBean.srazonsocialemisor="LABORATORIO QUIMICO CLINICO AZTECA S.A.P.I. DE C.V.";
					facturaelectronicaBean.srfcemisor="LQC920131M20";
					facturaelectronicaBean.scalleemisor="SIMON BOLIVAR 15";
					facturaelectronicaBean.snexterioremisor="";
					facturaelectronicaBean.sninterioremisor="";
					facturaelectronicaBean.scoloniaemisor="LOS REYES ACAQUILPAN CENTRO";
					facturaelectronicaBean.sciudademisor="ESTADO DE MEXICO";
					facturaelectronicaBean.smunicipioemisor="LA PAZ";
					facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
					facturaelectronicaBean.scodigopostalemisor="56400";
					facturaelectronicaBean.spaisemisor="MEXICO";				
					facturaelectronicaBean.cmarca = 4;
				}
				//variables del emisorSucursal
				facturaelectronicaBean.scallesuc="AV. VASCO DE QUIROGA NO.3380 P.B.";
				facturaelectronicaBean.snexteriorsuc="";
				facturaelectronicaBean.sninteriorsuc="";
				facturaelectronicaBean.scoloniasuc="LOMAS DE SANTA FE";
				facturaelectronicaBean.sciudadsuc="MEXICO D.F.";
				facturaelectronicaBean.smunicipiosuc="CUAJIMALPA";
				facturaelectronicaBean.sestadosuc="DISTRITO FEDERAL";
				facturaelectronicaBean.scodigopostalsuc="05300";
				facturaelectronicaBean.spaissuc="MEXICO";				
				//Datos del receptor
				facturaelectronicaBean.hDatosFiscal=frmPantalla.hdnkDatoFiscal.value;
				facturaelectronicaBean.srazonsocialreceptor=frmPantalla.txtRazonSocialFiscal.value;
				facturaelectronicaBean.srfcreceptor=frmPantalla.txtRFC.value;
				facturaelectronicaBean.scallereceptor=frmPantalla.txtCalleFiscal.value;
				facturaelectronicaBean.snexteriorreceptor="";
				facturaelectronicaBean.sninteriorreceptor="";
				facturaelectronicaBean.scoloniareceptor=frmPantalla.txtColoniaFiscal.value;
				facturaelectronicaBean.sciudadreceptor=frmPantalla.txtEstadoFiscal.value;
				facturaelectronicaBean.smunicipioreceptor=frmPantalla.txtDelegacionMunicipioFiscal.value;
				facturaelectronicaBean.sestadoreceptor=frmPantalla.txtEstadoFiscal.value;
				facturaelectronicaBean.scodigopostalreceptor=frmPantalla.txtCodigoPostalFiscal.value;
				facturaelectronicaBean.spaisreceptor="MEXICO";
				//impuesto
				facturaelectronicaBean.sdescripcion="";
				facturaelectronicaBean.ssellodigital="";
				facturaelectronicaBean.scadenaoriginal="";
				facturaelectronicaBean.ccliente=94; 	
			}

			function FacturaElectronicaBean() {
				sserie=null,
				sfolio=null,
				fecha=null,
				cconvenio=null,
				cmarca=null,
				nnumeroaprobacion=null,
				sanoaprobacion=null,
				sformapago=null, 
				msubtotal=null,
				msubtotaluniemp=null,
				mdescuento=null,
				mtotal=null,
				stipocomprobante=null,
				sncertificado=null,
				cert=null,
			    //variables del emisorOlab	
				srazonsocialemisor=null,
				srfcemisor=null,
				scalleemisor=null,
				snexterioremisor=null,
				sninterioremisor=null,
				scoloniaemisor=null,
				sciudademisor=null,
				smunicipioemisor=null,
				sestadoemisor=null,
				scodigopostalemisor=null,
				spaisemisor=null,
				//variables del emisorSucursal
				scallesuc=null,
				snexteriorsuc=null,
				sninteriorsuc=null,
				scoloniasuc=null,
				sciudadsuc=null,
				smunicipiosuc=null,
				sestadosuc=null,
				scodigopostalsuc=null,
				spaissuc=null,
				//Datos del receptor
				hDatosFiscal=null,
				srazonsocialreceptor=null,
				srfcreceptor=null,
				scallereceptor=null,
				snexteriorreceptor=null,
				sninteriorreceptor=null,
				scoloniareceptor=null,
				sciudadreceptor=null,
				smunicipioreceptor=null,
				sestadoreceptor=null,
				scodigopostalreceptor=null,
				spaisreceptor=null,
				//impuesto
				miva=null,
				sdescripcion=null,
				ssellodigital=null,
				lstexamenes=null,
				scadenaoriginal=null,
				ccliente=null 	
			}
				     		 
		 		 