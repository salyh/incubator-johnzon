package javax.json.bind;

public interface JsonContext {
	
	public Marshaller createMarshaller();
	public Unmarshaller createUnmarshaller();

}
