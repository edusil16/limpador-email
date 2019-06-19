import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

public class MailExcluiMensagens {

    public void excluiMensagens (String userName,String senha, String pastaLimpeza, int recuoDias) throws IOException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -recuoDias);
        //
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataEmailDate;
        Date hojeMenos120dias=cal.getTime();
        System.out.println("Apagar emails recebidos na pasta "+pastaLimpeza+" ate "+fmt.format(hojeMenos120dias));
        //
        Properties properties = new Properties();
        Store store;
        try {
            // Criar a sessão de conexão com o servidor  de e-mail
            Session session = Session.getDefaultInstance(properties, null);
            store = session.getStore("imaps");
            store.connect("outlook.office365.com",userName, senha);

            // opens the inbox folder
            IMAPFolder folderInbox = (IMAPFolder) store.getFolder(pastaLimpeza);
            folderInbox.open(IMAPFolder.READ_WRITE);

            // fetches new messages from server
            Message[] arrayMessages = folderInbox.getMessages();

            for (int i = 0; (i < arrayMessages.length && i <= 1000); i++) {
                Message message = arrayMessages[i];
                dataEmailDate = message.getSentDate();
                if (dataEmailDate.before(hojeMenos120dias)) {
                    String sentDate = fmt.format(message.getSentDate());
                    System.out.print("Mensagem NÂº " + (i + 1) + ":");
                    System.out.println("\t Enviado em: " + sentDate);
                    message.setFlag(Flags.Flag.DELETED, true);
                }
            }

            // disconnect
            folderInbox.close(true);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for imaps.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
    }
}