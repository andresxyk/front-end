package mx.com.web2lab.beans;

import java.io.Serializable;

public class MenuOptions implements Serializable{
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente al idGrupoMenu
	 */
	private int idGrupoMenu;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente a la descripcion de un grupo
	 */
	private String descGrupoMenu;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente al id del permiso
	 */
	private int idPermiso;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente a la descripcion de un permiso 
	 */
	private String descPermiso;
	
	/**
	 * Variable utilizada para conocer o extraer el valor
	 * referente al path donde se accesa a un modulo
	 */
	private String liga;
	
	
	/***      METODOS  ACCESORES   ***/
	
	/**
	 * Metodo que obtiene el Id del grupo al que pertenece un 
	 * permiso 
	 * @return
	 */
	public int getIdGrupoMenu(){
		return idGrupoMenu;
	}
	
	/**
	 * Metodo que obtiene la descripcion del grupo al que pertenece
	 * un permiso
	 * @return
	 */
	public String getDescGrupoMenu(){
		return descGrupoMenu;
	}

	/**
	 * Metodo que obtiene el id del permiso perteneciente al 
	 * objeto
	 * @return
	 */
	public int getIdPermiso(){
		return idPermiso;
	}
	
	/**
	 * Metodo que obtiene la descripcion del permiso 
	 * @return
	 */
	public String getDescPermiso(){
		return descPermiso;
	}
	
	/**
	 * Metodo que obtiene el path de un permiso referente al objeto
	 * @return
	 */
	public String getLiga(){
		return liga;
	}
	
	/***    	METODOS MUTADORES       ***/
	
	/**
	 * Metodo que asigna el Id del grupo al que pertenece un 
	 * permiso 
	 * @return
	 */
	public void setIdGRupoMenu(int idGrupoMenu){
		this.idGrupoMenu = idGrupoMenu;
	}
	
	/**
	 * Metodo que asigna la descripcion del grupo al que pertenece
	 * un permiso
	 * @return
	 */
	public void setDescGrupoMenu(String descGrupoMenu){
		this.descGrupoMenu = descGrupoMenu;
	}

	/**
	 * Metodo que obtiene el id del permiso perteneciente al 
	 * objeto
	 * @return
	 */
	public void getIdPermiso(int idPermiso){
		this.idPermiso = idPermiso;
	}
	
	/**
	 * Metodo que obtiene la descripcion del permiso 
	 * @return
	 */
	public void setDescPermiso(String descPermiso){
		this.descPermiso = descPermiso;
	}
	
	/**
	 * Metodo que obtiene el path de un permiso referente al objeto
	 * @return
	 */
	public void setLiga(String liga){
		this.liga = liga;
	}
}
