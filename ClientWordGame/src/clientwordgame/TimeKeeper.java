
package clientwordgame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author tifui
 */
public class TimeKeeper implements ActionListener{

    private final JLabel labelAfisare;
    private int seconds;
    private int minutes;
    private final Timer timer;
    /**
     * Creaza un TimeKeeper care retine si afiseaza in label timpul scrus.
     * @param label Labelul pe care va fi afisat timpul scurs 
     */
    public TimeKeeper(JLabel label)
    {
        labelAfisare=label;
        timer=new Timer(1000, this);
    }
    
    
    /**
     * Porneste timerul, incepe contorizarea timpului.
     */
    public void Start()
    {
       timer.start();
    }
    /**
     * Opreste timer-ulcontorizarea timpului.
     */
    public void Stop()
    {
        timer.stop();
    }
 
    
    private String toTimeString(int minutes,int seconds)
    {
        String result=new String();
        if(minutes<10)
        {
            result="0"+minutes;
        }
        else result=""+minutes;       
        result+=":";
        if(seconds<10)
        {
            result+="0"+seconds;
        }
        else result+=""+seconds;
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       labelAfisare.setText(toTimeString(minutes, seconds));
       if(seconds+1==60)
       {
           minutes++;
           seconds=0;
       }
       else seconds++;
    }
    
    
}
