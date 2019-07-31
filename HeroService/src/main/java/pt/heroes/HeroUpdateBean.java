package pt.heroes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/heroes")
public class HeroUpdateBean {

    @EJB
    private HeroManager heromanager;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateHero(Hero hero) {
        heromanager.updateHeroById(hero.getId(), hero.getName());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Hero addHero(Hero hero) {
        Hero result = heromanager.addHero(hero.getName());
        System.out.println("Addingresult: " + result.getName());
        return result;
    }

}
