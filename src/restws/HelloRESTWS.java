package restws;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/helloworld")   //@Path表示访问这个资源的路径
public class HelloRESTWS {
	@GET    //@GET表示响应HTTP 的get方法
	@Produces("text/plain")   //@Produces，标注返回的MIME媒体类型；
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
