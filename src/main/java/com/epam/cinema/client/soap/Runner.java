package com.epam.cinema.client.soap;

import com.epam.cinema.model.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;
import java.time.LocalDate;
import java.util.Arrays;

public class Runner {

    public static final String HTTP_LOCALHOST_8080_WS = "http://localhost:8080/ws";

    public static void main(String[] args) throws Exception {
        WebServiceTemplate template = createWebServiceTemplate("com.epam.cinema.model");

        User user = new User("Bob", "Bob", "email", LocalDate.now());
        user.setPassword("123");
        user.setRoles(Arrays.asList(Role.ADMIN, Role.MANAGER));
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUser(user);

        AddedUserResponse addedUserResponse = (AddedUserResponse) template.marshalSendAndReceive(HTTP_LOCALHOST_8080_WS, addUserRequest);
        System.out.println("Request: AddUserRequest(): User:  + " + user.toString() + "\nResponse: new ID: " + addedUserResponse.getNewUserId());
        System.out.println("\n\n");

        UsersResponse response = (UsersResponse) template.marshalSendAndReceive(HTTP_LOCALHOST_8080_WS, new GetUsersRequest());
        System.out.println("Request: GetUsersRequest()\nResponse:");
        response.getUserList().forEach(System.out::println);
    }

    private static WebServiceTemplate createWebServiceTemplate(String contextPath) throws Exception {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
        messageFactory.afterPropertiesSet();

        WebServiceTemplate template = new WebServiceTemplate(messageFactory);

        Jaxb2Marshaller jaxb2Marshaller = createJaxb2Marshaller(contextPath);

        template.setMarshaller(jaxb2Marshaller);
        template.setUnmarshaller(jaxb2Marshaller);
        template.afterPropertiesSet();

        return template;
    }

    private static Jaxb2Marshaller createJaxb2Marshaller(String contextPath) throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(contextPath);
        marshaller.afterPropertiesSet();

        return marshaller;
    }
}
