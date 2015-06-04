package babyNameExtractor.persister.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import babyNameExtractor.persister.model.FirstName;
import babyNameExtractor.persister.model.Gender;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class NamePersistenceServiceImplTest {

    private static final String FIRST_NAME_0 = "first_name_0";
    private static final String FIRST_NAME_1 = "first_name_1";
    private static final String MEANING_0 = "meaning_0";
    private static final String MEANING_1 = "meaning_1";
    private static final String ORIGIN_0 = "origin_0";
    private static final String ORIGIN_1 = "origin_1";
    private static final Gender GENDER_0 = Gender.MALE;
    private static final Gender GENDER_1 = Gender.FEMALE;

    private FirstName firstName0;
    private FirstName firstName1;

    @Autowired
    private NamePersistenceService namePersistenceService;

    @Before
    public void setup() {
        firstName0 = new FirstName();
        firstName0.setFirstName(FIRST_NAME_0);
        firstName0.setMeaning(MEANING_0);
        firstName0.setOrigin(ORIGIN_0);
        firstName0.setGender(GENDER_0);

        firstName1 = new FirstName();
        firstName1.setFirstName(FIRST_NAME_1);
        firstName1.setMeaning(MEANING_1);
        firstName1.setOrigin(ORIGIN_1);
        firstName1.setGender(GENDER_1);
    }
    @Test
    public void testPersistAndFindAll() {
        namePersistenceService.persist(firstName0);
        namePersistenceService.persist(firstName1);

        //Test findAll()
        List<FirstName> firstNamesFound = namePersistenceService.findAll();
        assertNotNull(firstNamesFound);
        assertEquals(2, firstNamesFound.size());

        assertEquals(FIRST_NAME_0, firstNamesFound.get(0).getFirstName());
        assertEquals(MEANING_0, firstNamesFound.get(0).getMeaning());
        assertEquals(ORIGIN_0, firstNamesFound.get(0).getOrigin());
        assertEquals(GENDER_0, firstNamesFound.get(0).getGender());

        assertEquals(FIRST_NAME_1, firstNamesFound.get(1).getFirstName());
        assertEquals(MEANING_1, firstNamesFound.get(1).getMeaning());
        assertEquals(ORIGIN_1, firstNamesFound.get(1).getOrigin());
        assertEquals(GENDER_1, firstNamesFound.get(1).getGender());
    }

    @After
    public void tearDown() {
        List<FirstName> firstNames = namePersistenceService.findAll();
        for(FirstName firstName : firstNames){
            namePersistenceService.delete(firstName);
        }
    }
}
