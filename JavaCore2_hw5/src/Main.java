public class Main
{

  static final int SIZE = 1000000;
  static final int HALF = SIZE/2;
  static float array[] = new float[SIZE];

  public static void main(String[] args)
  {
    init_array(1.0f);
    step_by_step_count();
    half_count();
  }


  public static void init_array(float value)
  {
    for (int i = 0; i < SIZE; i++) {array[i] = i;}
  }

  public static void step_by_step_count()
  {
    System.out.println("<- Поэлементное вычисление в одном потоке ->");
    long time = System.currentTimeMillis();
    for (int i = 0; i < SIZE; i++)
    {
      array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
    time = System.currentTimeMillis() - time;
    System.out.println("Время расчета массива: " + time + " мс");

  }


  public static void half_count()
  {
    float[] a1 = new float[HALF];

    int right_side = (SIZE%2 == 0)? HALF: (HALF+1);
    float[] a2 = new float[right_side];

    System.out.println("<- Параллельная обработка ->");

    long time = System.currentTimeMillis();
    System.arraycopy(array, 0, a1, 0, HALF);
    System.arraycopy(array, HALF, a2, 0, right_side);
    time = System.currentTimeMillis() - time;
    System.out.println("Время разделения массива на два: " + time + " мс");

    new Thread(() -> count(a1, true, HALF       )).start();
    new Thread(() -> count(a2, false, right_side)).start();

  }


  //side = true - левая часть массива; false - правая часть
  public static void count (float arr[], boolean side, int h)
  {

    long time = System.currentTimeMillis();
    for (int i = 0; i < h; i++)
    {
      arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
    time = System.currentTimeMillis() - time;
    System.out.println("Время преобразования " + (side? "левой":"правой") + " части массива: " + time + " мс");

    time = System.currentTimeMillis();

    Object lock = new Object();
    synchronized (lock)
    {
      if (side)
        {
          System.arraycopy(arr, 0, array, 0, h);
        }
      else
        {
          System.arraycopy(arr, 0, array, HALF, h);
        }
    }

    time = System.currentTimeMillis() - time;
    System.out.println("Время склейки " + (side? "левой":"правой") + " части : "  + time + " мс");
  }


}
