package pleilist.app.domain;

import java.time.Duration;
import java.util.List;

import pleilist.app.facade.dto.Entrada;

/**
 * Guarda video e duracao num Item para usar em Playlists
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Item {
	
    private Duration duracao;
    private Duration horaInicio;
    private Video video;

    /**
     * Construtor da classe
     * @param video - Video para guardar
     * @param horaInicio - Tempo de inicio dentro da lista
     */
    public Item(Video video, Duration horaInicio) {
        this.video = video;
        this.duracao = null;
        this.horaInicio = horaInicio;
    }
    
    /**
     * Retorna o titulo do video do item
     * @return titulo do video
     */
    public String getTitulo() {
        return video.getTitulo();
    }
    
    /**
     * Retorna o codigo do video do item
     * @return codigo do video
     */
    public String getCodigo() {
        return video.getCodigo();
    }
    
    /**
     * Retorna a duracao do item
     * @return duracao do item
     */
    public Duration getDuracao() {
        return this.duracao;
    }
    
    /**
     * Muda a duracao do item
     * @param duracao - duracao de transmissao do video
     */
    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }
    
    /**
     * Retorna a hora de inicio do item
     * @return hora de inicio do item
     */
    public Duration getHoraInicio() {
        return horaInicio;
    }
    
    /**
     * Retorna os hashtags do item
     * @return lista de hashtags
     */
    public List<Hashtag> getTags(){
        return video.getTags();
    }

    /**
     * Retorna uma Entrada com atributos do item
     * @return entrada
     */
    public Entrada getEntrada() {
        return new Entrada(getTitulo(), getCodigo(), getDuracao(), getHoraInicio());
    }

    /**
     * Retorna a classificacao deste item
     * @return classificao do item em estrelas
     */
    public double getClassificacao() {
        return video.getClassificacao();
    }

}
      