package com.realdolmen.togethair.domain.booking.pricing;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PriceSetting {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriceSettingLevel level;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriceSettingType type;
    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
