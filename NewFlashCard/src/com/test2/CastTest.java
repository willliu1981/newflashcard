package com.test2;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class CastTest extends JFrame {
	
	
	static class Book{
		 String name;
	}

	public static void main(String[] args) {
		CastTest frame=new CastTest();
		frame .setVisible(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		JDesktopPane desk=new  JDesktopPane();
		frame.getContentPane().add(desk);
		
		JInternalFrame inter=new  JInternalFrame();
		inter.getContentPane(). add(new JButton("xxxx"));
		inter.pack();
		inter.setVisible(true);
		desk.add(inter);
		
		frame.pack();
		
		
		
		
		
		
		//frame.getContentPane().add(new JButton("aaa"));
		
		
		
		
		
		
		
		
		//JOptionPane.showConfirmDialog(null, "xxx");
	
		
		JOptionPane pane=new  JOptionPane();
		pane.setInputMap(0, null);
		//JOptionPane.showInternalConfirmDialog(pane, "aaa");
	}

}
