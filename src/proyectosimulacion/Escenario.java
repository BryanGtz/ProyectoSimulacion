/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.util.ArrayList;
import java.util.List;
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
    List<Bus> buses = new ArrayList();
    int numBus = 0;
    int mediaExpLlegada = 120;
    int espera = 10000;
    int numReplica = 1;
    
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
        
//        System.out.println("Primer hora: "+horaLlegadaBus);
    }
    
    public void setMediaExpLlegadas(int m_e_ll){
        mediaExpLlegada = m_e_ll;
    }
    
    //Animacion
    @Override
    public synchronized void run() {
        while(iniciar){
            double exp = va.Exponencial(mediaExpLlegada); //se genera la variable aleatoria exponencial
            horaLlegadaBus = new Hora(exp); //se guarda la hora que llega el primer bus
            while(iniciar&&r.hora.menorQue(new Hora(9600))){
                //se comprueba que la hora de entrada siguiente ya llegó
                if(r.hora.equals(horaLlegadaBus)){ 
                    numBus++;
                    Bus b = new Bus(this,100,50,numBus,horaLlegadaBus);//crear el bus
                    buses.add(b);//Se añade a la lista de buses del sistema
                    filaInspeccion.entrada(b);//entra al bus a la fila de inspeccion
                    //se genera la sig va de llegada del bus
                    Hora h = new Hora(va.Exponencial(mediaExpLlegada));
                    //se suma la hora actual con la de la va
                    horaLlegadaBus = horaLlegadaBus.mas(h);
                    //se actualiza el panel
//                    validate();
//                    repaint();
                }
                //si la estacion de inspeccion ya esta libre, se extrae un bus
                if(eIns.libre&&!filaInspeccion.fila.isEmpty()){
                    //se saca el bus de la fila de inspeccion
                    filaInspeccion.salida(eIns,r.hora);
//                    validate();
//                    repaint();
                }
                //se comprueba si ya terminó la inspeccion del bus
                if(!eIns.libre&&r.hora.equals(eIns.horaSalida)){
                    int reparacion = va.bernoulli(0.30);
                    //si necesita reparacion
                    if(reparacion==1){
                        Bus bus = eIns.removeBus();
                        filaReparacion.entrada(bus);
                        bus.reparacion = 1;
                        bus.llegadaFilaRep = new Hora(r.hora);
                    }
                    //si no necesita reparacion
                    else{
                        Bus bus = eIns.removeBus();
                        bus.salida = new Hora(r.hora);
                    }
//                    validate();
//                    repaint();
                }
                //Si la estacion de reparacion 1 esta libre, se extrae un bus
                if(eRep1.libre&&!filaReparacion.fila.isEmpty()){
                    filaReparacion.salida(eRep1, r.hora);
//                    validate();
//                    repaint();
                }
                //Si no, se comprueba que la estacion de reparacion esté libre
                else if(eRep2.libre&&!filaReparacion.fila.isEmpty()){
                    filaReparacion.salida(eRep2, r.hora);
//                    validate();
//                    repaint();
                }
                if(!eRep1.libre&&r.hora.equals(eRep1.horaSalida)){
//                    System.out.println("Terminó la reparacion");
                    eRep1.removeBus();
//                    validate();
//                    repaint();
                }
                if(!eRep2.libre&&r.hora.equals(eRep2.horaSalida)){
//                    System.out.println("Terminó la reparacion");
                    eRep2.removeBus();
//                    validate();
//                    repaint();
                }
                r.hora = r.hora.mas(1);
                r.reloj.setText(r.hora.toString());
//                System.out.println(r.hora);
                revalidate();
                repaint();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException ex) {
//
//                }
                //Mantiene ocupado el hilo por 10000 nanosegundos
                esperar(espera);
            
            }
            double minUsoEsInsp = 0;
            double minUsoEsRep = 0;
            int busesReparados = 0;
            System.out.println("NUM REPLICA: "+numReplica);
            for(Bus b:buses){
                System.out.println(b);
                busesReparados+=b.reparacion;
                minUsoEsInsp += b.duracionInsp.aMinutos();
                if(b.duracionRep!=null){
                    minUsoEsRep += b.duracionRep.aMinutos();
                }
            }
            System.out.println("NUMERO TOTAL DE BUSES: "+numBus);
            System.out.println("PROMEDIO DE ESPERA EN LA FILA DE INSPECCION: "
                    + filaInspeccion.sumaEsperas.entre(numBus));
            System.out.println("PROMEDIO DE ESPERA EN LA FILA DE REPARACION: "
                    + filaReparacion.sumaEsperas.entre(numBus));
            System.out.println("NUMERO DE AUTOBUSES QUE NECESTARON REPARACION: "
                    + busesReparados);
            System.out.println("LONGITUD MAXIMA DE FILA DE INSPECCION: "
                    +filaInspeccion.longMax);
            System.out.println("LONGITUD MAXIMA DE FILA DE REPARACION: "
                    +filaReparacion.longMax);
            System.out.println("PORCENTAJE DE UTILIZACION DE ESTACION DE INSPECCION: "
                    +minUsoEsInsp*100/9600+"%");
            System.out.println("PORCENTAJE PROMEDIO DE UTILIZACION DE CADA ESTACION DE REPARACION: "
                    +minUsoEsRep/2*100/9600+"%");
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
