package com.example.ankaref.Business.concret;

import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.UpdateRequest;

import com.example.ankaref.DTO.Response.User.GetAllKullanicilarResponse;
import com.example.ankaref.DTO.Response.User.GetByIdKullanicilarResponse;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Users;
import com.example.ankaref.Mapper.ModelMapperService;

import lombok.Data;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service//Kullanici Service
@Data
public class UsersService implements com.example.ankaref.Business.Abstracts.UsersService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;

    public void KullaniciService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<GetAllKullanicilarResponse> getAll() {
        List<Users>  Users = userRepository.findAll();
        List<GetAllKullanicilarResponse> UsersResponse = Users.stream()
                .map(user -> this.modelMapperService
                        .forResponse().map(user, GetAllKullanicilarResponse.class)).collect(Collectors.toList());
        return UsersResponse;
    }

    @Override
    public GetByIdKullanicilarResponse getId(Long id) {
        Users users = userRepository.findById(id).orElseThrow();

        GetByIdKullanicilarResponse response = this.modelMapperService.forResponse().map(users, GetByIdKullanicilarResponse.class);
        return response;
    }




    @Override
    public void creatRequest(CreatRequest creatRequest) {
        Users users = this.modelMapperService.forRequest().map(creatRequest, Users.class);
        this.userRepository.save(users);
    }

    @Override
    public void updateRequest(UpdateRequest updateRequest) {
        Users users = this.modelMapperService.forRequest().map(updateRequest, Users.class);
    }



    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);

    }
}
