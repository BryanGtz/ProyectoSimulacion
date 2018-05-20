/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

/**
 *
 * @author HP
 */
public class EstacionInspeccion {
    boolean libre;
    Hora tiempoRevisionActual;
    Bus bus;
    
    public EstacionInspeccion(){
        libre = true;
        tiempoRevisionActual = new Hora();
    }
    
    public void addBus(Bus b){
        if(libre){
            if(b!=null){
                bus = b;
            }
        }
    }
    
}
