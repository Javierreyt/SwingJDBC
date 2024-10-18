package org.example.gui;

import org.example.dao.CopiaDAO;
import org.example.dao.JdbcUtils;
import org.example.model.Copia;
import org.example.model.CopiaMovieDTO;
import org.example.model.Movie;
import org.example.model.Session;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 * Vista de usuario que muestra una tabla con información de películas y copias.
 */
public class UserView extends JFrame {
    private JPanel panel1;
    private JTable table;
    private JButton logoutButton;
    private JButton exitButton;
    private DefaultTableModel model;

    /**
     * Constructor que inicializa la vista de usuario.
     */
    public UserView() {
        String[] cabecera = {"Id_Pelicula", "Titulo", "Genero", "Año", "Descripcion", "Director", "Estado", "Soporte"};
        model = new DefaultTableModel(cabecera, 0);
        table.setModel(model);
        List<CopiaMovieDTO> lista = new CopiaDAO(JdbcUtils.getConnection()).joinTableById(Session.user.getId());
        for (CopiaMovieDTO o1 : lista) {
            model.addRow(new Object[]{
                    o1.getMovie().getId(),
                    o1.getMovie().getTitle(),
                    o1.getMovie().getGenre(),
                    o1.getMovie().getYear(),
                    o1.getMovie().getDescription(),
                    o1.getMovie().getDirector(),
                    o1.getCopia().getEstado().substring(0,1).toUpperCase()+o1.getCopia().getEstado().substring(1),
                    o1.getCopia().getSoporte()
            });
        }

        table.getColumnModel().getColumn(4).setCellRenderer(new MultiLineCellRenderer());
        ajustarAnchoColumnas(table);

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) return;

            var id = Integer.valueOf(model.getValueAt(table.getSelectedRow(), 0).toString());
            var titulo = model.getValueAt(table.getSelectedRow(), 1).toString();
            var genero = model.getValueAt(table.getSelectedRow(), 2).toString();
            var año = Integer.valueOf(model.getValueAt(table.getSelectedRow(), 3).toString());
            var descripcion = model.getValueAt(table.getSelectedRow(), 4).toString();
            var director = model.getValueAt(table.getSelectedRow(), 5).toString();
            var estado = model.getValueAt(table.getSelectedRow(), 6).toString();
            //estado = estado.substring(0, 1).toUpperCase() + estado.substring(1);
            var soporte = model.getValueAt(table.getSelectedRow(), 7).toString();

            Session.movie = new Movie(id, titulo, genero, año, descripcion, director);
            Session.copia = new Copia(estado, soporte);
            dispose();
            new DetailsView().setVisible(true);
        });

        logoutButton.addActionListener((ActionEvent e) -> {
            Session.user = null;
            Session.movie = null;
            dispose();
            new Login().setVisible(true);
        });

        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    /**
     * Ajusta el ancho de las columnas de la tabla.
     *
     * @param table la tabla a ajustar
     */
    public void ajustarAnchoColumnas(JTable table) {
        for (int col = 0; col < table.getColumnCount(); col++) {
            TableColumn column = table.getColumnModel().getColumn(col);
            int width = 15;

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, col);
                Component comp = table.prepareRenderer(renderer, row, col);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            column.setPreferredWidth(width);
        }
    }

    /**
     * Renderer personalizado para mostrar la descripción en múltiples líneas.
     */
    class MultiLineCellRenderer extends JLabel implements TableCellRenderer {
        public MultiLineCellRenderer() {
            setVerticalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            int preferredHeight = getPreferredSize().height + 80;
            if (value != null) {
                setText("<html><body style='width: 200px; font-style: italic; font-size: 12px;'>" + value.toString() + "</body></html>");
                table.setRowHeight(row, preferredHeight);
            } else {
                setText("");
                table.setRowHeight(row, preferredHeight);
            }
            return this;
        }
    }
}