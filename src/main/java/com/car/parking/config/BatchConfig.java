package com.car.parking.config;

import com.car.parking.model.CarParking;
import com.car.parking.model.ParkingLot;
import com.car.parking.util.CarParkingConstants;
import com.car.parking.util.ParkingLotRESTClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
 
    @Value("classPath:/input/hdb-carpark-information.csv")
    private Resource inputResource;

    @Bean
    public Job readRESTJob() {
        return jobBuilderFactory
                .get("readRESTJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("step1")
                .<ParkingLot, ParkingLot>chunk(5)
                .reader(parkingLotReader())
                .processor(parkingLotProcessor())
                .writer(parkingLotWriter())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get("step2")
                .<CarParking, CarParking>chunk(5)
                .reader(carParkingReader())
                .processor(carParkingProcessor())
                .writer(carParkingWriter())
                .build();
    }

     
    @Bean
    public ItemProcessor<CarParking, CarParking> carParkingProcessor() {
        return new CarParkingProcessor();
    }
     
    @Bean
    public FlatFileItemReader<CarParking> carParkingReader() {
        FlatFileItemReader<CarParking> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
 
    @Bean
    public LineMapper<CarParking> lineMapper() {
        DefaultLineMapper<CarParking> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] { "car_park_no", "address", "x_coord", "y_coord","car_park_type","type_of_parking_system","short_term_parking","free_parking", "night_parking", "car_park_decks", "gantry_height", "car_park_basement"});
        lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
        BeanWrapperFieldSetMapper<CarParking> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CarParking.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
 
    @Bean
    public JdbcBatchItemWriter<CarParking> carParkingWriter() {
        JdbcBatchItemWriter<CarParking> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource());
        itemWriter.setSql(CarParkingConstants.QUERY_INSERT_CARPARKING);
        ItemPreparedStatementSetter<CarParking> valueSetter = new CarParkingPreparedStatementSetter();
        itemWriter.setItemPreparedStatementSetter(valueSetter);
        return itemWriter;
    }
     
    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:CarParking.sql")
                .setType(EmbeddedDatabaseType.H2) 
                .build();
    }

    @Bean
    public ItemProcessor<ParkingLot, ParkingLot> parkingLotProcessor() {
        return new ParkingLotProcessor();
    }

    @Bean
    public ListItemReader<ParkingLot> parkingLotReader() {
        return new ListItemReader<>(ParkingLotRESTClient.read());
    }
    @Bean
    public JdbcBatchItemWriter<ParkingLot> parkingLotWriter() {
        JdbcBatchItemWriter<ParkingLot> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource());
        itemWriter.setSql(CarParkingConstants.QUERY_INSERT_PARKINGLOT);
        ItemPreparedStatementSetter<ParkingLot> valueSetter = new ParkingLotPreparedStatementSetter();
        itemWriter.setItemPreparedStatementSetter(valueSetter);
        return itemWriter;
    }
}