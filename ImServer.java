// Prima niz bajtova od klijenta (ImClient).

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ImServer 
{
	public void pokreniServer (byte [] b) throws IOException
	{
		int port = 4444;
		
		try 
		{
			ServerSocket serverSocket = new ServerSocket (port);
			Socket clientSocket = serverSocket.accept ();
		
			BufferedInputStream in = new BufferedInputStream (clientSocket.getInputStream ());
		
			while (in.read (b) != -1);
			
			in.close ();
			clientSocket.close ();
			serverSocket.close ();
		}
		catch (IOException ex)
		{
			System.err.println (ex);
		}
	}
		
}
