package user;

import data.UserDao;
import data.UserDaoSimleDBImpl;
import data.UserDaoSimpleMemImp;
import exceptions.UserAlreadyExistException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akos on 2017.05.10..
 */
public class UserServlet extends HttpServlet
{
    private List<User> users;
    private UserDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        users = new ArrayList<>();
        dao = new UserDaoSimleDBImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        String name = req.getParameter("username");
        try
        {
            dao.add(name);
            session.setAttribute("username", name);
            resp.sendRedirect("./todos.jsp");

        } catch (UserAlreadyExistException e)
        {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("./login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userName = (String) req.getSession().getAttribute("username");
        dao.remove(userName);
        req.setAttribute("message", "Successfully deleted your account");
        req.getRequestDispatcher("./login.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
         HttpSession session = req.getSession();
         session.setAttribute("username", req.getParameter("name"));
         resp.sendRedirect("./todos.jsp");
    }
}
