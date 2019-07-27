// Salje niz bajtova serveru (ImServer).

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ImClient 
{
	
	public  void pokreniKlijenta (byte [] buf) throws IOException
	{
		String hostname = "localhost";
		
		try 
		{
			Socket sock = new Socket (hostname, 4444);
			BufferedOutputStream out = new BufferedOutputStream (sock.getOutputStream ());
			
			out.write (buf);
			out.flush ();
			sock.close ();
			out.close ();
		}
		catch (IOException ex)
		{
			System.err.println (ex);
		}
	}
}
