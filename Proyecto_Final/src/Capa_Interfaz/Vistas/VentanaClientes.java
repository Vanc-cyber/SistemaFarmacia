package Capa_Interfaz.Vistas;

import Capa_Entidades.Cliente;
import Capa_LogicaNegocio.LogicaCliente;

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

public class VentanaClientes extends JPanel {

    // Colores utilizados en la interfaz
    private final Color FONDO = new Color(242, 245, 249);
    private final Color TEXTO = new Color(40, 52, 70);
    private final Color AZUL_ACENTO = new Color(45, 145, 190);
    private final Color AZUL_OSCURO = new Color(23, 37, 58);

    // Campos para ingresar los datos del cliente
    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JTextField txtApellido1;
    private JTextField txtApellido2;
    private JTextField txtFechaNacimiento;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtPuntos;

    // Componentes para mostrar los clientes registrados
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    // Comunicación con la capa lógica
    private final LogicaCliente logicaCliente;

    // Constructor por defecto
    public VentanaClientes() {
        this(new LogicaCliente());
    }

    // Constructor que recibe la lógica compartida
    public VentanaClientes(LogicaCliente logicaCliente) {

        if (logicaCliente == null) {
            throw new IllegalArgumentException(
                    "LogicaCliente es obligatoria."
            );
        }

        this.logicaCliente = logicaCliente;

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

        JLabel titulo = new JLabel("Clientes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 27));
        titulo.setForeground(TEXTO);

        JLabel descripcion = new JLabel("Administracion de clientes");
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

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 237)
                        ),
                        BorderFactory.createEmptyBorder(20, 25, 20, 25)
                )
        );

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
        txtPuntos = new JTextField();

        campos.add(crearCampo("Identificacion", txtIdentificacion));
        campos.add(crearCampo("Nombre", txtNombre));
        campos.add(crearCampo("Primer apellido", txtApellido1));
        campos.add(crearCampo("Segundo apellido", txtApellido2));
        campos.add(crearCampo(
                "Fecha nacimiento (dd/MM/yyyy)",
                txtFechaNacimiento
        ));
        campos.add(crearCampo("Direccion", txtDireccion));
        campos.add(crearCampo("Correo electronico", txtCorreo));
        campos.add(crearCampo("Telefono", txtTelefono));
        campos.add(crearCampo("Puntos", txtPuntos));

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

        JPanel botones =
                new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

        botones.setBackground(Color.WHITE);

        JButton btnRegistrar = crearBoton("Registrar");
        JButton btnBuscar = crearBoton("Buscar");
        JButton btnModificar = crearBoton("Modificar");
        JButton btnEliminar = crearBoton("Eliminar");
        JButton btnLimpiar = crearBoton("Limpiar");

        btnRegistrar.addActionListener(e -> registrarCliente());
        btnBuscar.addActionListener(e -> buscarCliente());
        btnModificar.addActionListener(e -> modificarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
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
        boton.setBorder(
                BorderFactory.createEmptyBorder(9, 18, 9, 18)
        );
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        return boton;
    }

    // Crea la tabla de clientes
    private JScrollPane crearTabla() {

        String[] columnas = {
            "Identificacion",
            "Nombre",
            "Apellido 1",
            "Apellido 2",
            "Nacimiento",
            "Telefono",
            "Correo",
            "Puntos"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaClientes = new JTable(modeloTabla);

        tablaClientes.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        tablaClientes.setRowHeight(28);

        tablaClientes.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        tablaClientes.getTableHeader().setBackground(AZUL_OSCURO);
        tablaClientes.getTableHeader().setForeground(Color.WHITE);

        // Al seleccionar una fila carga los datos del cliente
        tablaClientes.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                cargarClienteSeleccionado();
            }
        });

        JScrollPane scroll = new JScrollPane(tablaClientes);

        scroll.setPreferredSize(new Dimension(0, 220));

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 237)
                )
        );

        return scroll;
    }

    // Carga los datos del cliente seleccionado
    private void cargarClienteSeleccionado() {

        try {

            int fila = tablaClientes.getSelectedRow();

            if (fila == -1) {
                return;
            }

            String identificacion =
                    tablaClientes.getValueAt(fila, 0).toString();

            Cliente cliente =
                    logicaCliente.buscarCliente(identificacion);

            if (cliente != null) {

                mostrarCliente(cliente);
                txtIdentificacion.setEditable(false);

                JOptionPane.showMessageDialog(
                        this,
                        "Puede modificar los datos del cliente.\n"
                        + "Para guardar los cambios debe presionar "
                        + "el boton Modificar.",
                        "Modificar cliente",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {

            mostrarError(e.getMessage());
        }
    }

    // Registra un nuevo cliente
    private void registrarCliente() {

        try {

            if (!txtIdentificacion.isEditable()) {

                throw new IllegalArgumentException(
                        "Presione Limpiar antes de registrar "
                        + "un nuevo cliente."
                );
            }

            Cliente cliente = crearCliente();

            logicaCliente.registrarCliente(cliente);

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente registrado correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } catch (IllegalArgumentException e) {

            mostrarError(e.getMessage());
        }
    }

    // Busca un cliente solicitando su identificación
    private void buscarCliente() {

        try {

            String identificacion =
                    JOptionPane.showInputDialog(
                            this,
                            "Ingrese la identificación del cliente:",
                            "Buscar cliente",
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (identificacion == null) {
                return;
            }

            identificacion = identificacion.trim();

            if (identificacion.isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe ingresar la identificación del cliente."
                );
            }

            if (!identificacion.matches("\\d{9}")) {

                throw new IllegalArgumentException(
                        "La identificación debe contener "
                        + "exactamente 9 dígitos."
                );
            }

            Cliente cliente =
                    logicaCliente.buscarCliente(identificacion);

            if (cliente == null) {

                throw new IllegalArgumentException(
                        "No existe un cliente registrado "
                        + "con esa identificación."
                );
            }

            mostrarCliente(cliente);

            txtIdentificacion.setEditable(false);

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente encontrado.\n"
                    + "Puede modificar los datos y luego presionar "
                    + "el boton Modificar para guardar los cambios.",
                    "Cliente encontrado",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            mostrarError(e.getMessage());
        }
    }

    // Modifica los datos del cliente seleccionado
    private void modificarCliente() {

        try {

            if (txtIdentificacion.isEditable()) {

                throw new IllegalArgumentException(
                        "Primero debe buscar o seleccionar "
                        + "el cliente que desea modificar."
                );
            }

            Cliente cliente = crearCliente();

            boolean modificado =
                    logicaCliente.modificarCliente(cliente);

            if (modificado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente modificado correctamente."
                );

                actualizarTabla();
                limpiarCampos();

            } else {

                mostrarError(
                        "No se pudo modificar el cliente."
                );
            }

        } catch (IllegalArgumentException e) {

            mostrarError(e.getMessage());
        }
    }

    // Solicita la identificación y confirma antes de eliminar
    private void eliminarCliente() {

        try {

            String identificacion =
                    JOptionPane.showInputDialog(
                            this,
                            "Ingrese la identificación del cliente "
                            + "que desea eliminar:",
                            "Eliminar cliente",
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (identificacion == null) {
                return;
            }

            identificacion = identificacion.trim();

            if (identificacion.isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe ingresar la identificación del cliente."
                );
            }

            if (!identificacion.matches("\\d{9}")) {

                throw new IllegalArgumentException(
                        "La identificación debe contener "
                        + "exactamente 9 dígitos."
                );
            }

            Cliente cliente =
                    logicaCliente.buscarCliente(identificacion);

            if (cliente == null) {

                throw new IllegalArgumentException(
                        "No existe un cliente registrado "
                        + "con esa identificación."
                );
            }

            SimpleDateFormat formato =
                    new SimpleDateFormat("dd/MM/yyyy");

            String fecha = "";

            if (cliente.getFechaNacimiento() != null) {

                fecha = formato.format(
                        cliente.getFechaNacimiento()
                );
            }

            String informacion =
                    "¿Está seguro de eliminar este cliente?\n\n"
                    + "Identificación: "
                    + cliente.getIdentificacion() + "\n"
                    + "Nombre: "
                    + cliente.getNombre() + "\n"
                    + "Primer apellido: "
                    + cliente.getApellido1() + "\n"
                    + "Segundo apellido: "
                    + cliente.getApellido2() + "\n"
                    + "Fecha de nacimiento: "
                    + fecha + "\n"
                    + "Dirección: "
                    + cliente.getDireccion() + "\n"
                    + "Correo: "
                    + cliente.getCorreoElectronico() + "\n"
                    + "Teléfono: "
                    + cliente.getTelefono() + "\n"
                    + "Puntos: "
                    + cliente.getPuntos();

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            informacion,
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (respuesta == JOptionPane.YES_OPTION) {

                boolean eliminado =
                        logicaCliente.eliminarCliente(
                                identificacion
                        );

                if (eliminado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cliente eliminado correctamente."
                    );

                    actualizarTabla();
                    limpiarCampos();

                } else {

                    mostrarError(
                            "No se pudo eliminar el cliente."
                    );
                }
            }

        } catch (IllegalArgumentException e) {

            mostrarError(e.getMessage());
        }
    }

    // Obtiene los datos del formulario y crea el cliente
    private Cliente crearCliente() {

        String textoPuntos =
                txtPuntos.getText().trim();

        if (textoPuntos.isEmpty()) {

            throw new IllegalArgumentException(
                    "Los puntos son obligatorios."
            );
        }

        int puntos;

        try {

            puntos = Integer.parseInt(textoPuntos);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "Los puntos deben ser un numero entero."
            );
        }

        String textoFecha =
                txtFechaNacimiento.getText().trim();

        if (textoFecha.isEmpty()) {

            throw new IllegalArgumentException(
                    "La fecha de nacimiento es obligatoria."
            );
        }

        // Valida el formato dd/MM/yyyy
        if (!textoFecha.matches("\\d{2}/\\d{2}/\\d{4}")) {

            throw new IllegalArgumentException(
                    "La fecha debe tener el formato dd/MM/yyyy."
            );
        }

        SimpleDateFormat formato =
                new SimpleDateFormat("dd/MM/yyyy");

        // Evita fechas inexistentes
        formato.setLenient(false);

        Date fechaNacimiento;

        try {

            fechaNacimiento =
                    formato.parse(textoFecha);

        } catch (ParseException e) {

            throw new IllegalArgumentException(
                    "La fecha ingresada no es valida."
            );
        }

        // Evita fechas futuras
        if (fechaNacimiento.after(new Date())) {

            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede ser futura."
            );
        }

        Cliente cliente = new Cliente();

        cliente.setIdentificacion(
                txtIdentificacion.getText().trim()
        );

        cliente.setNombre(
                txtNombre.getText().trim()
        );

        cliente.setApellido1(
                txtApellido1.getText().trim()
        );

        cliente.setApellido2(
                txtApellido2.getText().trim()
        );

        cliente.setFechaNacimiento(fechaNacimiento);

        cliente.setDireccion(
                txtDireccion.getText().trim()
        );

        cliente.setCorreoElectronico(
                txtCorreo.getText().trim()
        );

        cliente.setTelefono(
                txtTelefono.getText().trim()
        );

        cliente.setPuntos(puntos);

        return cliente;
    }

    // Muestra los datos de un cliente en el formulario
    private void mostrarCliente(Cliente cliente) {

        SimpleDateFormat formato =
                new SimpleDateFormat("dd/MM/yyyy");

        txtIdentificacion.setText(
                cliente.getIdentificacion()
        );

        txtNombre.setText(
                cliente.getNombre()
        );

        txtApellido1.setText(
                cliente.getApellido1()
        );

        txtApellido2.setText(
                cliente.getApellido2()
        );

        if (cliente.getFechaNacimiento() != null) {

            txtFechaNacimiento.setText(
                    formato.format(
                            cliente.getFechaNacimiento()
                    )
            );

        } else {

            txtFechaNacimiento.setText("");
        }

        txtDireccion.setText(
                cliente.getDireccion()
        );

        txtCorreo.setText(
                cliente.getCorreoElectronico()
        );

        txtTelefono.setText(
                cliente.getTelefono()
        );

        txtPuntos.setText(
                String.valueOf(
                        cliente.getPuntos()
                )
        );
    }

    // Actualiza la tabla con los clientes registrados
    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        SimpleDateFormat formato =
                new SimpleDateFormat("dd/MM/yyyy");

        for (Cliente cliente
                : logicaCliente.listarClientes()) {

            String fecha = "";

            if (cliente.getFechaNacimiento() != null) {

                fecha = formato.format(
                        cliente.getFechaNacimiento()
                );
            }

            modeloTabla.addRow(
                    new Object[]{
                        cliente.getIdentificacion(),
                        cliente.getNombre(),
                        cliente.getApellido1(),
                        cliente.getApellido2(),
                        fecha,
                        cliente.getTelefono(),
                        cliente.getCorreoElectronico(),
                        cliente.getPuntos()
                    }
            );
        }
    }

    // Limpia el formulario para ingresar un nuevo cliente
    private void limpiarCampos() {

        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtFechaNacimiento.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtPuntos.setText("");

        txtIdentificacion.setEditable(true);

        tablaClientes.clearSelection();

        txtIdentificacion.requestFocus();
    }

    // Muestra mensajes de error
    private void mostrarError(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}