package solitaire;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public abstract class CardPile {
	protected List pile;//牌的存储结构
	protected int x;//牌堆的x坐标
	protected int y;//牌堆的y坐标

	public CardPile(int x, int y) {
		pile = new LinkedList();
		this.x = x;
		this.y = y;
	}

	public int size() {//返回牌堆的牌数
		return pile.size();
	}

	public void setLocation(int x, int y) {//设置牌堆的位置
		this.x = x;
		this.y = y;
	}

	public boolean empty() {//返回牌堆是否为空
		return pile.isEmpty();
	}

	public Card topCard() {//得到牌堆最上面一张牌
		if (!empty())
			return (Card) pile.get(pile.size() - 1);
		else
			return null;
	}

	public Card pop() {//得到牌堆最上面的一张牌
		if (!empty())
			return (Card) pile.remove(pile.size() - 1);
		else
			return null;
	}

	public boolean includes(int tx, int ty) {//判断鼠标是否停留在该牌堆上
		return x <= tx && tx <= x + Card.width && y <= ty
				&& ty <= y + Card.height;
	}

	public void addCard(Card aCard) {//向牌堆中加入一张牌
		pile.add(aCard);
	}

	public void draw(Graphics g) {//画出该牌堆
		if (empty()) {//如果牌堆为空，则画一个卡牌大小的矩形
			g.drawRect(x, y, Card.width, Card.height);
		} else
			//画出最上面一张牌
			topCard().draw(g, x, y);
	}

	public int getX() {//得到牌堆的x坐标
		return x;
	}

	public int getY() {//得到牌堆的y坐标
		return y;
	}

	public abstract boolean canTake(Card aCard);//判断牌堆是否可以加入aCard这张牌

	public abstract void select(int x, int y);//选中牌堆后的操作
}
