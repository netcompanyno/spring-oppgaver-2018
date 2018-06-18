package com.github.netcompanyno.springkurs.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class User {

    private final long id;
    private final String email;
    private final String firstName;
    private final String lastName;
    
    @JsonCreator
    public User(@JsonProperty("email") final String email, 
                @JsonProperty("firstname") final String firstName, 
                @JsonProperty("lastname") final String lastName) {
        this(0, email, firstName, lastName);
    }

    public User(final long id, final String email, final String firstName, final String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", this.id)
                .append("email", this.email)
                .append("firstName", this.firstName)
                .append("lastName", this.lastName)
                .toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        
        final User that = (User) o;
        
        return new EqualsBuilder()
                .append(this.id, that.id)
                .append(this.email, that.email)
                .append(this.firstName , that.firstName)
                .append(this.lastName, that.firstName)
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(email)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }

}
