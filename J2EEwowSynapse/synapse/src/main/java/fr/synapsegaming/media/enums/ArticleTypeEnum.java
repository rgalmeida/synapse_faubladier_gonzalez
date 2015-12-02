package fr.synapsegaming.media.enums;

/**
 * ArticleTypeEnum specify what kind of Article is in use. The value is the
 * ArticleType ID in the database
 * 
 * @author Meidi
 * 
 */
public enum ArticleTypeEnum {
    BLOG(1L), NEWS(2L), ABOUT(3L);

    private long code;

    private ArticleTypeEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

}
