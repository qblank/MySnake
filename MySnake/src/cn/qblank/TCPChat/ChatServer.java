package cn.qblank.TCPChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 
 * @author Administrator
 * 
 */
public class ChatServer {
	public static void main(String[] args) throws IOException {
		// 建立tcp的服务端
		ServerSocket serverSocket = new ServerSocket(9090);
		// 接收客户端的连接
		Socket socket = serverSocket.accept();
		// 获取Socket输出流对象
		OutputStreamWriter socketOut = new OutputStreamWriter(
				socket.getOutputStream());
		// 获取键盘输入流对象
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(
				System.in));
		

		// 获取输入流对象
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		String line = null;
		while ((line = socketReader.readLine()) != null) {
			System.out.println("服务器接收到的数据:" + line);
			System.out.println("请输入回送给客户端的数据:");
			line = keyReader.readLine();
			socketOut.write(line+"\r\n");
			socketOut.flush();
			

		}
		serverSocket.close();
	}
}
