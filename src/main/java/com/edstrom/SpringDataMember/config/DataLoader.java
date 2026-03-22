package com.edstrom.SpringDataMember.config;

import com.edstrom.SpringDataMember.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataLoader {

    private final MemberService memberService;

    public DataLoader(MemberService memberService){
        this.memberService = memberService;
    }
    @Bean
    CommandLineRunner loadData() {
        return args -> {
            memberService.createSampleData();


        };

    }


}
