package jacob.jaxb;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InternalCalculator implements ActionListener {

	private JPanel frame;
	
	//buttons
	private ArrayList<JButton> buttonList;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button0;
	private JButton buttonDec;
	private JButton buttonPlus;
	private JButton buttonMinus;
	private JButton buttonMult;
	private JButton buttonDiv;
	private JButton buttonEq;
	private JButton buttonClr;
	private JTextField textField;
	
	private double runningTotal;
	private boolean append;
	private int opcode;
	private final int none = 0;
	private final int plus = 1;
	private final int minus = 2;
	private final int mult = 3;
	private final int div = 4;
	
	private String fontName;
	private int fontSize;
	
	/**
	 * Create the frame.
	 */
	public InternalCalculator(JPanel panel, String fName, int fSize) {
		frame = panel;
		fontName = fName;
		fontSize = fSize;
		
		runningTotal = 0;
		initialize();
		append = false;
		opcode = none;
	}
	
	private void initialize() {
		//calculator buttons
				buttonList = new ArrayList<JButton>();
				
				button1 = new JButton("1");
				button1.setBounds(25, 50, 45, 45);
				button1.setOpaque(true);
				button1.addActionListener(this);
				frame.add(button1);
				buttonList.add(button1);
				
				button2 = new JButton("2");
				button2.setBounds(75, 50, 45, 45);
				button2.setOpaque(true);
				button2.addActionListener(this);
				frame.add(button2);
				buttonList.add(button2);
				
				button3 = new JButton("3");
				button3.setBounds(125, 50, 45, 45);
				button3.setOpaque(true);
				button3.addActionListener(this);
				frame.add(button3);
				buttonList.add(button3);
				
				button4 = new JButton("4");
				button4.setBounds(25, 100, 45, 45);
				button4.setOpaque(true);
				button4.addActionListener(this);
				frame.add(button4);
				buttonList.add(button4);
				
				button5 = new JButton("5");
				button5.setBounds(75, 100, 45, 45);
				button5.setOpaque(true);
				button5.addActionListener(this);
				frame.add(button5);
				buttonList.add(button5);
				
				button6 = new JButton("6");
				button6.setBounds(125, 100, 45, 45);
				button6.setOpaque(true);
				button6.addActionListener(this);
				frame.add(button6);
				buttonList.add(button6);
				
				button7 = new JButton("7");
				button7.setBounds(25, 150, 45, 45);
				button7.setOpaque(true);
				button7.addActionListener(this);
				frame.add(button7);
				buttonList.add(button7);
				
				button8 = new JButton("8");
				button8.setBounds(75, 150, 45, 45);
				button8.setOpaque(true);
				button8.addActionListener(this);
				frame.add(button8);
				buttonList.add(button8);
				
				button9 = new JButton("9");
				button9.setBounds(125, 150, 45, 45);
				button9.setOpaque(true);
				button9.addActionListener(this);
				frame.add(button9);
				buttonList.add(button9);
				
				button0 = new JButton("0");
				button0.setBounds(75, 200, 45, 45);
				button0.setOpaque(true);
				button0.addActionListener(this);
				frame.add(button0);
				buttonList.add(button0);
				
				buttonDec = new JButton(".");
				buttonDec.setBounds(125, 200, 45, 45);
				buttonDec.setOpaque(true);
				buttonDec.addActionListener(this);
				frame.add(buttonDec);
				buttonList.add(buttonDec);
				
				buttonPlus = new JButton("+");
				buttonPlus.setBounds(175, 50, 45, 45);
				buttonPlus.setOpaque(true);
				buttonPlus.addActionListener(this);
				frame.add(buttonPlus);
				buttonList.add(buttonPlus);
				
				buttonMinus = new JButton("-");
				buttonMinus.setBounds(175, 100, 45, 45);
				buttonMinus.setOpaque(true);
				buttonMinus.addActionListener(this);
				frame.add(buttonMinus);
				buttonList.add(buttonMinus);
				
				buttonMult = new JButton("*");
				buttonMult.setBounds(175, 150, 45, 45);
				buttonMult.setOpaque(true);
				buttonMult.addActionListener(this);
				frame.add(buttonMult);
				buttonList.add(buttonMult);
				
				buttonDiv = new JButton("/");
				buttonDiv.setBounds(175, 200, 45, 45);
				buttonDiv.setOpaque(true);
				buttonDiv.addActionListener(this);
				frame.add(buttonDiv);
				buttonList.add(buttonDiv);
				
				buttonEq = new JButton("=");
				buttonEq.setBounds(225, 100, 45, 145);
				buttonEq.setOpaque(true);
				buttonEq.addActionListener(this);
				frame.add(buttonEq);
				buttonList.add(buttonEq);
				
				buttonClr = new JButton("C");
				buttonClr.setBounds(225, 50, 45, 45);
				buttonClr.setOpaque(true);
				buttonClr.addActionListener(this);
				frame.add(buttonClr);
				buttonList.add(buttonClr);
				
				textField = new JTextField();
				textField.setHorizontalAlignment(SwingConstants.TRAILING);
				textField.setBounds(25, 6, 245, 39);
				frame.add(textField);
				textField.setColumns(15);
				textField.setText(Double.toString(runningTotal));
				
				setFont(fontName, fontSize);
	}
	
	public void setFont(String fName, int fSize)
	{
		fontName = fName;
		fontSize = fSize;
		for(int i = 0; i < buttonList.size(); i++)
		{
			buttonList.get(i).setFont(new Font(fontName, Font.PLAIN, fontSize));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button1)) {
			if(append) {
				textField.setText(textField.getText() + "1");
			}
			else {
				textField.setText("1");
				append = true;
			}
		}
		else if(e.getSource().equals(button2)) {
			if(append) {
				textField.setText(textField.getText() + "2");
			}
			else {
				textField.setText("2");
				append = true;
			}
		}
		else if(e.getSource().equals(button3)) {
			if(append) {
				textField.setText(textField.getText() + "3");
			}
			else {
				textField.setText("3");
				append = true;
			}
		}
		else if(e.getSource().equals(button4)) {
			if(append) {
				textField.setText(textField.getText() + "4");
			}
			else {
				textField.setText("4");
				append = true;
			}
		}
		else if(e.getSource().equals(button5)) {
			if(append) {
				textField.setText(textField.getText() + "5");
			}
			else {
				textField.setText("5");
				append = true;
			}
		}
		else if(e.getSource().equals(button6)) {
			if(append) {
				textField.setText(textField.getText() + "6");
			}
			else {
				textField.setText("6");
				append = true;
			}
		}
		else if(e.getSource().equals(button7)) {
			if(append) {
				textField.setText(textField.getText() + "7");
			}
			else {
				textField.setText("7");
				append = true;
			}
		}
		else if(e.getSource().equals(button8)) {
			if(append) {
				textField.setText(textField.getText() + "8");
			}
			else {
				textField.setText("8");
				append = true;
			}
		}
		else if(e.getSource().equals(button9)) {
			if(append) {
				textField.setText(textField.getText() + "9");
			}
			else {
				textField.setText("9");
				append = true;
			}
		}
		else if(e.getSource().equals(button0)) {
			if(append) {
				textField.setText(textField.getText() + "0");
			}
			else {
				textField.setText("0");
				append = true;
			}
		}
		else if(e.getSource().equals(buttonDec)) {
			if(append) {
				if(!textField.getText().contains(".")){
					textField.setText(textField.getText() + ".");
				}
			}
			else {
				textField.setText("0.");
				append = true;
			}
		}
		else if(e.getSource().equals(buttonPlus)) {
			processOperation();
			append = false;
			opcode = plus;
		}
		else if(e.getSource().equals(buttonMinus)) {
			processOperation();
			append = false;
			opcode = minus;
		}
		else if(e.getSource().equals(buttonMult)) {
			processOperation();
			append = false;
			opcode = mult;
		}
		else if(e.getSource().equals(buttonDiv)) {
			processOperation();
			append = false;
			opcode = div;
		}
		else if(e.getSource().equals(buttonEq)) {
			processOperation();
			append = false;
			opcode = none;
		}
		else if(e.getSource().equals(buttonClr)) {
			if(append) {
				textField.setText("0.0");
				append = false;
			}
			else {
				textField.setText("0.0");
				append = false;
				opcode = none;
				runningTotal = 0;
			}
		}
	}
	
	private void processOperation() {
		switch (opcode) {
		case none:
			runningTotal = Double.parseDouble(textField.getText());
			append = false;
			break;
		case plus:
			runningTotal = runningTotal + Double.parseDouble(textField.getText());
			textField.setText(Double.toString(runningTotal));
			append = false;
			break;
		case minus:
			runningTotal = runningTotal - Double.parseDouble(textField.getText());
			textField.setText(Double.toString(runningTotal));
			append = false;
			break;
		case mult:
			runningTotal = runningTotal * Double.parseDouble(textField.getText());
			textField.setText(Double.toString(runningTotal));
			append = false;
			break;
		case div:
			runningTotal = runningTotal / Double.parseDouble(textField.getText());
			textField.setText(Double.toString(runningTotal));
			append = false;
			break;
		}
	}
}
