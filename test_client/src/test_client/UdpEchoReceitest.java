package test_client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpEchoReceitest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UdpEchoReceitest().receiverUdp();
	}
	
	public void receiverUdp() {
		int myPort = 6005;
		DatagramSocket dSock = null;
		try {
			dSock = new DatagramSocket(myPort);
			
			while(true) {
				byte[] byteMsg = new byte[1000];
				DatagramPacket receivedData = new DatagramPacket(byteMsg, byteMsg.length);
				dSock.receive(receivedData);
				
				String receivedStr = new String(receivedData.getData());
				System.out.println("수신메시지 : "+ receivedStr);
				
				DatagramPacket sendData = new DatagramPacket(receivedData.getData(), receivedData.getLength(), receivedData.getAddress(), receivedData.getPort());
				dSock.send(sendData);
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
