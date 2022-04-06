/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

import cinejunglaadmin.Conector;
import cinejunglaadmin.vistas.Pane_admin;
import cinejunglaadmin.vistas.RegistroUsuarios;
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
public class Modelo_admin_usuarios extends Conector{
    Connection con = conexion();
    
    public boolean Refrescar(Pane_admin vista){
        try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtUsuarios().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT usuario, password, last_session, id_rango From usuarios";
            modelo_table.addColumn("usuario");
            modelo_table.addColumn("password");
            modelo_table.addColumn("last_session");
            modelo_table.addColumn("id_rango");
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
    public void Registar(){
        RegistroUsuarios fromRegistroUsuarios = new RegistroUsuarios();
        fromRegistroUsuarios.setVisible(true);
    }
    
    public boolean modificar(Pane_admin vista,Usuarios urs,String lugar){
        String query = "UPDATE usuarios SET password = ? , id_rango = ? WHERE usuario = "+lugar;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,urs.getPassword());
            ps.setInt(2,urs.getId_tipo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean Eliminar(Pane_admin vista,Usuarios urs){
        String query = "DELETE FROM usuarios WHERE usuario = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,urs.getUsuario());
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
            int fila = vista.getJtUsuarios().getSelectedRow();
            String ID = vista.getJtUsuarios().getValueAt(fila,0).toString();

            ps = con.prepareStatement("SELECT usuario, password, id_rango From usuarios WHERE usuario = ?");
            ps.setString(1,ID);
            rs= ps.executeQuery();

            while (rs.next()){
                vista.getTxt_nombreurs().setText(rs.getString("usuario"));
                vista.getTxt_password().setText(rs.getString("password"));
                vista.getBox_tipousr().setSelectedIndex(Integer.parseInt(rs.getString("id_rango")));
            }
           
        }catch (SQLException x){
            System.err.println(x);
        }
    }
    
    public boolean Buscar(Pane_admin vista,String filtro){
        
    try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtUsuarios().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT usuario, password, last_session, id_rango From usuarios WHERE "+filtro;
            modelo_table.addColumn("usuario");
            modelo_table.addColumn("password");
            modelo_table.addColumn("last_session");
            modelo_table.addColumn("id_rango");
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
    
    
    
    
    
    public void limpiar(Pane_admin vista){
    vista.getTxt_nombreurs().setText("");
    vista.getTxt_password().setText("");
    vista.getBox_tipousr().setSelectedIndex(0);
    }
}
