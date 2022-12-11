package com.location.keyword.model;

import javax.persistence.*;

@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @Column
    public String name;

    @Column
    public long count;

    public Keyword() {
    }

    public Keyword(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
