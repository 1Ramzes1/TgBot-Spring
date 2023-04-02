package com.example.demo.models;
import com.example.demo.helpers.DoctorEnum;
import jakarta.persistence.*;
import lombok.Data;



@Table(name = "telegram_user")
@Entity
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "username")
    String username;

    @Column(name = "telegram_id")
    String tgId;

    @Column(name = "name")
    String name;

    @Column(name = "wanted_doc")

    @Enumerated
    DoctorEnum doctorEnum;
}
