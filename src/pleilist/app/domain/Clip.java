package pleilist.app.domain;

import java.time.Duration;

/**
 * Um Video que eh um Clip com duracao finita
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Clip extends Video {

    private Duration duracao;
  
    /**
     * Construtor da classe
     * @param titulo do clip
     * @param endereco do clip
     * @param codigo do clip
     * @param visualizacoes do clip
     * @param classificacoes do clip
     * @param tags do clip 
     * @param duracao do clip
     */
    public Clip() {
        super();
        this.duracao = null;
    }
    
    /**
     * Retorna a duracao do clip
     * @return Duracao do clip
     */
    public Duration getDuracao() {
        return duracao;
    }
    
    /**
     * Adiciona a duraaco ao clip
     * @param duracao - duracao do clip 
     */
    public void adicionarDuracao(Duration duracao) {
        this.duracao = duracao;
    }
    
}
