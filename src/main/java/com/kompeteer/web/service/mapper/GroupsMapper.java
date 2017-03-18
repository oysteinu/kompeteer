package com.kompeteer.web.service.mapper;

import com.kompeteer.web.domain.*;
import com.kompeteer.web.service.dto.GroupsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Groups and its DTO GroupsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupsMapper {

    GroupsDTO groupsToGroupsDTO(Groups groups);

    List<GroupsDTO> groupsToGroupsDTOs(List<Groups> groups);

    @Mapping(target = "players", ignore = true)
    Groups groupsDTOToGroups(GroupsDTO groupsDTO);

    List<Groups> groupsDTOsToGroups(List<GroupsDTO> groupsDTOs);
}
