package javax.json.bind;

public interface ContextFilter {
	
	public boolean include(Class clazz, String field);
	

}
