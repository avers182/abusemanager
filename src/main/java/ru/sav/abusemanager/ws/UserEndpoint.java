package ru.sav.abusemanager.ws;


import ru.sav.abusemanager.service.UserService;
import ru.sav.abusemanager.ws.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

@Endpoint
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @PayloadRoot(localPart = "getUserByLoginRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public GetUserByLoginResponse getUserByLogin(@RequestPayload GetUserByLoginRequest request, SoapHeader soapHeader) {
        return userService.getUserByLogin(request.getLogin());
    }

    @PayloadRoot(localPart = "createUserRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request, SoapHeader soapHeader) {
        CreateUserResponse response = new CreateUserResponse();
        response.setResult(
                userService.createUser(
                        request.getLogin(),
                        request.getFirstName(),
                        request.getSecondName(),
                        request.getJabberId()
                )
        );
        return response;
    }

    @PayloadRoot(localPart = "updateUserRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request, SoapHeader soapHeader) {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setResult(
                userService.updateUser(
                        request.getLogin(),
                        request.getFirstName(),
                        request.getSecondName(),
                        request.getJabberId()
                )
        );
        return response;
    }

    @PayloadRoot(localPart = "deleteUserRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request, SoapHeader soapHeader) {
        DeleteUserResponse response = new DeleteUserResponse();
        response.setResult(userService.deleteUser(request.getLogin()));
        return response;
    }

    @PayloadRoot(localPart = "getUserGroupsRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public GetUserGroupsResponse getUserGroups(@RequestPayload GetUserGroupsRequest request, SoapHeader soapHeader) {
        GetUserGroupsResponse response = new GetUserGroupsResponse();
        response.setUserGroups(userService.getUserGroups(request.getLogin(), request.getRoles()));
        return response;
    }

}