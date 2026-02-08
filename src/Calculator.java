import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends WindowAdapter implements ActionListener {
    double num1, num2, check, xd;

    private final JTextArea jt = new JTextArea(10, 2);
    private final JFrame frame = new JFrame("Ultra Basic Calculator");
    private final JPanel content = new JPanel(null);
    private final JButton[] buttonsNums = new JButton[11];
    private final JButton[] buttonsArits = new JButton[5];
    private final JButton bClear = new JButton("CE");


    public void init(){
        // Configurando a janela inicial
        configFrame();

        // Setando a cor de fundo do app
        content.setBackground(Color.gray);

        configButtons(content);
        initColumns();

        content.setSize(300, 500);
        content.add(configLabel());
        frame.add(content);
    }

    private void configFrame(){
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setVisible(true);
    }

    private void configButtons(JPanel content){
        // Adicionando botões numéricos
        for (int i = 9; i >= 0; i--) {
            System.out.println("estou guardando " + i + " na posição " + i);
            String buttonNum = String.valueOf(i);
            buttonsNums[i] = new JButton(buttonNum);
            buttonsNums[i].addActionListener(this);
            content.add(buttonsNums[i]);
        }

        // adicionando botões de operação
        for (int i = 4; i >= 0; i--) {
            buttonsArits[i] = new JButton();
            buttonsArits[i].addActionListener(this);
            content.add(buttonsArits[i]);
        }

        // adicionando botão de limpeza
        bClear.setBounds(50, 30, 60, 30);
        bClear.addActionListener(this);
        content.add(bClear);
    }

    private void initColumns(){
        firstColumn();
        secondColumn();
        thirdColumn();
        fourthColumn();
    }

    private JTextArea configLabel(){
        jt.setBackground(Color.black);
        jt.setForeground(Color.pink);
        Font bigFont = new Font("sanserif", Font.BOLD, 18);
        jt.setFont(bigFont);
        jt.setLineWrap(true);
        jt.setEditable(true);

        jt.setBounds(120, 30, 240, 30);
        return jt;
    }

    private void firstColumn(){
        buttonsNums[7].setBounds(50, 10 + 3 * 20, 60, 30);
        buttonsNums[4].setBounds(50, 10 + 5 * 20, 60, 30);
        buttonsNums[1].setBounds(50, 10 + 7 * 20, 60, 30);
        buttonsNums[0].setBounds(50, 10 + 9 * 20, 130, 30);
    }

    private void secondColumn(){
        buttonsNums[8].setBounds(120, 10 + 3 * 20, 60, 30);
        buttonsNums[5].setBounds(120, 10 + 5 * 20, 60, 30);
        buttonsNums[2].setBounds(120, 10 + 7 * 20, 60, 30);
    }

    private void thirdColumn(){
        buttonsNums[9].setBounds(190, 10 + 3 * 20, 60, 30);
        buttonsNums[6].setBounds(190, 10 + 5 * 20, 60, 30);
        buttonsNums[3].setBounds(190, 10 + 7 * 20, 60, 30);
        buttonsArits[0].setBounds(190, 10 + 9 * 20, 60, 30);
        buttonsArits[0].setText("+");
    }

    private void fourthColumn(){
        buttonsArits[1].setBounds(260, 10 + 3 * 20, 100, 30);
        buttonsArits[1].setText("/");

        buttonsArits[2].setBounds(260, 10 + 5 * 20, 100, 30);
        buttonsArits[2].setText("*");

        buttonsArits[3].setBounds(260, 10 + 7 * 20, 100, 30);
        buttonsArits[3].setText("-");

        buttonsArits[4].setBounds(260, 10 + 9 * 20, 100, 30);
        buttonsArits[4].setText("=");
        buttonsArits[4].setBackground(Color.red);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String z, zt;

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == buttonsNums[i]) {
                String getStr = String.valueOf(i);
                zt = jt.getText();
                z = zt + getStr;
                jt.setText(z);
            }
        }

        if(e.getSource()== buttonsArits[0]){                    //FOR ADDITION
            try{
                num1=Double.parseDouble(jt.getText());
            }catch(NumberFormatException f){
                jt.setText("Invalid Format");
                return;
            }
            z="";
            jt.setText(z);
            check = 1;
        }

        if (e.getSource() == buttonsArits[1]) {                  //FOR  DIV
            try{
                num1=Double.parseDouble(jt.getText());
            }catch(NumberFormatException f){
                jt.setText("Invalid Format");
                return;
            }
            z="";
            jt.setText(z);
            check = 4;
        }

        if (e.getSource() == buttonsArits[2]) {                  //FOR MULT
            try{
                num1=Double.parseDouble(jt.getText());
            }catch(NumberFormatException f){
                jt.setText("Invalid Format");
                return;
            }
            z="";
            jt.setText(z);
            check = 3;
        }

        if (e.getSource() == buttonsArits[3]) {                 //FOR SUBTRACTION
            try{
                num1=Double.parseDouble(jt.getText());
            }catch(NumberFormatException f){
                jt.setText("Invalid Format");
                return;
            }
            z = "";
            jt.setText(z);
            check = 2;
        }

        if (e.getSource() == buttonsArits[4]) {
            showResult();
        }

        if(e.getSource()==bClear){
            num1=0;
            num2=0;
            check=0;
            xd=0;
            z="";
            jt.setText(z);
        }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        frame.dispose();
    }

    private void showResult(){
        try{
            num2 = Double.parseDouble(jt.getText());
        } catch (NumberFormatException ex) {
            jt.setText("Enter number first");
            return;
        }

        // Checando qual operação estamos tratando
        if(check==1)
            xd = num1+num2;
        if(check==2)
            xd = num1-num2;
        if(check==3)
            xd = num1*num2;
        if(check==4)
            xd = num1/num2;

        jt.setText(String.valueOf(xd));
    }
}