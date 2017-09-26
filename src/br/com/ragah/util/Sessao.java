
package br.com.ragah.util;

import br.com.ragah.domain.Usuario;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author douglas
 */
public class Sessao {

    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }

    public static String acessoToString() {
        return usuario.getUltimoAcesso().format(
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm"));
    }
    
}
