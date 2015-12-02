package fr.synapsegaming.user.enums;

public enum BlizzardURLEnum {

    DEFAULT_USER_URL("/resources/img/default_avatar.png"), BLIZZARD_EU_WOW_API(
            "http://eu.battle.net/api/wow/"), BLIZZARD_EU_WOW_API_CHARACTER(
            "http://eu.battle.net/api/wow/character/"), BLIZZARD_EU_THUMBNAIL_URL(
            "http://eu.battle.net/static-render/eu/");

    private String url;

    private BlizzardURLEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
