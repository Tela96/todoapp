package data;

import task.Task;

import java.util.List;

/**
 * Created by akos on 2017.05.09..
 */
public interface TaskDao
{
    List<Task> getAll(String userName);
    Task get(String ID);
    boolean add(Task task);
    boolean remove(String ID);
    boolean remove(Task task);
    List<Task> getBacklog(String userName);
    List<Task> getActive(String userName);
    List<Task> getDone(String userName);
    void setState(String ID, String state, String userName);
    void editText(String ID, String newText, String userID);
}
