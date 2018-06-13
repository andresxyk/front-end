package mx.com.web2lab.beans;

import java.io.Serializable;

public class BMenuOptions implements Serializable{
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente al idGrupoMenu
	 */
	private int gintidGrupoMenu;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente a la descripcion de un grupo
	 */
	private String gstrdescGrupoMenu;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente al id del permiso
	 */
	private int gintidPermiso;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente a la descripcion de un permiso 
	 */
	private String gstrdescPermiso;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente al path donde se accesa a un modulo
	 */
	private String gstrliga;
	
	
	/***      METODOS  ACCESORES   ***/
	
	/**
	 * Metodo que obtiene el Id del grupo al que pertenece un 
	 * permiso 
	 * @return
	 */
	public int getIdGrupoMenu(){
		return gintidGrupoMenu;
	}
	
	/**
	 * Metodo que obtiene la descripcion del grupo al que pertenece
	 * un permiso
	 * @return
	 */
	public String getDescGrupoMenu(){
		return gstrdescGrupoMenu;
	}

	/**
	 * Metodo que obtiene el id del permiso perteneciente al 
	 * objeto
	 * @return
	 */
	public int getIdPermiso(){
		return gintidPermiso;
	}
	
	/**
	 * Metodo que obtiene la descripcion del permiso 
	 * @return
	 */
	public String getDescPermiso(){
		return gstrdescPermiso;
	}
	
	/**
	 * Metodo que obtiene el path de un permiso referente al objeto
	 * @return
	 */
	public String getLiga(){
		return gstrliga;
	}
	
	/***    	METODOS MUTADORES       ***/
	
	/**
	 * Metodo que asigna el Id del grupo al que pertenece un 
	 * permiso 
	 * @return
	 */
	public void setIdGRupoMenu(int aIntIdGrupoMenu){
		this.gintidGrupoMenu = aIntIdGrupoMenu;
	}
	
	/**
	 * Metodo que asigna la descripcion del grupo al que pertenece
	 * un permiso
	 * @return
	 */
	public void setDescGrupoMenu(String aStrDescGrupoMenu){
		this.gstrdescGrupoMenu = aStrDescGrupoMenu;
	}

	/**
	 * Metodo que obtiene el id del permiso perteneciente al 
	 * objeto
	 * @return
	 */
	public void getIdPermiso(int aIntIdPermiso){
		this.gintidPermiso = aIntIdPermiso;
	}
	
	/**
	 * Metodo que obtiene la descripcion del permiso 
	 * @return
	 */
	public void setDescPermiso(String aStrDescPermiso){
		this.gstrdescPermiso = aStrDescPermiso;
	}
	
	/**
	 * Metodo que obtiene el path de un permiso referente al objeto
	 * @return
	 */
	public void setLiga(String aStrLiga){
		this.gstrliga = aStrLiga;
	}
}
