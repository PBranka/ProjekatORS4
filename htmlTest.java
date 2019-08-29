 /*
  *	Prikaz u html aplikacije
  **/

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class htmlTest {
	public static void main (String[] args) {
		
		// 									>>> HTML NACIN <<<
		
		/*StringBuffer result = new StringBuffer ("<html><body><h1>P2P model ispodurke sadrzaja</h1>");
		
		
		//	result.append("<button onclick="alert('Hello world!')">Browse</button>");
			result.append("<script>");
			result.append("function funct_1(){");
			result.append("document.getElementById('aaa').innerHTML = 'Hello World';");
			result.append("}");
			result.append("</script>");
			
			result.append("<button onclick='funct_1()'>Browse</button>");
			result.append("<p id='aaa'></p>");
			result.append("<button>Download all</button>");
			result.append("<p></p>");
			result.append("<p>Item list:</p><ol>");
			/*for (int i = 0; i<; i++)					// za svaki film/sliku treba izlistati
			{
			result.append("<li>");
			result.append("FilmN/SlikaM");
			result.append("<progress></progress>");
			}
			result.append("</ol></body></html>");
		
		JEditorPane jer = new JEditorPane("text/html",result.toString() );
		jer.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jer);
		*/
		//									>>> JAVA NACIN <<<
		
		JFrame f = new JFrame ("P2P model ispodurke sadrzaja");
		f.setSize(600,600);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JPanel p = new JPanel();
		f.add(p);
		
		JButton b1 = new JButton(); 
   		b1.setVisible(true);
    	b1.setText("Browse");
        b1.setSize(150,30);
        b1.setLocation(50,50);
        
    	JButton b2 = new JButton(); 
   		b2.setVisible(true);
    	b2.setText("Download All");
    	b2.setSize(150,30);
    	b2.setLocation(50,110);
    	
    	p.add(b1);
    	p.add(b2);
    	
		//EventQueue.invokeLater(new FrameShower(f));		// EventQueue dodajemo za slucaj velikih podataka da se uskladi racunanje i dizanje html forme
		
		/*							>>> Progres bar <<<
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setValue(0);
		progressBar.setMaximum(100);
		
		public void propertyChange(PropertyChangeEvent evt) {
    		if (!done) {
        		int progress = task.getProgress();
        		progressBar.setValue(progress);
    			}
		}
		*/
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
