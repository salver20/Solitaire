package solitaire;

public class DiscardPile extends CardPile {

	public DiscardPile(int x, int y) {
		super(x, y);
	}

	@Override
	public void addCard(Card aCard) {
		if (aCard.faceup) {//�����ƶ��е��ƾ�Ϊ���泯��
			pile.add(aCard);
		} else {
			aCard.faceup = true;
			pile.add(aCard);
		}
	}

	@Override
	public boolean canTake(Card aCard) {//���������������ƶѵ���
		return false;
	}

	@Override
	public void select(int x, int y) {
		if (!empty()) {//�����ƶѵ��Ʊ�ѡ���ˣ��Ͱ�������һ���Ƽӵ�movePile�У������ƶ��ƶѵĳ�ʼλ������Ϊ���ƶѵ�����
			Card aCard = pop();
			Solitaire.movePile.addCard(aCard);
			Solitaire.movePile.setLocation(this.x, this.y);
		}
	}

}
