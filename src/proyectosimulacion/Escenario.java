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
    int numBus = 0;
    int mediaExpLlegada = 120;
    
    public Escenario(Reloj r){
        setLayout(null);
        this.r = r;
        this.r.setBounds(800,0,200,100);
        add(this.r);
        filaInspeccion = new Fila(250, 100);
        filaInspeccion.setLocation(0,0);
        add(filaInspeccion);
        filaReparacion = new Fila(250,100);
        filaReparacion.setLocation(350,200);
        add(filaReparacion);
        eIns = new EstacionInspeccion(100,100);
        eIns.setLocation(400,0);
        add(eIns);
        double exp = va.Exponencial(mediaExpLlegada); //se genera la variable aleatoria exponencial
        horaLlegadaSigBus = new Hora(exp); //se guarda la hora que llega el primer bus
        
    }
    
    //Animacion
    @Override
    public void run() {        
        while(iniciar){
            while(r.hora.menorQue(new Hora(9600))){
                if(r.hora.equals(horaLlegadaSigBus)){ //se comprueba que la hora de llegada siguiente ya llegó
                    numBus++;
                    System.out.println("Bus "+numBus+"\nHora de llegada:"+horaLlegadaSigBus);
                    Bus b = new Bus(this, 100, 50); //se hace llegar al bus
                    filaInspeccion.fila.offer(b); //se añade el autobus a la fila
                    validate();
                    repaint();
                    double exp = va.Exponencial(mediaExpLlegada);
                    Hora h = new Hora(exp);
                    System.out.println(h);
                    horaLlegadaSigBus = horaLlegadaSigBus.mas(h);
//                    System.out.println("Siguiente hora: "+horaLlegadaSigBus);
                }
                if(eIns.libre&&!filaInspeccion.fila.isEmpty()){
                    System.out.println("Hora de inicio de inspeccion: "+r.hora);
                    Bus b = filaInspeccion.fila.poll();
                    eIns.addBus(b,r.hora);                    
                    validate();
                    repaint();
                }
                if(!eIns.libre&&r.hora.equals(eIns.horaSalida)){
                    int reparacion = va.bernoulli(0.30);
                    if(reparacion==1){
                        System.out.println("El autobus necesita reparacion");
                        filaReparacion.fila.offer(eIns.bus);
                        eIns.bus = null;
                        eIns.libre = true;
                    }
                    else{
                        System.out.println("El autobus no necesita reparacion");
                        eIns.bus = null;
                        eIns.libre = true;
                    }
                    validate();
                    repaint();
                }
                r.hora.mas(1);
                r.reloj.setText(r.hora.toString());
//                System.out.println(r.hora);
                validate();
                repaint();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException ex) {
//
//                }
                //Mantiene ocupado el hilo por 0.01 milisegundos
                long espera = System.nanoTime()+10000;
                while (espera>System.nanoTime()) {                    
                }
            }
        }
    }
    
}
