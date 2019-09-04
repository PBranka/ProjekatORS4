import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.beans.EventHandler;
import java.awt.event.*;
import javax.swing.SwingUtilities;
public class TestJ extends JFrame 
{
	    
 public static void main(String[]args){
 	
 		Program.getParameters ("C:\\Klijenti\\Parametri.txt");
		Program pr = new Program ();
		
 		JPanel panel = new JPanel(null);
		JFrame f = new JFrame("Torrent"); 
        f.setSize(500,pr.brKlijenata*200);		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(panel);
	//ikonica	
		  try {
            URL resource = f.getClass().getResource("/download-icon.png");
            BufferedImage image = ImageIO.read(resource);
            f.setIconImage(image);
		      }
          catch (IOException e) {
            e.printStackTrace();
              }
              
            JButton d=new JButton("Download");
        	panel.add(d);	
	    	d.setBounds( 150,100,150,50);
   		JProgressBar[] niz =new JProgressBar[pr.brKlijenata] ;
		JLabel [] niz2=new JLabel[pr.brKlijenata];
		for(int i=0;i<niz.length;i++){
			niz[i]=new JProgressBar(0,100);
			panel.add(niz[i]);
			niz2[i]=new JLabel("Klijent "+i);
		    panel.add(niz2[i]);
			niz2[i].setBounds(100,(i+2)*100,100,50);
			niz[i].setBounds(200,(i+2)*100,100,50);
		}   
			f.setVisible(true);


   
   	//	panel.revalidate();
   	//	panel.repaint();
   	
   	

		       
			
 	


	pr.klijenti.get (0).procitaj (); // prvi klijent preuzima sadrzaj
        //slusac dugmeta
         ActionListener klik = new ActionListener() {
       @Override
        public void actionPerformed(ActionEvent e) {  
        	ProgramRunnable p = new ProgramRunnable (pr);
    			while (!pr.completed ()) // dok svi klijenti ne preuzmu sadrzaj
		{
				p.run(); 
		//	Thread t=new Thread(p);
		//	t.start ();
		for (int i=0;i<pr.brKlijenata;i++){
		
				niz[i].setValue(pr.getProgress(i));
			  }
		} 
					    	    	// Prenosi sadrzaj svakog klijenta u odgovarajuci direktorijum.S
		   for (int s = 0; s < pr.klijenti.size (); s++)
			pr.klijenti.get (s).inDirectory ();	
      } }	;			
     	d.addActionListener(klik);
     				
		}
 

 }	
 
