package com.rag.SmartSupportDesk.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RagService {

    private final ChatClient chatClient;

    public RagService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

//    public String ask(String question) {
//
//        Query query = new Query(question);
//
//        // Step 1 — Retrieve manually (ONE retrieval)
//        List<Document> docs = retriever.retrieve(query);
//
//        // Step 2 — If empty, avoid LLM call
//        if (docs.isEmpty()) {
//            return "I could not find any relevant information in the knowledge base.";
//        }
//
//        // 3. Build a simple context string from the retrieved documents
//        String context = docs.stream()
//                .map(Document::getText)
//                .limit(5) // optional: cap number of docs
//                .collect(Collectors.joining("\n\n"));
//
//        String prompt = """
//                You are a helpful assistant. Use ONLY the context below to answer the question.
//
//                Context:
//                %s
//
//                Question:
//                %s
//                """.formatted(context, question);
//
//        // 4. Call the LLM once, with explicit context
//        return chatClient
//                .prompt()
//                .user(prompt)
//                .call()
//                .content();
//
//    }
}

