import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondWindow extends JFrame
{
  private int LIMIT = 3;

  public SecondWindow(String title, FirstWindow ptr)
  {
    setTitle(title);
    setBounds(450, 250, 500, 250);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    setVisible(true);

    setLayout(new GridLayout(4, 2));

    JLabel[] labels = new JLabel[LIMIT];

    labels[0] = new JLabel("Фамилия: ");
    labels[1] = new JLabel("Имя:     ");
    labels[2] = new JLabel("Отчество:");

    JTextField[] jtf = new JTextField[LIMIT];

    jtf[0] = new JTextField();
    jtf[1] = new JTextField();
    jtf[2] = new JTextField();

    for (int i = 0; i < LIMIT; i++)
    {
      add(labels[i]);
      add(jtf[i]);
    }

    JButton btn_save = new JButton("Сохранить");
    btn_save.setAlignmentY(CENTER_ALIGNMENT);
    add(btn_save);

    btn_save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ptr.set_fam (jtf[0].getText());
        ptr.set_name(jtf[1].getText());
        ptr.set_patr(jtf[2].getText());
        ptr.set_null_ptr();
        dispose();
      }
    });


  }
}
