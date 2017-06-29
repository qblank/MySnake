package cn.qblank.snake;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import cn.qblank.FrameTool.FrameUtil;

/**
 * ̰����
 * 
 * @author ��ĳ
 * 
 */
public class SnakeGame3 {
	// ��ͼ�Ŀ�(����)
	public static final int WIDTH = 35;

	// ��ͼ�ĸ�
	public static final int HEIGHT = 8;

	// ��ͼ
	private char[][] background = new char[HEIGHT][WIDTH];

	// ʹ�ü��ϱ���������Ϣ
	LinkedList<Point> snake = new LinkedList<>();

	// ʳ��
	Point food;
	
	

	// �����ĸ�������ʾ�ĸ�����
	public static final int UP_DIRECTION = 1;

	public static final int DOWN_DIRECTION = -1;

	public static final int LEFT_DIRECTION = 2;

	public static final int RIGHT_DIRECTION = -2;

	// ��¼�ߵ�ǰ�ķ���
	int currentDirection = -2;
	
	//�ж���Ϸ�Ƿ����	Ĭ��Ϊfalse����Ϸû�н���
	static boolean isGameOver = false;

	/**
	 * ���ƶ��ķ���
	 */
	public void move() {
		// ��ȡ��ͷ
		Point head = snake.getFirst();
		switch (currentDirection) {
		// ����
		case 1:
			// �����ͷ
			snake.addFirst(new Point(head.x, head.y - 1));
			System.out.println("����");
			break;
		// ����
		case -1:
			// �����ͷ
			snake.addFirst(new Point(head.x, head.y + 1));
			System.out.println("����");
			break;
		// ����
		case 2:
			if (head.x == 0) {
				snake.addFirst(new Point(WIDTH - 1, head.y ));
				
			}else{
				snake.addFirst(new Point(head.x - 1, head.y));
			}
			
			System.out.println("����");
			break;
		// ����
		case -2:
			if (head.x == WIDTH - 1) {
				snake.addFirst(new Point(0 , head.y));
			}else{
				snake.addFirst(new Point(head.x + 1, head.y));
				
			}
			System.out.println("����");
			break;

		default:
			break;
		}
		//����Ե���ʳ��
		if (eatFood()) {
			//�����µ�ʳ��
			createFood();
			
		}else{
			// ɾ����β
			snake.removeLast();
		}
		

	}

	// �ı䵱ǰ����ķ���
	public void changeDirection(int newDirection) {
		if (currentDirection + newDirection != 0) {
			this.currentDirection = newDirection;

		}
	}
	
	/**
	 * �ж��Ƿ������Ϸ
	 */
	public void isGameOver(){
		
		//ײǽ����
		Point head = snake.getFirst();
		if (background[head.y][head.x] == '*') {
			isGameOver = true;
		}
			
		//ҧ���Լ�
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			if (head.equals(body)) {
				isGameOver = true;
			}
		}
		
		
	}

	/**
	 * ����ʳ��
	 */
	public void createFood() {
		// ���������
		Random random = new Random();
		while (true) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			if (background[y][x] != '*') {
				food = new Point(x, y);
				break;
			}

		}

	}

	/**
	 * ��ʾʳ��
	 */
	public void showFood() {
		background[food.y][food.x] = '@';
	}
	
	
	/**
	 * ��ʳ��
	 */
	public boolean eatFood(){
		//��ȡ��ͷ
		Point head = snake.getFirst();
		if (head.equals(food)) {
			return true;
		}else{
			return false;
		}
		
	}

	/**
	 * ��ʼ����
	 */
	public void initSnake() {
		int x = WIDTH / 2;
		int y = HEIGHT / 2;
		snake.addFirst(new Point(x - 1, y));
		snake.addFirst(new Point(x, y));
		snake.addFirst(new Point(x + 1, y));
	}

	/**
	 * ��ʾ��
	 */
	public void showSnake() {
		// ȡ����ͷ
		Point head = snake.getFirst();
		background[head.y][head.x] = '$';
		// ������ֵ
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			background[body.y][body.x] = '#';
		}

	}

	/**
	 * ��ʼ����ͼ
	 */
	public void initBackground() {
		for (int row = 0; row < background.length; row++) {
			for (int cols = 0; cols < background[row].length; cols++) {
				if (row == 0 || row == (HEIGHT - 1)) {
					background[row][cols] = '*';
				} else if(cols == 0 || cols == (WIDTH - 1)){
					background[row][cols] = '|';
				}else{
					background[row][cols] = ' ';
				}
			}
		}
	}

	/**
	 * ��ʾ��ͼ
	 */
	public void showBackground() {
		// ��ӡ��ά����
		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[i].length; j++) {
				System.out.print(background[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * ˢ����Ϸ
	 */
	public void refrash() {
		// �����Ϸ֮ǰ��״̬
		initBackground();
		// �������µ�״̬��������ͼ��
		showSnake();
		// ��ʳ�����ʾ����ͼ��
		showFood();
		// ��ʾ��ǰ��ͼ����Ϣ
		showBackground();

	}

	/**
	 * �����ƶ�
	 */
	/*
	 * public void moveUp() { // ��ȡԭ������ͷ Point head = snake.getFirst(); //
	 * ����µ���ͷ snake.addFirst(new Point(head.x , head.y - 1)); // ɾ����β
	 * snake.removeLast();
	 * 
	 * }
	 */

	/**
	 * �����ƶ�
	 */
	/*
	 * public void moveDown() { // ��ȡԭ������ͷ Point head = snake.getFirst();
	 * //����µ���ͷ snake.addFirst(new Point(head.x , head.y + 1)); //ɾ����β
	 * snake.removeLast(); }
	 */

	/**
	 * �����ƶ�
	 */
	/*
	 * public void moveRight() { //��ȡ��ͷ Point head = snake.getFirst(); //����µ���ͷ
	 * snake.addFirst(new Point(head.x + 1, head.y )); //ɾ����β
	 * snake.removeLast(); }
	 */

	/**
	 * �����ƶ�
	 */
	/*
	 * public void moveLeft() { //��ȡ��ͷ Point head = snake.getFirst(); //����µ���ͷ
	 * snake.addFirst(new Point(head.x - 1, head.y )); //ɾ����β
	 * snake.removeLast(); }
	 */

	public static void main(String[] args) throws InterruptedException {
		final SnakeGame3 snake = new SnakeGame3();
		// ��ʼ����ͼ
		snake.initBackground();

		// ��ʼ����
		snake.initSnake();
		// ��ʾ��
		snake.showSnake();

		// ��ʼ��ʳ��
		snake.createFood();

		// ��ʳ����ʾ����ͼ��
		snake.showFood();

		snake.showBackground();

		JFrame frame = new JFrame("������");
		frame.add(new JButton("��"), BorderLayout.NORTH);
		frame.add(new JButton("��"), BorderLayout.SOUTH);
		frame.add(new JButton("��"), BorderLayout.WEST);
		frame.add(new JButton("��"), BorderLayout.EAST);
		JButton button = new JButton("������Ʒ���");
		frame.add(button);
		FrameUtil.initFrame(frame, 300, 300);
		
		//Ϊ��ť����¼�
		button.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
					//��
					case KeyEvent.VK_UP:
						snake.changeDirection(UP_DIRECTION);
						break;
					//��
					case KeyEvent.VK_DOWN:
						snake.changeDirection(DOWN_DIRECTION);
						break;
					//��
					case KeyEvent.VK_LEFT:
						snake.changeDirection(LEFT_DIRECTION);
						break;
					//��
					case KeyEvent.VK_RIGHT:
						snake.changeDirection(RIGHT_DIRECTION);
						break;
	
					default:
						break;
					}
					
					snake.move();
					snake.isGameOver();
					snake.refrash();
					//�ж���Ϸ�Ƿ����
					if (isGameOver) {
						System.out.println("��Ϸ����");
						System.exit(0);
						
					}
				
			}
			
		});

		// ����ѭ��������
		/*
		 * boolean flag = true; while(flag){
		 */

		// �ƶ���
		// �����ƶ�����
		/*
		 * for (int i = 0; i < 3; i++) { snake.moveUp(); snake.refrash();
		 * Thread.sleep(500); }
		 */

		// �����ƶ��岽
		/*
		 * for (int i = 0; i < 10; i++) { snake.moveRight(); snake.refrash();
		 * Thread.sleep(500);
		 * 
		 * }
		 */

		// �����ƶ�����
		/*
		 * for (int i = 0; i < 3; i++) { snake.moveDown(); snake.refrash();
		 * Thread.sleep(500); }
		 */

		// �����ƶ��岽
		/*
		 * for (int i = 0; i < 10; i++) { snake.moveLeft(); snake.refrash();
		 * Thread.sleep(500);
		 * 
		 * }
		 */

		// }

	}
}
