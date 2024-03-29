package com.example.chat_gpt_clone_be.service;

import com.example.chat_gpt_clone_be.dto.request.ChatRequest;
import com.example.chat_gpt_clone_be.dto.response.ContentResponse;
import com.example.chat_gpt_clone_be.dto.response.Response;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatCompletionService {
    @Value("${OPENAI_API_KEY}")
    private String API_KEY;

    public Response getChatCompletion(ChatRequest request) {
        OpenAiService service = new OpenAiService(API_KEY);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(request.getMessages())
                .maxTokens(2048)
                .temperature(0.0)
                .build();

        String content;
        try {
            content = service.createChatCompletion(chatCompletionRequest).getChoices().stream().findFirst().get().getMessage().getContent();
        } catch (Exception e) {
            return Response
                    .builder()
                    .status(500)
                    .message("Something went wrong! Try again.")
                    .build();
        }
        return Response
                .builder()
                .status(200)
                .message("Successfully!")
                .data(ContentResponse
                        .builder()
                        .content(content)
                        .build())
                .build();
    }
}
