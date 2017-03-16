package com.kompeteer.web.repository;

import com.kompeteer.web.domain.Groups;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Groups entity.
 */
@SuppressWarnings("unused")
public interface GroupsRepository extends JpaRepository<Groups,Long> {

}
