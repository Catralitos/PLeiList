package pleilist.app.facade.dto;

import java.time.Duration;

/**
 * Facade de Item
 * Biblioteca que guarda videos
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Entrada {
  
  private String titulo;
  private String codigo;
  private Duration duracao;
  private Duration horaInicio;
  
  /**
   * Construtor da classe
   * @param titulo da entrada
   * @param codigo da entrada
   * @param duracao da entrada 
   * @param horaInicio da entrada na playlist
   */
  public Entrada(String titulo, String codigo, Duration duracao, Duration horaInicio) {
	  this.titulo = titulo;
	  this.codigo = codigo;
	  this.duracao = duracao;
	  this.horaInicio = horaInicio;
  }
  
  /**
   * Retorna o titulo da entrada
   * @return titulo da entrada
   */
  public String getTitulo() {
	  return titulo;
  }
  
  /**
   * Retorna o codigo da entrada
   * @return codigo da entrada
   */
  public String getCodigo() {
	  return codigo;
  }
  
  /**
   * Retorna a duracao da entrada
   * @return duracao da entrada
   */
  public Duration getDuracao() {
	  return duracao;
  }
  
  /**
   * Retorna hora de inicio da entrada
   * @return hora de inicio da entrada na playlist
   */
  public Duration getHoraInicio() {
      return horaInicio;
  }
  
  /**
   * Devolve uma representaao textual de duracao
   * @param duration a converter para texto
   * @return duration sobre HH:MM:SS
   */
  public String formatDuration(Duration duration) {
      if (duration != null) {
        long seconds = duration.getSeconds();
      long absSeconds = Math.abs(seconds);
      String positive = String.format(
          "%d:%02d:%02d",
          absSeconds / 3600,
          (absSeconds % 3600) / 60,
          absSeconds % 60);
      return seconds < 0 ? "-" + positive : positive;  
      } else {
          return "";
      }
      
  }
  
  /**
   * Representacao textual da entrada
   */
  public String toString() {
      return formatDuration(getHoraInicio()) + " " + getTitulo() + ": " + getCodigo() + " - " + formatDuration(getDuracao());
  }
   
}
