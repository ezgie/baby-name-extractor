package babyNameExtractor.customExtractors.isimArsivi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import babyNameExtractor.customExtractors.common.service.Extractor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import babyNameExtractor.persister.model.FirstName;
import babyNameExtractor.persister.model.Gender;
import babyNameExtractor.persister.service.NamePersistenceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class IsimArsiviExtractorImpl implements Extractor {

    private Logger LOGGER = LoggerFactory.getLogger(IsimArsiviExtractorImpl.class);

    private NamePersistenceService namePersistenceService;

    @Override
    public void extractAndPersist() throws IOException {
        int pageNum = 1;
        List<FirstName> firstNames = extractPage(pageNum);
        while(!firstNames.isEmpty()){
            LOGGER.debug("Persisting page '{}'", pageNum);
            namePersistenceService.persist(firstNames);
            firstNames = extractPage(++pageNum);
        }
    }

    List<FirstName> extractPage(int pageNum) throws IOException {
        byte[] bytes = Request.Post("http://www.isimarsivi.com/isimler/ara/search")
                .useExpectContinue()
                .bodyString("{\"NumberToTake\":25,\"Page\":" + pageNum + "}", ContentType.APPLICATION_JSON)
                .execute().returnContent().asBytes();
        String content = new String(bytes);
        return parseJson(content);
    }

    List<FirstName> parseJson(String json) {
        JsonArray names = parseNameList(json);
        if(names.size() == 0) {
            return Collections.EMPTY_LIST;
        } else {
         List<FirstName> firstNames = new ArrayList<>();
            for (JsonElement name : names) {
                FirstName firstName = parseName(name.getAsJsonObject());
                firstNames.add(firstName);
            }
            return firstNames;
        }
    }

    private JsonArray parseNameList(String json) {
        JsonObject root = new JsonParser().parse(json).getAsJsonObject();
        JsonObject hits1 = root.getAsJsonObject("hits");
        return hits1.getAsJsonArray("hits");
    }

    private FirstName parseName(JsonObject name) {
        JsonObject nameDetails = name.getAsJsonObject("_source");
        FirstName firstName = new FirstName();
        firstName.setFirstName(nameDetails.getAsJsonPrimitive("ad").getAsString());
        firstName.setGender(parseGender(nameDetails.getAsJsonPrimitive("intCinsiyet").getAsInt()));
        firstName.setMeaning(nameDetails.getAsJsonPrimitive("anlam").getAsString());
        firstName.setOrigin(parseOrigin(nameDetails));
        return firstName;
    }

    private Gender parseGender(Integer intCinsiyet) {
        switch (intCinsiyet) {
            case 0:
                return Gender.FEMALE;
            case 1:
                return Gender.MALE;
            default:
                return Gender.UNISEX;
        }
    }

    private String parseOrigin(JsonObject nameDetails) {
        JsonArray originList = nameDetails.getAsJsonArray("koken");
        return originList.get(0).getAsJsonObject().getAsJsonPrimitive("kokenAdi").getAsString();
    }

    @Autowired
    public void setNamePersistenceService(NamePersistenceService namePersistenceService) {
        this.namePersistenceService = namePersistenceService;
    }
}
