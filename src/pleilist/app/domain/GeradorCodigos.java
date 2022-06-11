package pleilist.app.domain;

import java.util.UUID;

/**
 * Gera codigos para videos e playlists
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class GeradorCodigos {
    private static GeradorCodigos singleInstance = null; 

    /**
     * Construtor da classe 
     */
    private GeradorCodigos() {}
    
    /**
     * Retorna o GeradorCodigos
     * @return unica instancia de GeradorCodigos
     */
    public static GeradorCodigos getInstance() {
        if (singleInstance == null) 
            singleInstance = new GeradorCodigos(); 

        return singleInstance;
    }
	
    /**
     * Gera o codigo para um video
     * @return codigo 
     */
    public String gerarCodigoVideo() {
    	return "V-" + gerarCodigo();
    }
    
    /**
     * Gera o codigo para uma playlist
     * @return playlist
     */
    public String gerarCodigoPlaylist() {
    	return "P-" + gerarCodigo();
    }
    
    /**
     * Gera um codigo UUID
     * @return Um codigo aleatorio unico
     */
	private static String gerarCodigo() {
		return UUID.randomUUID().toString();
	}

}
