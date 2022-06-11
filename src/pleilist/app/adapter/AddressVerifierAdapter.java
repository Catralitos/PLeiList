package pleilist.app.adapter;

import java.net.MalformedURLException;
import java.net.URL;
import pleilist.app.adapter.net.padroesfactory.AddressVerifier;

/**
 * Verifica se um endereco web existe
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class AddressVerifierAdapter implements EnderecoException{
	
    /**
     * Avalia se um dado endereço existe
     */
	@Override
	public boolean existeEndereco(String url) {
		try {
			@SuppressWarnings("unused")
			URL enderecoUrl = new URL(url);
			return AddressVerifier.verifyAddress(enderecoUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
