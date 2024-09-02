package kr.co.wizbrain.tbn.cid;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogMaker {
	//private final static String logDir = "Z:\\log\\";
	private final static String logDir = "./logs/";
	private final static String logFileName = "CID_CLIENT";
	private static FileWriter objfile = null;

    public static void TraceLog(String log) {
        String stPath = "";
        String stFileName = "";

        stPath     = logDir;
        stFileName = logFileName;
        SimpleDateFormat yearMonthDate = new SimpleDateFormat ("yyyyMMdd");
        SimpleDateFormat hourMinSec = new SimpleDateFormat ("HH:mm:ss");
       
        String stDate = yearMonthDate.format(new Date());
        String stTime = hourMinSec.format(new Date());
        StringBuffer bufLogPath = new StringBuffer();      
                     bufLogPath.append(stPath);
                     bufLogPath.append(stFileName);
                     bufLogPath.append("_");
                     bufLogPath.append(stDate);
                     bufLogPath.append(".log") ;
        StringBuffer bufLogMsg = new StringBuffer();
			         bufLogMsg.append("[");
			         bufLogMsg.append(stTime);
			         bufLogMsg.append("]\r\n");            
			         bufLogMsg.append(log);
                    
        try{
	        objfile = new FileWriter(bufLogPath.toString(), true);
	        objfile.write(bufLogMsg.toString());
	        objfile.write("\r\n");
        } catch(IOException e){
           
        } finally{
        	try{
	        	objfile.close();
	        }catch(Exception e1){
	        	
	        }
        }
    }

}
















