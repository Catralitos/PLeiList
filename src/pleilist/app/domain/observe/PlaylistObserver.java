package pleilist.app.domain.observe;

/**
 * Observer para comunicar com observables
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public interface PlaylistObserver {
    
    /**
     * Faz um update a classe
     * @param o - observable que envia notificacao
     * @param e - evento que define o update
     */
    void update(VideoObservable o);
}

