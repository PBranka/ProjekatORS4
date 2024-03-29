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
	public ArrayList <byte []> lista = null;
	
	public Client (int in)
	{
		this.index = in;
		this.lista = new ArrayList <byte []> (Program.brFragm);
		
		for (int i = 0; i < Program.brFragm; i++)
			this.lista.add (null); // popunjavamo listu null elementima, treba nam kasnije u kodu zbog njene velicine
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

	// Cita sadrzaj, dijeli ga na fragmente i upisuje u listu. Koristicemo kod prvog klijenta.
	public void procitaj ()
	{
		lista.clear (); // zato sto imamo po postavci, zbog konstruktora, null vrijednosti u listi
		
		try
		{
			FileInputStream fin = new FileInputStream ("C:\\Slika\\Poppy.jpg");
			BufferedInputStream bin = new BufferedInputStream (fin);
			
			byte [] buf = new byte [Program.velFragm]; // fragmenti koje cemo dodavati u listu
			int bytesRead = 0;
			
			if (Program.velFragm > Program.brojBajtova)
			{
				System.out.println ("Greska. Velicina fragmenta je veca od velicine fajla.");
				System.exit (1);
			}
			
			long temp = Program.brojBajtova - Program.brojBajtova % Program.velFragm;
			// Posljednji dio, kada budemo dijelili, ne mora da bude velik kao i velFragm.
			
			int brojac = 0;
			
			while ((bytesRead = bin.read (buf)) != -1) // bytesRead broji koliko je elemenata u buf dodato
				                                       // zato dodajemo ovo brojac * ...
			{
				if (bytesRead % Program.velFragm == 0 && (bytesRead + brojac * Program.velFragm != temp))
				{
					lista.add (buf);
					buf = null;
					buf = new byte [Program.velFragm];
					brojac++;
				}
				else
					if (bytesRead + brojac * Program.velFragm == temp)
					{
						lista.add (buf);
						buf = null;
						buf = new byte [(int) Program.brojBajtova % Program.velFragm];
					}
			}
			
			if (buf.length != 0)
				lista.add (buf);
			
			bin.close ();
		}
		catch (IOException ex)
		{
			System.out.println (ex);
		}
	}

}

