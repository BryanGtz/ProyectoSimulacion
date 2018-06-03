/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

/**
 *
 * @author User
 */
public interface Estacion {
    
    public void addBus(Bus b, Hora inicio);
    public Bus removeBus();
}
