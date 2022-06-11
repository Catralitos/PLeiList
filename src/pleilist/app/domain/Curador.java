package pleilist.app.domain;

/**
 * Curador que consegue adicionar videos e criar playlists
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Curador extends Utilizador{
	
    /**
     * Construtor da classe
     * @param username -  Nome do curador
     */
	public Curador(String username) {
		super(username);
	}
	
	/**
     * Retorna se eh curador
     * Este metodo existe par anao importar o domain na classe sessao
     * E assim tratar as excecoes so com facade
     * @return true se for curador
     */
	@Override
    public boolean ehCurador() {
        return (this instanceof Curador);
    }

}
