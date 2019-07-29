
public class Test 
{
	public static void main (String [] args) 
	{
		// Citamo parametre iz fajla.
		Program.getParameters ("C:\\Klijenti\\Parametri.txt");
		
		Program pr = new Program ();
		
		pr.klijenti.get (0).procitaj (); // prvi klijent preuzima sadrzaj

		while (!pr.completed ()) // dok svi klijenti ne preuzmu sadrzaj
		{
			ProgramRunnable p = new ProgramRunnable (pr);
			Thread t = new Thread (p);
				
			t.start ();
		}
		
		// Prenosi sadrzaj svakog klijenta u odgovarajuci direktorijum.S
		for (int s = 0; s < pr.klijenti.size (); s++)
			pr.klijenti.get (s).inDirectory ();
	}
}
