package pleilist.app.domain.catalogs;

import java.util.Map;

import pleilist.app.domain.Biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Catalogo que guarda Bibliotecas
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class CatalogoBibliotecas {

    private static CatalogoBibliotecas singleInstance = null; 

    private Biblioteca bibGeral;
    private Map<String,Biblioteca> mapaPrivs;

    /**
     * Construtor de CatalogoBibliotecas
     */
    private CatalogoBibliotecas() {
        this.bibGeral = new Biblioteca();
        this.mapaPrivs = new HashMap<>();
    }

    /**
     * Retorna a unica instancia de CatalogoBiblioteca
     * @return Catalogo de Bibliotecas
     */
    public static CatalogoBibliotecas getInstance() {
        if (singleInstance == null) 
            singleInstance = new CatalogoBibliotecas(); 

        return singleInstance;
    }
    
    /**
     * Retorna a Biblioteca Geral do sistema
     * @return Biblioteca Geral
     */
    public Biblioteca getBibliotecaGeral() {
        return bibGeral;
    }
    
    /**
     * Retorna as Bibliotecas Privadas
     * @return Lista de Bibliotecas Privadas
     */
    public List<Biblioteca> getBibliotecasPrivadas(){
        return new ArrayList<>(mapaPrivs.values());
    }
    
    /**
     * Retorna a Biblioteca Privada de um utilizador
     * @param username - Utilizador a procurar a Biblioteca
     * @return Biblioteca Privada
     */
    public Biblioteca getBibliotecaPrivada(String username) {
        return mapaPrivs.get(username);
    }
    
    /**
     * Insere uma biblioteca no catalogo
     * @param username - Utilizador a receber uma biblioteca
     */
    public void inserirBiblioteca(String username) {
        mapaPrivs.put(username, new Biblioteca());
    }
}
