package kr.co.wizbrain.tbn.cid;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;


public class TcpAndWebClient {
	private OracleConnector oracle = null;
	
	private InputStream in;
	private Socket tcpSocket;
	int telPort;
	String telIp;
	
	byte[] bytes = null;
	String msg = "";
	
	public TcpAndWebClient() throws IOException {
		init();
		oracle = new OracleConnector();
		
		tcpSocket = new Socket();
		tcpSocket.connect(new InetSocketAddress(telIp, telPort));
		in = tcpSocket.getInputStream();
		System.out.println("--------------------------★TEL★Server 접속 성공--------------------------");
		System.out.println("---TelServer IP: " + tcpSocket.getInetAddress().getHostAddress());
		System.out.println("---TelServer Port: " + tcpSocket.getPort());

	}
	
	public String readDataFromTelServer() throws IOException, SQLException {
		bytes = new byte[1024];
		int readByteCount = in.read(bytes);
		msg = new String(bytes, 0, readByteCount, "UTF-8");
		
		String[] msgParsed = msg.split("\\|");
		String flag = msgParsed[0];
		String informerTel = msgParsed[3];
		
		String AREA_CODE = oracle.classifyAreaCodeByNum(msgParsed[1]);
		
		if(flag.equals("F")) {
			MissedCallVO vo = oracle.selectInformerBy(informerTel);
			vo.setAREA_CODE(AREA_CODE);
			oracle.insertMissedCall(vo);
			msg = "missedCall";
		} else if(flag.equals("P")) {
			msg += "|" + AREA_CODE;
		} else {
			
		}
		
		return msg;
	}
	
	public void removeClient() {
		try {
			if(in != null) {
				in.close();
			} else {
				LogMaker.TraceLog("Failed to close in");
			}
			
			if(tcpSocket != null) {
				tcpSocket.close();
			} else {
				LogMaker.TraceLog("Failed to close tcpSocket");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws IOException {
		Properties prop = ConfigLoader.loadConfigFileFrom();
		System.out.println("From property: " + prop.getProperty("telIp"));
		System.out.println("From property: " + prop.getProperty("telPort"));
		
		telIp = prop.getProperty("telIp");
		telPort = Integer.parseInt(prop.getProperty("telPort"));
	}
}


























