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
		// 设置颜色为灰色
		g.setColor(Color.GRAY);
		// 画矩形
		g.fill3DRect(0, 0, 30, 30, true);
		g.fill3DRect(30, 0, 30, 30, true);
		//设置颜色为红色
		g.setColor(Color.RED);
		g.fill3DRect(30, 30, 30, 30, true);
		
		
		//写字
		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.BOLD, 30));
		g.drawString("Game Over", 300, 200);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("测试");
		Demo1 d = new Demo1();
		frame.add(d);
		FrameUtil.initFrame(frame, 700, 500);

	}
}
