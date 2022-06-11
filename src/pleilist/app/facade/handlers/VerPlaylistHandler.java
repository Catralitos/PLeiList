package pleilist.app.facade.handlers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import pleilist.app.domain.Utilizador;
import pleilist.app.domain.Video;
import pleilist.app.domain.catalogs.CatalogoPlaylists;
import pleilist.app.domain.catalogs.CatalogoVideos;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.facade.dto.Pair;
import pleilist.app.facade.dto.Playlist;

/**
 * Biblioteca que guarda videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 * @author dco0001
 */
public class VerPlaylistHandler {

    private Utilizador uCorrente;
    private pleilist.app.domain.Playlist pCorrente;
    private Entrada entrada;
    private int indiceCorrente;

    /**
     * Construtor da classe
     * @param s - sessao do utilizador
     */
    public VerPlaylistHandler(Sessao s) {
        this.uCorrente = s.getUtilizador();
        this.indiceCorrente = 0;
    }

    /**
     * Procura todas as Playlists que tenham tag
     * @param tag
     * @return Lista com Playlists associada a aquela tag
     */
    public List<Playlist> procurarPorTag(String tag) {       
        List<Playlist> ls = new ArrayList<>();
        List<pleilist.app.domain.Playlist> aux = CatalogoPlaylists.getInstance().getPlaylists();
        for(pleilist.app.domain.Playlist p : aux) 
            if(p.temTag(tag))
                ls.add(new Playlist(p.getNome(), p.getCodigo(), p.getClassificacao()));
        return ls;
    }

    /**
     * Escolhe a playlist associada ao codigo
     * @param codigo
     * @return Duracao da Playlist
     */
    public Duration escolhePlaylist(String codigo) {
        this.pCorrente = CatalogoPlaylists.getInstance().getPlaylist(codigo);
        this.entrada = pCorrente.getItems().get(0).getEntrada();
        return pCorrente.getDuracao();
    }

    /**
     * Observa o Proximo Video da Playlist
     * @return Video corrente(proximo)
     */
    public Pair<String, Duration> verProximo() {
        Pair<String, Duration> par = new Pair<>(entrada.getTitulo(), entrada.getDuracao());
        CatalogoVideos catVids = CatalogoVideos.getInstance();
        Video v = catVids.getVideo(entrada.getCodigo());
        v.aumentarVisualizacao();
        if (haProximoVideo()) {
            //se nao for o ultimo
            if (indiceCorrente < pCorrente.getItems().size()) {
                this.entrada = pCorrente.getItems().get(indiceCorrente).getEntrada();
            }
            return par;
        } else {
            return new Pair<>("", Duration.ofSeconds(0));
        }
    }

    /**
     * Adicionar/Mudar classificacao para video corrente
     * @param estrelas da classificacao
     */
    public void classificar(int estrelas) {
        CatalogoVideos.getInstance()
        .getVideo(pCorrente.getItems().get(indiceCorrente - 1).getCodigo())
        .adicionarClassificacao(uCorrente.getUsername(), estrelas);
    }

    /**
     * Atualiza indiceCorrente, item, e videoCorrente
     * retorn true se houver proximo video
     */
    private boolean haProximoVideo(){
        int maxVideos = pCorrente.getItems().size();
        if(indiceCorrente < maxVideos) {
            this.indiceCorrente++;
            return true;
        }else{
            return false;
        }
    }

}
