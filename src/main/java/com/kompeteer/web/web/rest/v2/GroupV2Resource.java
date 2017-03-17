package com.kompeteer.web.web.rest.v2;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.app.PlayerRating;
import com.kompeteer.web.service.GroupsService;
import com.kompeteer.web.service.business.GroupBS;

@RestController
@RequestMapping("/api/v2")
@Transactional
public class GroupV2Resource {
	private final Logger log = LoggerFactory.getLogger(GroupV2Resource.class);
	
	private final GroupsService groupsService;
	private final GroupBS groupBS;
	
	public GroupV2Resource(GroupsService groupsService, GroupBS groupBS) {		
		this.groupsService = groupsService;
		this.groupBS = groupBS;
	}

	@GetMapping("/groups/{groupId}/games")
	@Timed
	public Set<Game> getGames(@PathVariable("groupId") long groupId) throws URISyntaxException {

		Groups group = groupsService.findOne(groupId); 
				
		return groupBS.getGames(group);
	}
	
	@GetMapping("/groups/{groupId}/ratings")
	@Timed
	public List<PlayerRating> getRatings(@PathVariable("groupId") long groupId) throws URISyntaxException {

		Groups group = groupsService.findOne(groupId); 
				
		return groupBS.getRatings(group);
	}
}
