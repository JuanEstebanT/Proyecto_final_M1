/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;
import cinejunglaadmin.Conector;
import cinejunglaadmin.vistas.Pane_admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author teban
 */
public class Modelo_admin_funcion extends Conector{
    Connection con = conexion();
    public boolean Refrescar(Pane_admin vista){
        try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtFunciones().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT f.ID, p.nombre, s.Nombre , m.Nombre, f.hora_inicio From Funciones AS f, multiplex as m,  peliculas as p, salas as s Where f.id_multiplex = m.id and  f.id_pelicula = p.id and f.id_sala = s.id";
            modelo_table.addColumn("ID");
            modelo_table.addColumn("Pelicula");
            modelo_table.addColumn("Sala");
            modelo_table.addColumn("Multiplex");
            modelo_table.addColumn("Fecha");
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int CantidadColumnas = rsmd.getColumnCount();
            
            while(rs.next()){
                Object[] filas = new Object[CantidadColumnas];
                for(int i=0;i<CantidadColumnas;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo_table.addRow(filas);
            }
            
            return true;
            
        } catch (Exception e){
            
            return false;
        }
    }   
    public void Transfer_data(Pane_admin vista) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int fila = vista.getJtFunciones().getSelectedRow();
            String ID = vista.getJtFunciones().getValueAt(fila,0).toString();

            ps = con.prepareStatement("SELECT ID, id_pelicula, id_sala, id_multiplex, hora_inicio From funciones WHERE ID = ?");
            ps.setString(1,ID);
            rs= ps.executeQuery();

            while (rs.next()){
                vista.getLb_IDfun().setText(rs.getString("ID"));
                vista.getTxt_IDpelfun().setText(rs.getString("id_pelicula"));
                vista.getTxt_IDsalfun().setText(rs.getString("id_sala"));
                vista.getTxt_IDmulfun().setText(rs.getString("id_multiplex"));
                vista.getTxt_fechafun().setText(rs.getString("hora_inicio"));
                
            }
           
        }catch (SQLException x){
            System.err.println(x);
        }
    }
     public boolean Buscar(Pane_admin vista,String filtro){
        
    try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtFunciones().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT f.ID, p.nombre, s.Nombre , m.Nombre, f.hora_inicio From Funciones AS f, multiplex as m,  peliculas as p, salas as s Where f.id_multiplex = m.id and  f.id_pelicula = p.id and f.id_sala = s.id and f.ID = "+filtro;
             modelo_table.addColumn("ID");
            modelo_table.addColumn("Pelicula");
            modelo_table.addColumn("Sala");
            modelo_table.addColumn("Multiplex");
            modelo_table.addColumn("Fecha");
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int CantidadColumnas = rsmd.getColumnCount();
            
            while(rs.next()){
                Object[] filas = new Object[CantidadColumnas];
                for(int i=0;i<CantidadColumnas;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo_table.addRow(filas);
            }
           
            return true;
            
        } catch (Exception e){
            
            return false;
        }
    
    }
     public boolean Registar(Pane_admin vista){
     
        String query = "INSERT INTO funciones (id_pelicula, id_sala, id_multiplex, hora_inicio) values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,Integer.parseInt(vista.getTxt_IDpelfun().getText()));
            ps.setInt(2,Integer.parseInt(vista.getTxt_IDsalfun().getText()));
            ps.setInt(3,Integer.parseInt(vista.getTxt_IDmulfun().getText()));
            ps.setString(4, vista.getTxt_fechafun().getText());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean Eliminar(Pane_admin vista){
        String query = "DELETE FROM funciones WHERE ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,Integer.parseInt(vista.getLb_IDfun().getText()));
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     public boolean modificar(Pane_admin vista,String lugar){
       
        String query = "UPDATE funciones SET id_pelicula = ?, id_sala = ?, id_multiplex = ?, hora_inicio = ? WHERE ID = "+lugar;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,Integer.parseInt(vista.getTxt_IDpelfun().getText()));
            ps.setInt(2,Integer.parseInt(vista.getTxt_IDsalfun().getText()));
            ps.setInt(3,Integer.parseInt(vista.getTxt_IDmulfun().getText()));
            ps.setString(4, vista.getTxt_fechafun().getText());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }  
     public void limpiar(Pane_admin vista){
        vista.getTxt_IDsalfun().setText("");
        vista.getTxt_IDmulfun().setText("");
        vista.getTxt_IDpelfun().setText("");
        vista.getTxt_fechafun().setText("");
        vista.getLb_IDfun().setText("");
    }
}
