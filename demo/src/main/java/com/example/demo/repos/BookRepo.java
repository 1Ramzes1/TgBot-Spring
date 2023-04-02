package com.example.demo.repos;

import com.example.demo.helpers.DoctorEnum;
import com.example.demo.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookModel, Long> {

    List<BookModel> findBookModelsByDoctorEnum(DoctorEnum d);
}
