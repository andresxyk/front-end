/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

/********************* Negocio *******************************/
   
   function generarReporteConvenio()
   {
       	var frmPantalla = window.document.frmReportesExcel;
       	claveConvenio = TypeObjeto(frmPantalla.selConvenios); 
       	claveReporte = TypeObjeto(frmPantalla.selReportes); 
		if (confirm("Estas seguro de generar el reporte del Convenio " + claveConvenio + "?")) {
            ReportesExcel.generarReporte(claveConvenio,claveReporte,generarReporteConvenio_CallBack)
		}    			   
   }
   
   function generarReporteConvenio_CallBack(data)
   {
	   alert(data);
   }
   