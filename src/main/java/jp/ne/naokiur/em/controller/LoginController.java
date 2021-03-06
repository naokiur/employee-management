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
import jp.ne.naokiur.em.exception.ModelValidatorException;
import jp.ne.naokiur.em.model.LoginModel;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
        session.setAttribute(AttributeKey.TITLE.getKey(), Site.LOGIN.getTitle());

        context.getRequestDispatcher(Site.LOGIN.getJspPath()).forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        LoginModel model = new LoginModel();

        try {
            session.setAttribute("authenticated-user", model.authenticate(userId, password));

        } catch (ModelValidatorException e) {
            req.setAttribute(AttributeKey.MESSAGE_LIST.getKey(), e.getMessagesList());

            context.getRequestDispatcher(Site.LOGIN.getJspPath()).forward(req, res);

            return;
        }

        res.sendRedirect(req.getContextPath() + Site.MENU.getUrl());
    }
}
