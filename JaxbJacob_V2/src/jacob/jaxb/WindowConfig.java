package jacob.jaxb;

import javax.xml.bind.annotation.*;

//this annotation defines that this is the root element of the XML file
@XmlRootElement


public class WindowConfig {
	
	public static final String xmlFile = "calcConfig.xml";
	public static final String white = "white";
	public static final String gray = "gray";
	public static final String black = "black";
	public static final String blue = "blue";
	public static final String cyan = "cyan";
	public static final String dark_gray = "dark_gray";
	public static final String green = "green";
	public static final String light_gray = "light_gray";
	public static final String magenta = "magenta";
	public static final String pink = "pink";
	public static final String red = "red";
	public static final String yellow = "yellow";
	
	private String buttonImage;
	private String fontName;
	private String backgroundImage;
	private int fontSize;
	public static final String backgroundPath = "res/Backdrops/";
	public static final String buttonPath = "res/Buttons/";
	
	public static final String[] fonts = {"Arial Black", "Calibri", "Cambria", "Comic Sans MS", "Lucida Sans", "Times New Roman"};
	public static final int[] fontSizes = {10, 12, 14, 16, 18, 20, 22, 24, 26};
	
	public WindowConfig() {
		buttonImage = "res/Buttons/Button.png";
		fontName = "Comic Sans MS";
		backgroundImage = "res/Backdrops/Autumn.png";
		fontSize = 20;
	}
	
	@XmlElement
	public String getButtonImage() {
		return buttonImage;
	}
	public void setButtonImage(String image) {
		buttonImage = image;
	}
	
	@XmlElement
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String font) {
		fontName = font;
	}
	
	@XmlElement
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int size) {
		fontSize = size;
	}
	
	@XmlElement
	public String getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(String image) {
		backgroundImage = image;
	}
}
