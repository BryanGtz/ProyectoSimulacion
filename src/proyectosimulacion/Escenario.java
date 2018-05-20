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
    EstacionInspeccion eIns;
    
    public Escenario(Reloj r){
        setLayout(null);
        this.r = r;
        this.r.setBounds(800,0,200,100);
        add(this.r);
        filaInspeccion = new Fila();
        filaReparacion = new Fila();
        eIns = new EstacionInspeccion();
        double exp = va.Exponencial(120); //se genera la variable aleatoria exponencial
        horaLlegadaSigBus = new Hora(exp); //se guarda la hora que llega el primer bus
        
    }
    
    //Animacion
    @Override
    public void run() {        
        while(iniciar){
            if(r.hora.equals(horaLlegadaSigBus)){ //se comprueba que la hora de llegada siguiente ya llegó
                System.out.println("Bus 1\nHora de llegada:"+horaLlegadaSigBus);
                Bus b = new Bus(this, 20, 20); //se hace llegar al bus
                filaInspeccion.fila.offer(b); //se añade el autobus a la fila
                
                double exp = va.Exponencial(120);
                Hora h = new Hora(exp);
                System.out.println(h);
                horaLlegadaSigBus = horaLlegadaSigBus.mas(h);
                System.out.println("Siguiente hora: "+horaLlegadaSigBus);
            }
            if(eIns.libre){
                eIns.addBus(filaInspeccion.fila.poll());
                System.out.println("Hora de inicio de inspeccion: "+r.hora);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {

            }
        }
    }
    
}
