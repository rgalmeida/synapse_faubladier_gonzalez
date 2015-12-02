package fr.synapsegaming.media.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b>Video</b> stands for the video's informations
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "videos")
public class Video {

    /**
     * Resource's technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_video", unique = true, nullable = false)
    private long id;

    /**
     * The title of the Video
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * A description or resume about the Video
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The integration code provided by the streamers (Youtube, Daylimotion...)
     */
    @Column(name = "integrationCode", nullable = false)
    private String integrationCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntegrationCode() {
        return integrationCode;
    }

    public void setIntegrationCode(String integrationCode) {
        this.integrationCode = integrationCode;
    }

}
