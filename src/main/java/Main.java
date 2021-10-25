import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Main {
    public static String URL = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) {

        AsyncHttpClient httpClient = new AsyncHttpClientImpl();
        Response processResponse = new Response();
        MyException processException = new MyException();

        Message postMessage = new Message();
        postMessage.setId(null);
        postMessage.setUserId(1);
        postMessage.setTitle("My post request title");
        postMessage.setBody("My post request body");

        Message putMessage = new Message();
        putMessage.setId(100);
        putMessage.setUserId(100);
        putMessage.setTitle("My put request title");
        putMessage.setBody("My put request body");

        String postData = "";
        String putData = "";

        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 101; i <= 103; i++) {
            String getUrl = URL + "/" + i;

            httpClient.getAsync(getUrl,
                    Map.of("Accept", "application/json"),
                    processResponse,
                    processException);
        }

        for (int i = 1; i <= 3; i++) {
            postMessage.setTitle("My POST request title " + i);
            postMessage.setBody("My POST request body " + i);

            try {
                postData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(postMessage);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            httpClient.postAsync(URL,
                    Map.of("Accept", "application/json",
                            "Content-Type", "application/json; charset=utf-8"),
                    postData,
                    processResponse,
                    processException);
        }

        for (int i = 52; i <= 54; i++) {
            putMessage.setTitle("My PUT request title " + i);
            putMessage.setBody("My PUT request body " + i);
            putMessage.setId(i);
            putMessage.setUserId(i);

            try {
                putData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(putMessage);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            String putUrl = URL + "/" + i;

            httpClient.putAsync(putUrl,
                    Map.of("Accept", "application/json",
                            "Content-Type", "application/json; charset=utf-8"),
                    putData,
                    processResponse,
                    processException);
        }

        for (int i = 32; i <= 34; i++) {

            String deleteUrl = URL + "/" + i;
            String deleteData = "";

            httpClient.deleteAsync(deleteUrl,
                    Map.of("Accept", "application/json",
                            "Content-Type", "application/json; charset=utf-8"),
                    deleteData,
                    processResponse,
                    processException);
        }
    }
}