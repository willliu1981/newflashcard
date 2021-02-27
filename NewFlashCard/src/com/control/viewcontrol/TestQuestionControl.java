package com.control.viewcontrol;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.model.CardBox;
import com.model.Vocabulary;

public class TestQuestionControl<T> extends ShowRowControl<T>{
	protected List<T> questions;
	protected List<T> answers;
	protected int cardboxIdx;
	protected int eventIdx;
	protected int correctAnswerRowIdx;

	public TestQuestionControl(JFrame eventJFrame) {
		super(eventJFrame);
	}

	public List<T> getQuestionResult(){
		return this.questions;
	}
	
	public List<T> getAnswerResult(){
		return this.answers;
	}
	
	public T getRandomAnswer() {
		return this.answers.get((int)(Math.random()*this.answers.size()));
	}

	public int getEventIdx() {
		return eventIdx;
	}

	public void setEventIdx(int eventIdx) {
		this.eventIdx = eventIdx;
	}

	public int getCorrectAnswerRowIdx() {
		return correctAnswerRowIdx;
	}

	public void setCorrectAnswerRowIdx(int correctAnswerRowIdx) {
		this.correctAnswerRowIdx = correctAnswerRowIdx;
	}

	public int getCardboxIdx() {
		return cardboxIdx;
	}

	public void setCardboxIdx(int cardboxIdx) {
		this.cardboxIdx = cardboxIdx;
	}
	

}
