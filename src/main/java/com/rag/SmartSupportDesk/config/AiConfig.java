package com.rag.SmartSupportDesk.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(BedrockChatModel model, VectorStore vectorStore) {

        QuestionAnswerAdvisor advisor = new QuestionAnswerAdvisor(vectorStore);

        advisor.setRequireContext(true);
        advisor.setNoContextResponse(
                "I couldn't find any relevant information in our knowledge base. " +
                        "Please rephrase your question or contact support."
        );

        return ChatClient.builder(model)
                .defaultAdvisors(advisor)
                .build();
    }
}

