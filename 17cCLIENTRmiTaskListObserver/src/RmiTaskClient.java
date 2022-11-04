import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RmiTaskClient implements RemoteListener<Task, Task>
{
  private RemoteTaskList taskList;
  private boolean running;

  public RmiTaskClient(String host) {
    try {
      String url = "rmi://";
      url+= host;
      url+=":1099/Case";

      taskList = (RemoteTaskList) Naming.lookup(url);
      running = true;
      UnicastRemoteObject.exportObject(this,0);
      taskList.addListener(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start() {
    while (running) {
      Scanner input = new Scanner(System.in);
      System.out.println("TASK LIST.");
      System.out.println("1) ADD a task");
      System.out.println("2) GET a task");
      System.out.println("3) Get SIZE of list");
      System.out.println("4) EXIT");
      System.out.print("Option: ");
      String option = input.nextLine();
      try {
      switch (option)
      {
        case "1":
            System.out.print("Task: ");
            String what = input.nextLine();
            System.out.println("Estimated time: ");
            long time = input.nextLong();
            input.nextLine();
            Task task = new Task(what, time);
            taskList.add(task);
            System.out.println("Added task.");
          break;
        case "2":
            Task taskGet = taskList.get();
            System.out.println(taskGet);
          break;
        case "3":
            int size = taskList.size();
            System.out.println("Size: " + size);
          break;
        case "4":
          running = false;
          System.out.println("EXITED.");
          break;
        default:
          System.out.println("That's not a valid option.");
          break;
      }
      }
      catch (Exception e)
      {
        System.out.println("Something went wrong. Try again");
        e.printStackTrace();
      }

    }
  }

  @Override public void propertyChange(ObserverEvent<Task, Task> event)
      throws RemoteException
  {
    if (event.getPropertyName().equals("add")) {
      System.out.println("\nBROADCAST: Task added: " + event.getValue2());
    }
  }
}
