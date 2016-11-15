package ua.kpi.ip31.jee.gunawardana.resource;

import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.OnlineComics;
import ua.kpi.ip31.jee.gunawardana.model.SuperHero;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;
import ua.kpi.ip31.jee.gunawardana.stereotype.DevelopmentEnvironment;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;

import static java.util.Arrays.asList;

/**
 * Fills the repository with some test {@link Comics} objects.
 */
@DevelopmentEnvironment
@Startup
@Singleton
public class ResourcesFiller {
    final ComicsRepository comicsRepository;

    /**
     * Required only to satisfy EJB specification.
     * @deprecated  Should not be called. {@link Inject} annotated constructor should be used instead.
     */
    @Deprecated
    public ResourcesFiller() {
        throw new AssertionError("Should not be called because doesn't initialize dependencies.");
    }

    @Inject
    public ResourcesFiller(ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    @PostConstruct
    public void init() {
        comicsRepository.save(new Comics("Batman", "DC Comics", 52, new BigDecimal("2.99"),
                new OnlineComics("http://batman.com", new BigDecimal("3.25")),
                asList(new SuperHero("Batman", "Bruce Wayne"),
                        new SuperHero("Two-Face", "Harvey Dent"))
        ));
        comicsRepository.save(new Comics("Spiderman", "Marvel", 3, new BigDecimal("2.99"),
                new OnlineComics("http://spiderman.com", new BigDecimal("3.25")),
                asList(new SuperHero("Spiderman", "Peter Parker"),
                        new SuperHero("Wolverine"))
        ));
    }
}
