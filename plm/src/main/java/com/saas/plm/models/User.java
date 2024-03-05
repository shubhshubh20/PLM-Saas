package com.saas.plm.models;


import com.gogettergeeks.annotation.FileDBGenerated;
import com.gogettergeeks.annotation.Persisted;
import com.gogettergeeks.annotation.UniqueKey;
import com.saas.plm.annotation.ApiGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@AllArgsConstructor
@FileDBGenerated
public class User {

    @Id @Getter @Setter
    @Persisted
    @UniqueKey
    private String userId;

}
