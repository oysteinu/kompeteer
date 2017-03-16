package com.kompeteer.web.service.impl;

import com.kompeteer.web.service.PlayerService;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Player.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Save a player.
     *
     * @param player the entity to save
     * @return the persisted entity
     */
    @Override
    public Player save(Player player) {
        log.debug("Request to save Player : {}", player);
        Player result = playerRepository.save(player);
        return result;
    }

    /**
     *  Get all the players.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Player> findAll() {
        log.debug("Request to get all Players");
        List<Player> result = playerRepository.findAllWithEagerRelationships();

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
    public Player findOne(Long id) {
        log.debug("Request to get Player : {}", id);
        Player player = playerRepository.findOneWithEagerRelationships(id);
        return player;
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
