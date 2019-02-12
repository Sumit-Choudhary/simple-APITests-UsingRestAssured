package TestNGMaven.restAssuredProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class httpUrlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL u=null;
		HttpURLConnection con =null;
		String responseInStringFormat=null;
		StringBuffer strBfr=new StringBuffer();
		try {
			u=new URL("https://jsonmock.hackerrank.com/api/countries/search?name=s");
			con=(HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
			while((responseInStringFormat=br.readLine())!=null) {
				strBfr.append(responseInStringFormat);
			}

			responseInStringFormat=new String(strBfr);
			JsonParser parser=new JsonParser();
			JsonObject jsObject=(JsonObject) parser.parse(responseInStringFormat);
			JsonArray jsArry=jsObject.getAsJsonArray("data");
			for(JsonElement jselm:jsArry) {
				System.out.println(jselm);

			}
			con.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
