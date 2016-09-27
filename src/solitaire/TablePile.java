package solitaire;

import java.awt.Graphics;

public class TablePile extends CardPile {
	public int cnum;//该牌堆的初始数目

	public TablePile(int x, int y, int num) {
		super(x, y);
		cnum = num;
	}

	public Card get(int i) {
		return (Card) pile.get(i);//得到该牌堆的第i张牌
	}

	@Override
	public boolean canTake(Card aCard) {
		//该牌堆的接受规则为aCard的花色与当前牌堆最上面一张牌相反（红黑即相反），点数小1
		if (empty() && aCard.number == 13) {
			return true;
		} else if (empty() && aCard.number != 13) {
			return false;
		} else if ((topCard().suit == 1 || topCard().suit == 4)
				&& (aCard.suit == 2 || aCard.suit == 3)
				&& topCard().number == aCard.number + 1) {
			return true;
		} else if ((topCard().suit == 2 || topCard().suit == 3)
				&& (aCard.suit == 1 || aCard.suit == 4)
				&& topCard().number == aCard.number + 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void select(int x, int y) {
		//选中该牌堆后把某些牌加入移动牌堆
		if (!empty()) {
			int n = locate(x, y);//确定鼠标停在哪张牌上
			if (get(pile.size() - n).faceup != true) {//判断所鼠标停留的牌是否正面朝上，若正面朝上，则可以移动该牌即它上面的牌
			} else {
				Solitaire.movePile.setLocation(this.x, this.y
						+ (pile.size() - n) * 30);
				for (int i = 0; i < n; i++) {
					if (topCard().faceup) {
						Card aCard = pop();
						Solitaire.movePile.addCard(aCard);
					}
				}
			}
		}
	}

	//根据鼠标所在的位置判断选中的哪张牌
	private int locate(int tx, int ty) {
		int n = 1;
		int temp = y;
		for (int i = 0; i < pile.size() - 1; i++) {
			if (ty > temp + 30) {
				n++;
				temp = temp + 30;
			} else {
				return (pile.size() - n + 1);
			}
		}
		return (pile.size() - n + 1);
	}

	public boolean includes(int tx, int ty) {//重写了父类的方法，因为桌面牌堆画出了它所拥有的所有牌
		return x <= tx && tx <= x + Card.width && y <= ty
				&& ty <= y + 30 * (pile.size() - 1) + Card.height;
	}

	public void draw(Graphics g) {
		//若不为空，则画出所有牌
		if (empty()) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			for (int i = 0; i < pile.size(); i++) {
				((Card) pile.get(i)).draw(g, x, y + 30 * i);
			}
		}
	}

}
