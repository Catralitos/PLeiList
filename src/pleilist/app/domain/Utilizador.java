package pleilist.app.domain;

import pleilist.app.domain.catalogs.CatalogoUtilizadores;

/**
 * Utilizador do pLeiList
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Utilizador implements Cloneable {

    private String username;

    /**
     * Construtor da classe
     * @param username do utilizador
     */
    public Utilizador(String username) {
        this.username = username;
    }

    /**
     * Devolve o username do utilizador
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retorna se eh curador
     * Este metodo existe par anao importar o domain na classe sessao
     * E assim tratar as excecoes so com facade
     * @return true se instancia de curador
     */
    public boolean ehCurador() {
        return (this instanceof Curador);
    }
    
    /**
     * Autentica o utilizador
     * @param username do utilizador
     * @param password do utilizador  
     * @return true se o utilizador existe no catalogo
     */
    public boolean autenticaCom(Utilizador utilizador, String username, String password) {
        CatalogoUtilizadores catUsers = CatalogoUtilizadores.getInstance();
        return catUsers.autentica(utilizador, username,password);
    }

    /*
     * Devolve um clone do utilizador
     */
    public Utilizador clone() {
        try {
            Utilizador result = (Utilizador) super.clone();
            result.username = this.username;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    /**
     * Averigua se dois utilizadores sao identicos
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
        Utilizador other = (Utilizador) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

}
