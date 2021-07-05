package com.control.viewcontrol;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;

public class TestQuestionControl<T> extends ShowRowControl<T> {
	public final static Color Color_Correct = Color.green;
	public final static Color Color_Failure = Color.orange;
	public final static Color Color_Base = Color.lightGray;
	protected List<T> questions;
	protected List<T> answers;
	protected List<T> reviews;
	protected int cardboxIdx;
	protected int currentQuestionIdx;
	protected int correctAnswerRowIdx;
	protected int minRowIdx;
	protected int maxRowIdx;
	protected Stage stage;
	protected boolean bingo;
	protected boolean firstFailure;
	protected boolean isReview;
	protected boolean isTesting;
	protected int originQuestionsQuantity;
	// random answer <row idx,question idx>

	public enum Stage {
		ShowQuestion(0), Guess(1), GotAnswer(2);

		private int v;

		Stage(int v) {
			this.v = v;
		}

		public int getValue() {
			return v;
		}

		public Stage next() {
			return Stage.values()[this.v + 1];
		}

	}

	public TestQuestionControl(JFrame eventJFrame) {
		super(eventJFrame);
		this.correctAnswerRowIdx = minRowIdx;
		this.reviews = new ArrayList<>();
	}

	public List<T> getQuestionResult() {
		return this.questions;
	}

	public T getCurrentQueation() {
		return this.questions.get(this.currentQuestionIdx);
	}

	public List<T> getAnswerResult() {
		return this.answers;
	}

	public void addReviewsVocabulary(T t) {
		if (!this.reviews.contains(t)) {
			this.reviews.add(t);
		}
	}

	public List<T> getReviews() {
		return this.reviews;
	}

	public void setQuestionFromReviews() {
		this.questions = this.reviews.stream().collect(Collectors.toList());
	}

	public boolean reviewIsEmpty() {
		return this.reviews.isEmpty();
	}

	public void clearReviews() {
		this.reviews.clear();
	}

	public void startReview() {
		this.init();
	}

	public T getRandomAnswer() {
		// 這個方法可能會被覆寫
		int idx = (int) (Math.random() * this.answers.size());
		return this.answers.get(idx);
	}

	public void initialize(int min, int max) {
		this.init(min, max);
		this.isReview = false;
		this.isTesting = true;
	}

	protected void init() {
		this.init(this.minRowIdx, this.maxRowIdx);
	}

	public void init(int min, int max) {
		this.currentQuestionIdx = -1;
		this.setRowIdxRange(min, max);
		questionReset();
		this.clearReviews();
	}

	public boolean isLastQuestion() {
		return this.getCurrentQuestionIdx() == this.questions.size() - 1;
	}

	public void questionReset() {
		this.bingo = false;
		this.firstFailure = false;
		stage = Stage.values()[0];
		this.moveCurrentQuestionIdx();
	}

	public void setRowIdxRange(int min, int max) {
		this.minRowIdx = min;
		this.maxRowIdx = max;
	}

	public boolean clickAnswerRowInRange(int idx) {
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

	public int getCorrectAnswerRowIdx() {
		return correctAnswerRowIdx;
	}

	protected void setCorrectAnswerRowIdx(int minRowIdx, int maxRowIdx) {
		if (minRowIdx != maxRowIdx) {
			this.correctAnswerRowIdx = (int) (Math.random() * (maxRowIdx - minRowIdx + 1)) + minRowIdx;
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

	public void nextStage() {
		this.stage = this.stage.next();
	}

	public boolean isBingo() {
		return bingo;
	}

	public void setFirstFailure() {
		this.firstFailure = true;
	}

	public boolean isFirstFailure() {
		return firstFailure;
	}

	public boolean isReview() {
		return isReview;
	}

	public void setReview() {
		this.isReview = true;
	}

	public boolean isTesting() {
		return isTesting;
	}

	public void endTest() {
		this.isTesting = false;
	}

	public int getOriginQuestionQuantity() {
		return this.originQuestionsQuantity;
	}
}
