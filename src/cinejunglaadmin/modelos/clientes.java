
package cinejunglaadmin.modelos;

/**
 *
 * @author teban
 */
public class clientes implements Restaurar{
    private static clientes cli;
    private clientes(int ID,String Nombre,String Apellido,String email,int puntos_acum){
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.puntos_acum = puntos_acum;
    }
    
    private int ID;
    private String Nombre;
    private String Apellido;
    private String email;
    private int puntos_acum = 0;
    public static clientes getInstance(){
        if(cli==null){
            cli= new clientes(0, "", "", "", 0);
        }
    return cli;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuntos_acum() {
        return puntos_acum;
    }

    public void setPuntos_acum(int puntos_acum) {
        this.puntos_acum = puntos_acum;
    }
    
    public void reset(){
        setApellido("");
        setEmail("");
        setID(0);
        setNombre("");
        setPuntos_acum(0);
    }

}
