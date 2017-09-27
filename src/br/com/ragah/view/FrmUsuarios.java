package br.com.ragah.view;

import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.UsuarioDAO;
import br.com.ragah.domain.Usuario;
import br.com.ragah.table.cellrenderer.UsuarioCellRenderer;
import br.com.ragah.table.model.UsuarioTableModel;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class FrmUsuarios extends javax.swing.JDialog {

    private static FrmUsuarios INSTANCIA;

    public static FrmUsuarios getInstancia() {
        return INSTANCIA;
    }

    public FrmUsuarios(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        INSTANCIA = this;
        initialize();
    }

    private void initialize() {
        atualizarTabela();
    }

    public void atualizarTabela() {
        tbUsuarios.setModel(new UsuarioTableModel());
        tbUsuarios.setDefaultRenderer(Object.class, new UsuarioCellRenderer());
        ((UsuarioTableModel) tbUsuarios.getModel()).atualizarDoBD();
        ((AbstractTableModel) tbUsuarios.getModel()).fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuários");

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbUsuarios);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/novo_16x16.png"))); // NOI18N
        btNovo.setMnemonic('N');
        btNovo.setText("Novo");
        btNovo.setFocusable(false);
        btNovo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        jToolBar1.add(btNovo);

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/alterar_16x16.png"))); // NOI18N
        btAlterar.setMnemonic('A');
        btAlterar.setText("Alterar");
        btAlterar.setFocusable(false);
        btAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        jToolBar1.add(btAlterar);

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/excluir_16x16.png"))); // NOI18N
        btExcluir.setMnemonic('E');
        btExcluir.setText("Excluir");
        btExcluir.setFocusable(false);
        btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        jToolBar1.add(btExcluir);

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pedidogm/img/sair_16x16.png"))); // NOI18N
        btSair.setMnemonic('S');
        btSair.setText("Sair");
        btSair.setFocusable(false);
        btSair.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        jToolBar1.add(btSair);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        FrmRegistroUsuario registroUsuario = new FrmRegistroUsuario(this);
        registroUsuario.setVisible(true);
    }//GEN-LAST:event_btNovoActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        int indice = tbUsuarios.getSelectedRow();
        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {
            FrmRegistroUsuario frmRegistroUsuario
                    = new FrmRegistroUsuario(this, ((UsuarioTableModel) tbUsuarios.getModel()).getColecao(), indice);
            frmRegistroUsuario.setVisible(true);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        int indice = tbUsuarios.getSelectedRow();
        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        } else {
            Usuario u = ((UsuarioTableModel) tbUsuarios.getModel()).getColecao().get(indice);
            int i = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente excluir este usuario?\n"
                    + u.getId() + " - " + u.getNomeUsuario() + " (" + u.getNomeCompleto() + ")",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                UsuarioDAO usuarioDAO = DAOFactory.getDefaultDAOFactory().getUsuarioDAO();
                usuarioDAO.excluir(u);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbUsuarios;
    // End of variables declaration//GEN-END:variables
}