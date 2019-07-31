package pt.heroes;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HeroManager {

    private static Logger logger = Logger.getLogger(HeroManager.class.getName());

    @EJB
    private DaoBase daobase;

    @PostConstruct
    public void init() {
        logger.info("HeroManager init end");
        // heroes = new HashMap<>();
        // heroes.put(11, new Hero(11, "Mr. Nice"));
        // heroes.put(12, new Hero(12, "Narco"));
        // heroes.put(13, new Hero(13, "Bombasto"));
        // heroes.put(14, new Hero(14, "Celeritas"));
        // heroes.put(15, new Hero(15, "Magneta"));
        // heroes.put(16, new Hero(16, "RubberMan"));
        // heroes.put(17, new Hero(17, "Dynama"));
        // heroes.put(18, new Hero(18, "Dr IQ"));
        // heroes.put(19, new Hero(19, "Magma"));
        // heroes.put(20, new Hero(20, "Tornado"));
    }

    // private Map<Integer, Hero> heroes;

    public Hero getHeroById(Integer id) {
        return daobase.getHeroes().get(id);
    }

    public void updateHeroById(Integer id, String name) {
        Hero hero = daobase.getHeroes().get(id);
        hero.setName(name);
    }

    public Hero addHero(String hero) {
        Integer nextId = getNextId();
        Hero newHero = new Hero(nextId, hero);
        daobase.persist(newHero);
        return newHero;
    }

    public void deleteHero(Integer id) {
        daobase.removeHero(id);
    }

    public List<Hero> filterHeroes(String filter) {
        if (filter == null)
            filter = "";
        final String fin = filter;

        logger.info("hero size: " + daobase.getHeroes().size());
        return daobase.getHeroes().values().stream()
                .filter(hero -> hero.getName().toLowerCase().contains(fin.toLowerCase())).collect(Collectors.toList());
    }

    private Integer getNextId() {
        return daobase.getHeroes().values().stream().map(hero -> hero.getId()).max((h1, h2) -> Integer.compare(h1, h2))
                .get() + 1;
    }
}