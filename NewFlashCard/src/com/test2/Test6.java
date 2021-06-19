package com.test2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class Test6 {

	public static void main(String[] args) {
		String voc="barrier";
		String url=String .format("https://s.yimg.com/bg/dict/ox/mp3/v1/%s@_us_1.mp3", voc);
		try {
			downloadUsingStream(url,String .format( "c:/test/%s.mp3",voc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		final JFXPanel fxPanel = new JFXPanel();
		
		String bip =String.format("c:/test/%s.mp3",voc);
		File f=new File(bip);
		System.out.println(f.exists());	;
		Media hit = new Media(f.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();

	} 
	

	 
    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }


}
