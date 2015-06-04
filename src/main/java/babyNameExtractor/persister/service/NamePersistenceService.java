package babyNameExtractor.persister.service;

import babyNameExtractor.persister.model.FirstName;

import java.util.List;

public interface NamePersistenceService {
    void persist(List<FirstName> firstNames);
    void persist(FirstName firstname);
    List<FirstName> findAll();

    void delete(FirstName firstName);

    void deleteAll();
}
