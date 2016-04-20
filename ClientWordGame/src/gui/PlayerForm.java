/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clientwordgame.PlayerNotify;
import clientwordgame.PlayerWordGame;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author tifuivali
 */
public class PlayerForm extends javax.swing.JFrame {

    
    PlayerWordGame wordGamePlayer=null;
    /**
     * Creates new form PlayerForm
     */
    public PlayerForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        text_ip_adress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        text_port = new javax.swing.JTextField();
        button_connect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        label_conn_status = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        text_nume = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        text_titles = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        label_time = new javax.swing.JLabel();
        text_word = new javax.swing.JTextField();
        label_status_game = new javax.swing.JLabel();
        button_Submit = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        label_score = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_notify = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Conect to:");

        text_ip_adress.setText("127.0.0.1");

        jLabel2.setText("IP:");

        jLabel3.setText("PORT:");

        text_port.setText("4545");

        button_connect.setText("Connect");
        button_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_connectActionPerformed(evt);
            }
        });

        jLabel4.setText("Connection Status:");

        label_conn_status.setText("-");

        jLabel5.setText("Nume:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(202, 53, 53));
        jLabel6.setText("Game:");

        jLabel7.setText("Your Curent Titles:");

        text_titles.setEditable(false);

        jLabel8.setText("Insert Word:");

        jLabel9.setText("Time:");

        label_time.setText("----");

        label_status_game.setText("-----");

        button_Submit.setText("Submit");
        button_Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SubmitActionPerformed(evt);
            }
        });

        jLabel10.setText("Score:");

        label_score.setText("0");

        text_notify.setColumns(20);
        text_notify.setRows(5);
        jScrollPane1.setViewportView(text_notify);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(2, 2, 2)
                                        .addComponent(text_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button_connect))
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(text_ip_adress, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(label_conn_status, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_time)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(label_status_game))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(text_nume, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(text_titles))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(text_word, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(button_Submit))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(123, 123, 123)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label_score)))))
                        .addGap(0, 59, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_ip_adress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(text_nume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_connect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_conn_status)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(label_time)
                    .addComponent(jLabel10)
                    .addComponent(label_score))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_titles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_word, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Submit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_status_game)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getTimeLabel()
    {
        return label_time;
    }
    public  JLabel getGameStatus()
    {
        return label_status_game; 
    }
    public String getWord()
    {
        return text_word.getText();
    }
    
    public void setTitles(String t)
    {
        text_titles.setText(t);
    }
    public void setScore(String score)
    {
        label_score.setText(score);
    }
    public void addNotifyMessage(String message)
    {
        text_notify.append(message+"\r\n");
    }
    public void setConectionButtonEnabble(boolean value)
    {
        button_connect.setEnabled(value);
    }
    public void setSubmitButtonEnnabble(boolean value)
    {
        button_Submit.setEnabled(value);
    }
    public String getTitles()
    {
        return text_titles.getText();
    }
    Socket playerClientSocket;
    Socket playerNotifySocket;
    private void button_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_connectActionPerformed
        
        if(text_nume.getText().length()<=0)
        {
            JOptionPane.showMessageDialog(this, "Nu ati introdus nici un nume!","Nume",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
       
        try{
           playerClientSocket=new Socket(text_ip_adress.getText(),Integer.parseInt(text_port.getText()));
           playerNotifySocket=new Socket(text_ip_adress.getText(),Integer.parseInt(text_port.getText()));
            wordGamePlayer=new PlayerWordGame(this, playerClientSocket,playerClientSocket,text_nume.getText());
            wordGamePlayer.sendNumeToRemote();
            label_conn_status.setText("Connected!");
            wordGamePlayer.waitOponents();
            text_titles.setText(wordGamePlayer.getTitlesFromRemote());
            PlayerNotify playerNotify=new PlayerNotify(this, playerNotifySocket,wordGamePlayer);
            new Thread(playerNotify).start();
             setConectionButtonEnabble(false);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Conectare esuata!","Eroare conectare",JOptionPane.ERROR_MESSAGE);
         setConectionButtonEnabble(true);
        }
       
    }//GEN-LAST:event_button_connectActionPerformed

    private void button_SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SubmitActionPerformed
        
       wordGamePlayer.SubmitWord();
       text_word.setText("");
    }//GEN-LAST:event_button_SubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Submit;
    private javax.swing.JButton button_connect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_conn_status;
    private javax.swing.JLabel label_score;
    private javax.swing.JLabel label_status_game;
    private javax.swing.JLabel label_time;
    private javax.swing.JTextField text_ip_adress;
    private javax.swing.JTextArea text_notify;
    private javax.swing.JTextField text_nume;
    private javax.swing.JTextField text_port;
    private javax.swing.JTextField text_titles;
    private javax.swing.JTextField text_word;
    // End of variables declaration//GEN-END:variables
}
