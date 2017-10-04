package br.com.ragah.view;

import br.com.ragah.domain.Empresa;
import br.com.ragah.table.cellrenderer.EmpresaCellRenderer;
import br.com.ragah.table.model.EmpresaTableModel;
import br.com.ragah.util.Sessao;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author douglas
 */
public class FrmBuscaEmpresa extends javax.swing.JDialog {

    public FrmBuscaEmpresa(Window parent) {
        super(parent, DEFAULT_MODALITY_TYPE);
        initComponents();
        initialize();
    }

    public void atualizarTabela() {
        tbEmpresas.setModel(new EmpresaTableModel());
        tbEmpresas.setDefaultRenderer(Object.class, new EmpresaCellRenderer());
        ((AbstractTableModel) tbEmpresas.getModel()).fireTableDataChanged();
    }

    private void initialize() {

        atualizarTabela();
        eventoFechar();

        // Se teclarmos ESC nesta janela, ela irá se fechar:  
        this.getRootPane().registerKeyboardAction((ActionEvent e) -> {
            FrmBuscaEmpresa.this.retornarEmpresa();
            FrmBuscaEmpresa.this.dispose();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

    }

    private void eventoFechar() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                retornarEmpresa();
            }
        });
    }

    private Boolean retornarEmpresa() {

        int row = this.tbEmpresas.getSelectedRow();

        if (row != (-1)) {

            EmpresaTableModel empresaTableModel = new EmpresaTableModel();
            Empresa e = empresaTableModel.get(row);

            Sessao.setEmpresa(e);
            this.dispose();

            return true;
        }

        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btOk = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEmpresas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busca de Empresas");

        btOk.setMnemonic('O');
        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        tbEmpresas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbEmpresas.setToolTipText("");
        tbEmpresas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEmpresasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbEmpresas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btOk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btOk)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        if (!retornarEmpresa()) {
            this.dispose();
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void tbEmpresasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmpresasMouseClicked
        if (evt.getClickCount() == 2) {
            retornarEmpresa();
        }
    }//GEN-LAST:event_tbEmpresasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOk;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbEmpresas;
    // End of variables declaration//GEN-END:variables
}