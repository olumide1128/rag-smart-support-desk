package com.rag.SmartSupportDesk.service;

import org.slf4j.Logger;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VectorStoreVerify {

    @Autowired
    private VectorStore vectorStore;

    Logger logger = org.slf4j.LoggerFactory.getLogger(VectorStoreVerify.class);

    public void loadDocs() {
        List<Document> docs = List.of(
                new Document("Spring AI is great for RAG."),
                new Document("Transformers ONNX embeddings run locally."),
                new Document("Donald Trump is the president of America."),
                new Document("Our Refund Policy allows returns within 30 days of purchase.")
        );

        vectorStore.accept(docs);
    }

    public String search(String query) {
        List<Document> docs = vectorStore.similaritySearch(query);
        System.out.println(docs);
        Optional<Document> result = docs.stream().findFirst();

        return result.map(Document::getText).orElse("No relevant documents found.");
    }
}
