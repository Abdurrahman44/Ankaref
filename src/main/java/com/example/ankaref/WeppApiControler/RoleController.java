package com.example.ankaref.WeppApiControler;

import com.example.ankaref.Business.Abstracts.RolService;
import com.example.ankaref.Entities.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class RoleController {
    private final RolService usersService;

    public RoleController(RolService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/roles")
    public String test() {
        return "Tests";
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<?> create(@RequestBody Role dto){
        usersService.creatRequest(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//
//    @PostMapping(value = "/save")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public void creatUser(@RequestBody Role creatRequest) {
//        this.usersService.creatRequest(creatRequest);
//    }


}
