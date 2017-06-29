package cn.qblank.TCPChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �����
 * 
 * @author Administrator
 * 
 */
public class ChatServer {
	public static void main(String[] args) throws IOException {
		// ����tcp�ķ����
		ServerSocket serverSocket = new ServerSocket(9090);
		// ���տͻ��˵�����
		Socket socket = serverSocket.accept();
		// ��ȡSocket���������
		OutputStreamWriter socketOut = new OutputStreamWriter(
				socket.getOutputStream());
		// ��ȡ��������������
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(
				System.in));
		

		// ��ȡ����������
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		String line = null;
		while ((line = socketReader.readLine()) != null) {
			System.out.println("���������յ�������:" + line);
			System.out.println("��������͸��ͻ��˵�����:");
			line = keyReader.readLine();
			socketOut.write(line+"\r\n");
			socketOut.flush();
			

		}
		serverSocket.close();
	}
}
