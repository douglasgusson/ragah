package br.com.ragah.table.model;

import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.EmpresaDAO;
import br.com.ragah.domain.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class EmpresaTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_RAZAO_SOCIAL = 1;

    private static final int COLUMN_COUNT = 2;

    private List<Empresa> dados;

    public EmpresaTableModel(List<Empresa> dados) {
        this.dados = dados;
    }

    public EmpresaTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
    }

    public List<Empresa> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return EmpresaTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empresa obj = dados.get(rowIndex);
        if (columnIndex == COL_CODIGO) {
            return obj.getId();
        } else if (columnIndex == COL_RAZAO_SOCIAL) {
            return obj.getRazaoSocial();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_CODIGO:
                coluna = "Código";
                break;
            case COL_RAZAO_SOCIAL:
                coluna = "Razão Social";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CODIGO) {
            return Integer.class;
        } else if (columnIndex == COL_RAZAO_SOCIAL) {
            return String.class;
        }
        return null;
    }

    public Empresa get(int row) {
        return dados.get(row);
    }

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        EmpresaDAO empresaDAO = DAOFactory.getDefaultDAOFactory().getEmpresaDAO();
        dados = empresaDAO.listarTodas();
    }

}
