package com.zhang.myproject.rest;

import com.zhang.myproject.dto.UserDTO;
import com.zhang.myproject.exception.CustomErrorType;
import com.zhang.myproject.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * create by zhangbo on 2019/10/31 0031
 *
 * @author zhangbo
 * @RestController 这是一个构造型注解，他本身使用了@Controller和@ResponseBody注解
 * @ResponseBody 注解用于定义API端点，这个注解让spring将结果渲染给调用方。
 * 要构建RESTful Web服务，控制层就要使用@RestController去处理http请求
 * @RequestMapping 这个注解用作提供路由信息。将http请求映射到相应的处理方法。这个注解也可以
 * 用作类级别上，映射http请求到控制类，或者作用于方法上，映射http请求到具体
 * 控制层方法上
 * ResponseEntity  这个类继承于HttpEntity,在控制层方法内被用于添加Http状态到响应里。
 * 他包含http状态码,headers，和body
 * @RequestBody 这个方法用于绑定一个方法参数到进来的http请求的body体里面。
 * Spring 将会使用 HttpMessageConverte 去转换 Web 请求的body体成一个依赖于请求
 * 内容类型的一个领域对象(model) .@valid 注解是可选的，他可以用作于自动验证
 * @ResponseBody 就是将http response body 的内容通过HttpMessageConverte转成JSON或XML或其他取决于
 * 请求头的内容类型
 * @PathVariable 这注解绑定一个方法参数到URI模板变量（这变量在{}中）
 * <p>
 * MediaType       这是MimeType的子类，当你使用@RequestMapping的时候你可以指定一个MediaType作为控制层方法的
 * 生产者(produces)或者消费者(Consumes)
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserRegistrationRestController {

    private UserJpaRepository userJpaRepository;

    /**
     * @param userJpaRepository 这就是为了在用户访问/api/user的时候注入userJpaRepository
     */
    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * 查找所有
     *
     * @return
     */

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        List<UserDTO> users = userJpaRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {

        log.info("创建用户:{}"+user);
        if (userJpaRepository.findByName(user.getName()) != null) {
            log.error("用户名{}已经存在"+user.getName());
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType(
                            "Unable to create new user. A User with name " + user.getName()
                                    + " already exist."),
                    HttpStatus.CONFLICT);
        }
        userJpaRepository.save(user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }

    /**
     * @param id
     * @return 根据id获取
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {

        Optional<UserDTO> optional = null;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        List<String> list = new ArrayList<>();
        list.add("application/json;charset=UTF-8");
        map.put("Content-Type",list);
        optional = userJpaRepository.findById(id);
        if (optional.isPresent()) {
            UserDTO user = optional.get();

            return new ResponseEntity<UserDTO>(user,map, HttpStatus.OK);
        }
//        使用自定义异常
        return new ResponseEntity<UserDTO>(
                new CustomErrorType("User with id " + id + "not found"),
                HttpStatus.NOT_FOUND);
    }

    /**
     * @param id
     * @param user
     * @return 修改
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user) {
        // fetch user based on id and set it to currentUser object of type UserDTO
        Optional<UserDTO> Optional = userJpaRepository.findById(id);
        if (Optional.isPresent()) {
            UserDTO currentUser = Optional.get();
            currentUser.setName(user.getName());
            currentUser.setAddress(user.getAddress());
            currentUser.setEmail(user.getEmail());
            userJpaRepository.saveAndFlush(currentUser);
            return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
        }
//        使用自定义的异常处理
        return new ResponseEntity<UserDTO>(
                new CustomErrorType("User with id " + id + "not found"),
                HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable final Long id) {

        Optional<UserDTO> option = userJpaRepository.findById(id);
        if (option.isPresent()) {
            userJpaRepository.deleteById(id);
            return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserDTO>(
                new CustomErrorType("Unable to delete. User with id "
                        + id + " not found."), HttpStatus.NOT_FOUND);
    }


}
