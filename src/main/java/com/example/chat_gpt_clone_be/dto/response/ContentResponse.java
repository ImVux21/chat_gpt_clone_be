package com.example.chat_gpt_clone_be.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentResponse {
    private String content;
}
