package org.example.gui;

import org.example.model.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Vista de detalles para mostrar la información de una película.
 */
public class DetailsView extends JFrame {
    private JPanel panel1;
    private JList list1;
    private JButton backButton;
    private JLabel idText;
    private JLabel titleText;
    private JLabel genreText;
    private JLabel yearText;
    private JLabel directorText;
    private JLabel statusText;
    private JLabel mediumText;
    private JLabel descriptionText;

    /**
     * Constructor que inicializa la vista de detalles.
     */
    public DetailsView() {
        setContentPane(panel1);
        setTitle("Detalles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        idText.setText(String.valueOf(Session.movie.getId()));
        titleText.setText(Session.movie.getTitle());
        genreText.setText(Session.movie.getGenre());
        descriptionText.setText("<html><body style='width: 300px'>"+Session.movie.getDescription()+"</body></html>");
        directorText.setText(Session.movie.getDirector());
        yearText.setText(Session.movie.getYear().toString());
        titleText.setText(Session.movie.getTitle());
        statusText.setText(Session.copia.getEstado());
        mediumText.setText(Session.copia.getSoporte());

        backButton.addActionListener((ActionEvent e) -> {
            Session.copia=null;
            Session.movie=null;
            dispose();
            new UserView().setVisible(true);
        });
    }
}