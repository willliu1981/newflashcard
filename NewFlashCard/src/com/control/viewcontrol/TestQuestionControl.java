package com.control.viewcontrol;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.model.CardBox;
import com.model.Vocabulary;

public class TestQuestionControl<T> extends ShowRowControl<T> {
	protected List<T> questions;
	protected List<T> answers;
	protected int cardboxIdx;
	protected int eventIdx;
	protected int correctAnswerRowIdx;
	protected int minRowIdx;
	protected int maxRowIdx;

	public TestQuestionControl(JFrame eventJFrame) {
		super(eventJFrame);
		this.correctAnswerRowIdx=minRowIdx;
	}

	public List<T> getQuestionResult() {
		return this.questions;
	}

	public List<T> getAnswerResult() {
		return this.answers;
	}

	public T getRandomAnswer() {
		return this.answers.get((int) (Math.random() * this.answers.size()));
	}

	public void init(int min,int max) {
		this.eventIdx = -1;
		this.setRowIdxRange(min, max);
		this.moveEventIdx();
	}
	
	public void setRowIdxRange(int min,int max) {
		this.minRowIdx=min;
		this.maxRowIdx=max;
	}

	protected void moveEventIdx() {
		this.eventIdx++;
		if (this.eventIdx >= this.questions.size()) {
			this.eventIdx = -1;
		}
		this.setCorrectAnswerRowIdx(minRowIdx, maxRowIdx);
	}

	public int getEventIdx() {
		return eventIdx;
	}

//	public void setEventIdx(int eventIdx) {
//		this.eventIdx = eventIdx;
//	}

	public int getCorrectAnswerRowIdx() {
		return correctAnswerRowIdx;
	}


	protected void setCorrectAnswerRowIdx(int minRowIdx, int maxRowIdx) {
		if(minRowIdx!=maxRowIdx) {
			this.correctAnswerRowIdx=(int) (Math.random() * (maxRowIdx - minRowIdx)) + minRowIdx;
		}
	}

	public int getCardboxIdx() {
		return cardboxIdx;
	}

	public void setCardboxIdx(int cardboxIdx) {
		this.cardboxIdx = cardboxIdx;
	}

}
