import java.util.Scanner;

public class Program 
{
	public static int brKlijenata, velFragm, softOgr;
	public static final int brojBajtova = 32344; // Izabrali smo sliku te velicine.
	
	// Cita sadrzaj iz fajla sa parametrima.
	public void getParameters (String ime_fajla) // Treba nam u stvari putanja.
	{
		Scanner sc = new Scanner (ime_fajla);
		
		int ln;
		
		for (ln = 1; sc.hasNextLine (); ln++)
		{
			String line = sc.nextLine ();
			
			switch (ln)
			{
				case 1: brKlijenata = Integer.parseInt (line);
				case 2: velFragm = Integer.parseInt (line);
				case 3: softOgr = Integer.parseInt (line);
				default: sc.close (); break;
			}
		}
		
		if (ln < 3)
			System.out.println ("Nepravilan unos u fajl sa parametrima.");
	}
}
