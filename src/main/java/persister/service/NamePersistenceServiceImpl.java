package persister.service;

import com.mysema.query.Tuple;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.PostgresTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import persister.model.FirstName;
import persister.model.Gender;
import persister.model.generated.QFirstnames;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static persister.model.generated.QFirstnames.firstnames;

public class NamePersistenceServiceImpl implements NamePersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NamePersistenceServiceImpl.class);

    private Configuration configuration;

    @Autowired
    private DataSource dataSource;

    public NamePersistenceServiceImpl() {
        SQLTemplates templates = new PostgresTemplates();
        configuration = new Configuration(templates);
    }

    @Override
    public void persist(FirstName firstname) {

        try(Connection connection = dataSource.getConnection()) {
            new SQLInsertClause(connection, configuration, QFirstnames.firstnames)
                    .columns(QFirstnames.firstnames.id,
                            QFirstnames.firstnames.firstname,
                            QFirstnames.firstnames.origin,
                            QFirstnames.firstnames.meaning,
                            QFirstnames.firstnames.gender)
                    .values(
                            firstname.getId(),
                            firstname.getFirstName(),
                            firstname.getOrigin(),
                            firstname.getMeaning(),
                            firstname.getGender().getTitle())
                    .execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<FirstName> findAll() {
        List<FirstName> firstNames = new ArrayList<FirstName >();
        try(Connection connection = dataSource.getConnection()){
            QFirstnames firstnames = new QFirstnames("f");
            SQLQuery query = new SQLQuery(connection, configuration);

            List<Tuple> lastNames = query.from(firstnames)
                    .list(firstnames.all());


            for(Tuple tuple : lastNames) {
                FirstName firstName = new FirstName();
                firstName.setId(tuple.get(firstnames.id));
                firstName.setFirstName(tuple.get(firstnames.firstname));
                firstName.setMeaning(tuple.get(firstnames.meaning));
                firstName.setOrigin(tuple.get(firstnames.origin));
                firstName.setGender(Gender.fromTitle(tuple.get(firstnames.gender)));
                firstNames.add(firstName);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return firstNames;

    }

    @Override
    public void delete(FirstName firstName) {
        try(Connection connection = dataSource.getConnection()) {
            new SQLDeleteClause(connection, configuration, QFirstnames.firstnames)
                    .where(firstnames.id.eq(firstName.getId()))
                    .execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}