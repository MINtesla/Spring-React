package com.ssl.pem.respositories;

import com.ssl.pem.models.Tasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositories extends CrudRepository<Tasks,Long> {

}
