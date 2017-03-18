package com.kompeteer.web.service.impl;

import com.kompeteer.web.service.TournamentService;
import com.kompeteer.web.domain.Tournament;
import com.kompeteer.web.repository.TournamentRepository;
import com.kompeteer.web.service.dto.TournamentDTO;
import com.kompeteer.web.service.mapper.TournamentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Tournament.
 */
@Service
@Transactional
public class TournamentServiceImpl implements TournamentService{

    private final Logger log = LoggerFactory.getLogger(TournamentServiceImpl.class);
    
    private final TournamentRepository tournamentRepository;

    private final TournamentMapper tournamentMapper;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    /**
     * Save a tournament.
     *
     * @param tournamentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TournamentDTO save(TournamentDTO tournamentDTO) {
        log.debug("Request to save Tournament : {}", tournamentDTO);
        Tournament tournament = tournamentMapper.tournamentDTOToTournament(tournamentDTO);
        tournament = tournamentRepository.save(tournament);
        TournamentDTO result = tournamentMapper.tournamentToTournamentDTO(tournament);
        return result;
    }

    /**
     *  Get all the tournaments.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TournamentDTO> findAll() {
        log.debug("Request to get all Tournaments");
        List<TournamentDTO> result = tournamentRepository.findAll().stream()
            .map(tournamentMapper::tournamentToTournamentDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one tournament by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TournamentDTO findOne(Long id) {
        log.debug("Request to get Tournament : {}", id);
        Tournament tournament = tournamentRepository.findOne(id);
        TournamentDTO tournamentDTO = tournamentMapper.tournamentToTournamentDTO(tournament);
        return tournamentDTO;
    }

    /**
     *  Delete the  tournament by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tournament : {}", id);
        tournamentRepository.delete(id);
    }
}
