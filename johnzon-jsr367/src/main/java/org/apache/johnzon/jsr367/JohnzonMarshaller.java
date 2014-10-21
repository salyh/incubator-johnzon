package org.apache.johnzon.jsr367;

import java.io.StringWriter;
import java.io.Writer;

import javax.json.JsonStructure;
import javax.json.bind.Marshaller;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

public class JohnzonMarshaller implements Marshaller {

	@Override
	public Marshaller setPrettyPrinting(boolean prettyPrint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Marshaller setProperty(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String marshal(Object object) {
        StringWriter sw = new StringWriter();
		new MapperBuilder().build().writeObject(object, sw);
		return sw.toString();
		
		
	}

	@Override
	public void marshal(Object object, Writer writer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void marshal(Object object, JsonGenerator jsonGenerator) {
		// TODO Auto-generated method stub

	}

	@Override
	public JsonParser getJsonParser(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonStructure getJsonStructure(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
