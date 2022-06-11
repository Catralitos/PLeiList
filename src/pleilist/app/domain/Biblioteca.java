package pleilist.app.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Biblioteca que guarda videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Biblioteca {
    
    private Map<String, Video> mapaVids;
    
    /**
     * Construtor da classe Biblioteca
     */
    public Biblioteca() {
        this.mapaVids = new HashMap<>();
    }
    
    /**
     * Retorna os videos na biblioteca
     * @return videos na biblioteca
     */
    public List<Video> getVideos(){
        return new ArrayList<>(mapaVids.values());
    }
    
    /**
     * Retorna o video que corresponde ao codigo
     * @param codigo - Codigo do video
     * @return video correspondente
     */
    public Video getVideo(String codigo) {
        return mapaVids.get(codigo);
    }
    
    /**
     * Adiciona um video a biblioteca
     * @param codigo - Codigo do video
     * @param video - Video a adicionar
     */
    public void adicionarVideo(String codigo, Video video) {
        mapaVids.put(codigo, video);
    }
}
