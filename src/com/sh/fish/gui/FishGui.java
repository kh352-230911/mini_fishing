package com.sh.fish.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FishGui extends JFrame{
	
	private String imagePath = "";
	private MyPanel panel = null;
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public FishGui(String path){
		setTitle(path);
		imagePath = getTitle();
		panel = new MyPanel(imagePath);
		setContentPane(panel);
		
		
		setSize(300, 300);
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		private String path = "";
		private ImageIcon imgIcon = new ImageIcon(imagePath);
		private Image img = imgIcon.getImage();
		
		MyPanel(String path){
			this.path = path;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
				g.drawImage(img, 20, 20, getWidth()-40, getHeight()-40, this);
		}
	}
	
	
}
