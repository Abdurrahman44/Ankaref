package com.example.ankaref.WeppApiControler.Kullanıcı;

import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetByIdKullanicilarResponse;
import com.example.ankaref.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController//annatation
@RequestMapping("/api/users")
public class KullanicilarController {
    private UsersService usersService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired

    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public KullanicilarController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<CreatRequest> login(@RequestBody CreatRequest dto){
        var request= CreatRequest.builder().name("abdurrahman").build();
        return ResponseEntity.ok(request);
    }

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
