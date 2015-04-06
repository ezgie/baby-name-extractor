/**
 * This class is generated by jOOQ
 */
package persister.model.generated;

/**
 * A class modelling foreign key relationships between tables of the <code>public</code> 
 * schema
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.3"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.UniqueKey<persister.model.generated.tables.records.FirstnamesRecord> FIRSTNAMES_PKEY = UniqueKeys0.FIRSTNAMES_PKEY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class UniqueKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.UniqueKey<persister.model.generated.tables.records.FirstnamesRecord> FIRSTNAMES_PKEY = createUniqueKey(persister.model.generated.tables.Firstnames.FIRSTNAMES, persister.model.generated.tables.Firstnames.FIRSTNAMES.ID);
	}
}
