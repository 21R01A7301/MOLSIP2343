// Task 4 - Quadratic Roots
//Project code - MOLAP649
//Develop a command line application or Graphical User Interface, that finds the roots of the quadratic equation. [ax^2 + bx + c=0]
// LinkedIn profile - https://www.linkedin.com/in/aditi-bengani/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quadraticroots extends JFrame {
    private JTextField A,B,C;
    private JLabel result;
    public Quadraticroots(){
        super("Quadratic Equation Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel input = new JPanel(new GridLayout(3,2,10,10));
        input.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        A = createTextField();
        B = createTextField();
        C = createTextField();

        input.add(new JLabel("Enter Coefficient a:"));
        input.add(A);
        input.add(new JLabel("Enter Coefficient b:"));
        input.add(B);
        input.add(new JLabel("Enter Coefficient c:"));
        input.add(C);

        JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton solvebtn = createButton("Solve", Color.GREEN);
        button.add(solvebtn);

        result = new JLabel();
        result.setHorizontalAlignment(JLabel.CENTER);
        solvebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solvequadraticequation();
            }
        });
        add(input,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
        add(result, BorderLayout.NORTH);
        setVisible(true);
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    private void solvequadraticequation() {
        try{
            double a = Double.parseDouble(A.getText());
            double b = Double.parseDouble(B.getText());
            double c = Double.parseDouble(C.getText());
            double d = b*b - 4*a*c;
            if(d>0){
                double r1 = (-b + Math.sqrt(d))/(2*a);
                double r2 = (-b - Math.sqrt(d))/(2*a);
                result.setText("Roots are "+r1+" and "+r2);
            }
            else if(d == 0 ){
                double r1 = -b/(2*a);
                result.setText("Roots "+r1);
            }
            else {
                result.setText("NO REAL ROOTS!!");
            }
        }
        catch (NumberFormatException e){
            result.setText("Invalid input!! Please enter valid coefficient.");
        }
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Quadraticroots();
            }
        });
    }
}
