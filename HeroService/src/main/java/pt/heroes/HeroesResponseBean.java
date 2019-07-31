package pt.heroes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/heroes")
public class HeroesResponseBean {

    @EJB
    HeroManager heromanager;

    private static Logger logger = Logger.getLogger(HeroesResponseBean.class.getName());

    @PostConstruct
    public void init() {
        logger.info("HeroesResponseBean init end");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hero> getHeroes(@QueryParam("name") String filter) {
        List<Hero> wr = new ArrayList<>();
        logger.info("helloejb: " + heromanager);

        wr = heromanager.filterHeroes(filter);

        return wr;
    }
}
