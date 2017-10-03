
package br.com.ragah.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Gusson
 */
public class GenericException extends RuntimeException {

    /**
     * Construtor default
     */
    public GenericException() {
    }

    /**
     * Construtor
     *
     * @param message
     */
    public GenericException(String message) {
        super(message);
        alert(message);
    }

    private void alert(String message) {
        JOptionPane.showMessageDialog(null, message,
                "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Construtor
     *
     * @param cause
     */
    public GenericException(Throwable cause) {
        super(cause);
        alert(cause);
    }

    private void alert(Throwable cause) {
        JOptionPane.showMessageDialog(null, cause,
                "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Construtor
     *
     * @param message
     * @param cause
     */
    public GenericException(String message, Throwable cause) {
        super(message, cause);
        alert(message, cause);
    }

    private void alert(String message, Throwable cause) {
        JOptionPane.showMessageDialog(null,
                "\n" + message
                + "\n\nDETALHES:\n" + cause,
                "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
