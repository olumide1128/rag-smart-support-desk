package com.rag.SmartSupportDesk.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.bedrockagentruntime.BedrockAgentRuntimeClient;
import software.amazon.awssdk.services.bedrockagentruntime.model.KnowledgeBaseQuery;
import software.amazon.awssdk.services.bedrockagentruntime.model.RetrieveRequest;
import software.amazon.awssdk.services.bedrockagentruntime.model.RetrieveResponse;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BedrockKbRetriever implements DocumentRetriever {

    private final BedrockAgentRuntimeClient client;
    private final String knowledgeBaseId;

    @Value("${aws.bedrock.retrieval.similarity-threshold:0.5}")
    private double similarityThreshold;

    public BedrockKbRetriever(BedrockAgentRuntimeClient client, @Value("${aws.knowledge-base-id}") String knowledgeBaseId) {
        this.client = client;
        this.knowledgeBaseId = knowledgeBaseId;
    }

    @Override
    public List<Document> retrieve(Query query) {

        // Extract the plain text query from Spring AI's Query object
        String text = query.text();

        KnowledgeBaseQuery retrievalQuery = KnowledgeBaseQuery.builder()
                .text(text)
                .build();


        RetrieveRequest retrieveRequest = RetrieveRequest.builder()
                .knowledgeBaseId(knowledgeBaseId)
                .retrievalQuery(retrievalQuery)
                .build();

        RetrieveResponse response = client.retrieve(retrieveRequest);

        return response.retrievalResults().stream()
                .filter(r -> r.score() >= similarityThreshold)
                .map(r -> new Document(
                        r.content().text()
                ))
                .collect(Collectors.toList());
    }

}

