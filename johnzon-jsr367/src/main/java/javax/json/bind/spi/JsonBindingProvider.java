package javax.json.bind.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import javax.json.bind.JsonBindingException;
import javax.json.bind.JsonContextBuilder;

public abstract class JsonBindingProvider {

	private static final String DEFAULT_PROVIDER = System.getProperty("java.json.bind.default-provider-class");
	
	public abstract JsonContextBuilder createJsonContextBuilder();

	public static JsonBindingProvider provider() {
		ServiceLoader<JsonBindingProvider> loader = ServiceLoader.load(JsonBindingProvider.class);

		Iterator<JsonBindingProvider> it = loader.iterator();

		if (it.hasNext()) {
			return it.next();
		}
		
		if(DEFAULT_PROVIDER == null) {
			throw new JsonBindingException("No default provider defined");
		}
			
		
		try {
			Class<?> clazz = Class.forName(DEFAULT_PROVIDER);
			return (JsonBindingProvider) clazz.newInstance();
		} catch (ClassNotFoundException x) {
			throw new JsonBindingException("Provider " + DEFAULT_PROVIDER + " not found", x);

		} catch (Exception x) {
			throw new JsonBindingException("Provider " + DEFAULT_PROVIDER + " could not be instantiated: " + x, x);
		}
	}
}
