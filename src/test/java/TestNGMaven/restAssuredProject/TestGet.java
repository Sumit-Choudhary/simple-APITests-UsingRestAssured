package TestNGMaven.restAssuredProject;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
public class TestGet {

	private Response resp = null; //Response object
	private JsonPath jp = null; //JsonPath object

	//@Before
	public void setUp() {
		RestUtil.setBaseURI("https://jsonplaceholder.typicode.com");

	}

	@Test
	public void getForDsrdUserNo() {
		/*RestUtil.createGetPathForReqdPg("2");
		resp=RestUtil.getResponse();
		 */
		resp=given().
				when().
				get("todos/1")
				.then()

				.extract()
				.response()

				;
		String contentType=resp.getContentType();
		System.out.println(contentType);

		ResponseBody body= resp.getBody();

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = resp.jsonPath();




		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String title = jsonPathEvaluator.get("title");

		// Let us print the city variable to see what we got
		System.out.println("Title received from Response " + title);

		//Object mapper is used when we dont specify content type in the response
		ToDos todos=body.as(ToDos.class,ObjectMapperType.GSON);

		//We need to specify the object type in the response
		//ToDos todos=body.as(ToDos.class);

		System.out.println(todos.getTitle());

	}

	@Test
	public void testPOSTmethods() {
		Posts postClass=new Posts();
		postClass.setTitle("newtitle");
		postClass.setBody("bar");
		postClass.setUserId("32");

		Response response=given().
				accept(ContentType.JSON).
				body("{\"title\": \"newtitle\",\"body\": \"2000\",\"userId\": \"32\"}").
				//body(postClass).
				when().
				post("http://jsonplaceholder.typicode.com/posts/");

		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		response=given().
				accept(ContentType.JSON).
				body(postClass).
				when().
				post("http://jsonplaceholder.typicode.com/posts/");

		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
	}
	@Test
	public void testUsingApacheGet() throws URISyntaxException, ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		URI uri= new URIBuilder().setScheme("https")
				.setHost("jsonplaceholder.typicode.com")
				.setPath("todos/1")
				.build();
		HttpGet httpGet=new HttpGet(uri);
		CloseableHttpResponse response= httpClient.execute(httpGet);
		System.out.println(response.getStatusLine().getStatusCode());
	}

	@Test
	public void testUsingApachePost() throws URISyntaxException, ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		URI uri= new URIBuilder().setScheme("http")
				.setHost("jsonplaceholder.typicode.com")
				.setPath("posts/")
				.build();
		HttpPost httpPost=new HttpPost(uri);
		List<NameValuePair> params= new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("title","newtitle"));
		params.add(new BasicNameValuePair("body","2000"));
		params.add(new BasicNameValuePair("userId","32"));
		httpPost.setEntity(new UrlEncodedFormEntity(params));

		CloseableHttpResponse response= httpClient.execute(httpPost);

		System.out.println(response.getStatusLine().getStatusCode());

	}

}
