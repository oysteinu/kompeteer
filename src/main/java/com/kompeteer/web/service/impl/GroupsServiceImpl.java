package com.kompeteer.web.service.impl;

import com.kompeteer.web.service.GroupsService;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.repository.GroupsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Groups.
 */
@Service
@Transactional
public class GroupsServiceImpl implements GroupsService{

    private final Logger log = LoggerFactory.getLogger(GroupsServiceImpl.class);
    
    private final GroupsRepository groupsRepository;

    public GroupsServiceImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    /**
     * Save a groups.
     *
     * @param groups the entity to save
     * @return the persisted entity
     */
    @Override
    public Groups save(Groups groups) {
        log.debug("Request to save Groups : {}", groups);
        Groups result = groupsRepository.save(groups);
        return result;
    }

    /**
     *  Get all the groups.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Groups> findAll() {
        log.debug("Request to get all Groups");
        List<Groups> result = groupsRepository.findAll();

        return result;
    }

    /**
     *  Get one groups by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Groups findOne(Long id) {
        log.debug("Request to get Groups : {}", id);
        Groups groups = groupsRepository.findOne(id);
        return groups;
    }

    /**
     *  Delete the  groups by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Groups : {}", id);
        groupsRepository.delete(id);
    }
}
