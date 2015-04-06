package persister.service;

import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import persister.model.FirstName;
import persister.model.Gender;
import persister.model.generated.tables.Firstnames;

import java.util.ArrayList;
import java.util.List;

public class NamePersistenceServiceImpl implements NamePersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NamePersistenceServiceImpl.class);

    @Autowired
    DSLContext create;
    @Override
    public void persist(FirstName firstName) {
        create.insertInto(Firstnames.FIRSTNAMES)
                .set(Firstnames.FIRSTNAMES.ID, firstName.getId())
                .set(Firstnames.FIRSTNAMES.FIRSTNAME, firstName.getFirstName())
                .set(Firstnames.FIRSTNAMES.MEANING, firstName.getMeaning())
                .set(Firstnames.FIRSTNAMES.ORIGIN, firstName.getOrigin())
                .set(Firstnames.FIRSTNAMES.GENDER, firstName.getGender().getTitle())
                .execute();
    }

    @Override
    public List<FirstName> findAll() {
        Firstnames f = Firstnames.FIRSTNAMES.as("f");
        Result<Record5<Long, String, String, String, String>> result =
                create.select(f.ID, f.FIRSTNAME, f.MEANING, f.ORIGIN, f.GENDER)
                        .from(f)
                        .fetch();

        List<FirstName> firstNames = new ArrayList<FirstName>();
        for(Record5<Long, String, String, String, String> record5 : result) {

            FirstName firstName = new FirstName();
            firstName.setId(record5.value1());
            firstName.setFirstName(record5.value2());
            firstName.setMeaning(record5.value3());
            firstName.setOrigin(record5.value4());
            firstName.setGender(Gender.fromTitle(record5.value5()));
            firstNames.add(firstName);
            LOGGER.info(firstName.toString());
        }
        return firstNames;
    }

    @Override
    public void delete(FirstName firstName) {
        create.delete(Firstnames.FIRSTNAMES)
                .where(Firstnames.FIRSTNAMES.ID.equal(firstName.getId()));
    }
}
