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
    Hora horaLlegadaSigBus;
    
    public Escenario(Reloj r){
        setLayout(null);
        this.r = r;
        this.r.setBounds(800,0,200,100);
        add(this.r);
        filaInspeccion = new Fila();
        filaReparacion = new Fila();
        double exp = va.Exponencial(120); //se genera la variable aleatoria exponencial
        horaLlegadaSigBus = new Hora(exp); //se guarda la hora que llega el primer bus
        System.out.println(horaLlegadaSigBus);
    }
    
    //Animacion
    @Override
    public void run() {        
        while(true){
            if (iniciar) {
                System.out.println("inicia hilo de escenario");
                if(r.hora.equals(horaLlegadaSigBus)){
                    Bus b = new Bus(this, (int)(Math.random()*1000), (int)(Math.random()*1000));
                    add(b);
                    System.out.println("entro");
                }
            }
        }
    }
    
}
