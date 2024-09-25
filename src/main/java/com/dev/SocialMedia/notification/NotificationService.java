package com.dev.SocialMedia.notification;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapper;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    // i think it makes more sense to use this in other service handlers instead of as a stand-alone api
    public ApiResponse createNotification(Long userId, CreateNotificationRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        Notification notification = Notification.builder()
                .type(request.getType())
                .message(request.getMessage())
                .contentId(request.getContentId())
                .user(user)
                .timestamp(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        return new ApiResponse("success", "create notifications successfully", mapper.mapNotificationToNotificationDto(notification));
    }

    public ApiResponse getUserNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        List<Notification> notifications = notificationRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("notifications not found"));
        return new ApiResponse("success", "get notifications successfully", mapper.mapListNotificationToListNotificationDto(notifications));
    }
}
