package org.apache.johnzon.jsr367;

import javax.json.bind.JsonContext;
import javax.json.bind.Marshaller;
import javax.json.bind.Unmarshaller;

public class JohnzonJsonContext implements JsonContext{

	@Override
	public Marshaller createMarshaller() {
		return new JohnzonMarshaller();
	}

	@Override
	public Unmarshaller createUnmarshaller() {
		return null;
	}

}
