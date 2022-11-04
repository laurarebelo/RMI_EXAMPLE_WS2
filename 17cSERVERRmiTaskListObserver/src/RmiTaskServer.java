import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class RmiTaskServer implements RemoteTaskList
{
  private TaskList taskList;
  private PropertyChangeHandler<Task,Task> property;

  public RmiTaskServer() {
    this.taskList = new TaskList();
    this.property = new PropertyChangeHandler<>(this,true);
    try {
    startRegistry();
    startServer(); }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void startRegistry() throws RemoteException
  {
    try {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (ExportException e) {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this,0);
    Naming.rebind("Case",this);
    System.out.println("Server started...");
  }

  @Override public void add(Task task) throws RemoteException
  {
    taskList.add(task);
    property.firePropertyChange("add",null,task);
    System.out.println("Added task: " + task);
  }

  @Override public Task get() throws RemoteException
  {
    Task task = taskList.getAndRemoveNextTask();
    System.out.println("Got task: " + task);
    return task;
  }

  @Override public int size() throws RemoteException
  {
    System.out.println("Queried size. Size: " + taskList.size());
    return taskList.size();
  }

  @Override public boolean addListener(GeneralListener<Task,Task> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(GeneralListener<Task,Task> listener,
      String... propertyNames) throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }
}
