    package com.example.Military_Asset_Management.Contollers;

    import com.example.Military_Asset_Management.DTOs.LoginResDto;
    import com.example.Military_Asset_Management.DTOs.SignupReqDto;
    import com.example.Military_Asset_Management.DTOs.SignupResDto;
    import com.example.Military_Asset_Management.Entities.Base;
    import com.example.Military_Asset_Management.Entities.User;
    import com.example.Military_Asset_Management.Repositories.BaseRepo;
    import com.example.Military_Asset_Management.Repositories.PurchaseRepo;
    import com.example.Military_Asset_Management.Repositories.UserRepo;
    import com.example.Military_Asset_Management.Services.AdminService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;

    import java.time.LocalDate;

    @RestController
    @CrossOrigin(origins = "https://militaryassetmanagementfrontend.vercel.app")
    public class AdminController {

        @Autowired
        private AdminService adminService;

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private BaseRepo baseRepo;


        @GetMapping("/openBalance/{equipmentType}/{date}")
        public Integer getOpeningBalance(
                @PathVariable String equipmentType, @PathVariable LocalDate date, Authentication authentication)
        {
            String email= authentication.getName();
            User user=userRepo.findByUserEmail(email);
            Integer id=user.getBase().getId();
            return adminService.getOpeningBalance(id,equipmentType, date);
        }


        @GetMapping("/closingBalance/{equipmentType}/{date}")
        public Integer getClosingBalance(
                 @PathVariable String equipmentType,@PathVariable LocalDate date,Authentication authentication)
        {
            String email= authentication.getName();
            User user=userRepo.findByUserEmail(email);
            Integer id=user.getBase().getId();
            return adminService.closingBalance(id,equipmentType,date);
        }


        @GetMapping("/netMovement/{equipmentType}/{date}")
        public Integer getNetMovement(
                 @PathVariable String equipmentType,@PathVariable LocalDate date,Authentication authentication)
        {
            String email= authentication.getName();
            User user=userRepo.findByUserEmail(email);
            Integer id=user.getBase().getId();
            return adminService.NetMovement(id, equipmentType, date);
        }

        @GetMapping("/assignitem/{equipmentType}/{date}")
        public Integer getAssign(
                @PathVariable String equipmentType,@PathVariable LocalDate date,Authentication authentication)
        {
            String email=authentication.getName();
            User user=userRepo.findByUserEmail(email);
            Integer id=user.getBase().getId();

            return adminService.getItemAssign(id,equipmentType,date);
        }

        @GetMapping("/expend/{equipmentType}/{date}")
        public Integer getExpenditure(
                @PathVariable String equipmentType,@PathVariable LocalDate date,Authentication authentication)
        {
            String email=authentication.getName();
            User user=userRepo.findByUserEmail(email);
            Integer id=user.getBase().getId();
            return adminService.getExpendItem(id,equipmentType,date);
        }

        @PostMapping("/base_commander/signup")
        public SignupResDto saveBase_Commander(@RequestBody SignupReqDto signupReqDto){
            return adminService.saveBaseCommander(signupReqDto);
        }

//        @PostMapping("/admin/signup")
//        public SignupResDto saveAdmin(@RequestBody SignupReqDto signupReqDto){
//            return adminService.saveAdmin(signupReqDto);
//        }
    }
