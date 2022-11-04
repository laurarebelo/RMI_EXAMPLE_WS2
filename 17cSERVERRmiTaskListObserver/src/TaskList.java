import java.util.ArrayList;

public class TaskList
{
  private ArrayList<Task> list;

  public TaskList() {
    this.list = new ArrayList<>();
  }

  public void add(Task task) {
    list.add(task);
  }

  public Task getAndRemoveNextTask() {
    Task returnIt = list.get(0);
    list.remove(0);
    return returnIt;
  }

  public int size() {
    return list.size();
  }

  @Override public String toString()
  {
    return "TaskList{" + "list=" + list + '}';
  }
}
