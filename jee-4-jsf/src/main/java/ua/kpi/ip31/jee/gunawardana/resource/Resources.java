package ua.kpi.ip31.jee.gunawardana.resource;

import ua.kpi.ip31.jee.gunawardana.stereotype.DevelopmentEnvironment;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans.
 */
@ApplicationScoped
public class Resources {
    @Produces
    @PersistenceContext(unitName = "production")
    EntityManager productionEntityManager;

    @Produces
    @DevelopmentEnvironment
    @PersistenceContext(unitName = "development")
    EntityManager developmentEntityManager;

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}
