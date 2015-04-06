package persister.service;

import persister.model.FirstName;

import java.util.List;

public interface NamePersistenceService {
    void persist(FirstName firstname);
    List<FirstName> findAll();

    void delete(FirstName firstName);
}
