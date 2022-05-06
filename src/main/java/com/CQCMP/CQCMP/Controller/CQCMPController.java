package com.CQCMP.CQCMP.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger= LogManager.getLogger(CQCMPController.class);

    @PostMapping(value = "/login-admin")
    public ResponseEntity<?> loginAdmin(@RequestBody AuthRequest authRequest){
        logger.info("[Admin-Login] ");

        String msg= cqcmpService.loginAdmin(authRequest);
        if(msg==null){
            logger.info("[Admin-Login] - Failed");
            ResponseEntity<String> response=new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            return response;
        }
        logger.info("[Admin-Login] - Successful");
        return ResponseEntity.ok(msg);
    }


    @PostMapping(value="/add-room")
    public ResponseEntity<?> addRoom(@RequestBody AddRoomDto addRoomDto){
        //String adminToken=jwtService.extractID(token);
//        if(adminToken==null)
//            return ResponseEntity.ok("Not authorized");
        logger.info("[Add-Room]");
        String msg= cqcmpService.addRoom(addRoomDto);
        if(msg==null){
            logger.info("[Add-Room] - Failed");
            ResponseEntity<String> response=new ResponseEntity<>("Room already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        logger.info("[Add-Room] - Successful");
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/add-member")
    public ResponseEntity<?> addMember(@RequestBody AddMember addMember){
        System.out.println(addMember.getSEmail());
        logger.info("[Add-Member] ");
        String msg= cqcmpService.addMember(addMember);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Student already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("[Add-Member] - Failed");
            return response;
        }
        logger.info("[Add-Member] - Successful");
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/add-test")
    public ResponseEntity<?> addTest(@RequestBody AddTestDto addtestdto){
        logger.info("[Add-Test] ");
        String msg= cqcmpService.addTest(addtestdto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Test record already exists ",HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("[Add-Test] -Failed");
            return response;
        }
        logger.info("[Add-Test] -Successful");
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/allocate-room")
    public ResponseEntity<?> allocateRoom(@RequestBody AllocateRoomDto allocateRoomDto){
        logger.info("[Allocate-Room] ");
        String msg= cqcmpService.allocateRoom(allocateRoomDto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Either room or the student is already allocated ",HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("[Allocate-Room] - Failed");
            return response;
        }
        logger.info("[Allocate-Room] - Successful");
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value="/deallocate-room")
    public ResponseEntity<?> deallocateRoom(@RequestBody DeallocateRoom_Dto deallocateRoom_dto){
        logger.info("[Deallocate-Room] ");
        String msg= cqcmpService.deallocateRoom(deallocateRoom_dto);
        if(msg==null){
            ResponseEntity<String> response=new ResponseEntity<>("Internal server error ",HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("[Deallocate-Room] - Failed");
            return response;
        }
        logger.info("[Deallocate-Room] - Successful");
        return ResponseEntity.ok(msg);
    }

    @GetMapping(value = "/get-freerooms")
    public ResponseEntity<?> getFreeRooms(){
        logger.info("[Get-FreeRooms] ");
        List<Rooms> rooms= cqcmpService.getRooms(1);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(value = "/get-allocations")
    public ResponseEntity<?> getAllocations(){
        logger.info("[Get-Allocations] ");
        List<Allocation> allocationList= cqcmpService.getAllocations();
        return ResponseEntity.ok(allocationList);
    }

    @GetMapping(value = "/get-positiveStudents")
    public ResponseEntity<?> getPositiveStudents(){
        logger.info("[Get-Positive Students] ");
        List<Test> students= cqcmpService.getPositiveStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping(value="/send-Email")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmailDto sendEmailDto){
        logger.info("[Send-Email] ");
        cqcmpService.sendEmail(sendEmailDto);
        return ResponseEntity.ok("Email sent successfully");
    }
}
