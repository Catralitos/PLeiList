package pleilist.app.facade.handlers;

import java.util.List;

import pleilist.app.domain.Playlist;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.strategy.ChainedVideos;
import pleilist.app.strategy.RandomListStrategy;
import pleilist.app.strategy.RandomVideos;
import pleilist.app.strategy.TopRanked;

/**
 * Handler para criar playlists aleatoriamente
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 * @author dco0001
 */
public class PlaylistAleatoriaHandler {

    private Sessao sessao;
    private RandomListStrategy estrategia;
    private String nome;
    private Playlist lista;

    /**
     * Construtor da classe
     * @param s - sessao do utilizador
     */
    public PlaylistAleatoriaHandler(Sessao s) {
        this.sessao = s;
        this.estrategia = null;
        this.nome = "";
        this.lista = null;
    }

    /**
     * Comeca a criar a playlist
     * @param nome da playlist
     */
    public void comecarPlaylist(String nome) {
        this.nome = nome;
    }
    
    /**
     * Decide a estrategia a usar para criar a playlist
     * @param estrategia a usar
     */
    public void decidirEstrategia(String estrategia) {
    		if(estrategia.startsWith("C")) 
    			this.estrategia =  new ChainedVideos();
    		if(estrategia.startsWith("R"))
    			this.estrategia =  new RandomVideos();
    		if(estrategia.startsWith("T"))
    			this.estrategia =  new TopRanked();
    }
      
    /**
     * Obtem uma playlist
     * Se foi definida uma estrategia previamente
     * @param nVideos - numero de videos da playlist
     * @return codigo da playlist criada
     */
    public String obterPlaylist(int nVideos) {
         if (estrategia != null) {
         this.lista = estrategia.playlistAleatoria(this.nome, nVideos);
         return lista.getCodigo();
         } else {
             return "";
         }
    }
    
    /**
     * Devolve uma lista de entradas nesta lista
     * @return lista de entradas
     */
    public List<Entrada> listaEntradas(){
        return lista.getEntradas();
    }

}
