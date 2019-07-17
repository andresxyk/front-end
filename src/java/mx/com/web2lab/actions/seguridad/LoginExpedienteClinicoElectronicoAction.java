package mx.com.web2lab.actions.seguridad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import mx.com.web2lab.beans.BMenuOptions;
import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.util.GenericDAO;
import mx.com.web2lab.util.SCambiaPass;
import mx.com.web2lab.util.seguridad.BGrupoMenuUtil;
import mx.com.web2lab.backend.beans.ap.PacienteBean;
import mx.com.web2lab.backend.beans.comer.ConvenioBean;
import mx.com.web2lab.backend.dao.ap.PacientesDao;
import mx.com.web2lab.backend.dao.comer.ClientesNewDao;
import mx.com.web2lab.backend.dao.sms.AdministracionSmsDao;
import mx.com.web2lab.backend.util.beans.sistema.TurbineGroup;
import mx.com.web2lab.backend.util.catalogos.ValoresCatalogo;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.util.BasePeer;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.DataBackendException;
import org.apache.turbine.util.security.PasswordMismatchException;
import org.apache.turbine.util.security.TurbineSecurityException;
import org.apache.turbine.util.security.UnknownEntityException;
import org.apache.velocity.context.Context;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

public class LoginExpedienteClinicoElectronicoAction extends VelocityAction implements Serializable{


	private static Log iObjLog = LogFactory.getLog(LoginExpedienteClinicoElectronicoAction.class);

    /**
    * Metodo que indica si el usuario es valido o no a travez
    * de  si el resultado no arroja una escepcion, el usuario 
    * es logeado de lo contrario arrojara una excepcion segun el caso
    */
    public void doPerform(RunData aObjData, Context aObjContext)
        throws Exception{    	
        String nomUser = aObjData.getParameters().getString("username").trim();
        String nomPaciente = aObjData.getParameters().getString("username").trim();
        String passwd = aObjData.getParameters().getString("password").trim();
        String strCamContras = aObjData.getParameters().getString("chkCamContras");
        String strTipSistema = "";
        TurbineGroup objTurbineGroup = null;
        String strEmpresa = "";
        iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Usuario " + nomUser + " password " + passwd + " " + strCamContras);
        try {
        	if (nomUser.trim().length() > 2) {
        		strEmpresa = nomUser.substring(0,3);
        	}
            iObjLog.debug("LoginAction + + + + + + + ECE Empresas " + nomUser + " password " + passwd + " " + strCamContras + " SUbstring" + strEmpresa );
        	if ((strEmpresa.equals("CON")) || (strEmpresa == "CON")) {
                iObjLog.debug("LoginAction + + + + + + + ECE Empresas " + nomUser + " password " + passwd + " " + strCamContras);
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Paciente Usuario " + nomUser + " password " + passwd + " " + strCamContras);
        		ClientesNewDao objConvenioDao = new ClientesNewDao();
        		ConvenioBean objConvenioBean = new ConvenioBean();
        		objConvenioBean.setCconvenio(Integer.decode(nomPaciente.substring(3)));
    			objConvenioBean = (ConvenioBean)objConvenioDao.buscarConvenio(objConvenioBean,"0,1").get(0);
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados DAO son usuario Convenio Usuario " + objConvenioBean.getCconvenio() + " password " + objConvenioBean.getSpassword() + " " + strCamContras);
        		if ((objConvenioBean.getSpassword() == passwd) || (objConvenioBean.getSpassword().equals(passwd))) {
        			nomUser = "eceolab";
        			passwd  = "tjoriard1";
//        			objPacientesDAO.actualizaVisitaPaciente(objPacienteBean);
        		}
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Paciente Validacion Usuario " + nomUser + " password " + passwd + " " + strCamContras);
                objConvenioBean = null;
        		objConvenioDao = null;
	            List objRoles = getRoleByUser(nomUser);
	            iObjLog.debug("LoginAction:ROLES ENCONTRADOS= "+objRoles);
	            //Valida si el usuario esta dado de alta
	            TurbineUser objTurbineUser = getUserByName(nomUser);	
	    		User objUsuario = TurbineSecurity.getAuthenticatedUser(nomUser, passwd);
	    		
	    		if(objUsuario!=null) {
	    			iObjLog.debug("LoginAction.doPerform: EjecutandoSeguridadUtil....");
	    			ACL acl = SeguridadUtil.obtenACL(objTurbineUser.getUserId());
	    			objUsuario.setId(objTurbineUser.getUserId());
	    			aObjData.setUser(objUsuario);
	    			iObjLog.debug("LoginAction.doPerform: el id del usuario es "+objUsuario.getId());
	    			HttpSession objSesion = aObjData.getSession();
	    			objSesion.setAttribute( "username", nomUser );
            		objSesion.setAttribute("cConvenio", nomPaciente.substring(3));
	    			objSesion.setAttribute( "ACL", acl );
	                objUsuario.setHasLoggedIn(new Boolean(true));
	                objUsuario.updateLastLogin();
	                objUsuario.setLastLogin(new Date());
	    			objSesion.setAttribute("strNombreCompleto", objUsuario.getFirstName()+" "+objUsuario.getLastName());
	    			objSesion.setAttribute("IdUsuarioSesion", new Integer(objUsuario.getId()));
	                aObjData.save();
	                String strPath = aObjData.getContextPath().trim()+"/servlet/template/";   
	                List objTurbineGroups = getGroupByUser(objUsuario.getName());
	                if(objTurbineGroups.size() > 0  ){
	                	objTurbineGroup = (TurbineGroup)objTurbineGroups.get(0);
	                	strTipSistema = getSistema( objTurbineGroup );
	                	iObjLog.debug("LoginAction.doPerform El tiposistema que tiene es el siguiente "+strTipSistema);
	                	objSesion.setAttribute("existeUnidad", "true");
	                }
	                if ( strCamContras != null && strCamContras.trim().equals("1") ){
	                	aObjContext.put("username", nomUser);
	                	aObjContext.put("selDepto", "1");
	                	aObjData.setScreenTemplate("/web2lab,seguridad,CamContras.vm");
	                	return;
	                }
	                if(objTurbineGroups != null && !objTurbineGroups.isEmpty() && objTurbineGroups.size() > 1){
	                	iObjLog.debug("LoginAction.doPerform Entro al if de mas de un grupo");
	                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
	                	if ( strCamContras == null || !strCamContras.trim().equals("1") ){
	                		aObjData.setScreenTemplate("/web2lab,seguridad,OpcionDepartamento.vm");
	                	}
	                } else if(objTurbineGroups != null && objTurbineGroups.size() == 1){
	                	String objMenu = generaMenu(strPath, objRoles)+"";
	                	iObjLog.debug("LoginAction.doPerform regreso con el menu");
	                	objSesion.setAttribute("menu"," ");
	                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
	            		String st = this.getCLabDepByGroup(objTurbineGroup.getGroupId()+"");
	            		objSesion.setAttribute("strIdUnidadActual", st);	            	
	            		objSesion.setAttribute("grupo", "EXPEDIENTE CLINICO ELECTRONICO EMPRESA");
	            		objSesion.setAttribute("idgrupo", objTurbineGroup.getGroupId());
	            		objSesion.setAttribute("strIdCLab", getLabPorUnidad(st));
	            		objSesion.setAttribute("strIdCel", getCelPorUnidad(st));
	            		aObjData.setScreenTemplate("/web2lab,ap,BienvenidoECEConvenios.vm");
	                }
	        	}
        	} else if ((strEmpresa.equals("MED")) || (strEmpresa == "MED")) {
	                iObjLog.debug("LoginAction + + + + + + + ECE Medico " + nomUser + " password " + passwd + " " + strCamContras + " SUbstring" + strEmpresa );
	                iObjLog.debug("LoginAction + + + + + + + ECE Medicos " + nomUser + " password " + passwd + " " + strCamContras);
	                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Paciente Usuario " + nomUser + " password " + passwd + " " + strCamContras);
	 //       		PacientesDao objPacientesDAO = new PacientesDao();
	 //       		PacienteBean objPacienteBean = new PacienteBean();
//	        		objPacienteBean.setKpacientefundacion(Integer.decode(nomUser));
	 //       		objPacienteBean = objPacientesDAO.buscarPaciente(objPacienteBean);
	 //               iObjLog.debug("LoginAction + + + + + + + Los datos recuperados DAO son usuario Paciente Usuario " + objPacienteBean.getKpacientefundacion() + " password " + objPacienteBean.getSpasswordexpediente() + " " + strCamContras);
//	        		if ((objPacienteBean.getSpasswordexpediente() == passwd) || (objPacienteBean.getSpasswordexpediente().equals(passwd))) {
	        			nomUser = "eceolab";
	        			passwd  = "tjoriard1";
//	        			objPacientesDAO.actualizaVisitaPaciente(objPacienteBean);
//	       		}
	                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Paciente Validacion Usuario " + nomUser + " password " + passwd + " " + strCamContras);
	 //       		objPacienteBean = null;
	 //       		objPacientesDAO = null;
		            List objRoles = getRoleByUser(nomUser);
		            iObjLog.debug("LoginAction:ROLES ENCONTRADOS= "+objRoles);
		            //Valida si el usuario esta dado de alta
		            TurbineUser objTurbineUser = getUserByName(nomUser);	
		    		User objUsuario = TurbineSecurity.getAuthenticatedUser(nomUser, passwd);
		    		if(objUsuario!=null) {
		    			iObjLog.debug("LoginAction.doPerform: EjecutandoSeguridadUtil....");
		    			ACL acl = SeguridadUtil.obtenACL(objTurbineUser.getUserId());
		    			objUsuario.setId(objTurbineUser.getUserId());
		    			aObjData.setUser(objUsuario);
		    			iObjLog.debug("LoginAction.doPerform: el id del usuario es "+objUsuario.getId());
		    			HttpSession objSesion = aObjData.getSession();
		    			objSesion.setAttribute( "username", nomUser );
	            		objSesion.setAttribute("cClaveMedico", nomPaciente.substring(3));
		    			objSesion.setAttribute( "ACL", acl );
		                objUsuario.setHasLoggedIn(new Boolean(true));
		                objUsuario.updateLastLogin();
		                objUsuario.setLastLogin(new Date());
		    			objSesion.setAttribute("strNombreCompleto", objUsuario.getFirstName()+" "+objUsuario.getLastName());
		    			objSesion.setAttribute("IdUsuarioSesion", new Integer(objUsuario.getId()));
		                aObjData.save();
		                String strPath = aObjData.getContextPath().trim()+"/servlet/template/";   
		                List objTurbineGroups = getGroupByUser(objUsuario.getName());
		                if(objTurbineGroups.size() > 0  ){
		                	objTurbineGroup = (TurbineGroup)objTurbineGroups.get(0);
		                	strTipSistema = getSistema( objTurbineGroup );
		                	iObjLog.debug("LoginAction.doPerform El tiposistema que tiene es el siguiente "+strTipSistema);
		                	objSesion.setAttribute("existeUnidad", "true");
		                }
		                if ( strCamContras != null && strCamContras.trim().equals("1") ){
		                	aObjContext.put("username", nomUser);
		                	aObjContext.put("selDepto", "1");
		                	aObjData.setScreenTemplate("/web2lab,seguridad,CamContras.vm");
		                	return;
		                }
		                if(objTurbineGroups != null && !objTurbineGroups.isEmpty() && objTurbineGroups.size() > 1){
		                	iObjLog.debug("LoginAction.doPerform Entro al if de mas de un grupo");
		                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
		                	if ( strCamContras == null || !strCamContras.trim().equals("1") ){
		                		aObjData.setScreenTemplate("/web2lab,seguridad,OpcionDepartamento.vm");
		                	}
		                } else if(objTurbineGroups != null && objTurbineGroups.size() == 1){
		                	String objMenu = generaMenu(strPath, objRoles)+"";
		                	iObjLog.debug("LoginAction.doPerform regreso con el menu");
		                	objSesion.setAttribute("menu"," ");
		                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
		            		String st = this.getCLabDepByGroup(objTurbineGroup.getGroupId()+"");
		            		objSesion.setAttribute("strIdUnidadActual", st);	            	
		            		objSesion.setAttribute("grupo", "EXPEDIENTE CLINICO ELECTRONICO MEDICO");
		            		objSesion.setAttribute("idgrupo", objTurbineGroup.getGroupId());
		            		objSesion.setAttribute("strIdCLab", getLabPorUnidad(st));
		            		objSesion.setAttribute("strIdCel", getCelPorUnidad(st));
		            		aObjData.setScreenTemplate("/web2lab,ap,BienvenidoECEMedico.vm");
		                }
		        	}        	
        	} else if ((Formatos.isNumeric(nomUser)) && (Integer.parseInt(nomUser) > 999999)) {
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Paciente Usuario " + nomUser + " password " + passwd + " " + strCamContras);
        		PacientesDao objPacientesDAO = new PacientesDao();
        		PacienteBean objPacienteBean = new PacienteBean();
        		objPacienteBean.setKpacientefundacion(Integer.decode(nomUser));
        		objPacienteBean = objPacientesDAO.buscarPaciente(objPacienteBean);
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados DAO son usuario Paciente Usuario " + objPacienteBean.getKpacientefundacion() + " password " + objPacienteBean.getSpasswordexpediente() + " " + strCamContras);
        		if ((objPacienteBean.getSpasswordexpediente() == passwd) || (objPacienteBean.getSpasswordexpediente().equals(passwd))) {
        			nomUser = "eceolab";
        			passwd  = "tjoriard1";
        			objPacientesDAO.actualizaVisitaPaciente(objPacienteBean);
        		}
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Paciente Validacion Usuario " + nomUser + " password " + passwd + " " + strCamContras);
        		objPacienteBean = null;
        		objPacientesDAO = null;
	            List objRoles = getRoleByUser(nomUser);
	            iObjLog.debug("LoginAction:ROLES ENCONTRADOS= "+objRoles);
	            //Valida si el usuario esta dado de alta
	            TurbineUser objTurbineUser = getUserByName(nomUser);	
	    		User objUsuario = TurbineSecurity.getAuthenticatedUser(nomUser, passwd);
	    		if(objUsuario!=null) {
	    			iObjLog.debug("LoginAction.doPerform: EjecutandoSeguridadUtil....");
	    			ACL acl = SeguridadUtil.obtenACL(objTurbineUser.getUserId());
	    			objUsuario.setId(objTurbineUser.getUserId());
	    			aObjData.setUser(objUsuario);
	    			iObjLog.debug("LoginAction.doPerform: el id del usuario es "+objUsuario.getId());
	    			HttpSession objSesion = aObjData.getSession();
	    			objSesion.setAttribute( "username", nomUser );
            		objSesion.setAttribute("kPaciente", nomPaciente);
	    			objSesion.setAttribute( "ACL", acl );
	                objUsuario.setHasLoggedIn(new Boolean(true));
	                objUsuario.updateLastLogin();
	                objUsuario.setLastLogin(new Date());
	    			objSesion.setAttribute("strNombreCompleto", objUsuario.getFirstName()+" "+objUsuario.getLastName());
	    			objSesion.setAttribute("IdUsuarioSesion", new Integer(objUsuario.getId()));
	                aObjData.save();
	                String strPath = aObjData.getContextPath().trim()+"/servlet/template/";   
	                List objTurbineGroups = getGroupByUser(objUsuario.getName());
	                if(objTurbineGroups.size() > 0  ){
	                	objTurbineGroup = (TurbineGroup)objTurbineGroups.get(0);
	                	strTipSistema = getSistema( objTurbineGroup );
	                	iObjLog.debug("LoginAction.doPerform El tiposistema que tiene es el siguiente "+strTipSistema);
	                	objSesion.setAttribute("existeUnidad", "true");
	                }
	                if ( strCamContras != null && strCamContras.trim().equals("1") ){
	                	aObjContext.put("username", nomUser);
	                	aObjContext.put("selDepto", "1");
	                	aObjData.setScreenTemplate("/web2lab,seguridad,CamContras.vm");
	                	return;
	                }
	                if(objTurbineGroups != null && !objTurbineGroups.isEmpty() && objTurbineGroups.size() > 1){
	                	iObjLog.debug("LoginAction.doPerform Entro al if de mas de un grupo");
	                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
	                	if ( strCamContras == null || !strCamContras.trim().equals("1") ){
	                		aObjData.setScreenTemplate("/web2lab,seguridad,OpcionDepartamento.vm");
	                	}
	                }else if(objTurbineGroups != null && objTurbineGroups.size() == 1){
	                	String objMenu = generaMenu(strPath, objRoles)+"";
	                	iObjLog.debug("LoginAction.doPerform regreso con el menu");
//	                	objSesion.setAttribute("menu",objMenu);
	                	objSesion.setAttribute("menu"," ");
	                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
	            		String st = this.getCLabDepByGroup(objTurbineGroup.getGroupId()+"");
	            		objSesion.setAttribute("strIdUnidadActual", st);	            	
//	            		objSesion.setAttribute("grupo", objTurbineGroup.getGroupName());
	            		objSesion.setAttribute("grupo", "EXPEDIENTE CLINICO ELECTRONICO PACIENTE");
	            		objSesion.setAttribute("idgrupo", objTurbineGroup.getGroupId());
	            		objSesion.setAttribute("strIdCLab", getLabPorUnidad(st));
	            		objSesion.setAttribute("strIdCel", getCelPorUnidad(st));
	            		aObjData.setScreenTemplate("/web2lab,ap,BienvenidoECE.vm");
	                }
	        	}
        	} else {        	
                iObjLog.debug("LoginAction + + + + + + + Los datos recuperados son usuario Empleado Usuario " + nomUser + " password " + passwd + " " + strCamContras);
	            List objRoles = getRoleByUser(nomUser);
	            iObjLog.debug("LoginAction:ROLES ENCONTRADOS= "+objRoles);
	            //Valida si el usuario esta dado de alta
	            TurbineUser objTurbineUser = getUserByName(nomUser);
	            iObjLog.debug("LoginAction:Password= "+objTurbineUser.getPassword());
//	            if (objTurbineUser == null) {
//	                aObjContext.put("mensaje","El usuario no es v&aacute;lido, intente de nuevo.");
//	                aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");	            	
//	            } else {
		    		User objUsuario = TurbineSecurity.getAuthenticatedUser(nomUser, passwd);		    		
		    		if(objUsuario!=null) {
		    			iObjLog.debug("LoginAction.doPerform: EjecutandoSeguridadUtil....");
		    			ACL acl = SeguridadUtil.obtenACL(objTurbineUser.getUserId());
		    			objUsuario.setId(objTurbineUser.getUserId());
		    			aObjData.setUser(objUsuario);
		    			
		    			
		    			iObjLog.debug("LoginAction.doPerform: el id del usuario es "+objUsuario.getId());
		    			HttpSession objSesion = aObjData.getSession();
		    			objSesion.setAttribute( "username", nomUser );
		    			objSesion.setAttribute( "ACL", acl );
		                objUsuario.setHasLoggedIn(new Boolean(true));
		                objUsuario.updateLastLogin();
		                objUsuario.setLastLogin(new Date());
		    			objSesion.setAttribute("strNombreCompleto", objUsuario.getFirstName()+" "+objUsuario.getLastName());
		    			objSesion.setAttribute("IdUsuarioSesion", new Integer(objUsuario.getId()));
		                aObjData.save();
		                String strPath = aObjData.getContextPath().trim()+"/servlet/template/";   
		                List objTurbineGroups = getGroupByUser(objUsuario.getName());
		                if(objTurbineGroups.size() > 0  ){
		                	objTurbineGroup = (TurbineGroup)objTurbineGroups.get(0);
		                	strTipSistema = getSistema( objTurbineGroup );
		                	iObjLog.debug("LoginAction.doPerform El tiposistema que tiene es el siguiente "+strTipSistema);
		                	objSesion.setAttribute("existeUnidad", "true");
		                }
		//                boolean bolCamPasswd = new SCambiaPass().getCambiaPasswd(nomUser);
		//                iObjLog.debug("");
		//                if ( bolCamPasswd ){
		//                	aObjData.setScreenTemplate("/web2lab,seguridad,CamContras.vm");
		//                	aObjContext.put("username", nomUser);
		//                	aObjContext.put("selDepto", "1");
		//                	return;
		//                }
		                if ( strCamContras != null && strCamContras.trim().equals("1") ){
		                	aObjContext.put("username", nomUser);
		                	aObjContext.put("selDepto", "1");
		                	aObjData.setScreenTemplate("/web2lab,seguridad,CamContras.vm");
		                	return;
		                }
		                if(objTurbineGroups != null && !objTurbineGroups.isEmpty() && objTurbineGroups.size() > 1){
		                	iObjLog.debug("LoginAction.doPerform Entro al if de mas de un grupo");
		                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
		                	if ( strCamContras == null || !strCamContras.trim().equals("1") ){
		                		aObjData.setScreenTemplate("/web2lab,seguridad,OpcionDepartamento.vm");
		                	}
		                }else if(objTurbineGroups != null && objTurbineGroups.size() == 1){
		                	String objMenu = generaMenu(strPath, objRoles)+"";
		                    BGrupoMenuUtil objGrupMenUtil = new BGrupoMenuUtil();
		                    boolean bolGerente = objGrupMenUtil.isGerente(objUsuario.getId() + "");
		                    objGrupMenUtil = null;
		                	iObjLog.debug("LoginAction.doPerform regreso con el menu");
		                	objSesion.setAttribute("menu",objMenu);
		                	objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
		            		String st = this.getCLabDepByGroup(objTurbineGroup.getGroupId()+"");
		            		objSesion.setAttribute("strIdUnidadActual", st);	            	
		            		objSesion.setAttribute("grupo", objTurbineGroup.getGroupName());
		            		objSesion.setAttribute("idgrupo", objTurbineGroup.getGroupId());		            		
		            		if (Integer.parseInt(objUsuario.getConfirmed()) == 2) {
		            			int cConvenio = 0;
		            			int cCliente = 750;
		            			switch (objTurbineGroup.getGroupId().intValue()) {
		            				case 27:
		            					cConvenio = (1180);		            		
		            					break;
		            				case 28:
		            					cConvenio = (1177);		            		
		            					break;
		            				case 29:
		            					cConvenio = (1175);		            		
		            					break;
		            				case 30:
		            					cConvenio = (1179);		            		
		            					break;
		            				case 31:
		            					cConvenio = (1174);		            		
		            					break;
		            				case 32:
		            					cConvenio = (1178);		            		
		            					break;
		            				case 33:
		            					cConvenio = (1173);		            		
		            					break;
		            				case 34:
		            					cConvenio = (1176);		            		
		            					break;
		            			}
			            		objSesion.setAttribute("cConvenioMarca", cConvenio + "");		            				            			
			            		objSesion.setAttribute("cClienteMarca", cCliente + "");		            				            			
		            		}
		            		if (bolGerente) {
			            		objSesion.setAttribute("NombreRol", "Gerente Sucursal");
			            		objSesion.setAttribute("IdRol", "330");
		            		} else {
			            		objSesion.setAttribute("NombreRol", "Recepcionista InfoDiaMex");
			            		objSesion.setAttribute("IdRol", "0");
		            		}
		            		objSesion.setAttribute("strIdCLab", getLabPorUnidad(st));
		            		objSesion.setAttribute("strIdCel", getCelPorUnidad(st));
		//	            	objSesion.setAttribute("strIdCLabDefault", getLaboratorioByDesc(ValoresCatalogo.LABORATORIO_DEFAULT));
		            		aObjData.setScreenTemplate("/web2lab,ap,PantallaInicio.vm");
		                }
		        	} else {
		                aObjContext.put("mensaje","El Password no es v&aacute;lido, intente de nuevo.");
		                aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
		        	}
//	            }
        	}
        }catch(DataBackendException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","No se he podido Autentificar el usuario.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(UnknownEntityException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","El usuario no es v&aacute;lido, intente de nuevo.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(PasswordMismatchException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","El password no es v&aacute;lido, intente de nuevo.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(TurbineSecurityException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","El usuario o password no es v&aacute;lido, intente de nuevo.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch (Exception aError){
        	iObjLog.error(" Error en LoginAction.doPerform..... ", aError);
        	aObjContext.put("mensaje","No se he podido Autentificar el usuario");
        	aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        	throw aError;
        }
    }
        
    
    /**
     * Metodo que genera las opciones de menu a las que tiene
     * acceso un usuario, a travez de sus permisos y roles 
     * que tiene asignados
     * @param aStrPath se requiere conocer el path de la aplicacion 
     * para ser asignada la ruta completa 
     * @return String con todos las opciones generadas por el menu
     * @throws Exception
     */
    public String generaMenu(String aStrPath, List objRoles) throws Exception {
        StringBuffer sBMenu = new StringBuffer("");
        String sMenu = "";
        try{
            BGrupoMenuUtil objGrupMenUtil = new BGrupoMenuUtil();
            Vector objGruposMenu = new Vector();
            objGruposMenu = objGrupMenUtil.getMenu(objRoles);
            if(objGruposMenu!=null && !objGruposMenu.isEmpty()) {
                int i=0;
                int intMenuConTit = 1;
                int intMenuConSub = 1000; //esta variable es para evitar que la primera vez truene 
                String strTitMenu = "";
                sBMenu.append("oCMenu.makeMenu('top0','','&nbsp;MEN&Uacute;','') \n ");
                for(Enumeration objEnum = objGruposMenu.elements(); objEnum.hasMoreElements();){
                    BMenuOptions objMenuOp = (BMenuOptions)objEnum.nextElement();
                    if(objMenuOp.getIdGrupoMenu() != i){
                        strTitMenu = objMenuOp.getDescGrupoMenu();
                        if(!sBMenu.toString().equals("")) {
                        	sBMenu.append("oCMenu.makeMenu('sub"+intMenuConTit+"','top0','&nbsp;"+strTitMenu+"','') \n ");
                        }else {
                        	sBMenu.append("oCMenu.makeMenu('sub"+intMenuConTit+"','','&nbsp;"+strTitMenu+"','') \n ");
                        }
                        if( intMenuConTit > 1){
                            intMenuConSub = intMenuConTit * 10; //esta variable es para evitar que la primera vez truene
                        }
                        intMenuConTit ++;
                        i = objMenuOp.getIdGrupoMenu();
                    }
                    sBMenu.append("oCMenu.makeMenu('sub"+intMenuConSub+"','sub"+(intMenuConTit-1)+"','"+objMenuOp.getDescPermiso()+"','"+aStrPath+objMenuOp.getLiga()+"')\n ");
                    intMenuConSub++;
                }
                sMenu = "<script>\n"+ sBMenu.toString() + "\n oCMenu.construct() \n </script>";
            }
        }catch(DataSetException aObjException){
            iObjLog.error("Error en la generacion de menu...", aObjException);
            throw aObjException;
        }catch(TorqueException aObjException){
            iObjLog.error("Error en la generacion de menu...", aObjException);
            throw aObjException;
        }catch(SQLException aObjException){
            iObjLog.error("Error en la generacion de menu...", aObjException);
            throw aObjException;
        }
        return sMenu;
    }
    
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
     */
    public List getRoleByUser(String  strLoginUser) throws Exception{
    	List objTurbinGroupLLena = new Vector();

    	String strQuery = 	"SELECT DISTINCT TURBINE_ROLE.ROLE_ID, " +
    						"		TURBINE_ROLE.ROLE_NAME " +
			    			" FROM TURBINE_USER TURBINE_USER,  TURBINE_USER_GROUP_ROLE " +
			    			" TURBINE_USER_GROUP_ROLE, TURBINE_ROLE TURBINE_ROLE " +
			    			" WHERE ( TURBINE_USER.USER_ID=TURBINE_USER_GROUP_ROLE.USER_ID ) AND " +
			    			" ( TURBINE_ROLE.ROLE_ID=TURBINE_USER_GROUP_ROLE.ROLE_ID ) " +
			    			" AND TURBINE_USER.LOGIN_NAME='"+strLoginUser+"'";
    	try{
	    	List objRecord = BasePeer.executeQuery(strQuery);
	    	if(!objRecord.isEmpty()){
	        	Iterator iListaTurbinGroup = objRecord.iterator();
	        	while (iListaTurbinGroup.hasNext()){
	        		Record objRecordDet = (Record)iListaTurbinGroup.next();
	        		TurbineGroup objTurbineGroup = new TurbineGroup();
	        		objTurbineGroup.setGroupId(new BigDecimal(objRecordDet.getValue("ROLE_ID").asInt()));
	        		iObjLog.debug(" Datos de role Id "+objTurbineGroup.getGroupId());
	        		objTurbineGroup.setGroupName(objRecordDet.getValue("ROLE_NAME").asString());
	        		objTurbinGroupLLena.add(objTurbineGroup);
	        	}
	        }
	    	iObjLog.debug("El usuario tiene "+objTurbinGroupLLena.size()+" grupos");
	    	return objTurbinGroupLLena;  
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener el rol del usuario..", aObjException);
    	    throw aObjException;
    	}catch(TorqueException aObjException){
    	    iObjLog.error("Error al obtener el rol del usuario..", aObjException);
    	    throw aObjException;
    	}
    }
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
     */
    public List getRoleByUser( String  strLoginUser, String aStrIdDepto ) throws Exception {
    	List objTurbinGroupLLena = new Vector();

    	String strQuery = "SELECT DISTINCT TURBINE_ROLE.ROLE_ID, TURBINE_ROLE.ROLE_NAME " +
    			" FROM TURBINE_USER TURBINE_USER,  TURBINE_USER_GROUP_ROLE " +
    			" TURBINE_USER_GROUP_ROLE, TURBINE_ROLE TURBINE_ROLE " +
    			" WHERE ( TURBINE_USER.USER_ID=TURBINE_USER_GROUP_ROLE.USER_ID ) AND " +
    			" ( TURBINE_ROLE.ROLE_ID=TURBINE_USER_GROUP_ROLE.ROLE_ID ) " +
    			" AND TURBINE_USER.LOGIN_NAME='"+strLoginUser+"'" +
    			" AND GROUP_ID = "+aStrIdDepto;
    	try{
	    	List objRecord = BasePeer.executeQuery(strQuery);
	    	if(!objRecord.isEmpty()){
	        	Iterator iListaTurbinGroup = objRecord.iterator();
	        	while (iListaTurbinGroup.hasNext()){
	        		Record objRecordDet = (Record)iListaTurbinGroup.next();
	        		TurbineGroup objTurbineGroup = new TurbineGroup();
	        		objTurbineGroup.setGroupId(new BigDecimal(objRecordDet.getValue("ROLE_ID").asInt()));
	        		iObjLog.debug(" Datos de role Id "+objTurbineGroup.getGroupId());
	        		//System.out.println(" Datos de role Id "+objTurbineGroup.getGroupId());
	        		objTurbineGroup.setGroupName(objRecordDet.getValue("ROLE_NAME").asString());
	        		objTurbinGroupLLena.add(objTurbineGroup);
	        	}
	        }
	    	iObjLog.debug("El usuario tiene "+objTurbinGroupLLena.size()+" grupos");
	    	return objTurbinGroupLLena;
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener el rol del usuario..", aObjException);
    	    throw aObjException;
    	}catch(TorqueException aObjException){
    	    iObjLog.error("Error al obtener el rol del usuario..", aObjException);
    	    throw aObjException;
    	}
    }
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
     *
    *public List getGroupByUser(String  strLoginUser) throws TorqueException, DataSetException{
    	List objTurbinGroupLLena = new Vector();

    	String strQuery = "SELECT distinct TURBINE_GROUP.GROUP_ID, TURBINE_GROUP.GROUP_NAME " +
    			" FROM TURBINE_USER TURBINE_USER,  TURBINE_USER_GROUP_ROLE " +
    			" TURBINE_USER_GROUP_ROLE, TURBINE_GROUP TURBINE_GROUP " +
    			" WHERE ( TURBINE_USER.USER_ID=TURBINE_USER_GROUP_ROLE.USER_ID ) AND " +
    			" ( TURBINE_GROUP.GROUP_ID=TURBINE_USER_GROUP_ROLE.GROUP_ID ) " +
    			" AND TURBINE_USER.LOGIN_NAME='"+strLoginUser+"'";
    	List objRecord = BasePeer.executeQuery(strQuery);
    	if(!objRecord.isEmpty()){
        	Iterator iListaTurbinGroup = objRecord.iterator();
        	while (iListaTurbinGroup.hasNext()){
        		Record objRecordDet = (Record)iListaTurbinGroup.next();
        		TurbineGroup objTurbineGroup = new TurbineGroup();
        		objTurbineGroup.setGroupId(new BigDecimal(objRecordDet.getValue("GROUP_ID").asInt()));
        		iObjLog.debug(" Datos de Group Id "+objTurbineGroup.getGroupId());
        		objTurbineGroup.setGroupName(objRecordDet.getValue("GROUP_NAME").asString());
        		objTurbinGroupLLena.add(objTurbineGroup);
        	}
        }
    	iObjLog.debug("El usuario tiene "+objTurbinGroupLLena.size()+" grupos");
    	return objTurbinGroupLLena;    	
    }*/
    
    /**
     * Metodo que busca datos de un usuario a traves de su login
     * para conocer mayor informacion del TurbineSecuryti ara un usuario 
     * @param strName
     * @return
     */
    public TurbineUser getUserByName(String strName) throws Exception {
        return SeguridadUtil.getUserByName(strName);
    }
    
    
    
    /**
    * Metodo que obtiene la lista de los laboratorios para un usuario
    * a traves de un grupo seleccionado para conocer la descripcion del
    * laboratorio
    */
    public List getLaboratorioByUser(String aStrGroupId) throws Exception {
    	List objListResult = null;
    	String strQuery = 
    		" select clab.claboratorio, clab.slaboratorio, " + 
    		" cdep.cdepartamento, cdep.sdepartamento "  +
			" from turbine_grupo_departamento tgd  "   +
			" inner join dlaboratoriodepartamento dlabdep on "   +
			" dlabdep.claboratoriodepartamento = tgd.claboratoriodepartamento "   +
			" and and tgd.group_id = " + aStrGroupId + 
			" inner join claboratorio clab on "   +
			" dlabdep.claboratorio = clab.claboratorio "   +
			" inner join cdepartamento cdep on "   +
			" cdep.cdepartamento = dlabdep.cdepartamento "  ;
    	try{
        	List objRecord = BasePeer.executeQuery(strQuery);
        	if(!objRecord.isEmpty()){
            	Iterator iLista = objRecord.iterator();
            	objListResult = new ArrayList();
            	while (iLista.hasNext()){
            		Record objRecordDet = (Record)iLista.next();
            		objListResult.add(objRecordDet.getValue("claboratorio").asLongObj());
            		objListResult.add(objRecordDet.getValue("slaboratorio").asString());
            		objListResult.add(objRecordDet.getValue("cdepartamento").asIntegerObj());
            		objListResult.add(objRecordDet.getValue("sdepartamento").asString());
            		iObjLog.debug(" Datos del lab-dep: "+objListResult);
            	}
            }
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener datos en getLaboratorioByUser(String).....", aObjException);
    	    throw aObjException;
    	}catch(TorqueException aObjException){
    	    iObjLog.error("Error al ejecutar query  getLaboratorioByUser(String).....", aObjException);
    	    throw aObjException;
    	}
    	return objListResult;    	
    }


    /**
    * Metodo que regresa el nombre del sistema al que pertenece el usuario
    * Segun su rol al que esta asignado.
    * @param Lista de roles asignados
    * @return nombre del sistema al que esta asignado el usuario
    */
    public String getSistema ( TurbineGroup objTurbineGroup ) throws Exception{
    	BigDecimal objIdGroup = objTurbineGroup.getGroupId();
        String strSistema = "" ;
        String strQuery = "Select ts.ssistema " +
        	" from TURBINE_GRUPO_DEPARTAMENTO tgd, TURBINE_SISTEMA ts " + 
			" WHERE tgd.GROUP_ID = "+objIdGroup+
			" AND tgd.CSISTEMA = ts.CSISTEMA ";
    	try{
        	List objRecord = BasePeer.executeQuery(strQuery);
        	
        	if(!objRecord.isEmpty()){
            	Iterator iLista = objRecord.iterator();
            	while (iLista.hasNext()){
            		Record objRecordDet = (Record)iLista.next();
            		strSistema = objRecordDet.getValue("ssistema").asString();
            	}
            }
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener datos de getSistema.....", aObjException);
    	    throw aObjException;
    	}catch(TorqueException aObjException){
    	    iObjLog.error("Error al ejecutar query getSistema.....", aObjException);
    	    throw aObjException;
    	}
    	
    	return strSistema;    	
    }
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
     */
    public List getGroupByUser(String  strLoginUser) throws Exception {
    	List objTurbinGroupLLena = null;
    	CallableStatement objCstmt = null;
    	ResultSet objRs = null;
    	Connection con  = null;
    	String strSQL = "";
    	try{
    		//con = Torque.getConnection();
			GenericDAO objConn = new GenericDAO();
			con = objConn.getConnection();
			strSQL = "select distinct tg.group_id, group_name, tgd.claboratoriodepartamento " +
					 "from turbine_user_group_role tugr, turbine_user tu, turbine_group tg, turbine_grupo_departamento tgd " +
					 "where tugr.user_id =tu.user_id " +
					 "and tugr.group_id = tg.group_id " +
					 "and tg.GROUP_ID = tgd.GROUP_ID " +
					 "and tu.login_name = '" + strLoginUser + "' " + 
					 "order by 2";
/*			
    		objCstmt = con.prepareCall("{call USUARIOS.GETDEPTOS(?,?)}");    	
    		objCstmt.setString(1, strLoginUser);
    		objCstmt.registerOutParameter(2, OracleTypes.CURSOR);
    		objCstmt.execute();
    		objRs = (ResultSet)objCstmt.getObject(2);
*/
			Statement objsmt = con.createStatement();
    	    iObjLog.debug("Deubug al ejecutar PL GETDEPTOS...." + strSQL);
			objRs = objsmt.executeQuery(strSQL);
    		objTurbinGroupLLena = new Vector();
    		while (objRs.next()){
        		TurbineGroup objTurbineGroup = new TurbineGroup();
        		objTurbineGroup.setGroupId(new BigDecimal(objRs.getString(1)));
        		iObjLog.debug(" Datos de Group Id "+objTurbineGroup.getGroupId());
        		objTurbineGroup.setGroupName(objRs.getString(2));
        		objTurbineGroup.setCLabDepto(new Long(objRs.getString(3)));
        		objTurbinGroupLLena.add(objTurbineGroup);
        	}
    	}catch (TorqueException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}catch (SQLException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}finally {
    		if(objRs!=null)objRs.close();
    	    if(objCstmt!=null)objCstmt.close();
    	    if(con!=null)con.close();//Torque.closeConnection(con);// con.close();
    	}
    	return objTurbinGroupLLena;    	
    }
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
    public List getGroupByUser(String  strLoginUser) throws Exception {
    	List objTurbinGroupLLena = null;
    	OracleCallableStatement objCstmt = null;
    	ResultSet objRs = null;
    	Connection con  = null;
    	try{
    		//con = Torque.getConnection();
    		iObjLog.debug("Entrando a LoginAction::getGroupByUser");
			GenericDAO objConn = new GenericDAO();
    		iObjLog.debug("Entrando a LoginAction::getGroupByUser 2");
			con = objConn.getConnection();
			if (con!=null) {
				iObjLog.debug("Entrando a LoginAction::getGroupByUser 3");
			}
    		objCstmt =  (OracleCallableStatement) con.prepareCall("CALL USUARIOS.GETDEPTOS(?,?)");
    		objCstmt.setString(1, strLoginUser);
    		objCstmt.registerOutParameter(2, OracleTypes.CURSOR);
    		objCstmt.execute();
    		objRs = objCstmt.getCursor(2);
    		objTurbinGroupLLena = new Vector();
    		while (objRs.next()){
        		TurbineGroup objTurbineGroup = new TurbineGroup();
        		objTurbineGroup.setGroupId(new BigDecimal(objRs.getString(1)));
        		iObjLog.debug(" Datos de Group Id "+objTurbineGroup.getGroupId());
        		objTurbineGroup.setGroupName(objRs.getString(2));
        		objTurbineGroup.setCLabDepto(new Long(objRs.getString(3)));
        		objTurbinGroupLLena.add(objTurbineGroup);
        	}
    	}catch (TorqueException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}catch (SQLException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}finally {
    		if(objRs!=null)objRs.close();
    	    if(objCstmt!=null)objCstmt.close();
    	    if(con!=null)con.close();//Torque.closeConnection(con);// con.close();
    	}
    	return objTurbinGroupLLena;    	
    }
     */
    
    /**
     * Este metodo es utilizado para obtener el laboratorio 
     * asignado por default a una unidad
     * @param aStrUnidad
     * @return
     * @throws Exception
     */
    public String getLabPorUnidad(String aStrUnidad) throws Exception{
    	iObjLog.debug("getLabPorUnidad  Entra con unidad: "+aStrUnidad);
    	String strlabUnidad = "";
    	String strQuery = "select claboratorio " + 
				" from cunidad cu, dregioncelula drc, ccelula cc, dlaboratoriocelula dlc " +    
				" where  cu.cunidad = " + aStrUnidad +
				" and cu.cregioncelula = drc.cregioncelula " +
				" and drc.ccelula = cc.ccelula " +
				" and cc.ccelula = dlc.ccelula " ;
    	try{
    		iObjLog.debug("query de labXunidad  "+strQuery);
    		List objRecord = null;
    		if(aStrUnidad!=null && !aStrUnidad.trim().equals("")){
//    			objRecord = BasePeer.executeQuery(strQuery);
    		}
        	if(objRecord!=null && !objRecord.isEmpty()){
            	Iterator iLista = objRecord.iterator();
            	while (iLista.hasNext()){
            		Record objRecordDet = (Record)iLista.next();
            		strlabUnidad = objRecordDet.getValue("claboratorio").asString();
            	}
            }
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener datos del laboratorio asociado.....", aObjException);
    	    throw aObjException;
//    	}catch(TorqueException aObjException){
//    	    iObjLog.error("Error al ejecutar query getLabPorUnidad.....", aObjException);
//    	    throw aObjException;
    	}
    	return strlabUnidad;
    }
    /**
     * Este metodo es utilizado para obtener la celula 
     * asignado por default a una unidad
     * @param aStrUnidad
     * @return
     * @throws Exception
     */
    public String getCelPorUnidad(String aStrUnidad) throws Exception{
    	String strCelUnidad = "";
    	String strQuery = "select cc.ccelula " + 
			" from cunidad cu, dregioncelula drc, ccelula cc " +    
			" where  cu.cunidad =" + aStrUnidad + 
			" and cu.cregioncelula = drc.cregioncelula " + 
			" and drc.ccelula = cc.ccelula ";
    	try{
    		iObjLog.debug("query de celXunidad  "+strQuery);
        	List objRecord = null;
        	if(aStrUnidad!=null && !aStrUnidad.trim().equals("")){
//            	objRecord = BasePeer.executeQuery(strQuery);
        	}
        	if(objRecord!=null && !objRecord.isEmpty()){
            	Iterator iLista = objRecord.iterator();
            	while (iLista.hasNext()){
            		Record objRecordDet = (Record)iLista.next();
            		strCelUnidad = objRecordDet.getValue("ccelula").asString();
            	}
            }
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener datos del laboratorio asociado.....", aObjException);
    	    throw aObjException;
//    	}catch(TorqueException aObjException){
//    	    iObjLog.error("Error al ejecutar query getLabPorUnidad.....", aObjException);
//    	    throw aObjException;
    	}
    	return strCelUnidad;
    }
    /**
     * Metod que obtiene el laboratorio departamento a travez
     * del grupo_id que le corresponde al usuario
     * @param aStrLaboratorio
     * @return
     * @throws Exception
     */
    public String getCLabByGroup(String aStrLaboratorio) throws Exception{
    	String strlabDepto = "";
    	String strQuery = " select dld.claboratoriodepartamento as uno, " +
    			" dld.claboratorio as dos, dld.cdepartamento as tres" +
    			" from TURBINE_GRUPO_DEPARTAMENTO tgd, " +
    			" DLABORATORIODEPARTAMENTO dld " +
    			" WHERE GROUP_ID = "+aStrLaboratorio+
				"  and tgd.claboratoriodepartamento = " +
				"dld.claboratoriodepartamento ";
    	try{
    		iObjLog.debug("query de labXGROUP "+strQuery);
    		List objRecord = new ArrayList();
//        	List objRecord = BasePeer.executeQuery(strQuery);
        	if(!objRecord.isEmpty()){
            	Iterator iLista = objRecord.iterator();
            	while (iLista.hasNext()){
            		Record objRecordDet = (Record)iLista.next();
            		strlabDepto = objRecordDet.getValue("uno").asString();
            		strlabDepto = strlabDepto+"|"+objRecordDet.getValue("dos").asString();
            		strlabDepto = strlabDepto+"|"+objRecordDet.getValue("tres").asString();
            	}
            }
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener datos del laboratorio asociado.....", aObjException);
    	    throw aObjException;
//    	}catch(TorqueException aObjException){
//    	    iObjLog.error("Error al ejecutar query getLabPorUnidad.....", aObjException);
//    	    throw aObjException;
    	}
    	iObjLog.debug("REGRESANDOLABPORGRUPO:"+strlabDepto+"|");
    	return strlabDepto;
    }
    
    /**
     * Metod que obtiene el laboratorio departamento a travez
     * del grupo_id que le corresponde al usuario
     * @param aStrLaboratorio
     * @return
     * @throws Exception
     */
    public String getCLabDepByGroup(String aStrLaboratorio) throws Exception{
    	String strlabDepto = "";
    	String strQuery = " select tgd.claboratoriodepartamento as uno " +    			
    			" from TURBINE_GRUPO_DEPARTAMENTO tgd " +    			
    			" WHERE GROUP_ID = "+aStrLaboratorio;
    	try{
    		iObjLog.debug("query de labXGROUPDEP "+strQuery);
    		List objRecord = new ArrayList();
//        	List objRecord = BasePeer.executeQuery(strQuery);
        	if(!objRecord.isEmpty()){
            	Iterator iLista = objRecord.iterator();
            	while (iLista.hasNext()){
            		Record objRecordDet = (Record)iLista.next();
            		strlabDepto = objRecordDet.getValue("uno").asString();            		
            	}
            }
    	}catch(DataSetException aObjException){
    	    iObjLog.error("Error al obtener datos del laboratorio asociado.....", aObjException);
    	    throw aObjException;
//    	}catch(TorqueException aObjException){
//    	    iObjLog.error("Error al ejecutar query getLabPorUnidad.....", aObjException);
//    	    throw aObjException;
    	}
    	iObjLog.debug("REGRESANDOLABPORGRUPODEP:"+strlabDepto+"|");
    	return strlabDepto;
    }
    
    /**
     * Metodo que obtiene la lista de los laboratorios para un usuario
     * a traves de un grupo seleccionado para conocer la descripcion del
     * laboratorio
     */
     public String getLaboratorioByDesc(String aStrDescLaboratorio) throws Exception {
     	String strIdLab = "";
     	String strQuery = 
     		" select clab.claboratorio "  +
 			" from claboratorio clab "   +
 			" where clab.slaboratorio = '" + aStrDescLaboratorio + "'";
     	try{
    		List objRecord = new ArrayList();
//         	List objRecord = BasePeer.executeQuery(strQuery);
         	if(!objRecord.isEmpty()){
             	Iterator iLista = objRecord.iterator();
             	while (iLista.hasNext()){
             		Record objRecordDet = (Record)iLista.next();
             		strIdLab = objRecordDet.getValue("claboratorio").asString();
             		iObjLog.debug("Datos del lab: "+strIdLab);
             	}
             }
     	}catch(DataSetException aObjException){
     	    iObjLog.error("Error al obtener datos en getLaboratorioByDesc:" + aObjException.toString());
     	    throw aObjException;
//     	}catch(TorqueException aObjException){
//     	    iObjLog.error("Error al ejecutar query  getLaboratorioByDesc:" + aObjException.toString());
//     	    throw aObjException;
     	}
     	return strIdLab;    	
     }

}

