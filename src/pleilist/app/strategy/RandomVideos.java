package pleilist.app.strategy;

import java.util.ArrayList;
import java.util.Random;

import pleilist.app.domain.Clip;
import pleilist.app.domain.GeradorCodigos;
import pleilist.app.domain.Item;
import pleilist.app.domain.Playlist;
import pleilist.app.domain.Video;
import pleilist.app.domain.catalogs.CatalogoBibliotecas;
import pleilist.app.domain.catalogs.CatalogoPlaylists;

/**
 * Gera uma playlist com videos puramente aleatorios
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class RandomVideos implements RandomListStrategy{

    private Random r = new Random();
    
    /**
     * Devolve uma playlist aleatoria
     */
    @Override
    public Playlist playlistAleatoria(String nome, int nVideos) {
        Playlist lista = new Playlist(nome);
        ArrayList<Video> videos = new ArrayList<>(CatalogoBibliotecas.getInstance().getBibliotecaGeral().getVideos());
        int n = nVideos < videos.size() ? nVideos : videos.size(); 
        while (lista.getItems().size() < n) {
            Video aleatorio = videos.get(r.nextInt(videos.size()));
            if (!lista.getCodigos().contains(aleatorio.getCodigo())){
                Item item = new Item(aleatorio, lista.getDuracao());
                if (aleatorio instanceof Clip) {
                    item.setDuracao(((Clip) aleatorio).getDuracao());
                } else {
                    item.setDuracao(DURACAOSTREAM);
                }
                aleatorio.addObserver(lista);
                lista.adicionarItem(item);
            } 
        }
        finalizarPlaylist(lista);
        return lista;
    }

    /**
     * Finaliza a criacao da playlist
     * @param lista a finalizar a criacao
     */
    private void finalizarPlaylist(Playlist lista) {
        CatalogoPlaylists catPlay = CatalogoPlaylists.getInstance();
        GeradorCodigos gerCod = GeradorCodigos.getInstance();
        String codigo = gerCod.gerarCodigoPlaylist();
        lista.setCodigo(codigo);
        catPlay.inserirPlaylist(codigo, lista);
    }
}
