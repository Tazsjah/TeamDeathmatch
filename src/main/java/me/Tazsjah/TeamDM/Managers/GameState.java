package me.Tazsjah.TeamDM.Managers;

public enum GameState {
	
	// These are the states in which the game is in
	
	HALT, WAITING, COUNTING, GAME, SLAYERSW, DRAW, DRAGONW, END;
	
	private static GameState state;
	
	public static void setState(GameState state) {
		GameState.state = state;
	}
	
	public static boolean isState(GameState state) {
		return GameState.state == state;
	}
	
	public static GameState getState() {
		return state;
	}

}
