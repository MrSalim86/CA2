package ExternApi;

public class ChuckNorrisDto {
    String joke;

    public ChuckNorrisDto(String joke) {
        this.joke = joke;
    }
    public String getJoke() {
        return joke;
    }
    public void setJoke(String joke) {
        this.joke = joke;
    }
}
