package hello;

import hello.neo.Person;
import java.io.File;

import java.util.Random;
import org.neo4j.graphdb.Transaction;
import org.neo4j.io.fs.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.GraphDatabase;

@SpringBootApplication
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File(ApplicationConfig.DB));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner demo(PersonRepository personRepository,
            GraphDatabase graphDatabase) {
        return args -> {
            Random rand = new Random(10);

            log.info("Before linking up with Neo4j...");
            long start = System.currentTimeMillis();
            long mark = start;
            for (int j = 0; j < 10; j++) {
                try (Transaction tx = graphDatabase.beginTx()) {
                    for (int i = 0; i < 100; i++) {
                        Person greg = new Person(rand);
                        personRepository.save(greg);
                        tx.success();
                    }
                }
                long now = System.currentTimeMillis();
                System.out.format("%d : Time:%d\n", j, now -mark);
                mark = now;
            }
        };
    }

}
