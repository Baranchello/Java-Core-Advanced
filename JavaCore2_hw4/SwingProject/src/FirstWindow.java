import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FirstWindow extends JFrame
{

  private JLabel[] labels = new JLabel[3];
  private FirstWindow self_ptr = this;
  private SecondWindow ptr_sw = null;

  public FirstWindow(String title)
  {
    //создание основного окна программы
    setTitle(title);
    setBounds(400, 200, 500, 500);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    for (int i = 0; i < labels.length; i++)
    {
      labels[i] = new JLabel("Тест");
      labels[i].setAlignmentY(CENTER_ALIGNMENT);
      add(labels[i]);
    }

    JButton btn_add = new JButton("Добавить...");
    btn_add.setAlignmentY(LEFT_ALIGNMENT);
    add(btn_add);

    btn_add.addActionListener
      (new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e)
         {

           if (ptr_sw == null) create_second_window("Второе окно", self_ptr);

         }
       }
      );
  }

  public void set_fam( String value)
  {
    this.labels[0].setText(value);
  }

  public void set_name( String value)
  {
    this.labels[1].setText(value);
  }

  public void set_patr (String value)
  {
    this.labels[2].setText(value);
  }

  void create_second_window(String title, FirstWindow ptr)
  {
    ptr_sw = new SecondWindow("Второе окно программы", ptr);
  }

  void set_null_ptr()
  {
    this.ptr_sw = null;
  }

}
