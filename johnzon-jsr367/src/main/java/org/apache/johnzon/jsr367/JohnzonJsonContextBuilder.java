package org.apache.johnzon.jsr367;

import java.lang.annotation.Annotation;

import javax.json.bind.ContextFilter;
import javax.json.bind.JsonContext;
import javax.json.bind.JsonContextBuilder;

public class JohnzonJsonContextBuilder implements JsonContextBuilder{
	

	@Override
	public JsonContextBuilder setScope(Class... clazzes) {
		return this;
	}

	@Override
	public JsonContextBuilder setScope(String classNames) {
		return this;
	}

	@Override
	public JsonContextBuilder excludeClass(Class clazz) {
		return this;
	}

	@Override
	public JsonContextBuilder excludeClasses(String classNames) {
		return this;
	}

	@Override
	public JsonContextBuilder excludeField(String field, Class clazz) {
		return this;
	}

	@Override
	public JsonContextBuilder excludeField(String field) {
		return this;
	}

	@Override
	public JsonContextBuilder addFilter(ContextFilter contextFilter) {
		return this;
	}

	@Override
	public JsonContextBuilder ignoreAllAnnotations(boolean ignoreAnnotations) {
		return this;
	}

	@Override
	public JsonContextBuilder ignoreAnnotations(Annotation... annotations) {
		return this;
	}

	@Override
	public JsonContext build() {
		return new JohnzonJsonContext();
	}

}
