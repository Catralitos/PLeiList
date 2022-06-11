package pleilist.app.adapter;

import pleilist.app.adapter.com.chavetasfechadas.UrlChecker;

/**
 * Verifica se um url existe
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class UrlCheckerAdapter implements EnderecoException {

	UrlChecker uc = new UrlChecker();
	
	/**
	 * Retorna true se o url nao retorna 404 ou outro erro
	 */
	@Override
	public boolean existeEndereco(String url) {
		uc.setUrl(url);
		return uc.validate();
	}
}
