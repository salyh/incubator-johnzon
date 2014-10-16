package javax.json.bind;

import javax.json.bind.spi.JsonContextProvider;

public class JsonContext {
	
	private JsonContextProvider provider;

	public static JsonContext newInstance(){
		return null;
	}
	
	public static JsonContext newInstance(String className){
		return null;
	}
	
	
	public Marshaller createMarshaller(){
		return provider.createMarshaller();
	}
	
	public Unmarshaller createUnmarshaller(){
		return provider.createUnmarshaller();
	}
}
