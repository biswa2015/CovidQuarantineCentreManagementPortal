package com.CQCMP.CQCMP.Services;

import com.CQCMP.CQCMP.Dto.AddRoomDto;
import com.CQCMP.CQCMP.Dto.AddTestDto;
import com.CQCMP.CQCMP.Dto.AllocateRoomDto;
import com.CQCMP.CQCMP.Dto.AuthRequest;
import com.CQCMP.CQCMP.entity.Admin;
import com.CQCMP.CQCMP.entity.Allocation;
import com.CQCMP.CQCMP.entity.Rooms;
import com.CQCMP.CQCMP.entity.Test;
import com.CQCMP.CQCMP.repo.addRoom_repo;
import com.CQCMP.CQCMP.repo.addTest_repo;
import com.CQCMP.CQCMP.repo.adminLogin_repo;
import com.CQCMP.CQCMP.repo.allocateRoom_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQCMPService {

    @Autowired
    private adminLogin_repo adminlogin_repo;
    @Autowired
    private  JwtService jwtService;

    @Autowired
    private addTest_repo addtest_repo;

    @Autowired
    private addRoom_repo addroom_repo;

    @Autowired
    private allocateRoom_repo allocateroom_repo;
    
    public Admin getAdminById(String id){
        Admin res= adminlogin_repo.getAdminById(id);
        String msg;
        if(res==null){
            msg="Wrong credentials";
        }
        else{
            msg="Login successfull";
        }
        return res;
    }

    public String loginAdmin(AuthRequest authRequest){
        Admin admin=adminlogin_repo.getAdminByEmail(authRequest.getUsername());
        if(admin!=null){
            boolean isMatch=authRequest.getPassword().equals(admin.getPassword());
            if(isMatch){
                String token=jwtService.createToken(admin.getAdminID());
                return token;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    public String addRoom(AddRoomDto addroomDto1){

        int roomNum = addroomDto1.getRoomNum();
        int floorNum=addroomDto1.getFloorNum();
        //We have to check whether the admin is adding or someone else is adding the data
        Rooms room=addroom_repo.getRoomsByRoomAndFloor(roomNum,floorNum);
        if(room==null){
            Rooms room1 = new Rooms();
            room1.setRoomNum(addroomDto1.getRoomNum());
            room1.setFloorNum(addroomDto1.getFloorNum());
            room1.setFreeRoom(addroomDto1.getFreeRoom());
            long id=generateID();
            room1.setRoom_id(String.valueOf(id));
            addroom_repo.save(room1);

            return "Room added successfully";
        }
        else{
            return null;
        }

    }

    public String addTest(AddTestDto addtestDto1){

        String student_id = addtestDto1.getStudent_id();

        //We have to check whether the admin is adding or someone else is adding the data
        Test test=addtest_repo.getTestByStudent(student_id);
        if(test==null){
            //System.out.println(room);
            Test test1 = new Test();
            test1.setStudent_id(addtestDto1.getStudent_id());
            test1.setResult(addtestDto1.getResult());
            long id=generateID();
            test1.setTest_id(String.valueOf(id));
            addtest_repo.save(test1);

            return "Test record added successfully";
        }
        else{
            return null;
        }

    }

    public String allocateRoom(AllocateRoomDto allocateRoomDto){

        String student_id = allocateRoomDto.getStudent_id();
        String room_id=allocateRoomDto.getRoom_id();
        //We have to check whether the admin is adding or someone else is adding the data
        Allocation alloc=allocateroom_repo.getExistingAllocation(room_id,student_id);
        if(alloc==null){
            //System.out.println(room);
            Allocation alloc1 = new Allocation();
            alloc1.setStudent_id(allocateRoomDto.getStudent_id());
            alloc1.setRoom_id(allocateRoomDto.getRoom_id());
            long id=generateID();
            alloc1.setAlloc_id(String.valueOf(id));
            allocateroom_repo.save(alloc1);
            allocateroom_repo.updateRoom(allocateRoomDto.getRoom_id(),0);
            return "Allocation successful";

        }
        else{
            return null;
        }

    }

    public long generateID(){
        long id=(long) Math.floor(Math.random()*9_000_000_000L)+1_000_000_000L;
        return id;
    }
}
