package cn.qblank.FrameTool;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
/**
 * 初始化窗体的工具
 * @author Administrator
 *
 */
public class FrameUtil {
	public static void initFrame(JFrame jFrame,int width,int height){
		jFrame.setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int h = (int) d.getHeight();
		int w=(int) d.getWidth();
		
		jFrame.setBounds((w-width)/2, (h-height)/2, width, height);
		
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
