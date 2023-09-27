package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.ContactResponse;
import com.mff.restfulapi.model.ContactUpdateRequest;
import com.mff.restfulapi.model.CreateContactRequest;
import com.mff.restfulapi.model.SearchContactRequest;
import org.springframework.data.domain.Page;

public interface ContactService {

    ContactResponse create (Users users , CreateContactRequest request) ;

    ContactResponse get (Users users, String id);

    ContactResponse update (Users users , ContactUpdateRequest request);

    void delete (Users users, String id);

    Page<ContactResponse> search (Users users , SearchContactRequest request);

}
