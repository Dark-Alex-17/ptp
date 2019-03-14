package edu.msudenver.tsp.persistence.repository;

import edu.msudenver.tsp.persistence.dto.AccountDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<AccountDto, Integer> {
    Optional<AccountDto> findByUsername(String username);
}