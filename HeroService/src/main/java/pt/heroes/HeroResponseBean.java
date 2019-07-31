package pt.heroes;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("heroes/{hero_id}")
public class HeroResponseBean {

    @EJB
    private HeroManager heromanager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hero getHero(@PathParam("hero_id") Integer heroId) {
        Hero result = heromanager.getHeroById(heroId);
        if (result != null) {
            return result;
        } else {
            return new Hero(0, "Nincs ilyen");
        }
    }

    @DELETE
    public void deleteHero(@PathParam("hero_id") Integer heroId) {
        heromanager.deleteHero(heroId);
    }
}