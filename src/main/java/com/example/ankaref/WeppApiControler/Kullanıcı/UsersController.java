package com.example.ankaref.WeppApiControler.Kullanıcı;

import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.getByIdUsersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController//annatation
@RequestMapping("/api/users")
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Tests";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Login loginRequest) {
        return usersService.login(loginRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public getByIdUsersResponse getId(@PathVariable Long id) {
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
