package pleilist.app.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import pleilist.app.domain.Clip;
import pleilist.app.domain.GeradorCodigos;
import pleilist.app.domain.Item;
import pleilist.app.domain.Playlist;
import pleilist.app.domain.Video;
import pleilist.app.domain.catalogs.CatalogoBibliotecas;
import pleilist.app.domain.catalogs.CatalogoPlaylists;

/**
 * Para gerar uma playlist com os videos com melhor classificacao
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */public class TopRanked implements RandomListStrategy{

     /**
      * Devolve uma playlist com os videos com maior classificacao
      */
    @Override
    public Playlist playlistAleatoria(String nome, int nVideos) {
        Playlist lista = new Playlist(nome);
        ArrayList<Video> videos = new ArrayList<>(CatalogoBibliotecas.getInstance().getBibliotecaGeral().getVideos());
        int n = nVideos < videos.size() ? nVideos : videos.size(); 
        videos = new ArrayList<>(videos.stream()
                .sorted(Comparator.comparingDouble(Video::getClassificacao).reversed())  
                .limit(n)  
                .collect(Collectors.toList()));  
        for (Video v : videos) {
            Item item = new Item(v, lista.getDuracao());
            if (v instanceof Clip) {
                item.setDuracao(((Clip) v).getDuracao());
            } else {
                item.setDuracao(DURACAOSTREAM);
            }
            v.addObserver(lista);
            lista.adicionarItem(item);
        }
        finalizarPlaylist(lista);
        return lista;
    }

    /**
     * Finaliza a criacao de uma playlist
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
