package skypro.socks_sorter.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * Class socks
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "socks")
@NoArgsConstructor
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String color;
    @Min(value = 0)
    @Max(value = 100)
    private Integer cottonPart;
    @Min(value = 1, message = "minimum - 1")
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Socks socks = (Socks) o;
        return id != null && Objects.equals(id, socks.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}