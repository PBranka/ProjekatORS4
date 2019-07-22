import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Program 
{
	public static int brKlijenata, velFragm, softOgr;
	public static final long brojBajtova = (new File ("C:\\Slika\\Poppy.jpg")).length () ;
    // Odredjuje kolika je velicina fajla uopsteno, mi cemo to primijeniti na nasu sliku.

	public ArrayList <Client> klijenti = null;
	
	// Cita sadrzaj iz fajla sa parametrima.
	public static void getParameters (String ime_fajla) // Treba nam u stvari putanja.
	{
		Scanner sc = new Scanner (ime_fajla);
		
		int ln;
		
		for (ln = 1; sc.hasNextLine (); ln++)
		{
			String line = sc.nextLine ();
			
			switch (ln)
			{
				case 1: brKlijenata = Integer.parseInt (line.trim ());
				case 2: velFragm = Integer.parseInt (line.trim ());
				case 3: softOgr = Integer.parseInt (line.trim ());
				default: sc.close (); break;
			}
		}
		
		if (ln < 3)
			System.out.println ("Nepravilan unos u fajl sa parametrima.");
	}

	// Popunjava listu klijenti.
	public void konstruktor ()
	{
		long temp; // velicina liste u klasi Client...
		
		if (brojBajtova % velFragm == 0)
			temp = brojBajtova / velFragm;
		else
			temp = brojBajtova / velFragm + 1;
		
		for (int i = 0; i < brKlijenata; i++)
		{
			klijenti.add (new Client (i));
			klijenti.get (i).lista = new ArrayList <byte []> ((int) temp);
		}
	}
	
	// Provjerava da li su svi klijenti preuzeli sadrzaj.
	public boolean completed ()
	{
		for (int i = 0; i < brKlijenata; i++)
			if (klijenti.get (i).lista.contains (null))
				return false;
		
		return true;
	}


}
