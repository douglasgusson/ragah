package br.com.ragah.main;

import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.UsuarioDAO;
import br.com.ragah.util.Info;
import br.com.ragah.view.FrmLogin;
import br.com.ragah.view.FrmRegistroUsuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author Douglas Gusson
 */
public class Main extends JWindow {

    private final int duration;

    public Main(int d) {
        duration = d;
    }

    public void showSplash() {
        desenharSplash();
        try {
            Thread.sleep(duration);
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();
                    if (connection.isValid(0)) {
                        UsuarioDAO udao = DAOFactory.getDefaultDAOFactory().getUsuarioDAO();
                        if (udao.countUsuarios() > 0) {
                            new FrmLogin().setVisible(true);
                        } else {
                            new FrmRegistroUsuario().setVisible(true);
                        }                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            setVisible(false);
        } catch (Exception e) {
        }
    }

    private void desenharSplash() {

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        int comprimento = 400;
        int altura = 280;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - comprimento) / 2;
        int y = (screen.height - altura) / 2;
        setBounds(x, y, comprimento, altura);

        JLabel lbTitulo = new JLabel(Info.getNameMoreVersion(), JLabel.CENTER);
        lbTitulo.setFont(new Font("Calibri", Font.BOLD, 16));

        JLabel load = new JLabel("Carregando módulos do sistema...", JLabel.CENTER);
        load.setFont(new Font("Calibri", Font.BOLD, 12));

        content.add(lbTitulo, BorderLayout.NORTH);

        Color borda = new Color(201, 201, 201);
        content.setBorder(BorderFactory.createLineBorder(borda, 12));

        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main(1500);
        m.showSplash();
    }

}