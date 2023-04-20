package ExternApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChuckNorrisApi {

       public ChuckNorrisDto fetchJoke() throws Exception {
           URL url = new URL("https://api.chucknorris.io/jokes/random");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        Gson gson = new Gson();
        ChuckNorrisModel joke = gson.fromJson(content.toString(), ChuckNorrisModel.class);

        System.out.println("Chuck Norris Joke:");
        System.out.println(joke.getValue());

        ChuckNorrisDto dto = new ChuckNorrisDto(joke.getValue());

        return dto;
    }



}
