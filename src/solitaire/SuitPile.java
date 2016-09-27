package solitaire;

public class SuitPile extends CardPile {

	public SuitPile(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean canTake(Card aCard) {
		//目标牌堆的接受规则为花色相同，且将要加入的牌比当前最上面一张牌的点数大1
		if (empty() && aCard.number == 1) {
			return true;
		} else if (empty() && aCard.number != 1) {
			return false;
		} else if (topCard().suit == aCard.suit
				&& topCard().number == aCard.number - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void select(int x, int y) {
		if (!empty()) {//如果该牌堆被选中，则把最上面一张牌加入移动牌堆，并把移动牌堆的初始位置设为该牌堆所在位置
			Card aCard = pop();
			Solitaire.movePile.addCard(aCard);
			Solitaire.movePile.setLocation(this.x, this.y);
		}
	}

}
