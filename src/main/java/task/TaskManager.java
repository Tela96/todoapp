package task;

import data.TaskDao;
import data.TaskDaoDBImpl;
import data.TaskDaoMemImpl;
import user.User;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by akos on 2017.05.09..
 */
public class TaskManager
{
    private TaskDao dao = new TaskDaoDBImpl();

    public void add(String text, String userName) throws Exception
    {
        Task newTask = new Task(text, userName);
        dao.add(newTask);
    }

    public List<Task> getAll(String userName)
    {
        return dao.getAll(userName);
    }

    public List<Task> get(String type, String userName) throws Exception
    {
        System.out.println("Type parameter:\t" + type);
        switch (type)
        {
            case "backlog":
                return dao.getBacklog(userName);
            case "active":
                return dao.getActive(userName);
            case "done":
                return dao.getDone(userName);
            case "all":
                return dao.getAll(userName);
        }
        throw new Exception();
    }

    public void setState(String ID, String state, String userName)
    {
        dao.setState(ID, state, userName);
    }
    public void editText(String ID, String newText, String userName)
    {
        dao.editText(ID, newText, userName);
    }

}

