package cn.qblank.TCPChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * �ͻ���
 * 
 * @author Administrator
 * 
 */
public class ChatCient {
	public static void main(String[] args) throws IOException {
		// ����tcp����Э��
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
		// ���Socket���������
		OutputStreamWriter socketOut = new OutputStreamWriter(
				socket.getOutputStream());
		// ��ȡSocket����������
		BufferedReader socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));

		// ��ȡ��������������
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(
				System.in));
		String line = null;
		while ((line = keyReader.readLine()) != null) {
			socketOut.write(line + "\r\n");
			// ˢ��
			socketOut.flush();
//			System.out.println("�ͻ��������ѷ���");
			//��ȡ���������յ�����
			line = socketReader.readLine();
			System.out.println("����˻��͵�����Ϊ:"+line);
		}
		socket.close();

	}
}
