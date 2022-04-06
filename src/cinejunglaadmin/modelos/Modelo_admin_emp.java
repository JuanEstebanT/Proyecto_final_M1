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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author teban
 */
public class Modelo_admin_emp extends Conector{
    Connection con = conexion();
    public boolean Refrescar(Pane_admin vista){
        try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtEmpleados().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT Cod_emp, Nombre, apellido, cargo, Multiplex_ID From empleados";
            modelo_table.addColumn("Cod_emp");
            modelo_table.addColumn("Nombre");
            modelo_table.addColumn("apellido");
            modelo_table.addColumn("cargo");
            modelo_table.addColumn("Multiplex_ID");
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
    public boolean Registar(Pane_admin vista, empleados emp){
     
        String query = "INSERT INTO empleados (Cod_emp, Nombre, apellido, cedula, telefono, fecha_inc, salario, Multiplex_ID, cargo) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,emp.getCod_emp());
            ps.setString(2,emp.getNombre());
            ps.setString(3,emp.getApellido());
            ps.setInt(4,emp.getCedula());
            ps.setString(5,emp.getTelefono());
            ps.setString(6,emp.getFecha_inc());
            ps.setInt(7,emp.getSalario());
            ps.setInt(8,emp.getMultiplex_ID());
            ps.setString(9,emp.getCargo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificar(Pane_admin vista,empleados emp,String lugar){
        String query = "UPDATE empleados SET Nombre = ?,  apellido = ?, cedula = ?, telefono = ?, fecha_inc = ?, salario = ?, Multiplex_ID = ?, cargo = ? WHERE Cod_emp = "+lugar;
        try {
            System.out.println(emp.getSalario());
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setString(1,emp.getNombre());
            ps.setString(2,emp.getApellido());
            ps.setInt(3,emp.getCedula());
            ps.setString(4,emp.getTelefono());
            ps.setString(5,emp.getFecha_inc());
            ps.setInt(6,emp.getSalario());
            ps.setInt(7,emp.getMultiplex_ID());
            ps.setString(8,emp.getCargo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean Eliminar(Pane_admin vista,empleados emp){
        String query = "DELETE FROM empleados WHERE Cod_emp = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,emp.getCod_emp());
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
            int fila = vista.getJtEmpleados().getSelectedRow();
            String ID = vista.getJtEmpleados().getValueAt(fila,0).toString();

            ps = con.prepareStatement("SELECT Cod_emp, Nombre, apellido, cedula, telefono, fecha_inc, salario, Multiplex_ID, cargo From empleados WHERE Cod_emp = ?");
            ps.setString(1,ID);
            rs= ps.executeQuery();

            while (rs.next()){
                
                vista.getTxt_cedulaemp().setText(rs.getString("cedula"));
                vista.getTxt_telefonoemp().setText(rs.getString("telefono"));
                vista.getBox_multiplexemp().setSelectedIndex(rs.getInt("Multiplex_ID")-1);
                vista.getTxt_nombreemp().setText(rs.getString("Nombre"));
                vista.getTxt_apellidoemp().setText(rs.getString("Apellido"));
                vista.getTxt_cargoemp().setText(rs.getString("cargo"));
                vista.getTxt_fechaemp().setText(rs.getString("fecha_inc"));
                vista.getLb_cod().setText(rs.getString("Cod_emp"));
                vista.getTxt_salarioemp().setText(rs.getString("salario"));
                vista.getTxt_buscaremp().setText(rs.getString("Cod_emp"));
            }
           
        }catch (SQLException x){
            System.err.println(x);
        }
    }
    
    public boolean Buscar(Pane_admin vista,String filtro){
        
    try {
            DefaultTableModel modelo_table = new DefaultTableModel();
            vista.getJtEmpleados().setModel(modelo_table);
            ResultSet rs = null;
            String query = "SELECT Cod_emp, Nombre, apellido, cargo, Multiplex_ID From empleados Where "+filtro;
            System.out.println(query);
            modelo_table.addColumn("Cod_emp");
            modelo_table.addColumn("Nombre");
            modelo_table.addColumn("apellido");
            modelo_table.addColumn("cargo");
            modelo_table.addColumn("Multiplex_ID");
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
                System.out.println("Hurra");
            }
            return true;
            
        } catch (Exception e){
            
            return false;
        }
    }
    
    public int generar_cod(Date date, int Multipex, int cedula){
    int Codigo;
    DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fechacodString = fecha.format(date);
    String codigo = fechacodString.substring(0, 4)+fechacodString.substring(11, 13)/*+fechacodString.substring(11, 13)*/;
    String cedulatoString = ""+cedula;
    int largocedula = cedulatoString.length();
    int x1 = largocedula-3;
    int x2 = x1+3;
    codigo = codigo+Multipex+cedulatoString.substring(x1,x2);
    Codigo = Integer.parseInt(codigo);
    return Codigo;
    }
    
    
    
    public void limpiar(Pane_admin vista){
        vista.getTxt_cedulaemp().setText("");
        vista.getTxt_telefonoemp().setText("");
        vista.getBox_multiplexemp().setSelectedIndex(0);
        vista.getTxt_nombreemp().setText("");
        vista.getTxt_apellidoemp().setText("");
        vista.getTxt_cargoemp().setText("");
        vista.getTxt_fechaemp().setText("");
        vista.getLb_cod().setText("");
        vista.getTxt_salarioemp().setText("");
    }
}
/*ps.setInt(1,Integer.parseInt(vista.getTxt_idprod().getText()));
            ps.setString(2,vista.getTxt_nombreprod().getText());
            ps.setInt(3,Integer.parseInt(vista.getTxt_precioprod().getText()));
            ps.executeUpdate();*/