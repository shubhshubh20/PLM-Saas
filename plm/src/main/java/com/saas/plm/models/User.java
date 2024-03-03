package com.saas.plm.models;

import com.saas.plm.annotations.ApiGenerator;
import com.saas.plm.annotations.FileDBGenerated;
import com.saas.plm.annotations.Persisted;
import com.saas.plm.annotations.UniqueKey;
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
@ApiGenerator
public class User {

    @Id @Getter @Setter
    private String userId;

}
