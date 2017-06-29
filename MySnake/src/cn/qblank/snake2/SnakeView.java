package cn.qblank.snake2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.qblank.FrameTool.FrameUtil;
/**
 * ̰����2.0
 * @author �ﲨͬѧ
 *
 */
public class SnakeView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ��ͼ�Ŀ�
	public static final int WIDTH = 40;

	// ��ͼ�ĸ�
	public static final int HEIGHT = 30;

	// ���Ӹ�
	public static final int CELLHEIHT = 20;

	// ���ӿ�
	public static final int CELLWIDTH = 20;

	// ��ͼ
	private boolean[][] background = new boolean[HEIGHT][WIDTH];

	// ʹ�ü��ϱ����߽ڵ��������Ϣ
	static LinkedList<Point> snake = new LinkedList<Point>();

	// ��¼�ߵ�ǰ�ķ���
	int currentDirection = -2;

	// �����ĸ�������ʾ�ĸ�����
	public static final int UP_DIRECTION = 1;

	public static final int DOWN_DIRECTION = -1;

	public static final int LEFT_DIRECTION = 2;

	public static final int RIGHT_DIRECTION = -2;

	// ����ʳ��
	Point food;

	/**
	 * ��ʼ����ͼ
	 */
	public void initBackground() {
		for (int row = 0; row < background.length; row++) {
			for (int cols = 0; cols < background[row].length; cols++) {
				if (row == 0 || row == (HEIGHT - 1) || cols == (HEIGHT - 5)
						&& !(row > 7 && row <= (HEIGHT - 9)) || (row == 8
						|| row == (HEIGHT - 8)) && (cols < 15)) {
					background[row][cols] = true;
				}
			}
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

	// �����ƶ�
	/*
	 * public void moveUp() { // ��ȡԭ����ͷ Point head = snake.getFirst(); // ����µ���ͷ
	 * snake.addFirst(new Point(head.x, head.y - 1)); // ɾ����β
	 * snake.removeLast(); }
	 * 
	 * //���� public void moveDown() { // ��ȡԭ����ͷ Point head = snake.getFirst(); //
	 * ����µ���ͷ snake.addFirst(new Point(head.x, head.y + 1)); // ɾ����β
	 * snake.removeLast(); }
	 * 
	 * //���� public void moveLeft() { // ��ȡԭ����ͷ Point head = snake.getFirst(); //
	 * ����µ���ͷ snake.addFirst(new Point(head.x - 1, head.y)); // ɾ����β
	 * snake.removeLast(); }
	 * 
	 * //���� public void moveRight() { // ��ȡԭ����ͷ Point head = snake.getFirst();
	 * // ����µ���ͷ snake.addFirst(new Point(head.x + 1, head.y)); // ɾ����β
	 * snake.removeLast(); }
	 */

	/**
	 * ����ʳ��
	 */
	public void createFood() {
		// ���������
		Random random = new Random();
		while (true) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			if (!background[y][x]) {
				food = new Point(x, y);
				break;
			}

		}

	}

	/**
	 * ��ʳ��
	 */
	public boolean eatFood() {
		// ��ȡ��ͷ
		Point head = snake.getFirst();
		System.out.println("��ͷ��λ��" + head.x + " " + head.y);
		System.out.println("ʳ���λ��" + food.x + " " + food.y);
		if (head.equals(food)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * �ж��Ƿ������Ϸ
	 */
	public boolean isGameOver() {

		// ײǽ����
		Point head = snake.getFirst();
		if (background[head.y][head.x]) {
			return true;
		}

		// ҧ���Լ�
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			if (head.equals(body)) {
				return true;
			}
		}
		return false;

	}

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
			// System.out.println("����");
			break;
		// ����
		case -1:
			// �����ͷ
			snake.addFirst(new Point(head.x, head.y + 1));
			// System.out.println("����");
			break;
		// ����
		case 2:
			if (head.x == 0) {
				snake.addFirst(new Point(WIDTH - 1, head.y));

			} else {
				snake.addFirst(new Point(head.x - 1, head.y));
			}

			// System.out.println("����");
			break;
		// ����
		case -2:
			if (head.x == WIDTH - 1) {
				snake.addFirst(new Point(0, head.y));
			} else {
				snake.addFirst(new Point(head.x + 1, head.y));

			}
			// System.out.println("����");
			break;

		default:
			break;
		}
		// ����Ե���ʳ��
		if (eatFood()) {
			System.out.println("�Ե���");
			// �����µ�ʳ��
			createFood();

		} else {
			System.out.println("û�Ե�");
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

	@Override
	public void paint(Graphics g) {
		// ����ͼ
		for (int row = 0; row < background.length; row++) {
			for (int cols = 0; cols < background[row].length; cols++) {
				if (background[row][cols]) {
					// ʯͷ
					g.setColor(Color.GRAY);
				} else {
					// �յ�
					g.setColor(Color.WHITE);
				}

				g.fill3DRect(cols * CELLWIDTH, row * CELLHEIHT, CELLWIDTH,
						CELLHEIHT, true);
			}
		}

		// ����
		// ȡ����ͷ
		Point head = snake.getFirst();
		g.setColor(Color.RED);
		g.fill3DRect(head.x * CELLWIDTH, head.y * CELLHEIHT, CELLWIDTH,
				CELLHEIHT, true);
		// ������
		g.setColor(Color.GREEN);
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			g.fill3DRect(body.x * CELLWIDTH, body.y * CELLHEIHT, CELLWIDTH,
					CELLHEIHT, true);

		}

		// ��ʳ��
		g.setColor(Color.BLUE);
		g.fill3DRect(food.x * CELLHEIHT, food.y * CELLWIDTH, CELLWIDTH,
				CELLHEIHT, true);

		// �Ƿ������Ϸ
		if (isGameOver()) {
			g.setColor(Color.RED);
			g.setFont(new Font("����", Font.BOLD, 50));
			g.drawString("GameOver", WIDTH * CELLWIDTH / 2 - 4, HEIGHT
					* CELLHEIHT / 2);
			// System.exit(0);
			
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("̰����");

		final SnakeView snakeView = new SnakeView();

		snakeView.initBackground();
		// ��ʼ����ͼ
		snakeView.initSnake();

		// ��ʼ��ʳ��
		snakeView.createFood();

		frame.add(snakeView);

		FrameUtil.initFrame(frame, WIDTH * CELLWIDTH + 20, HEIGHT * CELLHEIHT
				+ 35);

		// ��������Ӽ�����
		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				// ����
				case KeyEvent.VK_UP:
					// snakeView.moveUp();
					snakeView.changeDirection(UP_DIRECTION);
					break;
				// ����
				case KeyEvent.VK_DOWN:
					// snakeView.moveDown();
					snakeView.changeDirection(DOWN_DIRECTION);
					break;
				// ����
				case KeyEvent.VK_LEFT:
					// snakeView.moveLeft();
					snakeView.changeDirection(LEFT_DIRECTION);
					break;
				// ����
				case KeyEvent.VK_RIGHT:
					// snakeView.moveRight();
					snakeView.changeDirection(RIGHT_DIRECTION);

					break;

				default:
					break;

				}
				if (snakeView.isGameOver()) {
					System.exit(0);
				}
				snakeView.move();
				// ����Ե���ʳ��
				/*
				 * if (snakeView.eatFood()) { //�����µ�ʳ�� snakeView.createFood();
				 * 
				 * }else{ // ɾ����β snake.removeLast(); }
				 */

				// ˢ�»���
				snakeView.repaint();
				if (snakeView.isGameOver()) {
					
					// System.out.println("��Ϸ����");
					// System.exit(0);
				}
			}

		});

	}
}
