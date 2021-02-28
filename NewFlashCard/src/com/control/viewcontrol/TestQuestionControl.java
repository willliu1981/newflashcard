package com.control.viewcontrol;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.model.CardBox;
import com.model.Vocabulary;

public class TestQuestionControl<T> extends ShowRowControl<T> {
	protected List<T> questions;
	protected List<T> answers;
	protected int cardboxIdx;
	protected int currentQuestionIdx;
	protected int correctAnswerRowIdx;
	protected int minRowIdx;
	protected int maxRowIdx;
	protected Stage stage;
	protected boolean bingo;
	protected boolean firstFailure;
	// random answer <row idx,question idx>

	public enum Stage {
		Guess, GetAnswer
	}

	public TestQuestionControl(JFrame eventJFrame) {
		super(eventJFrame);
		this.correctAnswerRowIdx = minRowIdx;
	}

	public List<T> getQuestionResult() {
		return this.questions;
	}

	public List<T> getAnswerResult() {
		return this.answers;
	}

	public T getRandomAnswer() {
		int idx = (int) (Math.random() * this.answers.size());
		return this.answers.get(idx);
	}

	public void init(int min, int max) {
		this.currentQuestionIdx = -1;
		this.setRowIdxRange(min, max);
		this.moveCurrentQuestionIdx();
		questionReset();
	}

	protected void questionReset() {
		this.bingo = false;
		this.firstFailure = false;
		stage = Stage.Guess;
	}

	public void setRowIdxRange(int min, int max) {
		this.minRowIdx = min;
		this.maxRowIdx = max;
	}

	public boolean clickRowInRange(int idx) {
		return idx >= minRowIdx && idx <= maxRowIdx;
	}

	public boolean isCorrectAnswer(int rowIdx) {
		boolean bingo = rowIdx == this.correctAnswerRowIdx;
		if (!bingo) {
			this.firstFailure = true;
		} else {
			this.bingo = true;
		}
		return bingo;
	}

	protected void moveCurrentQuestionIdx() {
		this.currentQuestionIdx++;
		if (this.currentQuestionIdx >= this.questions.size()) {
			this.currentQuestionIdx = -1;
		}
		this.setCorrectAnswerRowIdx(minRowIdx, maxRowIdx);
		this.bingo = false;
	}

	public int getCurrentQuestionIdx() {
		return currentQuestionIdx;
	}

//	public void setEventIdx(int eventIdx) {
//		this.eventIdx = eventIdx;
//	}

	public int getCorrectAnswerRowIdx() {
		return correctAnswerRowIdx;
	}

	protected void setCorrectAnswerRowIdx(int minRowIdx, int maxRowIdx) {
		if (minRowIdx != maxRowIdx) {
			this.correctAnswerRowIdx = (int) (Math.random() * (maxRowIdx - minRowIdx)) + minRowIdx;
		}
	}

	public int getCardboxIdx() {
		return cardboxIdx;
	}

	public void setCardboxIdx(int cardboxIdx) {
		this.cardboxIdx = cardboxIdx;
	}

	public Stage getStage() {
		return stage;
	}

	public void moveStage() {
		// this.stage +=this.stage ;
	}

	public boolean isBingo() {
		return bingo;
	}

}
