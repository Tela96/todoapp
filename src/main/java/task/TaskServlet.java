package task;

import org.codehaus.jackson.map.ObjectMapper;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akos on 2017.05.09..
 */
public class TaskServlet extends HttpServlet
{
    private List<Task> tasks;
    private TaskManager taskManager;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        tasks = new ArrayList<>();
        taskManager = new TaskManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userName = (String) req.getSession().getAttribute("username");
        String operation = req.getParameter("type");
        String ID;
        switch (operation)
        {
            case "add" :
                String text = req.getParameter("taskText");
                try
                {   if (text == null || text.equals("") || text.equals(" "))break;
                    taskManager.add(text, userName);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case "state":
                ID = req.getParameter("ID");
                taskManager.setState(ID, req.getParameter("newstate"), userName);
                break;

            case "edit":
                ID = req.getParameter("ID");
                String newText = req.getParameter("newtext");
                if(newText == null || newText.equals("") || newText.equals(" ")) break;
                taskManager.editText(ID, newText, userName);
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userName = (String) req.getSession().getAttribute("username");
        String selection = req.getParameter("selection");
        try
        {
           tasks = taskManager.get(selection, userName);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        resp.setContentType("application/json");
        ObjectMapper om = new ObjectMapper();
        om.writeValue(resp.getOutputStream(), tasks);
    }
}
