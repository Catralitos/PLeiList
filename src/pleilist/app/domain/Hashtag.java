package pleilist.app.domain;

/**
 * Hashtag, uma etiqueta para videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Hashtag {

    private String nome;
    
    /**
     * Construtor da classe
     * @param nome - Nome do hashtag
     */
    public Hashtag(String nome) {
        this.nome = nome;
    }
    
    /**
     * Retorna o nome do hashtag
     * @return nome do hashtag
     */
    public String getNome() {
        return nome;
    }
}
