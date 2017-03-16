package com.kompeteer.web.service;

import com.kompeteer.web.domain.Player;
import java.util.List;

/**
 * Service Interface for managing Player.
 */
public interface PlayerService {

    /**
     * Save a player.
     *
     * @param player the entity to save
     * @return the persisted entity
     */
    Player save(Player player);

    /**
     *  Get all the players.
     *  
     *  @return the list of entities
     */
    List<Player> findAll();

    /**
     *  Get the "id" player.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Player findOne(Long id);

    /**
     *  Delete the "id" player.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
