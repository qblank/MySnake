package cn.qblank.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.qblank.FrameTool.FrameUtil;

public class Demo1 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		// ������ɫΪ��ɫ
		g.setColor(Color.GRAY);
		// ������
		g.fill3DRect(0, 0, 30, 30, true);
		g.fill3DRect(30, 0, 30, 30, true);
		//������ɫΪ��ɫ
		g.setColor(Color.RED);
		g.fill3DRect(30, 30, 30, 30, true);
		
		
		//д��
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("Game Over", 300, 200);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("����");
		Demo1 d = new Demo1();
		frame.add(d);
		FrameUtil.initFrame(frame, 700, 500);

	}
}
