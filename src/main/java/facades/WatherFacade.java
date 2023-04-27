package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.WeatherDTO;
import dtos.WeatherRemoteDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WatherFacade {

    private static WatherFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private WatherFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */


    public static WatherFacade getWatherFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WatherFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public String fetchData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json"); // Add this line
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("Error in fetchData" + e.getMessage());
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public WeatherRemoteDTO createWeatherRemoteDTO(String input) {
      return GSON.fromJson(input, WeatherRemoteDTO.class);
    }

}
