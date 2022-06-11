package pleilist.app.facade.handlers;

import java.time.Duration;

import pleilist.app.adapter.EnderecoException;
import pleilist.app.adapter.EnderecoExceptionFactory;
import pleilist.app.domain.Biblioteca;
import pleilist.app.domain.Clip;
import pleilist.app.domain.GeradorCodigos;
import pleilist.app.domain.Hashtag;
import pleilist.app.domain.Stream;
import pleilist.app.domain.Utilizador;
import pleilist.app.domain.Video;
import pleilist.app.domain.catalogs.CatalogoBibliotecas;
import pleilist.app.domain.catalogs.CatalogoHashtags;
import pleilist.app.domain.catalogs.CatalogoVideos;
import pleilist.app.facade.Sessao;

/**
 * Classe para adicionar videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 * @author dco0001
 */
public class AdicionarVideoHandler {

    private Video vCorrente;
    private Utilizador corrente;

    public AdicionarVideoHandler(Sessao s) {
        this.corrente = s.getUtilizador();
    }

    /**
     * Inicia a adicao do video.
     */
    public void iniciarAdicionar() {
        //DO NOTHING
    }

    /**
     * Define o tipo de Video que vai ser criado de acordo com o valor
     * do parametro passado. Se for true cria um Video do tipo Clip
     * caso contrario cria um Video do tipo Stream.
     * @param isClip indica se o Video eh Clip ou Stream
     */
    public void definirComoClip(boolean isClip) {
        if(isClip) {
            this.vCorrente = new Clip();
        }else {
            this.vCorrente = new Stream();
        }
    }

    /**
     * Indica o conteudo do Video, ou seja, o respetivo titulo
     * e endereco pertencentes ao Video.
     * @param title titulo do Video
     * @param address endereco do Video
     */
    public boolean indicaVideo(String title, String address) {
        EnderecoExceptionFactory fabrica = EnderecoExceptionFactory.getInstance();
        EnderecoException ee = fabrica.getEnderecoException();
        if (ee.existeEndereco(address)) {
            this.vCorrente.setTitulo(title);
            this.vCorrente.setEndereco(address); 
            return true;
        }
        return false;
    }

    /**
     * Indica a duracao do Video corrente, caso
     * este seja do tipo Clip.
     * @param duration duracao do Video
     */
    public void indicaDuracao(Duration duration) {
        if(vCorrente instanceof Clip) {
            Clip cl = (Clip) this.vCorrente;
            cl.adicionarDuracao(duration);
        }
    }

    /**
     * Adiciona um hashtag ao Video caso este
     * nao exista.
     * @param tag hashtag adicionado ao Video
     */
    public void indicaTag(String tag) {
        CatalogoHashtags catTag = CatalogoHashtags.getInstance();
        Hashtag h = catTag.getHashtag(tag);
        if(h == null) {
            h = new Hashtag(tag);
            catTag.inserirHashtag(tag, h);
        }
        this.vCorrente.adicionarTag(h);        
    }

    /**
     * Termina a adicao do Video de acordo com a sua privacidade.
     * @param ePublico indica se o Video eh publico ou privado
     * @return codigo do Video criado
     */
    public String defineComoPublico(boolean ePublico) {
        CatalogoBibliotecas catBib = CatalogoBibliotecas.getInstance();
        CatalogoVideos catVid = CatalogoVideos.getInstance();
        GeradorCodigos gerCod = GeradorCodigos.getInstance();
        this.vCorrente.setCodigo(gerCod.gerarCodigoVideo());
        String codigo = this.vCorrente.getCodigo();
        catVid.inserirVideo(codigo, this.vCorrente);
        if(ePublico) {
            Biblioteca bibGeral = catBib.getBibliotecaGeral();
            bibGeral.adicionarVideo(codigo, this.vCorrente);
        }else {
            Biblioteca bibPrivada = catBib.getBibliotecaPrivada(this.corrente.getUsername());
            bibPrivada.adicionarVideo(codigo, this.vCorrente);
        }
        return codigo;
    }

}
