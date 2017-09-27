package br.com.ragah.table.model;

import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.FuncaoDAO;
import br.com.ragah.domain.Funcao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class FuncaoTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_NOME = 1;

    private static final int COLUMN_COUNT = 2;

    private List<Funcao> dados;

    public FuncaoTableModel(List<Funcao> dados) {
        this.dados = dados;
    }

    public FuncaoTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
    }

    public List<Funcao> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return FuncaoTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcao obj = dados.get(rowIndex);
        if (columnIndex == COL_CODIGO) {
            return String.format("%05d", obj.getId());
        } else if (columnIndex == COL_NOME) {
            return obj.getDescricao();
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
            case COL_NOME:
                coluna = "Descrição";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CODIGO) {
            return int.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
        }
        return null;
    }

    public Funcao get(int row) {
        return dados.get(row);
    }

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        FuncaoDAO adao = DAOFactory.getDefaultDAOFactory().getFuncaoDAO();
        dados = adao.listarTodos();
    }

}
