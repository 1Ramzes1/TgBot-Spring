package com.example.demo.helpers;

import com.example.demo.models.BookModel;
import com.example.demo.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorHelper {
    @Autowired
    BookRepo bookRepo;

    private static DoctorHelper doctorHelper = null;

    public DoctorHelper(){
        doctorHelper = this;
    }

    public static void save(BookModel b){
        doctorHelper.bookRepo.save(b);
    }
    public static List<String> getFreeTimes(DoctorEnum d){
        TimeControl timeControl = new TimeControl();
        List<BookModel> bookModelsList = doctorHelper.bookRepo.findBookModelsByDoctorEnum(d);
        List<String> freeTimes = new ArrayList<>();
        freeTimes = timeControl.getTimes();

        List<String> list = new ArrayList<>();
        for(BookModel b: bookModelsList){
            for (String str: freeTimes){
                if(b.getTime().equals(str)){
                    list.add(b.getTime());
                }
            }
        }
        freeTimes.remove(list);
        return freeTimes;
    }
}
