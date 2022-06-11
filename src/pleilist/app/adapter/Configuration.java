package pleilist.app.adapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Gera as propriedades do programa
 * @author fc51035 - Gabriel Freitas
 * @author fc51779 - Ruhan Azevedo
 * @author fc51964 - Carlos Marques
 */
public class Configuration {
	
	private static Configuration INSTANCE = null;
	
	/**
	 * Devolve a Configuration
	 * @return unica instancia de Configuration
	 */
	public static Configuration getInstance() {
		if (INSTANCE == null) {
			 INSTANCE = new Configuration();
		}
		return INSTANCE;
	}
	
	private Properties prop = new Properties();
	
	/*
	 * Construtor de Configuration
	 */
	private Configuration() {
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.getCause();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Busca uma string de um ficheiro de propriedades
	 * Com properiedade chave
	 */
	public String getString(String chave) {
		return prop.getProperty(chave);
	}

	/*
     * Busca uma string[] de um ficheiro de propriedades
     * Com properiedade chave
     */
	public String[] getStringArray(String chave) {
		return getString(chave).split(";");
	}
	
	/*
	* Tenta Criar instancia da classe com nome className, se não conseguir, devolve o defaultObject
	*/
	@SuppressWarnings("unchecked")
	public <T> T createInstance(String className, T defaultObject) {
        Class<T> c = null;
        T ins = defaultObject;
        try {
            c = (Class<T>) Class.forName(className);
            try {
                Constructor<T> cons = c.getConstructor();
                ins = cons.newInstance();
                
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e1) {
            e1.getMessage();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        
        
        return ins;
    }
	/*
	* Busca o array que tem chave, depois cria instancias dos elementos desse array
	*/
	@SuppressWarnings("unchecked")
	public <T> Iterable<T> getInstances(String chave, T defaultObject) {
		return (Iterable<T>) Arrays.stream(this.getStringArray(chave)).map((x) -> this.createInstance(x, defaultObject));
	}
	
	/*
	* Cria instancias de chave
	*/
	public <T> T createInstanceFromKey(String chave, T defaultObject) {
		String className = this.getString(chave);
		return this.createInstance(className, defaultObject);
	}
	
}
