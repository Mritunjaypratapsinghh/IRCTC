package ticket.booking.utils;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.text.StyledEditorKit;

public class PasswordUtil {
    public static String hashPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(12));

    }



    public static Boolean verifyPassword(String rawPassword, String hashPassword){
        return BCrypt.checkpw(rawPassword,hashPassword);
    }

}
