package pleilist.app.domain;

/**
 * Playlist de videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pleilist.app.facade.dto.Entrada;
import pleilist.app.domain.observe.VideoObservable;
import pleilist.app.domain.observe.PlaylistObserver;

public class Playlist implements PlaylistObserver{
    
    private String nome;
    private String codigo;
    private List<Item> lista;
    private Map<String, Hashtag> tags;
    private double classificacao;

    /**
     * Construtor da classe
     * @param nome - Nome da Playlist
     */
    public Playlist(String nome) {
        this.nome = nome;
        this.codigo = "";
        this.lista = new ArrayList<>();
        this.tags = new HashMap<>();
        this.classificacao = 0;
    }

    /**
     * Retorna o nome da playlist
     * @return nome da playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o codigo da playlist
     * @return codigo da playlist
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Muda o codigo da playlist
     * @param codigo a atribuir a playlist
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Adiciona um Item a playlist
     * @param item a adicionar
     */
    public void adicionarItem(Item item) {
        lista.add(item);
        List<Hashtag> listaTags = item.getTags();
        for (Hashtag h : listaTags) {
            tags.put(h.getNome(), h);
        }
    }

    /**
     * Retorna os items na playlist
     * @return lista de items
     */
    public List<Item> getItems(){
        return lista;
    }

    /**
     * Se existir, devolve o item da playlist com o codigo
     * @param codigo do item a procurar
     * @return optional ou com o item, ou vazio 
     */
    public Optional<Item> getItem(String codigo) {
        for (Item i : lista) {
            if (i.getCodigo().equals(codigo))
                return Optional.of(i);
        }
        return Optional.empty();
    }

    /**
     * Retorna os hashtags da playlist
     * @return lista de hashtags
     */
    public List<Hashtag> getTags(){
        return new ArrayList<>(tags.values());
    }
    
    /**
     * Retorna todos os codigos dos items da playlist
     * @return lista de codigos
     */
    public List<String> getCodigos(){
    	ArrayList<String> codigos = new ArrayList<>();
    	for (Item i : lista) {
    		codigos.add(i.getCodigo());
    	}
    	return codigos;
    }
    
    /**
     * Averigua se a plyalist tem uma certa tag
     * @param tag a procurar na lista
     * @return true se a tag pertencer ah playlist
     */
    public boolean temTag(String tag) {
        return tags.containsKey(tag);
    }
    
    /**
     * Retorna os items desta lista como Entradas
     * @return lista de entradas
     */
    public List<Entrada> getEntradas(){
        ArrayList<Entrada> listaE = new ArrayList<>();
        for (Item i: lista) {
            listaE.add(i.getEntrada());
        }
        return listaE;
    }
    
    /**
     * Retorna a duracao desta lista
     * @return duracao da playlist
     */
    public Duration getDuracao() {
        Duration d = Duration.ofSeconds(0);
        for (Item i : lista) {
            d = d.plus(i.getDuracao());
        }
        return d;
    }
    
    /**
     * Retorna a classificacao desta playlist
     * @return classificacao media da playlist
     */
    public double getClassificacao() {
        return classificacao;
    }
    
    /**
     * Faz um update a playlist
     * @param observable que envia update
     * @param evento a realizar
     */
    @Override
    public void update(VideoObservable o) {
        if (o instanceof Video) {
            
            List<Double> listaClass = new ArrayList<>();
            for (Item i : lista) {
                listaClass.add(i.getClassificacao());
            }
            int length = lista.size();     
            double count = 0;
            for (Double c  : listaClass) {
                count += c;
            }
            classificacao = round(count/length, 1);
            //TODO tirar isto para debug
            System.out.println("A nova pontuacao da lista " + this.nome + " eh " + classificacao);
        }
    }
    
    /**
     * Arredonda um numero
     * @param value - numero a arredondar
     * @param precision - precisao, numero de algarimos decimais
     * @return double arredondado
     */
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
