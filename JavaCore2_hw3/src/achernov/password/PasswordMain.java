package achernov.password;

import java.util.regex.*;
import java.util.Scanner;

public class PasswordMain
{


  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    System.out.print("Введите пароль: ");
    String pswd = s.nextLine();
    s.close();
    System.out.println("Результат проверки пароля: " + verify(pswd));
  }


  public static boolean verify(String str)
  {
    return
      (has_number      (str) &&
       has_valid_length(str) &&
       has_special_smb (str) &&
       has_big_and_small_smb(str)
      );
  }


  public static boolean has_number(String str)
  {
    Pattern p = Pattern.compile(".*[0-9].*");
    Matcher m = p.matcher(str);
    System.out.println("has_number - " + m.matches());
    return m.matches();
  }

  public static boolean has_valid_length(String str)
  {
    Pattern p = Pattern.compile(".{8,20}");
    Matcher m = p.matcher(str);
    System.out.println("has_valid_length - " + m.matches());
    return m.matches();
  }


  //сделано предположение, что к спец.символам относятся только ~@#$!%^&*()_+
  public static boolean has_special_smb(String str)
  {
    Pattern p = Pattern.compile(".*[~@#$!%^&*()_+].*");
    Matcher m = p.matcher(str);
    System.out.println("has_special_smb - " + m.matches());
    return m.matches();
  }

  public static boolean has_big_and_small_smb(String str)
  {
    Pattern p = Pattern.compile("((.*[a-z]+.*)+(.*[A-Z]+.*)+)|((.*[A-Z]+.*)+(.*[a-z]+.*)+)");
    Matcher m = p.matcher(str);
    System.out.println("has_big_and_small_smb - " + m.matches());
    return m.matches();
  }

}
