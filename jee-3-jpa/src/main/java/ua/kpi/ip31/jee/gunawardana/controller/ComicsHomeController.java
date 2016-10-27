package ua.kpi.ip31.jee.gunawardana.controller;

import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller views all {@link Comics}.
 *
 * @author Ruslan Gunawardana
 */
@WebServlet("/index")
public class ComicsHomeController extends HttpServlet {
    final ComicsRepository repository;

    @Inject
    public ComicsHomeController(ComicsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("comicsList", repository.findAll());
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}
