package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.CommandEntity;

public interface ICommandCustomRepository {
    CommandEntity save(CommandEntity command);
}
