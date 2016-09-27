package solitaire;

public class DiscardPile extends CardPile {

	public DiscardPile(int x, int y) {
		super(x, y);
	}

	@Override
	public void addCard(Card aCard) {
		if (aCard.faceup) {//暂置牌堆中的牌均为正面朝上
			pile.add(aCard);
		} else {
			aCard.faceup = true;
			pile.add(aCard);
		}
	}

	@Override
	public boolean canTake(Card aCard) {//不接受来自其他牌堆的牌
		return false;
	}

	@Override
	public void select(int x, int y) {
		if (!empty()) {//若该牌堆的牌被选中了，就把最上面一张牌加到movePile中，并把移动牌堆的初始位置设置为该牌堆的坐标
			Card aCard = pop();
			Solitaire.movePile.addCard(aCard);
			Solitaire.movePile.setLocation(this.x, this.y);
		}
	}

}
