package jp.ne.naokiur.em.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.AttributeKey;
import jp.ne.naokiur.em.code.Site;

@WebServlet(name = "MenuController", urlPatterns = {"/user/menu"})
public class MenuController extends HttpServlet {

    /** Default serialize ID */
    private static final long serialVersionUID = 1L;

    /** ServletContext */
    private ServletContext context;

    public void init() {
        context = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute(AttributeKey.TITLE.getKey(), Site.MENU.getTitle());

        context.getRequestDispatcher(Site.MENU.getJspPath()).forward(req, res);
    }
}
