package javax.json.bind;

import java.io.Writer;

import javax.json.JsonStructure;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

public interface Marshaller {
	
	public Marshaller setPrettyPrinting(boolean prettyPrint);
	public Marshaller setProperty(String property);

	public String marshal(Object object);
	public void marshal(Object object, Writer writer);
	public void marshal(Object object, JsonGenerator jsonGenerator);
	public JsonParser getJsonParser(Object object);
	public JsonStructure getJsonStructure(Object object);
	

}
