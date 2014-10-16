package javax.json.bind.spi;

import javax.json.bind.Marshaller;
import javax.json.bind.Unmarshaller;

public interface JsonContextProvider {

	public Marshaller createMarshaller();	
	public Unmarshaller createUnmarshaller();
	
}
