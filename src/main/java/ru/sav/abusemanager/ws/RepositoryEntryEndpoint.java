package ru.sav.abusemanager.ws;

import ru.sav.abusemanager.service.RepositoryEntryService;
import ru.sav.abusemanager.ws.repositoryentry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

@Endpoint
public class RepositoryEntryEndpoint {
    @Autowired
    RepositoryEntryService repositoryEntryService;

    @PayloadRoot(localPart = "listCoursesRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public ListCoursesResponse listCourses(@RequestPayload ListCoursesRequest request, SoapHeader soapHeader) {
        return repositoryEntryService.listCourses();
    }

    @PayloadRoot(localPart = "listCoursesTLRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public ListCoursesTLResponse listCoursesTL(@RequestPayload ListCoursesTLRequest request, SoapHeader soapHeader) {
        return repositoryEntryService.listCoursesTL();
    }

    @PayloadRoot(localPart = "getCourseTimeLimitRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public GetCourseTimeLimitResponse getCourseTimeLimit(@RequestPayload GetCourseTimeLimitRequest request, SoapHeader soapHeader) {
        return repositoryEntryService.getCourseTimeLimit(request.getCourseId());
    }

    @PayloadRoot(localPart = "setCourseTimeLimitRequest", namespace = "http://sav.ru/abusemanager")
    @ResponsePayload
    public SetCourseTimeLimitResponse setCourseTimeLimit(@RequestPayload SetCourseTimeLimitRequest request, SoapHeader soapHeader) {
        SetCourseTimeLimitResponse response = new SetCourseTimeLimitResponse();
        response.setResult(repositoryEntryService.setCourseTimeLimit(request.getCourseId(), request.getTimeLimit()));
        return response;
    }
}
