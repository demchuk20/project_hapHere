package com.nulp.hapHere.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post")
    private Integer idPost;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "img")
    private String img;
    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id_user", nullable = false)
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(idPost, post.idPost) &&
                Objects.equals(title, post.title) &&
                Objects.equals(img, post.img) &&
                Objects.equals(category, post.category) &&
                Objects.equals(date, post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPost, title, img, category, date);
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost=" + idPost +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
