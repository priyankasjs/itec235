// 00076110 | Priyanka Jagdipsingh

import java.awt.*;       //using awt's layouts
import java.awt.event.*; //using awt's event classes and listener interfaces
import javax.swing.*;    //using swing components and containers

//a swing application extends from javax.swing.JFrame
public class SwingPhoneApp extends JFrame {
    private JButton[] btnNumbers;  // array
    private JButton btnCall;
    private JButton btnClear;
    private JTextField tfDisplay;
    private String strPhoneNumber = "";
    private boolean isCalling = false;

    public SwingPhoneApp() {
        //get the content-pane of top-level container JFrame. components are added onto content pane
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //set up the top display panel
        JPanel pnlDisplay = new JPanel(new FlowLayout());
        cp.add(pnlDisplay, BorderLayout.NORTH);

        //set up the display text field
        tfDisplay = new JTextField(30);
        tfDisplay.setEditable(false);
        tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        pnlDisplay.add(tfDisplay);

        //set up the center button panel
        JPanel pnlButtons = new JPanel(new GridLayout(4, 3, 3, 3));
        cp.add(pnlButtons, BorderLayout.CENTER);

        //set up a common listener for all number buttons
        MyNumberButtonListener lis = new MyNumberButtonListener();

        //allocate the JButton array for number buttons
        btnNumbers = new JButton[10];

        //allocate each of the item (JButton) of the array
        for (int i = 0; i < btnNumbers.length; ++i) {
            btnNumbers[i] = new JButton(i + "");
            //add the common listener to each of the JButton
            btnNumbers[i].addActionListener(lis);
        }

        //add the JButtons in the right order
        for (int i = 1; i <= 9; ++i) {
            pnlButtons.add(btnNumbers[i]);
        }

        btnClear = new JButton("CLEAR");
        pnlButtons.add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                strPhoneNumber = "";
                tfDisplay.setText(strPhoneNumber);
            }
        });

        pnlButtons.add(btnNumbers[0]);

        btnCall = new JButton("CALL");
        pnlButtons.add(btnCall);
        btnCall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (isCalling) {
                    isCalling = false;
                    btnCall.setText("CALL");
                    //call(strPhoneNumber)
                } else {
                    isCalling = true;
                    btnCall.setText("HANG UP");
                    //hangup()
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit program if JFrame's close-window button clicked
        setSize(400, 350);
        setTitle("Swing Phone App");
        setVisible(true);    //show it
    }

    // Named inner listener class for the number buttons
    private class MyNumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            strPhoneNumber += evt.getActionCommand();
            tfDisplay.setText(strPhoneNumber);
        }
    }

    public static void main(String[] args) {
        //recommended to run the GUI construction in event Dispatching thread for thread-safe operations
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingPhoneApp(); //Let the constructor does the job
            }
        });
    }
}