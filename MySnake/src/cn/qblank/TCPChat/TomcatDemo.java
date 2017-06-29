package cn.qblank.TCPChat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TomcatDemo extends Thread{
	Socket socket;
	public TomcatDemo(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		/*//接收客户端的连接
		Socket socket = serverSocket.accept();*/
		
		try {
			//获取Socket输出流
			OutputStream out = socket.getOutputStream();
			
			/*//获取输入流
			int len = 0;
			byte[] buf =new byte[1024];
			StringBuffer sb = new StringBuffer();*/
			/*while((len = fis.read(buf))!=-1){
				//输出数据
				sb.append(new String(buf, 0, len));
				
			}*/
			out.write("你好".getBytes());
			//关闭资源
//			fis.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		//建立tcp的服务端
		ServerSocket serverSocket = new ServerSocket(9090);
		boolean flag = true;
		while(flag){
			//建立与客户端的连接
			Socket socket = serverSocket.accept();
			new TomcatDemo(socket).start();
		}
		serverSocket.close();
		
	}
}
