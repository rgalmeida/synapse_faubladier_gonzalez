package fr.synapsegaming.raid.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>BossDifficultyAssociation</b> stands for the association between
 * <b>Boss</b> and <b>Difficulty</b>
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_achievement", unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_boss", updatable = false, insertable = false)
    private Boss boss;

    @ManyToOne
    @JoinColumn(name = "id_difficulty", updatable = false, insertable = false)
    private Difficulty difficulty;

    @Column(name = "down", nullable = false)
    private boolean down;

    @Column(name = "date_down")
    private Date dateDown;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raid")
    private Raid raid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Raid getRaid() {
        return raid;
    }

    public void setRaid(Raid raid) {
        this.raid = raid;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public Date getDateDown() {
        return dateDown;
    }

    public void setDateDown(Date dateDown) {
        this.dateDown = dateDown;
    }

}
