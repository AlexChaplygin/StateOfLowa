package com.alex.che.stateoflowa.configuration;

import javax.sql.DataSource;

import com.alex.che.stateoflowa.entity.Voter;
import com.alex.che.stateoflowa.utils.DBVotersLogProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classPath:/input/State_of_Iowa_-_Monthly_Voter_Registration_Totals_by_County.csv")
    private Resource inputResource;

    @Bean
    public Job readCSVFileJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<Voter> writer) {
        return stepBuilderFactory
                .get("step")
                .<Voter, Voter>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public ItemProcessor<Voter, Voter> processor() {
        return new DBVotersLogProcessor();
    }

    @Bean
    public FlatFileItemReader<Voter> reader() {
        FlatFileItemReader<Voter> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }

    @Bean
    public LineMapper<Voter> lineMapper() {
        DefaultLineMapper<Voter> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("Date",
                "FIPS",
                "County",
                "Democrat - Active",
                "Republican - Active",
                "Libertarian - Active",
                "No Party - Active",
                "Other - Active",
                "Total - Active",
                "Democrat - Inactive",
                "Republican - Inactive",
                "Libertarian - Inactive",
                "No Party - Inactive",
                "Other - Inactive",
                "Total - Inactive",
                "Grand Total",
                "Primary Lat Dec",
                "Primary Long Dec",
                "Primary County Coordinates");
        lineTokenizer.setIncludedFields(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new RecordFieldSetMapper());
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<Voter> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Voter> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO VOTER (" +
                "date,\n" +
                "month,\n" +
                "fips,\n" +
                "county,\n" +
                "democrat_active,\n" +
                "democrat_inactive,\n" +
                "republican_active,\n" +
                "libertarian_active,\n" +
                "no_party_active,\n" +
                "other_active,\n" +
                "total_active,\n" +
                "republican_inactive,\n" +
                "libertarian_inactive,\n" +
                "no_party_inactive,\n" +
                "other_inactive,\n" +
                "total_inactive,\n" +
                "grand_total,\n" +
                "primary_lat_dec,\n" +
                "primary_long_dec,\n" +
                "primary_county_coordinates) VALUES (" +
                ":date,\n" +
                ":month,\n" +
                ":fips,\n" +
                ":county,\n" +
                ":democratActive,\n" +
                ":democratInactive,\n" +
                ":republicanActive,\n" +
                ":libertarianActive,\n" +
                ":noPartyActive,\n" +
                ":otherActive,\n" +
                ":totalActive,\n" +
                ":republicanInactive,\n" +
                ":libertarianInactive,\n" +
                ":noPartyInactive,\n" +
                ":otherInactive,\n" +
                ":totalInactive,\n" +
                ":grandTotal,\n" +
                ":primaryLatDec,\n" +
                ":primaryLongDec,\n" +
                ":primaryCountyCoordinates)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return itemWriter;
    }
}

