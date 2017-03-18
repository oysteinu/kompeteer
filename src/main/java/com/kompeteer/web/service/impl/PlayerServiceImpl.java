package com.kompeteer.web.service.impl;

import com.kompeteer.web.service.PlayerService;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.repository.PlayerRepository;
import com.kompeteer.web.service.dto.PlayerDTO;
import com.kompeteer.web.service.mapper.PlayerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Player.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    
    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    /**
     * Save a player.
     *
     * @param playerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlayerDTO save(PlayerDTO playerDTO) {
        log.debug("Request to save Player : {}", playerDTO);
        Player player = playerMapper.playerDTOToPlayer(playerDTO);
        player = playerRepository.save(player);
        PlayerDTO result = playerMapper.playerToPlayerDTO(player);
        return result;
    }

    /**
     *  Get all the players.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PlayerDTO> findAll() {
        log.debug("Request to get all Players");
        List<PlayerDTO> result = playerRepository.findAllWithEagerRelationships().stream()
            .map(playerMapper::playerToPlayerDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one player by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PlayerDTO findOne(Long id) {
        log.debug("Request to get Player : {}", id);
        Player player = playerRepository.findOneWithEagerRelationships(id);
        PlayerDTO playerDTO = playerMapper.playerToPlayerDTO(player);
        return playerDTO;
    }

    /**
     *  Delete the  player by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Player : {}", id);
        playerRepository.delete(id);
    }
}
