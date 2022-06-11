package pleilist.app.strategy;

import java.time.Duration;

import pleilist.app.domain.Playlist;

/**
 * Interface para as estrategias que geram listas aleatoriamente
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public interface RandomListStrategy {
	
    public static final Duration DURACAOSTREAM = Duration.ofMinutes(15);
	
    /**
     * Devolve uma playlist gerada por uma estrategia
     * @param nome - nome da playlist
     * @param nVideos - numero de videos da playlist
     * @return playlist gerada aleatoriamente
     */
	public Playlist playlistAleatoria(String nome, int nVideos);
}
