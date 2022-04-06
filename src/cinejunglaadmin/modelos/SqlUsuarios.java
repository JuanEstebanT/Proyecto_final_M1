/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

import cinejunglaadmin.Conector;
import java.sql.*; 

/**
 *
 * @author teban
 */
public class SqlUsuarios extends Conector{
    public boolean registrar(Usuarios usr){
       PreparedStatement ps = null;
       Connection con = conexion();
       String query= "INSERT INTO usuarios (usuario, password, id_rango,last_session) VALUES(?,?,?,?)";
       
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setInt(3, usr.getId_tipo());
            ps.setString(4, usr.getLast_session());
            ps.execute();
                 return true;
        } catch (SQLException ex) {
           
            System.err.println(ex);
            return false;
        }
   
    }
    
    public boolean login (Usuarios usr){
       PreparedStatement ps = null;
       ResultSet rs = null;
       Connection con = conexion();
    
        
       String query= "SELECT u.usuario, u.password, u.id_rango, t.cargo FROM usuarios AS u INNER JOIN rangos AS t ON u.id_rango=t.ID WHERE usuario = ?";
       
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, usr.getUsuario());
            rs = ps.executeQuery();
            if(rs.next()){
                
                if(usr.getPassword().equals(rs.getString(2))){
                    String SQLUPDATE= "UPDATE usuarios SET last_session = ? WHERE usuario = ?";
                    ps = con.prepareStatement(SQLUPDATE);
                    ps.setString(1, usr.getLast_session());
                    ps.setInt(2, rs.getInt(1));
                    ps.execute();
                    usr.setId_tipo(rs.getInt(3));
                    usr.setCargo(rs.getString(4));
                    return true;
              
                }else{
                    return false;
                }
            
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    
    }
    
   
}
