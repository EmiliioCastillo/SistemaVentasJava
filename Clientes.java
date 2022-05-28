import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Clientes {
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    //Constructor
    public Clientes(String cedula, String nombre, String apellido, String direccion, String telefono) {

        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //Metodos getter y setter

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    DefaultTableModel modelo = new DefaultTableModel();

    public void CabeceraClientes(JTable tablaClientes) {
        String[] cabecera = new String[]{"Nombre", "Apellido", "Cedula", "Direccion", "Telefono"};
        modelo.setColumnIdentifiers(cabecera);
        tablaClientes.setModel(modelo);
    }
    public void AgregarCliente(JTextField nombre, JTextField apellido, JTextField cedula, JTextField direccion, JTextField telefono){
        this.telefono = telefono.getText();
        this.apellido = apellido.getText();
        this.cedula = cedula.getText();
        this.nombre = nombre.getText();
        this.apellido = apellido.getText();
        this.direccion = direccion.getText();
    }

    public void LimpiarDatosClientes(JTextField nombre, JTextField apellido, JTextField cedula, JTextField direccion, JTextField telefono){
        nombre.setText("");
        apellido.setText("");
        cedula.setText("");
        direccion.setText("");
        telefono.setText("");
    }

    public void eliminarFilaCliente(JTable tablaClientes){
        int fila = tablaClientes.getSelectedRow();
        if(tablaClientes.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            modelo.removeRow(fila);
        }
    }
}


