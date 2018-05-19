/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Escenario extends JPanel implements Runnable {

    boolean iniciar=false;
    Bus b;
    VariableAleatoria va = new VariableAleatoria();
    Reloj r;
    
    public Escenario(){
        setLayout(null);
        b = new Bus(this, 150,75);
        add(b);
        r = new Reloj(this);
        r.setBounds(100,0,200,100);
        
        add(r);
    }
    
    //Animacion
    @Override
    public void run() {
        while (iniciar) {
            Thread t = new Thread(r);
            t.start();
            b.setBounds(b.getX()+2,b.getY(),150,75);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
        
    }
    
}
