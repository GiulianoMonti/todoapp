package todo.g.service;

import todo.g.dto.UserDtoRequest;
import todo.g.dto.UserDtoResponse;


public interface IUserService {

    UserDtoResponse registerUser(UserDtoRequest dto);

}
