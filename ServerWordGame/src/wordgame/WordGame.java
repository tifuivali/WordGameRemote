/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dictionar.TreeVocabulary;
import dictionar.Vocabulary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import serverwordgame.GameReprezentation;
import serverwordgame.Player;

/**
 *
 * @author tifui
 */
public class WordGame implements Runnable{
    private final LeterBag leterBag;
    private Vocabulary game_vocabuVocabulary;
    private List<Player> players;
    private boolean finished=false;
    /**
     *Creaza un nou obiect WordGame primind ca 
     * parametru numarul jucatorilor
     */
    public WordGame()
    {
      leterBag=new LeterBag();
      players=new ArrayList<>();
    }
    
    
    private boolean available=true;
    
    public void addJoinPlayer(Player  player)
    {
       players.add(player);
    }
    
    public void removePlayer(Player player)
    {
        for(int i=0;i<getPlayers().size();i++)
        {
            if(getPlayers().get(i).equals(player))
                getPlayers().remove(i);
        }
    }
    public void startGame()
    {
        for(Player player:players)
        {
            PrintWriter writer;
            try {
                writer = new PrintWriter(player.getSockPlayer().getOutputStream());
                writer.println("start");
                writer.flush();
            } catch (IOException ex) {
                System.out.println("eroare start game "+ex.getMessage());
            }
         
        }
    }
    
    public synchronized void SubmitWord(Player player,String word)
    {
        boolean corectWord=checkWord(word);
        while(!available)
        {           
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
                }   
        }
        available=false;
        //cod sincronizat
        System.out.println(corectWord);
        try {
            if(corectWord)
            {  
                System.out.flush();
                 PrintWriter writer=new PrintWriter(player.getSockPlayer().getOutputStream());
                 String strTitles=getSevenStringTitles();
                  if(strTitles.length()<=0)
                  {
                     notifyPlayersGameFinished();
                     GameReprezentation reprezentation=new GameReprezentation(this);
                     reprezentation.makeReprezentation();
                     reprezentation.uploadReprezentation();
                      System.out.println("am realizat reprezentarea");
                     finished=true;
                     return;
                 }
                player.setScore(player.getScore()+word.length()*5);
                writer.println("corect");
                writer.flush();
                writer.println(strTitles);
                writer.flush();
                writer.println(player.getScore()+"");
                writer.flush();
                notifyPlayersWithMessage(player.getNume()+" submited."+"Score:"+player.getScore());
            }
            else {
                PrintWriter writer=new PrintWriter(player.getSockPlayer().getOutputStream());
                writer.println("notcorect");
                writer.flush();
            }
            
           
        } catch (Exception e) {
        }
        available=true;
        notifyAll();
    }
    
    private void notifyPlayersWithMessage(String message) throws IOException
    {
        for(Player player:players)
        {
                PrintWriter writerNotify=new PrintWriter(player.getNotifySockPlayer().getOutputStream());
                writerNotify.println("message");
                writerNotify.flush();
                writerNotify.println(message);
                writerNotify.flush();
         }
    }
    
    public void notifyPlayersGameFinished() throws IOException
    {
        for(Player player:players)
        {
                PrintWriter writerNotify=new PrintWriter(player.getNotifySockPlayer().getOutputStream());
                writerNotify.println("finish");
                writerNotify.flush();
                writerNotify.println("http://fenrir.info.uaic.ro/~vali.tifui/wordgame.html");
                writerNotify.flush();
         }
    }
       
     /**
     * @param args the command line arguments
     */
    private boolean checkWord(String word)
    {
        String singular="null";
        try{
            System.out.println("ajuns 1");
           String continut=readUrl("http://openapi.ro/api/inflections/"+word+".json");
           Gson gson=new Gson();
           Map<String,Object> map=new HashMap<>();
           map=(Map<String, Object>)gson.fromJson(continut, map.getClass());
           singular=(String)map.get("singular");
           System.out.println("singular "+singular);
           
          
           
        }
        catch(IOException | JsonSyntaxException ex)
        {
            System.out.println("exceptie api "+ex.getMessage());
        }
        if(singular==null)
            return false;
        return !singular.equals("null");
        
        
        
    }
    

    
    private  String readUrl(String urlString) throws MalformedURLException, IOException  {
   
        URL url = new URL(urlString);
        StringBuilder builder=new StringBuilder();
      
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                builder.append(inputLine);
            in.close();
            
   
        return builder.toString();
     }
   
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /**
     * Incarcarea dictionarului de cuvunte din fisier.
     * @param String file 
     */
    private void loadDictionaryFromFile(String filePath,int dificulty)
    {
        List<String> words=new ArrayList<>();
        
       
        
        try {
            File file=new File(filePath);
            Scanner scan=new Scanner(file);
            while(scan.hasNextLine())
            {
               String word=scan.nextLine();
               if(word.length()>=dificulty)
                 words.add(word.toUpperCase());
            }
            Collection<String> vocabulary=words;
             game_vocabuVocabulary=new TreeVocabulary(vocabulary);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
     private void setLettersOntable(DefaultTableModel model,char[][] letterDist)
    {
        for(int i=0;i<model.getRowCount();i++)
            model.removeRow(i);
       
        for (char[] letterRow : letterDist) {
           
            String[] strings=new String[letterDist.length];
            for(int i=0;i<letterRow.length;i++)
            {
                strings[i]=""+letterRow[i];
            }
            model.addRow(strings);
           
           
        }
    }


    
    @Override
    public void run() {
        
     
        
    }
    
    
    /**
     * @return the leterBag
     */
    public LeterBag getLeterBag() {
        return leterBag;
    }
    
    public String getSevenStringTitles()
    {
        String result="";
        Title[] titles=leterBag.getSevenRandomTitles();
        for(Title title:titles)
        {
            result+=title.getValue();
        }
        return result;
    }
    
    public String getKStringTitles(int k)
    {
        String result="";
        Title[] titles=leterBag.getKRandomTitles(k);
        for(Title title:titles)
        {
            result+=title.getValue();
        }
        return result;
    }

    /**
     * @return the game_vocabuVocabulary
     */
    public Vocabulary getGame_vocabuVocabulary() {
        return game_vocabuVocabulary;
    }

    /**
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @param finished the finished to set
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    
}
