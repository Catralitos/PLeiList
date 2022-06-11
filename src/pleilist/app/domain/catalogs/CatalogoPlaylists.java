package pleilist.app.domain.catalogs;

/**
 * Catalogo que guarda Playlists
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
import java.util.Map;

import pleilist.app.domain.Playlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CatalogoPlaylists {

    private static CatalogoPlaylists singleInstance = null; 

    private Map<String,Playlist> mapaLists;

    /**
     * Construtor da classe
     */
    private CatalogoPlaylists() {
        this.mapaLists = new HashMap<>();
    }

    /**
     * Retorna a unica instancia de CatalogoPlaylists
     * @return Catalogo de Playlists
     */
    public static CatalogoPlaylists getInstance() {
        if (singleInstance == null) 
            singleInstance = new CatalogoPlaylists(); 

        return singleInstance;
    }
    
    /**
     * Retorna as playlists deste catalogo
     * @return lista de playlists
     */
    public List<Playlist> getPlaylists(){
        return new ArrayList<>(mapaLists.values());
    }
    
    /**
     * Retorna a playlist com o certo codigo
     * @param codigo da playlist
     * @return playlist com o codigo
     */
    public Playlist getPlaylist(String codigo) {
        return mapaLists.get(codigo);
    }
    
    /**
     * Adiciona uma playlist ao catalogo
     * @param codigo da playlist
     * @param playlist a adicionar
     */
    public void inserirPlaylist(String codigo, Playlist playlist) {
        mapaLists.put(codigo, playlist);
    }
}
