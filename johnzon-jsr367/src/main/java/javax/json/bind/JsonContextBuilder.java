package javax.json.bind;

import java.lang.annotation.Annotation;

public interface JsonContextBuilder {

	public abstract JsonContextBuilder setScope(Class... clazzes);

	//interpret classNames as regex? 
	public abstract JsonContextBuilder setScope(String classNames);

	public abstract JsonContextBuilder excludeClass(Class clazz);

	//interpret classNames as regex? 
	public abstract JsonContextBuilder excludeClasses(String classNames);

	//interpret field as regex? 
	public abstract JsonContextBuilder excludeField(String field, Class clazz);

	//exclude field for all classes, interpret field as regex? 
	public abstract JsonContextBuilder excludeField(String field);

	public abstract JsonContextBuilder addFilter(ContextFilter contextFilter);

	public abstract JsonContextBuilder ignoreAllAnnotations(
			boolean ignoreAnnotations);

	public abstract JsonContextBuilder ignoreAnnotations(
			Annotation... annotations);

	public abstract JsonContext build();

}