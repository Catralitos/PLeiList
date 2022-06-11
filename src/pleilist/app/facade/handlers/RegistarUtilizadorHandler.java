package pleilist.app.facade.handlers;

import pleilist.app.domain.catalogs.CatalogoUtilizadores;

/**
 * Biblioteca que guarda videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 * @author dco0001
 */
public class RegistarUtilizadorHandler {

	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */ 
	public void registarUtilizador(String username, String password) {
		CatalogoUtilizadores catUsers = CatalogoUtilizadores.getInstance();
		catUsers.inserirUtilizador(username,password);
	}


	/**
	 * Regista um utilizador com nível de curador.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username, com nível de curador
	 */
	public void registarCurador(String string, String string2) {
		CatalogoUtilizadores catUsers = CatalogoUtilizadores.getInstance();
		catUsers.inserirCurador(string, string2);	
	}

}
