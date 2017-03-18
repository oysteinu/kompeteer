package com.kompeteer.web.service;

import com.kompeteer.web.service.dto.TournamentDTO;
import java.util.List;

/**
 * Service Interface for managing Tournament.
 */
public interface TournamentService {

    /**
     * Save a tournament.
     *
     * @param tournamentDTO the entity to save
     * @return the persisted entity
     */
    TournamentDTO save(TournamentDTO tournamentDTO);

    /**
     *  Get all the tournaments.
     *  
     *  @return the list of entities
     */
    List<TournamentDTO> findAll();

    /**
     *  Get the "id" tournament.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TournamentDTO findOne(Long id);

    /**
     *  Delete the "id" tournament.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
