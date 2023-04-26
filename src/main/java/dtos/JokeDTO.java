package dtos;

import java.util.ArrayList;

public class JokeDTO {
    private String id;
    private ArrayList<String> jokes;

    public JokeDTO(ChuckNorrisDto Cnd) {
        this.id = Cnd.getId();
        this.jokes = new ArrayList<>();
        this.jokes.add(Cnd.getValue());
    }

    public JokeDTO(String id) {
        this.id = id;
    }

    public JokeDTO(ArrayList<String> jokes) {
        this.jokes = jokes;
    }
}
