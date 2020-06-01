package com.wiley.covid19tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.wiley.covid19tracker.entity.CovidStatistical;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrentStatisticalMapper {

	CurrentStatisticalMapper INSTANCE = Mappers.getMapper(CurrentStatisticalMapper.class);
	
	@Mappings({
		@Mapping(target = "id", ignore = true)
	})
	CovidStatistical maptoEntityCovidStatistical(Integer localTotalCases, Integer localRecovered, Integer localActiveCases, Integer localDeaths);
}
