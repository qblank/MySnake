package cn.qblank.TCPChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 客户端
 * 
 * @author Administrator
 * 
 */
public class ChatCient {
	public static void main(String[] args) throws IOException {
		// 建立tcp服务协议
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
		// 获得Socket输出流对象
		OutputStreamWriter socketOut = new OutputStreamWriter(
				socket.getOutputStream());
		// 获取Socket输入流对象
		BufferedReader socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));

		// 获取键盘输入流对象
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(
				System.in));
		String line = null;
		while ((line = keyReader.readLine()) != null) {
			socketOut.write(line + "\r\n");
			// 刷新
			socketOut.flush();
//			System.out.println("客户端数据已发出");
			//读取服务器回收的数据
			line = socketReader.readLine();
			System.out.println("服务端回送的数据为:"+line);
		}
		socket.close();

	}
}
