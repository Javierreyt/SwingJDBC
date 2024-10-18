package org.example.gui;

import org.example.dao.UserDAO;
import org.example.model.Session;
import org.example.model.User;

import javax.swing.*;

import static org.example.dao.JdbcUtils.getConnection;

/**
 * Clase para la ventana de inicio de sesión.
 */
public class Login extends JFrame {
    private JPanel panel1;
    private JPasswordField txtPassword;
    private JTextField txtUser;
    private JButton buttonConfirm;

    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     */
    public Login() {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        buttonConfirm.addActionListener(e -> {
                String username = txtUser.getText();
                String password = txtPassword.getText();
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this,"Falta algún campo por completar","Error al iniciar sesión",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = new UserDAO(getConnection()).validateUser(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(Login.this, "Bienvenido " + user.getUsername(), "Información del Usuario", JOptionPane.INFORMATION_MESSAGE);
                    Session.user = user;
                    dispose();
                    new UserView().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectas","Error al iniciar sesión", JOptionPane.INFORMATION_MESSAGE);
                }
        });
    }
}