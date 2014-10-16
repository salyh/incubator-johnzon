package javax.json.bind;

public class Jsonb {

	public static String marshal(Object object){
		return JsonContext.newInstance().createMarshaller().marshal(object);
	}
	public static Object unmarshal(String jsonString, Class clazz)
	{
		return JsonContext.newInstance().createUnmarshaller().unmarshal(jsonString, clazz);
	}
	
}
