package pleilist.app.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pleilist.app.domain.Clip;
import pleilist.app.domain.GeradorCodigos;
import pleilist.app.domain.Hashtag;
import pleilist.app.domain.Item;
import pleilist.app.domain.Playlist;
import pleilist.app.domain.Video;
import pleilist.app.domain.catalogs.CatalogoBibliotecas;
import pleilist.app.domain.catalogs.CatalogoPlaylists;

/**
 * Cria uma playlist com videos ligados por hastags
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class ChainedVideos implements RandomListStrategy{

    private Random r = new Random();
    private Video corrente = null;

    /**
     * Devolve uma playlist gerada aleatoriamente
     * Em que o primeiro elemento e puramente aleatorio
     * E cada a seguir partilha um hashtag com o ultimo escolhido
     */
    @Override
    public Playlist playlistAleatoria(String nome, int nVideos) {
        ArrayList<Video> videos = new ArrayList<>(CatalogoBibliotecas.getInstance().getBibliotecaGeral().getVideos());
        Playlist lista = escolherPrimeiroVideo(nome, videos);
        int n = nVideos < videos.size() ? nVideos : videos.size(); 
        ArrayList<Video> videosNaoPodemosAdicionar = new ArrayList<>();

        while (lista.getItems().size() < n && 
                videosNaoPodemosAdicionar.size() <= (videos.size() - lista.getItems().size())) {
            Video aleatorio = videos.get(r.nextInt(videos.size()));
            if (!lista.getCodigos().contains(aleatorio.getCodigo()) && contemTags(aleatorio)){
                corrente = aleatorio;
                Item item = new Item(aleatorio, lista.getDuracao());
                if (aleatorio instanceof Clip) {
                    item.setDuracao(((Clip) aleatorio).getDuracao());
                } else {
                    item.setDuracao(DURACAOSTREAM);
                }
                aleatorio.addObserver(lista);
                lista.adicionarItem(item);
                videosNaoPodemosAdicionar.clear();
            } else {
                if(!videosNaoPodemosAdicionar.contains(aleatorio))
                    videosNaoPodemosAdicionar.add(aleatorio);
            }
        }

        finalizarPlaylist(lista);
        return lista;
    }

    /**
     * Finaliza a criacao da lista
     * @param lista a finalizar
     */
    private void finalizarPlaylist(Playlist lista) {
        CatalogoPlaylists catPlay = CatalogoPlaylists.getInstance();
        GeradorCodigos gerCod = GeradorCodigos.getInstance();
        String codigo = gerCod.gerarCodigoPlaylist();
        lista.setCodigo(codigo);
        catPlay.inserirPlaylist(codigo, lista);
    }

    /**
     * Escolhe o primeiro video par auma lista
     * @param nome da lista
     * @param videos - lista de videos de onde escolher
     * @return playlist com um novo video aleatorio
     */
    private Playlist escolherPrimeiroVideo(String nome, List<Video> videos) {
        Playlist lista = new Playlist(nome);
        Video v = videos.get(r.nextInt(videos.size()));
        corrente = v;
        Item primeiro = new Item(corrente, lista.getDuracao());
        if (corrente instanceof Clip) {
            primeiro.setDuracao(((Clip) corrente).getDuracao());
        } else {
            primeiro.setDuracao(DURACAOSTREAM);
        }
        v.addObserver(lista);
        lista.adicionarItem(primeiro);
        return lista;
    }

    /**
     * Ve se um video tem tags em comum com o video corrente
     * @param v - video a comparar tags
     * @return true se partilham tags
     */
    private boolean contemTags(Video v) {
        ArrayList<Hashtag> tagsCorrente = new ArrayList<>(corrente.getTags());
        ArrayList<Hashtag> tagsVideo = new ArrayList<>(v.getTags());
        for (Hashtag h : tagsVideo) {
            if (tagsCorrente.contains(h)) {
                return true;
            }
        }
        return false;
    }

}
