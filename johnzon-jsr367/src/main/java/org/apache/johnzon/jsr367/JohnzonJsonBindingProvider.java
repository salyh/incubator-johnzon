package org.apache.johnzon.jsr367;

import java.lang.annotation.Annotation;

import javax.json.bind.ContextFilter;
import javax.json.bind.JsonContext;
import javax.json.bind.JsonContextBuilder;
import javax.json.bind.spi.JsonBindingProvider;


public class JohnzonJsonBindingProvider extends JsonBindingProvider {

	@Override
	public JsonContextBuilder createJsonContextBuilder() {
		return new JohnzonJsonContextBuilder();
	}
	
	
}
