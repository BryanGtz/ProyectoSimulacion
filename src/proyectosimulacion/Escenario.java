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
    Hora horaLlegadaSigBus;
    
    public Escenario(){
        setLayout(null);
        r = new Reloj(this);
        r.setBounds(800,0,200,100);
        add(r);
        t = new Thread(r);
        t.start();
        filaInspeccion = new Fila();
        filaReparacion = new Fila();
        double exp = va.Exponencial(120); //se genera la variable aleatoria exponencial
        horaLlegadaSigBus = new Hora(exp);
        System.out.println(horaLlegadaSigBus);
    }
    
    //Animacion
    @Override
    public void run() {
        while (iniciar) {
            r.iniciar = true;
            if(r.hora.equals(horaLlegadaSigBus)){
                Bus b = new Bus(this, (int)(Math.random()*1000), (int)(Math.random()*1000));
                add(b);
                System.out.println("entro");
            }
        }
        
    }
    
}
