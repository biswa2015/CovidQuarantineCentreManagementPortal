package com.CQCMP.CQCMP;

import com.CQCMP.CQCMP.Dto.*;
import com.CQCMP.CQCMP.Services.CQCMPService;
import com.CQCMP.CQCMP.Services.JwtService;
import com.CQCMP.CQCMP.entity.*;
import com.CQCMP.CQCMP.repo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;


public class CQCMPTestService {

    //@Mock
    private adminLogin_repo adminlogin_repo;

    private JwtService jwtService;


    private addTest_repo addtest_repo;


    private addRoom_repo addroom_repo;


    private AddMember_repo addMember_repo;


    private GetRooms_repo getRooms_repo;


    private allocateRoom_repo allocateroom_repo;

    CQCMPService service=new CQCMPService();

    //private Admin

    @BeforeEach
    public void setup()
    {
        adminlogin_repo=Mockito.mock(adminLogin_repo.class);
        jwtService=Mockito.mock(JwtService.class);
        addroom_repo=Mockito.mock(addRoom_repo.class);
        getRooms_repo=Mockito.mock(GetRooms_repo.class);
        ReflectionTestUtils.setField(service,"adminlogin_repo",adminlogin_repo);
        ReflectionTestUtils.setField(service,"jwtService",jwtService);
        ReflectionTestUtils.setField(service,"addroom_repo",addroom_repo);
        ReflectionTestUtils.setField(service,"getRooms_repo",getRooms_repo);
    }
    @org.junit.jupiter.api.Test
    public void testGetAdminById(){
        //CQCMPService service=new CQCMPService();
        Admin admin=new Admin();
        admin.setName("admin");
        Mockito.when(adminlogin_repo.getAdminById(ArgumentMatchers.anyString())).thenReturn(admin);
        Admin res= service.getAdminById("7676");
        Assertions.assertEquals("admin",res.getName());
        /*String msg;
        if(res==null){
            msg="Wrong credentials";
        }
        else{
            msg="Login successfull";
        }
        System.out.println(msg);*/
    }

    public long generateID(){
        long id=(long) Math.floor(Math.random()*9_000_000_000L)+1_000_000_000L;
        return id;
    }

    @org.junit.jupiter.api.Test
    public void testloginAdmin(){
        Admin admin=new Admin();
        admin.setName("admin");
        admin.setAdmin_ID("adminId");
        admin.setPassword("password");
        Mockito.when(adminlogin_repo.getAdminByEmail(ArgumentMatchers.anyString())).thenReturn(admin);
        Mockito.when(jwtService.createToken(ArgumentMatchers.anyString())).thenReturn("token");
        AuthRequest authRequest=new AuthRequest();
        authRequest.setPassword("password");
        authRequest.setEmail("Admin");
        String res=service.loginAdmin(authRequest);
        Assertions.assertEquals("token",res);
//        if(admin!=null){
//            boolean isMatch=authRequest.getPassword().equals(admin.getPassword());
//            if(isMatch){
//                String token=jwtService.createToken(admin.getAdminID());
//                return token;
//            }
//            else{
//                return null;
//            }
//        }
//        else{
//            return null;
//        }
    }

    @org.junit.jupiter.api.Test
    public void addRoom(){

        AddRoomDto addRoomDto=new AddRoomDto();
        addRoomDto.setRoomNum(100);
        addRoomDto.setFloorNum(1);

        Mockito.when(addroom_repo.getRoomsByRoomAndFloor(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt())).thenReturn(null);
        String res=service.addRoom(addRoomDto);
//        if(room==null){
//            Rooms room1 = new Rooms();
//            room1.setRoomNum(addroomDto1.getRoomNum());
//            room1.setFloorNum(addroomDto1.getFloorNum());
//            room1.setFreeRoom(addroomDto1.getFreeRoom());
//            room1.setFreeRoom(1);
//            long id=generateID();
//            room1.setRoom_id(String.valueOf(id));
//            addroom_repo.save(room1);
//
//            return "Room added successfully";
//        }
//        else{
//            return null;
//        }

    }

    @org.junit.jupiter.api.Test
    public String addMember(AddMember addMember1){

        String email = addMember1.getSEmail();

        Students student=addMember_repo.getStudentByEmail(email,addMember1.getSRoll(),addMember1.getSContact());
        if(student==null){
            Students std = new Students();
            std.setContact(addMember1.getSContact());
            std.setEmail_id(addMember1.getSEmail());
            std.setStudentName(addMember1.getSName());
            std.setRollNum(addMember1.getSRoll());
            long id=generateID();
            std.setStudent_id(String.valueOf(id));
            addMember_repo.save(std);

            return "Student added successfully";
        }
        else{
            return null;
        }

    }

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
    public String deallocateRoom(DeallocateRoom_Dto deallocateRoomDto){

        String room_id= deallocateRoomDto.getRoom_id();
        //We have to check whether the admin is adding or someone else is adding the data
        Allocation alloc=allocateroom_repo.getExistingAllocation(room_id,"1");
        if(alloc!=null){
            //System.out.println(room);
            allocateroom_repo.updateRoom(deallocateRoomDto.getRoom_id(),1);
            return "Room deallocation successful";
        }
        else{
            return "Room is already free";
        }
    }

    @org.junit.jupiter.api.Test
    public void testgetRooms(){

        List<Rooms> roomsList=new ArrayList<>();
        Rooms room1=new Rooms();
        room1.setRoom_id("R001");
        room1.setRoomNum(100);
        room1.setFloorNum(1);
        room1.setFreeRoom(1);

        Rooms room2=new Rooms();
        room2.setRoom_id("R002");
        room2.setRoomNum(200);
        room2.setFloorNum(2);
        room2.setFreeRoom(1);
        roomsList.add(room1);
        roomsList.add(room2);

        Mockito.when(getRooms_repo.getRooms(ArgumentMatchers.anyInt())).thenReturn(roomsList);
        List<Rooms> res=service.getRooms(1);
        Assertions.assertEquals(2,roomsList.size());
        //List<Rooms> roomsList=getRooms_repo.getRooms(free);
        //return roomsList;
    }

    @org.junit.jupiter.api.Test
    public List<Allocation> getAllocations(){

        List<Allocation> allocationList=allocateroom_repo.getAllocations();
        return allocationList;
    }

    @org.junit.jupiter.api.Test
    public List<Test> getPositiveStudents(){

        String result="+VE";
        List<Test> positiveStudentList=addtest_repo.getPositives(result);
        return positiveStudentList;
    }
}
