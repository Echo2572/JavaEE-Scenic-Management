package restws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/thirdService")
public class ThirdServiceRESTWS {
	@GET    //@GET表示响应HTTP 的get方法
	@Produces("text/plain")   //@Produces，标注返回的MIME媒体类型；
	public String sayHello() {
	   return "Hello World";
	}

	@GET
    @Path("/getWeather")
    @Produces("application/json")
    public List<WeatherInfo> weatherService(@QueryParam("spot") String spot) { 
        List<WeatherInfo> weatherList = new ArrayList<WeatherInfo>();

        // 模拟返回三天的天气数据
        for (int i = 1; i <= 3; i++) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setDate("2024-06-" + (10 + i));
            weatherInfo.setDescription("晴朗");
            weatherInfo.setTemperature("2"+i+"°C");

            weatherList.add(weatherInfo);
        }

        return weatherList;
    }
	
	@GET
    @Path("/getRoutes")
    @Produces("application/json")
    public List<RouteInfo> routeService(@QueryParam("spot") String spot) {
        List<RouteInfo> routeList = new ArrayList<RouteInfo>();

        // 模拟返回三条交通路线数据
        for (int i = 1; i <= 3; i++) {
            RouteInfo routeInfo = new RouteInfo();
            routeInfo.setRouteName("路线 " + i);
            routeInfo.setDescription("第 " + i+" 条路线比较近，建议走本路线");
            routeInfo.setDuration("整个过程将持续: " + (30 * i) + " 分钟");

            routeList.add(routeInfo);
        }

        return routeList;
    }
}
