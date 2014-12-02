package com.musala.db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity implements Serializable{
    private static final long serialVersionUID = 75986582104478L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID")
    private long categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToMany(mappedBy="categories")
    private List<ArticleEntity> articles = new ArrayList<ArticleEntity>();

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEntity() {
    }
}
