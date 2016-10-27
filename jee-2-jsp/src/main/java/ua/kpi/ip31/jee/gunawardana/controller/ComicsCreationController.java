package ua.kpi.ip31.jee.gunawardana.controller;

import ua.kpi.ip31.jee.gunawardana.ComicsStoreServiceLocator;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static java.lang.Integer.parseInt;

/**
 * Controller saves a new {@link Comics}.
 *
 * @author Ruslan Gunawardana
 */
@WebServlet("/comics")
public final class ComicsCreationController extends HttpServlet {
    private ComicsRepository comicsRepository = ComicsStoreServiceLocator.getComicsRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comics comics = new Comics(
                req.getParameter("title"),
                req.getParameter("publisher"),
                req.getParameter("author"),
                parseInt(req.getParameter("number")),
                new BigDecimal(req.getParameter("price")));
        comicsRepository.save(comics);
        resp.sendRedirect(req.getContextPath());
    }
}
