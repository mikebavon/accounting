package com.bavon.app.model;

import javax.persistence.*;

@Entity
@Table(name = "boyfriends")
public class BoyFriend extends BaseEntity{

    @Column
    private String name;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "boyFriend", cascade = CascadeType.ALL)
    private GirlFriend girlFriend;

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

    public GirlFriend getGirlFriend() {
        return girlFriend;
    }

    public void setGirlFriend(GirlFriend girlFriend) {
        if (girlFriend != null)
            girlFriend.setBoyFriend(this);

        this.girlFriend = girlFriend;
    }
}
