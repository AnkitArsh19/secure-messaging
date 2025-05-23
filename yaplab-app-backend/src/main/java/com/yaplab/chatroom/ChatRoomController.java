package com.yaplab.chatroom;

import com.yaplab.message.MessageResponseDTO;
import com.yaplab.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {
    public final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/")
    public ResponseEntity<ChatRoomResponseDTO> createPersonalChatroom(
            @RequestBody ChatRoomDTO chatRoomDTO
    ){
        return ResponseEntity.ok(chatRoomService.createChatRoom(chatRoomDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoomResponseDTO>> getUserChatRooms(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(chatRoomService.getUserChatRooms(userId));
    }

    @GetMapping("/{chatroomId}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesFromChatroom(
            @PathVariable String chatroomId
    ){
        return ResponseEntity.ok(chatRoomService.getMessagesFromChatRoom(chatroomId));
    }

    @PostMapping("/{chatroomId}/addParticipant")
    public ResponseEntity<ChatRoomResponseDTO> addParticipantsInGroup(
            @PathVariable String chatroomId,
            @RequestParam Long userId
    ){
        return ResponseEntity.ok(chatRoomService.addParticipantsInGroup(chatroomId, userId));
    }

    @DeleteMapping("/{chatroomId}/removeParticipant")
    public ResponseEntity<ChatRoomResponseDTO> removeParticipantsInGroup(
            @PathVariable String chatroomId,
            @RequestParam Long userId
    ){
        return ResponseEntity.ok(chatRoomService.removeParticipantsInGroup(chatroomId, userId));
    }

    @MessageMapping("/chatroom.join")
    @SendTo("/topic/{chatroomId}")
    public UserDTO joinChatroom(
        @DestinationVariable String chatroomId,
        @Payload UserDTO user
    ){
        chatRoomService.updateLastActivity(chatroomId);
        return user;
    }

    @MessageMapping("/chatroom.leave")
    @SendTo("/topic/{chatroomId}")
    public UserDTO leaveChatroom(
            @DestinationVariable String chatroomId,
            @Payload UserDTO user
    ){
        chatRoomService.updateLastActivity(chatroomId);
        return user;
    }




}
