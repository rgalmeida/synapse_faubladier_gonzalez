package fr.synapsegaming.raid.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Patch</b> stands for Blizzard version patches
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "patches")
public class Patch {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patch", unique = true, nullable = false)
    private long id;

    /**
     * The name of the Patch provided by Blizzard
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The X of the version of the Patch provided by Blizzard
     */
    @Column(name = "version_x", nullable = false)
    private String versionX;

    /**
     * The Y of the version of the Patch provided by Blizzard
     */
    @Column(name = "version_y", nullable = false)
    private String versionY;

    /**
     * The 2 of the version of the Patch provided by Blizzard
     */
    @Column(name = "version_z", nullable = false)
    private String versionZ;

    /**
     * The list of raids from the patch
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_raid")
    private Set<Raid> raids;

    /**
     * The Extension where the patch is from
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_extension")
    private Extension extension;

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

    public String getVersionX() {
        return versionX;
    }

    public void setVersionX(String versionX) {
        this.versionX = versionX;
    }

    public String getVersionY() {
        return versionY;
    }

    public void setVersionY(String versionY) {
        this.versionY = versionY;
    }

    public String getVersionZ() {
        return versionZ;
    }

    public void setVersionZ(String versionZ) {
        this.versionZ = versionZ;
    }

    public Set<Raid> getRaids() {
        return raids;
    }

    public void setRaids(Set<Raid> raids) {
        this.raids = raids;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

}
