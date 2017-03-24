package com.kompeteer.web.service.business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.enumeration.GameStatus;
import com.kompeteer.web.repository.GameRepository;
import com.kompeteer.web.service.GameService;
import com.kompeteer.web.service.dto.GameDTO;

@Service
@Transactional
public class GameBS {
	private final Logger log = LoggerFactory.getLogger(GameBS.class);

	@PersistenceContext
	private EntityManager em;
	
	private final GameRepository gameRepository;
	private final GameService gameService;

	public GameBS(GameRepository gameRepository, GameService gameService) {

		this.gameRepository = gameRepository;
		this.gameService = gameService;
	}

	public Game save(GameDTO gameDTO) {
		Player originalPlayer1 = null;
		Player originalPlayer2 = null;
		
		if (gameDTO.getId() != null) {
			Game game = gameRepository.getOne(gameDTO.getId());
			
			if (gameDTO.getPlayer1Id() == null) {
				gameDTO.setPlayer1Id(game.getPlayer1().getId());
			}
			
			if (gameDTO.getPlayer2Id() == null) {
				gameDTO.setPlayer2Id(game.getPlayer2().getId());
			}
			
			if (gameDTO.getResult() == null) {
				gameDTO.setResult(game.getResult());
			}
			
			if (gameDTO.getStatus() == null) {
				gameDTO.setStatus(game.getStatus());
			}
			
			if (gameDTO.getTournamentId() == null && game.getTournament() != null) {
				gameDTO.setTournamentId(game.getTournament().getId());
			}
			
			originalPlayer1 = game.getPlayer1();
			originalPlayer2 = game.getPlayer2();
		}
		
		if (gameDTO.getResult() != null) {
			gameDTO.setStatus(GameStatus.COMPLETE);
		}

		gameDTO = gameService.save(gameDTO);
		
		em.flush();
		
		Game game = gameRepository.getOne(gameDTO.getId());
		
		em.refresh(game);
		
		Player player1 = game.getPlayer1();
		Player player2 = game.getPlayer2();
		
		em.refresh(player1);
		em.refresh(player2);
		
		if (originalPlayer1 != null && !player1.equals(originalPlayer1)) {
			em.refresh(originalPlayer1);
		}
		
		if (originalPlayer2 != null && !player2.equals(originalPlayer2)) {
			em.refresh(originalPlayer2);
		}
		
		return game;
	}
	
	public Game delete(long gameId) {
		Game game = gameRepository.getOne(gameId);
		
		Player player1 = game.getPlayer1();
		Player player2 = game.getPlayer2();
		
		gameRepository.delete(gameId);
		
		em.flush();
		
		em.refresh(player1);
		em.refresh(player2);
		
		return game;
	}
}
