package com.hool.app.service;

import java.lang.Math;
import java.lang.System;
import java.util.*;

public class Deck {
	private boolean cards[];

	public Deck() {
		cards = new boolean[52];
	}

	public Deck(Deck aDeck) {
		for (int i = 0; i < 51; i++)
			cards[i] = aDeck.getCard(i);

	}

	public Hand getRandomHand() {
		Hand hand_ = new Hand();
		Random rn = new Random();
		int chosen_card;
		int number_of_cards_remaining = getCardsRemaining();
		for (int i = 0; i < 13; i++) {
			chosen_card = getNthRemainingCard((int) Math.floor(rn.nextDouble() * (number_of_cards_remaining - i)));
			hand_.setCard(chosen_card);
			setCard(chosen_card);
		}
		return hand_;
	}

	public Hand getRandomHand(Hand chosenCards) {
		Random rn = new Random();
		Hand hand_ = new Hand(chosenCards);
		int chosen_card;
		while (hand_.getNumberOfCards() < 13) {

			chosen_card = getNthRemainingCard((int) Math.floor(rn.nextDouble() * getCardsRemaining()));
			hand_.setCard(chosen_card);
			setCard(chosen_card);
		}
		return hand_;
	}

	private boolean setCard(int card) {
		return ((!cards[card]) && (cards[card] = true));

	}

	public void setCards(Hand _hand) {
		for (int i = 0; i < 52; i++)
			if (_hand.getCard(i))
				cards[i] = true;

	}

	private boolean getCard(int card) {
		return cards[card];

	}

	public void replaceCards(Hand _hand) {
		for (int i = 0; i < 52; i++)
			if (_hand.getCard(i))
				cards[i] = false;

	}

	public void resetDeck() {
		for (int i = 0; i < 52; i++)
			cards[i] = false;
	}

	private int getCardsRemaining() {
		int card_numbers = 52;
		for (int i = 0; i < 52; i++)
			if (cards[i])
				card_numbers--;
		return card_numbers;
	}

	private int getNthRemainingCard(int card_number) {
		int used_cards = 0;
		for (int i = 0; i < card_number + 1; i++)
			while (cards[i + used_cards]) {
				++used_cards;
			}

		return card_number + used_cards;

	}

}
