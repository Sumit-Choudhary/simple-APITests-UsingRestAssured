package TestNGMaven.restAssuredProject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApacheHttpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloseableHttpClient httpClient=HttpClients.createDefault();
		try {
			URI uri =new URI("https://jsonmock.hackerrank.com/api/countries/search?name=s");
			HttpGet httpGet=new HttpGet(uri);
			HttpResponse httpResp=httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResp.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);

			JsonParser parser=new JsonParser();
			JsonObject jObject=(JsonObject) parser.parse(apiOutput);
			JsonArray jsArray =jObject.getAsJsonArray("data");
			for (int i = 0; i < jsArray.size(); i++) {
				JsonElement element=	jsArray.get(i);
				JsonObject currJsonObj=element.getAsJsonObject();
				System.out.println(currJsonObj.get("population").getAsInt());
			}

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
