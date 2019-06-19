import javax.mail.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class VisualizarMensagens {
    public void listaMensagens(String userName, String senha) throws IOException {

        Properties props = new Properties();
        Store store;

        try {

            Session emailSession = Session.getDefaultInstance(props, null);

            store = emailSession.getStore("imaps");
            store.connect("outlook.office365.com", userName, senha);
            Folder pastaEmail = store.getFolder("teste");
            pastaEmail.open(Folder.READ_ONLY);

            Message[] mensagens = pastaEmail.getMessages();
            System.out.println("Total de Emails: " + mensagens.length);


            for (int i = 0, n = mensagens.length; i < n; i++) {

                Message mensagem = mensagens[i];

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                String data = format.format(mensagem.getReceivedDate());

                System.out.println("Data do E-mail --> " + data);
                System.out.println("Assunto --> " + mensagem.getSubject());

            }

            pastaEmail.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

