//package com.wiley.covid19tracker.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.NullValueCheckStrategy;
//import org.mapstruct.ReportingPolicy;
//import org.mapstruct.factory.Mappers;
//
//import com.wiley.covid19tracker.builder.ApplicationEnvironmentVariablesBuilder;
//import com.wiley.covid19tracker.dto.ApplicationEnvironmentVariables;
//
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
//public interface ApplicationEnvironmentVariablesMapper {
//
//	ApplicationEnvironmentVariablesMapper INSTANCE = Mappers.getMapper(ApplicationEnvironmentVariablesMapper.class);
//	
//	public ApplicationEnvironmentVariables mapApplicationInitPropertiesBuilderToApplicationInitProperties(ApplicationEnvironmentVariablesBuilder source);
//	
//}
