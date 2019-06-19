import sun.plugin2.message.Message;

import java.io.IOException;

public class Inicializar {
    public static void main(String[] args) throws IOException {
        // os argumentos nao servem para nada, ignoro
        String pastaLimpeza = "teste";
        String userName = "sistema.tjrj.excel@mprj.mp.br";
        String senha ="investigacao1";
        VisualizarMensagens visualizarMensagens = new VisualizarMensagens();
        MailExcluiMensagens mailExcluiMensagens = new MailExcluiMensagens();


        visualizarMensagens.listaMensagens(userName, senha);

        //mailExcluiMensagens.excluiMensagens(userName, senha, pastaLimpeza, 120);

    }

}
