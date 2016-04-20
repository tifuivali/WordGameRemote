/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverwordgame;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import wordgame.WordGame;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author tifuivali
 */
public class GameReprezentation {
    
    WordGame game=null;
    File fileReprezentation=null;
    
    
    public GameReprezentation(WordGame game)
    {
        this.game=game;
        fileReprezentation=new File("/home/tifuivali/NetBeansProjects/ServerWordGame/wordgame.html");
    }
    
    public void makeReprezentation()
    {
        List<Player> sortedPlayer=getClasament();
        try {
             Document document=Jsoup.parse(fileReprezentation, "utf-8");
             Element body=document.body();
             body.append("<strong>Clasament:</strong>");
             for(int i=0;i<sortedPlayer.size();i++)
             {
                 body.append("<p>Locul "+(i+1)+" "+sortedPlayer.get(i).getNume()+" "+
                             "  "+sortedPlayer.get(i).getScore()+"</p>");
             }
             
            FileUtils.writeStringToFile(fileReprezentation, document.outerHtml());
             
        } catch (IOException ex) {
            System.out.println("Make reprezentation eror");
        }
    }
    
   public void uploadReprezentation(String url)
   {
       String user = "vali.tifui";
    String password = "adrianx16D";
    String host = "fenrir.info.uaic.ro";
    int port=22;

    String remoteFile="sample.txt";

    try
    {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
        System.out.println("Connection established.");
        System.out.println("Crating SFTP Channel.");
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");
        InputStream out= null;
    out= sftpChannel.get(remoteFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(out));
        String line;
        while ((line = br.readLine()) != null) 
    {
            System.out.println(line);
        }
    br.close();
        sftpChannel.disconnect();
        session.disconnect();
    }
    catch(JSchException | SftpException | IOException e)
{
    System.out.println(e);
}
}
   
    
   private List<Player> getClasament()
   {
       List<Player> sorted=new ArrayList<>();
       for(Player player:game.getPlayers())
       {
           sorted.add(player);
       }
       for(int i=0;i<sorted.size();i++)
       {
           for(int j=i+1;j<sorted.size()-1;j++)
           {
               if(sorted.get(i).getScore()<sorted.get(j).getScore())
               {
                   Player aux=sorted.get(i);
                   sorted.set(i, sorted.get(j));
                   sorted.set(j, aux);
               }
           }
       }
       return sorted;
   }
    
    
}
