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
    VariableAleatoria va = new VariableAleatoria();
    Reloj r;
    Fila filaInspeccion;
    Fila filaReparacion;
    Thread t;
    
    public Escenario(){
        setLayout(null);
        r = new Reloj(this);
        r.setBounds(800,0,200,100);
        add(r);
        t = new Thread(r);
        filaInspeccion = new Fila();
        filaReparacion = new Fila();
    }
    
    //Animacion
    @Override
    public void run() {
        while (iniciar) {
            t.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
        
    }
    
}
