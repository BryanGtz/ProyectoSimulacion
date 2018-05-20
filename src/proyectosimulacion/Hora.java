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
public class Hora {
    int hora;
    int minutos;
    int segundos;
    
    /**
     * Constructor que inicia en 0 las horas, minutos y segundos
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
    
    /**
    * Método que comprueba si la hora es menor que otra
    * @param h Hora a comparar
    * @return true si this.hora es menor que h
    * false de otra forma
    */
    
    public boolean menorQue(Hora h){
        if(this.hora<h.hora)
            return true;
        else if(this.hora==h.hora)
            if(this.minutos<h.minutos)
                return true;
            else if(this.minutos==h.minutos)
                return this.segundos<h.segundos;
            else
                return false;
        else
            return false;
    }
    
    public Hora mas(Hora h){
        if(segundos+h.segundos>59){
            minutos++;
            segundos = 0;
        }
        else
            segundos+=h.segundos;
        if(minutos+h.minutos>59){
            hora++;
            minutos = 0;
        }
        else
            minutos+=h.minutos;
        hora+=h.hora;
        return this;
    }
    
    public Hora mas(int seg){
        if(segundos+seg>59){
            minutos++;
            segundos = 0;
        }
        else
            segundos+=seg;
        if(minutos>59){
            hora++;
            minutos = 0;
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Hora)){
            return false;
        }
        else{
            Hora h = (Hora) obj;
            return (hora==h.hora&&minutos==h.minutos&&segundos==h.segundos);
        }
    }
    
    @Override
    public String toString(){
        String texto = "";
        texto+= (hora<10)?"0"+hora+":":hora+":";
        texto+= (minutos<10)?"0"+minutos:minutos;
        //texto+= (segundos<10)?":0"+segundos:":"+segundos;
        return texto;
    }
    
}
