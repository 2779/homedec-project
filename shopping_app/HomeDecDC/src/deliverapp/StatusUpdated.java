package deliverapp;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusUpdated implements ActionListener {
	
	int rowidd;int i;
	JFrame panel_statusupdated;
	JLabel tick_image_lab;
	JLabel lab_statusupdted;
	JButton but_done;
	JButton but_set_transac;
	Login parent=null;
	final static String path="C:\\Users\\niyaz\\OneDrive\\Documents\\bootcampexercises\\55-27aug2021projectpresent\\project\\shopping_app\\shopping_app\\img\\status.wav";
	public StatusUpdated(int rowid,Login p) {
		Timer t=new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
				
				}
				
			
		});
		
	
		rowidd=rowid;
		panel_statusupdated=new JFrame("status updated");
		
		tick_image_lab=new JLabel(new ImageIcon(new ImgPack("C:\\Users\\niyaz\\OneDrive\\Documents\\bootcampexercises\\55-27aug2021projectpresent\\project\\shopping_app\\shopping_app\\img\\stausdone.jpg").getImage(25, 25)));
		tick_image_lab.setBounds(50, 50, 25, 25);
		
		lab_statusupdted=new JLabel("Status Updated Successfully");
		lab_statusupdted.setBounds(80, 50, 300, 30);
		
		but_done=new JButton("Done");
		but_done.setBounds(70, 150, 80, 30);
		but_done.addActionListener(this);
		
		but_set_transac=new JButton("Set Transaction id");
		but_set_transac.setBounds(190, 150, 150, 30);
		but_set_transac.addActionListener(this);
		
		
		panel_statusupdated.add(tick_image_lab);
		panel_statusupdated.add(lab_statusupdted);
		panel_statusupdated.add(but_done);
		panel_statusupdated.add(but_set_transac);
		
		panel_statusupdated.setLayout(null);
		panel_statusupdated.setBounds(250,200,400,300);
		panel_statusupdated.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but_done) panel_statusupdated.dispose();
		if(e.getSource()==but_set_transac) {
			panel_statusupdated.dispose();
			try {
				new Settrans(rowidd,parent);
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
	}
}
