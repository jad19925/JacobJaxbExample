package jacob.jaxb;

import javax.xml.bind.annotation.*;

//this annotation defines that this is the root element of the XML file
@XmlRootElement


public class CalcConfig {
	
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
	
	private String buttonColor;
	private String textColor;
	private String backgroundColor;
	
	public CalcConfig() {
		buttonColor = white;
		textColor = black;
		backgroundColor = gray;
	}
	
	@XmlElement
	public String getButtonColor() {
		return buttonColor;
	}
	public void setButtonColor(String color) {
		buttonColor = color;
	}
	
	@XmlElement
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String color) {
		textColor = color;
	}
	
	@XmlElement
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String color) {
		backgroundColor = color;
	}
}
