package com.mff.restfulapi.controller;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.*;
import com.mff.restfulapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(
            path = "/api/contact",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create (Users users ,@RequestBody CreateContactRequest request){

       ContactResponse response = contactService.create(users , request);

       return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/contact/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> get (Users users, @PathVariable("contactId") String id){

        ContactResponse response = contactService.get(users , id);

        return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @PutMapping(
            path = "/api/contact/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> update ( Users users ,
                                                 @RequestBody ContactUpdateRequest request,
                                                 @PathVariable String contactId){
        request.setId(contactId);

        ContactResponse response = contactService.update(users , request);

        return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @DeleteMapping(
            path = "/api/contact/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(Users users , @PathVariable("contactId") String contactId){
        contactService.delete(users , contactId);

        return WebResponse.<String>builder().data("ok").build();
    }

    @GetMapping(
            path = "/api/contact/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ContactResponse>> search(Users users ,
                                                     @RequestParam(value = "name" , required = false) String name,
                                                     @RequestParam(value = "phone" , required = false) String phone,
                                                     @RequestParam(value = "email" , required = false) String email,
                                                     @RequestParam(value = "page" , required = false , defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size" , required = false, defaultValue = "10") Integer size){
        SearchContactRequest request = SearchContactRequest.builder()
                .page(page)
                .size(size)
                .email(email)
                .phone(phone)
                .name(name)
                .build();
        Page<ContactResponse> responses = contactService.search(users , request);
        return WebResponse.<List<ContactResponse>>builder()
                .data(responses.getContent())
                .paging(PaggingResponse.builder()
                        .currentPage(responses.getNumber())
                        .totalPage(responses.getTotalPages())
                        .size(responses.getSize())
                        .build())
                .build();
    }

}
