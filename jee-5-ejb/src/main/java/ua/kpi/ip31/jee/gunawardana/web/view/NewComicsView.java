package ua.kpi.ip31.jee.gunawardana.web.view;

import lombok.extern.log4j.Log4j2;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.OnlineComics;
import ua.kpi.ip31.jee.gunawardana.service.ComicsService;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * JSF ComicsListView Controller.
 */
@Log4j2
@Model
public class NewComicsView {
    final FacesContext facesContext;
    final ComicsService comicsService;

    @Produces
    @Named
    final Comics newComics;

    @Inject
    public NewComicsView(FacesContext facesContext, ComicsService comicsService) {
        this.facesContext = facesContext;
        this.comicsService = comicsService;

        newComics = new Comics();
        newComics.setOnlineComics(new OnlineComics());
    }

    public void save() {
        try {
            if (newComics.getOnlineComics().getUrl() == null) {
                newComics.setOnlineComics(null);
            }
            comicsService.save(newComics);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            log.warn(errorMessage, e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, "Registration unsuccessful"));
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
}
