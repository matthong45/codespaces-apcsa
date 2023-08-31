import java.util.Scanner;
import java.util.Set;


public class PasswordStrength {
    enum passwordCategory{
        UNACCEPTABLE,
        WEAK,
        STRONG,
        VERYSTRONG
    }

    private static Set<String> tooCommonPasswords = Set.of("123","Password","12345678", "Password1", "Querty123", "iloveyou", "guest", "a1b2c3");
    private static Set<String> specialChars = Set.of("!","@","#", "$", "%", "^", "&", "*", "(", ")");
    public static void main(String args[]){
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("What password would you like to classify?");
            String password = scan.nextLine();
            System.out.println("Your password: " + password + " was classified as: " + evaluatePassword(password).name());
        }
    }

    public static passwordCategory evaluatePassword(String password)
    {
        
        if(tooCommonPasswords.contains(password))
        {
            return passwordCategory.UNACCEPTABLE;
        }
        if(password.length() < 7)
        {
            return passwordCategory.WEAK;
        }
        if(password.length() > 15)
        {
            return passwordCategory.VERYSTRONG;
        }
        return passwordCategory.STRONG;
    }


}
