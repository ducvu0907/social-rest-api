package com.dev.SocialMedia.notification;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto {
    private Long contentId;
    private String type;
    private String message;
    private LocalDateTime timestamp;
}
