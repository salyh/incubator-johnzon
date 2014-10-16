package javax.json.bind;

import java.io.Reader;
import java.io.Writer;

import javax.json.JsonStructure;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

public interface Unmarshaller {

		public Unmarshaller setCloseStreams(boolean closeStreams);
		
		public Object unmarshal(String jsonString);
		public Object unmarshal(String jsonString, Class clazz);
		
		public Object unmarshal(Reader json);
		public Object unmarshal(Reader json, Class clazz);
		
		public Object unmarshal(JsonParser parser);
		public Object unmarshal(JsonParser parser, Class clazz);
		
		public Object unmarshal(JsonStructure structure);
		public Object unmarshal(JsonStructure structure, Class clazz);
	
}
