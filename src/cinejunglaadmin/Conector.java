/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin;
import javax.swing.*;   //Para JFrame
import java.sql.*; 
/**
 *
 * @author teban
 */
public class Conector {
       Connection con;
    public Connection conexion(){

        try{
            //Para decirle al programa que base de datos conectarse
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinejungla","root","2b6c3bc2");
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Conexion no establecida");
        }

        return con;
    }
}
