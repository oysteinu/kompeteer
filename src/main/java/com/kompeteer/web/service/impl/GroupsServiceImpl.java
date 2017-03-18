package com.kompeteer.web.service.impl;

import com.kompeteer.web.service.GroupsService;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.repository.GroupsRepository;
import com.kompeteer.web.service.dto.GroupsDTO;
import com.kompeteer.web.service.mapper.GroupsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Groups.
 */
@Service
@Transactional
public class GroupsServiceImpl implements GroupsService{

    private final Logger log = LoggerFactory.getLogger(GroupsServiceImpl.class);
    
    private final GroupsRepository groupsRepository;

    private final GroupsMapper groupsMapper;

    public GroupsServiceImpl(GroupsRepository groupsRepository, GroupsMapper groupsMapper) {
        this.groupsRepository = groupsRepository;
        this.groupsMapper = groupsMapper;
    }

    /**
     * Save a groups.
     *
     * @param groupsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GroupsDTO save(GroupsDTO groupsDTO) {
        log.debug("Request to save Groups : {}", groupsDTO);
        Groups groups = groupsMapper.groupsDTOToGroups(groupsDTO);
        groups = groupsRepository.save(groups);
        GroupsDTO result = groupsMapper.groupsToGroupsDTO(groups);
        return result;
    }

    /**
     *  Get all the groups.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GroupsDTO> findAll() {
        log.debug("Request to get all Groups");
        List<GroupsDTO> result = groupsRepository.findAll().stream()
            .map(groupsMapper::groupsToGroupsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

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
    public GroupsDTO findOne(Long id) {
        log.debug("Request to get Groups : {}", id);
        Groups groups = groupsRepository.findOne(id);
        GroupsDTO groupsDTO = groupsMapper.groupsToGroupsDTO(groups);
        return groupsDTO;
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
