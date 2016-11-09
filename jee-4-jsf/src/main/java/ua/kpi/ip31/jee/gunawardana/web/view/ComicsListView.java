package ua.kpi.ip31.jee.gunawardana.web.view;

import lombok.Getter;
import org.primefaces.model.LazyDataModel;
import ua.kpi.ip31.jee.gunawardana.model.Comics;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * JSF ComicsListView Controller.
 */
@Named
@ApplicationScoped
public class ComicsListView implements Serializable {
    @Getter
    final LazyDataModel<Comics> lazyModel;

    @Inject
    public ComicsListView(LazyDataModel<Comics> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
