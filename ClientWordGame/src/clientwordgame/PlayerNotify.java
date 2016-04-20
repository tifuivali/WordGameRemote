package clientwordgame;

import gui.PlayerForm;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            String linkString="";
            while(!strNotify.equals("finish"))
            {
                strNotify=reader.readLine();
                
                if(strNotify==null)
                    JOptionPane.showMessageDialog(player_ui, "Jocul s-a termnat!Serverul a fost oprit.",
                                                   "Finished",JOptionPane.INFORMATION_MESSAGE);
                if(strNotify.equals("message"))
                {   
                    String message=reader.readLine();
                    player_ui.addNotifyMessage(message);
                }  
                else{
                    player_ui.setSubmitButtonEnnabble(false);
                    linkString=reader.readLine();
                    player.stopTimer();
                    JOptionPane.showMessageDialog(player_ui, "Jocul s-a termnat! Vizualizati Scorul!",
                                                   "Finished",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().browse(new URI(linkString));
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare read notify");
        } catch (URISyntaxException ex) {
            Logger.getLogger(PlayerNotify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
