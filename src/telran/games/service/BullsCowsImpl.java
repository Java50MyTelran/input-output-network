package telran.games.service;

import java.util.*;

import telran.games.dto.MoveResult;

public class BullsCowsImpl implements BullsCows {
	private String secretStr;
	private List<MoveResult> history;
	private int count;
	public BullsCowsImpl(String secretStr) {
		this.secretStr = secretStr;
		history = new ArrayList<>();
	}
	@Override
	public MoveResult move(String guessStr) {
		// TODO Auto-generated method stub
		//create MoveResult according to the game algorithm
		MoveResult res = getRes(guessStr);
		history.add(res);
		count++;
		return res;
	}
	private MoveResult getRes(String guessStr) {
		Integer[] bullsCows = {0, 0};
		char[] guessStrAr = guessStr.toCharArray();
		for(int i = 0; i < guessStrAr.length; i++) {
			int indexSecret = secretStr.indexOf(guessStrAr[i]);
			if (indexSecret > -1) {
				if(i == indexSecret) {
					bullsCows[0]++;
				} else {
					bullsCows[1]++;
				}
			}
		}
		MoveResult res = new MoveResult(bullsCows, guessStr);
		return res;
	}

	@Override
	public List<MoveResult> getHistory() {
		
		return history;
	}

	@Override
	public Integer gameOver() {
		MoveResult lastResult = history.get(history.size() - 1);
		Integer res = -1;
	
		if (lastResult.bullsCows()[0] == secretStr.length()) {
			res = count;
		}
		return res;
	}

}
