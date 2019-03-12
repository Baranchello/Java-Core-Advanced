import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FirstWindow extends JFrame
{
  public FirstWindow(String title)
  {
    //создание основного окна программы
    setTitle(title);
    setBounds(400, 200, 500, 500);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    JLabel[] labels = new JLabel[3];
    for (int i = 0; i < labels.length; i++)
    {
      labels[i] = new JLabel("");
      labels[i].setAlignmentY(CENTER_ALIGNMENT);
      add(labels[i]);
    }

    JButton btn_add = new JButton("Добавить...");
    btn_add.setAlignmentY(CENTER_ALIGNMENT);
    add(btn_add);

    btn_add.addActionListener
      (new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {new SecondWindow("Второе окно программы");}
       }
      );


  }

}
