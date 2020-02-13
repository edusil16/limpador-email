import sun.plugin2.message.Message;

import java.io.IOException;

public class Inicializar {
    public static void main(String[] args) throws IOException {
        // os argumentos nao servem para nada, ignoro
        String pastaLimpeza = "teste";
        String userName = "Email@teste.com.br";
        String senha ="senha***";
        VisualizarMensagens visualizarMensagens = new VisualizarMensagens();
        MailExcluiMensagens mailExcluiMensagens = new MailExcluiMensagens();


        visualizarMensagens.listaMensagens(userName, senha);

        //mailExcluiMensagens.excluiMensagens(userName, senha, pastaLimpeza, 120);

    }

}
