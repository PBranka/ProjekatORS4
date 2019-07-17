// Pretpostavimo da znamo velicinu sadrzaja koji preuzimamo.
// Nasa slika ima 32,344 bajta.

import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class Client 
{
	// Prilikom pozivanja konstruktora, numerise klijenta.
	private int index;
	// Lista koja sadrzi fragmente.
	private ArrayList <byte []> lista = null;
	
	public Client (int in)
	{
		this.index = in;
	}
	
	// Kada klijent bude imao sav sadrzaj, sadrzaj prosljedjuje u direktorijum sa imenom Klijentindex.
	// Npr. Klijent10.
	public void inDirectory ()
	{
		StringBuilder ime_dir = new StringBuilder ("Klijent");
		ime_dir.append (index);
		
		Path p = Paths.get ("C:\\Klijenti\\" + ime_dir);
		
		// Ukoliko direktorijum ne postoji, pravimo ga.
		if (!Files.exists (p))
			try
			{
				Files.createDirectory (p);
			}
			catch (IOException ex)
			{
				System.err.println ("Nismo uspjeli da kreiramo direktorijum.");
			}
		
		// Sada cemo ispisati sadrzaj u direktorijum.
		try
		{
			// Slika koju preuzimamo je slika maka, sa ekstenzijom jpg.
			FileOutputStream fout = new FileOutputStream ("C:\\Klijenti\\Klijent" + index + "\\Mak.jpg");
			BufferedOutputStream bout = new BufferedOutputStream (fout);
			
			for (int i = 0; i < lista.size () && !lista.isEmpty (); i++)
				bout.write (lista.get (i));
			
			bout.flush ();
			bout.close ();
		}
		catch (IOException ex)
		{
			System.err.println ("Ispis u direktorijum nije uspio.");
		}	
	}

	// Cita sadrzaj, dijeli ga na fragmente i upisuje u listu, mora se doraditi, nije dobro ovako.
	public void procitaj ()
	{
		try
		{
			FileInputStream fin = new FileInputStream ("C:\\Slika\\Poppy.jpg");
			BufferedInputStream bin = new BufferedInputStream (fin);
			
			byte [] buf = new byte [Program.velFragm];
			int bytesRead = 0;
			
			while ((bytesRead = bin.read (buf)) != -1)
			{
				if (bytesRead % Program.velFragm == 0)
				{
					lista.add (buf);
					buf = null;
					buf = new byte [Program.velFragm];
				}
			}
			
			bin.close ();
		}
		catch (IOException ex)
		{
			System.out.println (ex);
		}
	}
}
