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
		/*//���տͻ��˵�����
		Socket socket = serverSocket.accept();*/
		
		try {
			//��ȡSocket�����
			OutputStream out = socket.getOutputStream();
			
			/*//��ȡ������
			int len = 0;
			byte[] buf =new byte[1024];
			StringBuffer sb = new StringBuffer();*/
			/*while((len = fis.read(buf))!=-1){
				//�������
				sb.append(new String(buf, 0, len));
				
			}*/
			out.write("���".getBytes());
			//�ر���Դ
//			fis.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		//����tcp�ķ����
		ServerSocket serverSocket = new ServerSocket(9090);
		boolean flag = true;
		while(flag){
			//������ͻ��˵�����
			Socket socket = serverSocket.accept();
			new TomcatDemo(socket).start();
		}
		serverSocket.close();
		
	}
}
