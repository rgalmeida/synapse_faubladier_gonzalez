package fr.synapsegaming.user.enums;

public enum RoleEnum {
    TANK(1), RANGE(2), MELEE(3), HEAL(4);

    private long code;

    private RoleEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
