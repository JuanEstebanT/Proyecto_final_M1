/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

import cinejunglaadmin.Conector;
import cinejunglaadmin.vistas.Taquilla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teban
 */
public class Modelo_taquilla extends Conector{
    Connection con = conexion();
    int id_sala = 0;
    public void verificarsilla(Taquilla taquilla){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id_silla = 0;
        String fila = taquilla.getTxt_fila().getText().trim();
        int numero = Integer.parseInt(taquilla.getTxt_numero().getText().trim());
        int multiplex = taquilla.getBox_mul().getSelectedIndex()+1;
        String sala = taquilla.getBox_func().getSelectedItem().toString().substring(0, 6);
        String hora = taquilla.getBox_func().getSelectedItem().toString().substring(taquilla.getBox_func().getSelectedItem().toString().length()-19, taquilla.getBox_func().getSelectedItem().toString().length());
        int id_sala = 0;
        int id_funcion = 0;
        
       
        
        try {
            String query3 = "select ID from salas  where Nombre = ? and id_multiplex = ?";
            ps = con.prepareStatement(query3);
            ps.setString(1, sala);
            ps.setInt(2, multiplex);
            rs = ps.executeQuery();
             if(rs.next()){
             id_sala = rs.getInt("ID");
             }
             
            String query4 = "select ID from funciones  where id_sala = ? and id_multiplex = ? and hora_inicio = ?";
            ps = con.prepareStatement(query4);
            ps.setInt(1, id_sala);
            ps.setInt(2, multiplex);
            ps.setString(3, hora);
            rs = ps.executeQuery();
             if(rs.next()){
             id_funcion = rs.getInt("ID");
             }
            
            String query = "Select ID from asientos where fila = ? and numero = ? AND id_multiplex = ? and id_sala = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, fila);
            ps.setInt(2, numero);
            ps.setInt(3, multiplex);
            ps.setInt(4, id_sala);
            rs = ps.executeQuery();
            if(rs.next()){
            id_silla = rs.getInt("ID");
            
            }else{
                
                System.out.println("Asiento no encontrado");
                
            }
            
            
            String query2 = "select id_asiento, id_funcion from asientos_res, reservaciones, funciones, asientos where reservaciones.id_funcion = funciones.id and asientos_res.id_reservacion = reservaciones.id and asientos_res.id_asiento = asientos.id and asientos.id = ?";
            ps = con.prepareStatement(query2);
            ps.setInt(1, id_silla);
            rs = ps.executeQuery();
            
            if(rs.next()){
                   taquilla.getLb_disp().setText("No esta disponible"); 
                   taquilla.getBnt_Pago().setEnabled(false);
            }else{
                  taquilla.getLb_disp().setText("Esta disponible");
                  taquilla.getBnt_Pago().setEnabled(true);
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
       if(taquilla.getTxt_fila().getText().equals("E") || taquilla.getTxt_fila().getText().equals("F"))
        taquilla.getjLabel7().setText("$15000");
       else taquilla.getjLabel7().setText("$11000");
    
    
    }
    public void pagar_silla(Taquilla taquilla){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id_silla = 0;
        String fila = taquilla.getTxt_fila().getText().trim();
        int numero = Integer.parseInt(taquilla.getTxt_numero().getText().trim());
        int multiplex = taquilla.getBox_mul().getSelectedIndex()+1;
        String sala = taquilla.getBox_func().getSelectedItem().toString().substring(0, 6);
        String hora = taquilla.getBox_func().getSelectedItem().toString().substring(taquilla.getBox_func().getSelectedItem().toString().length()-19, taquilla.getBox_func().getSelectedItem().toString().length());
        int id_sala = 0;
        int id_funcion = 0;
        
        
        try {
            String query5 = "select ID from salas  where Nombre = ? and id_multiplex = ?";
            ps = con.prepareStatement(query5);
            ps.setString(1, sala);
            ps.setInt(2, multiplex);
            rs = ps.executeQuery();
             if(rs.next()){
             id_sala = rs.getInt("ID");
             }
             
            String query6 = "select ID from funciones  where id_sala = ? and id_multiplex = ? and hora_inicio = ?";
            ps = con.prepareStatement(query6);
            ps.setInt(1, id_sala);
            ps.setInt(2, multiplex);
            ps.setString(3, hora);
            rs = ps.executeQuery();
             if(rs.next()){
             id_funcion = rs.getInt("ID");
             }  
            
            String query = "Select a.ID from asientos as a where a.fila = ? and a.numero = ? AND id_multiplex = ? and id_sala = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, fila);
            ps.setInt(2, numero);
            ps.setInt(3, multiplex);
            ps.setInt(4, id_sala);
            rs = ps.executeQuery();
            if(rs.next()){
            id_silla = rs.getInt("ID");
                
            }else{
                
                System.out.println("Asiento no encontrado");
                
            }
            
            String query2 = "Insert into reservaciones (id_funcion,id_cliente) value (?,?)";
            ps = con.prepareStatement(query2);
            ps.setInt(1, id_funcion);
            ps.setInt(2, Integer.parseInt(taquilla.getTxt_codclii().getText().trim()));
            ps.executeUpdate();
            int id_res = 0;
            
            int puntoscliente = 0;
            ps = con.prepareStatement("select puntos_acum from clientes WHERE ID = ?");
            ps.setInt(1,Integer.parseInt(taquilla.getTxt_codclii().getText()));
            rs = ps.executeQuery();
            if(rs.next()){
            puntoscliente = rs.getInt("puntos_acum");
            }
            
            ps = con.prepareStatement("UPDATE clientes SET puntos_acum = ? WHERE ID = ?");
            ps.setInt(1,puntoscliente+10);
            ps.setInt(2,Integer.parseInt(taquilla.getTxt_codclii().getText()));
            ps.executeUpdate();
            
            String query3 = "Select max(ID) from reservaciones";
            rs = ps.executeQuery(query3);
            if(rs.next()){
                id_res = rs.getInt(1);
            }
            
            String query4 = "INSERT INTO asientos_res (id_reservacion,id_asiento) value (?,?)";
            ps = con.prepareStatement(query4);
            ps.setInt(1, id_res);
            ps.setInt(2, id_silla);
            ps.executeUpdate();
        } catch (SQLException a) {
            a.printStackTrace();
        }
    
    
    }
    public void actualizarFun(Taquilla taquilla){
        taquilla.getBox_func().removeAllItems();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int multiplex = taquilla.getBox_mul().getSelectedIndex()+1;
        String pelicula = taquilla.getBox_pel().getSelectedItem().toString();
        int id_pelicula = 0;
        
        String query = "select ID from peliculas where Nombre = ?";
        
        try {
            
            ps = con.prepareStatement(query);
            ps.setString(1, pelicula);
            rs = ps.executeQuery();
            
            if(rs.next()){
            id_pelicula = rs.getInt("ID");
            }
            
            String query2 = "SELECT s.Nombre, s.ID , f.hora_inicio from salas as s, funciones as f where f.id_multiplex = ? AND f.id_pelicula = ? AND f.id = s.id";
            ps = con.prepareStatement(query2);
            ps.setInt(1, multiplex);
            ps.setInt(2, id_pelicula);
            rs = ps.executeQuery();
            
            while(rs.next()){
            
            taquilla.getBox_func().addItem(rs.getString("Nombre")+" "+rs.getString("hora_inicio"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_taquilla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
}
