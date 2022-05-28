import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Productos {

    private String nombre;
    private double precio;
    private int cantidad;
    private ArrayList<Productos> ListaProducto = new ArrayList();
    Ventas venta = new Ventas();


    public Productos( int cantidad, double precio, String nombre){
            this.cantidad = cantidad;
            this.precio = precio;
            this.nombre = nombre;
        }

        public double getPrecio () {
            return precio;
        }

        public void setPrecio ( float precio){
            this.precio = precio;
        }

        public int getCantidad () {
            return cantidad;
        }

        public void setDescripcion (String nombre){
            this.nombre = nombre;
        }

        public ArrayList<Productos> getListaProducto () {
            return ListaProducto;
        }
        public void setProductos (ArrayList < Productos > productos) {
            this.ListaProducto = productos;
        }
        DefaultTableModel modelo = new DefaultTableModel();

        public void CabeceraProductos (JTable tablaProductos){
            String[] cabecera = new String[]{"Nombre", "Cantidad disponible", "Precio"};
            modelo.setColumnIdentifiers(cabecera);
            tablaProductos.setModel(modelo);
        }
        public void AgregarProducto (JTextField nombre, JTextField precio, JTextField cantidad){
            if (nombre.getText().length() == 0 && cantidad.getText().length() == 0 && precio.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene los campos");
            } else {
                this.cantidad = Integer.parseInt(cantidad.getText());
                this.precio = Double.parseDouble(precio.getText());
                this.nombre = nombre.getText();
                modelo.addRow(new Object[]{this.nombre, this.cantidad, this.precio});
                Productos productos = new Productos(this.cantidad, this.precio, this.nombre);

                ListaProducto.add(productos);
                System.out.println("Producto agregado");
            }

        }

        public void LimpiarDatosProductos (JTextField nombre, JTextField cantidad, JTextField precio){
            nombre.setText("");
            cantidad.setText("");
            precio.setText("");
        }
        public void eliminarFilaProducto (JTable tablaProductos){
            int fila = tablaProductos.getSelectedRow();
            if (tablaProductos.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
            } else {
                modelo.removeRow(fila);
            }
        }
    }
