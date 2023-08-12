package com.example.ankaref.WeppApiControler.Kullanıcı;

import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetAllUsersResponse;
import com.example.ankaref.DTO.Response.User.GetByIdUsersResponse;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController//annatation
@RequestMapping("/api/users")
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class UsersController {
    private final UsersService usersService;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    public UsersController(UsersService usersService, UserRepository userRepository) {
        this.usersService = usersService;
        this.userRepository = userRepository;
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
    public GetByIdUsersResponse getId(@PathVariable Long id) {
        return usersService.getId(id);
    }


    @GetMapping(value = "/alluser")
    public List<GetAllUsersResponse> getAll() {
        List<Users> Users = userRepository.findAll();
        List<GetAllUsersResponse> UsersResponse = Users.stream().map(user -> mapper.map(user, GetAllUsersResponse.class))
                .collect(Collectors.toList());//collection bütün verileri listelemeyi sağlar

        return UsersResponse;
    }

    @PostMapping(value = "/creat")
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
