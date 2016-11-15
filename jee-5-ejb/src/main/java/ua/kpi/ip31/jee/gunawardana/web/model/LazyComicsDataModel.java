package ua.kpi.ip31.jee.gunawardana.web.model;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.Comics_;
import ua.kpi.ip31.jee.gunawardana.service.ComicsService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Long.parseLong;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;

/**
 * Used by view for lazy datasource access.
 */
public class LazyComicsDataModel extends LazyDataModel<Comics> {
    private ComicsService comicsService;

    @Inject
    public LazyComicsDataModel(ComicsService comicsService) {
        this.comicsService = comicsService;
    }

    @Override
    public Comics getRowData(String rowKey) {
        return comicsService.findById(parseLong(rowKey)).orElseThrow(AssertionError::new);
    }

    @Override
    public Object getRowKey(Comics comics) {
        return comics.getId();
    }

    @Override
    public List<Comics> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Comics> result = Optional.ofNullable(filters.get(Comics_.publisher.getName()))
                .map(publisher -> comicsService.findByPublisher(publisher.toString()))
                .orElseGet(() -> comicsService.findAll());

        result.sort(nullsLast(comparing(Comics::getId)));

        int resultSize = result.size();
        this.setRowCount(resultSize);
        if (resultSize > pageSize) {
            if (first + pageSize >= resultSize) {
                return result.subList(first, first + pageSize);
            } else {
                return result.subList(first, first + (resultSize % pageSize));
            }
        } else {
            return result;
        }
    }
}
