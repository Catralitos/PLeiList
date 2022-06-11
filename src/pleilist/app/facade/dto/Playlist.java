package pleilist.app.facade.dto;

/**
 * Facade de playlist
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Playlist {
	
	private String nome;
	private String codigo;
	private double pontuacao;
	
	/**
	 * Construtor da classe
	 * @param nome da playlist
	 * @param codigo da playlist
	 * @param pontuacao da playlist
	 */
	public Playlist(String nome, String codigo, double pontuacao) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.pontuacao = pontuacao;
	}

	/**
	 * Averigua se duas playlists sao identicas
	 */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Playlist other = (Playlist) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (Double.doubleToLongBits(pontuacao) != Double.doubleToLongBits(other.pontuacao)) {
            return false;
        }
        return true;
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
	 * Retorna a pontuacao da playlist
	 * @return pontuacao da playlist
	 */
	public double getPontuacao() {
		return pontuacao;
	}
	
}
