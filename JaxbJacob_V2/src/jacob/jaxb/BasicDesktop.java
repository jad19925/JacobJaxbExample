package jacob.jaxb;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class BasicDesktop implements ActionListener {

	private JFrame frame;
	private WindowConfig config;
	private File xmlFile;
	private JMenuItem mntmExit;
	
	private JLabel backgroundLabel;
	private JLabel lblCalculator;
	private JPanel testpanel;
	private InternalCalculator calculator;
	
	private JLabel speechTest;
	
	private ArrayList<JMenuItem> backgroundList;
	private ArrayList<JMenuItem> buttonImageList;
	private ArrayList<JMenuItem> fontList;
	private ArrayList<JMenuItem> fontSizeList;
	
	private ImageIcon backImage = null;
	private ImageIcon buttonImage = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicDesktop window = new BasicDesktop();
					window.frame.setVisible(true);

					Graphics gl = window.speechTest.getGraphics();
					SpeechBubble.drawBubble(gl, 100, 100, 50, 50);
					window.frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BasicDesktop() {
		xmlFile = new File(WindowConfig.xmlFile);
		if(!xmlFile.exists()) {
			config = new WindowConfig();
		}
		else {
			openConfig(xmlFile);
		}
		try
		{
			BufferedImage img = null;
			img = ImageIO.read(new File(config.getBackgroundImage()));
			backImage = new javax.swing.ImageIcon(img.getScaledInstance(1400, 540, Image.SCALE_SMOOTH));
			
			BufferedImage img2 = ImageIO.read(new File(config.getButtonImage()));
			Graphics g = img2.getGraphics();
			g.setFont(new Font(config.getFontName(), Font.PLAIN, config.getFontSize()));
			g.setColor(Color.BLACK);
			g.drawString("Calculator", 17, 23);
			g.dispose();
			buttonImage = new javax.swing.ImageIcon(img2);
		}
		catch(Exception e)
		{
			System.out.println("Could not find one or more of files: " + config.getBackgroundImage() + ", " + config.getButtonImage());
		}
		initialize();
	}
	
	public void exit() {
		saveConfig(xmlFile);
		System.exit(0);
	}
	
	public void openConfig(File xml) {
		try {
			//Create jaxb context and unmarshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(WindowConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			//create Java object - CalcConfig from xml file
			config = (WindowConfig) jaxbUnmarshaller.unmarshal(xml);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			config = new WindowConfig();
		}
	}
	
	public void saveConfig(File xml){
		try {
			//Create jaxb context and unmarshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(WindowConfig.class);
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
		frame.setBounds(100, 100, 1400, 540 + 44);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);
		
		JMenu mnConfiguration = new JMenu("Configuration");
		menuBar.add(mnConfiguration);
		
		JMenu mnBackgroundImage = new JMenu("Background Image");
		mnConfiguration.add(mnBackgroundImage);
		
		//get list of all filenames
		backgroundList = new ArrayList<JMenuItem>();
		String backPath = config.getBackgroundImage().substring(0, config.getBackgroundImage().lastIndexOf('/'));
		File folder = new File(backPath);
		File[] listOfBackgrounds = folder.listFiles();
		for(int i = 0; i < listOfBackgrounds.length; i++)
		{
			String path = listOfBackgrounds[i].getPath();
			if(path.endsWith(".png"))
			{
				backgroundList.add(new JMenuItem(path.substring(path.lastIndexOf('/')+1, path.lastIndexOf('.'))));
				backgroundList.get(backgroundList.size()-1).addActionListener(this);
				mnBackgroundImage.add(backgroundList.get(backgroundList.size()-1));
			}
		}
		//System.out.println(backPath);
		
		JMenu mnButtonImage = new JMenu("Button Image");
		mnConfiguration.add(mnButtonImage);
		
		//get list of all filenames
		buttonImageList = new ArrayList<JMenuItem>();
		String buttonPath = config.getButtonImage().substring(0, config.getButtonImage().lastIndexOf('/'));
		File folderButton = new File(buttonPath);
		File[] listOfButtons = folderButton.listFiles();
		for(int i = 0; i < listOfButtons.length; i++)
		{
			String path = listOfButtons[i].getPath();
			if(path.endsWith(".png"))
			{
				buttonImageList.add(new JMenuItem(path.substring(path.lastIndexOf('/')+1, path.lastIndexOf('.'))));
				buttonImageList.get(buttonImageList.size()-1).addActionListener(this);
				mnButtonImage.add(buttonImageList.get(buttonImageList.size()-1));
			}
		}
		//System.out.println(buttonPath);
		
		JMenu mnFont = new JMenu("Font");
		mnConfiguration.add(mnFont);
		
		fontList = new ArrayList<JMenuItem>();
		for(int i = 0; i < WindowConfig.fonts.length; i++)
		{
			fontList.add(new JMenuItem(WindowConfig.fonts[i]));
			fontList.get(fontList.size()-1).addActionListener(this);
			mnFont.add(fontList.get(fontList.size()-1));
		}
		
		JMenu mnFontSize = new JMenu("Font Size");
		mnConfiguration.add(mnFontSize);
		
		fontSizeList = new ArrayList<JMenuItem>();
		for(int i = 0; i < WindowConfig.fontSizes.length; i++)
		{
			fontSizeList.add(new JMenuItem(Integer.toString(WindowConfig.fontSizes[i])));
			fontSizeList.get(fontSizeList.size()-1).addActionListener(this);
			mnFontSize.add(fontSizeList.get(fontSizeList.size()-1));
		}
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		backgroundLabel = new JLabel();
		backgroundLabel.setBackground(Color.BLACK);
		backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundLabel.setBounds(0, 0, 1400, 540);
		backgroundLabel.setIcon(backImage);
		layeredPane.add(backgroundLabel);
		
		lblCalculator = new JLabel();
		lblCalculator.setFont(new Font(config.getFontName(), Font.PLAIN, config.getFontSize()));
		lblCalculator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//System.out.println("label clicked!");
				showHideCalculator();
			}
		});
		layeredPane.setLayer(lblCalculator, 3);
		lblCalculator.setBounds(1271, 6, 123, 32);
		lblCalculator.setIcon(buttonImage);
		layeredPane.add(lblCalculator);
		
		testpanel = new JPanel();
		testpanel.setBackground(Color.YELLOW);
		testpanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		testpanel.setLayout(null);
		testpanel.setBounds(1090, 50, 300, 270);
		calculator = new InternalCalculator(testpanel, config.getFontName(), config.getFontSize());
		layeredPane.add(testpanel, new Integer(1),0);
		testpanel.setVisible(false);
		
		speechTest = new JLabel("Hello");
		speechTest.setFont(new Font(config.getFontName(), Font.PLAIN, config.getFontSize()));
		speechTest.setBounds(400, 200, 50, 50);
		layeredPane.setLayer(speechTest, 3);
		layeredPane.add(speechTest);
//		Graphics gl = speechTest.getGraphics();
//		SpeechBubble.drawBubble(gl, 100, 100, 50, 50);
	}
	
	public void setBackgroundImage(String imagePath)
	{
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imagePath));
			backImage = new javax.swing.ImageIcon(img.getScaledInstance(1400, 540, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundLabel.setIcon(backImage);
	}
	
	public void setButtonDesign(String imagePath, String fontName, int fontSize)
	{
		try {
			BufferedImage img2 = ImageIO.read(new File(imagePath));
			Graphics g = img2.getGraphics();
			g.setFont(new Font(fontName, Font.PLAIN, fontSize));
			g.setColor(Color.BLACK);
			g.drawString("Calculator", 17, 23);
			g.dispose();
			buttonImage = new javax.swing.ImageIcon(img2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblCalculator.setIcon(buttonImage);
		calculator.setFont(fontName, fontSize);
	}
	
	public void showHideCalculator()
	{
		testpanel.setVisible(!testpanel.isVisible());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(mntmExit)) {
			exit();
		}
		if(backgroundList.contains(e.getSource()))
		{
			config.setBackgroundImage(WindowConfig.backgroundPath + ((JMenuItem)e.getSource()).getText() + ".png");
			setBackgroundImage(config.getBackgroundImage());
			//System.out.println(config.getBackgroundImage());
		}
		if(buttonImageList.contains(e.getSource()))
		{
			config.setButtonImage(WindowConfig.buttonPath + ((JMenuItem)e.getSource()).getText() + ".png");
			setButtonDesign(config.getButtonImage(), config.getFontName(), config.getFontSize());
			//System.out.println(config.getButtonImage());
		}
		if(fontList.contains(e.getSource()))
		{
			config.setFontName(((JMenuItem)e.getSource()).getText());
			setButtonDesign(config.getButtonImage(), config.getFontName(), config.getFontSize());
			//System.out.println(config.getFontName());
		}
		if(fontSizeList.contains(e.getSource()))
		{
			config.setFontSize(Integer.parseInt(((JMenuItem)e.getSource()).getText()));
			setButtonDesign(config.getButtonImage(), config.getFontName(), config.getFontSize());
			System.out.println(config.getFontSize());
		}
	}
}
