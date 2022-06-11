package pleilist.app;

import java.util.Optional;

import pleilist.app.domain.Utilizador;
import pleilist.app.domain.catalogs.CatalogoUtilizadores;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.handlers.AdicionarVideoHandler;
import pleilist.app.facade.handlers.CriarPlayListHandler;
import pleilist.app.facade.handlers.PlaylistAleatoriaHandler;
import pleilist.app.facade.handlers.RegistarUtilizadorHandler;
import pleilist.app.facade.handlers.VerPlaylistHandler;

/**
 * Esta é a classe do sistema.
 */
public class PleiList {

    public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler();
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username do utilizador
	 * @param password do utilizador 
	 * @return optional of sessao de utilizador autenticou ou empty se nao
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		CatalogoUtilizadores catUsers = CatalogoUtilizadores.getInstance();

		for (Utilizador u: catUsers.getUtilizadores()) {
			if (u.autenticaCom(u, username, password)) {
				return Optional.of(new Sessao(u));
			}
		}
		return Optional.empty();
	}

	public AdicionarVideoHandler getAdicionarVideoHandler(Sessao s) {
		return new AdicionarVideoHandler(s);
	}

	public CriarPlayListHandler getCriarPlayListHandler(Sessao s) {
		return new CriarPlayListHandler(s); 
	}

	public VerPlaylistHandler getVerPlaylistHandler(Sessao s) {
		return new VerPlaylistHandler(s); 
	}

    public PlaylistAleatoriaHandler getPlaylistAleatoriaHandler(Sessao s) {
        return new PlaylistAleatoriaHandler(s); 
    }
	
}
