package com.car.parking;

import com.car.parking.config.CarParkingPreparedStatementSetter;
import com.car.parking.config.CarParkingProcessor;
import com.car.parking.model.CarParking;
import com.car.parking.util.CarParkingConstants;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfigTest {
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<CarParking> itemReader, ItemProcessor<CarParking, CarParking> itemProcessor, ItemWriter<CarParking> itemWriter) {
        Step step = stepBuilderFactory.get("readCSVFile")
                .<CarParking, CarParking>chunk(1)
                .reader(carParkingReader())
                .processor(carParkingProcessor())
                .writer(carParkingWriter())
                .build();
        return jobBuilderFactory
                .get("job")
                .incrementer(new RunIdIncrementer())
                .start(step).build();
    }
    @Bean
    public ItemProcessor<CarParking, CarParking> carParkingProcessor() {
        return new CarParkingProcessor();
    }

    @Bean
    public FlatFileItemReader<CarParking> carParkingReader() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("data/carpark_testdata.csv");
        File file = new File(url.getPath());
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(file.getPath());
        FlatFileItemReader<CarParking> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource( resource);
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
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:CarParking.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}