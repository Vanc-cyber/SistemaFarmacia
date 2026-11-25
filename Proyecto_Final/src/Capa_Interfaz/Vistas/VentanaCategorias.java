package Capa_Interfaz.Vistas;

import Capa_Entidades.Categoria;
import Capa_LogicaNegocio.LogicaCategoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaCategorias extends JPanel {

    // Colores utilizados en la interfaz
    private final Color FONDO = new Color(242, 245, 249);
    private final Color TEXTO = new Color(40, 52, 70);
    private final Color AZUL_ACENTO = new Color(45, 145, 190);
    private final Color AZUL_OSCURO = new Color(23, 37, 58);

    // Campos para ingresar los datos de la categoría
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;

    // Componentes para mostrar las categorías registradas
    private JTable tablaCategorias;
    private DefaultTableModel modeloTabla;

    // Comunicación con la capa lógica
    private final LogicaCategoria logicaCategoria;

    // Usa su propia lógica (solo para pruebas aisladas)
    public VentanaCategorias() {
        this(new LogicaCategoria());
    }

    // Recibe la lógica compartida con las demás ventanas
    public VentanaCategorias(LogicaCategoria logicaCategoria) {

        this.logicaCategoria = logicaCategoria;

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

        JLabel titulo = new JLabel("Categorias");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 27));
        titulo.setForeground(TEXTO);

        JLabel descripcion = new JLabel("Administracion de categorias");
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
                        BorderFactory.createLineBorder(new Color(225, 230, 237)),
                        BorderFactory.createEmptyBorder(20, 25, 20, 25)
                )
        );

        JPanel campos = new JPanel(new GridLayout(1, 3, 15, 12));
        campos.setBackground(Color.WHITE);

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtDescripcion = new JTextField();

        campos.add(crearCampo("Identificador", txtId));
        campos.add(crearCampo("Nombre", txtNombre));
        campos.add(crearCampo("Descripcion", txtDescripcion));

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

        btnRegistrar.addActionListener(e -> registrarCategoria());
        btnBuscar.addActionListener(e -> buscarCategoria());
        btnModificar.addActionListener(e -> modificarCategoria());
        btnEliminar.addActionListener(e -> eliminarCategoria());
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

    // Crea la tabla de categorías
    private JScrollPane crearTabla() {

        String[] columnas = {"Identificador", "Nombre", "Descripcion"};

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaCategorias = new JTable(modeloTabla);

        tablaCategorias.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaCategorias.setRowHeight(28);

        tablaCategorias.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        tablaCategorias.getTableHeader().setBackground(AZUL_OSCURO);
        tablaCategorias.getTableHeader().setForeground(Color.WHITE);

        // Al seleccionar una fila carga los datos de la categoría
        tablaCategorias.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarCategoriaSeleccionada();
            }
        });

        JScrollPane scroll = new JScrollPane(tablaCategorias);

        scroll.setPreferredSize(new Dimension(0, 220));

        scroll.setBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 237))
        );

        return scroll;
    }

    // Convierte el texto del identificador a número entero.
    // Solo convierte el tipo de dato, las reglas las valida la capa lógica.
    private int leerId(String texto) {

        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "El identificador debe ser un numero entero."
            );
        }
    }

    // Carga los datos de la categoría seleccionada en la tabla
    private void cargarCategoriaSeleccionada() {

        try {

            int fila = tablaCategorias.getSelectedRow();

            if (fila == -1) {
                return;
            }

            int id = Integer.parseInt(
                    tablaCategorias.getValueAt(fila, 0).toString()
            );

            Categoria categoria = logicaCategoria.buscarCategoria(id);

            if (categoria != null) {

                mostrarCategoria(categoria);
                txtId.setEditable(false);

                JOptionPane.showMessageDialog(
                        this,
                        "Puede modificar los datos de la categoria.\n"
                        + "Para guardar los cambios debe presionar "
                        + "el boton Modificar.",
                        "Modificar categoria",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Registra una nueva categoría
    private void registrarCategoria() {

        try {

            if (!txtId.isEditable()) {
                throw new IllegalArgumentException(
                        "Presione Limpiar antes de registrar una nueva categoria."
                );
            }

            Categoria categoria = crearCategoria();

            logicaCategoria.registrarCategoria(categoria);

            JOptionPane.showMessageDialog(
                    this,
                    "Categoria registrada correctamente."
            );

            actualizarTabla();
            limpiarCampos();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Busca una categoría solicitando su identificador
    private void buscarCategoria() {

        try {

            String texto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el identificador de la categoria:",
                    "Buscar categoria",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (texto == null) {
                return;
            }

            Categoria categoria =
                    logicaCategoria.buscarCategoria(leerId(texto));

            if (categoria == null) {
                throw new IllegalArgumentException(
                        "No existe una categoria registrada con ese identificador."
                );
            }

            mostrarCategoria(categoria);
            txtId.setEditable(false);

            JOptionPane.showMessageDialog(
                    this,
                    "Categoria encontrada.\n"
                    + "Puede modificar los datos y luego presionar "
                    + "el boton Modificar para guardar los cambios.",
                    "Categoria encontrada",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Modifica los datos de la categoría seleccionada
    private void modificarCategoria() {

        try {

            if (txtId.isEditable()) {
                throw new IllegalArgumentException(
                        "Primero debe buscar o seleccionar "
                        + "la categoria que desea modificar."
                );
            }

            Categoria categoria = crearCategoria();

            boolean modificada = logicaCategoria.modificarCategoria(categoria);

            if (modificada) {

                JOptionPane.showMessageDialog(
                        this,
                        "Categoria modificada correctamente."
                );

                actualizarTabla();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo modificar la categoria.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Solicita el identificador y confirma antes de eliminar
    private void eliminarCategoria() {

        try {

            String texto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el identificador de la categoria que desea eliminar:",
                    "Eliminar categoria",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (texto == null) {
                return;
            }

            int id = leerId(texto);

            Categoria categoria = logicaCategoria.buscarCategoria(id);

            if (categoria == null) {
                throw new IllegalArgumentException(
                        "No existe una categoria registrada con ese identificador."
                );
            }

            String informacion =
                    "¿Está seguro de eliminar esta categoría?\n\n"
                    + "Identificador: " + categoria.getIdCategoria() + "\n"
                    + "Nombre: " + categoria.getNombre() + "\n"
                    + "Descripción: " + categoria.getDescripcion();

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    informacion,
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                boolean eliminada = logicaCategoria.eliminarCategoria(id);

                if (eliminada) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Categoria eliminada correctamente."
                    );

                    actualizarTabla();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo eliminar la categoria.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Crea la categoría con los datos del formulario
    // (la capa lógica se encarga de validarlos)
    private Categoria crearCategoria() {

        Categoria categoria = new Categoria();

        categoria.setIdCategoria(leerId(txtId.getText()));
        categoria.setNombre(txtNombre.getText());
        categoria.setDescripcion(txtDescripcion.getText());

        return categoria;
    }

    // Muestra los datos de una categoría en el formulario
    private void mostrarCategoria(Categoria categoria) {

        txtId.setText(String.valueOf(categoria.getIdCategoria()));
        txtNombre.setText(categoria.getNombre());
        txtDescripcion.setText(categoria.getDescripcion());
    }

    // Actualiza la tabla con las categorías registradas
    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Categoria categoria : logicaCategoria.listarCategorias()) {

            modeloTabla.addRow(
                    new Object[]{
                        categoria.getIdCategoria(),
                        categoria.getNombre(),
                        categoria.getDescripcion()
                    }
            );
        }
    }

    // Limpia el formulario para ingresar una nueva categoría
    private void limpiarCampos() {

        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");

        txtId.setEditable(true);
        tablaCategorias.clearSelection();
        txtId.requestFocus();
    }
}