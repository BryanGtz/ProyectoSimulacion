/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author HP
 */
public class VariableAleatoria {
    
    static final String PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    Pseudoaleatorio A1 = new Pseudoaleatorio(PATH+"\\Valores Prueba Poker.txt",0);
    Pseudoaleatorio A2 = new Pseudoaleatorio(PATH+"\\Valores Prueba Poker.txt",1);
    Pseudoaleatorio A3 = new Pseudoaleatorio(PATH+"\\Valores Prueba Poker.txt",2);
    
    //**********Algoritmos para generar variables aleatorias discretas**********
    public int bernoulli(double p){
        //Generar un numero aleatorio R ~ U (0,1)
        double R = A1.getNum();
        //Obtener la variable aleatoria
        //Si R<=p, X = 1
        if(R<=p){
            return 1;
        }
        //de lo contrario x = 0
        else{
            return 0;
        }
    }

    //**********Algoritmos para generar variables aleatorias continuas**********
    public double Uniforme(double a, double b){
        //El metodo getNum genera el numero pseudoaleatorio(R ~ U(0,1))
        return a+((b-a)*A2.getNum());
    }
    
    public double Exponencial(double media){
        //El metodo getNum genera el numero pseudoaleatorio(R ~ U(0,1))
        return ((-media)*Math.log(A3.getNum()));
    }
}
