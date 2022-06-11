package pleilist.app.domain.catalogs;

import java.util.Map;

import pleilist.app.domain.Hashtag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Catalogo de guarda Hashtags
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class CatalogoHashtags {

    private static CatalogoHashtags singleInstance = null; 
    
    private Map<String,Hashtag> mapaTags;
    
    /**
     * Construtor da classe
     */
    private CatalogoHashtags() {
        this.mapaTags = new HashMap<>();
    }
    
    /**
     * Retorna a unica instancia de CatalogoHashtags
     * @return Catalogo de Hashtags
     */
    public static CatalogoHashtags getInstance() {
        if (singleInstance == null) 
            singleInstance = new CatalogoHashtags(); 
  
        return singleInstance;
        
    }
    
    /**
     * Retorna todos os hashtags do catalogo
     * @return lista de hashtags
     */
    public List<Hashtag> getHashtags(){
        return new ArrayList<>(mapaTags.values());
    }
    
    /**
     * Retorna o hashtag com o certo nome
     * @param nome do hashtag
     * @return hashtag com esse nome
     */
    public Hashtag getHashtag(String nome) {
        return mapaTags.get(nome);
    }
    
    /**
     * Adiciona um hashtag ao catalogo
     * @param nome do hashtag
     * @param hashtag a adicionar
     */
    public void inserirHashtag(String nome, Hashtag hashtag) {
        mapaTags.put(nome, hashtag);
    }
}
