package solitaire;

import java.awt.Graphics;

public class MovePile extends CardPile {

	public MovePile(int x, int y) {
		super(x, y);
	}

	/*public Card bottomCard() {
		if (!empty()) {
			return (Card) pile.get(0);
		} else {
			return null;
		}
	}*/

	//public Card getBottom() {
		//return (Card) pile.remove(0);
	//}

	@Override
	public boolean canTake(Card aCard) {//任何牌都可以加入移动牌堆，不会用到该方法
		return false;
	}

	@Override
	public void select(int x, int y) {//也不会用到这个方法
	}

	public void draw(Graphics g) {
		if (empty()) {
		} else {
			for (int i = pile.size() - 1; i >= 0; i--) {//画出所有的牌
				Card aCard = (Card) pile.get(i);
				aCard.draw(g, x, y + 30 * (pile.size() - 1 - i));
			}
		}
	}

}
