package com.realdolmen.togethair.domain.pricing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by JCEBF12 on 7/11/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GeneralPricing {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;
    private double value;
    @NotNull
    private int priority;
    private String name;


    public GeneralPricing(Type type, double value, int priority, String name) {
        this.type = type;
        this.value = value;
        this.priority = priority;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

