package pleilist.app.domain;

/**
 * Classificacao de um video
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Classificacao {

	private int estrelas;
	
	/**
	 * Construtor da classe
	 * @param estrelas - Classificacao do video
	 */
	public Classificacao(int estrelas) {
		this.estrelas = estrelas;
	}
	
	/**
	 * Devolve as estrelas que representam esta classificacao
	 * @return numero de estrelas
	 */
	public int getClassificacao() {
		return estrelas;
	}
}
