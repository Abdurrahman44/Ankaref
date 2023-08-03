package com.example.ankaref.WeppApiControler.Kullanıcı;

import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetByIdKullanicilarResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController//annatation
@RequestMapping("/api/users")
public class UsersController {
    private UsersService usersService;




    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(value = "/login")
    public String login (@RequestBody Login loginRequest) {
        return usersService.login(loginRequest);
    }


   /* public ResponseEntity<CreatRequest> login(@RequestBody CreatRequest dto){
        var request= CreatRequest.builder().name("abdurrahman").build();
        return ResponseEntity.ok(request);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public GetByIdKullanicilarResponse getId(@PathVariable Long id) {

        return usersService.getId(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void creatUser(@RequestBody CreatRequest creatRequest) {
        this.usersService.creatRequest(creatRequest);
    }

    @PutMapping
    public void updateUser(@RequestBody UpdateRequest updateRequest) {

        this.usersService.updateRequest(updateRequest);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void deleteUser(@PathVariable long id) {

        this.usersService.deleteUser(id);
    }


}
