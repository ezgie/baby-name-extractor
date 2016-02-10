package babyNameExtractor.persister.service;

import org.apache.commons.lang3.SerializationUtils;
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
    private static final String SOURCE_0 = "source_0";
    private static final String SOURCE_1 = "source_1";

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
        firstName0.setGender(Gender.MALE);
        firstName0.setSource(SOURCE_0);


        firstName1 = new FirstName();
        firstName1.setFirstName(FIRST_NAME_1);
        firstName1.setMeaning(MEANING_1);
        firstName1.setOrigin(ORIGIN_1);
        firstName1.setGender(Gender.FEMALE);
        firstName1.setSource(SOURCE_1);
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
        assertEquals(Gender.MALE, firstNamesFound.get(0).getGender());

        assertEquals(FIRST_NAME_1, firstNamesFound.get(1).getFirstName());
        assertEquals(MEANING_1, firstNamesFound.get(1).getMeaning());
        assertEquals(ORIGIN_1, firstNamesFound.get(1).getOrigin());
        assertEquals(Gender.FEMALE, firstNamesFound.get(1).getGender());
    }

    @Test
    public void testPersistSameNameMeaningAndOriginKeptSame() {
        namePersistenceService.persist(firstName0);
        FirstName firstName0Clone = SerializationUtils.clone(firstName0);
        firstName0Clone.setMeaning("ANOTHER_MEANING");
        firstName0Clone.setOrigin("ANOTHER_ORIGIN");
        namePersistenceService.persist(firstName0Clone);

        //Test
        List<FirstName> firstNamesFound = namePersistenceService.findAll();
        assertEquals(1, firstNamesFound.size());

        assertEquals(FIRST_NAME_0, firstNamesFound.get(0).getFirstName());
        assertEquals(MEANING_0, firstNamesFound.get(0).getMeaning());
        assertEquals(ORIGIN_0, firstNamesFound.get(0).getOrigin());
        assertEquals(Gender.MALE, firstNamesFound.get(0).getGender());
    }

    @Test
    public void testPersistSameNameWithDifferentGender() {
        namePersistenceService.persist(firstName0);
        FirstName firstName0Clone = SerializationUtils.clone(firstName0);
        firstName0Clone.setGender(Gender.FEMALE);

        namePersistenceService.persist(firstName0Clone);

        //Test
        List<FirstName> firstNamesFound = namePersistenceService.findAll();
        assertEquals(1, firstNamesFound.size());

        assertEquals(FIRST_NAME_0, firstNamesFound.get(0).getFirstName());
        assertEquals(Gender.UNISEX, firstNamesFound.get(0).getGender());
    }

    @After
    public void tearDown() {
        List<FirstName> firstNames = namePersistenceService.findAll();
        for(FirstName firstName : firstNames){
            namePersistenceService.delete(firstName);
        }
    }
}
