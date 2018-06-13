package mx.com.web2lab.util.seguridad;

import java.io.Serializable;

public class BTurbineGroup  implements Serializable{
	/** Obtiene el id del grupo */
	private int iIntGroupId;
	/** Obtiene el nombre del grupo */
	private String iStrObjGroupName;
	/**
	 * @return Regresa la(s) iIntGroupId.
	 */
	public int getIIntGroupId() {
		return iIntGroupId;
	}
	/**
	 * @param intGroupId La iIntGroupId a establecer.
	 */
	public void setIIntGroupId(int intGroupId) {
		iIntGroupId = intGroupId;
	}
	/**
	 * @return Regresa la(s) iStrObjGroupName.
	 */
	public String getIStrObjGroupName() {
		return iStrObjGroupName;
	}
	/**
	 * @param strObjGroupName La iStrObjGroupName a establecer.
	 */
	public void setIStrObjGroupName(String strObjGroupName) {
		iStrObjGroupName = strObjGroupName;
	}
}