package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Contact;
import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.ContactResponse;
import com.mff.restfulapi.model.ContactUpdateRequest;
import com.mff.restfulapi.model.CreateContactRequest;
import com.mff.restfulapi.model.SearchContactRequest;
import com.mff.restfulapi.repository.ContactRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public ContactResponse create(Users users, CreateContactRequest request) {

        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(users);

        contactRepository.save(contact);

        return contactResponseBuilder(contact);
    }

    private ContactResponse contactResponseBuilder(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ContactResponse get(Users users, String id) {

        Contact contact = contactRepository.findFirstByUserAndId(users, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        return contactResponseBuilder(contact);
    }

    @Override
    public ContactResponse update(Users users, ContactUpdateRequest request) {

        Contact contact = contactRepository.findFirstByUserAndId(users, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        if (request.getFirstName() != null) {
            contact.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            contact.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            contact.setPhone(request.getPhone());
        }
        if (request.getEmail() != null) {
            contact.setEmail(request.getEmail());
        }
        contactRepository.save(contact);

        return contactResponseBuilder(contact);
    }

    @Transactional
    @Override
    public void delete(Users users, String id) {

        Contact contact = contactRepository.findFirstByUserAndId(users, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contactRepository.delete(contact);

    }

    @Transactional(readOnly = true)
    @Override
    public Page<ContactResponse> search(Users users, SearchContactRequest request) {
        Specification<Contact> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("user"), users));
            if (Objects.nonNull(request.getName())) {
                predicates.add(builder.or(
                        builder.like(root.get("firstName"), "%" + request.getName() + "%"),
                        builder.like(root.get("lastName"), "%" + request.getName() + "%")
                ));
            }
            if (Objects.nonNull(request.getEmail())) {
                predicates.add(builder.like(root.get("email"), "%" + request.getEmail() + "%"));
            }
            if (Objects.nonNull(request.getEmail())) {
                predicates.add(builder.like(root.get("phone"), "%" + request.getEmail() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Contact> contacts = contactRepository.findAll(specification, pageable);
        List<ContactResponse> contactResponses = contacts.getContent().stream()
                .map(this::contactResponseBuilder).toList();

        return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
    }
}
