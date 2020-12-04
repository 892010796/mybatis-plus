package com.example.mybatisplustest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CameraUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("reach_id")
    private String reach_id;

    @TableField("user_name")
    private String user_name;

    @ApiModelProperty(value = "phone")
    @TableField("phone")
    private String phone;

//    @ApiModelProperty(value = "是否推送(true推送，false不推送)")

//    @ApiModelProperty(value = "推送间隔(单位/分 0或者-1不限制)")
    @TableField("ip")
    private String ip;

    @TableField("email")
    private String email;
}
