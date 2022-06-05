import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Client.ClientRun;

import java.awt.event.*;
import java.awt.*;

public class TestPanel extends ClassPanel{

    Font font = new Font("SansSerif", Font.BOLD, 15);
    String message =    "A classic way to generate pseudo-random numbers, \n"+
                        "in java, is to use two methods from libraries: \n"+
                        "the MathClss.random(), and the Random.nextInt()\n"+
                        "methods. \n"+
                        "It's also possible to define a range for the \n"+
                        "random numbers generated. \n\n";

    JTextField mMax, mMin, mOutput;
    JTextField rMax, rMin, rOutput;
    
    JButton mSubmitButton;
    JButton rSubmitButton;


    public TestPanel(ClientRun cr){
        super(cr);        

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridLayout(0,1));

        //description
        JPanel description = new JPanel();
        description.setLayout(new BorderLayout());
        JLabel ldes = new JLabel();
        ldes.setText(TxtToHTML.escape(message));
        ldes.setFont(font);
        description.add(ldes, BorderLayout.CENTER);
        description.setBorder(new EmptyBorder(0, 25, 0, 25));
        //description.add(new JLabel(" "), BorderLayout.CENTER);
        finalPanel.add(description);
        
        //MathClass panel

            JPanel mPanel = new JPanel();
            JPanel mPanelText = new JPanel();

            mPanelText.setLayout(new BorderLayout());
            mPanel.setLayout(new BorderLayout());

            JLabel mTitle = new JLabel("Math Class method", SwingConstants.CENTER);
            mTitle.setFont(font);
            mPanel.add(mTitle, BorderLayout.NORTH);

            mMax = new JTextField(10);
            mMin = new JTextField(10);
            mOutput = new JTextField(10);
            mOutput.setEditable(false);
            mSubmitButton = new JButton("Submit");
            

            // Add labelled input fields to display
            JPanel m_inFieldPane = new JPanel();
            m_inFieldPane.setLayout(new GridLayout(2,2));
            m_inFieldPane.add(new JLabel("Max value"));
            m_inFieldPane.add(mMax);
            mMax.addActionListener(this);
            m_inFieldPane.add(new JLabel("Min value"));
            m_inFieldPane.add(mMin);
            mMin.addActionListener(this);
            mPanelText.add(m_inFieldPane, BorderLayout.NORTH);

            // Add submission button
            JPanel m_submitPane = new JPanel();
            m_submitPane.setLayout(new FlowLayout());
            m_submitPane.add(new JLabel("Press Button to Create Number"));
            mSubmitButton.addActionListener(this);
            m_submitPane.add(mSubmitButton);
            mPanelText.add(m_submitPane, BorderLayout.CENTER);

            // Add Output fields
            JPanel m_outFieldPane = new JPanel();
            m_outFieldPane.setLayout(new GridLayout(2,2));
            m_outFieldPane.add(new JLabel("Number Created"));
            m_outFieldPane.add(mOutput);
            mPanelText.add(m_outFieldPane, BorderLayout.SOUTH);

            mPanel.add(mPanelText, BorderLayout.CENTER);
            mPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            finalPanel.add(mPanel);

        //RandomClass panel
        
            JPanel rPanel = new JPanel();
            JPanel rPanelText = new JPanel();

            rPanelText.setLayout(new BorderLayout());
            rPanel.setLayout(new BorderLayout());

            JLabel rTitle = new JLabel("Random Class method", SwingConstants.CENTER);
            rTitle.setFont(font);
            rPanel.add(rTitle, BorderLayout.NORTH);

            rMax = new JTextField(10);
            rMin = new JTextField(10);
            rOutput = new JTextField(10);
            rOutput.setEditable(false);
            rSubmitButton = new JButton("Submit");
            

            // Add labelled input fields to display
            JPanel r_inFieldPane = new JPanel();
            r_inFieldPane.setLayout(new GridLayout(2,2));
            r_inFieldPane.add(new JLabel("Max value"));
            r_inFieldPane.add(rMax);
            rMax.addActionListener(this);
            r_inFieldPane.add(new JLabel("Min value"));
            r_inFieldPane.add(rMin);
            rMin.addActionListener(this);
            rPanelText.add(r_inFieldPane, BorderLayout.NORTH);

            // Add submission button
            JPanel r_submitPane = new JPanel();
            r_submitPane.setLayout(new FlowLayout());
            r_submitPane.add(new JLabel("Press Button to Create Number"));
            rSubmitButton.addActionListener(this);
            r_submitPane.add(rSubmitButton);
            rPanelText.add(r_submitPane, BorderLayout.CENTER);

            // Add Output fields
            JPanel r_outFieldPane = new JPanel();
            r_outFieldPane.setLayout(new GridLayout(2,2));
            r_outFieldPane.add(new JLabel("Number Created"));
            r_outFieldPane.add(rOutput);
            rPanelText.add(r_outFieldPane, BorderLayout.SOUTH);
            rPanel.add(rPanelText, BorderLayout.CENTER);
            rPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            //rPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
            finalPanel.add(rPanel);
        //CreatePanel(rMax, rMin, rOutput, rSubmitButton);

        // Display the final product
        finalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new FlowLayout());
        this.add(finalPanel);
    }


    //methods
    public int randomClassNumber(){
        Random r = new Random();
        int number = r.nextInt();
        return number;
    }

    public int randomClassNumber(int max, int min){
        Random r = new Random();
        int number = r.nextInt(max - min + 1 ) + min;
        return number;
    }

    public int MathClassNumber(){
        int number = (int)(Math.random()*1000);
        return number;
    }

    public int MathClassNumber(int max, int min){
        if(max < min){
            return 0;
        }
        else{
            int number = (int)(Math.random()*(max - min + 1 ) + min);
            return number;
        }
    }

    //deprecated
    /*
    public void CreatePanel(JTextField max, JTextField min, JTextField output, JButton submitButton){
        max = new JTextField(10);
        min = new JTextField(10);
        output = new JTextField(10);
        output.setEditable(false);
        submitButton = new JButton("Submit");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());;

        // Add labelled input fields to display
        JPanel inFieldPane = new JPanel();
        inFieldPane.setLayout(new GridLayout(2,2));
        inFieldPane.add(new JLabel("Max value"));
        inFieldPane.add(max);
        max.addActionListener(this);
        inFieldPane.add(new JLabel("Min value"));
        inFieldPane.add(min);
        min.addActionListener(this);
        panel.add(inFieldPane, BorderLayout.NORTH);
        // Add submission button
        JPanel submitPane = new JPanel();
        submitPane.setLayout(new FlowLayout());
        submitPane.add(new JLabel("Press Button to Create Number"));
        submitButton.addActionListener(this);
        submitPane.add(submitButton);
        panel.add(submitPane, BorderLayout.CENTER);
        // Add Output fields
        JPanel outFieldPane = new JPanel();
        outFieldPane.setLayout(new GridLayout(1,2));
        outFieldPane.add(new JLabel("Number Created"));
        outFieldPane.add(output);
        panel.add(outFieldPane, BorderLayout.SOUTH);

        this.frame.add(panel);
    }*/

    public void actionPerformed (ActionEvent e)
   {
        // Display full name if and only if button was pushed
        if (e.getSource() == mSubmitButton)
        {

            int max = Integer.parseInt(mMax.getText().trim());
            int min = Integer.parseInt(mMin.getText().trim());
            int number = MathClassNumber(max, min);
            mOutput.setText(String.valueOf(number));

        } else if (e.getSource() == rSubmitButton)
        {

            int max = Integer.parseInt(rMax.getText().trim());
            int min = Integer.parseInt(rMin.getText().trim());
            int number = randomClassNumber(max, min);
            rOutput.setText(String.valueOf(number));

        }
   }

}
