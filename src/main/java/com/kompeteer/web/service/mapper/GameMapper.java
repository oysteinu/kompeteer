package com.kompeteer.web.service.mapper;

import com.kompeteer.web.domain.*;
import com.kompeteer.web.service.dto.GameDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Game and its DTO GameDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GameMapper {

    @Mapping(source = "white.id", target = "whiteId")
    @Mapping(source = "black.id", target = "blackId")
    @Mapping(source = "tournament.id", target = "tournamentId")
    GameDTO gameToGameDTO(Game game);

    List<GameDTO> gamesToGameDTOs(List<Game> games);

    @Mapping(source = "whiteId", target = "white")
    @Mapping(source = "blackId", target = "black")
    @Mapping(source = "tournamentId", target = "tournament")
    Game gameDTOToGame(GameDTO gameDTO);

    List<Game> gameDTOsToGames(List<GameDTO> gameDTOs);

    default Player playerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Player player = new Player();
        player.setId(id);
        return player;
    }

    default Tournament tournamentFromId(Long id) {
        if (id == null) {
            return null;
        }
        Tournament tournament = new Tournament();
        tournament.setId(id);
        return tournament;
    }
}
