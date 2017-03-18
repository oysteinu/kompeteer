package com.kompeteer.web.repository;

import com.kompeteer.web.domain.Player;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Player entity.
 */
@SuppressWarnings("unused")
public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("select distinct player from Player player left join fetch player.tournaments left join fetch player.groups")
    List<Player> findAllWithEagerRelationships();

    @Query("select player from Player player left join fetch player.tournaments left join fetch player.groups where player.id =:id")
    Player findOneWithEagerRelationships(@Param("id") Long id);

}
