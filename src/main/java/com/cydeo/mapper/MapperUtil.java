package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil
{
    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    public <T> T convert(Object objectToBeConverted, T convertedObject){

        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
    }

//    public <T> T convertToEntity(Object objectToBeConverted, T convertedObject){
//
//        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//    }
//
//    public <T> T convertToDTO(Object objectToBeConverted, T convertedObject){
//
//        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//    }
}
// If we use T type as generic above method, we don't have to provide the return type
// define your type and then you can put the return type