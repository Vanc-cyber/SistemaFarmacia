package Capa_Interfaz.Vistas;

import Capa_Entidades.Proveedor;
import Capa_LogicaNegocio.LogicaProveedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaProveedores extends JPanel {

    // Colores utilizados en la interfaz
    private final Color FONDO = new Color(242, 245, 249);
    private final Color TEXTO = new Color(40, 52, 70);
    private final Color AZUL_ACENTO = new Color(45, 145, 190);
    private final Color AZUL_OSCURO = new Color(23, 37, 58);

    // Campos para ingresar los datos del proveedor
    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JTextField txtApellido1;
    private JTextField txtApellido2;
    private JTextField txtFechaNacimiento;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtEmpresa;

    // Componentes para mostrar los proveedores
    private JTable tablaProveedores;
    private DefaultTableModel modeloTabla;

    // Comunicación con la capa lógica
    private final LogicaProveedor logicaProveedor;

    public VentanaProveedores() {
        logicaProveedor = new LogicaProveedor();

        setLayout(new BorderLayout(0, 20));
        setBackground(FONDO);
        setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));

        crearEncabezado();
        crearContenido();
        actualizarTabla();
    }

    // Crea el encabezado de la sección
    private void crearEncabezado() {
        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 5));
        encabezado.setBackground(FONDO);

        JLabel titulo = new JLabel("Proveedores");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 27));
        titulo.setForeground(TEXTO);

        JLabel descripcion = new JLabel("Administracion de proveedores");
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descripcion.setForeground(Color.GRAY);

        encabezado.add(titulo);
        encabezado.add(descripcion);

        add(encabezado, BorderLayout.NORTH);
    }

    // Organiza el formulario y la tabla
    private void crearContenido() {
        JPanel contenido = new JPanel(new BorderLayout(0, 20));
        contenido.setBackground(FONDO);

        contenido.add(crearFormulario(), BorderLayout.NORTH);
        contenido.add(crearTabla(), BorderLayout.CENTER);

        add(contenido, BorderLayout.CENTER);
    }

    // Crea los campos del formulario
    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 237)),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        JPanel campos = new JPanel(new GridLayout(3, 4, 15, 12));
        campos.setBackground(Color.WHITE);

        txtIdentificacion = new JTextField();
        txtNombre = new JTextField();
        txtApellido1 = new JTextField();
        txtApellido2 = new JTextField();
        txtFechaNacimiento = new JTextField();
        txtDireccion = new JTextField();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();
        txtEmpresa = new JTextField();

        campos.add(crearCampo("Identificacion", txtIdentificacion));
        campos.add(crearCampo("Nombre", txtNombre));
        campos.add(crearCampo("Primer apellido", txtApellido1));
        campos.add(crearCampo("Segundo apellido", txtApellido2));
        campos.add(crearCampo("Fecha nacimiento (dd/MM/yyyy)", txtFechaNacimiento));
        campos.add(crearCampo("Direccion", txtDireccion));
        campos.add(crearCampo("Correo electronico", txtCorreo));
        campos.add(crearCampo("Telefono", txtTelefono));
        campos.add(crearCampo("Empresa", txtEmpresa));

        JPanel espacio1 = new JPanel();
        JPanel espacio2 = new JPanel();
        JPanel espacio3 = new JPanel();

        espacio1.setBackground(Color.WHITE);
        espacio2.setBackground(Color.WHITE);
        espacio3.setBackground(Color.WHITE);

        campos.add(espacio1);
        campos.add(espacio2);
        campos.add(espacio3);

        panel.add(campos, BorderLayout.CENTER);
        panel.add(crearBotones(), BorderLayout.SOUTH);

        return panel;
    }

    // Crea cada campo con su etiqueta
    private JPanel crearCampo(String texto, JTextField campo) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Color.WHITE);

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        etiqueta.setForeground(TEXTO);

        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setPreferredSize(new Dimension(150, 30));

        panel.add(etiqueta, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }

    // Crea los botones y asigna sus eventos
    private JPanel crearBotones() {
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        botones.setBackground(Color.WHITE);

        JButton btnRegistrar = crearBoton("Registrar");
        JButton btnBuscar = crearBoton("Buscar");
        JButton btnModificar = crearBoton("Modificar");
        JButton btnEliminar = crearBoton("Eliminar");
        JButton btnLimpiar = crearBoton("Limpiar");

        btnRegistrar.addActionListener(e -> registrarProveedor());
        btnBuscar.addActionListener(e -> buscarProveedor());
        btnModificar.addActionListener(e -> modificarProveedor());
        btnEliminar.addActionListener(e -> eliminarProveedor());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        botones.add(btnRegistrar);
        botones.add(btnBuscar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        return botones;
    }

    // Aplica el mismo diseño a los botones
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);

        boton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        boton.setForeground(Color.WHITE);
        boton.setBackground(AZUL_ACENTO);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        return boton;
    }

    // Crea la tabla de proveedores
    private JScrollPane crearTabla() {
        String[] columnas = {
            "Identificacion",
            "Empresa",
            "Nombre",
            "Apellido 1",
            "Apellido 2",
            "Nacimiento",
            "Telefono",
            "Correo"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProveedores = new JTable(modeloTabla);
        tablaProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaProveedores.setRowHeight(28);

        tablaProveedores.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        tablaProveedores.getTableHeader().setBackground(AZUL_OSCURO);
        tablaProveedores.getTableHeader().setForeground(Color.WHITE);

        // Al seleccionar una fila carga los datos del proveedor
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarProveedorSeleccionado();
            }
        });

        JScrollPane scroll = new JScrollPane(tablaProveedores);
        scroll.setPreferredSize(new Dimension(0, 220));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 230, 237)));

        return scroll;
    }

    // Carga los datos del proveedor seleccionado
    private void cargarProveedorSeleccionado() {
        try {
            int fila = tablaProveedores.getSelectedRow();

            if (fila == -1) {
                return;
            }

            String identificacion = tablaProveedores.getValueAt(fila, 0).toString();
            Proveedor proveedor = logicaProveedor.buscarProveedor(identificacion);

            if (proveedor != null) {
                mostrarProveedor(proveedor);
                txtIdentificacion.setEditable(false);

                JOptionPane.showMessageDialog(
                        this,
                        "Puede modificar los datos del proveedor.\n"
                        + "Para guardar los cambios debe presionar "
                        + "el boton Modificar.",
                        "Modificar proveedor",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // Registra un nuevo proveedor
    private void registrarProveedor() {
        try {
            if (!txtIdentificacion.isEditable()) {
                throw new IllegalArgumentException(
                        "Presione Limpiar antes de registrar un nuevo proveedor."
                );
            }

            Proveedor proveedor = crearProveedor();
            logicaProveedor.registrarProveedor(proveedor);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor registrado correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // Busca un proveedor solicitando su identificacion
    private void buscarProveedor() {
        try {
            String identificacion = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la identificacion del proveedor:",
                    "Buscar proveedor",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (identificacion == null) {
                return;
            }

            identificacion = identificacion.trim();

            if (identificacion.isEmpty()) {
                throw new IllegalArgumentException(
                        "Debe ingresar la identificacion del proveedor."
                );
            }

            if (!identificacion.matches("\\d{9}")) {
                throw new IllegalArgumentException(
                        "La identificacion debe contener exactamente 9 digitos."
                );
            }

            Proveedor proveedor = logicaProveedor.buscarProveedor(identificacion);

            if (proveedor == null) {
                throw new IllegalArgumentException(
                        "No existe un proveedor registrado con esa identificacion."
                );
            }

            mostrarProveedor(proveedor);
            txtIdentificacion.setEditable(false);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor encontrado.\n"
                    + "Puede modificar los datos y luego presionar "
                    + "el boton Modificar para guardar los cambios.",
                    "Proveedor encontrado",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // Modifica los datos del proveedor seleccionado
    private void modificarProveedor() {
        try {
            if (txtIdentificacion.isEditable()) {
                throw new IllegalArgumentException(
                        "Primero debe buscar o seleccionar "
                        + "el proveedor que desea modificar."
                );
            }

            Proveedor proveedor = crearProveedor();

            boolean modificado = logicaProveedor.modificarProveedor(proveedor);

            if (modificado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Proveedor modificado correctamente."
                );

                actualizarTabla();
                limpiarCampos();

            } else {
                mostrarError("No se pudo modificar el proveedor.");
            }

        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // Solicita la identificacion y confirma antes de eliminar
    private void eliminarProveedor() {
        try {
            String identificacion = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la identificacion del proveedor que desea eliminar:",
                    "Eliminar proveedor",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (identificacion == null) {
                return;
            }

            identificacion = identificacion.trim();

            if (identificacion.isEmpty()) {
                throw new IllegalArgumentException(
                        "Debe ingresar la identificacion del proveedor."
                );
            }

            if (!identificacion.matches("\\d{9}")) {
                throw new IllegalArgumentException(
                        "La identificacion debe contener exactamente 9 digitos."
                );
            }

            Proveedor proveedor = logicaProveedor.buscarProveedor(identificacion);

            if (proveedor == null) {
                throw new IllegalArgumentException(
                        "No existe un proveedor registrado con esa identificacion."
                );
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = "";

            if (proveedor.getFechaNacimiento() != null) {
                fecha = formato.format(proveedor.getFechaNacimiento());
            }

            String informacion =
                    "¿Esta seguro de eliminar este proveedor?\n\n"
                    + "Identificacion: " + proveedor.getIdentificacion() + "\n"
                    + "Empresa: " + proveedor.getEmpresa() + "\n"
                    + "Nombre: " + proveedor.getNombre() + "\n"
                    + "Primer apellido: " + proveedor.getApellido1() + "\n"
                    + "Segundo apellido: " + proveedor.getApellido2() + "\n"
                    + "Fecha de nacimiento: " + fecha + "\n"
                    + "Direccion: " + proveedor.getDireccion() + "\n"
                    + "Correo: " + proveedor.getCorreoElectronico() + "\n"
                    + "Telefono: " + proveedor.getTelefono();

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    informacion,
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                boolean eliminado =
                        logicaProveedor.eliminarProveedor(identificacion);

                if (eliminado) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Proveedor eliminado correctamente."
                    );

                    actualizarTabla();
                    limpiarCampos();

                } else {
                    mostrarError("No se pudo eliminar el proveedor.");
                }
            }

        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // Obtiene los datos del formulario y crea el proveedor
    private Proveedor crearProveedor() {
        String identificacion = txtIdentificacion.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido1 = txtApellido1.getText().trim();
        String apellido2 = txtApellido2.getText().trim();
        String textoFecha = txtFechaNacimiento.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String empresa = txtEmpresa.getText().trim();

        // Todos los campos son obligatorios
        if (identificacion.isEmpty()
                || nombre.isEmpty()
                || apellido1.isEmpty()
                || apellido2.isEmpty()
                || textoFecha.isEmpty()
                || direccion.isEmpty()
                || correo.isEmpty()
                || telefono.isEmpty()
                || empresa.isEmpty()) {

            throw new IllegalArgumentException(
                    "Todos los campos son obligatorios."
            );
        }

        // Identificacion de 9 digitos
        if (!identificacion.matches("\\d{9}")) {
            throw new IllegalArgumentException(
                    "La identificacion debe contener exactamente 9 digitos."
            );
        }

        // Formato exacto de la fecha
        if (!textoFecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException(
                    "La fecha debe tener el formato dd/MM/yyyy."
            );
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        Date fechaNacimiento;

        try {
            fechaNacimiento = formato.parse(textoFecha);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "La fecha ingresada no es valida."
            );
        }

        // No permite fechas futuras
        if (fechaNacimiento.after(new Date())) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede ser futura."
            );
        }

        return new Proveedor(
                empresa,
                identificacion,
                nombre,
                apellido1,
                apellido2,
                fechaNacimiento,
                direccion,
                correo,
                telefono
        );
    }

    // Muestra los datos del proveedor en el formulario
    private void mostrarProveedor(Proveedor proveedor) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        txtIdentificacion.setText(proveedor.getIdentificacion());
        txtNombre.setText(proveedor.getNombre());
        txtApellido1.setText(proveedor.getApellido1());
        txtApellido2.setText(proveedor.getApellido2());

        if (proveedor.getFechaNacimiento() != null) {
            txtFechaNacimiento.setText(
                    formato.format(proveedor.getFechaNacimiento())
            );
        } else {
            txtFechaNacimiento.setText("");
        }

        txtDireccion.setText(proveedor.getDireccion());
        txtCorreo.setText(proveedor.getCorreoElectronico());
        txtTelefono.setText(proveedor.getTelefono());
        txtEmpresa.setText(proveedor.getEmpresa());
    }

    // Actualiza la tabla con los proveedores registrados
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Proveedor proveedor : logicaProveedor.listarProveedores()) {
            String fecha = "";

            if (proveedor.getFechaNacimiento() != null) {
                fecha = formato.format(proveedor.getFechaNacimiento());
            }

            modeloTabla.addRow(new Object[]{
                proveedor.getIdentificacion(),
                proveedor.getEmpresa(),
                proveedor.getNombre(),
                proveedor.getApellido1(),
                proveedor.getApellido2(),
                fecha,
                proveedor.getTelefono(),
                proveedor.getCorreoElectronico()
            });
        }
    }

    // Limpia el formulario para ingresar un nuevo proveedor
    private void limpiarCampos() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtFechaNacimiento.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtEmpresa.setText("");

        txtIdentificacion.setEditable(true);
        tablaProveedores.clearSelection();
        txtIdentificacion.requestFocus();
    }

    // Muestra los errores de validacion
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}