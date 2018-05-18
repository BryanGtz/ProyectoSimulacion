/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Ventana v; //v tiene de atributo un objeto de tipo reloj, el cual tiene la hora desde 0 hasta 150 horas
    
    public Escenario(Ventana v){
        this.v = v;
        setLayout(null);
        b = new Bus(this, 150,75);
        add(b);
    }
    
    //Animacion
    @Override
    public void run() {
        while (iniciar) {
            b.setBounds(b.getX()+2,b.getY(),150,75);
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
        
    }
    
}
