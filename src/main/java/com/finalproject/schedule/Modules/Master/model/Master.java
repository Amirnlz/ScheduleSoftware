package com.finalproject.schedule.Modules.Master.model;

import com.fasterxml.jackson.annotation.*;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.enums.Roles;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "master_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codemelli")
public class Master implements Serializable {

    @Id
    private String codemelli;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String birthday;
    private String cover;

    private boolean enabled = true;

    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "authorities", joinColumns =
    @JoinColumn(name = "email", referencedColumnName = "email"))
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private List<Roles> roles;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @ManyToMany(mappedBy = "master")
    @JsonIgnore
    private List<Course> course;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Master() {
    }

    public Master(String email, String password, String name, String lastname, String birthday, String cover, MultipartFile file ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.cover = cover;
        this.file = file;
    }

    public String getCodemelli() {
        return codemelli;
    }

    public void setCodemelli(String codemelli) {
        this.codemelli = codemelli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
