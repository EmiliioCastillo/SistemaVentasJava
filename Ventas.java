import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Ventas {
    private Clientes cliente;
    private ArrayList<Productos> ListaProducto;
    private double Descuento;
    private double precioAlto;
    private double precioBajo;
    private double totalConDescuento;
    private double totalSinDescuento;
    private double subTotalVenta;
    private String tipoDescuento;

    public Ventas(Clientes cliente, ArrayList<Productos> producto) {
        this.cliente = cliente;
        this.ListaProducto = producto;
    }

    public Ventas() {

    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Productos> getListaProducto() {
        return ListaProducto;
    }

    public void setListaProducto(ArrayList<Productos> listaProducto) {
        ListaProducto = listaProducto;
    }

    public double getPrecioAlto() {
        return precioAlto;
    }

    public void setPrecioAlto(double precioAlto) {
        this.precioAlto = precioAlto;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double descuento) {
        Descuento = descuento;
    }

    public double getPrecioBajo() {
        return precioBajo;
    }

    public void setPrecioBajo(double precioBajo) {
        this.precioBajo = precioBajo;
    }

    public double getTotalConDescuento() {
        return totalConDescuento;
    }

    public void setTotalConDescuento(double totalDescuento) {
        this.totalConDescuento = totalDescuento;
    }

    public double getSubTotalVenta() {
        return subTotalVenta;
    }

    public void setSubTotalVenta(double subTotalVenta) {
        this.subTotalVenta = subTotalVenta;
    }

    public double getTotalSinDescuento() {
        return totalSinDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTotalSinDescuento(double totalSinDescuento) {
        this.totalSinDescuento = totalSinDescuento;
    }

    DefaultTableModel modelo = new DefaultTableModel();

    //Creando modelo y cabecera
    public void CabeceraSubTotalVentas(JTable tablaSubTotal) {
        String[] cabecera = new String[]{"Nombre", "Producto", "SubTotal"};
        modelo.setColumnIdentifiers(cabecera);
        tablaSubTotal.setModel(modelo);
    }

    public double calcularTotalVenta(JTable tablaSubtotal) {
        this.totalSinDescuento = 0;
        for (int i = 0; i < tablaSubtotal.getRowCount(); i++) {
            double valorTabla = Double.parseDouble(tablaSubtotal.getValueAt(i, 1).toString());
            this.totalSinDescuento = this.totalSinDescuento + valorTabla;
        }
        System.out.println("Total compra = " + this.totalSinDescuento);
        return totalSinDescuento;
    }

    public double CalcularTotalConDes() {
        this.totalConDescuento = this.totalSinDescuento - this.Descuento;
        return this.totalConDescuento;
    }

    public double CalcularDescuentoVenta() {
        this.Descuento = 0;
        this.tipoDescuento = "0%";
        if (this.totalConDescuento > 5000 && this.totalSinDescuento <= 10000) {
            this.Descuento = (this.totalSinDescuento * 0.2);
            System.out.println("Su descuento es del 20%");
            this.tipoDescuento = "20%";
        } else {
            if (this.totalSinDescuento > 10000 && this.totalSinDescuento <= 20000) {
                this.Descuento = (this.totalSinDescuento * 0.4);
                System.out.println("Su descuento es del 40%");
                this.tipoDescuento = "40%";
            }
        }
        return this.Descuento;
    }

    public double CalcularSubTotalAlto(JTable tablaSubTotal) {
        this.precioAlto = 0;
        for (int i = 0; i < tablaSubTotal.getRowCount(); i++) {
            double valorTabla = Double.parseDouble(tablaSubTotal.getValueAt(i, 1).toString());
            if (valorTabla > this.precioAlto) {
                this.precioAlto = valorTabla;
            }
        }
        return this.precioAlto;
    }

    public double CalcularSubTotalBajo(JTable tablaSubTotal) {
        this.precioBajo = Double.parseDouble(tablaSubTotal.getValueAt(0, 1).toString());
        tablaSubTotal.getSelectedRow();
        for (int i = 0; i < tablaSubTotal.getRowCount(); i++) {
            double valorTabla = Double.parseDouble(tablaSubTotal.getValueAt(i, 1).toString());
            if (valorTabla < this.precioBajo) {
                this.precioBajo = valorTabla;
            }
        }
        return this.precioBajo;
    }

    public void eliminarFilaVenta(JTable tablaVentas, JTextField bajo, JTextField alto, JTextField descuento, JTextField Total, JTextField ttalDescuento, JTextField TipoDes) {
        int fila = tablaVentas.getSelectedRow();
        if (tablaVentas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            modelo.removeRow(fila);
            bajo.setText(String.valueOf(this.CalcularSubTotalBajo(tablaVentas)));
            alto.setText(String.valueOf(this.CalcularSubTotalAlto(tablaVentas)));
            descuento.setText(String.valueOf(this.CalcularDescuentoVenta()));
            Total.setText(String.valueOf(this.calcularTotalVenta(tablaVentas)));
            ttalDescuento.setText(String.valueOf(this.CalcularTotalConDes()));
            TipoDes.setText(this.tipoDescuento);
        }
    }

    public double CalcularSubTotalVenta(JTextField cantidad, JTable tablaProductos) {
        this.subTotalVenta = 0;
        tablaProductos.getSelectedRow();
        //Este for es para iterar la tabla
        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            //Conversion y capturamos el valor de la fila seleccionado pero columna 2 que va a estar el precio
            double valorTabla = Double.parseDouble(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 2).toString());
            //A ese precio lo vamos a multiplicar por el subtotal de la venta
            this.subTotalVenta = valorTabla * Integer.parseInt(cantidad.getText());
        }
        return this.subTotalVenta;
    }

    public void AgregarTablaVentas(JTextField cantidad, JTable tablaProductos, JTable tablaVentas, JTextField bajo, JTextField alto, JTextField descuento, JTextField Total, JTextField ttalDescuento, JTextField TipoDes) {
        if (cantidad.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese cantidad a comprar");
        } else {
            if (tablaProductos.getSelectedRow() == 1) {
                JOptionPane.showMessageDialog(null, "Seleccione un producto");
            } else {
                modelo.addRow(new Object[]{tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0), this.CalcularSubTotalVenta(cantidad, tablaProductos)});
                bajo.setText(String.valueOf(this.CalcularSubTotalBajo(tablaVentas)));
                alto.setText(String.valueOf(this.CalcularSubTotalAlto(tablaVentas)));
                descuento.setText(String.valueOf(this.CalcularDescuentoVenta()));
                Total.setText(String.valueOf(this.calcularTotalVenta(tablaVentas)));
                ttalDescuento.setText(String.valueOf(this.CalcularTotalConDes()));
                TipoDes.setText(this.tipoDescuento);
                cantidad.setText("");
            }
        }
    }
}


