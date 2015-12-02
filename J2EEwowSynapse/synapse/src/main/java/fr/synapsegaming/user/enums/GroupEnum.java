package fr.synapsegaming.user.enums;

/**
 * GroupEnum specify what kind of Group is in use. The value is the Group ID in
 * the database
 * 
 * @author Meidi
 * 
 */
public enum GroupEnum {
    GUEST(4), GUILD(3), ADMIN(2), WEBMASTER(1);

    private int code;

    private GroupEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
