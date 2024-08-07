package koff.expense.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "refresh_token")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefreshToken {
    @Id
    private String id;
    private User user;
    private String token;
    private Boolean isBlacklisted;
    private Date createdDate;
    private Date expiryDate;
}
