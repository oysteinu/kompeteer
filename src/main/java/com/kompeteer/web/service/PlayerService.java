package com.kompeteer.web.service;

import com.kompeteer.web.service.dto.PlayerDTO;
import java.util.List;

/**
 * Service Interface for managing Player.
 */
public interface PlayerService {

    /**
     * Save a player.
     *
     * @param playerDTO the entity to save
     * @return the persisted entity
     */
    PlayerDTO save(PlayerDTO playerDTO);

    /**
     *  Get all the players.
     *  
     *  @return the list of entities
     */
    List<PlayerDTO> findAll();

    /**
     *  Get the "id" player.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PlayerDTO findOne(Long id);

    /**
     *  Delete the "id" player.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
