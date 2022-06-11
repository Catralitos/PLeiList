package pleilist.app.adapter;

/**
 * Fabrica de EnderecoException
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class EnderecoExceptionFactory {

	@SuppressWarnings("rawtypes")
	private static EnderecoExceptionFactory INSTANCE = null;
	
	private EnderecoException defaultAdapter = new UrlCheckerAdapter();
	private EnderecoException ee;
	
	/**
	 * Devolve a EnderecoExceptionFactory
	 * @return a unica instancia desta factory
	 */
	@SuppressWarnings("rawtypes")
	public static EnderecoExceptionFactory getInstance() {
		if (INSTANCE == null) { // Lazy Loading
			 INSTANCE = new EnderecoExceptionFactory();
		}
		return INSTANCE;
	}
	
	
	/**
	 * Construtor de EnderecoExceptionFactory
	 */
	@SuppressWarnings("unused")
	private EnderecoExceptionFactory() {
		Configuration conf = Configuration.getInstance();
	    ee = conf.createInstanceFromKey("adapters", defaultAdapter);
	}
	
	/**
	 * Retorna um EnderecoException, gerado de acordo com o properties.
	 * @return
	 */
	public EnderecoException getEnderecoException() {
	    return ee;
	}
	
}