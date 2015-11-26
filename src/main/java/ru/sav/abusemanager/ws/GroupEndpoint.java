package ru.sav.abusemanager.ws;

import ru.sav.abusemanager.service.GroupService;
import ru.sav.abusemanager.ws.group.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

@Endpoint
public class GroupEndpoint {
    @Autowired
    GroupService groupService;

    @PayloadRoot(localPart = "listGroupsRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public ListGroupsResponse listGroups(@RequestPayload ListGroupsRequest request, SoapHeader soapHeader) {
        ListGroupsResponse response = new ListGroupsResponse();
        response.setGroups(groupService.getAllGroups());
        return response;
    }

    @PayloadRoot(localPart = "createGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public CreateGroupResponse createGroup(@RequestPayload CreateGroupRequest request, SoapHeader soapHeader) {
        CreateGroupResponse response = new CreateGroupResponse();
        response.setResult(groupService.createGroup(request.getName()));
        return response;
    }

    @PayloadRoot(localPart = "updateGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public UpdateGroupResponse updateGroup(@RequestPayload UpdateGroupRequest request, SoapHeader soapHeader) {
        UpdateGroupResponse response = new UpdateGroupResponse();
        response.setResult(groupService.updateGroup(request.getId(), request.getName()));
        return response;
    }

    @PayloadRoot(localPart = "deleteGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public DeleteGroupResponse deleteGroup(@RequestPayload DeleteGroupRequest request, SoapHeader soapHeader) {
        DeleteGroupResponse response = new DeleteGroupResponse();
        response.setResult(groupService.deleteGroup(request.getId()));
        return response;
    }

    @PayloadRoot(localPart = "getGroupUsersRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public GetGroupUsersResponse getGroupUsers(@RequestPayload GetGroupUsersRequest request, SoapHeader soapHeader) {
        GetGroupUsersResponse response = new GetGroupUsersResponse();
        response.setGroupUsers(groupService.getGroupUsers(request.getGroupId(), request.getRoles()));
        return response;
    }

    @PayloadRoot(localPart = "addUserToGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public AddUserToGroupResponse addUserToGroup(@RequestPayload AddUserToGroupRequest request, SoapHeader soapHeader) {
        AddUserToGroupResponse response = new AddUserToGroupResponse();
        response.setResult(groupService.addUserToGroup(request.getLogin(), request.getGroupId(), request.getRoles()));
        return response;
    }

    @PayloadRoot(localPart = "deleteUserFromGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public DeleteUserFromGroupResponse deleteUserFromGroup(@RequestPayload DeleteUserFromGroupRequest request, SoapHeader soapHeader) {
        DeleteUserFromGroupResponse response = new DeleteUserFromGroupResponse();
        response.setResult(groupService.deleteUserFromGroup(request.getLogin(), request.getGroupId(), request.getRoles()));
        return response;
    }

    @PayloadRoot(localPart = "getGroupCoursesRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public GetGroupCoursesResponse getGroupCourses(@RequestPayload GetGroupCoursesRequest request, SoapHeader soapHeader) {
        GetGroupCoursesResponse response = new GetGroupCoursesResponse();
        response.setCourses(groupService.getGroupCourses(request.getGroupId()));
        return response;
    }

}
