package pleilist.app.domain.catalogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pleilist.app.domain.Video;

/**
 * Catalogo que guarda videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class CatalogoVideos {
    
    private static CatalogoVideos singleInstance = null; 

    private Map<String,Video> mapaVideos;

    /**
     * Construtor da classe
     */
    private CatalogoVideos() {
        this.mapaVideos = new HashMap<>();
    }

    /**
     * Retorna a unica instancia de CatalogoVideos
     * @return Catalogo de Videos
     */
    public static CatalogoVideos getInstance() {
        if (singleInstance == null) 
            singleInstance = new CatalogoVideos(); 

        return singleInstance;
    }
    
    /**
     * Retorna os videos no catalogo
     * @return lista de videos
     */
    public List<Video> getVideos(){
        return new ArrayList<>(mapaVideos.values());
    }
    
    /**
     * Retorna o video de certo codigo
     * @param codigo do video 
     * @return video com o codigo
     */
    public Video getVideo(String codigo) {
        return mapaVideos.get(codigo);
    }
    
    /**
     * Adiciona um video ao catalogo
     * @param codigo do video
     * @param video a adicionar
     */
    public void inserirVideo(String codigo, Video video) {
        mapaVideos.put(codigo, video);
    }
}
