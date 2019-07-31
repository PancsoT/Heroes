package pt.heroes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/heroes11")
public class HeroSearchBean {

    @EJB
    private HeroManager heromanager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hero> filterHeroes(@QueryParam("name") String filter) {
        List<Hero> wr = new ArrayList<Hero>();

        wr = heromanager.filterHeroes(filter);

        return wr;
    }
}
