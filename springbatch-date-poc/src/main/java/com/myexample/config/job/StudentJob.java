package com.myexample.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myexample.model.Student;
import com.myexample.writter.StudentWritter;

@Configuration
public class StudentJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FlatFileItemReader<Student> studentReader;

    @Autowired
    private StudentWritter studentWritter;

    @Bean
    public Job readstudentCSVFileJob() {
        return jobBuilderFactory.get("readstudentCSVFileJob").incrementer(new RunIdIncrementer())
                .start(countryCurrStepOne()).build();
    }

    @Bean
    public Step countryCurrStepOne() {
        return stepBuilderFactory.get("studentStepOne").<Student, Student>chunk(5).reader(studentReader)
                .writer(studentWritter).build();
    }
}