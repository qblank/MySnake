package cn.qblank.snake;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

/**
 * ̰����
 * 
 * @author ��ĳ
 * 
 */
public class SnakeGame2 {
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
				if (row == 0 || row == (HEIGHT - 1) || cols == 0
						|| cols == (WIDTH - 1)) {
					background[row][cols] = '*';
				} else {
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
	public void moveUp() {
		// ��ȡԭ������ͷ
		Point head = snake.getFirst();
		// ����µ���ͷ
		snake.addFirst(new Point(head.x , head.y - 1));
		// ɾ����β
		snake.removeLast();

	}

	/**
	 * �����ƶ�
	 */
	public void moveDown() {
		// ��ȡԭ������ͷ
		Point head = snake.getFirst();
		//����µ���ͷ
		snake.addFirst(new Point(head.x , head.y + 1));
		//ɾ����β
		snake.removeLast();
	}

	/**
	 * �����ƶ�
	 */
	public void moveRight() {
		//��ȡ��ͷ
		Point head = snake.getFirst();
		//����µ���ͷ
		snake.addFirst(new Point(head.x + 1, head.y ));
		//ɾ����β
		snake.removeLast();
	}

	/**
	 * �����ƶ�
	 */
	public void moveLeft() {
		//��ȡ��ͷ
		Point head = snake.getFirst();
		//����µ���ͷ
		snake.addFirst(new Point(head.x - 1, head.y ));
		//ɾ����β
		snake.removeLast();
	}

	public static void main(String[] args) throws InterruptedException {
		SnakeGame2 snake = new SnakeGame2();
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
		
		//����ѭ��������
		boolean flag = true;
		while(flag){

			// �ƶ���
			// �����ƶ�����
			for (int i = 0; i < 3; i++) {
					snake.moveUp();
					snake.refrash();
					Thread.sleep(500);
			}
			
			//�����ƶ��岽
			for (int i = 0; i < 10; i++) {
				snake.moveRight();
				snake.refrash();
				Thread.sleep(500);
				
			}
			
			//�����ƶ�����
			for (int i = 0; i < 3; i++) {
				snake.moveDown();
				snake.refrash();
				Thread.sleep(500);
			}
			
			//�����ƶ��岽
			for (int i = 0; i < 10; i++) {
				snake.moveLeft();
				snake.refrash();
				Thread.sleep(500);
				
			}
			
		}

	}

}
