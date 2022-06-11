package pleilist.app.domain.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable para comunicar com Observer
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */public abstract class VideoObservable {
    
    private List<PlaylistObserver> obs = new ArrayList<>();
    
    /**
     * Adiciona um observer
     * @param o - oberserver a adicionar
     */
    public void addObserver(PlaylistObserver o) {
        obs.add(o);
    }
    
    /**
     * Notificar todos os observers em obs
     * @param e evento a enviar
     */
    public void notifyObservers() {
        for (PlaylistObserver o : obs) {
            o.update(this);
        }
    }
    
}
