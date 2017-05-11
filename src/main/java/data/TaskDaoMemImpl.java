package data;

import task.States;
import task.Task;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by akos on 2017.05.09..
 */
public class TaskDaoMemImpl implements TaskDao
{
    private List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> getAll(String userName)
    {
        return tasks.stream()
            .filter(task -> task.getUserName().equals(userName))
            .collect(Collectors.toList());
    }

    @Override
    public Task get(String ID)
    {
        return tasks.stream()
            .filter(task -> task.getID().equals(ID))
            .collect(Collectors.toList()).get(0);
    }

    @Override
    public boolean add(Task task)
    {
        try
        {
            tasks.add(task);
            return true;
        }
        catch (Exception e)
        {
        return false;
        }
    }

    @Override
    public boolean remove(String ID)
    {
        try
        {
            tasks = tasks.stream()
                .filter(task -> !(task.getID().equals(ID)))
                .collect(Collectors.toList());
            return true;
        }
        catch (Exception e)
        {
        return false;
        }
    }

    @Override
    public boolean remove(Task task)
    {
        int initialSize = tasks.size();
        Task removed = tasks.stream()
            .filter(seltask -> seltask.equals(task))
            .collect(Collectors.toList()).get(0);

        tasks = tasks.stream()
            .filter(task1 -> !(task1.equals(task)))
            .collect(Collectors.toList());

        return (!(removed.equals(task)) && initialSize <= tasks.size());

    }

    @Override
    public List<Task> getBacklog(String userName)
    {
        return getAll(userName).stream()
            .filter(task -> task.getState() == States.BACKLOG)
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> getActive(String userName)
    {
        return getAll(userName).stream()
            .filter(task -> task.getState() == States.ACTIVE)
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> getDone(String userName)
    {
        return getAll(userName).stream()
            .filter(task -> task.getState() == States.DONE)
            .collect(Collectors.toList());

    }

    @Override
    public void setState(String ID, String state, String userName)
    {
        getAll(userName);
        tasks.stream()
            .filter(task -> task.getID().equals(ID))
            .forEach(task -> task.setState(States.valueOf(state)));
    }

    @Override
    public void editText(String ID, String newText, String userID)
    {
        getAll(userID).stream()
            .filter(task -> task.getID().equals(ID))
            .forEach(task -> task.setText(newText));
    }
}
