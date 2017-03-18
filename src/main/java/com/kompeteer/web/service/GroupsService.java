package com.kompeteer.web.service;

import com.kompeteer.web.service.dto.GroupsDTO;
import java.util.List;

/**
 * Service Interface for managing Groups.
 */
public interface GroupsService {

    /**
     * Save a groups.
     *
     * @param groupsDTO the entity to save
     * @return the persisted entity
     */
    GroupsDTO save(GroupsDTO groupsDTO);

    /**
     *  Get all the groups.
     *  
     *  @return the list of entities
     */
    List<GroupsDTO> findAll();

    /**
     *  Get the "id" groups.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    GroupsDTO findOne(Long id);

    /**
     *  Delete the "id" groups.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
