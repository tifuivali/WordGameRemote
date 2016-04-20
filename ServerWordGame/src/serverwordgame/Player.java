/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverwordgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import wordgame.WordGame;

/**
 *
 * @author tifuivali
 */
public class Player implements Runnable{

    private WordGame game=null;
    private Socket sockPlayer=null;
    private Socket notifySockPlayer=null;
    private String nume=null;
    private int score=0;
    public  Player(WordGame game,Socket player,Socket notifySock)
    {
        this.game=game;
        this.sockPlayer=player;
        this.notifySockPlayer=notifySock;
    }
    
    @Override
    public void run() {
        
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(getSockPlayer().getInputStream())))
        {   
            this.nume=reader.readLine();
            System.out.println("Nume:"+this.nume);
            while(!game.isFinished())
            {
               System.out.println("Wait command.."); 
               String command=reader.readLine();
                System.out.println("Primit comanda "+command);
               parseComand(command);
            }
        }
        catch(IOException e){
            System.out.println("Eroare citire comanda.. "+e.getMessage());
        }
    }
    
    private void parseComand(String comand)
    {
        if(comand.equals("gettitles"))
        {
            comandGetTitles();
        }
        if(comand.equals("submit"))
        {
            comandSubbmit();
        }
        if(comand.equals("getanothertitles"))
        {
            comandGetAnotherTitles(this);
        }
    }
    
    private void comandSubbmit() 
    {
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(getSockPlayer().getInputStream()));
           // PrintWriter writer=new PrintWriter(getSockPlayer().getOutputStream());
            String word=reader.readLine();
            game.SubmitWord(this, word);
            
        }
        catch(IOException e)
        {
            System.out.println("Exceptie submit.."+e.getMessage());
        }
    }
  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.getSockPlayer());
        return hash;
    }
    
    private void comandGetTitles()
    {
        try {
                PrintWriter writer=new PrintWriter(sockPlayer.getOutputStream());
                writer.println(game.getSevenStringTitles());
                writer.flush();
                
               
            }
            catch(IOException ex)
            {
                System.out.println("Eroare get titles..");       
            }
    }
    
    private void comandGetAnotherTitles(Player player)
    {
        try {
                PrintWriter writer=new PrintWriter(sockPlayer.getOutputStream());
                String titlesString=game.getSevenStringTitles();
                if(titlesString.length()<=0)
                {
                    game.notifyPlayersGameFinished();
                     GameReprezentation reprezentation=new GameReprezentation(game);
                     reprezentation.makeReprezentation();
                     reprezentation.uploadReprezentation();
                      System.out.println("am realizat reprezentarea");
                     game.setFinished(true);
                }
                writer.println(titlesString);
                writer.flush();
                if(player.getScore()>7)
                   player.setScore(player.getScore()-7); 
                else player.setScore(0);
                              
                writer.println(player.getScore());
                writer.flush();
               
            }
            catch(IOException ex)
            {
                System.out.println("Eroare get titles..");       
            }
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other==null)
            return false;
        if(other instanceof Player)
        {
            Player play=(Player)other;
            if(nume.equals(play.getNume()))
                return true;
        }
        return  false;
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

    /**
     * @return the sockPlayer
     */
    public Socket getSockPlayer() {
        return sockPlayer;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the notifySockPlayer
     */
    public Socket getNotifySockPlayer() {
        return notifySockPlayer;
    }
    
    
    
}
