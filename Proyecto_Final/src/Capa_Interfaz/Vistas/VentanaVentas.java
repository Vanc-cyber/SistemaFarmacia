package Capa_Interfaz.Vistas;

import Capa_Entidades.Cliente;
import Capa_Entidades.DetalleVenta;
import Capa_Entidades.Medicamento;
import Capa_Entidades.Venta;
import Capa_LogicaNegocio.LogicaCliente;
import Capa_LogicaNegocio.LogicaMedicamento;
import Capa_LogicaNegocio.LogicaVenta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaVentas extends JPanel {

    private final Color FONDO = new Color(242, 245, 249);
    private final Color TEXTO = new Color(40, 52, 70);
    private final Color AZUL_ACENTO = new Color(45, 145, 190);
    private final Color AZUL_OSCURO = new Color(23, 37, 58);
    private final Color BORDE = new Color(225, 230, 237);

    private JTextField txtNumeroVenta;
    private JTextField txtFecha;
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Medicamento> cmbMedicamento;
    private JTextField txtPrecio;
    private JTextField txtDisponible;
    private JTextField txtCantidad;
    private JLabel lblTotal;

    private JTable tablaDetalle;
    private DefaultTableModel modeloDetalle;

    private JTable tablaVentas;
    private DefaultTableModel modeloVentas;

    private final ArrayList<DetalleVenta> detallesActuales =
            new ArrayList<>();

    private final LogicaCliente logicaCliente;
    private final LogicaMedicamento logicaMedicamento;
    private final LogicaVenta logicaVenta;

    public VentanaVentas(
            LogicaCliente logicaCliente,
            LogicaMedicamento logicaMedicamento,
            LogicaVenta logicaVenta) {

        if (logicaCliente == null
                || logicaMedicamento == null
                || logicaVenta == null) {

            throw new IllegalArgumentException(
                    "Las capas logicas son obligatorias."
            );
        }

        this.logicaCliente = logicaCliente;
        this.logicaMedicamento = logicaMedicamento;
        this.logicaVenta = logicaVenta;

        setLayout(new BorderLayout(0, 20));
        setBackground(FONDO);

        setBorder(
                BorderFactory.createEmptyBorder(
                        30, 35, 30, 35
                )
        );

        crearEncabezado();
        crearContenido();

        cargarClientes();
        cargarMedicamentos();
        actualizarTablaVentas();
        prepararNuevaVenta();

        addComponentListener(
                new ComponentAdapter() {

            @Override
            public void componentShown(
                    ComponentEvent e) {

                actualizarDatos();
            }
        });
    }

    // Recarga la información de los demás módulos
    public void actualizarDatos() {

        cargarClientes();
        cargarMedicamentos();
        actualizarTablaVentas();
    }

    private void crearEncabezado() {

        JPanel encabezado =
                new JPanel(
                        new GridLayout(
                                2, 1, 0, 5
                        )
                );

        encabezado.setBackground(FONDO);

        JLabel titulo =
                new JLabel("Ventas");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        titulo.setForeground(TEXTO);

        JLabel descripcion =
                new JLabel(
                        "Registro y consulta de ventas"
                );

        descripcion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        descripcion.setForeground(Color.GRAY);

        encabezado.add(titulo);
        encabezado.add(descripcion);

        add(
                encabezado,
                BorderLayout.NORTH
        );
    }

    private void crearContenido() {

        JPanel contenido =
                new JPanel(
                        new BorderLayout(
                                0, 15
                        )
                );

        contenido.setBackground(FONDO);

        JPanel centro =
                new JPanel(
                        new GridLayout(
                                2, 1, 0, 15
                        )
                );

        centro.setBackground(FONDO);

        centro.add(
                crearPanelDetalle()
        );

        centro.add(
                crearPanelHistorial()
        );

        contenido.add(
                crearFormulario(),
                BorderLayout.NORTH
        );

        contenido.add(
                centro,
                BorderLayout.CENTER
        );

        add(
                contenido,
                BorderLayout.CENTER
        );
    }

    private JPanel crearFormulario() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0, 15
                        )
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDE
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 25, 20, 25
                        )
                )
        );

        JPanel campos =
                new JPanel(
                        new GridLayout(
                                2, 4, 15, 12
                        )
                );

        campos.setBackground(Color.WHITE);

        txtNumeroVenta =
                crearCampoSoloLectura();

        txtFecha =
                crearCampoSoloLectura();

        txtPrecio =
                crearCampoSoloLectura();

        txtDisponible =
                crearCampoSoloLectura();

        txtCantidad =
                new JTextField();

        cmbCliente =
                new JComboBox<>();

        cmbCliente.setRenderer(
                new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean foco) {

                super.getListCellRendererComponent(
                        lista,
                        valor,
                        indice,
                        seleccionado,
                        foco
                );

                if (valor instanceof Cliente) {

                    Cliente cliente =
                            (Cliente) valor;

                    setText(
                            cliente.getIdentificacion()
                            + " - "
                            + cliente.getNombre()
                            + " "
                            + cliente.getApellido1()
                    );

                } else {

                    setText(
                            "Seleccione un cliente"
                    );
                }

                return this;
            }
        });

        cmbMedicamento =
                new JComboBox<>();

        cmbMedicamento.setRenderer(
                new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean foco) {

                super.getListCellRendererComponent(
                        lista,
                        valor,
                        indice,
                        seleccionado,
                        foco
                );

                if (valor instanceof Medicamento) {

                    Medicamento medicamento =
                            (Medicamento) valor;

                    setText(
                            medicamento.getIdMedicamento()
                            + " - "
                            + medicamento.getNombre()
                    );

                } else {

                    setText(
                            "Seleccione un medicamento"
                    );
                }

                return this;
            }
        });

        cmbMedicamento.addActionListener(
                e -> mostrarDatosMedicamento()
        );

        campos.add(
                crearCampo(
                        "Numero de venta",
                        txtNumeroVenta
                )
        );

        campos.add(
                crearCampo(
                        "Fecha",
                        txtFecha
                )
        );

        campos.add(
                crearCampo(
                        "Cliente",
                        cmbCliente
                )
        );

        campos.add(
                crearCampo(
                        "Medicamento",
                        cmbMedicamento
                )
        );

        campos.add(
                crearCampo(
                        "Precio unitario",
                        txtPrecio
                )
        );

        campos.add(
                crearCampo(
                        "Cantidad disponible",
                        txtDisponible
                )
        );

        campos.add(
                crearCampo(
                        "Cantidad a vender",
                        txtCantidad
                )
        );

        JPanel espacio =
                new JPanel();

        espacio.setBackground(Color.WHITE);

        campos.add(espacio);

        panel.add(
                campos,
                BorderLayout.CENTER
        );

        panel.add(
                crearBotones(),
                BorderLayout.SOUTH
        );

        return panel;
    }

    private JTextField crearCampoSoloLectura() {

        JTextField campo =
                new JTextField();

        campo.setEditable(false);
        campo.setBackground(FONDO);

        return campo;
    }

    private JPanel crearCampo(
            String texto,
            Component campo) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0, 5
                        )
                );

        panel.setBackground(Color.WHITE);

        JLabel etiqueta =
                new JLabel(texto);

        etiqueta.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        etiqueta.setForeground(TEXTO);

        campo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        campo.setPreferredSize(
                new Dimension(
                        150, 30
                )
        );

        panel.add(
                etiqueta,
                BorderLayout.NORTH
        );

        panel.add(
                campo,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel crearBotones() {

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        botones.setBackground(Color.WHITE);

        JButton btnAgregar =
                crearBoton(
                        "Agregar producto"
                );

        JButton btnQuitar =
                crearBoton(
                        "Quitar producto"
                );

        JButton btnRegistrar =
                crearBoton(
                        "Registrar venta"
                );

        JButton btnBuscar =
                crearBoton(
                        "Buscar venta"
                );

        JButton btnLimpiar =
                crearBoton(
                        "Limpiar"
                );

        btnAgregar.addActionListener(
                e -> agregarProducto()
        );

        btnQuitar.addActionListener(
                e -> quitarProducto()
        );

        btnRegistrar.addActionListener(
                e -> registrarVenta()
        );

        btnBuscar.addActionListener(
                e -> buscarVenta()
        );

        btnLimpiar.addActionListener(
                e -> prepararNuevaVenta()
        );

        botones.add(btnAgregar);
        botones.add(btnQuitar);
        botones.add(btnRegistrar);
        botones.add(btnBuscar);
        botones.add(btnLimpiar);

        return botones;
    }

    private JButton crearBoton(
            String texto) {

        JButton boton =
                new JButton(texto);

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        boton.setForeground(Color.WHITE);
        boton.setBackground(AZUL_ACENTO);
        boton.setFocusPainted(false);

        boton.setBorder(
                BorderFactory.createEmptyBorder(
                        9, 18, 9, 18
                )
        );

        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        return boton;
    }

    private JPanel crearPanelDetalle() {

        String[] columnas = {
            "Codigo",
            "Medicamento",
            "Cantidad",
            "Precio unitario",
            "Subtotal"
        };

        modeloDetalle =
                crearModelo(
                        columnas
                );

        tablaDetalle =
                crearTabla(
                        modeloDetalle
                );

        lblTotal =
                new JLabel(
                        "Total: "
                        + formatoMoneda(0)
                );

        lblTotal.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        lblTotal.setForeground(
                AZUL_OSCURO
        );

        JPanel pie =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        pie.setBackground(FONDO);
        pie.add(lblTotal);

        return crearSeccionTabla(
                "Productos de la venta actual",
                tablaDetalle,
                pie
        );
    }

    private JPanel crearPanelHistorial() {

        String[] columnas = {
            "Numero",
            "Fecha",
            "Cliente",
            "Productos",
            "Total"
        };

        modeloVentas =
                crearModelo(
                        columnas
                );

        tablaVentas =
                crearTabla(
                        modeloVentas
                );

        tablaVentas.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {

                    verVentaSeleccionada();
                }
            }
        });

        return crearSeccionTabla(
                "Ventas registradas "
                + "(doble clic para ver el detalle)",
                tablaVentas,
                null
        );
    }

    private DefaultTableModel crearModelo(
            String[] columnas) {

        return new DefaultTableModel(
                columnas,
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int fila,
                    int columna) {

                return false;
            }
        };
    }

    private JTable crearTabla(
            DefaultTableModel modelo) {

        JTable tabla =
                new JTable(modelo);

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tabla.setRowHeight(28);

        tabla.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        tabla.getTableHeader()
                .setBackground(
                        AZUL_OSCURO
                );

        tabla.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        return tabla;
    }

    private JPanel crearSeccionTabla(
            String titulo,
            JTable tabla,
            JPanel pie) {

        JPanel seccion =
                new JPanel(
                        new BorderLayout(
                                0, 8
                        )
                );

        seccion.setBackground(FONDO);

        JLabel etiqueta =
                new JLabel(titulo);

        etiqueta.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        etiqueta.setForeground(TEXTO);

        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setPreferredSize(
                new Dimension(
                        0, 150
                )
        );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDE
                )
        );

        seccion.add(
                etiqueta,
                BorderLayout.NORTH
        );

        seccion.add(
                scroll,
                BorderLayout.CENTER
        );

        if (pie != null) {

            seccion.add(
                    pie,
                    BorderLayout.SOUTH
            );
        }

        return seccion;
    }

    private void cargarClientes() {

        Cliente seleccionado =
                (Cliente) cmbCliente
                        .getSelectedItem();

        cmbCliente.removeAllItems();
        cmbCliente.addItem(null);

        for (Cliente cliente
                : logicaCliente.listarClientes()) {

            cmbCliente.addItem(
                    cliente
            );
        }

        if (seleccionado != null) {

            cmbCliente.setSelectedItem(
                    seleccionado
            );

        } else {

            cmbCliente.setSelectedIndex(0);
        }
    }

    private void cargarMedicamentos() {

        Medicamento seleccionado =
                (Medicamento) cmbMedicamento
                        .getSelectedItem();

        cmbMedicamento.removeAllItems();
        cmbMedicamento.addItem(null);

        for (Medicamento medicamento
                : logicaMedicamento
                        .listarMedicamentos()) {

            cmbMedicamento.addItem(
                    medicamento
            );
        }

        if (seleccionado != null) {

            cmbMedicamento.setSelectedItem(
                    seleccionado
            );

        } else {

            cmbMedicamento.setSelectedIndex(0);
        }

        mostrarDatosMedicamento();
    }

    private void mostrarDatosMedicamento() {

        if (txtPrecio == null) {
            return;
        }

        Medicamento medicamento =
                (Medicamento) cmbMedicamento
                        .getSelectedItem();

        if (medicamento == null) {

            txtPrecio.setText("");
            txtDisponible.setText("");

            return;
        }

        txtPrecio.setText(
                formatoMoneda(
                        medicamento.getPrecio()
                )
        );

        txtDisponible.setText(
                String.valueOf(
                        Math.max(
                                0,
                                medicamento
                                        .getCantidadDisponible()
                                - cantidadEnCarrito(
                                        medicamento
                                                .getIdMedicamento()
                                )
                        )
                )
        );
    }

    /*
     * Regla de negocio:
     * debe existir al menos un cliente y un medicamento
     * registrados antes de realizar una venta.
     */
    private void validarRequisitosVenta() {

        if (logicaCliente
                .listarClientes()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe registrar al menos un cliente "
                    + "antes de realizar una venta."
            );
        }

        if (logicaMedicamento
                .listarMedicamentos()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe registrar al menos un medicamento "
                    + "antes de realizar una venta."
            );
        }
    }

    private void agregarProducto() {

        try {

            validarRequisitosVenta();

            Medicamento medicamento =
                    (Medicamento) cmbMedicamento
                            .getSelectedItem();

            if (medicamento == null) {

                throw new IllegalArgumentException(
                        "Debe seleccionar un medicamento."
                );
            }

            int cantidad =
                    leerCantidad();

            int disponible =
                    medicamento
                            .getCantidadDisponible()
                    - cantidadEnCarrito(
                            medicamento
                                    .getIdMedicamento()
                    );

            if (cantidad > disponible) {

                throw new IllegalArgumentException(
                        "La cantidad solicitada supera "
                        + "el inventario disponible."
                );
            }

            DetalleVenta existente =
                    buscarDetalle(
                            medicamento
                                    .getIdMedicamento()
                    );

            if (existente != null) {

                existente.setCantidad(
                        existente.getCantidad()
                        + cantidad
                );

                existente.calcularSubtotal();

            } else {

                DetalleVenta detalle =
                        new DetalleVenta(
                                medicamento,
                                cantidad,
                                medicamento.getPrecio(),
                                0
                        );

                detalle.calcularSubtotal();

                detallesActuales.add(
                        detalle
                );
            }

            txtCantidad.setText("");

            actualizarTablaDetalle();
            mostrarDatosMedicamento();

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void quitarProducto() {

        int fila =
                tablaDetalle
                        .getSelectedRow();

        if (fila == -1) {

            mostrarError(
                    "Seleccione en la tabla "
                    + "el producto que desea quitar."
            );

            return;
        }

        detallesActuales.remove(
                fila
        );

        actualizarTablaDetalle();
        mostrarDatosMedicamento();
    }

    private void registrarVenta() {

        try {

            // Primero valida que el sistema tenga datos para vender
            validarRequisitosVenta();

            Cliente cliente =
                    (Cliente) cmbCliente
                            .getSelectedItem();

            // Debe seleccionarse un cliente
            if (cliente == null) {

                throw new IllegalArgumentException(
                        "Debe seleccionar un cliente."
                );
            }

            // Debe agregarse por lo menos un medicamento
            if (detallesActuales.isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe agregar al menos un medicamento "
                        + "a la venta."
                );
            }

            Venta venta =
                    new Venta(
                            Integer.parseInt(
                                    txtNumeroVenta
                                            .getText()
                            ),
                            new Date(),
                            cliente,
                            new ArrayList<>(
                                    detallesActuales
                            ),
                            0
                    );

            logicaVenta.registrarVenta(
                    venta
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Venta #"
                    + venta.getNumeroVenta()
                    + " registrada correctamente.\n"
                    + "Total: "
                    + formatoMoneda(
                            venta.getTotal()
                    ),
                    "Venta registrada",
                    JOptionPane.INFORMATION_MESSAGE
            );

            actualizarTablaVentas();
            cargarMedicamentos();
            prepararNuevaVenta();

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void buscarVenta() {

        try {

            String texto =
                    JOptionPane.showInputDialog(
                            this,
                            "Ingrese el numero "
                            + "de la venta:",
                            "Buscar venta",
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (texto == null) {
                return;
            }

            texto = texto.trim();

            if (texto.isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe ingresar el numero "
                        + "de la venta."
                );
            }

            int numero;

            try {

                numero =
                        Integer.parseInt(
                                texto
                        );

            } catch (NumberFormatException e) {

                throw new IllegalArgumentException(
                        "El numero de venta debe "
                        + "ser un numero entero."
                );
            }

            Venta venta =
                    logicaVenta.buscarVenta(
                            numero
                    );

            if (venta == null) {

                throw new IllegalArgumentException(
                        "No existe una venta "
                        + "con ese numero."
                );
            }

            mostrarDetalleVenta(
                    venta
            );

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void verVentaSeleccionada() {

        int fila =
                tablaVentas
                        .getSelectedRow();

        if (fila == -1) {
            return;
        }

        int numero =
                Integer.parseInt(
                        tablaVentas
                                .getValueAt(
                                        fila,
                                        0
                                )
                                .toString()
                );

        Venta venta =
                logicaVenta.buscarVenta(
                        numero
                );

        if (venta != null) {

            mostrarDetalleVenta(
                    venta
            );
        }
    }

    private void mostrarDetalleVenta(
            Venta venta) {

        DefaultTableModel modelo =
                crearModelo(
                        new String[]{
                            "Medicamento",
                            "Cantidad",
                            "Precio unitario",
                            "Subtotal"
                        }
                );

        for (DetalleVenta detalle
                : venta.getDetalles()) {

            modelo.addRow(
                    new Object[]{
                        detalle
                                .getMedicamento()
                                .getNombre(),
                        detalle
                                .getCantidad(),
                        formatoMoneda(
                                detalle
                                        .getPrecioUnitario()
                        ),
                        formatoMoneda(
                                detalle
                                        .getSubTotal()
                        )
                    }
            );
        }

        JTable tabla =
                crearTabla(
                        modelo
                );

        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setPreferredSize(
                new Dimension(
                        520, 180
                )
        );

        JLabel info =
                new JLabel(
                        "<html><b>Venta #"
                        + venta.getNumeroVenta()
                        + "</b><br>Fecha: "
                        + formatoFecha(
                                venta.getFecha()
                        )
                        + "<br>Cliente: "
                        + nombreCliente(
                                venta.getCliente()
                        )
                        + "<br>Total: <b>"
                        + formatoMoneda(
                                venta.getTotal()
                        )
                        + "</b></html>"
                );

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0, 10
                        )
                );

        panel.add(
                info,
                BorderLayout.NORTH
        );

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        JOptionPane.showMessageDialog(
                this,
                panel,
                "Detalle de venta",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    private void prepararNuevaVenta() {

        detallesActuales.clear();

        actualizarTablaDetalle();

        txtNumeroVenta.setText(
                String.valueOf(
                        siguienteNumeroVenta()
                )
        );

        txtFecha.setText(
                formatoFecha(
                        new Date()
                )
        );

        txtCantidad.setText("");

        if (cmbCliente.getItemCount() > 0) {
            cmbCliente.setSelectedIndex(0);
        }

        if (cmbMedicamento.getItemCount() > 0) {
            cmbMedicamento.setSelectedIndex(0);
        }

        mostrarDatosMedicamento();
    }

    private void actualizarTablaDetalle() {

        modeloDetalle.setRowCount(0);

        double total = 0;

        for (DetalleVenta detalle
                : detallesActuales) {

            modeloDetalle.addRow(
                    new Object[]{
                        detalle
                                .getMedicamento()
                                .getIdMedicamento(),
                        detalle
                                .getMedicamento()
                                .getNombre(),
                        detalle
                                .getCantidad(),
                        formatoMoneda(
                                detalle
                                        .getPrecioUnitario()
                        ),
                        formatoMoneda(
                                detalle
                                        .getSubTotal()
                        )
                    }
            );

            total +=
                    detalle.getSubTotal();
        }

        lblTotal.setText(
                "Total: "
                + formatoMoneda(total)
        );

        lblTotal.revalidate();
        lblTotal.repaint();
    }

    private void actualizarTablaVentas() {

        modeloVentas.setRowCount(0);

        for (Venta venta
                : logicaVenta.listarVentas()) {

            int productos = 0;

            for (DetalleVenta detalle
                    : venta.getDetalles()) {

                productos +=
                        detalle.getCantidad();
            }

            modeloVentas.addRow(
                    new Object[]{
                        venta.getNumeroVenta(),
                        formatoFecha(
                                venta.getFecha()
                        ),
                        nombreCliente(
                                venta.getCliente()
                        ),
                        productos,
                        formatoMoneda(
                                venta.getTotal()
                        )
                    }
            );
        }
    }

    private int siguienteNumeroVenta() {

        int mayor = 0;

        for (Venta venta
                : logicaVenta.listarVentas()) {

            mayor =
                    Math.max(
                            mayor,
                            venta.getNumeroVenta()
                    );
        }

        return mayor + 1;
    }

    private int leerCantidad() {

        String texto =
                txtCantidad
                        .getText()
                        .trim();

        if (texto.isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe ingresar la cantidad."
            );
        }

        int cantidad;

        try {

            cantidad =
                    Integer.parseInt(
                            texto
                    );

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "La cantidad debe ser "
                    + "un numero entero."
            );
        }

        if (cantidad <= 0) {

            throw new IllegalArgumentException(
                    "La cantidad debe ser "
                    + "mayor que cero."
            );
        }

        return cantidad;
    }

    private DetalleVenta buscarDetalle(
            int idMedicamento) {

        for (DetalleVenta detalle
                : detallesActuales) {

            if (detalle
                    .getMedicamento()
                    .getIdMedicamento()
                    == idMedicamento) {

                return detalle;
            }
        }

        return null;
    }

    private int cantidadEnCarrito(
            int idMedicamento) {

        DetalleVenta detalle =
                buscarDetalle(
                        idMedicamento
                );

        return detalle == null
                ? 0
                : detalle.getCantidad();
    }

    private String nombreCliente(
            Cliente cliente) {

        if (cliente == null) {
            return "";
        }

        return cliente.getNombre()
                + " "
                + cliente.getApellido1();
    }

    private String formatoMoneda(
            double monto) {

        return String.format(
                "₡%,.2f",
                monto
        );
    }

    private String formatoFecha(
            Date fecha) {

        if (fecha == null) {
            return "";
        }

        return new SimpleDateFormat(
                "dd/MM/yyyy"
        ).format(fecha);
    }

    private void mostrarError(
            String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}