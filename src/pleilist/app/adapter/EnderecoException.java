package pleilist.app.adapter;

/**
 * Interface para classes que verificam se um endereco web existe
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public interface EnderecoException {

    /**
     * Retorna s eum url existe
     * @param url a testar
     * @return true se url nao retorn 404 ou outro erro
     */
	public boolean existeEndereco(String url);
	
}
