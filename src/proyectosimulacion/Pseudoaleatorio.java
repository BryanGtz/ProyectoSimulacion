/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryan
 */
public class Pseudoaleatorio {
    private int A;
    private int C;
    private int Xn;
    private int M;
    
    public Pseudoaleatorio(String Ruta, int num){
        try {
            BufferedReader br = new BufferedReader(new FileReader(Ruta));
            String linea = "";
            while ((linea=br.readLine())!=null) {
                if(linea.matches("[\\d\\t]+")){ 
                    for (int i = 0; i < num; i++) {
                        linea = br.readLine();
                    }
                    String[] split = linea.split("\t");
                    A = Integer.parseInt(split[0]);
                    C = Integer.parseInt(split[1]);
                    Xn = Integer.parseInt(split[2]);
                    M = Integer.parseInt(split[3]);
                    for (int i = 0; i < 50; i++) {
                        getNum();
                    }
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo");
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo");
            System.exit(1);
        }
    }
    
    public double getNum(){
        Xn = (A*Xn+C)%M;
        return Xn*1.0/M;
    }
    
}