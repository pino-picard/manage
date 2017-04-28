package dao.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by caoxi on 2017/4/28.
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;

    @Column(name = "user_name", unique = true, length = 30)
    private String userName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value={CascadeType.ALL})
    private RoleEntity roleInfo;


}
