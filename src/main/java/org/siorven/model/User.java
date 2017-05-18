package org.siorven.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andoni on 17/05/2017.
 */

@Entity
@Table(name="user")
public class User {

    private String username;

    private String password;
    
}
