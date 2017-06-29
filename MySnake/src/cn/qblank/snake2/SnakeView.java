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
 * 贪吃蛇2.0
 * @author 秋波同学
 *
 */
public class SnakeView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 地图的宽
	public static final int WIDTH = 40;

	// 地图的高
	public static final int HEIGHT = 30;

	// 格子高
	public static final int CELLHEIHT = 20;

	// 格子宽
	public static final int CELLWIDTH = 20;

	// 地图
	private boolean[][] background = new boolean[HEIGHT][WIDTH];

	// 使用集合保存蛇节点的所有信息
	static LinkedList<Point> snake = new LinkedList<Point>();

	// 记录蛇当前的方向
	int currentDirection = -2;

	// 定义四个常量表示四个方向
	public static final int UP_DIRECTION = 1;

	public static final int DOWN_DIRECTION = -1;

	public static final int LEFT_DIRECTION = 2;

	public static final int RIGHT_DIRECTION = -2;

	// 创建食物
	Point food;

	/**
	 * 初始化地图
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
	 * 初始化蛇
	 */
	public void initSnake() {
		int x = WIDTH / 2;
		int y = HEIGHT / 2;
		snake.addFirst(new Point(x - 1, y));
		snake.addFirst(new Point(x, y));
		snake.addFirst(new Point(x + 1, y));
	}

	// 向上移动
	/*
	 * public void moveUp() { // 获取原来蛇头 Point head = snake.getFirst(); // 添加新的蛇头
	 * snake.addFirst(new Point(head.x, head.y - 1)); // 删除蛇尾
	 * snake.removeLast(); }
	 * 
	 * //向下 public void moveDown() { // 获取原来蛇头 Point head = snake.getFirst(); //
	 * 添加新的蛇头 snake.addFirst(new Point(head.x, head.y + 1)); // 删除蛇尾
	 * snake.removeLast(); }
	 * 
	 * //向左 public void moveLeft() { // 获取原来蛇头 Point head = snake.getFirst(); //
	 * 添加新的蛇头 snake.addFirst(new Point(head.x - 1, head.y)); // 删除蛇尾
	 * snake.removeLast(); }
	 * 
	 * //向右 public void moveRight() { // 获取原来蛇头 Point head = snake.getFirst();
	 * // 添加新的蛇头 snake.addFirst(new Point(head.x + 1, head.y)); // 删除蛇尾
	 * snake.removeLast(); }
	 */

	/**
	 * 生成食物
	 */
	public void createFood() {
		// 创建随机数
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
	 * 吃食物
	 */
	public boolean eatFood() {
		// 获取蛇头
		Point head = snake.getFirst();
		System.out.println("蛇头的位置" + head.x + " " + head.y);
		System.out.println("食物的位置" + food.x + " " + food.y);
		if (head.equals(food)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否结束游戏
	 */
	public boolean isGameOver() {

		// 撞墙死亡
		Point head = snake.getFirst();
		if (background[head.y][head.x]) {
			return true;
		}

		// 咬到自己
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			if (head.equals(body)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 蛇移动的方法
	 */
	public void move() {
		// 获取蛇头
		Point head = snake.getFirst();
		switch (currentDirection) {
		// 向上
		case 1:
			// 添加蛇头
			snake.addFirst(new Point(head.x, head.y - 1));
			// System.out.println("向上");
			break;
		// 向下
		case -1:
			// 添加蛇头
			snake.addFirst(new Point(head.x, head.y + 1));
			// System.out.println("向下");
			break;
		// 向左
		case 2:
			if (head.x == 0) {
				snake.addFirst(new Point(WIDTH - 1, head.y));

			} else {
				snake.addFirst(new Point(head.x - 1, head.y));
			}

			// System.out.println("向左");
			break;
		// 向右
		case -2:
			if (head.x == WIDTH - 1) {
				snake.addFirst(new Point(0, head.y));
			} else {
				snake.addFirst(new Point(head.x + 1, head.y));

			}
			// System.out.println("向右");
			break;

		default:
			break;
		}
		// 如果吃到了食物
		if (eatFood()) {
			System.out.println("吃到了");
			// 创建新的食物
			createFood();

		} else {
			System.out.println("没吃到");
			// 删除蛇尾
			snake.removeLast();
		}

	}

	// 改变当前方向的方法
	public void changeDirection(int newDirection) {
		if (currentDirection + newDirection != 0) {
			this.currentDirection = newDirection;

		}
	}

	@Override
	public void paint(Graphics g) {
		// 画地图
		for (int row = 0; row < background.length; row++) {
			for (int cols = 0; cols < background[row].length; cols++) {
				if (background[row][cols]) {
					// 石头
					g.setColor(Color.GRAY);
				} else {
					// 空地
					g.setColor(Color.WHITE);
				}

				g.fill3DRect(cols * CELLWIDTH, row * CELLHEIHT, CELLWIDTH,
						CELLHEIHT, true);
			}
		}

		// 画蛇
		// 取出蛇头
		Point head = snake.getFirst();
		g.setColor(Color.RED);
		g.fill3DRect(head.x * CELLWIDTH, head.y * CELLHEIHT, CELLWIDTH,
				CELLHEIHT, true);
		// 画蛇身
		g.setColor(Color.GREEN);
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			g.fill3DRect(body.x * CELLWIDTH, body.y * CELLHEIHT, CELLWIDTH,
					CELLHEIHT, true);

		}

		// 画食物
		g.setColor(Color.BLUE);
		g.fill3DRect(food.x * CELLHEIHT, food.y * CELLWIDTH, CELLWIDTH,
				CELLHEIHT, true);

		// 是否结束游戏
		if (isGameOver()) {
			g.setColor(Color.RED);
			g.setFont(new Font("宋体", Font.BOLD, 50));
			g.drawString("GameOver", WIDTH * CELLWIDTH / 2 - 4, HEIGHT
					* CELLHEIHT / 2);
			// System.exit(0);
			
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("贪吃蛇");

		final SnakeView snakeView = new SnakeView();

		snakeView.initBackground();
		// 初始化地图
		snakeView.initSnake();

		// 初始化食物
		snakeView.createFood();

		frame.add(snakeView);

		FrameUtil.initFrame(frame, WIDTH * CELLWIDTH + 20, HEIGHT * CELLHEIHT
				+ 35);

		// 给窗体添加监听器
		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				// 向上
				case KeyEvent.VK_UP:
					// snakeView.moveUp();
					snakeView.changeDirection(UP_DIRECTION);
					break;
				// 向下
				case KeyEvent.VK_DOWN:
					// snakeView.moveDown();
					snakeView.changeDirection(DOWN_DIRECTION);
					break;
				// 向左
				case KeyEvent.VK_LEFT:
					// snakeView.moveLeft();
					snakeView.changeDirection(LEFT_DIRECTION);
					break;
				// 向右
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
				// 如果吃到了食物
				/*
				 * if (snakeView.eatFood()) { //创建新的食物 snakeView.createFood();
				 * 
				 * }else{ // 删除蛇尾 snake.removeLast(); }
				 */

				// 刷新画布
				snakeView.repaint();
				if (snakeView.isGameOver()) {
					
					// System.out.println("游戏结束");
					// System.exit(0);
				}
			}

		});

	}
}
