import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServerMain
{
  public static void main(String[] args)
      throws RemoteException, MalformedURLException
  {
    RmiTaskServer server = new RmiTaskServer();
  }
}
