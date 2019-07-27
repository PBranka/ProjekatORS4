import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

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

	// Poredi odredjene segmente dva klijenta i vrsi razmjenu, ako je to moguce.
	public void transfer (int position, int from, int to)
	{
		// Nije bas smisleno from i to, jer mozemo da prebacujemo u bilo kojem smjeru.
		
		if (klijenti.get (from).lista.get (position) != null && klijenti.get (to).lista.get (position) == null)
		{
			// Klijent na poziciji to preuzima segment od klijenta na poziciji from.
			
			byte niz1 [] = klijenti.get (from).lista.get (position);
			byte niz2 [] = new byte [niz1.length];
			
			// Uvodimo niti da bismo paralelno pokrenuli servera i klijenta.
			try
			{
				Thread t1 = new Thread ()
				{
					public void run ()
					{
						try
						{
							ImServer is = new ImServer ();
							is.pokreniServer (niz2);
						}
						catch (IOException e)
						{
							e.printStackTrace ();
						}
					}	
				};
				
				Thread t2 = new Thread ()
				{
					public void run ()
					{
						try
						{
							ImClient ic = new ImClient ();
							ic.pokreniKlijenta (niz1);
						}
						catch (IOException e)
						{
							e.printStackTrace ();
						}
					}
				};
				
				t1.start ();
				t2.start ();
				t1.join ();  // baca InterruptedException ukoliko je nit prekinuta
				t2.join ();  // stavlja nit u stanje cekanja sve dok ne ,,umre''
			}
			catch (InterruptedException ex)
			{
				System.err.println (ex);
			}
		}
		else
			if (klijenti.get (from).lista.get (position) == null && klijenti.get (to).lista.get (position) != null)
				transfer (position, to, from);
	}
}
