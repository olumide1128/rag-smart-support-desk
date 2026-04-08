package com.rag.SmartSupportDesk;

import com.rag.SmartSupportDesk.service.VectorStoreVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartSupportDeskApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SmartSupportDeskApplication.class, args);
    }

    @Autowired
    VectorStoreVerify testVectorStore;

    @Override
    public void run(String... args) throws Exception {

        testVectorStore.loadDocs();

        String query = "Where do Transformers run?";

        String result = testVectorStore.search(query);

        System.out.println(result);

    }
}
