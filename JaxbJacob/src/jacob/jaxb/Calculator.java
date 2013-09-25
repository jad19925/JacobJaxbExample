package jacob.jaxb;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator implements ActionListener {

	private JFrame frame;
	private CalcConfig config;
	private File xmlFile;
	
	private JMenuItem mntmExit;
	//button background color menu items
	private JMenuItem mnBtBlack;
	private JMenuItem mnBtGray;
	private JMenuItem mnBtWhite;
	private JMenuItem mnBtBlue;
	private JMenuItem mnBtCyan;
	private JMenuItem mnBtDark_Gray;
	private JMenuItem mnBtGreen;
	private JMenuItem mnBtLight_Gray;
	private JMenuItem mnBtMagenta;
	private JMenuItem mnBtPink;
	private JMenuItem mnBtRed;
	private JMenuItem mnBtYellow;
	//button text color menu items
	private JMenuItem mnTxBlack;
	private JMenuItem mnTxGray;
	private JMenuItem mnTxWhite;
	private JMenuItem mnTxBlue;
	private JMenuItem mnTxCyan;
	private JMenuItem mnTxDark_Gray;
	private JMenuItem mnTxGreen;
	private JMenuItem mnTxLight_Gray;
	private JMenuItem mnTxMagenta;
	private JMenuItem mnTxPink;
	private JMenuItem mnTxRed;
	private JMenuItem mnTxYellow;
	//background color menu items
	private JMenuItem mnBkBlack;
	private JMenuItem mnBkGray;
	private JMenuItem mnBkWhite;
	private JMenuItem mnBkBlue;
	private JMenuItem mnBkCyan;
	private JMenuItem mnBkDark_Gray;
	private JMenuItem mnBkGreen;
	private JMenuItem mnBkLight_Gray;
	private JMenuItem mnBkMagenta;
	private JMenuItem mnBkPink;
	private JMenuItem mnBkRed;
	private JMenuItem mnBkYellow;
	
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
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		//initialize();
		xmlFile = new File(CalcConfig.xmlFile);
		if(!xmlFile.exists()) {
			config = new CalcConfig();
		}
		else {
			openConfig(xmlFile);
		}
		runningTotal = 0;
		initialize();
		append = false;
		opcode = none;
	}
	
	public void exit() {
		saveConfig(xmlFile);
		System.exit(0);
	}
	
	public void openConfig(File xml) {
		try {
			//Create jaxb context and unmarshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(CalcConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			//create Java object - CalcConfig from xml file
			config = (CalcConfig) jaxbUnmarshaller.unmarshal(xml);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			config = new CalcConfig();
		}
	}
	
	public void saveConfig(File xml){
		try {
			//Create jaxb context and unmarshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(CalcConfig.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			//for getting nicely formatted xml output
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			//write to XML file
			jaxbMarshaller.marshal(config, xml);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);
		
		JMenu mnConfiguration = new JMenu("Configuration");
		menuBar.add(mnConfiguration);
		
		JMenu mnButtonColor = new JMenu("Button Color");
		mnConfiguration.add(mnButtonColor);
		
		mnBtBlack = new JMenuItem(CalcConfig.black);
		mnBtBlack.addActionListener(this);
		mnButtonColor.add(mnBtBlack);
		
		mnBtGray = new JMenuItem(CalcConfig.gray);
		mnBtGray.addActionListener(this);
		mnButtonColor.add(mnBtGray);
		
		mnBtWhite = new JMenuItem(CalcConfig.white);
		mnBtWhite.addActionListener(this);
		mnButtonColor.add(mnBtWhite);
		
		mnBtBlue = new JMenuItem(CalcConfig.blue);
		mnBtBlue.addActionListener(this);
		mnButtonColor.add(mnBtBlue);
		
		mnBtCyan = new JMenuItem(CalcConfig.cyan);
		mnBtCyan.addActionListener(this);
		mnButtonColor.add(mnBtCyan);
		
		mnBtDark_Gray = new JMenuItem(CalcConfig.dark_gray);
		mnBtDark_Gray.addActionListener(this);
		mnButtonColor.add(mnBtDark_Gray);
		
		mnBtGreen = new JMenuItem(CalcConfig.green);
		mnBtGreen.addActionListener(this);
		mnButtonColor.add(mnBtGreen);
		
		mnBtLight_Gray = new JMenuItem(CalcConfig.light_gray);
		mnBtLight_Gray.addActionListener(this);
		mnButtonColor.add(mnBtLight_Gray);
		
		mnBtMagenta = new JMenuItem(CalcConfig.magenta);
		mnBtMagenta.addActionListener(this);
		mnButtonColor.add(mnBtMagenta);
		
		mnBtPink = new JMenuItem(CalcConfig.pink);
		mnBtPink.addActionListener(this);
		mnButtonColor.add(mnBtPink);
		
		mnBtRed = new JMenuItem(CalcConfig.red);
		mnBtRed.addActionListener(this);
		mnButtonColor.add(mnBtRed);
		
		mnBtYellow = new JMenuItem(CalcConfig.yellow);
		mnBtYellow.addActionListener(this);
		mnButtonColor.add(mnBtYellow);
		
		//button text color
		JMenu mnTextColor = new JMenu("Text Color");
		mnConfiguration.add(mnTextColor);
		
		mnTxBlack = new JMenuItem(CalcConfig.black);
		mnTxBlack.addActionListener(this);
		mnTextColor.add(mnTxBlack);
		
		mnTxGray = new JMenuItem(CalcConfig.gray);
		mnTxGray.addActionListener(this);
		mnTextColor.add(mnTxGray);
		
		mnTxWhite = new JMenuItem(CalcConfig.white);
		mnTxWhite.addActionListener(this);
		mnTextColor.add(mnTxWhite);
		
		mnTxBlue = new JMenuItem(CalcConfig.blue);
		mnTxBlue.addActionListener(this);
		mnTextColor.add(mnTxBlue);
		
		mnTxCyan = new JMenuItem(CalcConfig.cyan);
		mnTxCyan.addActionListener(this);
		mnTextColor.add(mnTxCyan);
		
		mnTxDark_Gray = new JMenuItem(CalcConfig.dark_gray);
		mnTxDark_Gray.addActionListener(this);
		mnTextColor.add(mnTxDark_Gray);
		
		mnTxGreen = new JMenuItem(CalcConfig.green);
		mnTxGreen.addActionListener(this);
		mnTextColor.add(mnTxGreen);
		
		mnTxLight_Gray = new JMenuItem(CalcConfig.light_gray);
		mnTxLight_Gray.addActionListener(this);
		mnTextColor.add(mnTxLight_Gray);
		
		mnTxMagenta = new JMenuItem(CalcConfig.magenta);
		mnTxMagenta.addActionListener(this);
		mnTextColor.add(mnTxMagenta);
		
		mnTxPink = new JMenuItem(CalcConfig.pink);
		mnTxPink.addActionListener(this);
		mnTextColor.add(mnTxPink);
		
		mnTxRed = new JMenuItem(CalcConfig.red);
		mnTxRed.addActionListener(this);
		mnTextColor.add(mnTxRed);
		
		mnTxYellow = new JMenuItem(CalcConfig.yellow);
		mnTxYellow.addActionListener(this);
		mnTextColor.add(mnTxYellow);
		
		//background color
		JMenu mnBackgroundColor = new JMenu("Background Color");
		mnConfiguration.add(mnBackgroundColor);
		
		mnBkBlack = new JMenuItem(CalcConfig.black);
		mnBkBlack.addActionListener(this);
		mnBackgroundColor.add(mnBkBlack);
		
		mnBkGray = new JMenuItem(CalcConfig.gray);
		mnBkGray.addActionListener(this);
		mnBackgroundColor.add(mnBkGray);
		
		mnBkWhite = new JMenuItem(CalcConfig.white);
		mnBkWhite.addActionListener(this);
		mnBackgroundColor.add(mnBkWhite);
		
		mnBkBlue = new JMenuItem(CalcConfig.blue);
		mnBkBlue.addActionListener(this);
		mnBackgroundColor.add(mnBkBlue);
		
		mnBkCyan = new JMenuItem(CalcConfig.cyan);
		mnBkCyan.addActionListener(this);
		mnBackgroundColor.add(mnBkCyan);
		
		mnBkDark_Gray = new JMenuItem(CalcConfig.dark_gray);
		mnBkDark_Gray.addActionListener(this);
		mnBackgroundColor.add(mnBkDark_Gray);
		
		mnBkGreen = new JMenuItem(CalcConfig.green);
		mnBkGreen.addActionListener(this);
		mnBackgroundColor.add(mnBkGreen);
		
		mnBkLight_Gray = new JMenuItem(CalcConfig.light_gray);
		mnBkLight_Gray.addActionListener(this);
		mnBackgroundColor.add(mnBkLight_Gray);
		
		mnBkMagenta = new JMenuItem(CalcConfig.magenta);
		mnBkMagenta.addActionListener(this);
		mnBackgroundColor.add(mnBkMagenta);
		
		mnBkPink = new JMenuItem(CalcConfig.pink);
		mnBkPink.addActionListener(this);
		mnBackgroundColor.add(mnBkPink);
		
		mnBkRed = new JMenuItem(CalcConfig.red);
		mnBkRed.addActionListener(this);
		mnBackgroundColor.add(mnBkRed);
		
		mnBkYellow = new JMenuItem(CalcConfig.yellow);
		mnBkYellow.addActionListener(this);
		mnBackgroundColor.add(mnBkYellow);
		
		//calculator buttons
		buttonList = new ArrayList<JButton>();
		
		button1 = new JButton("1");
		button1.setBounds(25, 50, 45, 45);
		button1.setOpaque(true);
		button1.addActionListener(this);
		frame.getContentPane().add(button1);
		buttonList.add(button1);
		
		button2 = new JButton("2");
		button2.setBounds(75, 50, 45, 45);
		button2.setOpaque(true);
		button2.addActionListener(this);
		frame.getContentPane().add(button2);
		buttonList.add(button2);
		
		button3 = new JButton("3");
		button3.setBounds(125, 50, 45, 45);
		button3.setOpaque(true);
		button3.addActionListener(this);
		frame.getContentPane().add(button3);
		buttonList.add(button3);
		
		button4 = new JButton("4");
		button4.setBounds(25, 100, 45, 45);
		button4.setOpaque(true);
		button4.addActionListener(this);
		frame.getContentPane().add(button4);
		buttonList.add(button4);
		
		button5 = new JButton("5");
		button5.setBounds(75, 100, 45, 45);
		button5.setOpaque(true);
		button5.addActionListener(this);
		frame.getContentPane().add(button5);
		buttonList.add(button5);
		
		button6 = new JButton("6");
		button6.setBounds(125, 100, 45, 45);
		button6.setOpaque(true);
		button6.addActionListener(this);
		frame.getContentPane().add(button6);
		buttonList.add(button6);
		
		button7 = new JButton("7");
		button7.setBounds(25, 150, 45, 45);
		button7.setOpaque(true);
		button7.addActionListener(this);
		frame.getContentPane().add(button7);
		buttonList.add(button7);
		
		button8 = new JButton("8");
		button8.setBounds(75, 150, 45, 45);
		button8.setOpaque(true);
		button8.addActionListener(this);
		frame.getContentPane().add(button8);
		buttonList.add(button8);
		
		button9 = new JButton("9");
		button9.setBounds(125, 150, 45, 45);
		button9.setOpaque(true);
		button9.addActionListener(this);
		frame.getContentPane().add(button9);
		buttonList.add(button9);
		
		button0 = new JButton("0");
		button0.setBounds(75, 200, 45, 45);
		button0.setOpaque(true);
		button0.addActionListener(this);
		frame.getContentPane().add(button0);
		buttonList.add(button0);
		
		buttonDec = new JButton(".");
		buttonDec.setBounds(125, 200, 45, 45);
		buttonDec.setOpaque(true);
		buttonDec.addActionListener(this);
		frame.getContentPane().add(buttonDec);
		buttonList.add(buttonDec);
		
		buttonPlus = new JButton("+");
		buttonPlus.setBounds(175, 50, 45, 45);
		buttonPlus.setOpaque(true);
		buttonPlus.addActionListener(this);
		frame.getContentPane().add(buttonPlus);
		buttonList.add(buttonPlus);
		
		buttonMinus = new JButton("-");
		buttonMinus.setBounds(175, 100, 45, 45);
		buttonMinus.setOpaque(true);
		buttonMinus.addActionListener(this);
		frame.getContentPane().add(buttonMinus);
		buttonList.add(buttonMinus);
		
		buttonMult = new JButton("*");
		buttonMult.setBounds(175, 150, 45, 45);
		buttonMult.setOpaque(true);
		buttonMult.addActionListener(this);
		frame.getContentPane().add(buttonMult);
		buttonList.add(buttonMult);
		
		buttonDiv = new JButton("/");
		buttonDiv.setBounds(175, 200, 45, 45);
		buttonDiv.setOpaque(true);
		buttonDiv.addActionListener(this);
		frame.getContentPane().add(buttonDiv);
		buttonList.add(buttonDiv);
		
		buttonEq = new JButton("=");
		buttonEq.setBounds(225, 100, 45, 145);
		buttonEq.setOpaque(true);
		buttonEq.addActionListener(this);
		frame.getContentPane().add(buttonEq);
		buttonList.add(buttonEq);
		
		buttonClr = new JButton("C");
		buttonClr.setBounds(225, 50, 45, 45);
		buttonClr.setOpaque(true);
		buttonClr.addActionListener(this);
		frame.getContentPane().add(buttonClr);
		buttonList.add(buttonClr);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBounds(25, 6, 245, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(15);
		textField.setText(Double.toString(runningTotal));
		
		setBackgroundColor(config.getBackgroundColor());
		setButtonTextColor(config.getTextColor());
		setButtonColor(config.getButtonColor());
	}
	
	private void setBackgroundColor(String bColor) {
		frame.setBackground(stringToColor(bColor));
		config.setBackgroundColor(bColor);
	}
	
	private void setButtonTextColor(String bColor) {
		for(int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setForeground(stringToColor(bColor));
		}
		config.setTextColor(bColor);
	}
	
	private void setButtonColor(String bColor) {
		for(int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setBackground(stringToColor(bColor));
		}
		config.setButtonColor(bColor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(mntmExit)) {
			exit();
		}
		//frame background
		else if(e.getSource().equals(mnBkBlack)) {
			setBackgroundColor(CalcConfig.black);
		}
		else if(e.getSource().equals(mnBkWhite)) {
			setBackgroundColor(CalcConfig.white);
		}
		else if(e.getSource().equals(mnBkGray)) {
			setBackgroundColor(CalcConfig.gray);
		}
		else if(e.getSource().equals(mnBkBlue)) {
			setBackgroundColor(CalcConfig.blue);
		}
		else if(e.getSource().equals(mnBkCyan)) {
			setBackgroundColor(CalcConfig.cyan);
		}
		else if(e.getSource().equals(mnBkDark_Gray)) {
			setBackgroundColor(CalcConfig.dark_gray);
		}
		else if(e.getSource().equals(mnBkGreen)) {
			setBackgroundColor(CalcConfig.green);
		}
		else if(e.getSource().equals(mnBkLight_Gray)) {
			setBackgroundColor(CalcConfig.light_gray);
		}
		else if(e.getSource().equals(mnBkMagenta)) {
			setBackgroundColor(CalcConfig.magenta);
		}
		else if(e.getSource().equals(mnBkPink)) {
			setBackgroundColor(CalcConfig.pink);
		}
		else if(e.getSource().equals(mnBkRed)) {
			setBackgroundColor(CalcConfig.red);
		}
		else if(e.getSource().equals(mnBkYellow)) {
			setBackgroundColor(CalcConfig.yellow);
		}
		//button text
		else if(e.getSource().equals(mnTxBlack)) {
			setButtonTextColor(CalcConfig.black);
		}
		else if(e.getSource().equals(mnTxWhite)) {
			setButtonTextColor(CalcConfig.white);
		}
		else if(e.getSource().equals(mnTxGray)) {
			setButtonTextColor(CalcConfig.gray);
		}
		else if(e.getSource().equals(mnTxBlue)) {
			setButtonTextColor(CalcConfig.blue);
		}
		else if(e.getSource().equals(mnTxCyan)) {
			setButtonTextColor(CalcConfig.cyan);
		}
		else if(e.getSource().equals(mnTxDark_Gray)) {
			setButtonTextColor(CalcConfig.dark_gray);
		}
		else if(e.getSource().equals(mnTxGreen)) {
			setButtonTextColor(CalcConfig.green);
		}
		else if(e.getSource().equals(mnTxLight_Gray)) {
			setButtonTextColor(CalcConfig.light_gray);
		}
		else if(e.getSource().equals(mnTxMagenta)) {
			setButtonTextColor(CalcConfig.magenta);
		}
		else if(e.getSource().equals(mnTxPink)) {
			setButtonTextColor(CalcConfig.pink);
		}
		else if(e.getSource().equals(mnTxRed)) {
			setButtonTextColor(CalcConfig.red);
		}
		else if(e.getSource().equals(mnTxYellow)) {
			setButtonTextColor(CalcConfig.yellow);
		}
		//button text
		else if(e.getSource().equals(mnBtBlack)) {
			setButtonColor(CalcConfig.black);
		}
		else if(e.getSource().equals(mnBtWhite)) {
			setButtonColor(CalcConfig.white);
		}
		else if(e.getSource().equals(mnBtGray)) {
			setButtonColor(CalcConfig.gray);
		}
		else if(e.getSource().equals(mnBtBlue)) {
			setButtonColor(CalcConfig.blue);
		}
		else if(e.getSource().equals(mnBtCyan)) {
			setButtonColor(CalcConfig.cyan);
		}
		else if(e.getSource().equals(mnBtDark_Gray)) {
			setButtonColor(CalcConfig.dark_gray);
		}
		else if(e.getSource().equals(mnBtGreen)) {
			setButtonColor(CalcConfig.green);
		}
		else if(e.getSource().equals(mnBtLight_Gray)) {
			setButtonColor(CalcConfig.light_gray);
		}
		else if(e.getSource().equals(mnBtMagenta)) {
			setButtonColor(CalcConfig.magenta);
		}
		else if(e.getSource().equals(mnBtPink)) {
			setButtonColor(CalcConfig.pink);
		}
		else if(e.getSource().equals(mnBtRed)) {
			setButtonColor(CalcConfig.red);
		}
		else if(e.getSource().equals(mnBtYellow)) {
			setButtonColor(CalcConfig.yellow);
		}
		
		//calculator buttons
		else if(e.getSource().equals(button1)) {
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
	
	private Color stringToColor(String cIn) {
		if(cIn.equals(CalcConfig.white)) {
			return Color.WHITE;
		}
		else if (cIn.equals(CalcConfig.gray)) {
			return Color.GRAY;
		}
		else if (cIn.equals(CalcConfig.blue)) {
			return Color.BLUE;
		}
		else if (cIn.equals(CalcConfig.cyan)) {
			return Color.CYAN;
		}
		else if (cIn.equals(CalcConfig.dark_gray)) {
			return Color.DARK_GRAY;
		}
		else if (cIn.equals(CalcConfig.green)) {
			return Color.GREEN;
		}
		else if (cIn.equals(CalcConfig.light_gray)) {
			return Color.LIGHT_GRAY;
		}
		else if (cIn.equals(CalcConfig.magenta)) {
			return Color.MAGENTA;
		}
		else if (cIn.equals(CalcConfig.pink)) {
			return Color.PINK;
		}
		else if (cIn.equals(CalcConfig.red)) {
			return Color.RED;
		}
		else if (cIn.equals(CalcConfig.yellow)) {
			return Color.YELLOW;
		}
		else {
			return Color.BLACK;
		}
	}
}
