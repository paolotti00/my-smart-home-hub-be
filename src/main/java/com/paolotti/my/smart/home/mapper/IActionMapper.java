package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.Action;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class})
public interface IActionMapper {
    List<ActionDto> toDtoList(List<Action> actionList);

    List<Action> toModelList(List<ActionDto> actionDtoList);

    ActionDto toDto(Action action);

    Action toModel(ActionDto action);
}
