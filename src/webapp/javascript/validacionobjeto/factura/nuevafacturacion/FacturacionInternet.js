var facturaelectronicaBean = new FacturaElectronicaBean();


		function init() {
		    var frmPantalla = window.document.frmConsultaOrden;
		    DWRUtil.useLoadingMessage();
		    adminDIV("cotizaExamenes","hidden","none");
		    adminDIV("pagoOrden","hidden","none");
		    adminDIV("RegistraUsuario","hidden","none");
		    adminDIV("RegistraPago","hidden","none");
		    adminDIV("gridbusquedaDireccion","hidden","none");	
		    adminDIV("cotizaExamenesSeccion","visible","inline");	    
		    adminDIV("Medico","hidden","none");	
		    adminDIV("buscarExamenesACotizar","hidden","none");			
		}
		


		function mostrarFactura(kAdmision) {
			var frmPantalla = window.document.frmConsultaOrden;
			DatosOrden.getOrden(kAdmision,actualizarOrdenExamen_CallBack);
		}
				 
		function actualizarOrdenExamen_CallBack(data) {     
			var frmPantalla = window.document.frmConsultaOrden;
			document.getElementById('txtTotalPagar').style.color="black";			            
			document.getElementById('txtAdeudo').style.color="black";			            
			document.getElementById('txtAcuenta').style.color="black";			            
			frmPantalla.txtSubTotal.value 			= data.msubtotal;				
			frmPantalla.txtDescuentoPaciente.value 	= data.mdescuentopaciente;
			frmPantalla.txtDescuentoEmpresa.value 	= data.mdescuentoempresa;
			frmPantalla.txtFacturarEmpresa.value 	= data.mfacturaempresa; 
			frmPantalla.txtTotalPagar.value 		= data.mpagapaciente;
			frmPantalla.txtAdeudo.value 			= data.madeuda;
			frmPantalla.hdnAdeudo.value 			= data.madeuda;
			frmPantalla.txtAcuenta.value 			= data.macuenta;
			frmPantalla.IdUnidadActual.value 		= data.csucursal;			
			adminDIV("pagoOrden","visible","inline");
			adminDIV("RegistraUsuario","visible","inline");	
			adminDIV("DireccionFiscal","hidden","none");		    	    
			adminDIV("RegistraUsuario","hidden","none");	
			adminDIV("RegistraPago","hidden","none");		    	    
			if (data.strFactura != "") {
				alert("Esta Orden ya est� facturada con el numero " + data.strFactura);
				adminDIV("BusquedaFiscal","hidden","none");	
				codeDIVHTML("gridbusquedaDatosFiscales","");
				adminDIV("gridbusquedaDatosFiscales","hidden","none");	 
			} else {
				adminDIV("BusquedaFiscal","visible","inline");	
				codeDIVHTML("gridbusquedaDatosFiscales","");
				adminDIV("gridbusquedaDatosFiscales","hidden","none");	 
			}
		}	 	     
		 
		function reimprimirFactura(sfactura) {
			alert("Esta Orden ya est� facturada con el numero " + sfactura);
			return false;
		}
		 
		 function guardarFactura() {
            var frmPantalla = window.document.frmConsultaOrden;
            if ((frmPantalla.hdnkDatoFiscal.value >0) && (frmPantalla.hdnkDatoFiscal.value != "")) {
    			frmPantalla.idGuardarFactura.disabled=true;	
    			frmPantalla.idLimpiarDatosFiscales.disabled=true;
	            LoadFacturaElectronica();
				if (trim(frmPantalla.txtCorreoElectronico.value) != "") {
					if (confirm("�Quieres enviar la factura al correo electr�nico del paciente, el cual es " + trim(frmPantalla.txtCorreoElectronico.value) + " ?")) {
						if (frmPantalla.txtCorreoElectronico.value.length > 3) {
							OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,frmPantalla.txtCorreoElectronico.value,generarFactura_CallBack);
						} else {
							alert('Es necesario ingresar el e-mail del paciente');
							frmPantalla.idGuardarFactura.disabled=false;		
						}
					} else {
						if (confirm("�Quieres enviar la factura a otro correo electr�nico?")) {
							alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener un @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
							emaila = prompt("�Cual es la direccion del correo electronico?", "borrasoloesto@borrasoloesto.com.mx");
							OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,emaila,generarFactura_CallBack);
						} else {
							OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false," ",generarFactura_CallBack);
						}
					}
				} else {
					if (confirm("No existe correo electronico del Paciente, �Quieres enviar la factura a otro correo electr�nico?")) {
						alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener una sola @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
						emailb = prompt("�Cual es la direccion del correo electronico?", "borrasoloesto@borrasoloesto.com.mx");
						OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,emailb,generarFactura_CallBack);
					} else {
						OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false," ",generarFactura_CallBack);
					}					
				}
            } else {
            	alert('Existe un error en la seleccion de los Datos Demograficos, busquelos de nuevo por favor.');
            	cleanDatosFiscales();
            }
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
						if (confirm("�Quieres RE-FACTURAR la orden?")) {
							frmPantalla.idGuardarFactura.disabled=true;								
							if (trim(frmPantalla.txtCorreoElectronico.value) != "") {
								if (confirm("�Quieres enviar la factura al correo electr�nico del paciente, el cual es " + trim(frmPantalla.txtCorreoElectronico.value) + " ?")) {
									if (frmPantalla.txtCorreoElectronico.value.length > 3) {
										OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true,frmPantalla.txtCorreoElectronico.value,generarFactura_CallBack);
									} else {
										alert('Es necesario ingresar el e-mail del paciente');
										frmPantalla.idGuardarFactura.disabled=false;		
									}
								} else {
									if (confirm("�Quieres enviar la factura a otro correo electr�nico?")) {
										alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener un @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
										emaila = prompt("�Cual es la direccion del correo electronico?", "borrasoloesto@borrasoloesto.com.mx");
										OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true,emaila,generarFactura_CallBack);
									} else {
										OrdenFacturarAjax.crearOrdenFacturar(facturaelectronicaBean,frmPantalla.txtAdmision.value,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,true," ",generarFactura_CallBack);
									}
								}
							} else {
								if (confirm("No existe correo electronico del Paciente, �Quieres enviar la factura a otro correo electr�nico?")) {
									alert('Recuerda la estructura de un correo electronico: \n En tu Explorer debe estar habilitado la opcion de ventana emergente \n No lleva espacios \n Debe contener una sola @ \n Debe tener un dominio ejemplo @olab.com.mx \n Si escribes mal el correo no sale la Factura y tendras que refacturar');
									emailb = prompt("�Cual es la direccion del correo electronico?", "borrasoloesto@borrasoloesto.com.mx");
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
				facturaelectronicaBean.cconvenio=0;
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
				if (document.getElementById('cMarca').value == 1) {
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
				ccliente=null,
				cmarca=null
			}
				     		 
		 		 