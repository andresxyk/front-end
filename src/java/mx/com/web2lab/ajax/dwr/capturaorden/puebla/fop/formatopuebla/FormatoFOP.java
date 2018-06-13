package mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.formatopuebla;


import mx.com.web2lab.backend.beans.puebla.CuestionarioPantallaInterpretacionBean;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.util.Formatos;
import mx.com.web2lab.util.CalculaFechas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FormatoFOP {
	
	private static Log iObjLog = LogFactory.getLog(FormatoFOP.class);
	
	public String crearFormato(CuestionarioPantallaInterpretacionBean objCuestionarioPantallaInterpretacionBean) throws Exception{
		
        String strFileFop="",strFileEncabezadoFop="",strFileCuerpoFop="",strFileFinalFop="";        		
        CalculaFechas objCalculaFechas = new CalculaFechas();
        Formatos objFormatos = new Formatos();
        String sHttpPath =  ConfiguracionProperties.getPropiedad("reporte.ruta.imagenes"); 
        String strNemonicoFolio = "";
		try
        {
			iObjLog.debug("Entrando FormatoFOP.crearFormato:..."+objCuestionarioPantallaInterpretacionBean.getKcuestionariopacienteinterpretacionpuebla());
	        int csucursal =objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getCsucursal();
	        if(csucursal==17){
	        	strNemonicoFolio=" - C4";
	        }
	        else if(csucursal==18){
	        	strNemonicoFolio=" - C1";
	        }
	        else if(csucursal==19){
	        	strNemonicoFolio=" - C6";
	        }
	        else if(csucursal==20){
	        	strNemonicoFolio=" - C3";
	        }
	        else if(csucursal==21){
	        	strNemonicoFolio=" - C2";
	        }
	        else if(csucursal==23){
	        	strNemonicoFolio=" - C5";
	        }
			
		  
		    String sJuridiccion = objCuestionarioPantallaInterpretacionBean.getsjuridiccion();
			strFileEncabezadoFop ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>																																				\n"+
									"<fo:root xmlns:fo=\"http://www.w3.org/1999/XSL/Format\">																																	\n"+
									"	<fo:layout-master-set>																																									\n"+
									"		<fo:simple-page-master master-name=\"hello\" page-height=\"11in\"  page-width=\"8.5in\" margin-top=\"1in\" margin-bottom=\"1in\" margin-left=\"1in\" margin-right=\"1in\">			\n"+
									"			<fo:region-body margin-top=\"1in\" margin-bottom=\".5in\"/>																														\n"+
									"			<fo:region-before extent=\".5in\" margin-bottom=\".5in\"/>																														\n"+
									"		</fo:simple-page-master>																																							\n"+
									"	</fo:layout-master-set>																																									\n"+
									"<fo:page-sequence  master-reference=\"hello\">																																				\n"+
									" <fo:static-content flow-name=\"xsl-region-before\">																																		\n"+
									//ENCABEZADO
									"      <fo:block-container height='2cm' width='2cm' top='-2.6cm' left='-2cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='70pt' height='70pt' content-width='70pt' content-height='70pt' overflow='hidden' src = '" + sHttpPath + "LogoMexicoFederal.jpg' />		\n"+  
				                    "                    </fo:block>																																							\n"+
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container height='10cm' width='20.9cm' top='-2.3cm' left='-0cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +    
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='600pt' height='200pt' content-width='400pt' content-height='300pt' overflow='hidden' src = '" + sHttpPath + "Encabezado.jpg' />			\n"+  
				                    "                    </fo:block>																																							\n"+
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container height='10cm' width='70cm' top='-2.3cm' left='14cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='80pt' height='25pt' content-width='70pt' content-height='30pt' overflow='hidden' src = '" + sHttpPath + "LogoGobiernoFederal.jpg' />	\n"+  
				                    "                    </fo:block>																																							\n"+
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container height='10cm' width='70cm' top='-1.4cm' left='14cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='44cm' height='45pt' content-width='69pt' content-height='70pt' overflow='hidden' src = '" + sHttpPath + "Salud3.jpg' />					\n"+  
				                    "                    </fo:block>																																							\n"+
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container height='10cm' width='70cm' top='-2.3cm' left='16.1cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='30cm' height='140pt' content-width='56pt' content-height='100pt' overflow='hidden' src = '" + sHttpPath + "EscudoNacional2.jpg' />		\n"+  
				                    "                    </fo:block>																																							\n"+
				                    "      </fo:block-container>																																								\n"+
				                    " </fo:static-content>																																										\n"+
				                    " <fo:flow flow-name=\"xsl-region-body\">																																					\n"+
				                    //IDENTIFICACION DE LA UNIDAD
									"      <fo:block-container background-color='#A4A4A4'  height='0.45cm' width='9cm' top='-2.5cm' left='-1.7cm' position='absolute'>															\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"fixedsys\"  font-weight=\"bold\">																									\n"+
									"				I.IDENTIFICACION DE LA UNIDAD																																				\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-1.6cm' left='-1.7cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"7pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				1.INSTITUCI&#211;N																																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-1.6cm' left='0.4cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCuestionarioPantallaInterpretacionBean.getSinstitucion()+"																												\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='-1.38cm' left='0.2cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='220pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container width='9cm' top='-1.6cm' left='8.1cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"7pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				2.ENTIDAD																																									\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-1.6cm' left='9.5cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCuestionarioPantallaInterpretacionBean.getSentidad()+"																													\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='-1.3cm' left='9.3cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='235pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container width='9cm' top='-1.10cm' left='-1.7cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"8pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				3.CLUES																																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-1.15cm' left='-0.4cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCuestionarioPantallaInterpretacionBean.getSclues()+"																														\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='-0.88cm' left='-0.4cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='200pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container width='9cm' top='-1.10cm' left='6.8cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"7pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				4.JURISDICCI&#211;N																																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-1.10cm' left='9cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													sJuridiccion+"																																								\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='-0.88cm' left='8.94cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='240pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container width='9cm' top='-0.6cm' left='-1.7cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"8pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				5.MUNICIPIO																																									\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-0.6cm' left='0.3cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCuestionarioPantallaInterpretacionBean.getSmunicipio()+"																													\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='-0.38cm' left='0cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='255pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container width='9cm' top='-0.6cm' left='9cm' position='absolute'>																											\n"+
									"			<fo:block font-size=\"7pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				6.UNIDAD M&#201;DICA																																						\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='-0.6cm' left='11.6cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">"+" \n"+
									  				objCuestionarioPantallaInterpretacionBean.getSunidadmedica()+ strNemonicoFolio +"																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='-0.38cm' left='11.3cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='180pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                  //IDENTIFICACION DEL PACIENTE
									"      <fo:block-container background-color='#A4A4A4'  height='0.45cm' width='9cm' top='0.2cm' left='-1.7cm' position='absolute'>															\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"fixedsys\"  font-weight=\"bold\">																									\n"+
									"				II.IDENTIFICACION DE LA PACIENTE																																			\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='1cm' left='-1.7cm' position='absolute'>																											\n"+
									"			<fo:block font-size=\"7pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				7.CLAVE INSTITUCIONAL																																						\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='0.88cm' left='1.6cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCuestionarioPantallaInterpretacionBean.getSclaveinstitucional()+"																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='1.22cm' left='1.5cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='220pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container width='9cm' top='1cm' left='9.5cm' position='absolute'>																											\n"+
									"			<fo:block font-size=\"8pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				8.CURP																																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='1cm' left='10.8cm' position='absolute'>																											\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCuestionarioPantallaInterpretacionBean.getScurp()+"																														\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='1.22cm' left='10.5cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='200pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container  height='5cm' width='20.4cm' top='1.72cm' left='-1.7cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">																																\n"+
									"				9.NOMBRE: 																																									\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='1.71cm' left='0cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getSappaterno()+"																			\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='1.94cm' left='-0.54cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='430pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container  height='5cm' width='20.4cm' top='1.72cm' left='6cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getSapmaterno()+"																			\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='1.72cm' left='10cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getSnombre()+"																				\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
				                    "      <fo:block-container  height='5cm' width='20.4cm' top='2.13cm' left='0cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				APELLIDO PATERNO 																																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='2.13cm' left='6cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				APELLIDO MATERNO 																																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='2.13cm' left='10cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				NOMBRE 																																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='1.72cm' left='14.8cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"8pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				10.EDAD																																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='1.72cm' left='16.3cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objCalculaFechas.getEdadAnios(objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getDnacimiento())+" A&#209;OS									\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='1.94cm' left='16cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='45pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container  height='5cm' width='20.4cm' top='2.63cm' left='-1.7cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">																																\n"+
									"				11.ENTIDAD DE NACIMIENTO 																																					\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='2.63cm' left='2cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getSlugarnacimiento() +"\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='2.85cm' left='1.89cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='200pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container width='9cm' top='2.63cm' left='9.5cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"7pt\"  font-family=\"Times New Roman\"  font-weight=\"bold\">																								\n"+
									"				12.FECHA DE NACIMIENTO																																						\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container width='9cm' top='2.63cm' left='13cm' position='absolute'>																										\n"+
									"			<fo:block font-size=\"9pt\"  font-family=\"Times New Roman\">																													\n"+
													objFormatos.getFechaNumeros(objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getDnacimiento())+"												\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='15cm' top='2.85cm' left='12.9cm' position='absolute'>																						\n"+  
				                    "                    <fo:block>																																								\n"+  
				                    "                       <fo:external-graphic width='140pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+     
				                    "      <fo:block-container  height='5cm' width='20.4cm' top='3.35cm' left='-1.7cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">																																\n"+
									"				13.DIRECCI&#211;N 																																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.33cm' left='0.1cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getSdireccion()+"																			\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='3.57cm' left='0cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='200pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
				                    "      <fo:block-container  height='5cm' width='20.4cm' top='3.76cm' left='2.5cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				CALLE 																																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='3.57cm' left='7.2cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='100pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.76cm' left='8.5cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				N&#218;MERO 																																								\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.33cm' left='8cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													" 																																											\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='3.57cm' left='10.9cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='130pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.76cm' left='12.5cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				COLONIA 																																									\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.33cm' left='11.3cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getScolonia()+"																				\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='3.57cm' left='15.5cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='70pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.76cm' left='17cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				C.P 																																										\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.33cm' left='15.7cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													Formatos.mascaraNumerico(objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getScodigopostal(),4) +"											\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='4.09cm' left='-1.7cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='130pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='4.28cm' left='0cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				TEL&#201;FONO 																																								\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.9cm' left='-1.7cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getStelefono()+"																				\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='4.09cm' left='2.5cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='300pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='4.28cm' left='5.9cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				LOCALIDAD/MUNICIPIO 																																						\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.9cm' left='2.5cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getSdelegmuni()+"																			\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container height='3cm' width='3cm' top='4.09cm' left='12.5cm' position='absolute'>																							\n"+  
				                    "                    <fo:block>  																																							\n"+  
				                    "                       <fo:external-graphic width='150pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />				\n"+  
				                    "                    </fo:block>																																							\n"+  
				                    "      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='4.28cm' left='13.8cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">																																\n"+
									"				ENTIDAD FEDERATIVA 																																							\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='3.9cm' left='12.8cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"9pt\">																																					\n"+
													objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getBpacientebean().getSciudad() +"																				\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='4.83cm' left='-1.7cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">																																\n"+
									"				14.DERECHOHABIENCIA 																																						\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.45cm' width='0.75cm' top='4.73cm' left='1.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\" text-align='center' font-weight=\"bold\">"+" \n"+
													objCuestionarioPantallaInterpretacionBean.getUderechohabiencia()+"																											\n"+
									"			</fo:block>																																										\n"+
									"      </fo:block-container>																																								\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='4.83cm' left='2.5cm' position='absolute'>																						\n"+
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				1. SEGURO POPULAR &#160;&#160;&#160;2.IMSS&#160;&#160;&#160;3.ISSTE&#160;&#160;&#160;4.PEMEX&#160;&#160;&#160;5.SEDENA&#160;&#160;&#160;6.SEDEMAR&#160;&#160;&#160;7.IMSS OPORTUNIDADES&#160;&#160;&#160;8.NINGUNA&#160;&#160;&#160;9.OTRO"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n";
									 //ANTECEDENTES
				   strFileCuerpoFop="      <fo:block-container background-color='#A4A4A4'  height='0.45cm' width='9cm' top='5.60cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\"  font-family=\"fixedsys\"  font-weight=\"bold\">"+" \n"+
									"				III.ANTECEDENTES"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='6.45cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				15.DE MASTOGRAF&#205;A "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.45cm' width='0.75cm' top='6.45cm' left='1.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\" text-align='center'  font-weight=\"bold\">"+" \n"+
											objCuestionarioPantallaInterpretacionBean.getUantescedentedemastografia()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='6.45cm' left='2.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				1. S&#237; &#160;&#160;&#160;2.No"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='6.45cm' left='4.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				16.FECHA DE &#218;LTIMA MASTOGRAF&#205;A "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
												objCuestionarioPantallaInterpretacionBean.resultFOPValidaFecha(objCuestionarioPantallaInterpretacionBean.getDfechaultimamastografia(), 	objFormatos, (new String[] {"left='9.3cm'"	,"left='10.83cm'"	,"left='12.34cm'"}), "top='6.45cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='7cm' left='9.9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				D&#237;a "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='7cm' left='11.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				Mes "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='7cm' left='13cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				A&#241;o "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
									"      <fo:block-container  height='5cm' width='20.4cm' top='6.45cm' left='14cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				17.RESULTADO BIRADS "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
												objCuestionarioPantallaInterpretacionBean.resultFOPValidaSIvsNO(objCuestionarioPantallaInterpretacionBean.getUresultadobiradsmastografia(), 		"top='6.45cm' 	left='17cm'") +
									 //MASTOGRAFIA
									"      <fo:block-container background-color='#A4A4A4'  height='0.45cm' width='9cm' top='7.4cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\"  font-family=\"fixedsys\"  font-weight=\"bold\">"+" \n"+
									"				IV.MASTOGRAF&#205;A"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='8.23cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				18.MODALIDAD DE MASTOGRAF&#205;A "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='8.23cm' left='5.9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				TAMIZAJE "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaTamizajeDiagnostica(objCuestionarioPantallaInterpretacionBean.getUmodalidadmastografiatamizaje(), 	"top='8.23cm' 	left='7.5cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='8.23cm' left='11.59cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				DIAGN&#211;STICA "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaTamizajeDiagnostica(objCuestionarioPantallaInterpretacionBean.getUmodalidadmastografiadiagnostica(), 	"top='8.23cm' 	left='13.7cm'") +
									"      <fo:block-container height='3cm' width='10cm' top='8.9cm' left='5.9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				11.-Invitaci&#243;n organizada "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm'  top='9.3cm' left='5.9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				12.-Derivada por personal de salud "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='9.7cm' left='5.9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				13.-Espont&#225;nea(De la mujer) "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='8.9cm' left='11.59cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				21.-Por sintomatolog&#237;a cl&#237;nica "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='9.3cm' left='11.59cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				22.-Control patolo&#237;a benigna "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='9.7cm' left='11.59cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				23.-Control patolo&#237;a maligna "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='10.4cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				19.FECHA DE TOMA DE LA MASTOGRAF&#205;A "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaFecha(objCuestionarioPantallaInterpretacionBean.getObjOrdenBean().getDregistro(), 		objFormatos, (new String[] {"left='3.9cm'"	,"left='5.4cm'"	,"left='6.9cm'"}),  "top='10.4cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='10.9cm' left='4.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				D&#237;a "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='10.9cm' left='6cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				Mes "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='10.9cm' left='7.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				A&#241;o "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='10.4cm' left='8.6cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				20.MASTOGRAF&#205;A T&#201;CNICAMENTE ADECUADA "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
												objCuestionarioPantallaInterpretacionBean.resultFOPValidaSIvsNO(objCuestionarioPantallaInterpretacionBean.getUmastografiaadecuada(), 		"top='10.4cm' left='14.35cm'") + 
									"      <fo:block-container  height='5cm' width='20.4cm' top='10.4cm' left='15.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				1. S&#237; &#160;&#160;&#160;2.No"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='11.6cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				1.IMAGEN INCOMPLETA DE LA MAMA"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolimagenincompleta(), 				"top='11.6cm' 	left='3cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='11.6cm' left='3.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				2.BAJO CONTRASTE"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolbajocontraste(), 					"top='11.6cm' 	left='6.1cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='11.6cm' left='6.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				3.BAJA RESOLUCI&#211;N"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolbajaresolucion(), 				"top='11.6cm' 	left='9.4cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='11.6cm' left='10cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				4.ARTEFACTOS"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolartefactos(), 					"top='11.6cm' 	left='12cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='11.6cm' left='12.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				5.MAL POSICIONAMIENTO"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolmalposcicionamiento(), 			"top='11.6cm' 	left='15.7cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='11.6cm' left='16.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				6.OTROS"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolotros(), 							"top='11.6cm' 	left='17.4cm'") +
									 //LINEA
									"      <fo:block-container background-color='#A4A4A4'  height='0.25cm' width='20cm' top='12.4cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\"  font-family=\"fixedsys\"  font-weight=\"bold\">"+" \n"+
									"				"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='13cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				21.FECHA DE LA INTERPRETACI&#211;N DE LA MASTOGRAF&#205;A "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaFecha(objCuestionarioPantallaInterpretacionBean.getDfechainterpretacionmastografia(), 	objFormatos, (new String[] {"left='5.5cm'"	,"left='7cm'"	,"left='8.5cm'"}),  "top='13cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='13.5cm' left='6cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				D&#237;a "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='13.5cm' left='7.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				Mes "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='13.5cm' left='9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				A&#241;o "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='13cm' left='10.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				22.RESULTADO Y HALLAZGOS DE LA MASTOGRAF&#205;A "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container height='3cm' width='10cm' top='14cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				HALLAZGO "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container height='3cm' width='10cm' top='14cm' left='1cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				DER "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container height='3cm' width='10cm' top='14cm' left='2.4cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				IZQ. "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container height='3cm' width='10cm' top='14cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				23.BIRADS "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.44cm' width='1.5cm' top='14cm' left='7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				LECTOR "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.44cm' width='2.6cm' top='14cm' left='9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				RESULTADOS "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.44cm' width='4cm' top='14cm' left='12.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				RFC "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container height='3cm' width='10cm' top='14.4cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				1.Tumor "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBoltumorderecho(), " height='0.46cm' ",	" top='14.3cm' 	left='1cm'") +
									"	   <fo:block-container height='3cm' width='10cm' top='15cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				2.Asimetr&#237;a "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBolasimetriaderecho(), " height='0.46cm' "," top='14.795cm' 	left='1cm'") +
									"	   <fo:block-container height='3cm' width='10cm' top='15.53cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				3.Deformidad "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBoldeformidadderecho(),  " height='0.50cm' "	," top='15.3cm' 	left='1cm'") +
									"	   <fo:block-container height='3cm' width='10cm' top='16cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				4.Calcificaci&#243;n "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBolcalcificacionderecho(),  " height='0.46cm' "	," top='15.8cm' 	left='1cm'") +
									"	   <fo:block-container height='3cm' width='3cm' top='16.5cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				5.Densidad asim&#233;trica "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBoldensidadasimetricaderecho(), " height='0.48cm' ",	" top='16.32cm' 	left='1cm'") +
									"	   <fo:block-container height='3cm' width='3cm' top='17cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				6.Otros "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBolotrosderecho(), " height='0.46cm' ", " top='16.82cm' 	left='1cm'") +
									"	   <fo:block-container height='3cm' width='3cm' top='17.68cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				7.Observaciones: "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='17.68cm' left='0.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\">"+" \n"+
											objCuestionarioPantallaInterpretacionBean.getSobservaciones()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
									"      <fo:block-container height='3cm' width='3cm' top='17.98cm' left='0.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +  
			                        "                    <fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       \n" +  
			                        "                       <fo:external-graphic width='310pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />                                      \n" +  
			                        "                    </fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     \n" +  
			                        "      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
			                        "      <fo:block-container height='3cm' width='3cm' top='18.55cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +  
			                        "                    <fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       \n" +  
			                        "                       <fo:external-graphic width='370pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />                                      \n" +  
			                        "                    </fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     \n" +  
			                        "      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
			                        "      <fo:block-container height='3cm' width='10cm' top='16cm' left='11cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
			                        "                    <fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       \n" +  
			                        "                       <fo:external-graphic width='70pt' height='70pt' content-width='160pt' content-height='160pt'  src = '" + sHttpPath + "mamas2.jpg' />                                      \n" +  
			                        "                    </fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     \n" +
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBoltumorizquierdo(), 	" height='0.46cm' ",	" top='14.3cm' 	left='2.2cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBolasimetriaizquierdo()," height='0.46cm' ", " top='14.795cm' 	left='2.2cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBoldeformidadizquierdo()," height='0.50cm' ", " top='15.3cm' 	left='2.2cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBolcalcificacionizquierdo()," height='0.46cm' ", " top='15.8cm' 	left='2.2cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBoldensidadasimetricaizquierdo()," height='0.48cm' ", " top='16.32cm' left='2.2cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBooleanHallazgo(objCuestionarioPantallaInterpretacionBean.isBolotrosizquierdo(), " height='0.46cm' "," top='16.82cm' 	left='2.2cm'") +
									"      <fo:block-container height='3cm' width='10cm' top='14.4cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				[0]Estudio no concluyente "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='15cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				[1]Mama normal "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='15.53cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				[2]Hallazgos benignos "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='16cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				[3]Probablemente benignos "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='16.5cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				[4]Probablemente maligno "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container height='3cm' width='10cm' top='17cm' left='3.3cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				[5]Maligno "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									//LECTOR
									"      <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.38cm' width='1.5cm' top='14.52cm' left='7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				Uno "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.38cm' width='1.5cm' top='14.98cm' left='7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				Dos "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.38cm' width='1.5cm' top='15.48cm' left='7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				Decisi&#243;n "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBirads(objCuestionarioPantallaInterpretacionBean.getUresultadobiradsuno(), " height='0.38cm' width='2.6cm'  top='14.52cm' 	left='9cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBirads(objCuestionarioPantallaInterpretacionBean.getUresultadobiradsdos(), " height='0.38cm' width='2.6cm'  top='14.98cm' 	left='9cm'") +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBirads(objCuestionarioPantallaInterpretacionBean.getUresultadobiradstres()," height='0.38cm' width='2.6cm'  top='15.48cm' 	left='9cm'") +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.38cm' width='4cm' top='14.52cm' left='12.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
											objCuestionarioPantallaInterpretacionBean.getSrfcresultadobiradsuno()+"\n"+
									"			</fo:block>"+"\n"+
									"      	</fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.38cm' width='4cm' top='14.98cm' left='12.5cm'  position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
											objCuestionarioPantallaInterpretacionBean.getSrfcresultadobiradsdos()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container border-color='black' border-style='solid' border-width='0.65pt' height='0.38cm' width='4cm' top='15.48cm' left='12.5cm'  position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
											objCuestionarioPantallaInterpretacionBean.getSrfcresultadobiradstres()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='19.02cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				24.FECHA DE INFORME DE RESULTADOS A LA MUJER "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaFecha(objCuestionarioPantallaInterpretacionBean.getDfechainformeresultado(), objFormatos, (new String[] {"left='5.51cm'"	,"left='7.01cm'"	,"left='8.51cm'"}),  "top='19.02cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='19.55cm' left='5.99cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				D&#237;a "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='19.55cm' left='7.66cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				Mes "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='19.55cm' left='8.78cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				A&#241;o "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" ;
									//CONDUCTA A SEGUIR
				   strFileFinalFop ="      <fo:block-container background-color='#A4A4A4'  height='0.45cm' width='9cm' top='19.77cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\"  font-family=\"fixedsys\"  font-weight=\"bold\">"+" \n"+
									"				V.CONDUCTA A SEGUIR"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.88cm' top='20.55cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				25.REPETIR POR ESTUDIO INADECUADO "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaNumeric(objCuestionarioPantallaInterpretacionBean.isBolrepeticionestudio(), 				"top='20.46cm' 	left='3.5cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='20.55cm' left='9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				26.PR&#211;XIMA DETECCI&#211;N POR MASTOGRAF&#205;A EN 2 A&#209;OS "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolproximadeteccion(), 				"top='20.46cm' 	left='16.15cm'") +
									"      <fo:block-container  height='5cm' width='20.88cm' top='20.88cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				27.REFERENCIA A GINECOLOG&#205;A PARA TRATAMIENTO DE PATOLOG&#205;A BENIGNA "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>\n"+
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaBoolean(objCuestionarioPantallaInterpretacionBean.isBolreferenciaginecologia(), 			"top='20.88cm' 	left='8.2cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='20.88cm' left='9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				28.REFERENCIA A EVALUACI&#211;N DIAGN&#211;STICA A UNIDAD:"+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container  height='0.35cm' width='0.55cm' top='21cm' left='16.15cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"                    <fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       \n" +  
			                        "                       <fo:external-graphic width='40pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />                                      \n" +  
			                        "                    </fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     \n" +  
			                        "      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
			                        "      <fo:block-container  height='5cm' width='21.21cm' top='21.21cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				29.FECHA DE REFERENCIA "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
											objCuestionarioPantallaInterpretacionBean.resultFOPValidaFecha(objCuestionarioPantallaInterpretacionBean.getDfechareferencia(), objFormatos, (new String[] {"left='2cm'"	,"left='3.56cm'"	,"left='5.1cm'"}),  "top='21.21cm'") +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.76cm' left='2.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				D&#237;a "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.75cm' left='3.8cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				Mes "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.75cm' left='5.6cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"5pt\"  font-weight=\"bold\">"+" \n"+
									"				A&#241;o "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.21cm' left='9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\">"+" \n"+
													objCuestionarioPantallaInterpretacionBean.getSreferenciaevaluacion()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.98cm' left='-1.7cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				30.NOMBRE DEL RADI&#211;LOGO "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.98cm' left='2cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\">"+" \n"+
													objCuestionarioPantallaInterpretacionBean.getSnombreradiologo().toUpperCase()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container  height='0.35cm' width='0.55cm' top='22.22cm' left='2cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"            <fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       \n" +  
							        "                <fo:external-graphic width='190pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />                                      \n" +  
							        "             </fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     \n" +  
							        "      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
							        "      <fo:block-container  height='5cm' width='20.4cm' top='21.98cm' left='9cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				RFC "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"      <fo:block-container  height='5cm' width='20.4cm' top='21.98cm' left='9.63cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"9pt\">"+" \n"+
													objCuestionarioPantallaInterpretacionBean.getSrfcradiologo()+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
									"	   <fo:block-container  height='0.35cm' width='0.55cm' top='22.23cm' left='9.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"                    <fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       \n" +  
							        "                       <fo:external-graphic width='110pt' height='100pt' content-width='800pt' content-height='150pt' overflow='hidden' src = '" + sHttpPath + "Line2.jpg' />                                      \n" +  
							        "                    </fo:block>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     \n" +  
							        "      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
							        "      <fo:block-container  height='5cm' width='20.4cm' top='21.98cm' left='13.5cm' position='absolute'>                                                                                                                                                                                                                                                                                                \n" +
									"			<fo:block font-size=\"7pt\"  font-weight=\"bold\">"+" \n"+
									"				FIRMA "+"\n"+
									"			</fo:block>"+"\n"+
									"      </fo:block-container>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \n" +
												objCuestionarioPantallaInterpretacionBean.resultFirma(objCuestionarioPantallaInterpretacionBean.getSrfcradiologo(), sHttpPath)+"\n"+
									" </fo:flow>"+"\n"+
									"</fo:page-sequence>"+"\n"+
									"</fo:root>";							
				   strFileFop=strFileEncabezadoFop+strFileCuerpoFop+strFileFinalFop;
        } catch(Exception aErrorException) {
	 		iObjLog.error("FileFOP::run:Exception: ", aErrorException);
	 		throw aErrorException;
        } 
        return strFileFop;
    }
}
