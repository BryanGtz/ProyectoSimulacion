/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

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
    Hora horaLlegadaBus;
    EstacionInspeccion eIns;
    EstacionReparacion eRep1;
    EstacionReparacion eRep2;
    int numBus = 0;
    int mediaExpLlegada = 120;
    int espera = 10000;
    
    public Escenario(Reloj r){
        setLayout(null);
        this.r = r;
        this.r.setBounds(800,0,200,100);
        add(this.r);
        filaInspeccion = new Fila(250, 100,this);
        filaInspeccion.setLocation(0,0);
        add(filaInspeccion);
        filaReparacion = new Fila(250,100, this);
        filaReparacion.setLocation(350,200);
        add(filaReparacion);
        eIns = new EstacionInspeccion(100,100);
        eIns.setLocation(400,0);
        add(eIns);
        eRep1 = new EstacionReparacion(100,100);
        eRep1.setLocation(700,150);
        add(eRep1);
        eRep2 = new EstacionReparacion(100,100);
        eRep2.setLocation(700,250);
        add(eRep2);
        double exp = va.Exponencial(mediaExpLlegada); //se genera la variable aleatoria exponencial
        horaLlegadaBus = new Hora(exp); //se guarda la hora que llega el primer bus
    }
    
    //Animacion
    @Override
    public void run() {
        while(iniciar){
            while(iniciar&&r.hora.menorQue(new Hora(9600))){
                //se comprueba que la hora de llegada siguiente ya llegó
                if(r.hora.equals(horaLlegadaBus)){ 
                    numBus++;
                    System.out.println("Bus "+numBus+"\n"
                            + "Hora de llegada:"+horaLlegadaBus);
                    //Hacer llegar al bus. Retorna la siguiente hora
                    Hora h = filaInspeccion.llegada(numBus,mediaExpLlegada, horaLlegadaBus);
                    horaLlegadaBus = horaLlegadaBus.mas(h);
                    validate();
                    repaint();
                }
                //si la estacion de inspeccion ya esta libre, se extrae un bus
                if(eIns.libre&&!filaInspeccion.fila.isEmpty()){
                    System.out.println("Hora de inicio de inspeccion: "+r.hora);
                    filaInspeccion.salida(eIns,r.hora);
                    validate();
                    repaint();
                }
                //se comprueba si ya terminó la inspeccion del bus
                if(!eIns.libre&&r.hora.equals(eIns.horaSalida)){
                    int reparacion = va.bernoulli(0.30);
                    //si necesita reparacion
                    if(reparacion==1){
                        System.out.println("El autobus "+eIns.bus.num+" necesita reparacion");
                        eIns.removeBus(true, filaReparacion);
                        filaReparacion.fila.offer(eIns.bus);
                        eIns.bus = null;
                        eIns.libre = true;
                    }
                    //si no necesita reparacion
                    else{
                        System.out.println("El autobus no necesita reparacion");
                        eIns.bus = null;
                        eIns.libre = true;
                    }
                    validate();
                    repaint();
                }
                if(eRep1.libre&&!filaReparacion.fila.isEmpty()){
                    System.out.println("Hora de inicio de reparación: "+r.hora);
                    Bus b = filaReparacion.fila.poll();
                    eRep1.addBus(b,r.hora);
                    validate();
                    repaint();
                }
                else if(eRep2.libre&&!filaReparacion.fila.isEmpty()){
                    System.out.println("Hora de inicio de reparación: "+r.hora);
                    Bus b = filaReparacion.fila.poll();
                    eRep2.addBus(b,r.hora);
                    validate();
                    repaint();
                }
                if(!eRep1.libre&&r.hora.equals(eRep1.horaSalida)){
                    System.out.println("Terminó la reparacion");
                    eRep1.liberar();
                    validate();
                    repaint();
                }
                if(!eRep2.libre&&r.hora.equals(eRep2.horaSalida)){
                    System.out.println("Terminó la reparacion");
                    eRep2.liberar();
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
                //Mantiene ocupado el hilo por 10000 nanosegundos
                esperar(espera);
            
            }
            System.out.println("NUMERO TOTAL DE BUSES: "+numBus);
            System.out.println("PROMEDIO DE ESPERA EN LA FILA DE INSPECCION: "
                    + filaInspeccion.sumaEsperas.entre(numBus));
            System.out.println("PROMEDIO DE ESPERA EN LA FILA DE REPARACION: "
                    + filaReparacion.sumaEsperas.entre(numBus));
            break;
        }
    }
    
    public void esperar(int nanosegundos){
        long espera = System.nanoTime()+nanosegundos;
        while (espera>System.nanoTime()) {}
    }
    
    public void setEspera(int nanosegundos){
        espera = nanosegundos;
    }
    
}
