/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientwordgame;

import gui.PlayerForm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author tifuivali
 */
public class PlayerWordGame {

    private PlayerForm player_ui=null;
    private Socket gameSocket=null;
    private String nume=null;
    private TimeKeeper timeKeeper=null;
    private  Socket nitifySocket=null;
    
    public  PlayerWordGame(PlayerForm playerUi,Socket connection,Socket notify,String nume)
    {
        this.player_ui=playerUi;
        this.nitifySocket=notify;
        this.gameSocket=connection;
        this.nume=nume;
        this.timeKeeper=new TimeKeeper(player_ui.getTimeLabel()); 
        timeKeeper.Start();
    }
    
    
    
    public void sendNumeToRemote() throws IOException
    {
        PrintWriter writer=new PrintWriter(gameSocket.getOutputStream());
        writer.println(this.nume);
        writer.flush();
    }
    
    public void waitOponents() throws IOException
    {
        player_ui.getGameStatus().setText("Wait opponents..");
        BufferedReader reader=new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
        if(reader.readLine().equals("start"))
            player_ui.getGameStatus().setText("Start!");   
    }
    
    public void stopTimer()
    {
        timeKeeper.Stop();
    }
    
    public void SubmitWord()
    {
        try{
            PrintWriter writer=new PrintWriter(gameSocket.getOutputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
            writer.println("submit");
            writer.flush();
            writer.println(player_ui.getWord());
            writer.flush();
            String res=reader.readLine();
            if(res==null)
                return;
            if(res.equals("notcorect"))
                player_ui.getGameStatus().setText("Cuvantul este incorect!");
            else 
            {player_ui.getGameStatus().setText("Cuvant corect!");
            res=reader.readLine();
            player_ui.setTitles(res);
            String score=reader.readLine();
            player_ui.setScore(score);
            }
            
        }
       catch(IOException e)
        {
            JOptionPane.showMessageDialog(player_ui, "Eroare la submit.."+e.getMessage());
        }
                
                
    }
    
    public String getTitlesFromRemote()
    {
        String result=null;
        try
        {
            BufferedReader reader=new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
            PrintWriter writer=new PrintWriter(gameSocket.getOutputStream());
            writer.println("gettitles");
            writer.flush();
            result=reader.readLine(); 
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(player_ui, "Eraoare getting titles from remote..","Error",JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * @return the nume
     */
    public String getNume() {
        return nume;
    }

    /**
     * @param nume the nume to set
     */
    public void setNume(String nume) {
        this.nume = nume;
    }
    
    
 
}
