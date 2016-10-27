package ua.kpi.ip31.jee.gunawardana.controller;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.OnlineComics;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static java.lang.Integer.parseInt;
import static java.util.Collections.emptySet;

/**
 * Controller saves a new {@link Comics}.
 *
 * @author Ruslan Gunawardana
 */
@Log4j2
@WebServlet("/comics")
public class ComicsCreationController extends HttpServlet {
    final ComicsRepository comicsRepository;

    @Inject
    public ComicsCreationController(ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String onlineUrlParam = Strings.emptyToNull(req.getParameter("online_url"));
        String onlinePriceParam = Strings.emptyToNull(req.getParameter("online_price"));
        String priceParam = Strings.emptyToNull(req.getParameter("price"));

        BigDecimal onlinePrice = onlinePriceParam != null ? new BigDecimal(onlinePriceParam) : null;
        OnlineComics onlineComics = onlineUrlParam != null ? new OnlineComics(onlineUrlParam, onlinePrice) : null;

        BigDecimal price = priceParam != null ? new BigDecimal(priceParam) : null;
        Comics comics = new Comics(
                req.getParameter("title"),
                req.getParameter("publisher"),
                parseInt(req.getParameter("number")),
                price,
                onlineComics,
                emptySet());
        try {
            comicsRepository.save(comics);
            resp.sendRedirect(req.getContextPath());
        } catch (Exception e) {
            log.warn("Comics could not be caved", e);
            resp.sendError(400, "Invalid comics");
        }
    }
}
