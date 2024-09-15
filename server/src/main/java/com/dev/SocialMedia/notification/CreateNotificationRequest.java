package com.dev.SocialMedia.notification;

import lombok.Data;

@Data
public class CreateNotificationRequest {
    private String type;
    private Long contentId;
    private String message;
}
