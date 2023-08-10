//package com.example.ankaref.Mapper.olld;
//
//import com.example.ankaref.Mapper.olld.ModelMapperService;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//@NoArgsConstructor
//public class ModeMapperManager implements ModelMapperService {
//    private ModelMapper modelMapper;
//
//    @Override
//    public ModelMapper forRequest() {
//        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);//gevşike mapleme
//
//        return this.modelMapper;
//    }
//
//    @Override
//    public ModelMapper forResponse() {
//
//        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);//Daha sıkı bağlar sağlar.
//
//
//        return this.modelMapper;
//    }
//}
