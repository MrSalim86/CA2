package facades;

import ExternApi.ChuckNorrisDto;
import entities.Joke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ChuckNorrisFacade {


    private static ChuckNorrisFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ChuckNorrisFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ChuckNorrisFacade getChuckNorrisFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ChuckNorrisFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ChuckNorrisDto create(ChuckNorrisDto cd){
        Joke je = new Joke();
        je.setChuckNorris(cd.getJoke());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(je);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ChuckNorrisDto(cd.getJoke());
    }
}
