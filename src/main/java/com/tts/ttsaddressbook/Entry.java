package com.tts.ttsaddressbook;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Entry
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // allows id to be generated by the database
    @Column(name = "id")
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(unique = true, name = "email")
    private String email;

    @Column // Name of column in database
    @CreationTimestamp // allows the date to be generated by the system
    private Date entryRecorded;

    // Empty Constructor
    public Entry()
    {

    }

    // Full arg Constructor
    public Entry(String firstName, String lastName, String phoneNumber, String email, Date entryRecorded)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.entryRecorded = entryRecorded;
    }

    // Getters & Setters
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getEntryRecorded()
    {
        return entryRecorded;
    }

    public void setEntryRecorded(Date entryRecorded)
    {
        this.entryRecorded = entryRecorded;
    }

    // toString()
    @Override
    public String toString()
    {
        return "Entry [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", entryRecorded=" + entryRecorded + "]";
    }

}