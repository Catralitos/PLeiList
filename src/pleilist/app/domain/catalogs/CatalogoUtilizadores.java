package pleilist.app.domain.catalogs;

import java.util.Map;

import pleilist.app.domain.Curador;
import pleilist.app.domain.Utilizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Catalogo que guarda utilizadores
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public final class CatalogoUtilizadores {
	
	private static CatalogoUtilizadores singleInstance = null; 
	
	private Map<String,String> mapaAut;
	private Map<String,Utilizador> mapaUser;
	
	/**
	 * Construtor da classe
	 */
	private CatalogoUtilizadores() {
		this.mapaAut = new HashMap<>();
		this.mapaUser = new HashMap<>();
	}
	
	/**
	 * Retorna a unica instancia de CatalogoUtilizadores
	 * @return Catalogo de Utilizadores
	 */
	public static CatalogoUtilizadores getInstance() {
		if (singleInstance == null) 
            singleInstance = new CatalogoUtilizadores(); 
  
        return singleInstance;
	}
	
	/**
	 * Adicionar um utilizador ao catalogo
	 * @param username do utilizador
	 * @param password do utilizador
	 */
	public void inserirUtilizador(String username, String password) {
		Utilizador user = new Utilizador(username);
		mapaAut.put(username,password);	
		mapaUser.put(username,user);
	}
	
	/**
	 * Adicionar um curador ao catalogo
	 * @param username do curador
	 * @param password do curador
	 */
	public void inserirCurador(String username, String password) {
		Curador curator = new Curador(username);
		CatalogoBibliotecas catBib = CatalogoBibliotecas.getInstance();
		mapaAut.put(username,password);	
		mapaUser.put(username,curator);
		catBib.inserirBiblioteca(username);
	}

	/**
	 * Retornar os utilizadores do catalogo
	 * @return lista de utilizadores
	 */
	public List<Utilizador> getUtilizadores() {
		return new ArrayList<>(mapaUser.values());
	}

	/**
	 * Retornar o utilizador de certo username
	 * @param username do utilizador
	 * @return utilizador
	 */
	public Utilizador getUtilizador(String username) {
	    return mapaUser.get(username);
	}
	
	/**
	 * Autentica um utilizador
	 * @param utilizador 
	 * @param username do utilizador
	 * @param password do utilizador
	 * @return true, se o utilizador existe no catalogo, com essa password
	 */
	public boolean autentica(Utilizador utilizador, String username, String password) {
		return mapaAut.get(username).equals(password) && mapaUser.get(username).equals(utilizador);
	}

}
