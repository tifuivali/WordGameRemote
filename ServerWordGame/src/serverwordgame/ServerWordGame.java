/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverwordgame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import wordgame.WordGame;

/**
 *
 * @author tifuivali
 */
public class ServerWordGame {
    
    
   // ServerSocket serverSocket=null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        WordGame game=new WordGame();
        int nrPlayers;
        int i=0;
        System.out.println("Introduceti numarul de jucatori:");
        Scanner scan=new Scanner(System.in);
        nrPlayers=scan.nextInt();
        try {
            ServerSocket serverSocket=new ServerSocket(4545, 5);
            System.out.println("Server running..");
            System.out.println("IP:"+serverSocket.getInetAddress().getHostName());
            System.out.println("Port:"+serverSocket.getLocalPort());
            System.out.flush();
            while(i<nrPlayers)
            {
            Socket curentPlayerSocket=serverSocket.accept();
            Socket notifySocket=serverSocket.accept();
            game.addJoinPlayer(new Player(game, curentPlayerSocket,notifySocket));
            new Thread(game.getPlayers().get(i)).start();
            i++;
            }
            game.startGame();
        } catch (IOException ex) {  
            System.out.println("Server error starting..");
            System.out.println(ex.getMessage());
        }
       
    }
    
}
