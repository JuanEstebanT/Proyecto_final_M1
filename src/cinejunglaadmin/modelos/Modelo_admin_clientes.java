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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author teban
 */
public class Modelo_admin_clientes extends Conector{
    
    Connection con = conexion();
    
    public boolean Refrescar(Pane_admin vista){
        try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtClientes().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT ID, Nombre, apellido, email, puntos_acum From clientes";
            modelo_table.addColumn("ID");
            modelo_table.addColumn("Nombre");
            modelo_table.addColumn("Apellido");
            modelo_table.addColumn("Email");
            modelo_table.addColumn("Puntos");
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
            e.printStackTrace();
            return false;
        }
    }
    public boolean Registar(clientes cli){
        String query = "INSERT INTO clientes (Nombre, apellido, email, puntos_acum) values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,cli.getNombre());
            ps.setString(2,cli.getApellido());
            ps.setString(3,cli.getEmail());
            ps.setInt(4,cli.getPuntos_acum());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean modificar(Pane_admin vista,clientes cli,String lugar){
        String query = "UPDATE clientes SET Nombre = ? , apellido = ?, email = ? WHERE ID = "+lugar;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,cli.getNombre());
            ps.setString(2,cli.getApellido());
            ps.setString(3,cli.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean Eliminar(Pane_admin vista,clientes cli){
        String query = "DELETE FROM clientes WHERE ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,cli.getID());
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void Transfer_data(Pane_admin vista) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int fila = vista.getJtClientes().getSelectedRow();
            String ID = vista.getJtClientes().getValueAt(fila,0).toString();

            ps = con.prepareStatement("SELECT ID, Nombre, apellido, email, puntos_acum From clientes WHERE ID = ?");
            ps.setString(1,ID);
            rs= ps.executeQuery();

            while (rs.next()){
                vista.getTxt_nombrecli().setText(rs.getString("Nombre"));
                vista.getTxt_apellidocli().setText(rs.getString("apellido"));
                vista.getTxt_emailcli().setText(rs.getString("email"));
                vista.getLb_resID().setText(rs.getString("ID"));
                vista.getLb_totalP().setText(rs.getString("puntos_acum"));
                vista.getTxt_buscarcli().setText(rs.getString("ID"));
            }
           
        }catch (SQLException x){
            System.err.println(x);
        }
    }
    
    public boolean Buscar(Pane_admin vista,String filtro){
        
    try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtClientes().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT ID, Nombre, apellido, email, puntos_acum From clientes WHERE "+filtro;
            modelo_table.addColumn("ID");
            modelo_table.addColumn("Nombre");
            modelo_table.addColumn("Apellido");
            modelo_table.addColumn("Email");
            modelo_table.addColumn("Puntos");
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
            e.printStackTrace();
            return false;
        }
    }

    public void limpiar(Pane_admin vista){
      vista.getTxt_nombrecli().setText("");
      vista.getTxt_apellidocli().setText("");
      vista.getTxt_emailcli().setText("");
      vista.getLb_resID().setText("");
      vista.getLb_totalP().setText("");
    }
    
}
