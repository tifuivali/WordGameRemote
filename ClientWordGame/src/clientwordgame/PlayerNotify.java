package clientwordgame;

import gui.PlayerForm;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tifuivali
 */
public class PlayerNotify implements Runnable{
    
    private PlayerForm  player_ui=null;
    private Socket notifySocket=null;
    private PlayerWordGame player=null;
    public PlayerNotify(PlayerForm player_Form,Socket notifySock,PlayerWordGame player)
    {
        this.notifySocket=notifySock;
        this.player_ui=player_Form;
        this.player=player;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(notifySocket.getInputStream()));
            String strNotify="3";
            while(!strNotify.equals("finish"))
            {
                strNotify=reader.readLine();
                if(strNotify.equals("message"))
                {   
                    String message=reader.readLine();
                    player_ui.addNotifyMessage(message);
                }  
                else{
                    player_ui.setSubmitButtonEnnabble(false);
                    player.stopTimer();
                    JOptionPane.showMessageDialog(player_ui, "Jocul s-a termnat! Vzitati ..pentru a putea vizualiza scorul",
                                                   "Finished",JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare read notify");
        }
    }
    
}
