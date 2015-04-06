package persister.model;

public enum Gender {
    MALE("m"),
    FEMALE("f"),
    UNISEX("u");

    private String title;
    private Gender(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public static Gender fromTitle(String title) {
        if(Gender.MALE.getTitle().equals(title)){
            return MALE;
        } else if(Gender.FEMALE.getTitle().equals(title)){
            return FEMALE;
        } else if(Gender.UNISEX.getTitle().equals(title)){
            return UNISEX;
        } else {
            return null;
        }
    }
}
