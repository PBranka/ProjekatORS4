import java.util.Random;

public class ProgramRunnable implements Runnable
{
	private static final int MAX_DELAY = 3;
	private Program program;
	
	public ProgramRunnable (Program program)
	{
		this.program = program;
	}
	
	public void run ()
	{
		Random rand = new Random ();
		int from =  rand.nextInt (Program.brKlijenata); // i i j su pozicije klijenata cije fragmente posmatramo
		int to = rand.nextInt (Program.brKlijenata);
		int position = rand.nextInt (Program.brFragm); // pozicija fragmenta
	
		try
		{
			program.transfer (position, from, to);
			
			Thread.sleep((int) (Math.random () * MAX_DELAY));
		}
		catch (InterruptedException ex) // zbog metoda sleep
		{
			ex.printStackTrace ();
		}
	}
}

