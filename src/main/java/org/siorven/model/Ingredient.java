package org.siorven.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Andoni on 21/05/2017.
 */
public class Ingredient {

    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "resource", cascade = CascadeType.ALL)
    private Resource resource;
}
