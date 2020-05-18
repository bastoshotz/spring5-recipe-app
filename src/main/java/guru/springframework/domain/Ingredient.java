package guru.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ind;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMesasure unitOfMesasure;

    @ManyToOne
    private Recipe recipe;

    public Long getInd() {
        return ind;
    }

    public void setInd(Long ind) {
        this.ind = ind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMesasure getUnitOfMesasure() {
        return unitOfMesasure;
    }

    public void setUnitOfMesasure(UnitOfMesasure unitOfMesasure) {
        this.unitOfMesasure = unitOfMesasure;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
