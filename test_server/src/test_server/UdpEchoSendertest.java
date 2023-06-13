package test_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpEchoSendertest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UdpEchoSendertest().senderUdp();
	}
	
	public void senderUdp() {
		int myPort = 5005;
		int destPort = 6005;
		String destName = "localhost";
		
		DatagramSocket dSock = null;
		BufferedReader br = null;
		
		try {
			// DatagramSocket 객체 생성
			dSock = new DatagramSocket(myPort);
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				System.out.println("입력>>");
				String sendMsg = br.readLine();
				
				if(sendMsg.equals("exit")) {
					break;
				}
				
				InetAddress destIp = null;
				
				try {
					destIp = InetAddress.getByName(destName);
					byte[] byteMsg = sendMsg.getBytes();
					DatagramPacket sendData = new DatagramPacket(byteMsg, byteMsg.length, destIp, destPort);
					
					dSock.send(sendData);	
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				byte[] byteMsg = new byte[1000];
				DatagramPacket receivedData = new DatagramPacket(byteMsg, byteMsg.length);
				dSock.receive(receivedData);
				String receivedStr = new String(receivedData.getData());
				System.out.println("Echo메시지 : "+ receivedStr);
			
		}
		} catch(SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(dSock != null) dSock.close();
 			
		}
		
	}
	

}
