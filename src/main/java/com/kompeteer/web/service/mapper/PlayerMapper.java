package com.kompeteer.web.service.mapper;

import com.kompeteer.web.domain.*;
import com.kompeteer.web.service.dto.PlayerDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Player and its DTO PlayerDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, TournamentMapper.class, GroupsMapper.class, })
public interface PlayerMapper {

    @Mapping(source = "user.id", target = "userId")
    PlayerDTO playerToPlayerDTO(Player player);

    List<PlayerDTO> playersToPlayerDTOs(List<Player> players);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "player1Games", ignore = true)
    @Mapping(target = "player2Games", ignore = true)
    Player playerDTOToPlayer(PlayerDTO playerDTO);

    List<Player> playerDTOsToPlayers(List<PlayerDTO> playerDTOs);

    default Tournament tournamentFromId(Long id) {
        if (id == null) {
            return null;
        }
        Tournament tournament = new Tournament();
        tournament.setId(id);
        return tournament;
    }

    default Groups groupsFromId(Long id) {
        if (id == null) {
            return null;
        }
        Groups groups = new Groups();
        groups.setId(id);
        return groups;
    }
}
