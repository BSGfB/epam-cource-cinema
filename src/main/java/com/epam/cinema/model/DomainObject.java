package com.epam.cinema.model;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Yuriy_Tkach
 */
@XmlTransient
public class DomainObject {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
