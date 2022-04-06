
package cinejunglaadmin.modelos;

import cinejunglaadmin.Conector;
import java.sql.Connection;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author teban
 */
public class Modelo_confiteria extends Conector{
     Connection con = conexion();
    public boolean facturar(String producto, int cant,int num_fact,int id_cliente,int cod_emp){
        
        int id = 0;
        int precio = 0;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.util.Date date = new java.util.Date();
            DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            ps = con.prepareStatement("SELECT ID, precio From productos WHERE Nombre = ?");
            ps.setString(1,producto);
            rs= ps.executeQuery();
            if(rs.next()){
            id = rs.getInt("ID");
            precio = rs.getInt("precio");
            
            }

            ps = con.prepareStatement("insert into factura (ID,id_producto,id_empleado,id_cliente,cantidad,valor_venta,fecha_venta) values (?,?,?,?,?,?,?)");
            ps.setInt(1,num_fact);
            ps.setInt(2,id);
            ps.setInt(3, cod_emp);
            ps.setInt(4,id_cliente);
            ps.setInt(5,cant);
            ps.setInt(6,precio*cant);
            ps.setString(7,fecha.format(date));
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    public int cotizacion(String producto, int cant){
        
        int id = 0;
        int precio = 0;
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.util.Date date = new java.util.Date();
            DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            ps = con.prepareStatement("SELECT ID, precio From productos WHERE Nombre = ?");
            ps.setString(1,producto);
            rs= ps.executeQuery();
            if(rs.next()){
            id = rs.getInt("ID");
            precio = rs.getInt("precio");
            }
            precio = precio*cant;
            return precio;
        } catch (Exception e) {
            return precio;
            
        }
        
    }
    public int gen_numero(){
    int min = 1;
    int getRandomValue = 0;
		int max = 100000000;
		for(int i = min; i <=max; i++) {
			getRandomValue = (int) (Math.random()*(max-min)) + min; 
		}
    return getRandomValue;
    }
}
