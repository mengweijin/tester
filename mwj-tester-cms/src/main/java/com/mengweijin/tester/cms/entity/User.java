package com.mengweijin.tester.cms.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mengweijin.tester.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2019-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cms_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录账号
     */
    @TableId(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "mobile_phone")
    private String mobilePhone;

    /**
     * 性别（M:男(male)， F:女(female)）
     */
    @TableField(value = "sex")
    private String sex;

}
