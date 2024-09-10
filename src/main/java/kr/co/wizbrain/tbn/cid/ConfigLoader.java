package kr.co.wizbrain.tbn.cid;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	private static final String configPath = "C:\\Users\\MinKyeong Kim\\Desktop\\CID_Client\\conf\\cid_config.properties";
	//private final static String configPath = "./conf/cid_config.properties";
	
	public static Properties loadConfigFileFrom() throws IOException {
		Properties prop = new Properties();
		File file = new File(configPath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileInputStream fi = new FileInputStream(configPath);
		prop.load(new BufferedInputStream(fi));
		fi.close();
		return prop;
	}
}
