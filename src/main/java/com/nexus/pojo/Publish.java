package com.nexus.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Publish {
    private int publish_id;
    private String title;
    private String description;
    private String tag;
    private Date create_time;
    private Date update_time;
    private String user_id;
}
