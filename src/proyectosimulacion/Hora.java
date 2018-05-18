/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Hora {
    int hora;
    int minutos;
    int segundos;
    
    /**
     * Constructor que inicia en 0 la hora, minutos, segundos
    */
    
    public Hora(){
        hora = 0;
        minutos = 0;
        segundos = 0;
    }
    
    public Hora(double min){
        hora = (int)min/60;
        minutos = (int) Math.floor(min%60);
        segundos = (int)(min*60)%60;
    }
    
    public boolean menorQue(Hora h){
        if(this.hora<h.hora)
            return true;
        else if(this.hora==h.hora){
            if(this.minutos<h.minutos)
                return true;
            else if(this.minutos==h.minutos)
                return this.segundos<h.segundos;
            else
                return false;
        }
        else
            return false;
    }
    
    public Hora mas(Hora h){
        
        h.segundos += this.segundos;
        return h;
    }
    
    public Hora mas(int seg){
        if(segundos+seg>59){
            minutos++;
            segundos = 0;
        }
        else{
            segundos+=seg;
        }
        if(minutos>59){
            hora++;
            minutos = 0;
        }
        return this;
    }
    
    @Override
    public String toString(){
        return hora+":"+minutos+":"+segundos;
    }
    
}
