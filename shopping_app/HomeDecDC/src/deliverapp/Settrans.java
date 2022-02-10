package deliverapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

//import jaco.mp3.player.MP3Player;
import Network.DI;

public class Settrans {
	JFrame jfr_settrans;
	JProgressBar jpr_generatetranscat;
	int i;
	JLabel lab_generatetrans;
	Login parent=null;
	public Settrans(int orderid,Login p) throws LineUnavailableException {
		parent=p;
		final String path="C:\\Users\\niyaz\\OneDrive\\Documents\\bootcampexercises\\55-27aug2021projectpresent\\project\\shopping_app\\shopping_app\\img\\transactionid.wav";
		
		jfr_settrans=new JFrame("Set Transaction ID");
		jpr_generatetranscat=new JProgressBar(0, 20);
		jpr_generatetranscat.setBounds(82, 82, 150, 30);
		jpr_generatetranscat.setStringPainted(true);
		
		lab_generatetrans=new JLabel("Generating Transaction ID...");
		lab_generatetrans.setBounds(82, 55, 300, 30);
		int trans=new getrans().gettransactionid(orderid);
		
		try {
			parent.objdi.setTransactionId(orderid, trans, parent.EMAIL, parent.PASSWORD);			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Timer t=new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i==100) {
					try {
						AudioInputStream in=AudioSystem.getAudioInputStream(new File(path));
						
						Clip clip = AudioSystem.getClip(); 
			            clip.open(in); 
			            clip.start(); 
			            try {
							Thread.sleep(5000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					jfr_settrans.dispose();
				}
				jpr_generatetranscat.setValue(i++);
			}
		});
		
		jfr_settrans.add(lab_generatetrans);
	    jfr_settrans.add(jpr_generatetranscat);
	    t.start();
		jfr_settrans.setBounds(250, 200, 400, 300);
		jfr_settrans.setLayout(null);
		jfr_settrans.setVisible(true);
	}
}
