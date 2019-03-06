import achernov.MyException.*;

enum DAY_OF_WEEK
  {
    MONDAY(40), TUESDAY(32), WEDNESDAY(24), THURSDAY(16), FRIDAY(8), SATURDAY(0), SUNDAY(0);
    private int time_left;

    DAY_OF_WEEK(int time_left)
    {
      this.time_left = time_left;
    }

    public int get_time_left()
    {
      return time_left;
    }
  }

public class Main
{
  public static void main(String[] args)
  {

    getWorkingHours(DAY_OF_WEEK.MONDAY);

    exception_test();
  }

  public static void getWorkingHours(DAY_OF_WEEK day)
  {
    System.out.println("Текущий день: " + day + ". Осталось рабочих часов: " + day.get_time_left());
  }

  public static void exception_test()
  {
    String[][] str1 = {{"1", "2", "3", "4"}, {"2", "3", "4", "5"}, {"3", "4", "5", "6"}, {"4", "5", "6", "7"}};
    String[][] str2 = {{"1.5 2 14 37", "2", "3.5", "4"}, {"2", "-3.5", "4", "5"}, {"3", "4", "5.5", "6"}, {"4", "5", "6.5", "7 19 -10 0"}};
    String[][] str3 = {{"1.5", "2", "3.5", "4"}, {"2", "3", "Не в этот раз", "5"}, {"3", "4", "5", "6"}, {"4", "5", "6", "7"}};
    String[][] str4 = {{"1.5", "2", "3.5", "4"}, {"2", "3", "Нет"}, {"3", "4", "5", "6"}, {"4", "5", "6", "7"}};

    //Проверка работы с простыми строками с целыми числами
    try
    {
      summ(str1);
    }
    catch (MyArraySizeException e)
    {
      e.printStackTrace();
    }

    //Проверка работы с составными строками строками с целыми и вещественными числами
    try
    {
      summ(str2);
    }
    catch (MyArraySizeException e)
    {
      e.printStackTrace();
    }

    //Проверка генерации исключений с ошибкой преобразования строки (подстроки) в число
    try
    {
      summ(str3);
    }
    catch (MyArraySizeException e)
    {
      e.printStackTrace();
    }

    //Проверка генерации исключений с ошибкой размерности входного массива
    try
    {
      summ(str4);
    }
    catch (MyArraySizeException e)
    {
      e.printStackTrace();
    }
  }

  public static void summ(String[][] input_str_array) throws MyArraySizeException
  {
    final int LIMIT = 4;

    if (input_str_array == null   ||
        input_str_array.length    != LIMIT || input_str_array[0].length != LIMIT ||
        input_str_array[1].length != LIMIT || input_str_array[2].length != LIMIT ||
        input_str_array[3].length != LIMIT)
    {
      throw new MyArraySizeException("Некорректный размер входного массива.");
    }
    else
    {
      show(input_str_array);
      try
      {
       calc(input_str_array);
      }
      catch (MyArrayDataException e)
      {
        e.printStackTrace();
      }
    }
  }


  public static void show(String[][] str)
  {
    for (int i = 0; i < str.length; i++)
    {
      for (int j = 0; j < str[i].length; j++) System.out.print(str[i][j] + " ");
      System.out.println();
    }
  }

  public static void calc(String[][] input_str_array) throws MyArrayDataException
  {
    String[] sub_str;
    double result = 0;

    for (int i = 0; i < input_str_array.length; i++)
    {
      for (int j = 0; j < input_str_array[i].length; j++)
      {
        sub_str = input_str_array[i][j].split(" ");
        for (int n = 0; n < sub_str.length; n ++)
        {
          try
          {
            int elm = Integer.parseInt(sub_str[n]);
            result += elm;
          }
          catch (NumberFormatException e_int)
          {
            try
            {
              double elm = Double.parseDouble(sub_str[n]);
              result += elm;
            }
            catch (NumberFormatException e_double)
            {
              throw new MyArrayDataException("Ошибка преобразование подстроки [" + n + "] в элементе входного массива [" + i + "][" + j +"]");
            }
          }
        }

      }
    }
    System.out.println("Результат сложения: " + result);
  }

}
