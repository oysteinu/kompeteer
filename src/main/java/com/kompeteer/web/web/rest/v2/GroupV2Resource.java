package com.kompeteer.web.web.rest.v2;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.app.PlayerRating;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.dto.GameDTO;
import com.kompeteer.web.service.mapper.GameMapper;

@RestController
@RequestMapping("/api/v2")
@Transactional
public class GroupV2Resource {
	private final Logger log = LoggerFactory.getLogger(GroupV2Resource.class);
	
	private final GroupBS groupBS;
	private final GameMapper gameMapper;
	
	public GroupV2Resource(GroupBS groupBS, GameMapper gameMapper) {
		this.groupBS = groupBS;
		this.gameMapper = gameMapper;
	}

	@GetMapping("/groups/{groupId}/games")
	@Timed
	public List<GameDTO> getGames(@PathVariable("groupId") long groupId) throws URISyntaxException {

		List<Game> games = groupBS.getGames(groupId);
				
		return gameMapper.gamesToGameDTOs(games);
	}
	
	@GetMapping("/groups/{groupId}/ratings")
	@Timed
	public List<PlayerRating> getRatings(@PathVariable("groupId") long groupId) throws URISyntaxException {
		return groupBS.getRatings(groupId);
	}
}
