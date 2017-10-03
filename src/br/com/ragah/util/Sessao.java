
package br.com.ragah.util;

import br.com.ragah.domain.Empresa;
import br.com.ragah.domain.Usuario;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author douglas
 */
public class Sessao {

    private static Usuario usuario;
    private static Empresa empresa;

    public static Usuario getUsuario() {
        return Sessao.usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Sessao.usuario = usuario;
    }

    public static Empresa getEmpresa() {
        return Sessao.empresa;
    }

    public static void setEmpresa(Empresa empresa) {
        Sessao.empresa = empresa;
    }

    public static String acessoToString() {
        return usuario.getUltimoAcesso().format(
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm"));
    }

}
