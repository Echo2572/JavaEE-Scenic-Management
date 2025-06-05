package restws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/thirdService")
public class ThirdServiceRESTWS {
	@GET    //@GET��ʾ��ӦHTTP ��get����
	@Produces("text/plain")   //@Produces����ע���ص�MIMEý�����ͣ�
	public String sayHello() {
	   return "Hello World";
	}

	@GET
    @Path("/getWeather")
    @Produces("application/json")
    public List<WeatherInfo> weatherService(@QueryParam("spot") String spot) { 
        List<WeatherInfo> weatherList = new ArrayList<WeatherInfo>();

        // ģ�ⷵ���������������
        for (int i = 1; i <= 3; i++) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setDate("2024-06-" + (10 + i));
            weatherInfo.setDescription("����");
            weatherInfo.setTemperature("2"+i+"��C");

            weatherList.add(weatherInfo);
        }

        return weatherList;
    }
	
	@GET
    @Path("/getRoutes")
    @Produces("application/json")
    public List<RouteInfo> routeService(@QueryParam("spot") String spot) {
        List<RouteInfo> routeList = new ArrayList<RouteInfo>();

        // ģ�ⷵ��������ͨ·������
        for (int i = 1; i <= 3; i++) {
            RouteInfo routeInfo = new RouteInfo();
            routeInfo.setRouteName("·�� " + i);
            routeInfo.setDescription("�� " + i+" ��·�߱ȽϽ��������߱�·��");
            routeInfo.setDuration("�������̽�����: " + (30 * i) + " ����");

            routeList.add(routeInfo);
        }

        return routeList;
    }
}
