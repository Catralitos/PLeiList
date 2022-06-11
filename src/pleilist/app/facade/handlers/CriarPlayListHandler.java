package pleilist.app.facade.handlers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pleilist.app.domain.Clip;
import pleilist.app.domain.GeradorCodigos;
import pleilist.app.domain.Hashtag;
import pleilist.app.domain.Item;
import pleilist.app.domain.Playlist;
import pleilist.app.domain.Utilizador;
import pleilist.app.domain.Video;
import pleilist.app.domain.catalogs.CatalogoBibliotecas;
import pleilist.app.domain.catalogs.CatalogoHashtags;
import pleilist.app.domain.catalogs.CatalogoPlaylists;
import pleilist.app.domain.catalogs.CatalogoVideos;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.facade.dto.Pair;

/**
 * Handler para criar playlists
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 * @author dco0001
 */
public class CriarPlayListHandler {

    private Utilizador corrente;
    private Playlist lista;
    private Item item;

    /**
     * Construtor da classe
     * @param s - sessao do utilizador a criar playlist
     */
    public CriarPlayListHandler(Sessao s) {
        this.corrente = s.getUtilizador();
    }

    /**
     * Inicia a criacao da lista
     * @param nomePlaylist - nome da lista
     */
    public void iniciaCriacao(String nomePlaylist) {
        this.lista = new Playlist(nomePlaylist);
    }

    /**
     * Entradas correntes na lista
     * @return lista de entradas
     */
    public List<Entrada> obterEntradasActuais() {
        return lista.getEntradas();
    }

    /**
     * Devolve a lista de videos com uma determinada hashtag.
     * @param tag hashTag de videos a procurar
     * @return uma lista de pares Titulo, Codigo
     */
    public List<Pair<String, String>> obterVideosComHashtag(String tag) {
        List<Video> disponiveis = new ArrayList<>(videosDisponiveis());
        List<Pair<String, String>> ls = new ArrayList<>();
        CatalogoHashtags catTag = CatalogoHashtags.getInstance();
        for (Video v : disponiveis) {
            List<Hashtag> tags = new ArrayList<>(v.getTags());
            if (tags.contains(catTag.getHashtag(tag))){
                Pair<String, String> par = new Pair<>(v.getTitulo(), v.getCodigo());
                ls.add(par);
            }
        }
        return ls;   
    }

    /**
     * Devolve uma lista dos videos disponiveis ao utilizador para adicionar 
     * a playlist
     * @return Videos da bibliotea geral + da privada
     */
    private List<Video> videosDisponiveis(){
        CatalogoBibliotecas catBib = CatalogoBibliotecas.getInstance();
        List<Video> bibGeral = new ArrayList<>(catBib.getBibliotecaGeral().getVideos());
        List<Video> bibPrivada = new ArrayList<>(catBib.getBibliotecaPrivada(corrente.getUsername()).getVideos());
        bibGeral.addAll(bibPrivada);
        return bibGeral;
    }

    /**
     * Seleciona o video a adicionar com um determinado c√≥digo.
     * @param codigo
     * @return se o video eh uma stream ou n√£o
     */  
    public Pair<Boolean,Boolean> indicarCodigo(String codigo) {
        boolean ehStream = false;
        boolean existe = false;
        List<Video> vidsDisponiveis = new ArrayList<>(videosDisponiveis());
        for (Video v: vidsDisponiveis) {
            if (v.getCodigo().equals(codigo)) {
                existe = true;
                Item video = new Item(v, lista.getDuracao());
                if (v instanceof Clip) {
                    video.setDuracao(((Clip) v).getDuracao()); 
                    lista.adicionarItem(video);
                    adicionarObservable(video);
                    finalizarPlaylist();
                } else {
                    ehStream = true;
                    item = video;
                }
            }
        }
        return new Pair<>(ehStream, existe);  
    }

    /**
     * Adiciona esta lista como observable de um video
     * @param video a que adicionar a lista como observable
     */
    private void adicionarObservable(Item video) {
      Video v = CatalogoVideos.getInstance().getVideo(video.getCodigo());
      v.addObserver(lista);
    }

    /**
     * Indica quanto tempo um stream deve ser reproduzido na playlist
     * @param duration - duracao de transmissao do video
     */
    public void indicaDuracao(Duration duration) {
        item.setDuracao(duration);
        lista.adicionarItem(item);
        adicionarObservable(item);
        finalizarPlaylist();
    }
    
    /**
     * Finaliza a playlist atribuindo um codigo ou substituindo uma iteraÁ„o anterior
     */
    private void finalizarPlaylist() {
        CatalogoPlaylists catPlay = CatalogoPlaylists.getInstance();
        String codigo = lista.getCodigo();
        if (codigo.equals("")) {
        	GeradorCodigos gerCod = GeradorCodigos.getInstance();
            codigo =  gerCod.gerarCodigoPlaylist();
            lista.setCodigo(codigo);
        }
        catPlay.inserirPlaylist(codigo, lista);
    }
    
    /**
     * Retorna os 10 videos mais vistos
     * @return lista de videos
     */
    public List<Entrada> mostrarTop10() {  
        List<Video> vids = CatalogoBibliotecas.getInstance().getBibliotecaGeral().getVideos();
        vids.stream()   
        .sorted(Comparator.comparing(Video::getVisualizacoes))  
        .limit(10)  
        .collect(Collectors.toList());  
        List<Entrada> top10 = new ArrayList<>();    
        for (Video v: vids) {   
            if (v instanceof Clip) {    
                Entrada entrada = new Entrada(v.getTitulo(), v.getCodigo(), ((Clip) v).getDuracao(), null);   
                top10.add(entrada); 
            } else {
                Entrada entrada = new Entrada(v.getTitulo(), v.getCodigo(), null, null);   
                top10.add(entrada);  
            }
        }   
        return top10;   
    }

}
