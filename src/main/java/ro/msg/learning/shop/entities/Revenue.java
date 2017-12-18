package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Data
@NoArgsConstructor
@Entity
@ToString(doNotUseGetters = true)
@Table(name = "REVENUES")
public class Revenue {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Location location;

    private double sum;

    private Date date;
}
