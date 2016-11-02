package ua.kpi.ip31.jee.gunawardana.resource;

import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.OnlineComics;
import ua.kpi.ip31.jee.gunawardana.model.SuperHero;
import ua.kpi.ip31.jee.gunawardana.repository.JpaComicsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.math.BigDecimal;

import static java.util.Arrays.asList;

@ApplicationScoped
public class ResourcesFiller {
    final JpaComicsRepository comicsRepository;

    @Inject
    public ResourcesFiller(JpaComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
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
