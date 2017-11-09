package com.realdolmen.togethair.domain.booking.pricing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PriceSetting {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PriceSettingLevel level;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PriceSettingType type;
    @NotNull
    private double value;

    @NotNull
    private int priority = 100;


    public PriceSetting() {}

    public PriceSetting(PriceSettingLevel level, PriceSettingType type, double value, int priority, String name) {
        this(level, type, value, priority);
        this.name = name;
    }

    public PriceSetting(PriceSettingLevel level, PriceSettingType type, double value, int priority) {
        this(level, type, value);
        this.priority = priority;
    }

    public PriceSetting(PriceSettingLevel level, PriceSettingType type, double value, String name) {
        this(level, type, value);
        this.name = name;
    }

    public PriceSetting(PriceSettingLevel level, PriceSettingType type, double value) {
        this.level = level;
        this.type = type;
        this.value = value;
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

    public PriceSettingLevel getLevel() {
        return level;
    }

    public void setLevel(PriceSettingLevel level) {
        this.level = level;
    }

    public PriceSettingType getType() {
        return type;
    }

    public void setType(PriceSettingType type) {
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
}
