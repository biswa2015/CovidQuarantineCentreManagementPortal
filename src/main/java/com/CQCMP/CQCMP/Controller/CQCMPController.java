package com.CQCMP.CQCMP.Controller;

import com.CQCMP.CQCMP.Dto.AddRoomDto;
import com.CQCMP.CQCMP.Dto.AddTestDto;
import com.CQCMP.CQCMP.Dto.AllocateRoomDto;
import com.CQCMP.CQCMP.Dto.AuthRequest;
import com.CQCMP.CQCMP.Services.CQCMPService;
import com.CQCMP.CQCMP.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> addTest(@RequestBody AllocateRoomDto allocateRoomDto){
        String msg= cqcmpService.allocateRoom(allocateRoomDto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Either room or the student is already allocated ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        return ResponseEntity.ok(msg);
    }
}
