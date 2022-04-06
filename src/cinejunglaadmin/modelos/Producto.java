/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

/**
 *
 * @author teban
 */
public class Producto {
    
    private static Producto prod;
    private Producto(int ID,String NombreString,int Precio){
        this.ID = ID;
        this.NombreString = NombreString;
        this.Precio = Precio;
       
    }
    
    private int ID;
    private String NombreString;
    private int Precio;
    
    public static Producto getInstance(){
        if(prod==null){
            prod= new Producto(0, "", 0);
        }
    return prod;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreString() {
        return NombreString;
    }

    public void setNombreString(String NombreString) {
        this.NombreString = NombreString;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }
    public void reset(){
        setID(0);
        setNombreString("");
        setPrecio(0);
    }
}
