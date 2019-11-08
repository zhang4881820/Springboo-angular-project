package com.zhang.myproject.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * create by zhangbo on 2019/10/31 0031
 * @Entity 注解使这个实体成为JPA实体
 * @Table 这个注解定义表名字位Users
 * @Id 标记主键
 * @GeneratedValue 标记这个ID自动生成
 * @Column 指定域或属性映射到数据库的列名
 *
 *  可以使用hibernate验证器，他是JSR 303 and JSR 349 的实现框架
 */
@Data
@Entity
@Table(name = "Users")
public class UserDTO {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @NotEmpty(message = "error.name.empty")
    @Length(max = 50,message = "error.name.length")
    @Column(name = "NAME")
    private String name;

    @NotEmpty(message = "error.address.empty")
    @Length(max = 150,message = "error.address.length")
    @Column(name = "ADDRESS")
    private String address;

    @Email(message = "error.email.email")
    @NotEmpty(message = "error.email.empty")
    @Length(max = 80,message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;
}

/**
 * 说一下hibernate的验证
 * NotNull          被这个标记的属性不能为null
 * NotEmpty         被这标记的属性((string, collection, map, or array)不能是null或空("")
 * Size             标记的变量(string, collection, map, or array)大小必须在指定的范围内
 * Length           这是执行一个更新操作
 * Email            被标记的变量必须符合email的格式
 * Min              被标记的变量必须是数字,且值大于等于指定的最小值
 * Max              被标记的变量必须是数字,且值小于等于指定的最大值
 */