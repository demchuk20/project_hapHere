package com.nulp.hapHere.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "created_post", schema = "mydb")
public class CreatedPost {
    @Id
    @Column(name = "post")
    private Integer postId;
    @Basic
    @Column(name = "moderated")
    private Boolean moderated;
    @Basic
    @Column(name = "edited")
    private Boolean edited;
    @Basic
    @Column(name = "moderated_by")
    private Integer moderated_by;
    @OneToOne
    @JoinColumn(name = "post", referencedColumnName = "id_post", nullable = false)
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedPost that = (CreatedPost) o;
        return Objects.equals(postId, that.postId) &&
                Objects.equals(moderated, that.moderated) &&
                Objects.equals(edited, that.edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, moderated, edited);
    }


}
