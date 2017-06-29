package cn.qblank.snake;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

/**
 * 贪吃蛇
 * 
 * @author 岳某
 * 
 */
public class SnakeGame2 {
	// 地图的宽(列数)
	public static final int WIDTH = 35;

	// 地图的高
	public static final int HEIGHT = 8;

	// 地图
	private char[][] background = new char[HEIGHT][WIDTH];

	// 使用集合保存所有信息
	LinkedList<Point> snake = new LinkedList<>();

	// 食物
	Point food;

	/**
	 * 生成食物
	 */
	public void createFood() {
		// 创建随机数
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
	 * 显示食物
	 */
	public void showFood() {
		background[food.y][food.x] = '@';
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

	/**
	 * 显示蛇
	 */
	public void showSnake() {
		// 取出蛇头
		Point head = snake.getFirst();
		background[head.y][head.x] = '$';
		// 给蛇身赋值
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			background[body.y][body.x] = '#';
		}

	}

	/**
	 * 初始化地图
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
	 * 显示地图
	 */
	public void showBackground() {
		// 打印二维数组
		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[i].length; j++) {
				System.out.print(background[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 刷新游戏
	 */
	public void refrash() {
		// 清空游戏之前的状态
		initBackground();
		// 把蛇最新的状态反馈到地图上
		showSnake();
		// 把食物的显示到地图上
		showFood();
		// 显示当前地图的信息
		showBackground();

	}

	/**
	 * 向上移动
	 */
	public void moveUp() {
		// 获取原来的蛇头
		Point head = snake.getFirst();
		// 添加新的蛇头
		snake.addFirst(new Point(head.x , head.y - 1));
		// 删除蛇尾
		snake.removeLast();

	}

	/**
	 * 向下移动
	 */
	public void moveDown() {
		// 获取原来的蛇头
		Point head = snake.getFirst();
		//添加新的蛇头
		snake.addFirst(new Point(head.x , head.y + 1));
		//删除蛇尾
		snake.removeLast();
	}

	/**
	 * 向右移动
	 */
	public void moveRight() {
		//获取蛇头
		Point head = snake.getFirst();
		//添加新的蛇头
		snake.addFirst(new Point(head.x + 1, head.y ));
		//删除蛇尾
		snake.removeLast();
	}

	/**
	 * 向左移动
	 */
	public void moveLeft() {
		//获取蛇头
		Point head = snake.getFirst();
		//添加新的蛇头
		snake.addFirst(new Point(head.x - 1, head.y ));
		//删除蛇尾
		snake.removeLast();
	}

	public static void main(String[] args) throws InterruptedException {
		SnakeGame2 snake = new SnakeGame2();
		// 初始化地图
		snake.initBackground();

		// 初始化蛇
		snake.initSnake();
		// 显示蛇
		snake.showSnake();

		// 初始化食物
		snake.createFood();

		// 把食物显示到地图上
		snake.showFood();

		snake.showBackground();
		
		//跳出循环的条件
		boolean flag = true;
		while(flag){

			// 移动蛇
			// 向上移动三步
			for (int i = 0; i < 3; i++) {
					snake.moveUp();
					snake.refrash();
					Thread.sleep(500);
			}
			
			//向右移动五步
			for (int i = 0; i < 10; i++) {
				snake.moveRight();
				snake.refrash();
				Thread.sleep(500);
				
			}
			
			//向下移动三步
			for (int i = 0; i < 3; i++) {
				snake.moveDown();
				snake.refrash();
				Thread.sleep(500);
			}
			
			//向左移动五步
			for (int i = 0; i < 10; i++) {
				snake.moveLeft();
				snake.refrash();
				Thread.sleep(500);
				
			}
			
		}

	}

}
