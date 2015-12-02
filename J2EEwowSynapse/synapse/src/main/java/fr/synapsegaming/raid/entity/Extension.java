package fr.synapsegaming.raid.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Extension</b> stands for Blizzard version extensions
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "extensions")
public class Extension {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_extension", unique = true, nullable = false)
    private long id;

    /**
     * The name of the extension
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * First digit of the version
     */
    @Column(name = "version_x", nullable = false)
    private int versionX;

    /**
     * Second digit of the version
     */
    @Column(name = "version_y", nullable = false)
    private int versionY;

    /**
     * Third digit of the version
     */
    @Column(name = "version_z", nullable = false)
    private int versionZ;

    /**
     * Extension logo
     */
    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    /**
     * The list of patches from the extension
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patch")
    private Set<Patch> patches;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionX() {
        return versionX;
    }

    public void setVersionX(int versionX) {
        this.versionX = versionX;
    }

    public int getVersionY() {
        return versionY;
    }

    public void setVersionY(int versionY) {
        this.versionY = versionY;
    }

    public int getVersionZ() {
        return versionZ;
    }

    public void setVersionZ(int versionZ) {
        this.versionZ = versionZ;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Patch> getPatches() {
        return patches;
    }

    public void setPatches(Set<Patch> patches) {
        this.patches = patches;
    }

}
