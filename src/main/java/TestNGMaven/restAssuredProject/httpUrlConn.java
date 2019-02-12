package TestNGMaven.restAssuredProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class httpUrlConn {




	static int getCountries(String s, int p) throws Exception{
		HttpURLConnection c = null;
		int status=0;
		String inputLine;
		String inline = null;
		int count=0;
		try {
			URL u = new URL("https://jsonmock.hackerrank.com/api/countries/search?name="+s);
			c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-length", "0");
			c.setUseCaches(false);
			c.setAllowUserInteraction(false);
			c.connect();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(c.getInputStream()));

			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String stringResp= new String(response);
			JsonParser parser = new JsonParser();
			JsonObject jsonObj = (JsonObject) parser.parse(stringResp);

			JsonArray data= jsonObj.getAsJsonArray("data");
			for (JsonElement pa : data) {
				JsonObject pops = pa.getAsJsonObject();
				int     population     = pops.get("population").getAsInt();
				if(population>p){
					count++;
				}

			}
		}
		catch(Exception ex){
		}
		return count;

	}


	public static void main(String[] args) throws IOException{


		try {
			int	res = getCountries("s", 8);
			System.out.println(res);
		} catch (Exception e) {

		}

	}
}

