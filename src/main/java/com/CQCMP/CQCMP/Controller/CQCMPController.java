package com.CQCMP.CQCMP.Controller;

import com.CQCMP.CQCMP.Dto.*;
import com.CQCMP.CQCMP.Services.CQCMPService;
import com.CQCMP.CQCMP.Services.JwtService;
import com.CQCMP.CQCMP.entity.Allocation;
import com.CQCMP.CQCMP.entity.Rooms;
import com.CQCMP.CQCMP.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class CQCMPController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CQCMPService cqcmpService;

    @PostMapping(value = "/login-admin")
    public ResponseEntity<?> loginPatient(@RequestBody AuthRequest authRequest){
        String msg= cqcmpService.loginAdmin(authRequest);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            return response;
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/add-room")
    public ResponseEntity<?> addRoom(@RequestBody AddRoomDto addRoomDto){
        String msg= cqcmpService.addRoom(addRoomDto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Room already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/add-member")
    public ResponseEntity<?> addMember(@RequestBody AddMember addMember){
        System.out.println(addMember.getSEmail());
        String msg= cqcmpService.addMember(addMember);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Student already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/add-test")
    public ResponseEntity<?> addTest(@RequestBody AddTestDto addtestdto){
        String msg= cqcmpService.addTest(addtestdto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Test record already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/allocate-room")
    public ResponseEntity<?> allocateRoom(@RequestBody AllocateRoomDto allocateRoomDto){
        String msg= cqcmpService.allocateRoom(allocateRoomDto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Either room or the student is already allocated ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/deallocate-room")
    public ResponseEntity<?> deallocateRoom(@RequestBody DeallocateRoom_Dto deallocateRoom_dto){
        String msg= cqcmpService.deallocateRoom(deallocateRoom_dto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Internal server error ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        return ResponseEntity.ok(msg);
    }

    @GetMapping(value = "/get-freerooms")
    public ResponseEntity<?> getFreeRooms(){
        List<Rooms> rooms= cqcmpService.getRooms(1);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(value = "/get-allocations")
    public ResponseEntity<?> getAllocations(){
        List<Allocation> allocationList= cqcmpService.getAllocations();
        return ResponseEntity.ok(allocationList);
    }

    @GetMapping(value = "/get-positiveStudents")
    public ResponseEntity<?> getPositiveStudents(){
        List<Test> students= cqcmpService.getPositiveStudents();
        return ResponseEntity.ok(students);
    }
}
