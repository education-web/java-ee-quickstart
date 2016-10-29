package ua.kpi.ip31.jee.gunawardana.controller;

import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class ComicsListView {
    final ComicsRepository repository;

    @Inject
    public ComicsListView(ComicsRepository repository) {
        this.repository = repository;
    }

    public List<Comics> getAll() {
        return repository.findAll();
    }
}
