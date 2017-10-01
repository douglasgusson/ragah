package br.com.ragah.table.model;

import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.CidadeDAO;
import br.com.ragah.domain.Cidade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class CidadeTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_NOME = 1;
    private static final int COL_UF = 2;

    private static final int COLUMN_COUNT = 3;

    private List<Cidade> dados;

    public CidadeTableModel(List<Cidade> dados) {
        this.dados = dados;
    }

    public CidadeTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
    }

    public List<Cidade> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return CidadeTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade obj = dados.get(rowIndex);
        if (columnIndex == COL_CODIGO) {
            return obj.getId();
        } else if (columnIndex == COL_NOME) {
            return obj.getNome();
        } else if (columnIndex == COL_UF) {
            return obj.getUf().getSigla();
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
                coluna = "Nome";
                break;
            case COL_UF:
                coluna = "UF";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CODIGO) {
            return Long.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
        } else if (columnIndex == COL_UF) {
            return String.class;
        }
        return null;
    }

    public Cidade get(int row) {
        return dados.get(row);
    }

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        CidadeDAO cdao = DAOFactory.getDefaultDAOFactory().getCidadeDAO();
        dados = cdao.listar();
    }

}
