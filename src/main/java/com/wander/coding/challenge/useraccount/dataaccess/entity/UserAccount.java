package com.wander.coding.challenge.useraccount.dataaccess.entity;

import com.wander.coding.challenge.useraccount.dataaccess.type.AccountStatusType;
import com.wander.coding.challenge.useraccount.dataaccess.type.AccountType;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userid;
    private String status;
    private Date creationDate;
    private String accountType;

}
