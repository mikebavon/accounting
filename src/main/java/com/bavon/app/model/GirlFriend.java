package com.bavon.app.model;

import javax.persistence.*;

@Entity
@Table(name = "girl_friends")
public class GirlFriend extends BaseEntity{

    @Column
    private String name;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @OneToOne
    private BoyFriend boyFriend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BoyFriend getBoyFriend() {
        return boyFriend;
    }

    public void setBoyFriend(BoyFriend boyFriend) {
        this.boyFriend = boyFriend;
    }
}
