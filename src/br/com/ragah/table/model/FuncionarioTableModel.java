package br.com.ragah.table.model;

import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.FuncionarioDAO;
import br.com.ragah.domain.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class FuncionarioTableModel extends AbstractTableModel {

    private static final int COL_MATRICULA = 0;
    private static final int COL_NOME = 1;

    private static final int COLUMN_COUNT = 2;

    private List<Funcionario> dados;

    public FuncionarioTableModel() {
        dados = new ArrayList<>();
        carregarDoBD();
    }

    public List<Funcionario> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return FuncionarioTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario obj = dados.get(rowIndex);
        if (columnIndex == COL_MATRICULA) {
            return obj.getMatricula();
        } else if (columnIndex == COL_NOME) {
            return obj.getNome();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_MATRICULA:
                coluna = "Matrícula";
                break;
            case COL_NOME:
                coluna = "Nome";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_MATRICULA) {
            return Integer.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
        }
        return null;
    }

    public Funcionario get(int row) {
        return dados.get(row);
    }

    public void atualizarDoBD() {
        dados.clear();
        carregarDoBD();
    }

    private void carregarDoBD() {
        FuncionarioDAO funcionarioDAO = DAOFactory.getDefaultDAOFactory().getFuncionarioDAO();
        dados = funcionarioDAO.listarTodos();
    }

}
