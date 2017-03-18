package com.kompeteer.web.service.mapper;

import com.kompeteer.web.domain.*;
import com.kompeteer.web.service.dto.TournamentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Tournament and its DTO TournamentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TournamentMapper {

    TournamentDTO tournamentToTournamentDTO(Tournament tournament);

    List<TournamentDTO> tournamentsToTournamentDTOs(List<Tournament> tournaments);

    @Mapping(target = "games", ignore = true)
    @Mapping(target = "players", ignore = true)
    Tournament tournamentDTOToTournament(TournamentDTO tournamentDTO);

    List<Tournament> tournamentDTOsToTournaments(List<TournamentDTO> tournamentDTOs);
}
