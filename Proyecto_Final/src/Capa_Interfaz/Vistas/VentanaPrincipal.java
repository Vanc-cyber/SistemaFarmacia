/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Interfaz.Vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame {

    // Colores principales
    private final Color AZUL_OSCURO = new Color(23, 37, 58);
    private final Color AZUL_SECUNDARIO = new Color(34, 52, 78);
    private final Color AZUL_ACENTO = new Color(45, 145, 190);
    private final Color FONDO = new Color(242, 245, 249);
    private final Color TEXTO = new Color(40, 52, 70);

    // Contenedor que permite cambiar entre secciones
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContenido = new JPanel(cardLayout);

    public VentanaPrincipal() {

        setTitle("Sistema de Gestion de Farmacia");
        setSize(1200, 750);
        setMinimumSize(new Dimension(950, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Construir los componentes
        crearMenuLateral();
        crearEncabezado();
        crearContenido();

        // Mostrar inicio al abrir
        cardLayout.show(panelContenido, "Inicio");
    }

    // =========================================
    // MENU LATERAL
    // =========================================
    private void crearMenuLateral() {

        JPanel menu = new JPanel();
        menu.setBackground(AZUL_OSCURO);
        menu.setPreferredSize(new Dimension(235, 0));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("  FARMACIA");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 23));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 15, 8, 0));

        JLabel subtitulo = new JLabel("  Sistema de gestion");
        subtitulo.setForeground(new Color(170, 190, 210));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 15, 35, 0));

        menu.add(titulo);
        menu.add(subtitulo);

        agregarBotonMenu(menu, "Inicio", "Inicio");
        agregarBotonMenu(menu, "Medicamentos", "Medicamentos");
        agregarBotonMenu(menu, "Categorias", "Categorias");
        agregarBotonMenu(menu, "Clientes", "Clientes");
        agregarBotonMenu(menu, "Proveedores", "Proveedores");
        agregarBotonMenu(menu, "Ventas", "Ventas");

        menu.add(Box.createVerticalGlue());

        JLabel pie = new JLabel("  FARMACIA | 2026");
        pie.setForeground(new Color(145, 165, 185));
        pie.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        pie.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 0));

        menu.add(pie);

        add(menu, BorderLayout.WEST);
    }

    private void agregarBotonMenu(
            JPanel menu, String texto, String seccion) {

        JButton boton = new JButton(texto);

        boton.setMaximumSize(new Dimension(220, 48));
        boton.setPreferredSize(new Dimension(220, 48));
        boton.setHorizontalAlignment(SwingConstants.LEFT);

        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(AZUL_OSCURO);

        boton.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setAlignmentX(LEFT_ALIGNMENT);

        boton.addActionListener(e
                -> cardLayout.show(panelContenido, seccion));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(AZUL_SECUNDARIO);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(AZUL_OSCURO);
            }
        });

        menu.add(boton);
    }

    // =========================================
    // ENCABEZADO
    // =========================================
    private void crearEncabezado() {

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(Color.WHITE);

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(18, 30, 18, 30));

        JLabel titulo = new JLabel("Sistema de Gestion de Farmacia");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 19));
        titulo.setForeground(TEXTO);

        JLabel usuario = new JLabel("Administrador");
        usuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        usuario.setForeground(AZUL_ACENTO);

        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(usuario, BorderLayout.EAST);

        JPanel superior = new JPanel(new BorderLayout());
        superior.add(encabezado, BorderLayout.CENTER);
        superior.setBackground(Color.WHITE);

        add(superior, BorderLayout.NORTH);
    }

    // =========================================
    // CONTENIDO PRINCIPAL
    // =========================================
    private void crearContenido() {

        panelContenido.setBackground(FONDO);

        panelContenido.add(crearPanelInicio(), "Inicio");

        panelContenido.add(
                crearPanelVacio("Medicamentos",
                        "Administracion del inventario de medicamentos."),
                "Medicamentos");

        panelContenido.add(
                crearPanelVacio("Categorias",
                        "Administracion de categorias."),
                "Categorias");

        panelContenido.add(
                new VentanaClientes(),
                "Clientes");
        panelContenido.add(
                crearPanelVacio("Proveedores",
                        "Administracion de proveedores."),
                "Proveedores");

        panelContenido.add(
                crearPanelVacio("Ventas",
                        "Registro y consulta de ventas."),
                "Ventas");

        add(panelContenido, BorderLayout.CENTER);
    }

    // =========================================
    // PANEL DE INICIO
    // =========================================
    private JPanel crearPanelInicio() {

        JPanel panel = new JPanel(new BorderLayout(15, 20));
        panel.setBackground(FONDO);
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 35, 30, 35));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setBackground(FONDO);

        JLabel titulo = new JLabel("Panel principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 27));
        titulo.setForeground(TEXTO);

        JLabel descripcion = new JLabel(
                "Bienvenido al sistema de gestion de farmacia.");

        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descripcion.setForeground(Color.GRAY);

        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(8));
        encabezado.add(descripcion);

        JPanel estadisticas = new JPanel(new GridLayout(1, 3, 18, 0));
        estadisticas.setBackground(FONDO);

        estadisticas.add(
                crearTarjeta("Medicamentos", "Inventario", AZUL_ACENTO));

        estadisticas.add(
                crearTarjeta("Clientes", "Registrados", new Color(45, 155, 120)));

        estadisticas.add(
                crearTarjeta("Ventas", "Registradas", new Color(130, 100, 190)));

        JPanel informacion = new JPanel(new BorderLayout());
        informacion.setBackground(Color.WHITE);
        informacion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 237)),
                BorderFactory.createEmptyBorder(22, 22, 22, 22)));

        JLabel mensaje = new JLabel(
                "<html><h2>Bienvenido</h2>"
                + "<p>Seleccione una opcion del menu lateral "
                + "para comenzar a administrar la farmacia.</p>"
                + "</html>");

        mensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        informacion.add(mensaje, BorderLayout.CENTER);

        JPanel centro = new JPanel(new BorderLayout(0, 25));
        centro.setBackground(FONDO);
        centro.add(encabezado, BorderLayout.NORTH);
        centro.add(estadisticas, BorderLayout.CENTER);

        panel.add(centro, BorderLayout.NORTH);
        panel.add(informacion, BorderLayout.CENTER);

        return panel;
    }

    // =========================================
    // TARJETAS DE ESTADISTICAS
    // =========================================
    private JPanel crearTarjeta(
            String titulo, String descripcion, Color color) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, color),
                BorderFactory.createEmptyBorder(20, 20, 20, 15)));

        JLabel nombre = new JLabel(titulo);
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nombre.setForeground(TEXTO);

        JLabel detalle = new JLabel(descripcion);
        detalle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        detalle.setForeground(Color.GRAY);

        tarjeta.add(nombre);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(detalle);

        return tarjeta;
    }

    // =========================================
    // PANELES TEMPORALES
    // =========================================
    private JPanel crearPanelVacio(
            String titulo, String descripcion) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
        panel.setBorder(
                BorderFactory.createEmptyBorder(35, 35, 35, 35));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBackground(Color.WHITE);

        contenido.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel nombre = new JLabel(titulo);
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        nombre.setForeground(TEXTO);

        JLabel texto = new JLabel(descripcion);
        texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        texto.setForeground(Color.GRAY);

        JLabel estado = new JLabel("Modulo en desarrollo");
        estado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        estado.setForeground(AZUL_ACENTO);

        contenido.add(nombre);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(texto);
        contenido.add(Box.createVerticalStrut(25));
        contenido.add(estado);

        panel.add(contenido, BorderLayout.CENTER);

        return panel;
    }

    // =========================================
    // EJECUCION INDEPENDIENTE
    // =========================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
