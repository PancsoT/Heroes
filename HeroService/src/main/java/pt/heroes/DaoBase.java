package pt.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class DaoBase {

    private static Logger logger = Logger.getLogger(DaoBase.class.getName());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "mariaDbUnit")
    private EntityManager em;

    @PostConstruct
    public void init() {
        logger.info("DaoBase init end");
    }

    public Map<Integer, Hero> getHeroes() {
        logger.info("daobase getheroes START");
        @SuppressWarnings("unchecked")
        List<Hero> resultList = (List<Hero>) em.createQuery("SELECT h FROM Hero h").getResultList();
        logger.info("daobase, list size: " + resultList.size());
        Map<Integer, Hero> map = new HashMap<>();
        for (Hero hero : resultList) {
            map.put(hero.getId(), hero);
        }
        return map;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void removeHero(Integer id) {
        Hero toRemove = em.find(Hero.class, id);
        em.remove(toRemove);
    }
}
