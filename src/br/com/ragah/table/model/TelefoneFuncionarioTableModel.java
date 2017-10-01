package br.com.ragah.table.model;

import br.com.ragah.domain.TelefoneFuncionario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Douglas Gusson
 */
public class TelefoneFuncionarioTableModel extends AbstractTableModel {

    private static final int COL_NUM_TELEFONE = 0;
    private static final int COL_DESCRICAO = 1;

    private static final int COLUMN_COUNT = 2;

    private List<TelefoneFuncionario> dados;

    public TelefoneFuncionarioTableModel(List<TelefoneFuncionario> dados) {
        this.dados = dados;
    }
    
    public List<TelefoneFuncionario> getColecao() {
        return dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return TelefoneFuncionarioTableModel.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TelefoneFuncionario obj = dados.get(rowIndex);
        if (columnIndex == COL_NUM_TELEFONE) {
            return obj.getNumTelefone();
        } else if (columnIndex == COL_DESCRICAO) {
            return obj.getDescricao();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NUM_TELEFONE:
                coluna = "Número";
                break;
            case COL_DESCRICAO:
                coluna = "Descrição";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_NUM_TELEFONE) {
            return String.class;
        } else if (columnIndex == COL_DESCRICAO) {
            return String.class;
        }
        return null;
    }

    public TelefoneFuncionario get(int row) {
        return dados.get(row);
    }

}
