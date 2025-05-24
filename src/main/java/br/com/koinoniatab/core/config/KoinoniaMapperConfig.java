package br.com.koinoniatab.core.config;

import org.mapstruct.MapperConfig;

@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface KoinoniaMapperConfig {
}
