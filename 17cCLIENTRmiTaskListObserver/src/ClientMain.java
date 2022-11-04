import java.rmi.RemoteException;

public class ClientMain
{
  public static void main(String[] args) throws RemoteException
  {

    String host = "localhost";
    if (args.length > 0)
    {
      host = args[0];
    }
    RmiTaskClient client = new RmiTaskClient(host);
    client.start();
  }
}