package pleilist.app.facade;

import pleilist.app.domain.Utilizador;

/**
 * Sessao do utilizador autenticado
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Sessao {

    private Utilizador u;

    /**
     * Construtor da classe
     * @param u -  utilizador a que a sessao corresponde
     */
	public Sessao(Utilizador u) {
	  this.u = u;
	}
	
	/**
	 * Retorna um clone do utilizador
	 * @return clone do utilizador 
	 */
	public Utilizador getUtilizador() {
	    return u.clone();
	}
	
	/**
	 * Retorna se esta sessao corresponde a um curador
	 * @return
	 */
	public boolean ehCurador() {
	    return u.ehCurador();
	}
	
}
