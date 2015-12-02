package fr.synapsegaming.media.entity;

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

import fr.synapsegaming.media.enums.ArticleTypeEnum;

/**
 * <b>Blog</b> stands for the blog's articles
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "articles_types")
public class ArticleType {

    /**
     * The technical unique identifier for the article's type
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article_type", unique = true, nullable = false)
    private long id;

    /**
     * The name of the article's type
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The list of articles from a type
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_article_type")
    private Set<Article> articles;

    public ArticleType(ArticleTypeEnum type) {
        this.id = type.getCode();
        this.name = type.toString();
    }

    public ArticleType() {

    }

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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

}
