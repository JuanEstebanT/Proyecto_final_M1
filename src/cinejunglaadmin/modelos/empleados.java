/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

/**
 *
 * @author teban
 */
public class empleados implements Restaurar{
    public static empleados emp;
    private empleados(int Cod_emp,int cedula,String telefono,int Multiplex_ID,String Nombre,String Apellido,String cargo,String fecha_inc){
        this.Apellido = Apellido;
        this.Cod_emp = Cod_emp;
        this.cedula = cedula;
        this.telefono = telefono;
        this.Multiplex_ID = Multiplex_ID;
        this.Nombre = Nombre;
        this.cargo = cargo;
        this.fecha_inc = fecha_inc;
    }
    
    
    private int Cod_emp;
    private int cedula;
    private String telefono;
    private int salario;
    private int Multiplex_ID;
    private String Nombre;
    private String Apellido;
    private String cargo;
    private String fecha_inc;

    public static empleados getInstance(){
        if(emp==null){
            emp = new empleados(0, 0, "", 0, "", "", "", "");
        }
    return emp;
    }
    
    public int getCod_emp() {
        return Cod_emp;
    }

    public void setCod_emp(int Cod_emp) {
        this.Cod_emp = Cod_emp;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getMultiplex_ID() {
        return Multiplex_ID;
    }

    public void setMultiplex_ID(int Multiplex_ID) {
        this.Multiplex_ID = Multiplex_ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFecha_inc() {
        return fecha_inc;
    }

    public void setFecha_inc(String fecha_inc) {
        this.fecha_inc = fecha_inc;
    }
    
    public void reset(){
        setCod_emp(0);
        setCedula(0);
        setTelefono("");
        setSalario(0);
        setMultiplex_ID(0);
        setNombre("");
        setApellido("");
        setCargo("");
        setFecha_inc("");
    }
}
