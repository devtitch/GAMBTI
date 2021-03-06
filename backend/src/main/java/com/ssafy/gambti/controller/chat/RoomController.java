package com.ssafy.gambti.controller.chat;

import com.ssafy.gambti.dto.basicResponse.Response;
import com.ssafy.gambti.dto.chat.GroupRoomRequest;
import com.ssafy.gambti.dto.chat.RoomRequest;
import com.ssafy.gambti.service.chat.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name="Chatting", description = "Chat REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "rooms")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final RoomService roomService;

    @PostMapping(value = "/get")
    @Operation(summary = "1:1 chatting roomid 받기", description = "상대방 uid를 받아와서 chat room id를 받는다.")
    public ResponseEntity<? extends Response> getRoom(@RequestBody RoomRequest roomRequest, HttpServletRequest httpServletRequest) {
        String roomId = roomService.getRoom(roomRequest, httpServletRequest);
        if(roomId != null)
            return new ResponseEntity<>(new Response(SUCCESS, "roomId 조회 성공", roomId), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Response(FAIL, "roomId 조회 실패", null), HttpStatus.OK);
    }
    @PostMapping(value = "/getGroupRoom")
    @Operation(summary = "Group chat room", description = "점수를 구해서 group room을 만들거나 해당 유저를 room에 넣어준다.")
    public ResponseEntity<? extends Response> getGroupRoom(@RequestBody GroupRoomRequest groupRoomRequest, HttpServletRequest httpServletRequest) {
        String roomId = roomService.getGroupRoom(groupRoomRequest, httpServletRequest);
        if(roomId !=null)
            return new ResponseEntity<>(new Response(SUCCESS, "roomId 조회 성공", roomId), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Response(FAIL, "roomId 조회 실패", null), HttpStatus.OK);
    }
}