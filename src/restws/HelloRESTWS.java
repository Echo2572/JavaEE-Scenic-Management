package restws;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/helloworld")   //@Path��ʾ���������Դ��·��
public class HelloRESTWS {
	@GET    //@GET��ʾ��ӦHTTP ��get����
	@Produces("text/plain")   //@Produces����ע���ص�MIMEý�����ͣ�
	public String sayHello() {
	   return "Hello World";
	}

	@GET
	@Path("/sayHi")
	@Produces("text/plain") 
	public String sayHi(@QueryParam("name") String n) { 
		return "Hi " + n;
	}
}
