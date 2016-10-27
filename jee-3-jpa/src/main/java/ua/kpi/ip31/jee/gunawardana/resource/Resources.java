package ua.kpi.ip31.jee.gunawardana.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans.
 */
@ApplicationScoped
public class Resources {
    @PersistenceContext
    @Produces
    EntityManager em;
}
