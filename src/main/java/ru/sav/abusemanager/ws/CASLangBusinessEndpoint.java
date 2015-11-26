package ru.sav.abusemanager.ws;

import ru.sav.abusemanager.service.CASLangBusinessService;
import ru.sav.abusemanager.ws.caslangbusiness.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

@Endpoint
public class CASLangBusinessEndpoint {

    @Autowired
    CASLangBusinessService casLangBusinessService;

    @PayloadRoot(localPart = "listRolesToGroupsRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public ListRolesToGroupsResponse listRolesToGroups(@RequestPayload ListRolesToGroupsRequest request, SoapHeader soapHeader) {
        ListRolesToGroupsResponse response = new ListRolesToGroupsResponse();
        response.setRoleToGroups(casLangBusinessService.listRolesToGroups());
        return response;
    }

    @PayloadRoot(localPart = "getRoleToGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public GetRoleToGroupResponse getRoleToGroup(@RequestPayload GetRoleToGroupRequest request, SoapHeader soapHeader) {
        GetRoleToGroupResponse response = new GetRoleToGroupResponse();
        response.setRoleToGroups(casLangBusinessService.getRoleToGroup(request.getId(), request.getRole(), request.getGroupId()));
        return response;
    }

    @PayloadRoot(localPart = "createRoleToGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public CreateRoleToGroupResponse createRoleToGroup(@RequestPayload CreateRoleToGroupRequest request, SoapHeader soapHeader) {
        CreateRoleToGroupResponse response = new CreateRoleToGroupResponse();
        response.setResult(casLangBusinessService.createRoleToGroup(request.getRole(), request.getGroupId()));
        return response;
    }

    @PayloadRoot(localPart = "updateRoleToGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public UpdateRoleToGroupResponse updateRoleToGroup(@RequestPayload UpdateRoleToGroupRequest request, SoapHeader soapHeader) {
        UpdateRoleToGroupResponse response = new UpdateRoleToGroupResponse();
        response.setResult(casLangBusinessService.updateRoleToGroup(request.getId(), request.getRole(), request.getGroupId()));
        return response;
    }

    @PayloadRoot(localPart = "deleteRoleToGroupRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public DeleteRoleToGroupResponse deleteRoleToGroup(@RequestPayload DeleteRoleToGroupRequest request, SoapHeader soapHeader) {
        DeleteRoleToGroupResponse response = new DeleteRoleToGroupResponse();
        response.setResult(casLangBusinessService.deleteRoleToGroup(request.getId(), request.getRole(), request.getGroupId()));
        return response;
    }
}
