package ExternApi;

import entities.Joke;

public class ChuckNorrisDto {
    String joke;

    public ChuckNorrisDto(String joke) {
        this.joke = joke;
    }

    public ChuckNorrisDto(Joke joke) {
        this.joke = joke.getChuckNorris();
    }

    public String getJoke() {
        return joke;
    }
    public void setJoke(String joke) {
        this.joke = joke;
    }
}
