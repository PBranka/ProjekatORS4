 /*
  *	Prikaz u html aplikacije
  **/


import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
//import javax.swing.*;			Ako JButton dodajemo... potrebna je ova biblioteka

public class htmlTest {
	public static void main (String[] args) {
		StringBuffer result = new StringBuffer ("<html><body><h1>P2P model ispodurke sadrzaja</h1>");
		
		
			result.append("<button>Browse</button>");
			result.append("<p></p>");
			result.append("<button>Download all</button>");
			result.append("<p></p>");
			result.append("<p>Item list:</p><ol>");
			/*for (int i = 0; i<; i++)					// za svaki film/sliku treba izlistati
			{
			result.append("<li>");
			result.append("FilmN/SlikaM");
			result.append("<progress"></progress>");
			}*/
			result.append("</ol></body></html>");
		
		JEditorPane jer = new JEditorPane("text/html",result.toString() );
		jer.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jer);
		JFrame f = new JFrame ("P2P model ispodurke sadrzaja");
		/*
		JButton b1 = new JButton(); 
   		b1.setVisible(true);
    	b1.setText("Browse");
        b1.setSize(70,210);
    	JButton b2 = new JButton(); 
   		b2.setVisible(true);
    	b2.setText("Download All");
    	b2.setSize(70,210);
    	f.add(b1);
		f.add(b2);
		*/
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setContentPane(scrollPane);					// Moze a i ne mora biti scrollPane
		f.setSize(800,800);
		EventQueue.invokeLater(new FrameShower(f));		// EventQueue dodajemo za slucaj velikih podataka da se uskladi racunanje i dizanje html forme
		f.setVisible(true);
	}
	private static class FrameShower implements Runnable{
		private final Frame frame;
		FrameShower(Frame frame){
			this.frame = frame;
		}
	public void run(){
		this.frame.setVisible(true);
		}
	}
	 
}