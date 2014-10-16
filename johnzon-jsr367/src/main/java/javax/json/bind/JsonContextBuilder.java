package javax.json.bind;

public class JsonContextBuilder {
	
	public JsonContextBuilder setScope(Class... clazzes){
		return this;
	}
	
	public JsonContextBuilder excludeField(String field, Class clazz)	{
		return this;
	}
	
	public JsonContextBuilder addFilter(ContextFilter contextFilter)	{
		return this;
	}
	
	public JsonContext build()	{
		return null;
	}

}
