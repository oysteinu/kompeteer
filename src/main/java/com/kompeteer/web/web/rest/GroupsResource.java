package com.kompeteer.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kompeteer.web.service.GroupsService;
import com.kompeteer.web.web.rest.util.HeaderUtil;
import com.kompeteer.web.service.dto.GroupsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Groups.
 */
@RestController
@RequestMapping("/api")
public class GroupsResource {

    private final Logger log = LoggerFactory.getLogger(GroupsResource.class);

    private static final String ENTITY_NAME = "groups";
        
    private final GroupsService groupsService;

    public GroupsResource(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    /**
     * POST  /groups : Create a new groups.
     *
     * @param groupsDTO the groupsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groupsDTO, or with status 400 (Bad Request) if the groups has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/groups")
    @Timed
    public ResponseEntity<GroupsDTO> createGroups(@RequestBody GroupsDTO groupsDTO) throws URISyntaxException {
        log.debug("REST request to save Groups : {}", groupsDTO);
        if (groupsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new groups cannot already have an ID")).body(null);
        }
        GroupsDTO result = groupsService.save(groupsDTO);
        return ResponseEntity.created(new URI("/api/groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /groups : Updates an existing groups.
     *
     * @param groupsDTO the groupsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groupsDTO,
     * or with status 400 (Bad Request) if the groupsDTO is not valid,
     * or with status 500 (Internal Server Error) if the groupsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/groups")
    @Timed
    public ResponseEntity<GroupsDTO> updateGroups(@RequestBody GroupsDTO groupsDTO) throws URISyntaxException {
        log.debug("REST request to update Groups : {}", groupsDTO);
        if (groupsDTO.getId() == null) {
            return createGroups(groupsDTO);
        }
        GroupsDTO result = groupsService.save(groupsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /groups : get all the groups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of groups in body
     */
    @GetMapping("/groups")
    @Timed
    public List<GroupsDTO> getAllGroups() {
        log.debug("REST request to get all Groups");
        return groupsService.findAll();
    }

    /**
     * GET  /groups/:id : get the "id" groups.
     *
     * @param id the id of the groupsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groupsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/groups/{id}")
    @Timed
    public ResponseEntity<GroupsDTO> getGroups(@PathVariable Long id) {
        log.debug("REST request to get Groups : {}", id);
        GroupsDTO groupsDTO = groupsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(groupsDTO));
    }

    /**
     * DELETE  /groups/:id : delete the "id" groups.
     *
     * @param id the id of the groupsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroups(@PathVariable Long id) {
        log.debug("REST request to delete Groups : {}", id);
        groupsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
