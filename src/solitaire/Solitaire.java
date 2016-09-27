package solitaire;

import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;

public class Solitaire extends JPanel implements MouseInputListener {
	static DeckPile deckPile;//初始牌堆
	static DiscardPile discardPile;//暂置牌堆
	static TablePile tableau[];//桌面牌堆
	static SuitPile suitPile[];//目标牌堆
	static CardPile allPiles[];//所有牌堆
	static MovePile movePile;//移动牌堆
	private Point point = new Point(0, 0);//用于移动牌堆的临时点
	private CardPile home = null;//记录移动牌堆的来源

	public Solitaire() {//构造器
		setBackground(new Color(14, 85, 7));//设置背景
		addMouseListener(this);//添加监听器
		addMouseMotionListener(this);
		allPiles = new CardPile[14];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
		int deckPos = 20;//初始牌堆的位置
		int suitPos = 320;//目标牌堆的位置
		//新建牌堆
		allPiles[0] = deckPile = new DeckPile(deckPos, 5);
		allPiles[1] = discardPile = new DiscardPile(deckPos + Card.width + 20,
				5);
		allPiles[13] = movePile = new MovePile(0, 0);
		for (int i = 0; i < 4; i++)
			allPiles[2 + i] = suitPile[i] = new SuitPile(suitPos
					+ (Card.width + 30) * i, 5);
		for (int i = 0; i < 7; i++)
			allPiles[6 + i] = tableau[i] = new TablePile(30 + (Card.width + 20)
					* i, 5 + Card.height + 15, i + 1);
		//为桌面牌堆添加卡牌
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j <= i + 1; j++) {
				Card aCard = deckPile.pop();
				tableau[i].addCard(aCard);
			}
			//设桌面牌堆的第一张牌正面向上
			tableau[i].topCard().faceup = true;
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//画出每张牌
		for (int i = 0; i < 14; i++)
			allPiles[i].draw(g);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setTitle(" S o l i t a i r e ");
		Solitaire s = new Solitaire();
		frame.add(s);
		frame.validate();
		s.repaint();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		point.setLocation(x, y);//点击点，为之后movePile的移动收集位置信息
		//判断哪个牌堆被点击，并将被选中的牌加入movePile，设置movePile中牌的来源
		for (int i = 0; i < 13; i++) {
			if (allPiles[i].includes(x, y)) {//判断哪个牌堆被点击
				allPiles[i].select(x, y);//将被选中的牌加入movePile
				home = allPiles[i];//设置movePile中牌的来源
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//若movePile中有牌，在释放鼠标时将其加入相应的牌堆，可能放入鼠标所在的牌堆，可能放回原牌堆
		if (!movePile.empty()) {
			for (int i = 0; i < 13; i++) {
				if (allPiles[i].includes(x, y)) {//判断鼠标停留在哪个牌堆
					if (allPiles[i].canTake(movePile.topCard())) {//判断该牌堆是否可以接受来自movePile的牌
						int t = movePile.size();//记录当前movePile的大小
						//向牌堆添加卡牌
						for (int j = 0; j < t; j++) {
							Card aCard = movePile.pop();
							allPiles[i].addCard(aCard);
						}
						//把源牌堆的第一张牌设为正面朝上
						if (!home.empty()) {
							home.topCard().faceup = true;
						}
						home = null;
					} else {
						//若该牌堆不能接受来自movePile的牌，则将它们放回源牌堆
						int t = movePile.size();
						for (int j = 0; j < t; j++) {
							Card aCard = movePile.pop();
							home.addCard(aCard);
							home = null;
						}
					}
				}
			}
			//如果没有牌堆被选中，则把movePile中的牌放回源牌堆
			if (home != null) {
				int t = movePile.size();
				for (int j = 0; j < t; j++) {
					Card aCard = movePile.pop();
					home.addCard(aCard);
					home = null;
				}
			}
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!movePile.empty()) {
			int x = e.getX();
			int y = e.getY();
			Point newPoint = new Point(x, y);//记录当前点
			//设置movePile的位置，注意不是当前鼠标所在的位置，而是又原位置加上移动的距离
			movePile.setLocation(movePile.getX() + (newPoint.x - point.x),
					movePile.getY() + (newPoint.y - point.y));
			point = newPoint;
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
