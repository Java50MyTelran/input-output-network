package telran.games.service;

import java.util.List;

import telran.games.dto.MoveResult;

public interface BullsCows {
	MoveResult move(String guessStr);
	List<MoveResult> getHistory();
	Integer gameOver();//counter of moves if game is over otherwise -1
}
