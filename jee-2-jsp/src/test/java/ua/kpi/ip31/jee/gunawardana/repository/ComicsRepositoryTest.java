package ua.kpi.ip31.jee.gunawardana.repository;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.kpi.ip31.jee.gunawardana.ComicsStoreServiceLocator;
import ua.kpi.ip31.jee.gunawardana.model.Comics;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class ComicsRepositoryTest {
    private static ComicsRepository repository = ComicsStoreServiceLocator.getComicsRepository();

    @BeforeClass
    public static void cleanRepositoryBeforeAll() {
        repository.deleteAll();
    }

    @After
    public void cleanRepositoryAfterTestCase() {
        repository.deleteAll();
    }

    @Test
    public void testFindByPublisher() {
        List<Comics> savedComicsList = asList(
                new Comics("Batman", "DC Comics", "John Bell", 52, new BigDecimal("2.99")),
                new Comics("Spiderman", "Marvel", "Mark Wayne", 4, new BigDecimal("1.59")));
        savedComicsList = savedComicsList.parallelStream().map(c -> repository.save(c)).collect(toList());
        List<Comics> retrievedComicsList = repository.findByPublisher(savedComicsList.get(0).getPublisher());
        assertEquals(retrievedComicsList.size(), 1);
        assertEquals(savedComicsList.get(0), retrievedComicsList.get(0));
    }

    @Test
    public void testSave() {
        Comics savedComics = new Comics("Batman", "DC Comics", "John Bell", 52, new BigDecimal("2.99"));
        savedComics = repository.save(savedComics);
        List<Comics> retrievedComics = repository.findAll();
        assertEquals(retrievedComics.size(), 1);
        assertEquals(savedComics, retrievedComics.get(0));
    }

    @Test
    public void testUpdate() {
        Comics savedComics = new Comics("Batman", "DC Comics", "John Bell", 52, new BigDecimal("2.99"));
        savedComics = repository.save(savedComics);
        savedComics = savedComics.withNumber(42);
        repository.update(savedComics);
        Comics retrievedComics = repository.findById(savedComics.getId()).orElse(null);
        assertEquals(savedComics, retrievedComics);
    }
}
