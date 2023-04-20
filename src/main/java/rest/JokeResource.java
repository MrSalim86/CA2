package rest;

import ExternApi.ChuckNorrisApi;
import ExternApi.ChuckNorrisDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.ChuckNorrisFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final ChuckNorrisFacade FACADECN=  ChuckNorrisFacade.getChuckNorrisFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ChuckNorrisDto joke() throws Exception {

        ChuckNorrisApi api = new ChuckNorrisApi();
        ChuckNorrisDto joke = api.fetchJoke();
        FACADECN.create(joke);
        return joke;
    }


}
