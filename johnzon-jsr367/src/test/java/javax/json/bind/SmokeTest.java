package javax.json.bind;

import org.junit.Assert;
import org.junit.Test;

import test.a.b.Test.PlainSimple;

public class SmokeTest {

	@Test
	public void test1(){
		PlainSimple ps = new PlainSimple();
		ps.setMyInt(42);
		ps.setMyString("Hello World!");
				
		JsonContextBuilder builder = Jsonb.createJsonContextBuilder();
		builder.ignoreAllAnnotations(true);
		JsonContext context = builder.build();
		
		Marshaller m = context.createMarshaller();
		String json = m.marshal(ps);
		Assert.assertNotNull(json);
		System.out.println(json);
		
	}
	
}
