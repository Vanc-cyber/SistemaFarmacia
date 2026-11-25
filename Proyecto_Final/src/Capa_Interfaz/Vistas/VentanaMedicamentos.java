package Capa_Interfaz.Vistas;

import Capa_Entidades.Categoria;
import Capa_Entidades.Medicamento;
import Capa_Entidades.Proveedor;
import Capa_LogicaNegocio.LogicaCategoria;
import Capa_LogicaNegocio.LogicaMedicamento;
import Capa_LogicaNegocio.LogicaProveedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaMedicamentos extends JPanel {

    private final Color FONDO = new Color(242, 245, 249);
    private final Color TEXTO = new Color(40, 52, 70);
    private final Color AZUL_ACENTO = new Color(45, 145, 190);
    private final Color AZUL_OSCURO = new Color(23, 37, 58);

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtFechaVencimiento;

    private JComboBox<Categoria> cmbCategoria;
    private JComboBox<Proveedor> cmbProveedor;

    private JTable tablaMedicamentos;
    private DefaultTableModel modeloTabla;

    private final LogicaMedicamento logicaMedicamento;
    private final LogicaCategoria logicaCategoria;
    private final LogicaProveedor logicaProveedor;

    public VentanaMedicamentos(
            LogicaMedicamento logicaMedicamento,
            LogicaCategoria logicaCategoria,
            LogicaProveedor logicaProveedor) {

        if (logicaMedicamento == null
                || logicaCategoria == null
                || logicaProveedor == null) {

            throw new IllegalArgumentException(
                    "Las capas logicas son obligatorias."
            );
        }

        this.logicaMedicamento = logicaMedicamento;
        this.logicaCategoria = logicaCategoria;
        this.logicaProveedor = logicaProveedor;

        setLayout(new BorderLayout(0, 20));
        setBackground(FONDO);
        setBorder(
                BorderFactory.createEmptyBorder(
                        30, 35, 30, 35
                )
        );

        crearEncabezado();
        crearContenido();

        cargarCategorias();
        cargarProveedores();
        actualizarTabla();

        // Recarga los datos al volver a mostrar la ventana
        addComponentListener(
                new java.awt.event.ComponentAdapter() {

            @Override
            public void componentShown(
                    java.awt.event.ComponentEvent e) {

                cargarCategorias();
                cargarProveedores();
                actualizarTabla();
            }
        });
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
                new JLabel("Medicamentos");

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
                        "Administracion del inventario de medicamentos"
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
                                0, 20
                        )
                );

        contenido.setBackground(FONDO);

        contenido.add(
                crearFormulario(),
                BorderLayout.NORTH
        );

        contenido.add(
                crearTabla(),
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
                                new Color(225, 230, 237)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 25, 20, 25
                        )
                )
        );

        JPanel campos =
                new JPanel(
                        new GridLayout(
                                3, 3, 15, 12
                        )
                );

        campos.setBackground(Color.WHITE);

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtDescripcion = new JTextField();
        txtPrecio = new JTextField();
        txtCantidad = new JTextField();
        txtFechaVencimiento = new JTextField();

        cmbCategoria = new JComboBox<>();

        cmbCategoria.setRenderer(
                crearRenderer(
                        "Seleccione una categoria"
                )
        );

        cmbProveedor = new JComboBox<>();

        cmbProveedor.setRenderer(
                crearRenderer(
                        "Seleccione un proveedor"
                )
        );

        campos.add(
                crearCampo(
                        "Identificador",
                        txtId
                )
        );

        campos.add(
                crearCampo(
                        "Nombre",
                        txtNombre
                )
        );

        campos.add(
                crearCampo(
                        "Descripcion",
                        txtDescripcion
                )
        );

        campos.add(
                crearCampo(
                        "Precio",
                        txtPrecio
                )
        );

        campos.add(
                crearCampo(
                        "Cantidad disponible",
                        txtCantidad
                )
        );

        campos.add(
                crearCampo(
                        "Fecha vencimiento (dd/MM/yyyy)",
                        txtFechaVencimiento
                )
        );

        campos.add(
                crearCampo(
                        "Categoria",
                        cmbCategoria
                )
        );

        campos.add(
                crearCampo(
                        "Proveedor",
                        cmbProveedor
                )
        );

        JPanel espacio = new JPanel();
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

    private DefaultListCellRenderer crearRenderer(
            String textoVacio) {

        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean conFoco) {

                super.getListCellRendererComponent(
                        lista,
                        valor,
                        indice,
                        seleccionado,
                        conFoco
                );

                if (valor == null) {

                    setText(textoVacio);

                } else if (valor instanceof Proveedor) {

                    Proveedor proveedor =
                            (Proveedor) valor;

                    setText(
                            proveedor.getEmpresa()
                            + " - "
                            + proveedor.getNombre()
                    );

                } else if (valor instanceof Categoria) {

                    Categoria categoria =
                            (Categoria) valor;

                    setText(
                            categoria.getNombre()
                    );
                }

                return this;
            }
        };
    }

    private JPanel crearCampo(
            String texto,
            JComponent campo) {

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

        JButton btnRegistrar =
                crearBoton("Registrar");

        JButton btnBuscar =
                crearBoton("Buscar");

        JButton btnModificar =
                crearBoton("Modificar");

        JButton btnEliminar =
                crearBoton("Eliminar");

        JButton btnLimpiar =
                crearBoton("Limpiar");

        btnRegistrar.addActionListener(
                e -> registrarMedicamento()
        );

        btnBuscar.addActionListener(
                e -> buscarMedicamento()
        );

        btnModificar.addActionListener(
                e -> modificarMedicamento()
        );

        btnEliminar.addActionListener(
                e -> eliminarMedicamento()
        );

        btnLimpiar.addActionListener(
                e -> limpiarCampos()
        );

        botones.add(btnRegistrar);
        botones.add(btnBuscar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
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

    private JScrollPane crearTabla() {

        String[] columnas = {
            "Identificador",
            "Nombre",
            "Descripcion",
            "Precio",
            "Cantidad",
            "Vencimiento",
            "Categoria",
            "Proveedor"
        };

        modeloTabla =
                new DefaultTableModel(
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

        tablaMedicamentos =
                new JTable(
                        modeloTabla
                );

        tablaMedicamentos.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tablaMedicamentos.setRowHeight(28);

        tablaMedicamentos
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        tablaMedicamentos
                .getTableHeader()
                .setBackground(
                        AZUL_OSCURO
                );

        tablaMedicamentos
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        tablaMedicamentos.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                cargarMedicamentoSeleccionado();
            }
        });

        JScrollPane scroll =
                new JScrollPane(
                        tablaMedicamentos
                );

        scroll.setPreferredSize(
                new Dimension(
                        0, 220
                )
        );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                225, 230, 237
                        )
                )
        );

        return scroll;
    }

    private void cargarCategorias() {

        Categoria seleccionada =
                (Categoria) cmbCategoria
                        .getSelectedItem();

        cmbCategoria.removeAllItems();
        cmbCategoria.addItem(null);

        for (Categoria categoria
                : logicaCategoria.listarCategorias()) {

            cmbCategoria.addItem(
                    categoria
            );
        }

        seleccionarCategoria(
                seleccionada
        );
    }

    private void cargarProveedores() {

        Proveedor seleccionado =
                (Proveedor) cmbProveedor
                        .getSelectedItem();

        cmbProveedor.removeAllItems();
        cmbProveedor.addItem(null);

        for (Proveedor proveedor
                : logicaProveedor.listarProveedores()) {

            cmbProveedor.addItem(
                    proveedor
            );
        }

        seleccionarProveedor(
                seleccionado
        );
    }

    private void seleccionarCategoria(
            Categoria categoria) {

        if (cmbCategoria.getItemCount() > 0) {
            cmbCategoria.setSelectedIndex(0);
        }

        if (categoria == null) {
            return;
        }

        for (int i = 1;
                i < cmbCategoria.getItemCount();
                i++) {

            Categoria item =
                    cmbCategoria.getItemAt(i);

            if (item.getIdCategoria()
                    == categoria.getIdCategoria()) {

                cmbCategoria.setSelectedIndex(i);
                return;
            }
        }
    }

    private void seleccionarProveedor(
            Proveedor proveedor) {

        if (cmbProveedor.getItemCount() > 0) {
            cmbProveedor.setSelectedIndex(0);
        }

        if (proveedor == null) {
            return;
        }

        for (int i = 1;
                i < cmbProveedor.getItemCount();
                i++) {

            Proveedor item =
                    cmbProveedor.getItemAt(i);

            if (item.getIdentificacion() != null
                    && item.getIdentificacion()
                            .equals(
                                    proveedor.getIdentificacion()
                            )) {

                cmbProveedor.setSelectedIndex(i);
                return;
            }
        }
    }

    private int leerEntero(
            String texto,
            String mensaje) {

        try {

            return Integer.parseInt(
                    texto.trim()
            );

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    mensaje
            );
        }
    }

    private double leerPrecio(
            String texto) {

        try {

            return Double.parseDouble(
                    texto.trim()
            );

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "El precio debe ser un numero."
            );
        }
    }

    // Valida formato, existencia y que la fecha no sea anterior a hoy
    private Date leerFecha(
            String texto) {

        texto = texto.trim();

        if (texto.isEmpty()) {

            throw new IllegalArgumentException(
                    "La fecha de vencimiento es obligatoria."
            );
        }

        if (!texto.matches(
                "\\d{2}/\\d{2}/\\d{4}")) {

            throw new IllegalArgumentException(
                    "La fecha debe tener el formato dd/MM/yyyy."
            );
        }

        SimpleDateFormat formato =
                new SimpleDateFormat(
                        "dd/MM/yyyy"
                );

        formato.setLenient(false);

        Date fechaVencimiento;

        try {

            fechaVencimiento =
                    formato.parse(texto);

        } catch (ParseException e) {

            throw new IllegalArgumentException(
                    "La fecha ingresada no es valida."
            );
        }

        Calendar hoy =
                Calendar.getInstance();

        Calendar vencimiento =
                Calendar.getInstance();

        vencimiento.setTime(
                fechaVencimiento
        );

        // Elimina la hora para comparar solamente el día
        hoy.set(
                Calendar.HOUR_OF_DAY,
                0
        );

        hoy.set(
                Calendar.MINUTE,
                0
        );

        hoy.set(
                Calendar.SECOND,
                0
        );

        hoy.set(
                Calendar.MILLISECOND,
                0
        );

        vencimiento.set(
                Calendar.HOUR_OF_DAY,
                0
        );

        vencimiento.set(
                Calendar.MINUTE,
                0
        );

        vencimiento.set(
                Calendar.SECOND,
                0
        );

        vencimiento.set(
                Calendar.MILLISECOND,
                0
        );

        if (vencimiento.before(hoy)) {

            throw new IllegalArgumentException(
                    "La fecha de vencimiento no puede "
                    + "ser anterior a la fecha actual."
            );
        }

        return fechaVencimiento;
    }

    private void cargarMedicamentoSeleccionado() {

        try {

            int fila =
                    tablaMedicamentos
                            .getSelectedRow();

            if (fila == -1) {
                return;
            }

            int id =
                    Integer.parseInt(
                            tablaMedicamentos
                                    .getValueAt(
                                            fila,
                                            0
                                    )
                                    .toString()
                    );

            Medicamento medicamento =
                    logicaMedicamento
                            .buscarMedicamento(
                                    id
                            );

            if (medicamento != null) {

                mostrarMedicamento(
                        medicamento
                );

                txtId.setEditable(false);

                JOptionPane.showMessageDialog(
                        this,
                        "Puede modificar los datos del medicamento.\n"
                        + "Para guardar los cambios debe presionar "
                        + "el boton Modificar.",
                        "Modificar medicamento",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void registrarMedicamento() {

        try {

            // Debe existir al menos una categoria
            if (logicaCategoria
                    .listarCategorias()
                    .isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe registrar al menos una categoria "
                        + "antes de registrar medicamentos."
                );
            }

            // Debe existir al menos un proveedor
            if (logicaProveedor
                    .listarProveedores()
                    .isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe registrar al menos un proveedor "
                        + "antes de registrar medicamentos."
                );
            }

            if (!txtId.isEditable()) {

                throw new IllegalArgumentException(
                        "Presione Limpiar antes de registrar "
                        + "un nuevo medicamento."
                );
            }

            Medicamento medicamento =
                    crearMedicamento();

            logicaMedicamento
                    .registrarMedicamento(
                            medicamento
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Medicamento registrado correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void buscarMedicamento() {

        try {

            String texto =
                    JOptionPane.showInputDialog(
                            this,
                            "Ingrese el identificador "
                            + "del medicamento:",
                            "Buscar medicamento",
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (texto == null) {
                return;
            }

            int id =
                    leerEntero(
                            texto,
                            "El identificador debe "
                            + "ser un numero entero."
                    );

            Medicamento medicamento =
                    logicaMedicamento
                            .buscarMedicamento(
                                    id
                            );

            if (medicamento == null) {

                throw new IllegalArgumentException(
                        "No existe un medicamento registrado "
                        + "con ese identificador."
                );
            }

            mostrarMedicamento(
                    medicamento
            );

            txtId.setEditable(false);

            JOptionPane.showMessageDialog(
                    this,
                    "Medicamento encontrado.\n"
                    + "Puede modificar los datos y luego presionar "
                    + "el boton Modificar para guardar los cambios.",
                    "Medicamento encontrado",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void modificarMedicamento() {

        try {

            if (txtId.isEditable()) {

                throw new IllegalArgumentException(
                        "Primero debe buscar o seleccionar "
                        + "el medicamento que desea modificar."
                );
            }

            Medicamento medicamento =
                    crearMedicamento();

            boolean modificado =
                    logicaMedicamento
                            .modificarMedicamento(
                                    medicamento
                            );

            if (modificado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Medicamento modificado correctamente."
                );

                actualizarTabla();
                limpiarCampos();

            } else {

                mostrarError(
                        "No se pudo modificar el medicamento."
                );
            }

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private void eliminarMedicamento() {

        try {

            String texto =
                    JOptionPane.showInputDialog(
                            this,
                            "Ingrese el identificador del medicamento "
                            + "que desea eliminar:",
                            "Eliminar medicamento",
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (texto == null) {
                return;
            }

            int id =
                    leerEntero(
                            texto,
                            "El identificador debe "
                            + "ser un numero entero."
                    );

            Medicamento medicamento =
                    logicaMedicamento
                            .buscarMedicamento(
                                    id
                            );

            if (medicamento == null) {

                throw new IllegalArgumentException(
                        "No existe un medicamento registrado "
                        + "con ese identificador."
                );
            }

            String informacion =
                    "¿Está seguro de eliminar este medicamento?\n\n"
                    + "Identificador: "
                    + medicamento.getIdMedicamento()
                    + "\nNombre: "
                    + medicamento.getNombre()
                    + "\nPrecio: "
                    + medicamento.getPrecio()
                    + "\nCantidad: "
                    + medicamento.getCantidadDisponible();

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            informacion,
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (respuesta
                    == JOptionPane.YES_OPTION) {

                boolean eliminado =
                        logicaMedicamento
                                .eliminarMedicamento(
                                        id
                                );

                if (eliminado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Medicamento eliminado correctamente."
                    );

                    actualizarTabla();
                    limpiarCampos();

                } else {

                    mostrarError(
                            "No se pudo eliminar el medicamento."
                    );
                }
            }

        } catch (IllegalArgumentException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    private Medicamento crearMedicamento() {

        Medicamento medicamento =
                new Medicamento();

        medicamento.setIdMedicamento(
                leerEntero(
                        txtId.getText(),
                        "El identificador debe "
                        + "ser un numero entero."
                )
        );

        medicamento.setNombre(
                txtNombre.getText()
        );

        medicamento.setDescripcion(
                txtDescripcion.getText()
        );

        medicamento.setPrecio(
                leerPrecio(
                        txtPrecio.getText()
                )
        );

        medicamento.setCantidadDisponible(
                leerEntero(
                        txtCantidad.getText(),
                        "La cantidad disponible debe "
                        + "ser un numero entero."
                )
        );

        medicamento.setFechaVencimiento(
                leerFecha(
                        txtFechaVencimiento.getText()
                )
        );

        medicamento.setCategoria(
                (Categoria) cmbCategoria
                        .getSelectedItem()
        );

        medicamento.setProveedor(
                (Proveedor) cmbProveedor
                        .getSelectedItem()
        );

        return medicamento;
    }

    private void mostrarMedicamento(
            Medicamento medicamento) {

        SimpleDateFormat formato =
                new SimpleDateFormat(
                        "dd/MM/yyyy"
                );

        txtId.setText(
                String.valueOf(
                        medicamento
                                .getIdMedicamento()
                )
        );

        txtNombre.setText(
                medicamento.getNombre()
        );

        txtDescripcion.setText(
                medicamento.getDescripcion()
        );

        txtPrecio.setText(
                String.valueOf(
                        medicamento.getPrecio()
                )
        );

        txtCantidad.setText(
                String.valueOf(
                        medicamento
                                .getCantidadDisponible()
                )
        );

        if (medicamento
                .getFechaVencimiento()
                != null) {

            txtFechaVencimiento.setText(
                    formato.format(
                            medicamento
                                    .getFechaVencimiento()
                    )
            );

        } else {

            txtFechaVencimiento.setText("");
        }

        seleccionarCategoria(
                medicamento.getCategoria()
        );

        seleccionarProveedor(
                medicamento.getProveedor()
        );
    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        SimpleDateFormat formato =
                new SimpleDateFormat(
                        "dd/MM/yyyy"
                );

        for (Medicamento medicamento
                : logicaMedicamento
                        .listarMedicamentos()) {

            String fecha = "";

            if (medicamento
                    .getFechaVencimiento()
                    != null) {

                fecha =
                        formato.format(
                                medicamento
                                        .getFechaVencimiento()
                        );
            }

            String categoria = "";

            if (medicamento.getCategoria()
                    != null) {

                categoria =
                        medicamento
                                .getCategoria()
                                .getNombre();
            }

            String proveedor = "";

            if (medicamento.getProveedor()
                    != null) {

                proveedor =
                        medicamento
                                .getProveedor()
                                .getEmpresa();
            }

            modeloTabla.addRow(
                    new Object[]{
                        medicamento
                                .getIdMedicamento(),
                        medicamento
                                .getNombre(),
                        medicamento
                                .getDescripcion(),
                        medicamento
                                .getPrecio(),
                        medicamento
                                .getCantidadDisponible(),
                        fecha,
                        categoria,
                        proveedor
                    }
            );
        }
    }

    private void limpiarCampos() {

        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtFechaVencimiento.setText("");

        if (cmbCategoria.getItemCount() > 0) {
            cmbCategoria.setSelectedIndex(0);
        }

        if (cmbProveedor.getItemCount() > 0) {
            cmbProveedor.setSelectedIndex(0);
        }

        txtId.setEditable(true);

        tablaMedicamentos.clearSelection();

        txtId.requestFocus();
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