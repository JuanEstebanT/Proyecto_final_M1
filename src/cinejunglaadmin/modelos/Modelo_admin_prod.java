/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

import cinejunglaadmin.Conector;
import cinejunglaadmin.vistas.Pane_admin;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.*; 
import javax.swing.JOptionPane;

/**
 *
 * @author teban
 */
public class Modelo_admin_prod extends Conector{
    Connection con = conexion();
    public boolean Refrescar(Pane_admin vista){
        try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtProductos().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT ID, Nombre, precio From productos";
            modelo_table.addColumn("ID");
            modelo_table.addColumn("Nombre");
            modelo_table.addColumn("Precio");
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
    public boolean Registar(Pane_admin vista, Producto prod){
     
        String query = "INSERT INTO productos (ID, Nombre, Precio) values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,prod.getID());
            ps.setString(2,prod.getNombreString());
            ps.setInt(3,prod.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean modificar(Pane_admin vista,Producto prod,String lugar){
        String query = "UPDATE productos SET ID = ?, Nombre = ?, precio = ? WHERE ID = "+lugar;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,prod.getID());
            ps.setString(2,prod.getNombreString());
            ps.setInt(3,prod.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean Eliminar(Pane_admin vista,Producto prod){
        String query = "DELETE FROM productos WHERE ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,prod.getID());
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
            int fila = vista.getJtProductos().getSelectedRow();
            String ID = vista.getJtProductos().getValueAt(fila,0).toString();

            ps = con.prepareStatement("SELECT ID, Nombre, precio From productos WHERE ID = ?");
            ps.setString(1,ID);
            rs= ps.executeQuery();

            while (rs.next()){
                vista.getTxt_idprod().setText(rs.getString("ID"));
                vista.getTxt_nombreprod().setText(rs.getString("Nombre"));
                vista.getTxt_precioprod().setText(rs.getString("Precio"));
            }
           
        }catch (SQLException x){
            System.err.println(x);
        }
    }
    
    public boolean Buscar(Pane_admin vista,String filtro){
        
    try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtProductos().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT ID, Nombre, precio From productos WHERE "+filtro;
            modelo_table.addColumn("ID");
            modelo_table.addColumn("Nombre");
            modelo_table.addColumn("Precio");
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
            if(rs.next()){
            
            }else{
                
                JOptionPane.showMessageDialog(vista, "Registro no Encontrado");
            }
            return true;
            
        } catch (Exception e){
            
            return false;
        }
    }
    
    
    
    
    
    public void limpiar(Pane_admin vista){
    vista.getTxt_idprod().setText("");
    vista.getTxt_nombreprod().setText("");
    vista.getTxt_precioprod().setText("");
    }
}
/*ps.setInt(1,Integer.parseInt(vista.getTxt_idprod().getText()));
            ps.setString(2,vista.getTxt_nombreprod().getText());
            ps.setInt(3,Integer.parseInt(vista.getTxt_precioprod().getText()));
            ps.executeUpdate();*/