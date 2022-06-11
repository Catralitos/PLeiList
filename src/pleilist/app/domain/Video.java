package pleilist.app.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pleilist.app.domain.observe.VideoObservable;

/**
 * Video do pLeiList
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public abstract class Video extends VideoObservable{

    private String titulo = "Sem titulo";
    private String endereco = "Sem endereco";
    private String codigo = "";
    private int visualizacoes = 0;
    private Map<String,Classificacao> classificacoes = new HashMap<>();
    private Map<String, Hashtag> tags = new HashMap<>();
       
    
    /**
     * Retorna o titulo do video
     * @return titulo do video
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Muda o titulo do video
     * @param titulo a atribuir ao video
     */
    public void setTitulo(String titulo) {
    	this.titulo = titulo;
    }

    /**
     * Retorna o endereco do video
     * @return endereco do video
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Muda o endereco do video
     * @param endereco a atribuir ao video
     */
    public void setEndereco(String endereco) {
    	this.endereco = endereco;
    }
    
    /**
     * Retorna o codigo do video
     * @return codigo do video
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Muda o codigo do video
     * @param codigo a atribuir ao video
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Retorna os hashtags do video
     * @return lista de hashtags
     */
    public List<Hashtag> getTags(){
        return new ArrayList<>(tags.values());
    }
    
    /**
     * Adiciona um hashtag ao video
     * @param tag a adicionar
     */
    public void adicionarTag(Hashtag tag) {
        this.tags.put(tag.getNome(), tag);
    }
    
    /**
     * Adiciona uma classificao ao video
     * @param username do utilizador a classificar
     * @param estrelas atribuidas ao video
     */
    public void adicionarClassificacao(String username, int estrelas){
        if (estrelas >= 0 && estrelas <= 5) {
        	Classificacao rating = new Classificacao(estrelas);
            classificacoes.put(username, rating);
            notifyObservers();
        } 
    }
    
    /**
     * Retorna as classificacoes do video
     * @return lista de classificacoes do video
     */
    public List<Classificacao> getClassificacoes(){
        return new ArrayList<>(classificacoes.values());
    }
    
    /**
     * Retorna a media das classificacoes do video
     * @return classificacao do video (em estrelas)
     */
    public double getClassificacao() {
        List<Classificacao> lista = getClassificacoes();
        if (lista.isEmpty()) {
            return 0;
        }
        int length = lista.size();     
        int count = 0;
        for (Classificacao c  : lista) {
            count += c.getClassificacao();
        }
        return round(count/length, 1);
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
    
    /**
     * Retorna as visualizacoes do video
     * @return numero de vezes que video foi visto
     */
    public int getVisualizacoes() {
        return visualizacoes;
    }
    
    /**
     * Adiciona uma visualizacao ao video
     */
    public void aumentarVisualizacao() {
        visualizacoes++;
    }
    
}
